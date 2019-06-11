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
	public void selectDevice(Device device) {
		byte[] serializedDevice;
		JSONObject jsonObject = null;
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(device);
				serializedDevice = baos.toByteArray();
				String encoderDevice = Base64.getEncoder().encodeToString(serializedDevice);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "deviceSelect");
				jsonObject.put("device", encoderDevice);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("Ŭ���̾�Ʈ�� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void disconnectDevice(Device device) {
		if (device != null) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("requestType", "disconnectDevice");
			jsonObject.put("device_name", device.getDevice_name());
			mainFrame.clientThread.send(jsonObject.toString());
		}
	}
	
	public void salesSelectAllByAdminAndDate(int admin_id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "salesSelectAllByAdminAndDate");
		jsonObject.put("admin_id", Integer.toString(admin_id));

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("salesSelectAllByAdminAndDate ��û �Ϸ�");
	}

	public void categorySelectAll() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "categorySelectAll");
		
		mainFrame.clientThread.send(jsonObject.toString());
		
	}
	
	public void stockSelectAllByAdmin(int admin_id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "stockSelectAllByAdmin");
		jsonObject.put("admin_id", Integer.toString(admin_id));

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("stockSelectAllByAdmin ��û �Ϸ�");
	}
	//==========������û=======================================
	public void salesRegist(OrderSummary orderSummary, List<OrderDetail> orderDetailList) {
		System.out.println("Ŭ���̾�Ʈ�κ��� ���� orderSummary_id : "+orderSummary.getOrder_price());
		System.out.println("Ŭ���̾�Ʈ�κ��� ���� orderDetailList : "+orderDetailList.size());
		byte[] serializedOrderSummary;
		byte[] serializedOrderDetailList;
		JSONObject jsonObject = null;
		String encoderOrderSummary = null;
		String encoderOrderDetailList = null;
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(orderSummary);
				serializedOrderSummary = baos.toByteArray();
				encoderOrderSummary = Base64.getEncoder().encodeToString(serializedOrderSummary);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
				oos.writeObject(orderDetailList);
				serializedOrderDetailList = baos.toByteArray();
				encoderOrderDetailList = Base64.getEncoder().encodeToString(serializedOrderDetailList);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonObject = new JSONObject();
		jsonObject.put("requestType", "salesRegist");
		jsonObject.put("orderSummary", encoderOrderSummary);
		jsonObject.put("orderDetailList", encoderOrderDetailList);
		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("Ŭ���̾�Ʈ�� ��û �Ϸ�");
	}
}