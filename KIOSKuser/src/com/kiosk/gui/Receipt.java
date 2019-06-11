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
	Label2 lb_2;
	Label2 lb_title_menu;
	Label2 lb_title_ea;
	Label2 lb_title_price;
	Label2 lb_title_pay_method;
	Label2 lb_title_order_price;
	Label2 lb_menu;
	Label2 lb_ea;
	Label2 lb_price;
	Label2 lb_pay_method;
	Label2 lb_order_price;
	
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
		InfoPanel infoPanel = new InfoPanel(" 메뉴    ", " 수량    ", " 금액    ", mainFrame);	
		p_title.add(infoPanel);
		

		lb_2 = new Label2("=======================================", 500, 50, 20, Color.WHITE);
		lb_title_pay_method = new Label2("결제 수단 : ", 80, 50, 15, Color.WHITE);
		lb_title_pay_method.setHorizontalAlignment(SwingConstants.LEFT);
		lb_pay_method = new Label2(pay_method, 380, 50, 15, Color.WHITE);
		lb_pay_method.setHorizontalAlignment(SwingConstants.LEFT);
		lb_title_order_price = new Label2("결제 금액 : ", 80, 50, 15, Color.WHITE);
		lb_title_order_price.setHorizontalAlignment(SwingConstants.LEFT);
		lb_order_price = new Label2(Integer.toString(order_price)+"원", 380, 50, 15, Color.WHITE);
		lb_order_price.setHorizontalAlignment(SwingConstants.LEFT);
		
		wrapper.add(lb_title_order_num);
		wrapper.add(lb_order_num);
		wrapper.add(lb_title_admin_name);
		wrapper.add(lb_admin_name);
		wrapper.add(lb_title_order_date);
		wrapper.add(lb_order_date);
		wrapper.add(lb_1);
		wrapper.add(p_title);
		wrapper.add(scroller);
		wrapper.add(lb_2);
		wrapper.add(lb_title_pay_method);
		wrapper.add(lb_pay_method);
		wrapper.add(lb_title_order_price);
		wrapper.add(lb_order_price);
		
		this.add(wrapper);
	
		
		setTitle("구매 영수증");
		this.setResizable(false);
		this.setSize(500, 700);
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}
	
	public void fillReceipt(List<OrderDetail> orderDetailList) {
		container.removeAll();
		OrderDetail orderDetail = new OrderDetail();
		this.orderDetailList = orderDetailList;
		container.setLayout(new GridLayout(orderDetailList.size(), 1));
		for(int i=0; i<orderDetailList.size(); i++) {
			orderDetail = orderDetailList.get(0);
		}
		if(orderDetailList.size() != 0) {
			fillPayInfo();
		}
		
		lb_order_num.setText(Integer.toString(orderDetail.getOrderSummary().getOrder_num()));
		lb_order_date.setText(orderDetail.getOrderSummary().getOrder_date());
		lb_pay_method.setText(orderDetail.getOrderSummary().getPayType().getPay_method());
		lb_admin_name.setText(orderDetail.getOrderSummary().getDevice().getAdmin().getName()); 
		lb_order_price.setText(orderDetail.getOrderSummary().getOrder_price()+"원");

		this.validate();
		this.repaint();
	}
	
	public void fillPayInfo() {
		for(int i=0; i<orderDetailList.size(); i++) {
			OrderDetail orderDetail = orderDetailList.get(i);
			InfoPanel infoPanel2 = new InfoPanel(orderDetail.getMenu().getMenu_name(), Integer.toString(orderDetail.getEa()), Integer.toString(orderDetail.getMenu().getPrice())+"원", mainFrame);
			container.add(infoPanel2);
		}
	}
}
