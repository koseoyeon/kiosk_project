package com.kiosk.model.domain;

import java.io.Serializable;

public class Exist implements Serializable {
	private static final long serialVersionUID = 1L;
	int exist_id; // pk
	String exist_state;

	public int getExist_id() {
		return exist_id;
	}

	public String getExist_state() {
		return exist_state;
	}

	public void setExist_id(int exist_id) {
		this.exist_id = exist_id;
	}

	public void setExist_state(String exist_state) {
		this.exist_state = exist_state;
	}

}
