package com.kiosk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMain {
	public static void main(String[] args) {
		// 컨테이너를 이용하여 객체 생성 및 사용!!!!
		ClassPathXmlApplicationContext context = null;
		String path = "com/kiosk/spring/context/context.xml";
		context = new ClassPathXmlApplicationContext(path);
		MainFrame mainFrame = (MainFrame) context.getBean("mainFrame");
		
		
		context.close();
	}
}
