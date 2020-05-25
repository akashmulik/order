package com.order.it.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.order.it.entity.Cart;
import com.order.it.entity.LiveOrder;
import com.order.it.entity.comppk.CartPK;

public interface LiveOrderReopsitory extends CrudRepository<LiveOrder, CartPK> {

	List<LiveOrder> findByIdMobileNo(String mobileNo);
}
