package com.kiosk.commons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label2 extends JLabel{
	public String name;
	int width;
	int height;
	int FontSize;
	Color color;
	
	
	public Label2(String name, int width, int height, int FontSize, Color color) {
		this.name = name;
		this.width = width;
		this.height = height;
		this.FontSize = FontSize;
		this.color = color;

		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setPreferredSize(new Dimension(width,height));
		this.setText(name);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setBackground(Color.WHITE);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(color);
		this.setFont(new Font(null, Font.BOLD, FontSize));
	}
}
