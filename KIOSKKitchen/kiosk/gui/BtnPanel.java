package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.Button;
import com.kiosk.commons.Formatter;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.domain.PayType;
import com.kiosk.model.domain.Stock;

public class BtnPanel extends JPanel {
	MainFrame mainFrame;
	Button btn_allCancle;
	Button btn_cash;
	Button btn_card;
	List<ShowCart> cartList = new ArrayList<ShowCart>();
	List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();
	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

	int order_num = 0;
	int pay_type_id = 0;
	String pay_menthod = "";

	public void setPay_type_id(int pay_type_id) {
		this.pay_type_id = pay_type_id;
	}

	public int getPay_type_id() {
		return pay_type_id;
	}

	public void setPay_menthod(String pay_menthod) {
		this.pay_menthod = pay_menthod;
	}

	public String getPay_menthod() {
		return pay_menthod;
	}

	public BtnPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		btn_allCancle = new Button(1, "<html>전체<br />취소</html>", 90, 90, new Color(184, 184, 184));
		btn_cash = new Button(2, "현금", 90, 90, new Color(255, 90, 255));
		btn_card = new Button(3, "카드", 90, 90, new Color(157, 207, 255));

		add(btn_allCancle);
		add(btn_cash);
		add(btn_card);

		btn_cash.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setPay_type_id(1);
				setPay_menthod("현금");
				checkRegist();
			}
		});

		btn_card.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setPay_type_id(2);
				setPay_menthod("카드");
				checkRegist();
			}
		});

		setPreferredSize(new Dimension(290, 95));
		setBackground(new Color(250, 224, 212));
		setVisible(true);
	}

	public void checkRegist() {
		System.out.println("결제 요청");
		this.cartList = mainFrame.myWin.cartPanel.cartContainer.cartList;
		if (cartList.size() == 0) {
			JOptionPane.showMessageDialog(mainFrame, "주문할 상품을 선택해주세요.");
			return;
		}
		int result = JOptionPane.showConfirmDialog(mainFrame, getPay_menthod() + "결제 진행하시겠습니까?");
		if (result == JOptionPane.OK_OPTION) {
			salesSelectAllByAdminAndDate();
		}
	}

	public void salesRegist() {
		
		for (int i = 0; i < cartList.size(); i++) {
			ShowCart showCart = cartList.get(i);
			System.out.println("메뉴 : "+showCart.menu_id);
			System.out.println("갯수 : "+showCart.lb_ea.getText());
			OrderDetail orderDetail = new OrderDetail();
			Menu menu = new Menu();
			menu.setMenu_id(showCart.menu_id);
			orderDetail.setMenu(menu);
			orderDetail.setEa(Integer.parseInt(showCart.lb_ea.getText()));
			orderDetailList.add(orderDetail);
		}
		System.out.println("결제하는 곳 : 결제 타입 : "+pay_type_id);
		System.out.println("결제하는 곳 : 주문 번호 : " + order_num);
		System.out.println("결제하는 곳 : 주문 총 금액 : " + Formatter.getOriginNum(mainFrame.myWin.cartSum.lb_selectedSum.getText()));
		
		OrderSummary orderSummary = new OrderSummary();
		orderSummary.setDevice(mainFrame.getDevice());
		PayType payType = new PayType();
		payType.setPay_type_id(pay_type_id);
		orderSummary.setPayType(payType);
		orderSummary.setOrder_num(order_num);
		orderSummary.setOrder_price(Formatter.getOriginNum(mainFrame.myWin.cartSum.lb_selectedSum.getText()));
		mainFrame.requestController.salesRegist(orderSummary, orderDetailList);
	}

	public void salesSelectAllByAdminAndDate() {
		mainFrame.requestController.salesSelectAllByAdminAndDate(mainFrame.getAdmin().getAdmin_id());
	}

	// 주문번호 조회 결과
	public void responseResult(JSONObject jsonObject) {
		System.out.println("orderSummaryList : " + jsonObject.get("orderSummaryList"));
		this.orderSummaryList = (List<OrderSummary>) jsonObject.get("orderSummaryList");
		order_num = 0;
		order_num = orderSummaryList.size();
		order_num++;
		salesRegist();
	}
	
	public void salesRegistResponseResult(boolean result) {
		if(result) {
			JOptionPane.showMessageDialog(mainFrame, "결제가 완료되었습니다.");
			/*
			 * for(int i=0; i<mainFrame.myWin.cartPanel.cartContainer.cartList.size(); i++)
			 * { //mainFrame.myWin.cartPanel.cartContainer.cartList.remove(i); }
			 */
			cartList.removeAll(cartList);
			orderDetailList.removeAll(orderDetailList);
			mainFrame.myWin.cartPanel.cartContainer.renderCart(mainFrame.myWin.cartPanel.cartContainer.cartList);
			mainFrame.myWin.cartSum.renderCartSum(mainFrame.myWin.cartPanel.cartContainer.cartList);
		}else {
			JOptionPane.showMessageDialog(mainFrame, "결제 실패\n매장 직원에게 문의해주세요.");
		}
	}
}
