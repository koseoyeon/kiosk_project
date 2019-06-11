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
	String[] str_menu = { "상품 관리", "매출 정보", "가맹점 관리"};
	JMenu[] menu = new JMenu[str_menu.length];
//===================================	
	
	
	public JPanel[] pages = new JPanel[menu.length];
	public Store store;
	public Sales sales;
	public MenuContainer menuContainer;
	
	public Container(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		
		pages[0] = new MenuContainer(mainFrame);
		pages[1] = new Sales(mainFrame);
		pages[2] = new Store(mainFrame);
		menuContainer = (MenuContainer) pages[0];
		menuContainer.setVisible(true);
		sales = (Sales) pages[1];
		store = (Store) pages[2];
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
			menu[i].setPreferredSize(new Dimension(400,55));
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
