package com.kiosk.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;

public class RequestController {

	MainFrame mainFrame;

	public RequestController(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
	}

	//===============admin로그인===========================
	
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
				System.out.println("클라이언트측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void selectDevice(Device device) {
		byte[] serializedDevice;
		JSONObject jsonObject = null;
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(device);
				serializedDevice = baos.toByteArray();
				String encoderDevice = Base64.getEncoder().encodeToString(serializedDevice);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "kitchenDeviceSelect");
				jsonObject.put("device", encoderDevice);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("클라이언트측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void disconnectDevice(Device device) {
		if (device != null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("requestType", "kitchenDisconnectDevice");
			jsonObject.put("device_name", device.getDevice_name());
			mainFrame.clientThread.send(jsonObject.toString());
		}
	}
	
}
