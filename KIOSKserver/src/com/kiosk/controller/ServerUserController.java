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

public class ServerUserController {

	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;

	@Autowired
	@Qualifier("deviceServiceImpl")
	private DeviceService deviceService;

	@Autowired
	@Qualifier("orderSummaryServiceImpl")
	private OrderSummaryService orderSummaryService;
	
	@Autowired
	@Qualifier("orderDetailServiceImpl")
	private OrderDetailService orderDetailService;

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
					jsonObject.put("responseType", "deviceSelect");
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
			jsonObject.put("responseType", "deviceSelect");
			jsonObject.put("msg", "1");
			jsonObject.put("result", false);
			// ServerThread.send(jsonObject.toString());
		}
		return jsonObject;
	}
//////===========================================================
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
					jsonObject.put("responseType", "deviceSelect");
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
			jsonObject.put("responseType", "deviceSelect");
			jsonObject.put("msg", "1");
			jsonObject.put("result", false);
			// ServerThread.send(jsonObject.toString());
		}

		return jsonObject;
	}

	public JSONObject salesSelectAllByAdminAndDate(JSONObject obj) {
		boolean flag = false;
		byte[] returnOrderSummaryList = null;
		JSONObject jsonObject = null;
		List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();

		try {
			orderSummaryList = orderSummaryService.selectByDate(Integer.parseInt((String) obj.get("admin_id")));
			flag = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(orderSummaryList);
				returnOrderSummaryList = baos.toByteArray();
				String encoderOrderSummaryList = Base64.getEncoder().encodeToString(returnOrderSummaryList);
				jsonObject = new JSONObject();
				jsonObject.put("responseType", "salesSelectAllByAdminAndDate");
				jsonObject.put("orderSummaryList", encoderOrderSummaryList);
				jsonObject.put("result", flag);

				System.out.println("서버측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	//===================결제요청====================
	
	public JSONObject salesRegist(JSONObject obj) {
		boolean flag;
		String encorderOrderDetailList=null;
		JSONObject jsonObject = null;
		String base64OrderSummary = obj.get("orderSummary").toString();
		String base64OrderDetailList = obj.get("orderDetailList").toString();
		byte[] serializedOrderSummary = Base64.getDecoder().decode(base64OrderSummary);
		byte[] serializedOrderDetailList = Base64.getDecoder().decode(base64OrderDetailList);
		OrderSummary orderSummary = new OrderSummary();
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		List<OrderDetail> orderDetailList2 = new ArrayList<OrderDetail>();
		byte[] returnOrderDetailList =null;
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderSummary)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				System.out.println("첫번째 ois : " + ois);
				orderSummary = (OrderSummary) ois.readObject();
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
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderDetailList)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				System.out.println("두번째 ois : " + ois);
				orderDetailList = (List<OrderDetail>) ois.readObject();
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
			int key = orderSummaryService.insert(orderSummary);
			if(key != 0) {
				System.out.println("orderSummary 등록 성공");
				for(int i=0; i<orderDetailList.size(); i++) {
					OrderDetail orderDetail = orderDetailList.get(i);
					orderDetail.setOrderSummary(orderSummary);
				}
				orderDetailService.insert(orderDetailList);
			}
			flag = true;
			
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			flag = false;
		}
		
		orderDetailList2=orderDetailService.selectAllByOrderSummary(orderSummary.getOrder_summary_id());
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(orderDetailList2);
				returnOrderDetailList = baos.toByteArray();
				encorderOrderDetailList = Base64.getEncoder().encodeToString(returnOrderDetailList);

				// ServerThread.send(jsonObject.toString());
				System.out.println("서버측 요청 완료");
				// kioskServer.connectDevice(obj.get("device_name").toString());

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jsonObject = new JSONObject();
		jsonObject.put("responseType", "salesRegist");
		jsonObject.put("orderDetailList", encorderOrderDetailList);
		jsonObject.put("checkOrderDetailList", orderDetailList);
		jsonObject.put("result", flag);
		return jsonObject;
	}

}