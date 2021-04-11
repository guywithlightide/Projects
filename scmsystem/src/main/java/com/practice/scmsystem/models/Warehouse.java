package com.practice.scmsystem.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "warehouse")
public class Warehouse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(orphanRemoval = true,cascade = CascadeType.ALL)
	private List<Item> inventory;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Item> getInventory() {
		return inventory;
	}

	public void setInventory(List<Item> inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "Warehouse [id=" + id + ", inventory="
				+ (inventory != null ? inventory.subList(0, Math.min(inventory.size(), maxLen)) : null) + "]";
	}
	
	
	
}
