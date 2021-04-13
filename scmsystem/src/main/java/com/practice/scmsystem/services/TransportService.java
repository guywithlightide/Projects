package com.practice.scmsystem.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.practice.scmsystem.models.DestinationEntity;
import com.practice.scmsystem.models.Item;
import com.practice.scmsystem.models.SourceEntity;
import com.practice.scmsystem.models.TransportEntity;
import com.practice.scmsystem.models.Warehouse;
import com.practice.scmsystem.repositories.DestinationEntityRepository;
import com.practice.scmsystem.repositories.ItemRepository;
import com.practice.scmsystem.repositories.SourceEntityRepository;
import com.practice.scmsystem.repositories.TransportEntityRepository;
import com.practice.scmsystem.repositories.WarehouseRepository;
import com.practice.scmsystem.utils.ITEMSTATUS;

public class TransportService {
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private SourceEntityRepository sourceEntityRepository;
	@Autowired
	private DestinationEntityRepository destinationEntityRepository;
	@Autowired
	private TransportEntityRepository transportEntityRepository;
	
	public Item dispatchItemByIds(Item item,long sourceEntityId, long destinationEntityId,long warehouseId, long transportEntityId) {
		
		Optional<SourceEntity> sourceOptional = sourceEntityRepository.findById(sourceEntityId);
		Optional<DestinationEntity> destinationOptional = destinationEntityRepository.findById(destinationEntityId);
		Optional<TransportEntity> transportOptional = transportEntityRepository.findById(transportEntityId);
		Optional<Warehouse> warehouseOptional = warehouseRepository.findById(warehouseId);
		if(sourceOptional.isPresent() && destinationOptional.isPresent())
		{
			SourceEntity sourceEntity = sourceOptional.get();
			List<Item> sourceInventory = sourceEntity.getInventory();
			if(sourceInventory.remove(item))
			{
				sourceEntity.setInventory(sourceInventory);
			}
			TransportEntity transportEntity = transportOptional.get();
			List<Item> transportInventory = transportEntity.getInventory();
			if(transportInventory.add(item))
			{
				item.setStatus(ITEMSTATUS.IN_TRANSIT_TO_DESTINATION);
				transportEntity.setInventory(transportInventory);
			}			
		}
		return item;
	}
	
	public Item deliverItem(Item item, long transportEntityId, long destinationEntityId, long warehouseId) {
		
		Optional<TransportEntity> transportOptional = transportEntityRepository.findById(transportEntityId);
		Optional<DestinationEntity> destinationOptional = destinationEntityId!=-1?destinationEntityRepository.findById(destinationEntityId):Optional.ofNullable(null);
		Optional<Warehouse> warehouseOptional = warehouseId!=-1?warehouseRepository.findById(warehouseId):Optional.ofNullable(null);
		
		if(transportOptional.isPresent())
		{
			if(destinationOptional.isPresent())
			{
				TransportEntity transportEntity = transportOptional.get();
				List<Item> transportInventory = transportEntity.getInventory();
				if(transportInventory.remove(item))
				{
					transportEntity.setInventory(transportInventory);
				}
				DestinationEntity destinationEntity = destinationOptional.get();
				List<Item> destinationInventoy = destinationEntity.getInventory();
				item.setStatus(ITEMSTATUS.AT_DESTINATION);
				if(destinationInventoy.add(item))
				{
					destinationEntity.setInventory(destinationInventoy);
				}
			}
			
			if(warehouseOptional.isPresent())
			{
				TransportEntity transportEntity = transportOptional.get();
				List<Item> transportInventory = transportEntity.getInventory();
				if(transportInventory.remove(item))
				{
					transportEntity.setInventory(transportInventory);
				}
				Warehouse warehouse = warehouseOptional.get();
				List<Item> warehouseInventoy = warehouse.getInventory();
				item.setStatus(ITEMSTATUS.AT_DESTINATION);
				if(warehouseInventoy.add(item))
				{
					warehouse.setInventory(warehouseInventoy);
				}
			}						
		}
		return item;
	}
	
}
