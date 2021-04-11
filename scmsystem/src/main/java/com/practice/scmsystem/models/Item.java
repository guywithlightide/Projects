package com.practice.scmsystem.models;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.practice.scmsystem.interfaces.SourceEntity;

@Entity
public class Item implements Serializable, Comparator<Item>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long itemId;
	private String name;
	private String description;
	private String status;
	
	public long getId() {
		return itemId;
	}
	public void setId(long id) {
		this.itemId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Item [id=" + itemId + ", name=" + name + ", description=" + description + ", status=" + status + "]";
	}
	@Override
	public int compare(Item o1, Item o2) {
		return (int) (o1.itemId-o2.itemId);
	}
	
	@Override
	public boolean equals(Object obj) {
		Item item = (Item) obj;
		return this.itemId == item.itemId;
	}	
	
}
