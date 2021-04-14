package com.practice.scmsystem.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.practice.scmsystem.utils.ITEMSTATUS;

@ManagedBean
public class ItemQueries{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ItemQueries.class.getSimpleName());

	@Autowired
	ItemRepository itemRepository;
	public List<Item> findItemsLike(Map<String, String> item) {
				
		return itemRepository.findAll(new Specification<Item>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				if(item.get("name")!=null && !item.get("name").isEmpty())
				{
					predicates.add(criteriaBuilder.equal(root.get("name"),"%"+item.get("name")+"%"));
				}
				if(item.get("status")!=null && !item.get("status").toString().isEmpty())
				{
					if(item.get("status").toString().contains(","))
					{
						String [] statuses = item.get("status").toString().split(",");
						List<Predicate> orStatuses = new ArrayList<Predicate>();
						for(String status : statuses)
						{
							orStatuses.add(criteriaBuilder.equal(root.get("status"),ITEMSTATUS.valueOf(status)));
						}
						predicates.add(criteriaBuilder.or(orStatuses.toArray(new Predicate[0])));
					}
					else
						predicates.add(criteriaBuilder.equal(root.get("status"),ITEMSTATUS.valueOf(item.get("status"))));
				}
				if(item.get("description")!=null && !item.get("description").isEmpty())
				{
					predicates.add(criteriaBuilder.like(root.get("description"), "%"+item.get("description")+"%"));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
			}
		});
		
	}

}
