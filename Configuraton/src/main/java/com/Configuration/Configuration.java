package com.Configuration;

public class Configuration {
	private String logFilePath;
	private String logLevel;
	
	public Configuration(String logFilePath,String logLevel){
		this.logFilePath = logFilePath;
		this.logLevel = logLevel;
	}
	
	public String getLogFilePath() {
		return this.logFilePath;
	}
	
	public String getLogLevel() {
		return this.logLevel;
	}
}
