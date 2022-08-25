package com.Configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.INIConfiguration;

@SuppressWarnings("deprecation")
public class IniConfigurationFactory extends ConfigurationFactory{

	@Override
	public Configuration create() {
		try {
			INIConfiguration config = new INIConfiguration(getCmd().getOptionValue("c"));
			return new Configuration(config.getString("log.LogPath"),config.getString("log.LogLevel"));
		}
		catch (ConfigurationException cex)
		{
		    System.out.println("No such file!");
		    return null;
		}
	}

}
