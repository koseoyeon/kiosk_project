package com.kiosk;

import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.kiosk.controller.RequestController;
import com.kiosk.controller.ResponseController;
import com.kiosk.gui.LoginForm;
import com.kiosk.gui.MyWin;
import com.kiosk.gui.RegistDevice;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Device;

public class MainFrame extends JFrame {

	JButton registBtn;
	JPanel p1;
	int port = 9876;
	InetAddress inetAddess;
	//String ip="192.168.54.26";
	String ip;
	
	Socket client;
	public ClientThread clientThread;
	public RequestController requestController;
	public ResponseController responseController;
	public RegistDevice registDevice;
	public LoginForm loginForm;
	// ���� Ű����ũ ����̽��� ������ ������ ����
	public Admin admin;
	public Device device;

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public MyWin myWin;

	public MainFrame() {
		setLayout(new FlowLayout());
		getIp(); // ���� �������� ��ǻ���� ip������
		connect(); // kioskServer�� ����
		createController();

		myWin = new MyWin(this);
		registDevice = new RegistDevice(this);
		loginForm = new LoginForm(this);
		add(myWin);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				requestController.disconnectDevice(device);
				System.out.println("disconnet��û");
				System.exit(0);
			}
		});
		setTitle("KIOSK");
		setSize(600, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
	}

	// ���� ip������
	public void getIp() {
		try {
			inetAddess = InetAddress.getLocalHost();
			ip = inetAddess.getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		System.out.println("������ ip : " + ip);
	}

	// kioskServer�� ����
	public void connect() {
		System.out.println("connect() �޼��� ����");
		try {
			client = new Socket(ip, port);
			System.out.println("client : " + client);
			clientThread = new ClientThread(this, client);
			System.out.println("=====Client_thread����=====");
			// ������ ���� ����
			clientThread.start();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createController() {
		requestController = new RequestController(this);
		responseController = new ResponseController(this);
	}
}
