package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.kiosk.MainFrame;
import com.kiosk.commons.Formatter;
import com.kiosk.commons.ImageButton;
import com.kiosk.commons.Label;
import com.kiosk.model.domain.Stock;

public class ShowCart extends JPanel {
	Label lb_name;
	ImageButton bt_minus;
	public Label lb_ea;
	ImageButton bt_plus;
	Label lb_price;
	ImageButton bt_delete;

	String name;
	String price;
	MainFrame mainFrame;
	ShowCart cart = this;
	int menu_id = 0;
	
	List<Stock> stockList = new ArrayList<Stock>();
	
	public ShowCart(String name, String price,MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		this.name = name;		
		this.price = price;
		 
		getMenuPk();

		lb_name = new Label(menu_id,name, 180, 46, 14, new Color(234, 234, 234));
		bt_minus = new ImageButton("minus.png", 46, 46);
		lb_ea = new Label(menu_id,"1", 68, 46, 14, new Color(234, 234, 234));
		bt_plus = new ImageButton("plus.png", 46, 46);
		lb_price = new Label(menu_id,Formatter.getCurrency((Integer.parseInt(price))), 160, 46, 14, new Color(234, 234, 234));
		bt_delete = new ImageButton("delete.png", 40, 46);

		add(lb_name);
		add(bt_minus);
		add(lb_ea);
		add(bt_plus);
		add(lb_price);
		add(bt_delete);

		setPreferredSize(new Dimension(580, 46));
		setBackground(new Color(250, 224, 212));
		setVisible(true);
	}
	
	public void getMenuPk() {
		this.stockList = mainFrame.myWin.menuPanel.menuContainer.stockList;
		for(int i=0; i<stockList.size(); i++) {
			if(name.equals(stockList.get(i).getMenu().getMenu_name())) {
				this.menu_id = stockList.get(i).getMenu().getMenu_id();
			}
		}
	}
}
