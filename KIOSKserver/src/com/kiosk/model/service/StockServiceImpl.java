package com.kiosk.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.model.domain.Stock;
import com.kiosk.model.repository.StockDAO;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockDAO stockDAO;
	
	@Override
	public Stock select(int stock_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Stock stock) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Stock stock) throws RuntimeException{
		try {
			int result = stockDAO.update(stock);
			System.out.println("서비스에서의 result : " + result);

		} catch (RuntimeException e) {
			throw new RuntimeException("수정 실패");
		}
	}

	@Override
	public void delete(int stock_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Stock> selectAll() {
		// TODO Auto-generated method stub
		return stockDAO.selectAll();
	}

	@Override
	public List<Stock> selectAllByAdmin(int admin_id) {
		// TODO Auto-generated method stub
		return stockDAO.selectAllByAdmin(admin_id);
	}

}
