package com.Configuration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class ConfigurationFactoryFactory {
	public static ConfigurationFactory create(String[] args) throws ParseException, InstantiationException, IllegalAccessException, ClassNotFoundException {

		CommandLine cmd = processCommandLineArguments();
		String factoryClassName = determineFactoryNameFromCli(cmd);
		return createFactoryByName(factoryClassName);
	}
	
	private CommandLine processCommandLineArguments() {
		Options options = new Options();
		
		options.addOption("c","configuration",true,"config file");
		options.addOption("ll","log-level",true,"log level");
		options.addOption("lp","log-path",true,"log path");
		
		return new DefaultParser().parse(options, args);
	}

	public static String getFileFormat(String fileName) {
		String format = fileName.substring(fileName.indexOf(".") + 1);
		return format.substring(0,1).toUpperCase() + format.substring(1);
	}
	
	private String determineFactoryNameFromCli(CommandLine cmd) {
		if(cmd.hasOption("c")) {
			String fileFormat = getFileFormat(cmd.getOptionValue("c"));
			if(fileFormat.equals("Cli")) {
				throw new IllegalArgumentException("Cli is not a file extension");
			}
			return "com.Configuration." +fileFormat + "ConfigurationFactory";
		}
		else if(cmd.hasOption("ll") && cmd.hasOption("lp")){
			return "com.Configuration.CLIConfigurationFactory";
		}
		else {
			throw new Exception("Incorrect CLI arguments");
		}
	}

	private ConfigurationFactory createFactoryByName(String className, CommandLine cmd) {
		Class<?> factory = Class.forName(className);
		
		if( ! ConfigurationFactory.class.isAssignableFrom(factory)) {
			throw new IllegalArgumentException("Class " + className + " exists but does not implement ConfigurationFactory");
		}

		return factory.getConstructor(CommandLine.class).newInstance(cmd);
	}

}
