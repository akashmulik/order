package com.order.it.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.order.it.entity.Cart;
import com.order.it.entity.Stock;
import com.order.it.exception.OutOfStockException;

@Component
public class GeneralUtilities {

	public List<Stock> deductOrderedQtyFromStock(List<Cart> items) throws OutOfStockException {
		// TODO Auto-generated method stub
		
		List<Stock> stocksToUpdate = new ArrayList<Stock>();
		for(Cart cartItem : items) {
			float stockQty = cartItem.getProduct().getStock().getTotalQty();
			int qtyPerUnit = cartItem.getProduct().getQuantity();
			int orderedQty = cartItem.getQty();
			int unitId = cartItem.getProduct().getUnit().getId();
			
			// if unit is GMs/MLs, convert qty to KGs/LTRs
			if(unitId == 3 || unitId == 4) {
				float totalOrderedQty = qtyPerUnit * orderedQty;
				float qtyInTons = totalOrderedQty/1000.0f;
				stockQty = stockQty - qtyInTons;
			}
			// else if unit is KG, LTR or Net
			else if(unitId == 1 || unitId == 2 || unitId ==5) {
				stockQty = stockQty - (qtyPerUnit*orderedQty);
			}
			if(stockQty < 0)
				throw new OutOfStockException("One or more products ran out of stock");
			// set updated qty to stock
			Stock stock = new Stock();
			stock.setStockId(cartItem.getProduct().getStock().getStockId());
			stock.setTotalQty(stockQty);
			stocksToUpdate.add(stock);
		}
		return stocksToUpdate;
	}

	public String getCurrentDateString() {
		// TODO Auto-generated method stub
		Date todayDateTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(todayDateTime);
	}

}
