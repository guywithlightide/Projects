package com.practice.scmsystem.controllers;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.scmsystem.interfaces.SourceEntity;
import com.practice.scmsystem.models.Item;
import com.practice.scmsystem.models.Supplier;
import com.practice.scmsystem.models.Warehouse;
import com.practice.scmsystem.repositories.ItemRepository;
import com.practice.scmsystem.repositories.WarehouseRepository;
import com.practice.scmsystem.services.SourceEntityService;
import com.practice.scmsystem.utils.ITEMSTATUS;

@RestController
@RequestMapping(method = RequestMethod.POST, path = "post")
public class PostController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class.getName());
	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private SourceEntityService sourceEntityService;
	
	@Autowired
	private WarehouseRepository warehouseRepository;
	
	@PostMapping("/warehouse/create")
	public Warehouse createWarehouse() {
		Warehouse warehouse = new Warehouse();
		return warehouseRepository.save(warehouse);
	}
	
	@PostMapping("/warehouse/{warehouseid}/additems")
	public boolean addItems(@PathVariable(name = "warehouseid") long warehouseid,@RequestBody List<Item> items)
	{
		System.out.println("Items "+items);
		items.forEach(item->item.setStatus(ITEMSTATUS.AT_WAREHOUSE.toString()));
		Optional<Warehouse> warehouse= warehouseRepository.findById(warehouseid);
		if(warehouse.isPresent())
		{
			List<Item> inventory = warehouse.get().getInventory();
			inventory.addAll(items);
			warehouse.get().setInventory(inventory);
			warehouseRepository.save(warehouse.get());
			return true;
		}
		return false;
	}
	
	@PostMapping("/source/create")
	public SourceEntity createEmptySourceEntity(@RequestBody SourceEntity sourceEntity) {
		System.out.println(sourceEntityService.createSourceEntity(sourceEntity));
		return sourceEntityService.createSourceEntity(sourceEntity);
	}	

}
