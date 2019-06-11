package com.kiosk.model.domain;

import java.io.Serializable;

public class Device implements Serializable {
	private static final long serialVersionUID = 1L;
	private int device_id; // pk
	private String device_name;
	private Admin admin; // ass
	private Exist exist; // ass

	public int getDevice_id() {
		return device_id;
	}

	public String getDevice_name() {
		return device_name;
	}

	public Admin getAdmin() {
		return admin;
	}

	public Exist getExist() {
		return exist;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public void setExist(Exist exist) {
		this.exist = exist;
	}

}
