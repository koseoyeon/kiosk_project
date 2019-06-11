package com.kiosk.commons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Label extends JLabel{
	public int id = 0;
	public String name;
	int width;
	int height;
	int FontSize;
	Color bgColor;
	
	
	public Label(int id, String name, int width, int height, int FontSize, Color bgColor) {
		this.id = id;
		this.name = name;
		this.width = width;
		this.height = height;
		this.FontSize = FontSize;
		this.bgColor = bgColor;
		
		this.setPreferredSize(new Dimension(width,height));
		this.setText(name);
		this.setVerticalAlignment(SwingConstants.CENTER);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setFont(new Font(null, Font.BOLD, FontSize));
		this.setBackground(bgColor);
		this.setOpaque(true);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
