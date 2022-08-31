package com.Configuration;

import org.apache.commons.cli.CommandLine;

public class CLIConfigurationFactory extends ConfigurationFactory{


	public CLIConfigurationFactory(CommandLine cmd) {
		super(cmd);
	}

	@Override
	public Configuration create() {		
		return new Configuration(getCmd().getOptionValue("lp"),getCmd().getOptionValue("ll"));
	}

}
