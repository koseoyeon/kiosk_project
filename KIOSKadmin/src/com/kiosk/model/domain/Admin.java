package com.kiosk.model.domain;

import java.io.Serializable;

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	private int admin_id; // pk
	private String id;
	private String pw;
	private String name;
	private Auth auth; // ass
	private Exist exist; // ass

	public int getAdmin_id() {
		return admin_id;
	}

	public String getId() {
		return id;
	}

	public String getPw() {
		return pw;
	}

	public String getName() {
		return name;
	}

	public Auth getAuth() {
		return auth;
	}

	public Exist getExist() {
		return exist;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAuth(Auth auth) {
		this.auth = auth;
	}

	public void setExist(Exist exist) {
		this.exist = exist;
	}

}
