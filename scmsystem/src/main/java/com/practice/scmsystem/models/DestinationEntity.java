package com.practice.scmsystem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.practice.scmsystem.utils.DestinationEntityType;

@Entity
public class DestinationEntity {
		
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "destinationEntity")
	private List<Item> inventory;
	private DestinationEntityType type;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Item> getInventory() {
		return inventory;
	}
	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}	
	public DestinationEntityType getType() {
		return type;
	}
	public void setType(DestinationEntityType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		final int maxLen = 10;
		return "DestinationEntity [id=" + id + ", name=" + name + ", inventory="
				+ (inventory != null ? inventory.subList(0, Math.min(inventory.size(), maxLen)) : null) + ", type="
				+ type + "]";
	}
	
	

}
