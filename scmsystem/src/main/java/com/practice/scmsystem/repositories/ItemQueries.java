package com.practice.scmsystem.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import com.practice.scmsystem.models.Item;

@ManagedBean
public class ItemQueries{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemQueries.class.getSimpleName());

	@Autowired
	ItemRepository itemRepository;
	public List<Item> findItemsLike(Item item) {
				
		return itemRepository.findAll(new Specification<Item>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(item.getStatus()!=null && !item.getStatus().toString().isEmpty())
				{
					predicates.add(criteriaBuilder.equal(root.get("status"),item.getStatus()));
				}
				if(item.getDescription()!=null && !item.getDescription().isEmpty())
				{
					predicates.add(criteriaBuilder.like(root.get("description"), "%"+item.getDescription()+"%"));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		});
		
	}

}
