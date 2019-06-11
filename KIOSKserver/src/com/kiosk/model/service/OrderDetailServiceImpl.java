package com.kiosk.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.repository.OrderDetailDAO;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	@Override
	public OrderDetail select(int order_detail_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(OrderDetail orderDetail) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int order_detail_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderDetail> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OrderDetail> selectAllByOrderSummary(int order_summary_id) {
		// TODO Auto-generated method stub
		return orderDetailDAO.selectAllByOrderSummary(order_summary_id);
	}

	@Override
	public void insert(List<OrderDetail> orderDetailList) {
		// TODO Auto-generated method stub
		orderDetailDAO.insert(orderDetailList);
	}
}
