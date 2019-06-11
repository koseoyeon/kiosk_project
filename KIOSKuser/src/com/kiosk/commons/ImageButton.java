package com.kiosk.commons;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ImageButton extends JButton{
	String path;
	URL url;
	ImageIcon icon;
	Image newImg;
	int width;
	int height;
	
	public ImageButton(String path, int width, int height) {
		this.path = path;
		this.width = width;
		this.height = height;
		url = getClass().getClassLoader().getResource(path);
		icon = new ImageIcon(url);
		newImg = icon.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		this.setIcon(new ImageIcon(newImg));
		this.setPreferredSize(new Dimension(width,height));
		this.setBackground(new Color(255, 220, 108));
	}
}
