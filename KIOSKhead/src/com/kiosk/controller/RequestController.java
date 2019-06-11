package com.kiosk.controller;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Base64;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.ImageUpload;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.OrderSummary;

public class RequestController {

	MainFrame mainFrame;

	public RequestController(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
	}

//=============������==============================================
	public void adminRegist(Admin admin) {
		System.out.println("Ŭ���̾�Ʈ�κ��� ���� id : " + admin.getId());
		byte[] serializedAdmin;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(admin);
				serializedAdmin = baos.toByteArray();
				String encoderAdmin = Base64.getEncoder().encodeToString(serializedAdmin);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "adminRegist");
				jsonObject.put("admin", encoderAdmin);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("Ŭ���̾�Ʈ�� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adminEdit(Admin admin) {
		System.out.println("Ŭ���̾�Ʈ�κ��� ���� id : " + admin.getId());
		byte[] serializedAdmin;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(admin);
				serializedAdmin = baos.toByteArray();
				String encoderAdmin = Base64.getEncoder().encodeToString(serializedAdmin);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "adminEdit");
				jsonObject.put("admin", encoderAdmin);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("adminEdit ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adminLogin(Admin admin) {
		System.out.println("adminlogin--- id : " + admin.getId());
		byte[] serializedAdmin;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(admin);
				serializedAdmin = baos.toByteArray();
				String encoderAdmin = Base64.getEncoder().encodeToString(serializedAdmin);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "login");
				jsonObject.put("admin", encoderAdmin);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("Ŭ���̾�Ʈ�� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adminDelete(int admin_id) {
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		jsonObject.put("requestType", "adminDelete");
		jsonObject.put("admin_id", Integer.toString(admin_id));
		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("���̵� ���� ��û �Ϸ�");
	}

	public void adminSelectAll() {
		System.out.println("adminSelectAll �޼��� ����");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "adminSelectAll");

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("adminSelectAll ��û �Ϸ�");
	}

	public void adminCheckId(Admin admin) {

		byte[] serializedAdmin;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(admin);
				serializedAdmin = baos.toByteArray();
				String encoderAdmin = Base64.getEncoder().encodeToString(serializedAdmin);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "checkId");
				jsonObject.put("admin", encoderAdmin);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("���̵� üũ ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void adminCheckName(Admin admin) {

		byte[] serializedAdmin;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(admin);
				serializedAdmin = baos.toByteArray();
				String encoderAdmin = Base64.getEncoder().encodeToString(serializedAdmin);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "checkName");
				jsonObject.put("admin", encoderAdmin);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("������ üũ ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ================deviceRegist===============
	public void deviceRegist(Device device) {
		byte[] serializedDevice;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(device);
				serializedDevice = baos.toByteArray();
				String encoderDevice = Base64.getEncoder().encodeToString(serializedDevice);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "deviceRegist");
				jsonObject.put("device", encoderDevice);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("����̽� ��� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ===============deviceCheckByDeviceName=======
	public void deviceCheckByDeviceName(String device_name) {

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "deviceCheckByDeviceName");
		jsonObject.put("device_name", device_name);
		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("����̽� �̸� �ߺ�Ȯ�� ��û �Ϸ�");

	}

	// ================��ǰ����==================

	public void menuSelectAll() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "menuSelectAll");
		mainFrame.clientThread.send(jsonObject.toString());
	}

	public void categorySelectAll() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "categorySelectAll");

		mainFrame.clientThread.send(jsonObject.toString());

	}

	public void categoryRegist(Category category) {
		byte[] serializedCategory;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(category);
				serializedCategory = baos.toByteArray();
				String encoderCateogry = Base64.getEncoder().encodeToString(serializedCategory);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "categoryRegist");
				jsonObject.put("category", encoderCateogry);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("Ŭ���̾�Ʈ�� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ================deviceEdit=====================
	public void deviceEdit(Device device) {
		byte[] serializedDevice;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(device);
				serializedDevice = baos.toByteArray();
				String encodeDevice = Base64.getEncoder().encodeToString(serializedDevice);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "deviceEdit");
				jsonObject.put("device", encodeDevice);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("����̽� ���� ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ================deviceDelete===================
	public void deviceDelete(int device_id) {
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		jsonObject.put("requestType", "deviceDelete");
		jsonObject.put("device_id", Integer.toString(device_id));
		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("����̽� ���� ��û �Ϸ�");
	}
	// ================deviceSelectAll==================

	public void deviceSelectAll() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "deviceSelectAll");

		mainFrame.clientThread.send(jsonObject.toString());
	}

	// ===================���� ����=======================
	public void salesSelectAll() {
		System.out.println("salesSelectAll �޼��� ����");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "salesSelectAll");

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("salesSelectAll ��û �Ϸ�");
	}

	public void orderDetailSelectAllByOrderSummary(int order_summary_id) {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("requestType", "orderDetailSelectAllByOrderSummary");
		jsonObject.put("order_summary_id", Integer.toString(order_summary_id));

		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("salesSelectAllByAdmin ��û �Ϸ�");
	}

	// ==============MenuRegist=================
	public void menuRegist(Menu menu) {
		String encoderImage=ImageUpload.encoding(menu.getFile());
		
		byte[] serializedMenu;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(menu);
				serializedMenu = baos.toByteArray();
				String encoderMenu = Base64.getEncoder().encodeToString(serializedMenu);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "menuRegist");
				jsonObject.put("menu", encoderMenu);
				jsonObject.put("image", encoderImage);
	
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("menuRegist ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =====================categoryEdit===================
	public void categoryEdit(Category category) {
		byte[] serializedCategory;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(category);
				serializedCategory = baos.toByteArray();
				String encoderCategory = Base64.getEncoder().encodeToString(serializedCategory);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "categoryEdit");
				jsonObject.put("category", encoderCategory);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("categoryEdit ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =======================categoryDelete=====================
	public void categoryDelete(int category_id) {
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		jsonObject.put("requestType", "categoryDelete");
		jsonObject.put("category_id", Integer.toString(category_id));
		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("ī�װ� ���� ��û �Ϸ�");
	}

	public void salesSelectAllByDate(OrderSummary orderSummary) {
		byte[] serializedOrderSummary;
		JSONObject jsonObject = null;
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(orderSummary);
				serializedOrderSummary = baos.toByteArray();
				String encoderOrderSummary = Base64.getEncoder().encodeToString(serializedOrderSummary);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "salesSelectAllByDate");
				jsonObject.put("orderSummary", encoderOrderSummary);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("salesSelectAllByDate ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =================menuEdit========================
	public void menuEdit(Menu menu) {
		byte[] serializedMenu;
		JSONObject jsonObject = null;
		String encodingImg=ImageUpload.encoding(menu.getFile());
		menu.setEncoderImg(encodingImg);
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(menu);
				serializedMenu = baos.toByteArray();
				String encoderMenu = Base64.getEncoder().encodeToString(serializedMenu);
				jsonObject = new JSONObject();
				jsonObject.put("requestType", "menuEdit");
				jsonObject.put("menu", encoderMenu);
				mainFrame.clientThread.send(jsonObject.toString());
				System.out.println("menuEdit ��û �Ϸ�");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// =================menuDelete========================
	public void menuDelete(int menu_id) {
		JSONObject jsonObject = null;
		jsonObject = new JSONObject();
		jsonObject.put("requestType", "menuDelete");
		jsonObject.put("menu_id", Integer.toString(menu_id));
		mainFrame.clientThread.send(jsonObject.toString());
		System.out.println("�޴� ���� ��û �Ϸ�");
	}

}
