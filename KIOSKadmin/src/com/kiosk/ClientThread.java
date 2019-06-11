package com.kiosk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.kiosk.model.domain.Admin;

public class ClientThread extends Thread {

	Socket client; // 대화용 소켓
	String msg = null;
	MainFrame mainFrame;

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
			
			if (jsonObject.get("responseType").equals("adminLogin")) {
				mainFrame.responseController.adminLogin(jsonObject);
			}
			else if (jsonObject.get("responseType").equals("stockSelectAllByAdmin")) {
				mainFrame.responseController.stockSelectAllByAdmin(jsonObject);
			}
			else if (jsonObject.get("responseType").equals("stockEdit")) {
				mainFrame.responseController.stockEdit(jsonObject);
			}
			else if (jsonObject.get("responseType").equals("salesSelectAllByAdmin")) {
				mainFrame.responseController.salesSelectAllByAdmin(jsonObject);
			}
			else if (jsonObject.get("responseType").equals("orderDetailSelectAllByOrderSummary")) {
				mainFrame.responseController.orderDetailSelectAllByOrderSummary(jsonObject);
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
