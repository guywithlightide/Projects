package com.practice.scmsystem.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.scmsystem.models.Item;

@Repository
public interface ItemRepository extends CrudRepository<Item, String>,JpaSpecificationExecutor<Item> {
	
	@Query(value = "SELECT i FROM Item i ORDER BY id")
	Page<Item> findAllByPage(Pageable pageable);
}
