package com.kiosk.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Menu;

public class CategoryPanel extends JDialog {
	MainFrame mainFrame;
	JTable table;
	JScrollPane scroll;
	JLabel lb_name;
	JTextField t_name;
	TableModel menuTableModel;
	JButton bt_ca_edit, bt_ca_del;
	JPanel p;
	List<Category> categoryList = new ArrayList<Category>();
	Category category = new Category();
	int category_id = 0;

	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}

	public int getCategory_id() {
		return category_id;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	String[] columnName = { "카테고리 고유번호", "카테고리명" };

	public CategoryPanel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());

		p = new JPanel();
		p.setPreferredSize(new Dimension(360, 510));

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(340, 350));
		scroll = new JScrollPane(table);

		lb_name = new JLabel("카테고리 명 :");
		lb_name.setPreferredSize(new Dimension(90, 35));
		t_name = new JTextField();
		t_name.setPreferredSize(new Dimension(250, 35));

		this.mainFrame = mainFrame;
		bt_ca_edit = new JButton("수정");
		bt_ca_del = new JButton("삭제");

		p.add(scroll);
		p.add(lb_name);
		p.add(t_name);
		p.add(bt_ca_edit);
		p.add(bt_ca_del);

		add(p);

		menuTableModel = new TableModel();

		renderCategoryList();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				category = categoryList.get(table.getSelectedRow());
				setCategory_id(category.getCategory_id());
				t_name.setText(category.getCategory_name());
			}
		});

		bt_ca_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkEdit();
			}
		});

		bt_ca_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkDel();
			}
		});
	

		setTitle("카테고리 목록");
		this.setSize(380, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);
	}

	public void renderCategoryList() {
		System.out.println("renderList 메서드 접근");
		setModelData();
		table.setModel(menuTableModel);
		table.repaint();
		table.updateUI();
	}

	public void responseResult(JSONObject jsonObject) {
		System.out.println("카테고리패널 gui : " + jsonObject.get("categoryList"));
		this.categoryList = (List<Category>) jsonObject.get("categoryList");

		setModelData();
	}

	public void setModelData() {
		int total = this.categoryList.size();
		System.out.println("setModelData() 메서드 접근 , 사이즈 : " + total);
		String[][] data = new String[total][2];
		for (int i = 0; i < total; i++) {
			Category category = categoryList.get(i);
			data[i][0] = Integer.toString(category.getCategory_id());
			data[i][1] = category.getCategory_name();
			System.out.println("여기는 뽀문 : " + category.getCategory_id());
		}
		menuTableModel.columnName = columnName;
		menuTableModel.data = data;
		table.setModel(menuTableModel);
		table.repaint();
		table.updateUI();
	}

	//카테고리 수정 유효성 체크
	public void checkEdit() {
		boolean check = false;
		if(t_name.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "카테고리명을 입력해주세요.");
			return;
		}
		if (category.getCategory_name().equals(t_name.getText())) {
			JOptionPane.showMessageDialog(this, "기존의 값과 일치합니다.");
			return;
		}
		for (int i = 0; i < categoryList.size(); i++) {
			if (t_name.getText().equals(categoryList.get(i).getCategory_name())) {
				JOptionPane.showMessageDialog(this, "중복된 이름은 사용할 수 없습니다.");
				return;
			} else {
				check = true;
			}
		}
		if (check) {
			int result = JOptionPane.showConfirmDialog(this, "수정하시겠습니까?");
			if (result == JOptionPane.OK_OPTION) {
				categoryEdit();
			}
		}
	}

	
	public void categoryEdit() {

		Category category = new Category();
		category.setCategory_id(getCategory_id());
		category.setCategory_name(t_name.getText());

		mainFrame.requestController.categoryEdit(category);
	}

	public void responseCategoryEdit(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(this, "수정이 완료되었습니다.");
			mainFrame.requestController.categorySelectAll();
			renderCategoryList();
			mainFrame.container.menuContainer.menuTable.renderMenuList();
		} else {
			JOptionPane.showMessageDialog(this, "수정에 실패하였습니다.");
		}
		t_name.setText("");
	}
	
	//카테고리 삭제 유효성 체크
	public void checkDel() {
		if(t_name.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "삭제할 카테고리를 선택해주세요");
			return;
		}
		int result = JOptionPane.showConfirmDialog(this, "선택한 카테고리를 삭제하시겠습니까?");
		if(result == JOptionPane.OK_OPTION) {
			categoryDelete();
		}
	}
	
	public void categoryDelete() {
		mainFrame.requestController.categoryDelete(getCategory_id());
	}
	
	public void responseCategoryDelete(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.");
			mainFrame.requestController.categorySelectAll();
			setModelData();
			mainFrame.container.menuContainer.menuTable.renderMenuList();
		} else {
			JOptionPane.showMessageDialog(this, "삭제에 실패하였습니다.");
		}
		t_name.setText("");
	}
}
