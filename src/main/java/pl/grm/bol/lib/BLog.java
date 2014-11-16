package pl.grm.bol.lib;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JTextArea;

public class BLog {
	private Logger		logger;
	private JTextArea	console;
	
	/**
	 * Constructor of BOL Logger.
	 * 
	 * @param fileName
	 *            name of log file
	 */
	public BLog(String fileName) {
		Logger loggerR = null;
		try {
			loggerR = FileOperation.setupLogger(fileName);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		this.logger = loggerR;
	}
	
	public synchronized void log(Level level, String msg, Throwable thrown) {
		logger.log(level, msg, thrown);
		if (console != null) {
			console.append(msg + "\n");
		}
	}
	
	public synchronized void info(String msg) {
		logger.info(msg);
		if (console != null) {
			console.append(msg + "\n");
		}
	}
	
	public JTextArea getConsole() {
		return console;
	}
	
	public void setConsole(JTextArea console) {
		this.console = console;
	}
}
