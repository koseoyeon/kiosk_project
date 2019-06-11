package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kiosk.MainFrame;

public class Title extends JPanel{
	MainFrame mainFrame;
	
	JLabel lb_title;
	
	public Title(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		this.setLayout(new BorderLayout());
		
		lb_title = new JLabel();
		lb_title.setText("KIOSK");
		lb_title.setFont(new Font(null, Font.BOLD, 35));
		lb_title.setVerticalAlignment(SwingConstants.CENTER);
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(lb_title);
		
		setPreferredSize(new Dimension(600,70));
		setBackground(new Color(250, 224, 212));
		setVisible(true);	
	}
}
