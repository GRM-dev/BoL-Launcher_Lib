package pl.grm.bol.lib.net;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;

import pl.grm.bol.lib.Config;
import pl.grm.bol.lib.FileOperation;
import pl.grm.bol.lib.MD5HashChecksum;
import pl.grm.bol.lib.TypeOfProject;

public class Updater extends SwingWorker<Boolean, Void> {
	private String			pathToFile	= "";
	private int				fileSize;
	private UpdateFrame		frame;
	private Logger			logger;
	private TypeOfProject	runType;
	
	public Updater(UpdateFrame frameT) {
		frame = frameT;
		logger = FileOperation.setupLogger("Updater.log");
		runType = frame.getRunningType();
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		try {
			switch (runType) {
				case LAUNCHER :
					updateUpdater();
					break;
				case UPDATER :
					updateLauncher();
					break;
				default :
					break;
			}
			return false;
		}
		catch (Exception ex) {
			frame.getProgressBar().setValue(0);
			logger.log(Level.SEVERE, ex.toString(), ex);
			JOptionPane.showMessageDialog(frame, "Error executing upload task: " + ex.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
	
	private void updateUpdater() throws InvalidFileFormatException, IOException {
		String fileName = checkoutUpdaterVersion();
		pathToFile = Config.SERVER_DOWNLOAD_LINK + fileName;
		if (!correctFileExists(fileName)) {
			downloadFile();
		}
		startProcess(Config.DIRECTORY_MAIN, fileName);
	}
	
	private void updateLauncher() throws InvalidFileFormatException, IOException {
		String fileName = checkoutLauncherVersion();
		pathToFile = Config.SERVER_DOWNLOAD_LINK + fileName;
		if (!correctFileExists(fileName)) {
			downloadFile();
		}
		startProcess(Config.DIRECTORY_MAIN, fileName);
	}
	
	private void downloadFile() {
		DownloadTask task = new DownloadTask(frame, pathToFile, fileSize);
		task.addPropertyChangeListener(frame);
		task.execute();
	}
	
	@Override
	protected void done() {
		try {
			if (super.get()) {
				Thread.sleep(2000L);
				System.exit(0);
			}
		}
		catch (InterruptedException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		catch (ExecutionException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
	}
	
	/**
	 * Check version of launcher on the web server.
	 * 
	 * @return fileName
	 * @throws IOException
	 * @throws InvalidFileFormatException
	 */
	private String checkoutUpdaterVersion() throws InvalidFileFormatException, IOException {
		Ini sIni = new Ini();
		URL url = new URL(Config.SERVER_VERSION_LINK);
		sIni.load(url);
		String version = sIni.get("Updater", "last_version");
		String fileName = TypeOfProject.UPDATER.getFileNamePrefix() + version + Config.RELEASE_TYPE;
		return fileName;
	}
	
	private String checkoutLauncherVersion() throws InvalidFileFormatException, IOException {
		Ini sIni = new Ini();
		URL url = new URL(Config.SERVER_VERSION_LINK);
		sIni.load(url);
		String version = sIni.get("Launcher", "last_version");
		String fileName = TypeOfProject.LAUNCHER.getFileNamePrefix() + version
				+ Config.RELEASE_TYPE;
		return fileName;
	}
	
	/**
	 * Kills specified process of Launcher
	 * 
	 * @param processStringPID
	 * @return list Of Process
	 * @throws IOException
	 */
	public static ArrayList<String> killExec(String processStringPID) throws IOException {
		String outStr = "";
		ArrayList<String> processOutList = new ArrayList<String>();
		int i = -1;
		Process p = Runtime.getRuntime().exec(processStringPID);
		InputStream in = p.getInputStream();
		x11 : while ((i = in.read()) != -1) {
			if ((char) i == '\n') {
				processOutList.add((outStr));
				outStr = "";
				continue x11;
			}
			outStr += (char) i;
		}
		return processOutList;
	}
	
	/**
	 * If file exists than check checksum
	 * 
	 * @return true if correct file already exists on computer
	 */
	private boolean correctFileExists(String fileName) {
		File file = new File(Config.BOL_MAIN_PATH + fileName);
		if (file.exists()) {
			try {
				return MD5HashChecksum.isFileCorrect(new File(fileName));
			}
			catch (IOException e) {
				logger.log(Level.SEVERE, e.toString(), e);
			}
		}
		return false;
	}
	
	private boolean startProcess(File confDir, String fileName) {
		logger.info("Starting Process...");
		String launcherJarAbsPath;
		try {
			launcherJarAbsPath = FileOperation.getCurrentJarPath(UpdaterOld.class);
			ProcessBuilder processBuilder = new ProcessBuilder(Config.JAVA_PATH, "-jar",
					Config.BOL_MAIN_PATH + fileName, launcherJarAbsPath, Config.LAUNCHER_PID);
			processBuilder.directory(confDir);
			processBuilder.start();
			return true;
		}
		catch (UnsupportedEncodingException e1) {
			logger.log(Level.SEVERE, e1.toString(), e1);
		}
		catch (IOException e) {
			logger.log(Level.SEVERE, e.toString(), e);
		}
		return false;
	}
}
