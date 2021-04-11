package com.practice.scmsystem.interfaces;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.practice.scmsystem.models.Item;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "className")
public abstract class SourceEntity {
	public String type;
	public abstract Map<String,Item> getInventory();
	public abstract boolean procureItem();
	public abstract void sendItem();
	@Override
	public String toString() {
		return "SourceEntity [type=" + type + "]";
	}
	
}