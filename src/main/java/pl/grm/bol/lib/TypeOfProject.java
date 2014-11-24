package pl.grm.bol.lib;

/**
 * Specifies the type of project running lib.
 */
public enum TypeOfProject {
	LAUNCHER(
			"Launcher",
			"BoL-Launcher-") ,
	UPDATER(
			"Updater",
			"BoL-Launcher_Updater-") ,
	GAME(
			"Game",
			"BattleOfLegends-") ,
	SERVER(
			"Server",
			"BoL-Server-");
	
	private String	projectName;
	private String	fileNamePrefix;
	
	private TypeOfProject(String projectName, String fileNamePrefix) {
		this.projectName = projectName;
		this.fileNamePrefix = fileNamePrefix;
	}
	
	public String getProjectName() {
		return this.projectName;
	}
	
	public String getFileNamePrefix() {
		return this.fileNamePrefix;
	}
}
