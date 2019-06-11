package com.kiosk.gui;

import java.awt.Choice;
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
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.Menu;

public class DevicePanel extends JDialog {
	MainFrame mainFrame;
	JTable table;
	JScrollPane scroll;
	TableModel deviceTableModel;
	JButton bt_ca_edit, bt_ca_del, bt_ca_return;
	JPanel p;
	JPanel p_txt;
	JLabel lb_device;
	JTextField t_device;
	JLabel lb_admin;
	JTextField t_admin, t_admin2;
	JPanel p_btn;
	JTextField t_pk;
	Choice choice;
	List<Device> deviceList = new ArrayList<Device>();

	public void setDeviceList(List<Device> deviceList) {
		this.deviceList = deviceList;
	}

	public List<Device> getDeviceList() {
		return deviceList;
	}

	String[] columnName = { "NO", "키오스크 장치 명", "대여 상태", "소속 가맹점" };

	public DevicePanel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout());
		this.mainFrame = mainFrame;
		deviceSelectAll();

		Dimension d_t = new Dimension(200, 30);
		Dimension d_lb = new Dimension(100, 35);

		p = new JPanel();
		p.setPreferredSize(new Dimension(440, 320));

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(420, 280));
		scroll = new JScrollPane(table);

		p_txt = new JPanel();
		p_txt.setPreferredSize(new Dimension(380, 80));

		t_pk = new JTextField();

		lb_device = new JLabel("장치명 : ");
		lb_device.setPreferredSize(d_lb);
		t_device = new JTextField();
		t_device.setPreferredSize(d_t);
		lb_admin = new JLabel("가맹점 : ");
		lb_admin.setPreferredSize(d_lb);
		t_admin = new JTextField();
		t_admin2 = new JTextField();
		t_admin.setPreferredSize(d_t);

		p_btn = new JPanel();
		p_btn.setPreferredSize(new Dimension(460, 50));
		bt_ca_edit = new JButton("수정");
		bt_ca_del = new JButton("삭제");
		bt_ca_return = new JButton("반납처리");

		choice = new Choice();
		choice.setPreferredSize(new Dimension(200, 35));
		choice.setFont(new Font(null, Font.BOLD, 15));

		p.add(scroll);

		p_txt.add(lb_device);
		p_txt.add(t_device);
		p_txt.add(lb_admin);
		p_txt.add(choice);

		p_btn.add(bt_ca_edit);
		p_btn.add(bt_ca_del);
		p_btn.add(bt_ca_return);

		add(p);
		add(p_txt);
		add(p_btn);

		deviceTableModel = new TableModel();

		renderCategoryList();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				Device device = deviceList.get(row);
				filledAccount(device);

			}
		});

		setTitle("키오스크 장치 목록");
		this.setSize(480, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);

		bt_ca_edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(t_device.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.devicePanel, "변경할 장치를 선택해주세요");
					return;
				}
				if (choice.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.devicePanel, "가맹점명을 선택해주세요");
					return;

				} else if (t_admin2.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.devicePanel, "변경할 가맹점을 선택해주새요");
					return;
				} else {
					Device device = new Device();
					int admin_id = 0;
					device.setDevice_id(Integer.parseInt(t_pk.getText()));
					device.setDevice_name(t_device.getText());
					for (int i = 0; i < mainFrame.container.store.storeTable.getAdminList().size(); i++) {
						// System.out.println(mainFrame.container.store.storeTable.getAdminList().get(i).getName());
						if (choice.getSelectedItem()
								.equals(mainFrame.container.store.storeTable.getAdminList().get(i).getName())) {
							admin_id = mainFrame.container.store.storeTable.getAdminList().get(i).getAdmin_id();
						}
					}
					Admin admin = new Admin();

					admin.setAdmin_id(admin_id);
					device.setAdmin(admin);
					deviceEdit(device);
				}
			}
		});

		bt_ca_del.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t_device.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.devicePanel, "삭제할 장치를 선택해주세요");
					return;
				}
				deviceDelete(Integer.parseInt(t_pk.getText()));
			}
		});
		
		bt_ca_return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(t_device.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.devicePanel, "반납할 장치를 선택해주세요");
					return;
				}
				Device device = new Device();
				device.setDevice_id(Integer.parseInt(t_pk.getText()));
				device.setDevice_name(t_device.getText());
				Admin admin = new Admin();
				admin.setAdmin_id(mainFrame.getAdmin().getAdmin_id());
				device.setAdmin(admin);
				
				deviceEdit(device);
			}
		});
	}

	public void setFillChoice(List adminList) {
		choice.removeAll();
		choice.add("가맹점 이름 선택");
		for (int i = 0; i < adminList.size(); i++) {
			Admin admin = (Admin) adminList.get(i);
			choice.add(admin.getName());
		}
	}

	public void renderCategoryList() {
		System.out.println("renderList 메서드 접근");
		setModelData();
		table.setModel(deviceTableModel);
		table.repaint();
		table.updateUI();
	}

	public void responseResult(JSONObject jsonObject) {
		System.out.println("카테고리패널 gui : " + jsonObject.get("deviceList"));
		this.deviceList = (List<Device>) jsonObject.get("deviceList");
		// mainFrame.container.store.storeAccount.setAccount(adminList);
		setModelData();
	}

	public void setModelData() {
		int total = this.deviceList.size();

		String[][] data = new String[total][4];
		for (int i = 0; i < total; i++) {
			Device device = deviceList.get(i);
			data[i][0] = Integer.toString(i + 1);
			data[i][1] = device.getDevice_name();
			String state = "";
			System.out.println(device.getDevice_name());
			System.out.println(device.getAdmin());
			if (device.getAdmin().getAuth().getAuth_id()==1) {
				state = "반납";
			} else {
				state = "대여 중";
			}
			data[i][2] = state;
			data[i][3] = device.getAdmin().getName();
		}
		deviceTableModel.columnName = columnName;
		deviceTableModel.data = data;
		table.setModel(deviceTableModel);
		table.repaint();
		table.updateUI();
	}

	public void deviceSelectAll() {
		mainFrame.requestController.deviceSelectAll();
	}

	public void filledAccount(Device device) {
		t_admin2.setText(Integer.toString(device.getAdmin().getAdmin_id()));
		t_pk.setText(Integer.toString(device.getDevice_id()));
		t_device.setText(device.getDevice_name());
		t_admin.setText(device.getAdmin().getName());
		System.out.println(t_pk.getText());
	}

	public void deviceEdit(Device device) {
		System.out.println("디바이스 이름" + device.getDevice_name());
		System.out.println("admin_id" + device.getAdmin().getAdmin_id());
		System.out.println("디바이스 pk" + device.getDevice_id());
		mainFrame.requestController.deviceEdit(device);

	}

	public void responseDeviceEdit(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(this, "디바이스 수정완료");
			deviceSelectAll();

			t_admin2.setText("");
			t_admin.setText("");
			t_device.setText("");
			setFillChoice(mainFrame.container.store.storeTable.getAdminList());
		} else {
			JOptionPane.showMessageDialog(this, "디바이스 수정실패");
		}
	}

	public void deviceDelete(int device_id) {
		mainFrame.requestController.deviceDelete(device_id);
	}
	public void responseDeviceDelete(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(this, "디바이스 삭제 완료");
			deviceSelectAll();
			t_admin2.setText("");
			t_admin.setText("");
			t_device.setText("");
			setFillChoice(mainFrame.container.store.storeTable.getAdminList());
		} else {
			JOptionPane.showMessageDialog(this, "디바이스 삭제 실패");
		}
	}

}
