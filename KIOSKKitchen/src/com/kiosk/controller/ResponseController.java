package com.kiosk.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.domain.Stock;

public class ResponseController {
	
	MainFrame mainFrame;

	public ResponseController(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
	}

	//===============admin로그인===========================
		public void adminLogin(JSONObject jsonObject) {
			if ((boolean)jsonObject.get("result")) {
				String base64Admin = jsonObject.get("admin").toString();
				byte[] serializedMember = Base64.getDecoder().decode(base64Admin);

				try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
					try (ObjectInputStream ois = new ObjectInputStream(bais)) {
						// 역직렬화된 Member 객체를 읽어온다.
						System.out.println("ois : " + ois);
						Admin admin = (Admin) ois.readObject();
						jsonObject.remove("admin");
						jsonObject.put("admin", admin);
						//System.out.println(jsonObject.toString());
						mainFrame.loginForm.responseResult(jsonObject);

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

			}else {
				mainFrame.loginForm.responseResult(jsonObject);
			}
		}
	public void deviceSelectResult(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64Device = jsonObject.get("device").toString();
			byte[] serializedDevice = Base64.getDecoder().decode(base64Device);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					System.out.println("ois : " + ois);
					Device device = (Device) ois.readObject();
					jsonObject.remove("device");
					jsonObject.put("device", device);
					System.out.println(jsonObject.toString());

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

		}
		mainFrame.registDevice.responseResult(jsonObject);

	}
	public void salesRegist(JSONObject jsonObject){
		
		if ((boolean) jsonObject.get("result")) {
			String base64OrderDetailList = jsonObject.get("orderDetailList").toString();
			byte[] serializedOrderDetailList = Base64.getDecoder().decode(base64OrderDetailList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderDetailList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					System.out.println("ois : " + ois);
					List<OrderDetail> orderDetailList = (List<OrderDetail>) ois.readObject();
					jsonObject.remove("orderDetailList");
					jsonObject.put("orderDetailList", orderDetailList);
					System.out.println(jsonObject.toString());

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

		}
		mainFrame.myWin.salesRegistResponseResult(jsonObject);
	}
	
}
