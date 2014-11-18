package pl.grm.bol.lib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.MessageDigest;

public class MD5HashChecksum {
	private static final String	CHECKSUMLIST	= Config.CHECKSUMLIST;
	
	/**
	 * Checks if the specified file is without any errors and modifications.
	 * 
	 * @param file
	 * @return true if file is correct
	 * @throws IOException
	 */
	public static boolean isFileCorrect(File file) throws IOException {
		if (file.exists()) {
			String checksum = getOriginalChecksumForFile(file);
			if (equalmd5Checksums(checksum, file)) { return true; }
		}
		return false;
	}
	
	/**
	 * Compares if the specified file checksum is equal to specified checksum
	 * 
	 * @param checksum
	 * @param file
	 * @return true if specified file is correct
	 */
	public static boolean equalmd5Checksums(String checksum, File file) {
		if (checksum.equals(getFileChecksum(file))) { return true; }
		return false;
	}
	
	/**
	 * Generates md5 checksum for specified file
	 * 
	 * @param file
	 * @return checksum
	 */
	public static String getFileChecksum(File file) {
		try {
			InputStream fin = new FileInputStream(file);
			java.security.MessageDigest md5er = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int read;
			do {
				read = fin.read(buffer);
				if (read > 0)
					md5er.update(buffer, 0, read);
			}
			while (read != -1);
			fin.close();
			byte[] digest = md5er.digest();
			if (digest == null)
				return null;
			String strDigest = "0x";
			for (int i = 0; i < digest.length; i++) {
				strDigest += Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1)
						.toUpperCase();
			}
			String hash = strDigest.toLowerCase();
			if (hash.substring(0, 3).contains("0x")) {
				hash = hash.substring(2);
			}
			return hash;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * Checkout the specified file name checksum on the web server
	 * 
	 * @param file
	 * @return Original checkSum of file
	 * @throws IOException
	 */
	public static String getOriginalChecksumForFile(File file) throws IOException {
		String fileName = file.getName();
		URL webFile = new URL(CHECKSUMLIST);
		BufferedReader in = new BufferedReader(new InputStreamReader(webFile.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			if (inputLine.contains(fileName)) {
				String hash = inputLine.substring(0, 32);
				return hash;
			}
		in.close();
		return null;
	}
}
