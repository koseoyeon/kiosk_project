package com.kiosk.commons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class Button extends JButton{
	int id;
	String name;
	int width;
	int height;
	Color color;
	
	public Button(int id, String name, int width, int height, Color color) {
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.color = color;
		
		this.setFont(new Font(null,Font.BOLD, 14));
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(color);
		this.setText(name);
	}
}
