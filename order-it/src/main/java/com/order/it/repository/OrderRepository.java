package com.order.it.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.order.it.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Integer> {

	Order findByMobileNo(String mobileNo);

	@Query("SELECT o FROM Order o WHERE o.mobileNo = ?1 and o.orderStatusId < ?2")
	Order findLiveOrder(String mobileNo, int statusId);

}
