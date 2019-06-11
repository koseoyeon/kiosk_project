package com.kiosk.model.domain;

import java.io.Serializable;

public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private int category_id; // pk
	private String category_name;
	private Exist exist; // ass


	public int getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public Exist getExist() {
		return exist;
	}

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public void setExist(Exist exist) {
		this.exist = exist;
	}

}
