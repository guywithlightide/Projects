package com.practice.scmsystem.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.practice.scmsystem.utils.SourceEntityType;

@Entity
public class SourceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "sourceEntity")
	@JsonManagedReference
	private List<Item> inventory;
	private SourceEntityType type;
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
	public SourceEntityType getType() {
		return type;
	}
	public void setType(SourceEntityType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		final int maxLen = 10;
		return "SourceEntity [id=" + id + ", name=" + name + ", items="
				+ (inventory != null ? inventory.subList(0, Math.min(inventory.size(), maxLen)) : null) + ", type=" + type + "]";
	}
	
	
	
}
