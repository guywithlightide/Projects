package com.practice.scmsystem.interfaces;

import java.util.Map;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.practice.scmsystem.models.Item;

public abstract class TransportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected String id;
	protected String name;
	public abstract Map<String,Item> getInventory();
	public abstract void trackItem();
	public abstract void deliverItem();
	public abstract void receiveItem();
}
