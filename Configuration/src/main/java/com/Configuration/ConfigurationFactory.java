package com.Configuration;

import org.apache.commons.cli.CommandLine;

public abstract class ConfigurationFactory {
	private CommandLine cmd;
	
	public abstract Configuration create();
	
	public CommandLine getCmd() {
		return cmd;
	}
	
	public void setCmd(CommandLine cmd) {
		this.cmd = cmd;
	}
}
