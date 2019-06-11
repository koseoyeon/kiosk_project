package com.kiosk.model.domain;

import java.io.Serializable;

public class PayType implements Serializable{

	private static final long serialVersionUID = 1L;
	private int pay_type_id; //pk
	private String pay_method;

	public int getPay_type_id() {
		return pay_type_id;
	}

	public void setPay_type_id(int pay_type_id) {
		this.pay_type_id = pay_type_id;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

}
