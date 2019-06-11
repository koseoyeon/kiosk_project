package com.kiosk.model.domain;

import java.io.Serializable;

public class Stock implements Serializable {

	private static final long serialVersionUID = 1L;
	private int stock_id; // pk
	private Menu menu; // ass
	private Admin admin; // ass
	private State state; // ass

	public int getStock_id() {
		return stock_id;
	}

	public void setStock_id(int stock_id) {
		this.stock_id = stock_id;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
