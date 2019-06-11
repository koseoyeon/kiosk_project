package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.kiosk.MainFrame;
import com.kiosk.commons.Formatter;
import com.kiosk.commons.Label;

public class CartSum extends JPanel{
	MainFrame mainFrame;
	JPanel p_top;
	Label lb_ea;
	Label lb_selectedEa;
	JPanel p_bottom;
	JLabel lb_sum;
	JLabel lb_selectedSum;
	
	public CartSum(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		this.setLayout(new BorderLayout());
		
		p_top = new JPanel();
		p_top.setLayout(new BorderLayout());
		p_top.setBackground(new Color(250, 224, 212));
		p_top.setPreferredSize(new Dimension(300, 50));
		
		lb_ea = new Label(1,"주문 수량", 110, 42, 14, new Color(255, 220, 108));
		
		lb_selectedEa = new Label(1,"0 건", 170, 42, 14, new Color(234,234,234));
		
		p_bottom = new JPanel();
		p_bottom.setLayout(new BorderLayout());
		p_bottom.setBackground(new Color(250, 224, 212));
		p_bottom.setPreferredSize(new Dimension(300, 50));
		
		lb_sum = new Label(2,"주문 금액", 110, 42, 14, new Color(255, 220, 108));
		
		lb_selectedSum = new Label(2, Formatter.getCurrency(0)+"", 170, 42, 14, new Color(234,234,234));
		
		p_top.add(lb_ea, BorderLayout.WEST);
		p_top.add(lb_selectedEa, BorderLayout.CENTER);
		p_bottom.add(lb_sum, BorderLayout.WEST);
		p_bottom.add(lb_selectedSum, BorderLayout.CENTER);
		add(p_top, BorderLayout.NORTH);
		add(p_bottom, BorderLayout.SOUTH);
		
		setPreferredSize(new Dimension(290, 95));
		setBackground(new Color(250, 224, 212));
		setVisible(true);
	}
	
	public void renderCartSum(List<ShowCart> cartList) {
		System.out.println("renderCartSum : "+cartList.size());
		int ea = 0;
		int price = 0;
		for(int i=0; i<cartList.size(); i++) {
			ea = ea +Integer.parseInt(cartList.get(i).lb_ea.getText());
			price = price + Formatter.getOriginNum(cartList.get(i).lb_price.getText());
		}
		lb_selectedEa.setText(ea+" 건");
		lb_selectedSum.setText(Formatter.getCurrency(price));
		validate();
		repaint();
	}
}
