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
import com.kiosk.model.domain.Device;

public class RegistDevice extends JDialog {
	MainFrame mainFrame;
	JLabel lb_name;
	JPanel p_name;
	JPanel p_bt;
	JButton bt;
	public JTextField t_name;
	boolean flag = false;
	Device device;

	public RegistDevice(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		// ========new==========
		FlowLayout flowLayout = new FlowLayout();
		setLayout(flowLayout);
		flowLayout.setVgap(30);
		// =======name===========
		p_name = new JPanel();
		p_name.setPreferredSize(new Dimension(300, 30));
		lb_name = new JLabel("Dvice 이름 : ");
		lb_name.setPreferredSize(new Dimension(70, 25));
		t_name = new JTextField();
		t_name.setPreferredSize(new Dimension(150, 25));

		// =======bt===========
		bt = new JButton("등록");
		p_bt = new JPanel();

		// =======붙이기===========
		p_name.add(lb_name);
		p_name.add(t_name);
		add(p_name);

		bt.setPreferredSize(new Dimension(80, 30));
		p_bt.add(bt);
		add(p_bt);

		// ==========this의option or Listener==============
		setSize(400, 200);
		setLocationRelativeTo(mainFrame);
		setVisible(false);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);

			}
		});
		setTitle("KIOSK DEVICE 등록");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(false);
		setAlwaysOnTop(true);

		bt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				selectDevice();

			}
		});
	}

	public void selectDevice() {
		Device device = new Device();
		device.setDevice_name(t_name.getText());
		device.setAdmin(mainFrame.getAdmin());
		mainFrame.requestController.selectDevice(device);
	}

	public void responseResult(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(this, "장치 등록 완료");
			device = (Device) jsonObject.get("device");
			System.out.println("유저가 등록한 장치의 가맹점 이름 : " + device.getAdmin().getName());
			mainFrame.setAdmin(device.getAdmin());
			mainFrame.setDevice(device);
			//요청
			mainFrame.myWin.menuPanel.menuNavi.requestGetMenu();
			flag = true;
			setVisible(false);
			mainFrame.setVisible(true);

		} else {
			System.out.println((String) jsonObject.get("msg"));
			if (jsonObject.get("msg").equals("1")) {
				JOptionPane.showMessageDialog(this, "일치하는 정보가 없습니다");
			} else if (jsonObject.get("msg").equals("2")) {
				JOptionPane.showMessageDialog(this, "이미 접속한 디바이스가 있습니다");
			}
		}
	}

	public boolean loginFocus() {
		return flag;
	}

}
