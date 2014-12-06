package pl.grm.bol.lib.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import pl.grm.bol.lib.Config;

public class DownloadTask extends SwingWorker<Void, Void> {
	private static final int	BUFFER_SIZE		= 4096;
	private String				downloadURL;
	private String				saveDirectory	= Config.BOL_MAIN_PATH;
	private JFrame				gui;
	private long				fileSize		= 0;
	private Updater				updater;
	
	public DownloadTask(Updater updater, JFrame gui, String downloadURL) {
		this.updater = updater;
		this.gui = gui;
		this.downloadURL = downloadURL;
	}
	
	/**
	 * Executed in background thread
	 */
	@Override
	protected Void doInBackground() throws Exception {
		try {
			HTTPDownloadUtil util = new HTTPDownloadUtil();
			util.downloadFile(downloadURL);
			fileSize = util.getContentLength();
			String saveFilePath = saveDirectory + File.separator + util.getFileName();
			InputStream inputStream = util.getInputStream();
			FileOutputStream outputStream = new FileOutputStream(saveFilePath);
			byte[] buffer = new byte[BUFFER_SIZE];
			int bytesRead = -1;
			long totalBytesRead = 0;
			int percentCompleted = 0;
			fileSize = util.getContentLength();
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
				totalBytesRead += bytesRead;
				percentCompleted = (int) (totalBytesRead * 100 / fileSize);
				setProgress(percentCompleted);
			}
			outputStream.close();
			util.disconnect();
		}
		catch (IOException ex) {
			JOptionPane.showMessageDialog(gui, "Error downloading file: " + ex.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
			setProgress(0);
			cancel(true);
		}
		return null;
	}
	
	/**
	 * Executed in Swing's event dispatching thread
	 */
	@Override
	protected void done() {
		if (!isCancelled()) {
			JOptionPane.showMessageDialog(gui, "Update has been downloaded successfully!",
					"Update Message", JOptionPane.INFORMATION_MESSAGE);
		}
		updater.notifyUpdater();
	}
}
