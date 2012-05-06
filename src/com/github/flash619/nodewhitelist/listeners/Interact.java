package com.github.flash619.nodewhitelist.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

import com.github.flash619.nodewhitelist.NodeWhitelist;
import com.github.flash619.nodewhitelist.Commands.whitelistWaive;
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
			if(!whitelistWaive.isWaived(player)){
			if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
				if(!Config.GetString("NoneWhitelisted.Messages.Interact").equals(null)){
				player.sendMessage(Config.GetString("NoneWhitelisted.Messages.Interact"));
				}
				event.setCancelled(true);
			}
		}
	}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void onBreakBlock(BlockBreakEvent event){
		Player player = event.getPlayer();
		if(!player.hasPermission("NodeWhitelist.Whitelisted")){
			if(!whitelistWaive.isWaived(player)){
			if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
				if(!Config.GetString("NoneWhitelisted.Messages.Interact").equals(null)){
				player.sendMessage(Config.GetString("NoneWhitelisted.Messages.Interact"));
				}
				event.setCancelled(true);
			}
		}
	}
	}
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPickupItem(PlayerPickupItemEvent event){
		Player player = event.getPlayer();
		if(!player.hasPermission("NodeWhitelist.Whitelisted")){
			if(!whitelistWaive.isWaived(player)){
			if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
				event.setCancelled(true);
			}
		}
	}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void InteractDamageMob(EntityDamageEvent event){
		if(event.getCause() == DamageCause.ENTITY_ATTACK){
			final EntityDamageByEntityEvent realEvent = (EntityDamageByEntityEvent) event;
			if(realEvent.getDamager() instanceof Player){
				String damagerName = ((Player) realEvent.getDamager()).getName();
				Player HitingPlayer = Bukkit.getPlayer(damagerName);
				if(!HitingPlayer.hasPermission("NodeWhitelist.Whitelisted")){
					if(!whitelistWaive.isWaived(HitingPlayer)){
					if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
					event.setCancelled(true);
					}
				}
			}
		}
	}
	}
	@EventHandler(priority = EventPriority.HIGH)
	public void InteractAggroMob(EntityTargetEvent event){
			final EntityTargetEvent realEvent = (EntityTargetEvent) event;
			if(realEvent.getTarget() instanceof Player){
				String TargetName = ((Player) realEvent.getTarget()).getName();
				Player TargetedPlayer = Bukkit.getPlayer(TargetName);
				if(!TargetedPlayer.hasPermission("NodeWhitelist.Whitelisted")){
					if(!whitelistWaive.isWaived(TargetedPlayer)){
					if(!Config.GetBoolean("NoneWhitelisted.Restraints.Interact")){
					event.setCancelled(true);
					}
				}
			}
		}
	}
}
