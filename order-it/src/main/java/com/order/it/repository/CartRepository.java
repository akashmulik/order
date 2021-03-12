package com.order.it.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.order.it.entity.Cart;
import com.order.it.entity.comppk.CartPK;

public interface CartRepository extends CrudRepository<Cart, CartPK> {

	List<Cart> findByIdMobileNo(String mobileNo);
}
