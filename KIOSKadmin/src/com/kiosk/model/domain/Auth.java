package com.kiosk.model.domain;

import java.io.Serializable;

public class Auth implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int auth_id; //pk
	private String auth_name;

	public int getAuth_id() {
		return auth_id;
	}

	public void setAuth_id(int auth_id) {
		this.auth_id = auth_id;
	}

	public String getAuth_name() {
		return auth_name;
	}

	public void setAuth_name(String auth_name) {
		this.auth_name = auth_name;
	}
}
