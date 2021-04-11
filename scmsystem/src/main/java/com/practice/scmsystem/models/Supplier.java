package com.practice.scmsystem.models;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.practice.scmsystem.interfaces.SourceEntity;

@Entity
public class Supplier extends SourceEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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
