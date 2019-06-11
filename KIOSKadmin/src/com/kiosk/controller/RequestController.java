package com.kiosk.controller;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Base64;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Stock;


public class RequestController {
	
	MainFrame mainFrame;
	
	public RequestController(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
	}
	
	//===============admin�α���===========================
	
	public void adminLogin(Admin admin) {
		byte[] serializedAdmin;
		JSONObject jsonObject = null;
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(admin);
				serializedAdmin = baos.toByteArray();
				String encoderAdmin = Base64.getEncoder().encodeToString(serializedAdmin);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "adminLogin");
				jsonObject.put("admin", encoderAdmin);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("Ŭ���̾�Ʈ�� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//================������==================
	public void stockSelectAllByAdmin(int admin_id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "stockSelectAllByAdmin");
		jsonObject.put("admin_id", Integer.toString(admin_id));

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("stockSelectAllByAdmin ��û �Ϸ�");
	}
	
	public void stockEdit(Stock stock) {
		System.out.println("�����ϰ��� �ϴ� ���°� : "+stock.getState().getState_type());
		byte[] serializedStock;
		JSONObject jsonObject = null;
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(stock);
				serializedStock = baos.toByteArray();
				String encoderStock = Base64.getEncoder().encodeToString(serializedStock);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "stockEdit");
				jsonObject.put("stock", encoderStock);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("Ŭ���̾�Ʈ�� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//================�������==================
	public void salesSelectAllByAdmin(int admin_id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "salesSelectAllByAdmin");
		jsonObject.put("admin_id", Integer.toString(admin_id));

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("salesSelectAllByAdmin ��û �Ϸ�");
	}
	
	public void orderDetailSelectAllByOrderSummary(int order_summary_id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "orderDetailSelectAllByOrderSummary");
		jsonObject.put("order_summary_id", Integer.toString(order_summary_id));

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("salesSelectAllByAdmin ��û �Ϸ�");
	}
}
