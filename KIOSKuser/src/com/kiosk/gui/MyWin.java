package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Stock;

public class MyWin extends JPanel {
	MainFrame mainFrame;
	
	// 각종 자식 패널 생성
	Title title;
	public MenuPanel menuPanel;
	public CartPanel cartPanel;
	public CartSum cartSum;
	public BtnPanel btnPanel;
	public Receipt receipt;

	public MyWin(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		title = new Title(mainFrame);
		menuPanel = new MenuPanel(mainFrame);
		cartPanel = new CartPanel(mainFrame);
		cartSum = new CartSum(mainFrame);
		btnPanel = new BtnPanel(mainFrame);
		receipt = new Receipt(mainFrame);

		add(title);
		add(menuPanel);
		add(cartPanel);
		add(cartSum);
		add(btnPanel);

		setPreferredSize(new Dimension(600, 900));
		setBackground(new Color(250, 224, 212));
		setVisible(true);
	}
	
	
}
