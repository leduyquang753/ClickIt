package io.github.leduyquang753.ClickIt;

import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public final class Events implements Listener {
	@EventHandler
	public void onPLayerClick(InventoryClickEvent event) {
		
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		Player p = (Player) event.getPlayer();
		Main.opening.remove(p);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		LivingEntity p = event.getWhoClicked();
		if (!Main.opening.containsKey(p)) return; else event.setCancelled(true);
		Bukkit.getPluginManager().callEvent(new GUIClickEvent(Main.opening.get(p), event.getSlot(), p));
	}
}
