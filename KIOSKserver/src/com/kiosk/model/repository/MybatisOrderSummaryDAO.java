package com.kiosk.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kiosk.model.domain.OrderSummary;

@Repository
public class MybatisOrderSummaryDAO implements OrderSummaryDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public OrderSummary select(int order_summary_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("OrderSummary.insert", orderSummary);
	}

	@Override
	public int update(OrderSummary orderSummary) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int order_detail_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderSummary> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("OrderSummary.selectAll");
	}

	@Override
	public List<OrderSummary> selectByAdminId(int admin_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("OrderSummary.selectByAdminId", admin_id);
	}

	@Override
	public List<OrderSummary> selectByDate(int admin_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("OrderSummary.selectByDate", admin_id);
	}

	@Override
	public List<OrderSummary> salesSelectAllByDate(OrderSummary orderSummary) {

		return sqlSessionTemplate.selectList("OrderSummary.selectAllByDate", orderSummary);
	}

}
