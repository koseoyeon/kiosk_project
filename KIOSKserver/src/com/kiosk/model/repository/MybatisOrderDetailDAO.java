package com.kiosk.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kiosk.model.domain.OrderDetail;

@Repository
public class MybatisOrderDetailDAO implements OrderDetailDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public OrderDetail select(int order_detail_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int order_detail_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDetail> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> selectAllByOrderSummary(int order_summary_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("OrderDetail.selectAllByOrderSummary", order_summary_id);
	}

	@Override
	public int insert(List<OrderDetail> orderDetailList) {
		// TODO Auto-generated method stub
		int result =0;
		for(int i=0; i<orderDetailList.size(); i++) {
			OrderDetail orderDetail = orderDetailList.get(i);
			//resultrk 하나라도 1이라면을 처리?
			result = sqlSessionTemplate.insert("OrderDetail.insert", orderDetail);
		}
		return result;
	}
}
