package com.kiosk.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
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
	
	//==================재고 관리====================
	public void stockSelectAllByAdmin(JSONObject jsonObject) {
		if ((boolean)jsonObject.get("result")) {
			String base64StockList = jsonObject.get("stockList").toString();
			byte[] serializedStockList = Base64.getDecoder().decode(base64StockList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedStockList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					List<Stock> stockList = (List<Stock>) ois.readObject();
					jsonObject.remove("stockList");
					jsonObject.put("stockList", stockList);
					//System.out.println("클라이언트쓰레드 / 리스트 : "+jsonObject.get("stockList"));
					mainFrame.container.product.responseResult1(jsonObject);

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
			mainFrame.container.product.responseResult1(jsonObject);
		}
	}
	public void stockEdit(JSONObject jsonObject) {
		mainFrame.container.product.responseResult2(jsonObject);
	}
	//==================매출관리====================
	
	public void salesSelectAllByAdmin(JSONObject jsonObject) {
		if ((boolean)jsonObject.get("result")) {
			String base64OrderSummaryList = jsonObject.get("orderSummaryList").toString();
			byte[] serializedOrderSummaryList = Base64.getDecoder().decode(base64OrderSummaryList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderSummaryList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					List<OrderSummary> orderSummaryList = (List<OrderSummary>) ois.readObject();
					jsonObject.remove("orderSummaryList");
					jsonObject.put("orderSummaryList", orderSummaryList);
					//System.out.println("클라이언트쓰레드 / 리스트 : "+jsonObject.get("orderSummaryList"));
					mainFrame.container.sales.responseResult(jsonObject);

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
			mainFrame.container.sales.responseResult(jsonObject);
		}
	}
	
	public void orderDetailSelectAllByOrderSummary(JSONObject jsonObject) {
		if ((boolean)jsonObject.get("result")) {
			String base64OrderDetailList = jsonObject.get("orderDetailList").toString();
			byte[] serializedOrderDetailList = Base64.getDecoder().decode(base64OrderDetailList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderDetailList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					List<OrderDetail> orderDetailList =(List<OrderDetail>) ois.readObject();
					jsonObject.remove("orderDetailList");
					jsonObject.put("orderDetailList", orderDetailList);
					System.out.println("클라이언트쓰레드 / 리스트 : "+jsonObject.get("orderDetailList"));
					mainFrame.container.sales.orderDetailPanel.responseResult(jsonObject);

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
			mainFrame.container.sales.orderDetailPanel.responseResult(jsonObject);
		}
	}
}
