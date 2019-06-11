package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import com.kiosk.MainFrame;
import com.kiosk.commons.Label;

public class CartNavi extends JPanel{
	MainFrame mainFrame;
	Label lb_menu;
	Label lb_ea;
	Label lb_price;
	Label lb_delete;

	public CartNavi(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		FlowLayout flowLayout =new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		this.mainFrame = mainFrame;
		lb_menu = new Label(1,"메뉴", 180, 50, 14, new Color(135, 94, 0));
		lb_menu.setForeground(Color.WHITE);
		lb_ea = new Label(2,"수량", 160, 50, 14, new Color(135, 94, 0));
		lb_ea.setForeground(Color.WHITE);
		lb_price = new Label(3,"가격", 160, 50, 14, new Color(135, 94, 0));
		lb_price.setForeground(Color.WHITE);
		lb_delete = new Label(4,"", 40, 50, 14, new Color(135, 94, 0));
		lb_delete.setForeground(Color.WHITE);
		
		
		add(lb_menu);
		add(lb_ea);
		add(lb_price);
		add(lb_delete);
		
		setPreferredSize(new Dimension(540, 42));
		setBackground(new Color(135, 94, 0));
		setVisible(true);
	}
}
