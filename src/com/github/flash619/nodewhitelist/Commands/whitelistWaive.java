package com.github.flash619.nodewhitelist.Commands;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.flash619.nodewhitelist.NodeWhitelist;
import com.github.flash619.nodewhitelist.conf.ConfigLink;

public class whitelistWaive implements CommandExecutor{
	private static NodeWhitelist plugin;
	
	public whitelistWaive(NodeWhitelist plugin){
		whitelistWaive.plugin = plugin;
	}
	
	//Hash tables
	public static Map<String, Boolean> WhitelistWaived = new HashMap<String, Boolean>();
	
	public static boolean isWaived(Player player){
		String playerName = player.getName();
		if(WhitelistWaived.containsKey(playerName)){
			return true;
		}else{
			return false;
		}
	}
	public static void RemoveWaive(Player player){
		String playerName = player.getName();
		if(WhitelistWaived.containsKey(playerName)){
            WhitelistWaived.remove(playerName);
		}
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String buyflight, String[] args){
		Logger log = plugin.getLogger();
		ConfigLink Config = new ConfigLink(plugin);	
		if(cmd.getName().equalsIgnoreCase("waiveconstraints")||cmd.getName().equalsIgnoreCase("wc")){
			if(args.equals(null)||args.length==0||args.length==2){
				return false;
			}else{
				Player player = (Player) sender;
				if(player.isOp()||player.hasPermission("NodeWhitelist.Waive")){
					Player TargetPlayer = (Bukkit.getServer().getPlayer(args[0]));
					if(TargetPlayer.equals(null)){
						sender.sendMessage(ChatColor.RED + "[ERROR]: " + ChatColor.WHITE + args[0] + " Is not a valid player!");
						return true;
					}else if(!TargetPlayer.hasPermission("NodeWhitelist.Whitelisted")){
						if(isWaived(TargetPlayer)){
							RemoveWaive(TargetPlayer);
							TargetPlayer.sendMessage(ChatColor.RED + "[INFO]: " + ChatColor.WHITE + Config.GetString("NoneWhitelisted.Messages.RestraintsUnWaived"));
							if(!Config.GetString("General.WhitelistingWebsite").equals(null)&&(Config.GetBoolean("General.EnableWebsiteMessages"))){
								TargetPlayer.sendMessage(ChatColor.RED + "[INFO]: " + ChatColor.WHITE + "Please visit: " + ChatColor.DARK_PURPLE + Config.GetString("General.WhitelistingWebsite") + ChatColor.WHITE + " for whitelisting.");
							}
							sender.sendMessage(ChatColor.RED + "[INFO]: " + ChatColor.WHITE + TargetPlayer.getName() + "'s restraints have been re-applied.");
							log.warning(player.getName() + " Has applied whitelist restraints on " + TargetPlayer.getName());
							return true;
						}else{
						WhitelistWaived.put(TargetPlayer.getName(), true);
						TargetPlayer.sendMessage(ChatColor.RED + "[INFO]: " + ChatColor.WHITE + Config.GetString("NoneWhitelisted.Messages.RestraintsWaived"));
						if(!Config.GetString("General.WhitelistingWebsite").equals(null)&&(Config.GetBoolean("General.EnableWebsiteMessages"))){
							TargetPlayer.sendMessage(ChatColor.RED + "[INFO]: " + ChatColor.WHITE + "Please visit: " + ChatColor.DARK_PURPLE + Config.GetString("General.WhitelistingWebsite") + ChatColor.WHITE + " for whitelisting.");
						}
						sender.sendMessage(ChatColor.RED + "[INFO]: " + ChatColor.WHITE + TargetPlayer.getName() + "'s restraints have been removed.");
						log.warning(player.getName() + " Has removed whitelist restraints on " + TargetPlayer.getName());
						return true;
						}
					}else{
						sender.sendMessage(ChatColor.RED + "[ERROR]: " + ChatColor.WHITE + TargetPlayer.getName() + " Is already whitelisted. Please remove his/her whitelist via your permissions manager.");
						return true;
					}
					
				}
			
			}
			
		}
		return false;	
	}

}
