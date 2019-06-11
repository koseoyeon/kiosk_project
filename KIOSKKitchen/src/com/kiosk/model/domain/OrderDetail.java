package com.kiosk.model.domain;

import java.io.Serializable;

public class OrderDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	private int order_detail_id; // pk
	private OrderSummary orderSummary; // ass
	private Menu menu; // ass
	private int ea;

	public int getOrder_detail_id() {
		return order_detail_id;
	}

	public void setOrder_detail_id(int order_detail_id) {
		this.order_detail_id = order_detail_id;
	}

	public OrderSummary getOrderSummary() {
		return orderSummary;
	}

	public void setOrderSummary(OrderSummary orderSummary) {
		this.orderSummary = orderSummary;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public int getEa() {
		return ea;
	}

	public void setEa(int ea) {
		this.ea = ea;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
