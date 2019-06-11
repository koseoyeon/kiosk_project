package com.kiosk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kiosk.controller.RequestController;

public class ClientThread extends Thread {

	Socket client; // 대화용 소켓
	String msg = null;
	public MainFrame mainFrame;
	public KitchenMainFrame kitchenMainFrame;
	BufferedReader buffr = null;
	BufferedWriter buffw = null;
	
	public ClientThread(MainFrame mainFrame, Socket client) {
		this.mainFrame = mainFrame;
		this.client = client;
		// TODO Auto-generated constructor stub
		try {
			System.out.println(client);
			
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listen() {
		try {
			String msg = buffr.readLine();
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = new JSONObject();
			jsonObject = (JSONObject) parser.parse(msg);
			System.out.println("서버로부터 온 응답 메세지 : " + msg);
			if (jsonObject.get("responseType").equals("deviceSelect")) {
				mainFrame.responseController.deviceSelectResult(jsonObject);
			} else if (jsonObject.get("responseType").equals("salesSelectAllByAdminAndDate")) {
				mainFrame.responseController.salesSelectAllByAdminAndDate(jsonObject);
			}else if (jsonObject.get("responseType").equals("categorySelectAll")) {
				mainFrame.responseController.categorySelectAll(jsonObject);
			}else if (jsonObject.get("responseType").equals("stockSelectAllByAdmin")) {
				mainFrame.responseController.stockSelectAllByAdmin(jsonObject);
			}else if (jsonObject.get("responseType").equals("salesRegist")) {
				mainFrame.responseController.salesRegist(jsonObject);
		
			}else if (jsonObject.get("responseType").equals("adminLogin")) {
				mainFrame.responseController.adminLogin(jsonObject);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			listen();
		}
	}
}
