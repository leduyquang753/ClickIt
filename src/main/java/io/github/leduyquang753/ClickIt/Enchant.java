package io.github.leduyquang753.ClickIt;

import org.bukkit.enchantments.Enchantment;

public final class Enchant {
	public Enchantment enchantment;
	public int level;
	
	/**
	 * Creates a new item enchantment.
	 * @param enchantment The type of the enchantment.
	 * @param level The level of the enchantment.
	 */
	public Enchant(Enchantment enchantment, int level) {
		this.enchantment = enchantment;
		this.level = level;
	}
}
