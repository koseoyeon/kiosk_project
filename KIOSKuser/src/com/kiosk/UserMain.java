package com.kiosk;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserMain {
	public static void main(String[] args) {
		// �����̳ʸ� �̿��Ͽ� ��ü ���� �� ���!!!!
		ClassPathXmlApplicationContext context = null;
		String path = "com/kiosk/spring/context/context.xml";
		context = new ClassPathXmlApplicationContext(path);
		MainFrame mainFrame = (MainFrame) context.getBean("mainFrame");
		
		
		context.close();
	}
}
