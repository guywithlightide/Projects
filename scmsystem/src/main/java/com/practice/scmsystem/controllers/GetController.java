package com.practice.scmsystem.controllers;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(path = "get")
public class GetController {

	@Value("${spring.application.name}")
	String appName;
	private static final Logger LOGGER = LoggerFactory.getLogger(GetController.class.getName());

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private SourceEntityRepository sourceEntityRepository;

	@Autowired
	private DestinationEntityRepository destinationEntityRepository;

	@Autowired
	private WarehouseRepository warehouseRepository;

	Map genericResponse = null;

	@GetMapping("/home")
	public String helloWorld() {
		return appName;
	}

	@GetMapping("/warehouse/{warehouseid}")
	public Warehouse getWarehouse(@PathVariable long warehouseid) {
		return warehouseRepository.findById(warehouseid).orElse(null);
	}

	@GetMapping("/warehouse/{warehouseid}/getinventory")
	public List<Item> getWarehouseInventory(@PathVariable long warehouseid) {
		return warehouseRepository.findById(warehouseid).orElse(new Warehouse()).getInventory();
	}
	
	@GetMapping("/warehouse/all")
	public List<Warehouse> getAllWarehouses() {
		return (List<Warehouse>) warehouseRepository.findAll();
	}

	@GetMapping("/source/{sourceid}/getinventory")
	public List<Item> getSourceInventory(@PathVariable long sourceid) {
		return sourceEntityRepository.findById(sourceid).orElse(new SourceEntity()).getInventory();
	}

	@GetMapping("/source/all")
	public List<SourceEntity> getAllSources() {
		return (List<SourceEntity>) sourceEntityRepository.findAll();
	}
	
	@GetMapping("item/all")
	public List<Item> getAllItems() {
		return (List<Item>) itemRepository.findAll();
	}
	
}
