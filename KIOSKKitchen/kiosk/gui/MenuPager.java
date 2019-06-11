package com.kiosk.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.kiosk.MainFrame;
import com.kiosk.commons.ButtonPager;
import com.kiosk.commons.ImageButton;

public class MenuPager extends JPanel {
	MainFrame mainFrame;
	ImageButton bt_left;
	ImageButton bt_right;
	JPanel p_center;
	int cnt = 0;
	ButtonPager buttonPager = new ButtonPager();

	public MenuPager(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		bt_left = new ImageButton("left.png", 60, 40);
		p_center = new JPanel();
		p_center.setPreferredSize(new Dimension(440, 50));
		p_center.setBackground(new Color(250, 224, 212));
		bt_right = new ImageButton("right.png", 60, 40);

		add(bt_left);
		add(p_center);
		add(bt_right);
		bt_right.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cnt = buttonPager.afterRender(4, cnt, mainFrame.myWin.menuPanel.menuContainer,
						mainFrame.myWin.menuPanel.menuContainer.addedList);
			}
		});

		bt_left.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cnt = buttonPager.beforeRender(4, cnt, mainFrame.myWin.menuPanel.menuContainer,
						mainFrame.myWin.menuPanel.menuContainer.addedList);
			}
		});

		setPreferredSize(new Dimension(600, 50));
		setBackground(new Color(250, 224, 212));
		setVisible(true);
	}

}
