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
	public List<Label> labelList = new ArrayList<Label>();
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
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.println(categoryList.get(i).getCategory_name());
			Label label = new Label(categoryList.get(i).getCategory_id(), categoryList.get(i).getCategory_name(), 120, 50, 20, new Color(255, 220, 108));
			labelList.add(label);
			this.add(label);
			labelList.get(i).addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					for(int k=0;k<labelList.size(); k++) {
						labelList.get(k).setBackground(new Color(255, 220, 108));
					}
					label.setBackground(new Color(250, 224, 212));
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

	// 카테고리 리스트 요청 결과
	public void responseResult(JSONObject jsonObject) {
		System.out.println("카테고리패널 gui : " + jsonObject.get("categoryList"));
		this.categoryList = (List<Category>) jsonObject.get("categoryList");
		// mainFrame.container.store.storeAccount.setAccount(adminList);
		for (int i = 0; i < categoryList.size(); i++) {
			System.out.println("카테고리패널 리스트에 있는 이름들 : " + categoryList.get(i).getCategory_name());
		}
		renderMenuNavi();
		validate();
		repaint();
	}

}
