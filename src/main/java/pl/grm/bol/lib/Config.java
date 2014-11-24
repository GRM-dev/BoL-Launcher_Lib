package pl.grm.bol.lib;

import java.awt.Color;
import java.io.File;

public class Config {
	public static final String	SERVER_SITE_LINK		= "http://grm-dev.pl/";
	public static final String	SERVER_DOWNLOAD_LINK	= SERVER_SITE_LINK + "jenkins/artifacts/";
	public static final String	SERVER_LINK				= "localhost";
	public static final String	SERVER_VERSION_LINK		= SERVER_SITE_LINK + "bol/version.ini";
	public static final String	CONFIG_FILE_NAME		= "config.ini";
	public static final String	APP_DATA				= System.getenv("APPDATA");
	public static final String	BOL_MAIN_PATH			= APP_DATA + "\\BOL\\";
	public static final String	BOL_GAME_PATH			= BOL_MAIN_PATH + "\\Game\\";
	public static final String	BOL_LAUNCHER_PATH		= BOL_MAIN_PATH + "\\Launcher\\";
	public static final String	RELEASE_TYPE			= "-SNAPSHOT.jar";
	public static final String	SEPARATOR				= System.getProperty("file.separator");
	public static final String	JAVA_PATH				= System.getProperty("java.home")
																+ SEPARATOR + "bin" + SEPARATOR
																+ "java";
	public static final String	USER_DIR				= System.getProperty("user.dir");
	public static final String	LAUNCHER_PID			= FileOperation.getProcessId(USER_DIR)
																.trim();
	public static final String	CHECKSUMLIST			= "http://grm-dev.pl/jenkins/checkList.chk";
	public static final Color[]	PROGRESS_BAR_COLORS		= {
			Color.BLUE, Color.CYAN, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED,
			Color.YELLOW								};
	public static final File	DIRECTORY_MAIN			= new File(Config.BOL_MAIN_PATH);
}
