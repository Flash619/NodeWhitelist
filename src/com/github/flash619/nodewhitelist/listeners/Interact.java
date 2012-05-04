package com.github.flash619.nodewhitelist.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.github.flash619.nodewhitelist.NodeWhitelist;
import com.github.flash619.nodewhitelist.conf.ConfigLink;

public class Interact implements Listener{
	
	public static ConfigLink Config;
	public static NodeWhitelist plugin;
	
	public Interact(NodeWhitelist plugin){
		Interact.plugin = plugin;
		Interact.Config = new ConfigLink(plugin);
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onPlaceBlock(BlockPlaceEvent event){
		Player player = event.getPlayer();
		
		if(!player.hasPermission("NodeWhitelist.Whitelisted")){
			if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
				player.sendMessage(Config.GetString("NoneWhitelisted.Messages.Interact"));
				event.setCancelled(true);
			}
		}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onBreakBlock(BlockBreakEvent event){
		Player player = event.getPlayer();
		
		if(!player.hasPermission("NodeWhitelist.Whitelisted")){
			if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
				player.sendMessage(Config.GetString("NoneWhitelisted.Messages.Interact"));
				event.setCancelled(true);
			}
		}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPickupItem(PlayerPickupItemEvent event){
		Player player = event.getPlayer();
		if(!player.hasPermission("NodeWhitelist.Whitelisted")){
			if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
				event.setCancelled(true);
			}
		}
	}

}
