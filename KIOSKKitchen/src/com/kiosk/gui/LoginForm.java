package com.kiosk.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.asm.Label;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;

public class LoginForm extends JDialog {
	MainFrame mainFrame;
	JLabel l_id, l_pw;
	JPanel p_id, p_pw, p_bt;
	JButton bt;
	public JTextField t_id;
	public JPasswordField t_pw;
	boolean flag = false;
	Admin admin;
	public LoginForm(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		// ========new==========
		setLayout(new FlowLayout());

		// =======id===========
		p_id = new JPanel();
		p_id.setPreferredSize(new Dimension(300, 30));
		l_id = new JLabel("ID : ");
		l_id.setPreferredSize(new Dimension(70, 25));
		t_id = new JTextField();
		t_id.setPreferredSize(new Dimension(150, 25));
		
		// =======pw===========
		p_pw = new JPanel();
		p_pw.setPreferredSize(new Dimension(300, 30));
		l_pw = new JLabel("Password : ");
		l_pw.setPreferredSize(new Dimension(70, 25));
		t_pw = new JPasswordField();
		t_pw.setPreferredSize(new Dimension(150, 25));

		// =======bt===========
		bt = new JButton("로그인");
		p_bt = new JPanel();

		// =======붙이기===========
		p_id.add(l_id);
		p_id.add(t_id);
		p_pw.add(l_pw);
		p_pw.add(t_pw);
		add(p_id);
		add(p_pw);

		bt.setPreferredSize(new Dimension(80, 30));
		p_bt.add(bt);
		add(p_bt);

		// ==========this의option or Listener==============
		setSize(400, 200);
		setLocationRelativeTo(mainFrame);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setTitle("가맹점 로그인(주방)");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setAlwaysOnTop(true);

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login();

			}
		});
	}

	public void login() {
		Admin admin = new Admin();
		admin.setId(t_id.getText());
		admin.setPw(t_pw.getText());
		mainFrame.requestController.adminLogin(admin);
	}

	public void responseResult(JSONObject jsonObject) {
		System.out.println("여긴 오나??");
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(this, "로그인 성공");
			admin = (Admin) jsonObject.get("admin");
			mainFrame.setAdmin(admin);
			mainFrame.setTitle(admin.getName()+" 로그인");
			flag = true;
			setVisible(false);
			mainFrame.registDevice.setVisible(true);

		}else {
			JOptionPane.showMessageDialog(this, "일치하는 정보가 없습니다");
		}
	}
	public boolean loginFocus() {
		return flag;
	}

}
