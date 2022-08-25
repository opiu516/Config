package com.Configuration;

public class CLIConfigurationFactory extends ConfigurationFactory{


	@Override
	public Configuration create() {		
		return new Configuration(getCmd().getOptionValue("lp"),getCmd().getOptionValue("ll"));
	}

}
