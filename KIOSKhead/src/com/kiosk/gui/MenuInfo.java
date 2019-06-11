package com.kiosk.gui;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.FileManager;
import com.kiosk.commons.IntegerDocument;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Menu;

public class MenuInfo extends JPanel {
	MainFrame mainFrame;
	JLabel lb_ca_title, lb_me_title, lb_ca_name, lb_me_name, lb_me_ch, lb_me_price, lb_filename;
	JPanel p_category, p_menu, p_ca_title, p_ca_name, p_me_title, p_me_ch, p_me_name, p_me_price, p_file, p_ca_bt,
			p_me_bt, p_img;
	JButton bt_ca_regist, bt_ca_list, bt_me_regist, bt_me_edit, bt_me_del, bt_file, bt_init;
	Choice choice;
	public JTextField t_ca_name, t_me_name, t_me_price, t_me_file;
	JFileChooser fileChooser;
	public CategoryPanel categoryPanel;
	FileManager fileManager = new FileManager();
	File registFile;
	Canvas c_img;
	Menu menu;
	Image img;

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Menu getMenu() {
		return menu;
	}

	public MenuInfo(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		categorySelectAll();
		// ========dimension========
		Dimension d_category = new Dimension(400, 150);
		Dimension d_title = new Dimension(400, 50);
		Dimension d_menu = new Dimension(400, 500);
		Dimension d_lb = new Dimension(100, 35);
		Dimension d_p = new Dimension(320, 40);
		Dimension d_p_bt = new Dimension(320, 100);
		Dimension d_t = new Dimension(200, 30);
		Dimension d_ch = new Dimension(200, 50);

		// ========font========
		Font font_lb = new Font(null, Font.BOLD, 20);
		Font font_ch = new Font(null, Font.BOLD, 15);

		// =======category===========

		p_category = new JPanel();
		p_category.setPreferredSize(d_category);

		p_ca_title = new JPanel();
		p_ca_title.setPreferredSize(d_title);
		lb_ca_title = new JLabel("카테고리");
		lb_ca_title.setFont(font_lb);

		p_ca_name = new JPanel();
		p_ca_name.setPreferredSize(d_p);

		lb_ca_name = new JLabel("카테고리명 : ");
		lb_ca_name.setPreferredSize(d_lb);

		t_ca_name = new JTextField();
		t_ca_name.setPreferredSize(d_t);

		p_ca_bt = new JPanel();
		p_ca_bt.setPreferredSize(d_p_bt);
		bt_ca_regist = new JButton("등록");
		bt_ca_list = new JButton("목록");
		categoryPanel = new CategoryPanel(mainFrame);

		// =======menu===========

		p_menu = new JPanel();
		p_menu.setPreferredSize(d_menu);

		p_me_title = new JPanel();
		p_me_title.setPreferredSize(d_title);
		lb_me_title = new JLabel("메뉴");
		lb_me_title.setFont(font_lb);

		p_me_ch = new JPanel();
		lb_me_ch = new JLabel("카테고리 선택 : ");
		lb_me_ch.setPreferredSize(d_lb);
		choice = new Choice();
		choice.setPreferredSize(d_ch);
		choice.setFont(font_ch);

		p_me_name = new JPanel();
		p_me_name.setPreferredSize(d_p);
		lb_me_name = new JLabel("메뉴명 : ");
		lb_me_name.setPreferredSize(d_lb);
		t_me_name = new JTextField();
		t_me_name.setPreferredSize(d_t);

		p_me_price = new JPanel();
		p_me_price.setPreferredSize(d_p);
		lb_me_price = new JLabel("가격 : ");
		lb_me_price.setPreferredSize(d_lb);
		t_me_price = new JTextField();
		t_me_price.setPreferredSize(d_t);
		IntegerDocument integerDocument = new IntegerDocument();
		t_me_price.setDocument(integerDocument);

		p_file = new JPanel();
		p_file.setPreferredSize(new Dimension(350, 40));
		lb_filename = new JLabel("이미지 : ");
		lb_filename.setPreferredSize(d_lb);
		t_me_file = new JTextField();
		t_me_file.setPreferredSize(new Dimension(140, 30));
		t_me_file.setText("선택된 파일이 없습니다.");
		t_me_file.setEditable(false);
		bt_file = new JButton("선택");

		p_me_bt = new JPanel();
		p_me_bt.setPreferredSize(d_p_bt);
		bt_me_regist = new JButton("등록");
		bt_me_edit = new JButton("수정");
		bt_me_del = new JButton("삭제");
		bt_init = new JButton("초기화");

		p_img = new JPanel();
		p_img.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		p_img.setLayout(new BorderLayout());
		c_img = new Canvas() {
			@Override
			public void paint(Graphics g) {
				// 그림 그리기 예정

				g.drawImage(img, 0, 0, 145, 145, null);

			}
		};
		p_img.setPreferredSize(new Dimension(145, 145));
		c_img.setPreferredSize(new Dimension(145, 145));

		// =================붙이기===================

		// 카테고리
		p_ca_title.add(lb_ca_title);
		p_ca_name.add(lb_ca_name);
		p_ca_name.add(t_ca_name);
		p_ca_bt.add(bt_ca_regist);
		p_ca_bt.add(bt_ca_list);
		p_category.add(p_ca_title);
		p_category.add(p_ca_name);
		p_category.add(p_ca_bt);

		// 메뉴
		p_me_title.add(lb_me_title);
		p_me_ch.add(lb_me_ch);
		p_me_ch.add(choice);
		p_me_name.add(lb_me_name);
		p_me_name.add(t_me_name);
		p_me_price.add(lb_me_price);
		p_me_price.add(t_me_price);
		p_file.add(lb_filename);
		p_file.add(t_me_file);
		p_file.add(bt_file);
		p_me_bt.add(bt_me_regist);
		p_me_bt.add(bt_me_edit);
		p_me_bt.add(bt_me_del);
		p_me_bt.add(bt_init);

		p_img.add(c_img);

		p_menu.add(p_me_title);
		p_menu.add(p_me_ch);
		p_menu.add(p_me_name);
		p_menu.add(p_me_price);
		p_menu.add(p_file);
		p_menu.add(p_img);
		p_menu.add(p_me_bt);

		FlowLayout flow = new FlowLayout();
		flow.setVgap(40);
		flow.setHgap(50);
		setLayout(flow);

		add(p_category);
		add(p_menu);

		setPreferredSize(new Dimension(350, 800));
		setVisible(true);

		bt_file.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println("파일 선택 버튼 클릭");
				fileChooser = new JFileChooser("C:\\", FileSystemView.getFileSystemView());
				fileChooser
						.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "png", "tif", "gif", "bmp"));
				int returnVal = fileChooser.showOpenDialog(mainFrame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					registFile = fileChooser.getSelectedFile().getAbsoluteFile();
					String fileName = fileChooser.getSelectedFile().getName();
					System.out.println("유저가 선택한 fileName : " + fileName);
					String extension = fileName.substring(fileName.lastIndexOf("."));
					if (extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png")
							|| extension.equalsIgnoreCase(".bmp") || extension.equalsIgnoreCase(".tif")
							|| extension.equalsIgnoreCase(".gif")) {

						String filePath = fileChooser.getSelectedFile().getAbsolutePath();
						ImageIcon icon = new ImageIcon(filePath);
						img = icon.getImage();
						c_img.repaint();
						// t_me_file.setText(filePath);
						// 이미지파일 전송시작
						fileName = System.currentTimeMillis() + "." + fileManager.getExt(filePath);

						t_me_file.setText(fileName);
						System.out.println(registFile);

					} else {
						JOptionPane.showMessageDialog(mainFrame, "이미지 파일만 선택 가능합니다.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					t_me_file.setText("선택된 파일이 없습니다.");
				}
			}
		});

		// 카테고리 등록 버튼
		bt_ca_regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (categoryPanel.categoryList.size() >= 4) {
					JOptionPane.showMessageDialog(mainFrame, "카테고리는 4개까지 등록 가능합니다.");
					return;
				}
				categoryRegist();
			}
		});

		// 카테고리 목록 보기 버튼
		bt_ca_list.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				categoryPanel.setVisible(true);
				categoryPanel.t_name.setText("");
			}
		});

		// 메뉴 등록 버튼
		bt_me_regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuRegist(t_me_file.getText());
			}
		});

		bt_me_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkMenuEdit();
			}
		});

		bt_me_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				checkMenuDel();
			}
		});
		
		bt_init.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				blankComponet();
			}
		});
	}

	public void setChoice(List<Category> categoryList) {
		choice.removeAll();
		choice.add("카테고리 선택");
		System.out.println("초이스문 : " + categoryList.size());
		for (int i = 0; i < categoryList.size(); i++) {
			Category category = (Category) categoryList.get(i);
			choice.add(category.getCategory_name());
		}
	}

	public void blankComponet() {
		choice.setEnabled(true);
		t_me_file.setText("선택된 파일이 없습니다.");
		t_me_name.setText("");
		t_me_price.setText("");

		img = null;
		c_img.repaint();
		setChoice(categoryPanel.categoryList);
	}

	public void setChoice2(String firstValue) {
		choice.removeAll();
		choice.add(firstValue);
		System.out.println("초이스문 : " + categoryPanel.categoryList.size());
		for (int i = 0; i < categoryPanel.categoryList.size(); i++) {
			Category category = (Category) categoryPanel.categoryList.get(i);
			if (!category.getCategory_name().equals(firstValue)) {
				choice.add(category.getCategory_name());
			}
		}
	}

	// 이미지 저장
	public void menuRegist(String imagePath) {
		byte[] rawBytes = null;
		FileInputStream fis = null;

		if (choice.getSelectedItem().equals("카테고리 선택")) {
			JOptionPane.showMessageDialog(mainFrame, "카테고리를 선택해주세요.");
			return;
		} else if (t_me_name.getText().equals("")) {
			JOptionPane.showMessageDialog(mainFrame, "메뉴명을 입력해주세요.");
			return;
		} else if (t_me_price.getText().equals("")) {
			JOptionPane.showMessageDialog(mainFrame, "가격을 입력해주세요.");
			return;
		} else if (imagePath.equals("선택된 파일이 없습니다.")) {
			JOptionPane.showMessageDialog(mainFrame, "이미지를 선택해주세요.");
			return;
		}

		List<Menu> menuList = new ArrayList<Menu>();
		menuList = mainFrame.container.menuContainer.menuTable.menuList;
		for (int i = 0; i < menuList.size(); i++) {
			if (menuList.get(i).getMenu_name().equals(t_me_name.getText())) {
				JOptionPane.showMessageDialog(mainFrame, "중복된 메뉴명은  사용할 수 없습니다.");
				return;
			}
		}
		Menu menu = new Menu();
		menu.setFile_name(t_me_file.getText());
		List<Category> categoryList = mainFrame.container.menuContainer.menuInfo.categoryPanel.categoryList;
		for (int i = 0; i < categoryList.size(); i++) {
			Category category = categoryList.get(i);
			if (category.getCategory_name().equals(choice.getSelectedItem())) {
				menu.setCategory(category);
			}
		}
		menu.setPrice(Integer.parseInt(t_me_price.getText()));
		menu.setMenu_name(t_me_name.getText());
		menu.setFile(registFile);
		mainFrame.requestController.menuRegist(menu);
	}

	// 카테고리 등록
	public void categoryRegist() {
		if (t_ca_name.getText().equals("")) {
			JOptionPane.showMessageDialog(mainFrame, "카테고리명을 입력해주세요.");
			return;
		}
		for (int i = 0; i < categoryPanel.categoryList.size(); i++) {
			if (t_ca_name.getText().equals(categoryPanel.categoryList.get(i).getCategory_name())) {
				JOptionPane.showMessageDialog(mainFrame, "중복된 이름은 사용할 수 없습니다.");
				return;
			}
		}
		Category category = new Category();
		category.setCategory_name(t_ca_name.getText());
		mainFrame.requestController.categoryRegist(category);
		t_ca_name.setText("");
		categorySelectAll();
	}

	public void categorySelectAll() {
		mainFrame.requestController.categorySelectAll();
	}

	public void responseMenuRegist(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 등록 성공");
			mainFrame.container.menuContainer.menuTable.renderMenuList();
			blankComponet();

		} else {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 등록 실패");
		}
	}

	// 메뉴 수정 유효성 체크
	public void checkMenuEdit() {
		boolean flag = false;
		System.out.println("유효성 체크" + getMenu());
		List<Menu> menuList = new ArrayList<Menu>();
		menuList = mainFrame.container.menuContainer.menuTable.menuList;
		if (getMenu() == null) {
			JOptionPane.showMessageDialog(this, "변경할 메뉴를 선택해주세요");
			blankComponet();
			return;
		}
		if (choice.getSelectedItem().equals("카테고리 선택")) {
			JOptionPane.showMessageDialog(mainFrame, "카테고리를 선택해주세요.");
			blankComponet();
			return;
		} else if (t_me_name.getText().equals("")) {
			JOptionPane.showMessageDialog(mainFrame, "메뉴명을 입력해주세요.");
			blankComponet();
			return;
		} else if (t_me_price.getText().equals("")) {
			JOptionPane.showMessageDialog(mainFrame, "가격을 입력해주세요.");
			blankComponet();
			return;
		}

		if ((choice.getSelectedItem().equals(getMenu().getCategory().getCategory_name())
				&& t_me_name.getText().equals(getMenu().getMenu_name()))
				&& ((t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
						&& t_me_file.getText().equals(getMenu().getFile_name())))) {
			blankComponet();
			JOptionPane.showMessageDialog(this, "기존의 메뉴와 정보가 동일합니다.");
			return;
		}

		for (int i = 0; i < menuList.size(); i++) {
			if (t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& t_me_file.getText().equals(getMenu().getFile_name())
					&& t_me_name.getText().equals(menuList.get(i).getMenu_name())) {
				blankComponet();
				JOptionPane.showMessageDialog(this, "중복된 이름은 사용할 수 없습니다.");
				return;
			}
		}

		for (int i = 0; i < menuList.size(); i++) {
			if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& (t_me_name.getText().equals(getMenu().getMenu_name())
							&& !t_me_name.getText().equals(menuList.get(i).getMenu_name()))
					&& !t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		for (int i = 0; i < menuList.size(); i++) {
			if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& !t_me_name.getText().equals(menuList.get(i).getMenu_name())
					&& t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		if (t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
				&& t_me_file.getText().equals(getMenu().getFile_name())
				&& t_me_name.getText().equals(getMenu().getMenu_name())) {
			blankComponet();
			JOptionPane.showMessageDialog(this, "중복된 이름은 사용할 수 없습니다.");
			return;
		}
		for (int i = 0; i < menuList.size(); i++) {
			if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& (!t_me_name.getText().equals(getMenu().getMenu_name())
							&& t_me_name.getText().equals(menuList.get(i).getMenu_name()))
					&& t_me_file.getText().equals(getMenu().getFile_name())) {
				blankComponet();
				JOptionPane.showMessageDialog(this, "중복된 이름은 사용할 수 없습니다.");
				return;
			}
		}
		if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
				&& t_me_name.getText().equals(getMenu().getMenu_name())
				&& !t_me_file.getText().equals(getMenu().getFile_name())) {
			flag = true;
		}

		// 가격이 같고 이름이 같지만 파일이름이 다르면 가능
		for (int i = 0; i < menuList.size(); i++) {
			if (t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& t_me_name.getText().equals(menuList.get(i).getMenu_name())
					&& !t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		// 파일이름이 같고 이름이 같지만 가격이 다르면 가능
		for (int i = 0; i < menuList.size(); i++) {
			if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& t_me_name.getText().equals(menuList.get(i).getMenu_name())
					&& t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
				&& t_me_name.getText().equals(getMenu().getMenu_name())
				&& t_me_file.getText().equals(getMenu().getFile_name())) {
			flag = true;
		}

		// 가격과 파일이름이 같지만 이름이 다르면 가능
		for (int i = 0; i < menuList.size(); i++) {
			if (t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& !t_me_name.getText().equals(menuList.get(i).getMenu_name())
					&& t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		for (int i = 0; i < menuList.size(); i++) {
			if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& t_me_name.getText().equals(menuList.get(i).getMenu_name())
					&& t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		for (int i = 0; i < menuList.size(); i++) {
			if (t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& t_me_name.getText().equals(menuList.get(i).getMenu_name())
					&& !t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		if (t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
				&& t_me_name.getText().equals(getMenu().getMenu_name())
				&& !t_me_file.getText().equals(getMenu().getFile_name())) {
			flag = true;
		}

		for (int i = 0; i < menuList.size(); i++) {
			if (!t_me_price.getText().equals(Integer.toString(getMenu().getPrice()))
					&& !t_me_name.getText().equals(menuList.get(i).getMenu_name())
					&& !t_me_file.getText().equals(getMenu().getFile_name())) {
				flag = true;
			}
		}

		if (flag) {
			MenuEdit();
			flag = false;
		}
	}

	// 메뉴리스트에서 값 선택시 메뉴 폼채우기
	public void filledAccount(Menu menu) {
		choice.setEnabled(false);
		setChoice2(menu.getCategory().getCategory_name());
		t_me_name.setText(menu.getMenu_name());
		t_me_price.setText(Integer.toString(menu.getPrice()));
		t_me_file.setText(menu.getFile_name());
	}

	public void responseMenuEdit(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 변경 성공");
			mainFrame.container.menuContainer.menuTable.renderMenuList();
		} else {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 변경 실패");
		}
		// 초기화
		blankComponet();
	}

	public void MenuEdit() {
		System.out.println("MenuEdit() 접근");
		Menu menu = new Menu();
		menu.setCategory(getMenu().getCategory());
		menu.setMenu_id(getMenu().getMenu_id());
		menu.setFile_name(t_me_file.getText());
		menu.setPrice(Integer.parseInt(t_me_price.getText()));
		menu.setMenu_name(t_me_name.getText());
		menu.setFile(registFile);
		mainFrame.requestController.menuEdit(menu);

	}

	public void checkMenuDel() {
		if (getMenu() == null) {
			JOptionPane.showMessageDialog(this, "삭제할 메뉴를 선택해주세요");
			return;
		}
		int result = JOptionPane.showConfirmDialog(this, "선택한 메뉴를 삭제하시겠습니까?");
		if (result == JOptionPane.OK_OPTION) {
			menuDelete();
		}
	}

	public void responseMenuDelete(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 삭제가  완료되었습니다.");
			mainFrame.container.menuContainer.menuTable.renderMenuList();
		} else {
			JOptionPane.showMessageDialog(mainFrame, "메뉴 삭제에 실패했습니다.");
		}
		// 초기화
		blankComponet();
	}

	public void menuDelete() {
		mainFrame.requestController.menuDelete(getMenu().getMenu_id());
	}

	public void imageUpload() {

	}
}