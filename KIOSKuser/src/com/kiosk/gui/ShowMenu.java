package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kiosk.MainFrame;
import com.kiosk.commons.Formatter;
import com.kiosk.commons.ImageLabel;
import com.kiosk.commons.Label;

public class ShowMenu extends JPanel {
	ImageLabel lb_image;
	Label lb_name;
	Label lb_price;

	String name;
	String price;
	String file_name;

	public void setName(String name) {
		this.name = name;
		System.out.println("name : " + name);
	}

	public String getName() {
		return name;
	}

	public ShowMenu(String name,String price, String file_name) {
		this.file_name = file_name;
		this.name = name;
		this.price=price;
		this.setLayout(new BorderLayout());

		lb_image = new ImageLabel(file_name, 190, 78, new Color(234, 234, 234));
		lb_image.setPreferredSize(new Dimension(190, 78));
		lb_name = new Label(1,name, 190, 78, 15, new Color(234, 234, 234));
		lb_price = new Label(1,Formatter.getCurrency((Integer.parseInt(price))), 170, 78, 20, new Color(234, 234, 234));

		add(lb_image, BorderLayout.WEST);
		add(lb_name, BorderLayout.CENTER);
		add(lb_price, BorderLayout.EAST);

		setPreferredSize(new Dimension(580, 80));
		setBackground(new Color(250, 224, 212));
		setVisible(true);

	}
}
