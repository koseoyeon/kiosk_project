package com.kiosk.model.domain;

import java.io.Serializable;

public class State implements Serializable {

	private static final long serialVersionUID = 1L;
	private int state_id; // pk
	private String state_type;

	public int getState_id() {
		return state_id;
	}

	public void setState_id(int state_id) {
		this.state_id = state_id;
	}

	public String getState_type() {
		return state_type;
	}

	public void setState_type(String state_type) {
		this.state_type = state_type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
