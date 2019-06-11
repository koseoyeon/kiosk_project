package com.kiosk.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Stock;

@Repository
public class MybatisStockDAO implements StockDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Stock select(int stock_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Stock stock) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("Stock.insert",stock);
	}

	@Override
	public int update(Stock stock) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("Stock.update", stock);
	}

	@Override
	public int delete(int stock_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Stock> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Stock.selectAll");
	}

	@Override
	public List<Stock> selectAllByAdmin(int admin_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Stock.selectAllByAdmin",admin_id);
	}

	@Override
	public int deleteByAdmin(int admin_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("Stock.deleteByAdmin",admin_id);
	}

	@Override
	public int deleteByMenu(int menu_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("Stock.deleteByMenu", menu_id);
	}

}
