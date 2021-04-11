package com.practice.scmsystem.interfaces;

import java.util.Map;

import com.practice.scmsystem.models.Item;

public abstract class TransportEntity {
	protected String id;
	protected String name;
	public abstract Map<String,Item> getInventory();
	public abstract void trackItem();
	public abstract void deliverItem();
	public abstract void receiveItem();
}
