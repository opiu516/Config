package com.Configuration;

public class CLIConfigurationFactory extends ConfigurationFactory{

	//REVIEW Added an empty constructor
	public CLIConfigurationFactory(CommandLine cmd) {

	}

	@Override
	public Configuration create() {		
		return new Configuration(getCmd().getOptionValue("lp"),getCmd().getOptionValue("ll"));
	}

}
