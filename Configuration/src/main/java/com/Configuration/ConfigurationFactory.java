package com.Configuration;

import org.apache.commons.cli.CommandLine;

public abstract class ConfigurationFactory {
	private CommandLine cmd;
	
	public ConfigurationFactory(CommandLine cmd) {
		this.cmd = cmd;
	}
	
	public abstract Configuration create();
	
	public CommandLine getCmd() {
		return cmd;
	}
}
