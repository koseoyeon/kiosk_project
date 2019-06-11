package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Menu;

public class MenuTable extends JPanel {
	MainFrame mainFrame;
	JTable table;
	JLabel name;
	JScrollPane scroll;
	JPanel p_container, p_blank2;
	TableModel menuTableModel;
	List<Menu> menuList = new ArrayList<Menu>();

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	String[] columnName = { "상품 고유 번호", "카테고리", "상품명", "가격" };

	public MenuTable(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		// ==============JLabel==============
		name = new JLabel("               메뉴 리스트");
		name.setPreferredSize(new Dimension(600, 60));
		// ===========JTable==============
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		scroll = new JScrollPane(table);

		// =================blank===============
		p_container = new JPanel();
		p_blank2 = new JPanel();
		p_container.setPreferredSize(new Dimension(600, 800));
		p_blank2.setPreferredSize(new Dimension(600, 100));
		// ================================
		p_container.add(name);
		p_container.add(scroll);
		add(p_blank2);
		add(p_container);

		menuTableModel = new TableModel();

		renderMenuList();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainFrame.container.menuContainer.menuInfo.setMenu(null);
				Menu menu = menuList.get(table.getSelectedRow());
				mainFrame.container.menuContainer.menuInfo.setMenu(menu);
				mainFrame.container.menuContainer.menuInfo.filledAccount(menu);
				mainFrame.container.menuContainer.menuInfo.repaint();
				String path = System.getProperty("user.dir");
				ImageIcon icon = new ImageIcon(path + "/menu_res/" + menu.getFile_name());
				File file = new File(path + "/menu_res/" + menu.getFile_name());
				mainFrame.container.menuContainer.menuInfo.registFile = file;
				mainFrame.container.menuContainer.menuInfo.img = icon.getImage();
				mainFrame.container.menuContainer.menuInfo.c_img.repaint();
			}
		});

	}

	// 초기 생성 및 가맹점을 신규 생성하거나 수정했을 경우에 호출할 메서드
	public void renderMenuList() {
		System.out.println("renderList 메서드 접근");
		mainFrame.requestController.menuSelectAll();
		setModelData();
		table.setModel(menuTableModel);
		table.repaint();
		table.updateUI();
	}

	public void responseMenuSelectAll(JSONObject jsonObject) {

		this.menuList = (List<Menu>) jsonObject.get("menuList");

		setModelData();
	}

	public void setModelData() {
		int total = this.menuList.size();
		System.out.println("setModelData() 메서드 접근 , 사이즈 : " + total);
		String[][] data = new String[total][4];
		for (int i = 0; i < total; i++) {
			Menu menu = menuList.get(i);
			data[i][0] = Integer.toString(menu.getMenu_id());
			data[i][1] = menu.getCategory().getCategory_name();
			data[i][2] = menu.getMenu_name();
			data[i][3] = Integer.toString(menu.getPrice());
			System.out.println("여기는 뽀문 : " + menu.getMenu_name());
		}
		menuTableModel.columnName = columnName;
		menuTableModel.data = data;
		table.setModel(menuTableModel);
		table.repaint();
		table.updateUI();
	}
}