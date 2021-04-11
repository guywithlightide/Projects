package com.practice.scmsystem.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.scmsystem.interfaces.SourceEntity;
import com.practice.scmsystem.models.Supplier;
import com.practice.scmsystem.repositories.SupplierRepository;
import com.practice.scmsystem.utils.SCMSConstants;

@Service
public class SourceEntityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SourceEntityService.class);
	@Autowired
	private SupplierRepository supplierRepository;	
		
	public SourceEntity createSourceEntity(SourceEntity sourceEntity) {		
		switch (sourceEntity.type) 
		{
		case SCMSConstants.CLASS_SUPPLIER:
			Supplier supplier = (Supplier) sourceEntity;
			sourceEntity = supplierRepository.save(supplier);
			return sourceEntity;
		default:
			LOGGER.error("Unknown model class {}",sourceEntity.getClass().getSimpleName());
			return null;
		}
	}

}
