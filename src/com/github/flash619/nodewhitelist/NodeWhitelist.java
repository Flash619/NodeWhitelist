package com.github.flash619.nodewhitelist;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.flash619.nodewhitelist.Commands.whitelistWaive;
import com.github.flash619.nodewhitelist.conf.ConfigLink;
import com.github.flash619.nodewhitelist.listeners.Interact;
import com.github.flash619.nodewhitelist.listeners.Login;
import com.github.flash619.nodewhitelist.listeners.Logout;
import com.github.flash619.nodewhitelist.listeners.Talk;

public class NodeWhitelist extends JavaPlugin{
	
	/* NodeWhitelist By: Flash619
	 * (C)2012 Licensed under the GNU Lesser General Public License v3
	 */

	ConfigLink config = new ConfigLink(this);
	
	private Login LoginListener;
	private Talk TalkListener;
	private Interact InteractListener;
	private Logout LogoutListener;
	private whitelistWaive whitelistWaiveExecutor;
	
	@Override
	public void onEnable(){
		Logger log = this.getLogger();
		log.info("NodeWhitelist Loading...");
		config.LoadConfig();
		if(!config.GetBoolean("General.WhitelistEnabled")){
			log.warning("NodeWhitelist was disabled in the config and will be unloaded.");
			this.getPluginLoader().disablePlugin(this);
		}else{
		LoginListener = new Login(this);
		TalkListener = new Talk(this);
		InteractListener = new Interact(this);
		LogoutListener = new Logout(this);
		whitelistWaiveExecutor = new whitelistWaive(this);
		Bukkit.getServer().getPluginManager().registerEvents(LoginListener, this);
		Bukkit.getServer().getPluginManager().registerEvents(TalkListener, this);
		Bukkit.getServer().getPluginManager().registerEvents(InteractListener, this);
		Bukkit.getServer().getPluginManager().registerEvents(LogoutListener, this);
		getCommand("WaiveConstraints").setExecutor(whitelistWaiveExecutor);
		
		
		log.info("NodeWhitelist Loaded Succesfully!");
		}
	}
	public void onDisable(){
		Logger log = this.getLogger();
		log.info("NodeWhitelist Unloaded.");
	}

}
