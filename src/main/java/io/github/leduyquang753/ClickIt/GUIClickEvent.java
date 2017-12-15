package io.github.leduyquang753.ClickIt;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class GUIClickEvent extends Event {
	private static final HandlerList handlers = new HandlerList();
	private String GUIName;
	private int slot;
	private LivingEntity entity;
	
	public GUIClickEvent(String GUIName, int slot, LivingEntity entity) {
		this.setGUIName(GUIName);
		this.setSlot(slot);
		this.setEntity(entity);
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * Returns the clicked GUI slot.
	 * @return The index of the clicked slot.
	 */
	public int getSlot() {
		return slot;
	}

	protected void setSlot(int slot) {
		this.slot = slot;
	}

	/**
	 * Returns the clicked GUI's name (not custom name).
	 * @return The GUI's name
	 */
	public String getGUIName() {
		return GUIName;
	}

	protected void setGUIName(String GUIName) {
		this.GUIName = GUIName;
	}

	/**
	 * Returns the entity clicked the GUI.
	 * @return The entity clicked.
	 */
	public LivingEntity getEntity() {
		return entity;
	}

	protected void setEntity(LivingEntity entity) {
		this.entity = entity;
	}
}
