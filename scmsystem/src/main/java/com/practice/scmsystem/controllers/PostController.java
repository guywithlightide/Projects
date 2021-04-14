package com.practice.scmsystem.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.practice.scmsystem.models.DestinationEntity;
import com.practice.scmsystem.models.Item;
import com.practice.scmsystem.models.SourceEntity;
import com.practice.scmsystem.models.TransportEntity;
import com.practice.scmsystem.models.Warehouse;
import com.practice.scmsystem.repositories.DestinationEntityRepository;
import com.practice.scmsystem.repositories.ItemQueries;
import com.practice.scmsystem.repositories.ItemRepository;
import com.practice.scmsystem.repositories.SourceEntityRepository;
import com.practice.scmsystem.repositories.TransportEntityRepository;
import com.practice.scmsystem.repositories.WarehouseRepository;
import com.practice.scmsystem.utils.DestinationEntityType;
import com.practice.scmsystem.utils.ITEMSTATUS;
import com.practice.scmsystem.utils.RandomNameGenerator;
import com.practice.scmsystem.utils.SourceEntityType;
import com.practice.scmsystem.utils.TransportEntityType;

@RestController
@RequestMapping(path = "post")
public class PostController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class.getName());

	@Autowired
	private ItemQueries itemQueries;	
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private SourceEntityRepository sourceEntityRepository;
	
	@Autowired
	private DestinationEntityRepository destinationEntityRepository;
	
	@Autowired
	private TransportEntityRepository transportEntityRepository;

	@Autowired
	private WarehouseRepository warehouseRepository;

	@PostMapping("/warehouse/create")
	public Warehouse createWarehouse() {
		Warehouse warehouse = new Warehouse();
		return warehouseRepository.save(warehouse);
	}

	@PostMapping("/warehouse/{warehouseid}/additems")
	public boolean addItemsToWarehouse(@PathVariable(name = "warehouseid") long warehouseid,@RequestBody List<Item> items)
	{
		items.forEach(item->item.setStatus(ITEMSTATUS.AT_WAREHOUSE));
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
	public SourceEntity createSourceEntity(@RequestBody SourceEntity sourceEntity) {
		return sourceEntityRepository.save(sourceEntity);
	}

	@PostMapping("/source/{sourceid}/additems")
	public List<Item> addItemsToSource(@PathVariable(name = "sourceid") long sourceid,@RequestBody List<Item> items) {
		items.forEach(item->item.setStatus(ITEMSTATUS.AT_SOURCE));
		Optional<SourceEntity> sourceOptional = sourceEntityRepository.findById(sourceid);
		if(sourceOptional.isPresent())
		{
			List<Item> inventory = sourceOptional.get().getInventory();
			inventory.addAll(items);
			sourceOptional.get().setInventory(inventory);
			sourceEntityRepository.save(sourceOptional.get());
			return sourceOptional.get().getInventory();
		}
		return null;
	}
	
	@PostMapping(path = "/item/like")
	public List<Item> getItemsFilteredByStatus(@RequestBody Item item) {
		item.setStatus(ITEMSTATUS.valueOf(item.getStatus().toString()));
		LOGGER.info("Item={}",item.toString());
		return itemQueries.findItemsLike(item);
	}

	@PostMapping("/randomtest/generate")
	public boolean generateRandomTestData(@RequestParam(name = "warehouses" ,defaultValue = "10") int warehouses,
			@RequestParam(name = "sources" ,defaultValue = "10") int sources,
			@RequestParam(name = "destinations" ,defaultValue = "10") int destinations,
			@RequestParam(name = "transports" ,defaultValue = "10") int transports,
			@RequestParam(name = "itemsforeach" ,defaultValue = "10") int itemsperentity) {
		RandomNameGenerator generator = new RandomNameGenerator();
		try
		{
			List<SourceEntity> sourceEntities = new ArrayList<SourceEntity>();
			LOGGER.info("********* GENERATING "+sources+" source entities with random data *********");
			for (int i = 0; i < sources; i++) {
				SourceEntity sourceEntity = new SourceEntity();
				sourceEntity.setName("SR_"+generator.generateString());
				sourceEntity.setType( SourceEntityType.values()[(int) ( (Math.random()*SourceEntityType.values().length) %SourceEntityType.values().length)] );
				sourceEntity = sourceEntityRepository.save(sourceEntity);
				List<Item> inventoryTemp = new ArrayList<>();
				for(int j=0; j<itemsperentity; j++)
				{
					Item item = new Item();
					item.setName(generator.generateString());
					if(j%6==0)
						item.setStatus(ITEMSTATUS.PROCURED);
					else
						item.setStatus(ITEMSTATUS.AT_SOURCE);
					item.setDescription(generator.generateString());
					item.setSourceEntity(sourceEntity);
					inventoryTemp.add(item);
				}
				sourceEntity.setInventory(inventoryTemp);
				sourceEntities.add(sourceEntity);
			}
			sourceEntityRepository.saveAll(sourceEntities);

			List<DestinationEntity> destinationEntities = new ArrayList<DestinationEntity>();
			LOGGER.info("********* GENERATING "+destinations+" destination entities with random data *********");
			for (int i = 0; i < destinations; i++) {
				DestinationEntity destinationEntity = new DestinationEntity();
				destinationEntity.setName("DT_"+generator.generateString());
				destinationEntity.setType( DestinationEntityType.values()[(int) ( (Math.random()*DestinationEntityType.values().length) %DestinationEntityType.values().length)]);
				destinationEntity = destinationEntityRepository.save(destinationEntity);
				List<Item> inventoryTemp = new ArrayList<>();
				for(int j=0; j<itemsperentity; j++)
				{
					Item item = new Item();
					item.setName(generator.generateString());
					item.setStatus(ITEMSTATUS.AT_DESTINATION);
					item.setDescription(generator.generateString());
					item.setDestinationEntity(destinationEntity);
					inventoryTemp.add(item);
				}
				destinationEntity.setInventory(inventoryTemp);
				destinationEntities.add(destinationEntity);
			}
			destinationEntityRepository.saveAll(destinationEntities);
			
			List<TransportEntity> transportEntities = new ArrayList<TransportEntity>();
			LOGGER.info("********* GENERATING "+transports+" transport entities with random data *********");
			for (int i = 0; i < transports; i++) {
				TransportEntity transportEntity = new TransportEntity();
				transportEntity.setName("TR_"+generator.generateString());
				transportEntity.setType( TransportEntityType.values()[(int) ( (Math.random()*TransportEntityType.values().length) %TransportEntityType.values().length)]);
				transportEntity  = transportEntityRepository.save(transportEntity );
				List<Item> inventoryTemp = new ArrayList<>();
				for(int j=0; j<itemsperentity; j++)
				{
					Item item = new Item();
					item.setName(generator.generateString());
					if(j%2==0)
						item.setStatus(ITEMSTATUS.IN_TRANSIT_TO_DESTINATION);
					else
						item.setStatus(ITEMSTATUS.IN_TRANSIT_TO_WAREHOUSE);
					item.setDescription(generator.generateString());
					item.setTransportEntity(transportEntity );
					inventoryTemp.add(item);
				}
				transportEntity .setInventory(inventoryTemp);
				transportEntities.add(transportEntity );
			}
			transportEntityRepository.saveAll(transportEntities);
			
			List<Warehouse> warehousesList = new ArrayList<Warehouse>();
			LOGGER.info("********* GENERATING "+warehouses+" warehouses with random data *********");
			for (int i = 0; i < warehouses; i++) {
				Warehouse warehouse = new Warehouse();
				warehouse.setName("WH_"+generator.generateString());
				warehouse = warehouseRepository.save(warehouse);
				List<Item> inventoryTemp = new ArrayList<>();
				for(int j=0; j<itemsperentity; j++)
				{
					Item item = new Item();
					item.setName(generator.generateString());
					if(j%4==0)
						item.setStatus(ITEMSTATUS.DAMAGED);
					else if(j%5==0)
						item.setStatus(ITEMSTATUS.MISSING);
					else
						item.setStatus(ITEMSTATUS.AT_WAREHOUSE);
					item.setDescription(generator.generateString());
					inventoryTemp.add(item);
				}
				warehouse.setInventory(inventoryTemp);
				warehousesList.add(warehouse);
			}
			warehouseRepository.saveAll(warehousesList);
		}
		catch (Exception e) {
			LOGGER.error("",e);
			return false;
		}
		return true;
	}
}
