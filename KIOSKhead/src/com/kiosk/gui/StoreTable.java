package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;

public class StoreTable extends JPanel{
	MainFrame mainFrame;
	JTable table;
	JLabel name;
	JScrollPane scroll;
	JPanel p_container,p_blank2;
	TableModel storeTableModel;
	int admin_id=0;
	
	public int getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}

	List<Admin> adminList = new ArrayList<Admin>();
	
	public List<Admin> getAdminList() {
		return adminList;
	}

	public void setAdminList(List<Admin> adminList) {
		this.adminList = adminList;
	}

	String[] columnName = {"No","가맹점 이름","아이디","비밀번호"};
	
	public StoreTable(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		//==============JLabel==============
		name = new JLabel("               가맹점 계정 리스트");
		name.setPreferredSize(new Dimension(600,60));
		//===========JTable==============
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		scroll = new JScrollPane(table);
		
		//=================blank===============
		p_container = new JPanel();
		p_blank2 = new JPanel();
		p_container.setPreferredSize(new Dimension(600,800));
		p_blank2.setPreferredSize(new Dimension(600,100));
		//================================
		p_container.add(name);
		p_container.add(scroll);
		add(p_blank2);
		add(p_container);
		
		storeTableModel = new TableModel();
		
		renderStoreList();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
				Admin admin = adminList.get(row);
				mainFrame.container.store.storeAccount.filledAccount(admin);
		
	
			}
		});
		
	
	}
	
	//초기 생성 및 가맹점을 신규 생성하거나 수정했을 경우에 호출할 메서드
	public void renderStoreList() {
		System.out.println("renderList 메서드 접근");
		mainFrame.requestController.adminSelectAll();
		setModelData();
		table.setModel(storeTableModel);
		table.repaint();
		table.updateUI();
	}
	public void reRender() {
		setModelData();
		table.setModel(storeTableModel);
		table.repaint();
		table.updateUI();
	}
	public void responseResult(JSONObject jsonObject) {
		System.out.println("여기는 스토어테이블 gui : "+jsonObject.get("adminList"));
		this.adminList = (List<Admin>) jsonObject.get("adminList");
		mainFrame.container.store.storeAccount.devicePanel.setFillChoice(adminList);
		for(int i=0; i< adminList.size(); i++) {
			System.out.println("스토어테이블 리스트에 있는 이름들 : "+adminList.get(i).getName());
		}
		
		setModelData();
		
	}
	
	public void setModelData() {
		int total = this.adminList.size();
		System.out.println("setModelData() 메서드 접근 , 사이즈 : "+total);
		String[][] data = new String[total][5];
		for (int i = 0; i < total; i++) {
			Admin admin = adminList.get(i);
			data[i][0] = Integer.toString(i+1);
			data[i][1] = admin.getName();
			data[i][2] = admin.getId();
			data[i][3] = admin.getPw();
			System.out.println("여기는 뽀문 : "+admin.getName());
		}
		storeTableModel.columnName = columnName;
		storeTableModel.data = data;
		table.setModel(storeTableModel);
		table.repaint();
		table.updateUI();
	}
}
