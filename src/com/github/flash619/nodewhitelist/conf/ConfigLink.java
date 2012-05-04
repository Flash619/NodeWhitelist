package com.github.flash619.nodewhitelist.conf;

import org.bukkit.configuration.file.FileConfiguration;

import com.github.flash619.nodewhitelist.NodeWhitelist;

public class ConfigLink {
	
	private static NodeWhitelist plugin;
	
	public ConfigLink(NodeWhitelist plugin){
		ConfigLink.plugin = plugin;
	}
		
	public void LoadConfig(){
		final FileConfiguration config = plugin.getConfig();
		//Add config defaults
		config.addDefault("General.WhitelistEnabled", true);
		config.addDefault("NoneWhitelisted.Restraints.Interact", false);
		config.addDefault("NoneWhitelisted.Restraints.CanTalk", false);
		config.addDefault("NoneWhitelisted.Restraints.LoginNotice", true);
		config.addDefault("NoneWhitelisted.Messages.Interact", "You are not whitelisted!");
		config.addDefault("NoneWhitelisted.Messages.Speak", "You are not whitelisted and thus, cannot speak.");
		config.addDefault("NoneWhitelisted.Messages.Login", "You are not whitelisted! Please visit www.myserver.com for whitelisting.");
		
		plugin.getConfig().options().copyDefaults(true);
		plugin.saveConfig();
	}
	public boolean GetBoolean(String ConfigLine){
		boolean Value = plugin.getConfig().getBoolean(ConfigLine);
		return(Value);
	}
	public String GetString(String ConfigLine){
		String ValueString = plugin.getConfig().getString(ConfigLine);
		return(ValueString);
	}

}
