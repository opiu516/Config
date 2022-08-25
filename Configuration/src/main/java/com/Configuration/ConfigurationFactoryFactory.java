package com.Configuration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ConfigurationFactoryFactory {
	public static ConfigurationFactory create(String[] args) throws ParseException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Options options = new Options();
		
		options.addOption("c","configuration",true,"config file");
		options.addOption("ll","log-level",true,"log level");
		options.addOption("lp","log-path",true,"log path");
		
		CommandLine cmd = new DefaultParser().parse(options, args);
		ConfigurationFactory returnValue;
		if(cmd.hasOption("c")) {
			returnValue = createConfigurationFactoryFromString(cmd.getOptionValue("c"));
		}
		else if(cmd.hasOption("ll") && cmd.hasOption("lp")){
			returnValue = new CLIConfigurationFactory();
		}
		else {
			return null;
		}
		returnValue.setCmd(cmd);
		return returnValue;
	}
	
	public static String getFileFormat(String fileName) {
		String format = fileName.substring(fileName.indexOf(".") + 1);
		return format.substring(0,1).toUpperCase() + format.substring(1);
	}
	
	public static ConfigurationFactory createConfigurationFactoryFromString(String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String fileFormat = getFileFormat(name);
		if(fileFormat.equals("Cli")) {
			throw new IllegalArgumentException("Cli is not a file extension");
		}
		String className = "com.Configuration." +fileFormat + "ConfigurationFactory";
		Class<?> factory = Class.forName(className);
		if( ! ConfigurationFactory.class.isAssignableFrom(factory)) {
			throw new IllegalArgumentException("Class " + className + " exists but does not implement ConfigurationFactory");
		}
		@SuppressWarnings("deprecation")
		ConfigurationFactory k = (ConfigurationFactory) factory.newInstance();
		return k;
	}
}
