package com.kiosk.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;

public class AdminRegistDialog extends JDialog{
	MainFrame mainFrame;
	JLabel l_admin_id,l_id, l_pw,l_name,l_title,l_device_name;
	JPanel p_admin_id,p_id, p_pw,p_name,p_bt,p_blank;
	public JTextField t_admin_id,t_id,t_pw,t_name;
	
	
	JButton bt_create, bt_id_check,bt_name_check;
	JPanel p;
	boolean checkId;
	boolean idChecked;
	boolean checkName;
	boolean checkedName;
	
	
	public AdminRegistDialog(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout());
		
		p = new JPanel();
		p.setPreferredSize(new Dimension(450, 290));
		// =======id===========
		p_id = new JPanel();
		p_id.setPreferredSize(new Dimension(430, 40));
		l_id = new JLabel("ID : ");
		l_id.setPreferredSize(new Dimension(70, 30));
		t_id = new JTextField();
		t_id.setPreferredSize(new Dimension(150, 30));
		

		// =======pw===========
		p_pw = new JPanel();
		p_pw.setPreferredSize(new Dimension(430, 40));
		l_pw = new JLabel("Password : ");
		l_pw.setPreferredSize(new Dimension(70, 30));
		t_pw = new JTextField();
		t_pw.setPreferredSize(new Dimension(150, 30));
		
		//========name===================
		
		p_name = new JPanel();
		p_name.setPreferredSize(new Dimension(430, 40));
		l_name = new JLabel("name : ");
		l_name.setPreferredSize(new Dimension(70, 30));
		t_name = new JTextField();
		t_name.setPreferredSize(new Dimension(150, 30));
		
		bt_create = new JButton("���� ����");
		bt_id_check = new JButton("���̵� üũ");
		bt_name_check = new JButton("������ üũ");
		
		//======================
		p_blank = new JPanel();
		p_blank.setPreferredSize(new Dimension(103,30));
		
		
		p_id.add(l_id);
		p_id.add(t_id);
		p_id.add(bt_id_check);
		
		
		
		p_pw.add(l_pw);
		p_pw.add(t_pw);
		p_pw.add(p_blank);
		
		p_name.add(l_name);
		p_name.add(t_name);
		p_name.add(bt_name_check);
		
		p.add(p_id);
		p.add(p_pw);
		p.add(p_name);
		p.add(bt_create);
		//���̵� �ߺ�üũ�����Ͽ� �������� ����
		Document doc = t_id.getDocument();
		doc.addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkId = false;
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				idChecked = false;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		
		Document doc2 = t_name.getDocument();
		doc2.addDocumentListener(new DocumentListener() {
			@Override
			public void removeUpdate(DocumentEvent e) {
				checkName = false;
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				checkedName = false;
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
	      
		add(p);
		bt_create.setVisible(true);
		bt_create.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (idChecked == false) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.adminRegistDialog, "���̵� �ߺ����θ� üũ�ϼ���");
					return;
				}
				if (checkedName == false) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.adminRegistDialog, "������ �ߺ����θ� üũ�ϼ���");
					return;
				}
				if (t_pw.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.adminRegistDialog, "��й�ȣ�� �Է����ּ���");
					return;
				}
				Admin admin =new Admin();
				admin.setId(t_id.getText());
				admin.setPw(t_pw.getText());
				admin.setName(t_name.getText());
				mainFrame.requestController.adminRegist(admin);
				t_id.setText("");
				t_pw.setText("");
				t_name.setText("");
				
			}
		});
		bt_id_check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t_id.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.adminRegistDialog, "����� ���̵� �Է����ּ���.");
					return;
				}
				Admin admin =new Admin();
				admin.setId(t_id.getText());
				mainFrame.requestController.adminCheckId(admin);
				
			}
		});
		bt_name_check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t_name.getText().equals("")) {
					JOptionPane.showMessageDialog(mainFrame.container.store.storeAccount.adminRegistDialog, "����� ���������� �Է����ּ���.");
					return;
				}
				Admin admin =new Admin();
				admin.setName(t_name.getText());
				mainFrame.requestController.adminCheckName(admin);
				
				
			}
		});

		this.setTitle("������ �ű� ���");
		this.setSize(500, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(true);
	}
	public void responseAdminRegist(boolean flag) {
		if(flag) {
			this.setVisible(false);
			JOptionPane.showMessageDialog(this, "���� ��� ����");
			mainFrame.container.store.storeTable.renderStoreList();
			mainFrame.container.store.storeTable.reRender();
		}else {
			JOptionPane.showMessageDialog(this, "���� ��� ����");
		}
		
	}
	public void responseCheckId(boolean flag) {
		if(flag) {
			JOptionPane.showMessageDialog(this, "��� ������ ���̵� �Դϴ�");
			idChecked = true;
		}else {
			JOptionPane.showMessageDialog(this, "��� �Ұ����� ���̵� �Դϴ�");

		}
	}
	public void responseCheckName(boolean flag) {
		if(flag) {
			JOptionPane.showMessageDialog(this, "��� ������ ������ �Դϴ�");
			checkedName = true;
			if(checkId ==true && checkName ==true) {
				bt_create.setVisible(true);
			}
		}else {
			JOptionPane.showMessageDialog(this, "��� �Ұ����� ������ �Դϴ�");

		}
	}
}
