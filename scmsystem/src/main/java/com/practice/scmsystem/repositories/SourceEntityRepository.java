package com.practice.scmsystem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practice.scmsystem.interfaces.SourceEntity;

@Repository
public interface SourceEntityRepository extends CrudRepository<SourceEntity, Long>{

}
