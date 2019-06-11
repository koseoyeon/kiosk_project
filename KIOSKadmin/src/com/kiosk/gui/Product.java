package com.kiosk.gui;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.gui.common.TableModel;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.State;
import com.kiosk.model.domain.Stock;

public class Product extends JPanel{
	MainFrame mainFrame;
	JPanel p_list;
	JPanel p_edit;
	JPanel p_btn;
	JLabel lb_menu;
	JTextField t_menu;
	JLabel lb_state;
	JLabel lb_info;
	Choice ch_menu;
	JButton btn;
	JTable table;
	JScrollPane scroll;
	TableModel stockTableModel;
	Stock stock;
	
	List<Stock> stockList = new ArrayList<Stock>();
	
	String[] columnName = {"메뉴 이름","재고 상태"};
	
	public Product(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		
		Dimension d_lb = new Dimension(150, 35);
		Dimension d_t = new Dimension(200, 30);
		Dimension d_ch = new Dimension(200, 50);
		
		Font font_lb = new Font(null, Font.BOLD, 20);
		Font font_ch = new Font(null, Font.BOLD, 15);
		
		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(100);
		setLayout(flowLayout);
		
		
		p_list = new JPanel();
		p_list.setPreferredSize(new Dimension(500,700));
		
		p_edit = new JPanel();
		p_edit.setPreferredSize(new Dimension(500,700));
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(400, 500));
		scroll = new JScrollPane(table);
		
		lb_info = new JLabel("※ 상태를 변경하고자 하는 메뉴를 선택해 주세요");
		lb_info.setPreferredSize(new Dimension(500,100));
		lb_info.setForeground(Color.RED);
		lb_info.setFont(new Font(null, Font.BOLD, 14));
		lb_menu = new JLabel("메뉴 이름 : ");
		lb_menu.setPreferredSize(d_lb);
		lb_menu.setFont(font_lb);
		t_menu = new JTextField();
		t_menu.setEditable(false);
		t_menu.setBackground(Color.white);
		t_menu.setPreferredSize(d_t);
		
		lb_state = new JLabel("재고 상태 : ");
		lb_state.setPreferredSize(d_lb);
		lb_state.setFont(font_lb);
		ch_menu = new Choice();
		ch_menu.setPreferredSize(d_ch);
		ch_menu.setFont(font_ch);
		
		p_btn = new JPanel();
		p_btn.setPreferredSize(new Dimension(400,300));
		btn = new JButton("수정");
		
		stockTableModel = new TableModel();
		
		p_list.add(scroll);
		
		p_edit.add(lb_info);
		p_edit.add(lb_menu);
		p_edit.add(t_menu);
		p_edit.add(lb_state);
		p_edit.add(ch_menu);
		p_btn.add(btn);
		p_edit.add(p_btn);
		
		
		add(p_list);
		add(p_edit);
		
		renderMenuList();
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stock = stockList.get(table.getSelectedRow());
				fillStock(stock);
			}
		});
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				stockEdit();
				
			}
		});
		
		//setBackground(Color.blue);
		setPreferredSize(new Dimension(1200,800));
		setVisible(false);
	}
	
	public void setChoice() {
		ch_menu.removeAll();
		ch_menu.add("일시 품절");
		ch_menu.add("판매 가능");
	}
	
	public void renderMenuList() {
		System.out.println("renderList 메서드 접근");
		setModelData();
		table.setModel(stockTableModel);
		table.repaint();
		table.updateUI();
	}
	
	public void responseResult1(JSONObject jsonObject) {
		System.out.println("stockList : "+jsonObject.get("stockList"));
		this.stockList = (List<Stock>) jsonObject.get("stockList");
		System.out.println("stockList : "+stockList);
		//mainFrame.container.store.storeAccount.setAccount(adminList);
		
		setModelData();
	}
	
	public void responseResult2(JSONObject jsonObject) {
		if ((boolean)jsonObject.get("result")) {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 변경이 되었습니다.");
			stockSelectAll();
			renderMenuList();
			changeStock();

		}else {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 변경에 실패하였습니다.");
		}
	}
	
	public void setModelData() {
		int total = this.stockList.size();
		System.out.println("setModelData() 메서드 접근 , 사이즈 : "+total);
		String[][] data = new String[total][2];
		for (int i = 0; i < total; i++) {
			Stock stock = stockList.get(i);
			data[i][0] = stock.getMenu().getMenu_name();
			data[i][1] = stock.getState().getState_type();
			System.out.println("여기는 뽀문 : "+stock.getMenu().getMenu_name());
		}
		stockTableModel.columnName = columnName;
		stockTableModel.data = data;
		table.setModel(stockTableModel);
		table.repaint();
		table.updateUI();
	}
	
	public void fillStock(Stock stock) {
		ch_menu.removeAll();
		t_menu.setText(stock.getMenu().getMenu_name());
		ch_menu.add(stock.getState().getState_type());
		if(stock.getState().getState_type().equals("판매가능")) {
			ch_menu.add("일시품절");
		}else if(stock.getState().getState_type().equals("일시품절")) {
			ch_menu.add("판매가능");
		}
		p_edit.validate();
		p_edit.repaint();
	}
	
	public void changeStock() {
		if(ch_menu.getItem(0).equals("판매가능")) {
			ch_menu.removeAll();
			ch_menu.add("일시품절");
			ch_menu.add("판매가능");
		}else if(ch_menu.getItem(0).equals("일시품절")) {
			ch_menu.removeAll();
			ch_menu.add("판매가능");
			ch_menu.add("일시품절");
		}
		p_edit.validate();
		p_edit.repaint();
	}
	
	public void stockSelectAll() {		
		mainFrame.requestController.stockSelectAllByAdmin(mainFrame.getAdmin().getAdmin_id());
	}
	
	public void stockEdit() {
		if(t_menu.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "메뉴를 선택해주세요");
			return;
		}else if(ch_menu.getSelectedIndex() == 0) {
			JOptionPane.showMessageDialog(this, "처음 값과 일치합니다.");
			return;
		}else {
			int result = JOptionPane.showConfirmDialog(this, "변경한 값으로 수정하시겠습니까?");
			if(result==JOptionPane.OK_OPTION) {
				Stock stock = new Stock();
				Menu menu = new Menu();
				State state = new State();
				for(int i=0; i<stockList.size(); i++) {
					if(stockList.get(i).getMenu().getMenu_name().equals(t_menu.getText())) {
						menu = stockList.get(i).getMenu();
						state = stockList.get(i).getState();
					}
				}
				int state_id = 0;
				if(ch_menu.getSelectedItem().equals("판매가능")){
					state_id = 1;
				}else if(ch_menu.getSelectedItem().equals("일시품절")) {
					state_id = 2;
				}
				state.setState_id(state_id);
				stock.setMenu(menu);
				stock.setState(state);
				stock.setAdmin(mainFrame.getAdmin());
				mainFrame.requestController.stockEdit(stock);
				p_edit.validate();
				p_edit.repaint();
			}
		}
	}
}
