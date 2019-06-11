package com.kiosk.model.domain;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	private int menu_id; // pk
	private Category category; // ass
	private String menu_name;
	private int price;
	private String file_name;
	private Exist exist; // ass
	private File file;
	private String encoderImg;



	public String getEncoderImg() {
		return encoderImg;
	}

	public void setEncoderImg(String encoderImg) {
		this.encoderImg = encoderImg;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public int getMenu_id() {
		return menu_id;
	}

	public Category getCategory() {
		return category;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public int getPrice() {
		return price;
	}

	public String getFile_name() {
		return file_name;
	}

	public Exist getExist() {
		return exist;
	}

	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setExist(Exist exist) {
		this.exist = exist;
	}

}
