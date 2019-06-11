package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.kiosk.MainFrame;

public class Store extends JPanel{
	MainFrame mainFrame;
	public StoreAccount storeAccount;
	public StoreTable storeTable;
	public Store(	MainFrame mainFrame) {
		setLayout(new BorderLayout());
		this.mainFrame = mainFrame;
		
		storeTable = new StoreTable(mainFrame);
		storeAccount = new StoreAccount(mainFrame);
		
		add(storeTable,BorderLayout.CENTER);
		add(storeAccount,BorderLayout.WEST);
		
		
		setBackground(Color.red);
		setPreferredSize(new Dimension(1200,800));
		setVisible(false);
		
	}
}
