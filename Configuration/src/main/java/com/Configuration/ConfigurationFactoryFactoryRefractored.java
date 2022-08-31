package com.Configuration;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ConfigurationFactoryFactoryRefractored {
	public static ConfigurationFactory create(String[] args) throws ParseException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		CommandLine cmd = readArgs(args);
		String factoryName = getFactoryName(cmd);
		return createFactoryByName(factoryName,cmd);
	}
	private static CommandLine readArgs(String[] args) throws ParseException {
		Options options = new Options();
		
		options.addOption("c","configuration",true,"config file");
		options.addOption("ll","log-level",true,"log level");
		options.addOption("lp","log-path",true,"log path");
		
		return new DefaultParser().parse(options, args);
	}
	private static String getFactoryName(CommandLine cmd) {
		if(cmd.hasOption("c")) {
			String fileFormat = getFileFormat(cmd.getOptionValue("c"));
			if(fileFormat == "Cli")
				throw new IllegalArgumentException();
			return "com.Configuration." + fileFormat + "ConfigurationFactory";
		}
		else if(cmd.hasOption("ll") && cmd.hasOption("lp")){
			return "com.Configuration.CLIConfigurationFactory";
		}
		else {
			throw new IllegalArgumentException();
		}
	}
	
	private static String getFileFormat(String fileName) {
		String format = fileName.substring(fileName.indexOf(".") + 1);
		return format.substring(0,1).toUpperCase() + format.substring(1);
	}
	
	private static ConfigurationFactory createFactoryByName(String className,CommandLine cmd) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> factory = Class.forName(className);
		if( ! ConfigurationFactory.class.isAssignableFrom(factory)) {
			throw new IllegalArgumentException("Class " + className + " exists but does not implement ConfigurationFactory");
		}
		ConfigurationFactory k = (ConfigurationFactory) factory.getConstructor(cmd.getClass()).newInstance(cmd);
		return k;
	}
}
