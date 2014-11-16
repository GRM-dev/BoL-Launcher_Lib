package pl.grm.bol.lib;

public class Config {
	public static final String	SERVER_LINK			= "http://grm-dev.pl/";
	public static final String	SERVER_VERSION_LINK	= SERVER_LINK + "bol/version.ini";
	public static final String	APP_DATA			= System.getenv("APPDATA");
	public static final String	BOL_CONF_PATH		= APP_DATA + "\\BOL\\";
	public static final String	CONFIG_FILE_NAME	= "config.ini";
}
