package com.kiosk;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kiosk.controller.RequestController;
import com.kiosk.controller.ResponseController;
import com.kiosk.gui.Container;
import com.kiosk.gui.LoginForm;
import com.kiosk.model.domain.Admin;

public class MainFrame extends JFrame {

//================================================
	int port = 9876;
	InetAddress inetAddess;
	//String ip="192.168.54.26";
	String ip;
	Socket client;
	public ClientThread clientThread;
	public LoginForm loginForm;
	public RequestController requestController = new RequestController(this);
	public ResponseController responseController = new ResponseController(this);
//================================================
	public Admin admin;
	
	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Container container;
	public MainFrame() {
		getIp(); // ���� �������� ��ǻ���� ip������
		connect(); // kioskServer�� ����
		container =new Container(this);
		loginForm = new LoginForm(this);
		add(container);		
		setSize(1200, 860);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("������ ����");
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
//========================network==========================
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
		try {
			client = new Socket(ip, port);
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
//========================================================
}
