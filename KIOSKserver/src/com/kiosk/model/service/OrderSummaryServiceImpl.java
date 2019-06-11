package com.kiosk.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.repository.OrderSummaryDAO;

@Service
public class OrderSummaryServiceImpl implements OrderSummaryService {

	@Autowired
	private OrderSummaryDAO orderSummaryDAO;
	
	@Override
	public OrderSummary select(int order_summary_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(OrderSummary orderSummary) {
		orderSummaryDAO.insert(orderSummary);
		return orderSummary.getOrder_summary_id();
	}

	@Override
	public void update(OrderSummary orderSummary) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(int order_detail_id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<OrderSummary> selectAll() {
		// TODO Auto-generated method stub
		return orderSummaryDAO.selectAll();
	}

	@Override
	public List<OrderSummary> selectByAdminId(int admin_id) {
		// TODO Auto-generated method stub
		return orderSummaryDAO.selectByAdminId(admin_id);
	}

	@Override
	public List<OrderSummary> selectByDate(int admin_id) {
		// TODO Auto-generated method stub
		return orderSummaryDAO.selectByDate(admin_id);
	}

	@Override
	public List<OrderSummary> salesSelectAllByDate(OrderSummary orderSummary) {
		
		return orderSummaryDAO.salesSelectAllByDate(orderSummary);
	}

}
