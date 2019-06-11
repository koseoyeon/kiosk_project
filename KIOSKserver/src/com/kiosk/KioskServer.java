package com.kiosk;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.kiosk.controller.ServerHeadController;
import com.kiosk.model.domain.Device;


public class KioskServer extends JFrame {
	JPanel p_north;
	JTextField t_port;
	JButton bt;
	JTextArea area;
	JScrollPane scroll;
	JScrollBar bar;

	// 서버 소켓 관련
	ServerSocket server;
	int port = 9876;
	Socket client;
	String ip;

	// 서버 쓰레드 관련
	Thread mainThread;
	public Vector<ServerThread> list = new Vector();
	public List<String> deviceList = new ArrayList<String>();
	public List<String> kitchenDeviceList = new ArrayList<String>();

	
	
	public KioskServer() {
		p_north = new JPanel();
		t_port = new JTextField(Integer.toString(port), 8);
		bt = new JButton("서버가동");
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bar = scroll.getVerticalScrollBar();

		// main쓰레드 생성(계속해서 접속자 감지를 하는 쓰레드)
		mainThread = new Thread() {
			public void run() {
				System.out.println("mainThread running");
				runServer();
			}
		};

		p_north.add(t_port);
		p_north.add(bt);
		add(p_north, BorderLayout.NORTH);
		add(scroll);

		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainThread.start();
			}
		});

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
				
			}
		});

		setSize(600, 800);
		setVisible(true);
	}
	

	public void runServer() {
		port = Integer.parseInt(t_port.getText());

		try {
			server = new ServerSocket(port); // 서버 소켓 생성
			area.append("서버 생성 완료\n");

			// 접속자 감지 행위를 계속 진행함
			while (true) {
				client = server.accept(); // 서버 가동
				ip = client.getInetAddress().getHostAddress();
				area.append(ip + "님이 접속했습니다.\n");
				// 접속자 각각을 감당할 쓰레드 생성
				ServerThread st = new ServerThread(this,client,ip);
				st.start();
				list.add(st);
				System.out.println("vector리스트에 담길 서버스레드 : " + st);
				area.append(list.size() + "명 접속중\n");

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 서버 생성
	}
	
	public void connectDevice(JSONObject obj) {
		Device device=null;
		
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				
				device = (Device) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		deviceList.add(device.getDevice_name());
		System.out.println("담기니?"+deviceList.get(0));
	}
	
	public boolean checkDevice(JSONObject obj) {
		Device device=null;
		
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				
				device = (Device) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(deviceList.size());
		boolean check = false;
		for(int i=0; i<deviceList.size(); i++) {
			if(deviceList.get(i).equals(device.getDevice_name())) {		
				check =  true;
			}
		}
		System.out.println("반환값은? "+check);
		return check;
	}
	
	public void removeDevice(String device_name) {
		deviceList.remove(device_name);
	}
//====================kitchen========================
	public void kitchenConnectDevice(JSONObject obj) {
		Device device=null;
		
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				
				device = (Device) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		kitchenDeviceList.add(device.getDevice_name());
		//System.out.println("담기니?"+kitchenDeviceList.get(0));
	}
	
	public boolean kitchenCheckDevice(JSONObject obj) {
		Device device=null;
		
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				
				device = (Device) ois.readObject();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(kitchenDeviceList.size());
		boolean check = false;
		for(int i=0; i<kitchenDeviceList.size(); i++) {
			if(kitchenDeviceList.get(i).equals(device.getDevice_name())) {		
				check =  true;
			}
		}
		System.out.println("반환값은? "+check);
		return check;
	}
	
	public void kitchenRemoveDevice(String device_name) {
		kitchenDeviceList.remove(device_name);
	}

}
