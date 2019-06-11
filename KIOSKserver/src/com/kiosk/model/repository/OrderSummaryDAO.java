package com.kiosk.model.repository;

import java.util.List;

import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;

public interface OrderSummaryDAO {
	public OrderSummary select(int order_summary_id);
	public int insert(OrderSummary orderSummary);
	public int update(OrderSummary orderSummary);
	public int delete(int order_detail_id);
	public List<OrderSummary> selectAll();
	public List<OrderSummary> selectByAdminId(int admin_id);
	public List<OrderSummary> selectByDate(int admin_id);
	public List<OrderSummary> salesSelectAllByDate(OrderSummary orderSummary);
	
}
