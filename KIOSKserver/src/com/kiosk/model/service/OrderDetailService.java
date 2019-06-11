package com.kiosk.model.service;

import java.util.List;

import com.kiosk.model.domain.OrderDetail;

public interface OrderDetailService {
	public OrderDetail select(int order_detail_id);
	public void insert(List<OrderDetail> orderDetailLit);
	public void update(OrderDetail orderDetail);
	public void delete(int order_detail_id);
	public List<OrderDetail> selectAll();
	public List<OrderDetail> selectAllByOrderSummary(int order_summary_id);
}
