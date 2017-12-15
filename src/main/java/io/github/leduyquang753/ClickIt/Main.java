package io.github.leduyquang753.ClickIt;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class Main extends JavaPlugin {
	public static HashMap<Player, String> opening = new HashMap<Player, String>();
	public static List<String> GUIs = new ArrayList<String>();
	
	@Override
	public void onEnable() {
		getLogger().info("ClickIt has been enabled.");
		Bukkit.getPluginManager().registerEvents(new Events(), this);
	}
	
	@Override
	public void onDisable() {
		getLogger().info("ClickIt has been disabled.");
	}
}
