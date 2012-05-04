package com.github.flash619.nodewhitelist.listeners;

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
			if(Config.GetBoolean("NoneWhitelisted.Restraints.LoginNotice")){
				Player.sendMessage(Config.GetString("NoneWhitelisted.Messages.Login"));
			}
			
		}
	}

}
