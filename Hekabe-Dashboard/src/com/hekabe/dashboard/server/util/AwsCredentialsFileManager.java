package com.hekabe.dashboard.server.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AwsCredentialsFileManager {

	private static final String ACCESS_KEY = "accessKey";
	private static final String SECRET_ACCESS_KEY = "secretKey";
	
	private String fileName;
	private String accessKey;
	private String secretAccessKey;
	
	/**
	 * 
	 * @param accessKey
	 * @param secretAccessKey
	 */
	public AwsCredentialsFileManager(String accessKey, String secretAccessKey) {
		this.accessKey = accessKey;
		this.secretAccessKey = secretAccessKey;
	}
	
	/**
	 * Creates aws credentials .properties file with current timestamp as its filename.
	 * @return Filename
	 */
	public String createFile() {
		Properties awsCred = new Properties();
		awsCred.setProperty(ACCESS_KEY, accessKey);
		awsCred.setProperty(SECRET_ACCESS_KEY, secretAccessKey);
		
		createTimestampFilename();
		writeFile(awsCred);
		
		return fileName;
	}
	
	/**
	 * Writes awsCred in properties file
	 * @param awsCred
	 */
	private void writeFile(Properties awsCred) {
		File file = new File("WEB-INF/classes/" + fileName);
		
		try {
			awsCred.store(new FileOutputStream(file), "AWS Credentials");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Sets fileName as TIMESTAMP.properties
	 */
	private void createTimestampFilename() {
		long currentTimeMillis = System.currentTimeMillis();
		String currentTimeMillisAsString = String.valueOf(currentTimeMillis);
		
		StringBuilder sb = new StringBuilder(currentTimeMillisAsString);
		sb.append(".properties");
		
		fileName = sb.toString();
	}

	/**
	 * 
	 * @return Filename
	 */
	public String getFileName() {
		return fileName;
	}
}
 