package com.kiosk.model.service;

import java.util.List;

import com.kiosk.model.domain.Stock;


public interface StockService{
	public Stock select(int stock_id);
	public void insert(Stock stock);
	public void update(Stock stock);
	public void delete(int stock_id);
	public List<Stock> selectAll();
	public List<Stock> selectAllByAdmin(int admin_id);
}
