package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.kiosk.MainFrame;

public class MenuPanel extends JPanel{
	MainFrame mainFrame;
	
	public MenuNavi menuNavi;
	public MenuContainer menuContainer;
	MenuPager menuPager;
	
	public MenuPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		System.out.println("Title패널 생성");
		
		menuNavi = new MenuNavi(mainFrame);
		menuContainer = new MenuContainer(mainFrame);		
		menuPager = new MenuPager(mainFrame);
		
		add(menuNavi);
		add(menuContainer);
		add(menuPager);
		
		setPreferredSize(new Dimension(600,480));
		setBackground(new Color(250, 224, 212));
		setVisible(true);
	}
}
