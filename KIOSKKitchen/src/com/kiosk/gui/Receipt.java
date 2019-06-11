package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.kiosk.MainFrame;
import com.kiosk.commons.Label2;
import com.kiosk.commons.InfoPanel;
import com.kiosk.model.domain.OrderDetail;

public class Receipt extends JDialog{
	MainFrame mainFrame;
	
	JPanel wrapper;
	
	Label2 lb_title_order_num;
	Label2 lb_title_admin_name;
	Label2 lb_title_order_date;
	Label2 lb_order_num;
	Label2 lb_admin_name;
	Label2 lb_order_date;
	Label2 lb_1;
	Label2 lb_title_menu;
	Label2 lb_title_ea;
	Label2 lb_menu;
	Label2 lb_ea;
	Label2 lb_price;
	
	JPanel container;
	JPanel p_title;
	
	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
	
	String order_num = "";
	String order_date = "";
	String menu_name = "";
	String pay_method = "";
	String admin_name = "";
	int ea = 0;
	int order_price = 0;
	int menu_price = 0;
	
	public Receipt(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		
		wrapper = new JPanel();
		wrapper.setPreferredSize(new Dimension(500, 700));
		wrapper.setLayout(flowLayout);
		wrapper.setBackground(Color.WHITE);
		
		p_title = new JPanel();
		p_title.setPreferredSize(new Dimension(500, 50));
		p_title.setLayout(flowLayout);
		p_title.setBackground(Color.WHITE);
		
		container = new JPanel();
		container.setBackground(Color.WHITE);
		
		JScrollPane scroller = new JScrollPane(container);
		scroller.setPreferredSize(new Dimension(480, 200));
		
		
		lb_title_order_num = new Label2("주문 번호 : ", 280, 100, 35, Color.WHITE);
		lb_title_order_num.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_order_num = new Label2(order_num, 160, 100, 35, Color.WHITE);
		lb_order_num.setHorizontalAlignment(SwingConstants.LEFT);
		lb_title_admin_name = new Label2("매장명 : ", 380, 50, 15, Color.WHITE);
		lb_title_admin_name.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_admin_name = new Label2(admin_name, 50, 50, 15, Color.WHITE);
		lb_admin_name.setHorizontalAlignment(SwingConstants.LEFT);
		lb_title_order_date = new Label2("결제 시간 : ", 80, 50, 15, Color.WHITE);
		lb_title_order_date.setHorizontalAlignment(SwingConstants.LEFT);
		lb_order_date = new Label2(order_date, 380, 50, 15, Color.WHITE);
		lb_order_date.setHorizontalAlignment(SwingConstants.LEFT);
		lb_1 = new Label2("=============== 구매 내역 ===============",500,50,20, Color.WHITE);
		InfoPanel infoPanel = new InfoPanel("    메뉴    ", "    수량    ", mainFrame);
		p_title.add(infoPanel);
		
		
		wrapper.add(lb_title_order_num);
		wrapper.add(lb_order_num);
		wrapper.add(lb_title_admin_name);
		wrapper.add(lb_admin_name);
		wrapper.add(lb_title_order_date);
		wrapper.add(lb_order_date);
		wrapper.add(lb_1);
		wrapper.add(p_title);
		wrapper.add(scroller);

		
		this.add(wrapper);
	
		
		setTitle("주문 확인서");
		this.setResizable(false);
		this.setSize(500, 550);
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	
	public void fillReceipt(List<OrderDetail> orderDetailList) {
		System.out.println("fillReceipt메서드 접근");
		container.removeAll();
		OrderDetail orderDetail = new OrderDetail();
		this.orderDetailList = orderDetailList;
		System.out.println("orderDetail 사이즈 : "+orderDetailList.size());
		container.setLayout(new GridLayout(orderDetailList.size(), 1));
		for(int i=0; i<orderDetailList.size(); i++) {
			orderDetail = orderDetailList.get(0);
		}
		if(orderDetailList.size() != 0) {
			fillPayInfo();
		}
		
		lb_order_num.setText(Integer.toString(orderDetail.getOrderSummary().getOrder_num()));
		lb_order_date.setText(orderDetail.getOrderSummary().getOrder_date());
		lb_admin_name.setText(orderDetail.getOrderSummary().getDevice().getAdmin().getName()); 

		this.validate();
		this.repaint();
	}
	
	public void fillPayInfo() {
		for(int i=0; i<orderDetailList.size(); i++) {
			OrderDetail orderDetail = orderDetailList.get(i);
			InfoPanel infoPanel2 = new InfoPanel(orderDetail.getMenu().getMenu_name(), Integer.toString(orderDetail.getEa()), mainFrame);
			container.add(infoPanel2);
		}
	}
}
