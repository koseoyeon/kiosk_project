package com.kiosk.model.repository;

import java.util.List;

import com.kiosk.model.domain.Stock;


public interface StockDAO{
	public Stock select(int stock_id);
	public int insert(Stock stock);
	public int update(Stock stock);
	public int delete(int stock_id);
	public List<Stock> selectAll();
	public List<Stock> selectAllByAdmin(int admin_id);
	public int deleteByAdmin(int admin_id);
	public int deleteByMenu(int menu_id);
}
