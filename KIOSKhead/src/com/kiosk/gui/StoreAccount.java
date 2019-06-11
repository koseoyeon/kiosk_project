package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Device;

public class StoreAccount extends JPanel{
	MainFrame mainFrame;
	JLabel l_admin_id,l_id, l_pw,l_name,l_title,l_device_name,l_device_title,lb_info;
	JPanel p_admin_id,p_id, p_pw,p_name,p_bt,p_blank,p_blank2,p_blank3,p_device,p_info;
	public JTextField t_admin_id,t_id,t_pw,t_name,t_device;
	JButton create_bt,edit_bt,delete_bt,bt_device_list,bt_device_regist;

	public AdminRegistDialog adminRegistDialog;
	public DevicePanel devicePanel;
	
	Admin admin;
	
	public StoreAccount(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		// ========new==========
		setLayout(new FlowLayout());
		FlowLayout flowLayout = new FlowLayout();
		
		l_title = new JLabel("                                가맹점");
		l_title.setPreferredSize(new Dimension(420,30));
		l_title.setFont(new Font(null, Font.BOLD, 20));
		//=======admin_id=======
		p_admin_id = new JPanel();
		p_admin_id.setPreferredSize(new Dimension(320, 30));
		l_admin_id = new JLabel("ADMIN_ID : ");
		l_admin_id.setPreferredSize(new Dimension(70, 25));
		t_admin_id = new JTextField();
		t_admin_id.setPreferredSize(new Dimension(150, 25));
		t_admin_id.setEditable(false);
		// =======id===========
		p_id = new JPanel();
		p_id.setPreferredSize(new Dimension(320, 30));
		l_id = new JLabel("ID : ");
		l_id.setPreferredSize(new Dimension(70, 25));
		t_id = new JTextField();
		t_id.setPreferredSize(new Dimension(150, 25));
		

		// =======pw===========
		p_pw = new JPanel();
		p_pw.setPreferredSize(new Dimension(320, 30));
		l_pw = new JLabel("Password : ");
		l_pw.setPreferredSize(new Dimension(70, 25));
		t_pw = new JTextField();
		t_pw.setPreferredSize(new Dimension(150, 25));

		//========name===================
		
		p_name = new JPanel();
		p_name.setPreferredSize(new Dimension(320, 30));
		l_name = new JLabel("name : ");
		l_name.setPreferredSize(new Dimension(70, 25));
		t_name = new JTextField();
		t_name.setPreferredSize(new Dimension(150, 25));
		
		// =======bt===========
		p_bt = new JPanel();
		p_bt.setPreferredSize(new Dimension(320, 60));
		create_bt = new JButton("생성");
		edit_bt = new JButton("수정");
		delete_bt = new JButton("삭제");
		//===========blank================
		p_blank = new JPanel();
		p_blank.setPreferredSize(new Dimension(320,90));
		
		p_blank2 = new JPanel();
		p_blank2.setPreferredSize(new Dimension(320,15));
		p_blank3 = new JPanel();
		p_blank3.setPreferredSize(new Dimension(320,10));
		//===========device===============
		p_device = new JPanel();
		p_device.setPreferredSize(new Dimension(320,200));
		
		l_device_title = new JLabel("디바이스");
		l_device_title.setFont(new Font(null, Font.BOLD, 20));
		

		l_device_name = new JLabel("디바이스 이름 : ");
		l_device_name.setPreferredSize(new Dimension(90, 25));
		t_device = new JTextField();
		t_device.setPreferredSize(new Dimension(150, 25));
		bt_device_list = new JButton("device 목록");
		bt_device_regist = new JButton("device 등록");
		devicePanel = new DevicePanel(mainFrame);
	
		p_info = new JPanel();
		p_info.setPreferredSize(new Dimension(450, 60));
		lb_info = new JLabel("                    ※ 가맹점을 등록 하시려면 생성 버튼을 눌러주세요.");
		lb_info.setPreferredSize(new Dimension(450, 60));
		lb_info.setForeground(Color.RED);
		lb_info.setFont(new Font(null, Font.BOLD, 14));
		
		
		
		
		
		
		
		//==========================================
		
	
		p_id.add(l_id);
		p_id.add(t_id);
		
		p_pw.add(l_pw);
		p_pw.add(t_pw);
		
		p_name.add(l_name);
		p_name.add(t_name);
		
		p_bt.add(create_bt);
		p_bt.add(edit_bt);
		p_bt.add(delete_bt);
		
		p_device.add(l_device_title);
		p_device.add(p_blank2);
		
		p_device.add(l_device_name);
		p_device.add(t_device);
		p_device.add(p_blank3);
		p_device.add(bt_device_regist);
		p_device.add(bt_device_list);
		
		p_info.add(lb_info);
		add(p_blank);
		add(p_info);
		add(l_title);
		add(p_id);
		add(p_pw);
		add(p_name);
		add(p_bt);
		p_device.setLayout(flowLayout);
		flowLayout.setVgap(5);
		add(p_device);
		
		
		create_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				adminRegistDialog = new AdminRegistDialog(mainFrame);
				
			}
		});
		delete_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(admin == null) {
					JOptionPane.showMessageDialog(mainFrame, "삭제할 가맹점을 선택해주세요");
					return;
				}
				adminDelete();
				blankText();
			}
		});
		edit_bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Admin> adminList = mainFrame.container.store.storeTable.adminList;
				if(admin == null) {
					JOptionPane.showMessageDialog(mainFrame, "수정할 가맹점을 선택해주세요");
					return;
				}
				for(int i=0; i<adminList.size(); i++) {
					if(t_id.getText().equals(adminList.get(i).getId())) {
						JOptionPane.showMessageDialog(mainFrame, "중복된 아이디로 수정할 수 없습니다");
						return;
					}
					if(t_name.getText().equals(adminList.get(i).getName())){
						JOptionPane.showMessageDialog(mainFrame, "중복된 이름으로 수정할 수 없습니다");
						return;
					}
				}
				
				adminEdit();
				blankText();
			}
		});
		bt_device_regist.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t_device.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame, "디바이스 이름을 입력해주세요");
					return;
				}
				deviceRegist();
				
			}


		});
		
		bt_device_list.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				devicePanel.setVisible(true);
			}
		});

		setPreferredSize(new Dimension(460,800));
		setVisible(true);
	}

	public void filledAccount(Admin admin) {
		this.admin = admin;
		t_admin_id.setText(Integer.toString(admin.getAdmin_id()));
		t_id.setText(admin.getId());
		t_pw.setText(admin.getPw());
		t_name.setText(admin.getName());
		System.out.println("admin_id"+t_admin_id.getText());
	}
	public void adminRegist() {
		Admin admin = new Admin();
		admin.setId(t_id.getText());
		admin.setPw(t_pw.getText());
		admin.setName(t_name.getText());
		mainFrame.requestController.adminRegist(admin);
	}
	public void adminDelete() {
		mainFrame.requestController.adminDelete(Integer.parseInt(t_admin_id.getText()));
	}
	public void responseAdminDelete(JSONObject jsonObject){
		if((boolean)jsonObject.get("result")){
			JOptionPane.showMessageDialog(mainFrame, "가맹점 삭제 성공");
			mainFrame.container.store.storeTable.renderStoreList();
			mainFrame.container.store.storeAccount.devicePanel.deviceSelectAll();
		}else {
			JOptionPane.showMessageDialog(mainFrame, "가맹점 삭제 실패");
		}
	}
	public void adminEdit() {
		Admin admin = new Admin();
		admin.setAdmin_id(Integer.parseInt(t_admin_id.getText()));
		admin.setId(t_id.getText());
		admin.setPw(t_pw.getText());
		admin.setName(t_name.getText());
		mainFrame.requestController.adminEdit(admin);
	}
	public void responseAdminEdit(JSONObject jsonObject){
		if((boolean)jsonObject.get("result")){
			JOptionPane.showMessageDialog(mainFrame, "가맹점 수정 성공");
			mainFrame.container.store.storeTable.renderStoreList();
			mainFrame.container.store.storeTable.reRender();
			mainFrame.container.store.storeAccount.devicePanel.deviceSelectAll();
		}else {
			JOptionPane.showMessageDialog(mainFrame, "가맹점 수정 실패");
			
		}
	}
	public void responseDeviceCheckByDeviceName(JSONObject jsonObject) {
		if((boolean)jsonObject.get("result")){
			deviceRegist();
		}else {
			JOptionPane.showMessageDialog(mainFrame, "중복된 디바이스 이름이 있습니다");
		}
	}
	public void blankText() {
		t_admin_id.setText("");
		t_id.setText("");
		t_pw.setText("");
		t_name.setText("");
	}
	public void deviceRegist() {
		Device device = new Device();
		device.setDevice_name(t_device.getText());
		device.setAdmin(mainFrame.getAdmin());
		Admin admin = new Admin();
		mainFrame.requestController.deviceRegist(device);
		
	}
}























