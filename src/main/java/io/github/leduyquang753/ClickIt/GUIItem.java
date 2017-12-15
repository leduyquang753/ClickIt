package io.github.leduyquang753.ClickIt;

import java.util.List;

import org.bukkit.Material;

public final class GUIItem {
	public Material item;
	public String itemName;
	public List<String> lore;
	public List<Enchant> enchantments;
	public int amount;
	
	/**
	 * Creates a new GUI item.
	 * @param material The type of item.
	 * @param amount The amount of item stack.
	 * @param name The name of the item.
	 * @param lore The lore of the item.
	 * @param enchantments The enchantments of the item.
	 */
	public GUIItem(Material material, int amount, String name, List<String> lore, List<Enchant> enchantments) {
		item = material;
		itemName = name;
		this.lore = lore;
		this.enchantments = enchantments;
	}
}
