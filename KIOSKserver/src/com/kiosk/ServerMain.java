package com.kiosk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kiosk.controller.ServerHeadController;

public class ServerMain {
	public static void main(String[] args) {
		// �����̳ʸ� �̿��Ͽ� ��ü ���� �� ���!!!!
		ClassPathXmlApplicationContext context = null;
		String path = "com/kiosk/spring/context/context.xml";
		context = new ClassPathXmlApplicationContext(path);
		KioskServer kioskServer = (KioskServer) context.getBean("kioskServer");
		System.out.println("����� ���θ޼����� kioskServer : "+kioskServer);
		context.close();
	}
}
