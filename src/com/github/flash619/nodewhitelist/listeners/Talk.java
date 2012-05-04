package com.github.flash619.nodewhitelist.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import com.github.flash619.nodewhitelist.NodeWhitelist;
import com.github.flash619.nodewhitelist.conf.ConfigLink;

public class Talk implements Listener{
	
	public static ConfigLink Config;
	public static NodeWhitelist plugin;
	
	public Talk(NodeWhitelist plugin){
		Talk.plugin = plugin;
		Talk.Config = new ConfigLink(plugin);
	}
	
	@EventHandler
	public void onTalk(PlayerChatEvent event){
		Player player = event.getPlayer();
		if(!player.hasPermission("NodeWhitelist.Whitelisted")){
			if(!Config.GetBoolean("NoneWhitelisted.Restraints.CanTalk")){
				player.sendMessage(Config.GetString("NoneWhitelisted.Messages.Speak"));
				event.setCancelled(true);
			}
		}
	}
	
	

}
