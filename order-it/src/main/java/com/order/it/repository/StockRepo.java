package com.order.it.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.order.it.entity.Stock;

@Repository
public interface StockRepo extends CrudRepository<Stock, Integer>{

	@Modifying
	@Query("update Stock s set s.totalQty = ?1 where s.stockId = ?2")
	int updateStockQty(float qty, int id);
}
