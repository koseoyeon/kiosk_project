package com.kiosk.model.repository;

import java.util.List;

import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.OrderDetail;

public interface OrderDetailDAO {
	public OrderDetail select(int order_detail_id);
	public int insert(List<OrderDetail> orderDetailList);
	public int update(OrderDetail orderDetail);
	public int delete(int order_detail_id);
	public List<OrderDetail> selectAll();
	public List<OrderDetail> selectAllByOrderSummary(int order_summary_id);
}
