package com.order.it.repository;

import org.springframework.data.repository.CrudRepository;

import com.order.it.entity.Products;

public interface ProductRepository extends CrudRepository<Products, Integer> {

	
}
