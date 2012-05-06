package com.github.flash619.nodewhitelist.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.github.flash619.nodewhitelist.NodeWhitelist;
import com.github.flash619.nodewhitelist.Commands.whitelistWaive;
import com.github.flash619.nodewhitelist.conf.ConfigLink;

public class Logout implements Listener{
	public static ConfigLink Config;
	public static NodeWhitelist plugin;
	
	public Logout(NodeWhitelist plugin){
		Logout.plugin = plugin;
	}
	
	@EventHandler
	public void onLogout(PlayerQuitEvent event){
		Player player = event.getPlayer();
		if(whitelistWaive.isWaived(player)){
			whitelistWaive.RemoveWaive(player);
		}
	}

}
