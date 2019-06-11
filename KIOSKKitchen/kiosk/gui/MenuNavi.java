package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.Label;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Stock;

public class MenuNavi extends JPanel {
	MainFrame mainFrame;
	public List<Category> categoryList = new ArrayList<Category>();
	
	public MenuNavi(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(5);
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.LEFT);
		setLayout(flowLayout);

		setPreferredSize(new Dimension(600, 70));
		setBackground(new Color(135, 94, 0));
		setVisible(true);
	}
	
	public void renderMenuNavi() {
		System.out.println("��ȯ�糪?");
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.println(categoryList.get(i).getCategory_name());
			Label label = new Label(categoryList.get(i).getCategory_id(), categoryList.get(i).getCategory_name(), 120, 50, 20, new Color(255, 220, 108));
			add(label);
			label.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("���� Ŭ�������?"+label.id);
					System.out.println("���� Ŭ�������?"+label.name);
					mainFrame.myWin.menuPanel.menuContainer.removeAll();
					mainFrame.myWin.menuPanel.menuContainer.renderMenu(label.id);
				}
			});
		}
	}
	
	public void requestGetMenu() {
		mainFrame.requestController.categorySelectAll();
		mainFrame.requestController.stockSelectAllByAdmin(mainFrame.getAdmin().getAdmin_id());
	}

	// ī�װ� ����Ʈ ��û ���
	public void responseResult(JSONObject jsonObject) {
		System.out.println("ī�װ��г� gui : " + jsonObject.get("categoryList"));
		this.categoryList = (List<Category>) jsonObject.get("categoryList");
		// mainFrame.container.store.storeAccount.setAccount(adminList);
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.println("ī�װ��г� ����Ʈ�� �ִ� �̸��� : " + categoryList.get(i).getCategory_name());
		}
		renderMenuNavi();
		validate();
		repaint();
	}

}
