package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.Formatter;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.domain.Stock;

public class CartContainer extends JPanel {
	CartNavi cartNavi;
	MainFrame mainFrame;
	List<ShowCart> cartList = new ArrayList<ShowCart>();
	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
	List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();
	List<Stock> stockList = new ArrayList<Stock>();
	ShowCart showCart;
	
	
	boolean flag = true;

	public CartContainer(MainFrame mainFrame) {
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(0);
		setLayout(flowLayout);
		this.mainFrame = mainFrame;

		cartNavi = new CartNavi(mainFrame);

		add(cartNavi);
		
		setPreferredSize(new Dimension(540, 180));
		setBackground(new Color(250, 224, 212));
		// setBackground(Color.yellow);
		setVisible(true);
	}

	public void getShowMenu(ShowMenu showMenu) {
		boolean flag=false;
		System.out.println("getShowMenu : " + showMenu.name);
		System.out.println("getShowMenu 리스트 사이즈 : " + cartList.size());
		ShowCart showCart=null;
		if(cartList.size()==0) {
			showCart = new ShowCart(showMenu.name, showMenu.price,mainFrame);
			eaChange(showCart);
			delCart(showCart);
			cartList.add(showCart);
			renderCart(cartList);
		}else {
			for (int i = 0; i <cartList.size(); i++) {
				showCart = cartList.get(i);
				if(showMenu.name.equals(showCart.name)) {
					JOptionPane.showMessageDialog(mainFrame, "이미 선택한 메뉴 입니다");
					flag=true;
				}
				
			}
			if(flag==false) {
				showCart = new ShowCart(showMenu.name, showMenu.price,mainFrame);
				eaChange(showCart);

				delCart(showCart);
				cartList.add(showCart);
				renderCart(cartList);
			}
		}
		mainFrame.myWin.cartSum.renderCartSum(cartList);
	}

	
	public void renderCart(List<ShowCart> cartList) {
		removeAll();
		add(cartNavi);
		for (int i = 0; i < cartList.size(); i++) {
			this.add(cartList.get(i));
			System.out.println("renderCart : " + cartList.get(i).name);
		}
		mainFrame.myWin.cartPanel.cartPager.cnt=0;
		this.validate();
		this.repaint();
	}

	public void delCart(ShowCart selectedShowCart) {
		
		selectedShowCart.bt_delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int result = JOptionPane.showConfirmDialog(mainFrame, "해당 메뉴가 삭제됩니다. 그래도 진행하시겠습니까?");
				if(result != JOptionPane.OK_OPTION) {
					return;
				}
				cartList.remove(selectedShowCart);
				renderCart(cartList);
				mainFrame.myWin.cartSum.renderCartSum(cartList);
			}
		});
	}
	public void eaChange(ShowCart showCart) {
		System.out.println("선택된 showCart : "+showCart.name);
		this.showCart = showCart;
		
		showCart.bt_plus.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				int ea=Integer.parseInt(showCart.lb_ea.getText());
				int price = getPrice(showCart);
				ea++;
				showCart.lb_ea.setText(Integer.toString(ea));
				showCart.lb_price.setText(Formatter.getCurrency((price * ea)));
				renderCart(cartList);
				mainFrame.myWin.cartSum.renderCartSum(cartList);
			}
		});
		
		showCart.bt_minus.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				int ea = Integer.parseInt(showCart.lb_ea.getText());
				int price = getPrice(showCart);
				if(ea> 1) {
					ea--;
					showCart.lb_ea.setText(Integer.toString(ea));
					showCart.lb_price.setText(Formatter.getCurrency((price * ea)));
					mainFrame.myWin.cartSum.renderCartSum(cartList);
				}else if(ea <= 1){
					JOptionPane.showMessageDialog(mainFrame, "수량은 1개부터 선택 가능합니다.");
					return;
				}
				renderCart(cartList);
				mainFrame.myWin.cartSum.renderCartSum(cartList);
			}
		});
	}
	
	public int getPrice(ShowCart showCart) {
		stockList = mainFrame.myWin.menuPanel.menuContainer.stockList;
		int price = 0;
		for(int i=0; i<stockList.size(); i++) {
			if(showCart.name.equals(stockList.get(i).getMenu().getMenu_name())){
				price = stockList.get(i).getMenu().getPrice();
			}
		}
		return price;
	}
}
