package com.kiosk.gui;

import java.awt.BorderLayout;
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

public class CartPanel extends JPanel {
	MainFrame mainFrame;
	public CartContainer cartContainer;
	CartPager cartPager;
	int selectedPrice = 0;

	public CartPanel(MainFrame mainFrame) {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		this.mainFrame = mainFrame;

		cartContainer = new CartContainer(mainFrame);
		cartPager = new CartPager(mainFrame);

		add(cartContainer);
		add(cartPager);

		setPreferredSize(new Dimension(600, 180));
		setBackground(new Color(250, 224, 212));
		// setBackground(Color.green);
		setVisible(true);

	}	
}
