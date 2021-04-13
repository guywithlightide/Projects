package com.practice.scmsystem.models;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.practice.scmsystem.utils.ITEMSTATUS;

@Entity
public class Item implements Serializable, Comparator<Item>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String description;
	private ITEMSTATUS status;

	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private SourceEntity sourceEntity;
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private DestinationEntity destinationEntity;	
	@ManyToOne(cascade = CascadeType.ALL)
	@JsonBackReference
	private TransportEntity transportEntity;	
		
	
	public TransportEntity getTransportEntity() {
		return transportEntity;
	}
	public void setTransportEntity(TransportEntity transportEntity) {
		this.transportEntity = transportEntity;
	}
	public SourceEntity getSourceEntity() {
		return sourceEntity;
	}
	public void setSourceEntity(SourceEntity sourceEntity) {
		this.sourceEntity = sourceEntity;
	}
	public DestinationEntity getDestinationEntity() {
		return destinationEntity;
	}
	public void setDestinationEntity(DestinationEntity destinationEntity) {
		this.destinationEntity = destinationEntity;
	}
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ITEMSTATUS getStatus() {
		return status;
	}
	public void setStatus(ITEMSTATUS itemstatus) {
		this.status = itemstatus;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", description=" + description + ", status=" + status + "]";
	}
	@Override
	public int compare(Item o1, Item o2) {
		return (int) (o1.id-o2.id);
	}
	
	@Override
	public boolean equals(Object obj) {
		Item item = (Item) obj;
		return this.id == item.id;
	}	
	
}
