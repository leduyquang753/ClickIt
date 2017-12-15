package io.github.leduyquang753.ClickIt;

import java.util.Arrays;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class ChestGUI {
	public String name;
	public String displayName = "";
	public GUIItem[] items;
	public int size;
	public Inventory inventory;
	
	/**
	 * Creates a new GUI with a custom display name.
	 * @param name The name of the GUI.
	 * @param displayName The display name of the GUI.
	 * @param size The size of the GUI (1-6).
	 * @param items The GUI items (get the default by ChestGUI.getDefaultGUI()).
	 */
	public ChestGUI(String name, String displayName, int size, GUIItem[] items) {
		this.name = name;
		this.size = size;
		if (size < 1) this.size = 1;
		if (size > 6) this.size = 6;
		this.items = items;
		this.displayName = displayName;
	}
	
	/**
	 * Creates a new GUI.
	 * @param name The name of the GUI, also the display name.
	 * @param size The size of the GUI (1-6).
	 * @param items The GUI items (get the default by ChestGUI.getDefaultGUI()).
	 */
	public ChestGUI(String name, int size, GUIItem[] items) {
		this.name = name;
		this.size = size;
		if (size < 1) this.size = 1;
		if (size > 6) this.size = 6;
		this.items = items;
	}
	
	/**
	 * Registers the GUI to the plugin. A GUI must be registered
	 * in order to be used.
	 * @return false if a GUI with the same name is registered.
	*/
	public boolean register() {
		if (Main.GUIs.contains(name)) return false;
		Main.GUIs.add(name);
		return true;
	}
	
	/**
	 * Unregisters the GUI. If it is opening to any player, their
	 * GUI will be closed.
	 * @return true if succeded, false if it is not registered before.
	 */
	public boolean unregister() {
		for (Player player : Main.opening.keySet()) {
			if (Main.opening.get(player).equals(name)) this.close(player);
		}
		
		if (!Main.GUIs.contains(name)) return false;
		Main.GUIs.remove(name);
		return true;
	}
	
	/**
	 * Checks if the GUI has been registered.
	 * @return true if it is registered, false if not.
	 */
	public boolean isRegistered() {
		return Main.GUIs.contains(name);
	}
	
	/**
	 * Returns the default array of GUIItems.
	 * @return A 54-element array of empty GUIItems.
	 */
	public static GUIItem[] getDefaultGUI() {
		GUIItem[] list = new GUIItem[54];
		for (int i = 0; i < 54; i++) {
			list[i] = new GUIItem(Material.AIR, 0, "", Arrays.asList(""), null); 
		}
		return list;
	}
	
	/**
	 * Sets the current size of the GUI.
	 * @param size If it is smaller than 1, it will be 1; if greater than 6, will be 6.
	 */
	public void setSize(int size) {
		this.size = size;
		if (size < 1) this.size = 1;
		if (size > 6) this.size = 6;
	}
	
	/**
	 * Sets the specified GUIItem to another. After that the GUI updates to the players.
	 * @param index The GUIItem's location.
	 * @param item The new GUIItem.
	 * @throws IllegalArgumentException If the index is greater than 53 or smaller than 0.
	 */
	public void setItem(int index, GUIItem item) throws IllegalArgumentException {
		if (index < 0 || index > 53) throw new IllegalArgumentException("The index must be from 0 to 53!");
		items[index] = item;
		for (Player player : Main.opening.keySet()) {
			if (Main.opening.get(player).equalsIgnoreCase(name)) this.show(player);
		}
	}
	
	/**
	 * Shows the GUI for a player.
	 * @param p The player.
	 */
	public void show(Player p) {
		if (!Main.GUIs.contains(name)) Main.GUIs.add(name);
		inventory = Bukkit.createInventory(p, 9*size, displayName == "" ? name : displayName);
		for (int i = 0; i < 9*size; i++)
			if (items[i].item != Material.AIR) {
				ItemStack stack = new ItemStack(items[i].item);
				ItemMeta meta = stack.getItemMeta();
				stack.setAmount(items[i].amount);
				meta.setDisplayName(items[i].itemName);
				meta.setLore(items[i].lore);
				for (Enchant ench : items[i].enchantments)
					meta.addEnchant(ench.enchantment, ench.level, true);
				stack.setItemMeta(meta);
				inventory.setItem(i, stack);
			}
		p.openInventory(inventory);
		if (!Main.opening.containsKey(p)) Main.opening.put(p, name); else {
			Main.opening.remove(p);
			Main.opening.put(p, name);
		}
	}
	
	/**
	 * Closes the GUI for a player.
	 * @param p The player
	 * @return true if suceeded, false if not.
	 */
	public boolean close(Player p) {
		if (!Main.opening.containsKey(p)) return false;
		p.closeInventory();
		Main.opening.remove(p);
		return true;
	}
}
