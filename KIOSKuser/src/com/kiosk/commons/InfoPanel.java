package com.kiosk.commons;

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
import com.kiosk.model.domain.Stock;

public class InfoPanel extends JPanel {
	Label2 lb_name;
	Label2 lb_ea;
	Label2 lb_price;

	String name;
	String ea;
	String price;
	MainFrame mainFrame;
	
	public InfoPanel(String name, String ea, String price,MainFrame mainFrame) {

		this.mainFrame = mainFrame;
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		
		this.name = name;	
		this.ea = ea;
		this.price = price;


		lb_name = new Label2(name, 153, 50, 14, Color.WHITE);
		lb_ea = new Label2(ea, 153, 50, 14,  Color.WHITE);
		lb_price = new Label2(price, 153, 50, 14,  Color.WHITE);

		add(lb_name);
		add(lb_ea);
		add(lb_price);

		setPreferredSize(new Dimension(460, 50));
		setBackground(Color.white);
		setVisible(true);
	}
}
