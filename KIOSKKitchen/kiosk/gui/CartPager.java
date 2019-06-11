package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import com.kiosk.MainFrame;
import com.kiosk.commons.ButtonPager;
import com.kiosk.commons.ImageButton;
import com.kiosk.commons.ReversePager;

public class CartPager extends JPanel{
	MainFrame mainFrame;
	ImageButton bt_up;
	ImageButton bt_down;
	JPanel p_center;
	int cnt = 0;
	ButtonPager buttonPager = new ButtonPager();
	ReversePager reversePager = new ReversePager();
	public CartPager(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		FlowLayout flowLayout =new FlowLayout();
		flowLayout.setHgap(2);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		this.mainFrame = mainFrame;
		
		bt_up = new ImageButton("up.png", 40, 60);
		p_center = new JPanel();
		p_center.setPreferredSize(new Dimension(60, 60));
		p_center.setBackground(new Color(250, 224, 212));
		bt_down = new ImageButton("down.png",  40, 60);

		add(bt_up);
		add(p_center);
		add(bt_down);
		
		bt_up.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				cnt=buttonPager.beforeRender(3, cnt, mainFrame.myWin.cartPanel.cartContainer, mainFrame.myWin.cartPanel.cartContainer.cartList);
			}
		});
		bt_down.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
		
				cnt=buttonPager.afterRender(3, cnt, mainFrame.myWin.cartPanel.cartContainer, mainFrame.myWin.cartPanel.cartContainer.cartList);

			}
		});
		
		
		
		setPreferredSize(new Dimension(46, 180));
		setBackground(new Color(250, 224, 212));
		//setBackground(Color.pink);
		setVisible(true);
	}
}
