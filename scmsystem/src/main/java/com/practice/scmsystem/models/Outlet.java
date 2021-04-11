package com.practice.scmsystem.models;

import java.util.Map;

import com.practice.scmsystem.interfaces.DestinationEntity;

public class Outlet extends DestinationEntity {

	public Outlet() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Map<String, Item> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean receiveItem() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean demandItem() {
		// TODO Auto-generated method stub
		return false;
	}

}
