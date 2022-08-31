package com.Configuration;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class XmlConfigurationFactory extends ConfigurationFactory{


	public XmlConfigurationFactory(CommandLine cmd) {
		super(cmd);
	}

	@Override
	public Configuration create() {
		try
		{
			 XMLConfiguration config = new XMLConfiguration(getCmd().getOptionValue("c"));
			 return new Configuration(config.getString("log.path"),config.getString("log.level"));
		}
		catch (ConfigurationException cex)
		{
		    System.out.println("No such file!");
		    return null;
		}
	}

}
