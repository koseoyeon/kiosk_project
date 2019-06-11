package com.kiosk.commons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ImageLabel extends JLabel{
	String path;
	URL url;
	ImageIcon icon;
	Image newImg;
	int width;
	int height;
	Color bgColor;
	
	public ImageLabel(String path, int width, int height, Color bgColor) {
		System.out.println("path : "+path);
		this.path = path;
		this.width = width;
		this.height = height;
		this.bgColor = bgColor;
		
		url = getClass().getClassLoader().getResource(path);
		icon = new ImageIcon(url);
		newImg = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		this.setIcon(new ImageIcon(newImg));
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(bgColor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
}
