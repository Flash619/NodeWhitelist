package com.github.flash619.nodewhitelist.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.github.flash619.nodewhitelist.NodeWhitelist;
import com.github.flash619.nodewhitelist.conf.ConfigLink;

public class Login implements Listener{
	
	public static ConfigLink Config;
	public static NodeWhitelist plugin;
	

	public Login(NodeWhitelist plugin){
		Login.plugin = plugin;
		Login.Config = new ConfigLink(plugin);
	}
	
	@EventHandler
	public void onLogin(PlayerJoinEvent event){
		Player Player = event.getPlayer();
		if(!Player.hasPermission("NodeWhitelist.Whitelisted")){
			if(!Config.GetString("General.WhitelistingWebsite").equals(null)&&(Config.GetBoolean("General.EnableWebsiteMessages"))){
				Player.sendMessage(ChatColor.RED + "[INFO]: " + ChatColor.WHITE + "Please visit: " + ChatColor.DARK_PURPLE + Config.GetString("General.WhitelistingWebsite") + ChatColor.WHITE + " for whitelisting.");
			}
			if(Config.GetBoolean("NoneWhitelisted.Restraints.LoginNotice")&&(!Config.GetString("NoneWhitelisted.Messages.Login").equals(null))){
				Player.sendMessage(Config.GetString("NoneWhitelisted.Messages.Login"));
			}
			
		}
	}

}
