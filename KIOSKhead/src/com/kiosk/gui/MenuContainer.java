package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import com.kiosk.MainFrame;

public class MenuContainer extends JPanel{
	MainFrame mainFrame;
	public MenuInfo menuInfo;
	public MenuTable menuTable;
	public MenuContainer(MainFrame mainFrame) {
		setLayout(new BorderLayout());
		this.mainFrame = mainFrame;
		
		
		menuTable = new MenuTable(mainFrame);
		menuInfo = new MenuInfo(mainFrame);
		
		add(menuTable,BorderLayout.CENTER);
		add(menuInfo,BorderLayout.WEST);
		
		
		setBackground(Color.blue);
		setPreferredSize(new Dimension(1200,800));
		setVisible(false);
	}
}
