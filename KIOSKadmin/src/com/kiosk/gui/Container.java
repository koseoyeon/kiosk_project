package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kiosk.MainFrame;

public class Container extends JPanel{
	MainFrame mainFrame;
//============JMenubar=================
	JMenuBar bar_menu;
	String[] str_menu = { "재고 관리", "매출 확인"};
	JMenu[] menu = new JMenu[str_menu.length];
//===================================	
	
	
	public JPanel[] pages = new JPanel[menu.length];
	public Product product;
	public Sales sales;
	
	public Container(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		
		
		pages[0] = new Product(mainFrame);
		pages[1] = new Sales(mainFrame);
		product = (Product) pages[0];
		sales = (Sales) pages[1];
		product.setVisible(true);
		for(int i=0;i<pages.length;i++) {
			add(pages[i]);
		}
		
		setMenuBar();
		setMenuAction();
		setBackground(Color.darkGray);
		setVisible(true);

	}

	//==========method==================
	public void setMenuBar() {
		bar_menu = new JMenuBar();

		for (int i = 0; i < menu.length; i++) {
			menu[i] = new JMenu(str_menu[i]);
			menu[i].setPreferredSize(new Dimension(800,60));
			menu[i].setFont(new Font("굴림체", Font.BOLD, 24));
			menu[i].setHorizontalAlignment(SwingConstants.RIGHT);
			menu[i].setBorder(BorderFactory.createLineBorder(Color.PINK));
			bar_menu.add(menu[i]);
		}
		mainFrame.setJMenuBar(bar_menu);
	}
	public void setMenuAction() {
		for (int i = 0; i < menu.length; i++) {
			if (pages[i] != null) {
				menu[i].addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						for(int i = 0 ; i < menu.length; i ++) {
							if(e.getSource()==menu[i]) {
								pages[i].setVisible(true);
							}
							else if(pages[i]!=null){
								pages[i].setVisible(false);
							}
						}
					}
				});
			}
		}
	}
}
