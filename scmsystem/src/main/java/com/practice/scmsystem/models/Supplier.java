package com.practice.scmsystem.models;

import java.util.Map;

import com.practice.scmsystem.interfaces.SourceEntity;

public class Supplier extends SourceEntity{

	@Override
	public Map<String, Item> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean procureItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void sendItem() {
		// TODO Auto-generated method stub
		
	}

}
