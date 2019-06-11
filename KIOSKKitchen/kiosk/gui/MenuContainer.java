package com.kiosk.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Stock;

public class MenuContainer extends JPanel {
	MainFrame mainFrame;
	JLabel lb_info;
	List<ShowMenu> menuList = new ArrayList<ShowMenu>();
	List<Stock> stockList = new ArrayList<Stock>();
	List<ShowMenu> addedList = new ArrayList<ShowMenu>();

	public int category_id = 0;

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public MenuContainer(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		lb_info = new JLabel("�� �ֹ��Ͻ� ī�װ��� �����Ͻø� �޴��� ��Ÿ���ϴ�.");
		
		lb_info.setPreferredSize(new Dimension(500,300));
		lb_info.setForeground(Color.black);
		lb_info.setFont(new Font(null, Font.BOLD, 14));
		

		add(lb_info);
		
		setPreferredSize(new Dimension(600, 345));
		setBackground(new Color(250, 224, 212));
		// setBackground(Color.yellow);
		setVisible(true);
	}

	// �޴� ����Ʈ ��û ���
	public void responseResult(JSONObject jsonObject) {
		System.out.println("stockList : " + jsonObject.get("stockList"));
		this.stockList = (List<Stock>) jsonObject.get("stockList");
		// mainFrame.container.store.storeAccount.setAccount(adminList);
		for (int i = 0; i < stockList.size(); i++) {
			System.out.println("�޴� ����Ʈ : " + stockList.get(i).getMenu().getMenu_name());
		}
	}
	
	public void renderMenu(int category_id) {
		addedList.removeAll(addedList);
		//System.out.println("�޴�����Ʈ  : "+menuList.size());
		System.out.println("renderMenu() ��ȯ");
		String name = "";
		String price = "";
		System.out.println("stockList  : "+stockList.size());
		for(int i=0; i<stockList.size(); i++) {
			Stock stock = stockList.get(i);
			name = stock.getMenu().getMenu_name();
			price = Integer.toString(stock.getMenu().getPrice());
			ShowMenu showMenu = new ShowMenu(name, price);
			if(name.equals(stockList.get(i).getMenu().getMenu_name())){
				menuList.add(showMenu);
			}
			System.out.println("�޴�����Ʈ  : "+menuList.size());
			if(stock.getMenu().getCategory().getCategory_id() == category_id) {
				addedList.add(showMenu);
				for(int k=0;k<addedList.size();k++) {
	
					this.add(addedList.get(k));
				}
			}
		}
		for (int k = 0; k < menuList.size(); k++) {
			System.out.println("ù��° �ٿ��� �޴�"+menuList.get(k));
			ShowMenu showMenu = menuList.get(k);
			menuList.get(k).addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					mainFrame.myWin.cartPanel.cartContainer.getShowMenu(showMenu);

				}
			});
		}
		this.validate();
		this.repaint();
	}
}
