package com.Configuration;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ConfigurationFactoryFactory {
	public static ConfigurationFactory create(String[] args) throws ParseException, InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Options options = new Options();
		
		options.addOption("c","configuration",true,"config file");
		options.addOption("ll","log-level",true,"log level");
		options.addOption("lp","log-path",true,"log path");
		
		CommandLine cmd = new DefaultParser().parse(options, args);
		ConfigurationFactory returnValue;
		if(cmd.hasOption("c")) {
			returnValue = createConfigurationFactoryFromString(cmd);
		}
		else if(cmd.hasOption("ll") && cmd.hasOption("lp")){
			returnValue = new CLIConfigurationFactory(cmd);
		}
		else {
			return null;
		}
		return returnValue;
	}
	
	public static String getFileFormat(String fileName) {
		String format = fileName.substring(fileName.indexOf(".") + 1);
		return format.substring(0,1).toUpperCase() + format.substring(1);
	}
	
	public static ConfigurationFactory createConfigurationFactoryFromString(CommandLine cmd) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String fileFormat = getFileFormat(cmd.getOptionValue("c"));
		if(fileFormat.equals("Cli")) {
			throw new IllegalArgumentException("Cli is not a file extension");
		}
		String className = "com.Configuration." +fileFormat + "ConfigurationFactory";
		Class<?> factory = Class.forName(className);
		if( ! ConfigurationFactory.class.isAssignableFrom(factory)) {
			throw new IllegalArgumentException("Class " + className + " exists but does not implement ConfigurationFactory");
		}
		ConfigurationFactory k = (ConfigurationFactory) factory.getConstructor(cmd.getClass()).newInstance(cmd);
		return k;
	}
}
