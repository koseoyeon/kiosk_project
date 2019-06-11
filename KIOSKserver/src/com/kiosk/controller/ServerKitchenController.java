package com.kiosk.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kiosk.KioskServer;
import com.kiosk.ServerThread;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.domain.Stock;
import com.kiosk.model.service.AdminService;
import com.kiosk.model.service.DeviceService;
import com.kiosk.model.service.OrderDetailService;
import com.kiosk.model.service.OrderSummaryService;

public class ServerKitchenController {

	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;

	@Autowired
	@Qualifier("deviceServiceImpl")
	private DeviceService deviceService;

	// =========Device selectByName============================
	public JSONObject selectByNameListSize0(JSONObject obj) {
		boolean flag = false;
		Device device=null;
	
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				System.out.println("첫번째 ois : " + ois);
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
		
		try {
			device = deviceService.selectByNameAndAdmin(device);
			System.out.println("wqeqwe" + device);
			flag = true;

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		byte[] returnDevice = null;
		JSONObject jsonObject = null;

		if (device != null) {
			
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
					oos.writeObject(device);
					returnDevice = baos.toByteArray();
					String encorderDevice = Base64.getEncoder().encodeToString(returnDevice);
					jsonObject = new JSONObject();
					jsonObject.put("responseType", "kitchenDeviceSelect");
					jsonObject.put("device", encorderDevice);
					jsonObject.put("checkDevice", device);
					jsonObject.put("result", flag);
					// ServerThread.send(jsonObject.toString());
					System.out.println("서버측 요청 완료");
					// kioskServer.connectDevice(obj.get("device_name").toString());

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			jsonObject = new JSONObject();
			jsonObject.put("responseType", "kitchenDeviceSelect");
			jsonObject.put("msg", "1");
			jsonObject.put("result", false);
			// ServerThread.send(jsonObject.toString());
		}
		return jsonObject;
	}

	public JSONObject selectByName(JSONObject obj) {
		boolean flag = false;
	
		Device device1=null;
		Device device2=null;
		
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				System.out.println("첫번째 ois : " + ois);
				device1 = (Device) ois.readObject();
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

		try {
			device2 = deviceService.selectByNameAndAdmin(device1);
			System.out.println("wqeqwe" + device2);
			flag = true;

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		byte[] returnDevice = null;
		JSONObject jsonObject = null;

		// System.out.println(device.getDevice_name());
		if (device2 != null && device2.getDevice_name().equals(device1.getDevice_name())) {
		
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
					oos.writeObject(device2);
					returnDevice = baos.toByteArray();
					String encorderDevice = Base64.getEncoder().encodeToString(returnDevice);
					jsonObject = new JSONObject();
					jsonObject.put("responseType", "kitchenDeviceSelect");
					jsonObject.put("device", encorderDevice);
					jsonObject.put("checkDevice", device2);
					jsonObject.put("result", flag);
					// ServerThread.send(jsonObject.toString());
					System.out.println("서버측 요청 완료");
					// kioskServer.connectDevice(obj.get("device_name").toString());

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			jsonObject = new JSONObject();
			jsonObject.put("responseType", "kitchenDeviceSelect");
			jsonObject.put("msg", "1");
			jsonObject.put("result", false);
			// ServerThread.send(jsonObject.toString());
		}

		return jsonObject;
	}




}