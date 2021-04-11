package com.practice.scmsystem.controllers;

import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practice.scmsystem.interfaces.SourceEntity;
import com.practice.scmsystem.models.Item;
import com.practice.scmsystem.models.Warehouse;
import com.practice.scmsystem.repositories.DestinationEntityRepository;
import com.practice.scmsystem.repositories.ItemRepository;
import com.practice.scmsystem.repositories.SourceEntityRepository;
import com.practice.scmsystem.repositories.WarehouseRepository;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "get")
public class GetController {

	@Value("${spring.application.name}")
	String appName;

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

	@GetMapping("/source/{sourceid}/getinventory")
	public List<Item> getSourceInventory(@PathVariable long sourceid) {
		return sourceEntityRepository.findById(sourceid).orElse(new SourceEntity()).getInventory();
	}

	@GetMapping("/warehouse/all")
	public List<Warehouse> getAllWarehouses() {
		return (List<Warehouse>) warehouseRepository.findAll();
	}
	
}
