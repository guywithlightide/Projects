package com.practice.scmsystem.repositories;

import org.springframework.data.repository.CrudRepository;

import com.practice.scmsystem.models.Item;

public interface ItemRepository extends CrudRepository<Item, String>{

}
