package com.kiosk.model.domain;

import java.io.Serializable;

public class OrderSummary implements Serializable {

	private static final long serialVersionUID = 1L;
	private int order_summary_id; // pk
	private String order_date;
	private PayType payType; // ass
	private Device device; // ass
	private int order_num;
	private int order_price;

	public int getOrder_summary_id() {
		return order_summary_id;
	}

	public String getOrder_date() {
		return order_date;
	}

	public PayType getPayType() {
		return payType;
	}

	public Device getDevice() {
		return device;
	}

	public int getOrder_num() {
		return order_num;
	}

	public int getOrder_price() {
		return order_price;
	}

	public void setOrder_summary_id(int order_summary_id) {
		this.order_summary_id = order_summary_id;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	public void setPayType(PayType payType) {
		this.payType = payType;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public void setOrder_price(int order_price) {
		this.order_price = order_price;
	}

}
