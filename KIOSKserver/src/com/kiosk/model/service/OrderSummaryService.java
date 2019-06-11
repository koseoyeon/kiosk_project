package com.kiosk.model.service;

import java.util.List;

import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;

public interface OrderSummaryService {
	public OrderSummary select(int order_summary_id);
	public int insert(OrderSummary orderSummary);
	public void update(OrderSummary orderSummary);
	public void delete(int order_detail_id);
	public List<OrderSummary> selectAll();
	public List<OrderSummary> selectByAdminId(int admin_id);
	public List<OrderSummary> selectByDate(int admin_id);
	public List<OrderSummary> salesSelectAllByDate(OrderSummary orderSummary);
}
