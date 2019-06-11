package com.kiosk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kiosk.controller.ServerHeadController;

public class ServerMain {
	public static void main(String[] args) {
		// 컨테이너를 이용하여 객체 생성 및 사용!!!!
		ClassPathXmlApplicationContext context = null;
		String path = "com/kiosk/spring/context/context.xml";
		context = new ClassPathXmlApplicationContext(path);
		KioskServer kioskServer = (KioskServer) context.getBean("kioskServer");
		System.out.println("여기는 메인메서드의 kioskServer : "+kioskServer);
		context.close();
	}
}
