package com.github.flash619.nodewhitelist;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.flash619.nodewhitelist.conf.ConfigLink;
import com.github.flash619.nodewhitelist.listeners.Interact;
import com.github.flash619.nodewhitelist.listeners.Login;
import com.github.flash619.nodewhitelist.listeners.Talk;

public class NodeWhitelist extends JavaPlugin{
	
	/* NodeWhitelist By: Flash619
	 * All code is subject to a Creative Commons
	 * Attribution-NonCommercial-NoDerivs 
	 * CC BY-NC-ND License
	 */
	
	ConfigLink config = new ConfigLink(this);
	
	private Login LoginListener;
	private Talk TalkListener;
	private Interact InteractListener;
	
	@Override
	public void onEnable(){
		Logger log = this.getLogger();
		log.info("NodeWhitelist Loading...");
		config.LoadConfig();
		
		LoginListener = new Login(this);
		TalkListener = new Talk(this);
		InteractListener = new Interact(this);
		
		Bukkit.getServer().getPluginManager().registerEvents(LoginListener, this);
		Bukkit.getServer().getPluginManager().registerEvents(TalkListener, this);
		Bukkit.getServer().getPluginManager().registerEvents(InteractListener, this);
		
		log.info("NodeWhitelist Enabled");
	}
	public void onDisable(){}

}
