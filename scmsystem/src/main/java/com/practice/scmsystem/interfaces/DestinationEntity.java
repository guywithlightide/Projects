package com.practice.scmsystem.interfaces;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.practice.scmsystem.models.Item;

@Entity
public abstract class DestinationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	protected String name;
	public abstract Map<String,Item> getInventory(); 
	public abstract boolean receiveItem();
	public abstract boolean demandItem();	
}
