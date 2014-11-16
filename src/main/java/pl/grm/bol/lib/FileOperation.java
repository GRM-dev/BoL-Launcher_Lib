package pl.grm.bol.lib;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.net.URLDecoder;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

public class FileOperation {
	private static Logger	logger;
	
	public static String getCurrentJar(Class<?> classHandler) throws UnsupportedEncodingException {
		String jarFileLoc = "";
		jarFileLoc = URLDecoder.decode(classHandler.getProtectionDomain().getCodeSource()
				.getLocation().getPath(), "UTF-8");
		jarFileLoc = jarFileLoc.replace("file:/", "");
		int index = 100;
		if (jarFileLoc.contains("!")) {
			index = jarFileLoc.indexOf("!");
			jarFileLoc = jarFileLoc.substring(0, index);
		}
		if (jarFileLoc.contains("/")) {
			index = jarFileLoc.indexOf("!");
			if (index == 0) {
				jarFileLoc = jarFileLoc.substring(1, jarFileLoc.length());
			}
		}
		return jarFileLoc;
	}
	
	public static String getProcessId(final String fallback) {
		final String jvmName = ManagementFactory.getRuntimeMXBean().getName();
		final int index = jvmName.indexOf('@');
		if (index < 1) { return fallback; }
		try {
			return Long.toString(Long.parseLong(jvmName.substring(0, index)));
		}
		catch (NumberFormatException e) {}
		return fallback;
	}
	
	public static Logger setupLogger(String fileName) throws IllegalArgumentException {
		logger = Logger.getLogger(fileName);
		try {
			FileHandler fileHandler = new FileHandler(Config.BOL_CONF_PATH + fileName, 1048476, 1,
					true);
			logger.addHandler(fileHandler);
			SimpleFormatter formatter = new SimpleFormatter();
			fileHandler.setFormatter(formatter);
		}
		catch (SecurityException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		logger.info("Config&Log Location: " + Config.BOL_CONF_PATH);
		logger.info("Logger is running ...");
		return logger;
	}
	
	public static Wini readConfigFile(Class<?> classHandler) throws IllegalArgumentException,
			SecurityException {
		File dir = new File(Config.BOL_CONF_PATH);
		Wini ini = null;
		if (!dir.exists()) {
			dir.mkdir();
		}
		File file = new File(Config.BOL_CONF_PATH + Config.CONFIG_FILE_NAME);
		if (!file.exists()) {
			createIniFile(file, classHandler);
		}
		ini = readIni(file);
		return ini;
	}
	
	public static void writeConfigParamLauncher(Wini ini, String param, String value)
			throws IOException {
		ini.put("Launcher", param, value);
		ini.store();
	}
	
	private static Wini readIni(File file) {
		Wini ini = null;
		try {
			ini = new Wini(file);
		}
		catch (InvalidFileFormatException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		return ini;
	}
	
	private static void createIniFile(File file, Class<?> classHandler) {
		Wini ini = null;
		try {
			file.createNewFile();
			ini = new Wini(file);
			ini.put("Launcher", "version", classHandler.getDeclaredField("LAUNCHER_VERSION"));
			ini.put("Launcher", "login", "");
			ini.put("Launcher", "pass", "");
			ini.put("Game", "version", "0.0.0");
			ini.put("Graphic", "resolutionX", "");
			ini.put("Graphic", "resolutionY", "");
			ini.put("Sound", "master_volume", "100");
			ini.store();
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		catch (NoSuchFieldException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		catch (SecurityException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
	}
}
