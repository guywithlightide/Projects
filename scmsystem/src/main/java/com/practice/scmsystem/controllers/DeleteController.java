package com.practice.scmsystem.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.scmsystem.models.Item;
import com.practice.scmsystem.models.SourceEntity;
import com.practice.scmsystem.models.Warehouse;
import com.practice.scmsystem.repositories.DestinationEntityRepository;
import com.practice.scmsystem.repositories.ItemRepository;
import com.practice.scmsystem.repositories.SourceEntityRepository;
import com.practice.scmsystem.repositories.WarehouseRepository;

@RestController
@RequestMapping(path = "delete")
public class DeleteController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DeleteController.class.getName());

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private SourceEntityRepository sourceEntityRepository;

	@Autowired
	private DestinationEntityRepository destinationEntityRepository;

	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@DeleteMapping("/warehouse/{warehouseid}")
	public boolean deleteWarehouse(@PathVariable long warehouseid) {		
		try{
			warehouseRepository.deleteById(warehouseid);
			return true;
		}catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return false;
	}
	
	@DeleteMapping("/source/{sourceid}")
	public boolean deleteSource(@PathVariable long sourceid) {		
		try{
			sourceEntityRepository.deleteById(sourceid);
			return true;
		}catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return false;
	}
	
	@DeleteMapping("/warehouse/{warehouseid}/deleteitems")
	public boolean deleteMultipleItemsFromWarehouse(@PathVariable long warehouseid, @RequestBody List<Item> itemsToDelete) {		
		try{
			Optional<Warehouse> optional = warehouseRepository.findById(warehouseid);
			if(optional.isPresent())
			{
				List<Item> items = optional.get().getInventory();
				if(!items.removeAll(itemsToDelete))
					return false;
				optional.get().setInventory(items);	
				itemRepository.deleteAll(itemsToDelete);
				return true;
			}
		}catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return false;
	}

	@DeleteMapping("/source/{sourceid}/deleteitems")
	public boolean deleteMultipleItemsFromSource(@PathVariable long sourceid, @RequestBody List<Item> itemsToDelete) {		
		try{
			Optional<SourceEntity> optional = sourceEntityRepository.findById(sourceid);
			if(optional.isPresent())
			{
				List<Item> items = optional.get().getInventory();
				if(!items.removeAll(itemsToDelete))
					return false;
				optional.get().setInventory(items);
				itemRepository.deleteAll(itemsToDelete);
				return true;
			}
		}catch (Exception e) {
			LOGGER.error(e.toString());
		}
		return false;
	}
}
