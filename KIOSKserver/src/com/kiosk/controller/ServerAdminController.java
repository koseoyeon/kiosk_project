package com.kiosk.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.aspectj.apache.bcel.generic.ReturnaddressType;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kiosk.ServerThread;
import com.kiosk.commons.ImageUpload;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.domain.Stock;
import com.kiosk.model.service.AdminService;
import com.kiosk.model.service.DeviceService;
import com.kiosk.model.service.OrderDetailService;
import com.kiosk.model.service.OrderSummaryService;
import com.kiosk.model.service.StockService;

public class ServerAdminController {

	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;

	@Autowired
	@Qualifier("deviceServiceImpl")
	private DeviceService deviceService;
	
	@Autowired
	@Qualifier("stockServiceImpl")
	private StockService stockService;
	
	@Autowired
	@Qualifier("orderDetailServiceImpl")
	private OrderDetailService orderDetailService;
	
	@Autowired
	@Qualifier("orderSummaryServiceImpl")
	private OrderSummaryService orderSummaryService;

	// =========adminlogin============================
	public JSONObject adminLogin(JSONObject obj) {
		boolean flag;
		Admin responseAdmin = null;
		String base64Admin = obj.get("admin").toString();
		JSONObject jsonObject = null;
		byte[] serializedAdmin = Base64.getDecoder().decode(base64Admin);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedAdmin)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				Admin admin = (Admin) ois.readObject();
				try {
					responseAdmin = adminService.login(admin);
					if (responseAdmin != null) {
						flag = true;
						byte[] returnAdmin;

						try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
							try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
								oos.writeObject(responseAdmin);
								returnAdmin = baos.toByteArray();
								String encoderAdmin = Base64.getEncoder().encodeToString(returnAdmin);
								jsonObject = new JSONObject();
								jsonObject.put("responseType", "adminLogin");
								jsonObject.put("admin", encoderAdmin);
								jsonObject.put("result", flag);
								// ServerThread.send(jsonObject.toString());
								System.out.println("서버측 요청 완료");
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						flag = false;
						jsonObject = new JSONObject();
						jsonObject.put("responseType", "adminLogin");
						jsonObject.put("result", flag);
						// ServerThread.send(jsonObject.toString());

					}
				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());

				}

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
		return jsonObject;
	}
	
	// =========상품관리============================
	
	public JSONObject stockSelectAllByAdmin(JSONObject obj) {
		boolean flag = false;
		byte[] returnStockList = null;

		
		JSONObject jsonObject = null;
		List<Stock> stockList = new ArrayList<Stock>();
		
		String path = System.getProperty("user.dir");
		System.out.println(path);
		String realPath=path+"/menu_res/";
		try {
			stockList = stockService.selectAllByAdmin(Integer.parseInt((String) obj.get("admin_id")));
			for(int i=0;i<stockList.size(); i++) {
				String file_name = stockList.get(i).getMenu().getFile_name();
				File file = new File(realPath+file_name);
				stockList.get(i).getMenu().setEncoderImg(ImageUpload.encoding(file));
			}
			flag = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(stockList);
				
				returnStockList = baos.toByteArray();
				String encoderStockList = Base64.getEncoder().encodeToString(returnStockList);
		
				jsonObject = new JSONObject();
				jsonObject.put("responseType", "stockSelectAllByAdmin");
				jsonObject.put("stockList", encoderStockList);
				jsonObject.put("result", flag);

				System.out.println("서버측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	public JSONObject stockEdit(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject=null;
		String base64Stock = obj.get("stock").toString();
		byte[] serializedStock = Base64.getDecoder().decode(base64Stock);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedStock)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				// 역직렬화된 Member 객체를 읽어온다.
				Stock stock = (Stock) ois.readObject();
				try {
					stockService.update(stock);
					System.out.println("업데이트 성공");
					flag = true;

				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "stockEdit");
				jsonObject.put("result", flag);

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
		return jsonObject;
	}
	// =========매출관리============================
	public JSONObject salesSelectAllByAdmin(JSONObject obj) {
		boolean flag = false;
		byte[] returnOrderSummaryList = null;
		JSONObject jsonObject = null;
		List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();
		int admin_id=Integer.parseInt((String) obj.get("admin_id"));
		try {
			orderSummaryList = orderSummaryService.selectByAdminId(admin_id);
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
				jsonObject.put("responseType", "salesSelectAllByAdmin");
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
	
	public JSONObject orderDetailSelectAllByOrderSummary(JSONObject obj) {
		boolean flag = false;
		byte[] returnOrderDetailList = null;
		JSONObject jsonObject = null;
		List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
		int order_summary_id=Integer.parseInt((String) obj.get("order_summary_id"));
		try {
			orderDetailList = orderDetailService.selectAllByOrderSummary(order_summary_id);
			System.out.println("여기는 서버 어드민 컨트롤러 : "+orderDetailList.size());
			flag = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(orderDetailList);
				returnOrderDetailList = baos.toByteArray();
				String encoderOrderDetailList = Base64.getEncoder().encodeToString(returnOrderDetailList);
				jsonObject = new JSONObject();
				jsonObject.put("responseType", "orderDetailSelectAllByOrderSummary");
				jsonObject.put("orderDetailList", encoderOrderDetailList);
				jsonObject.put("result", flag);

				System.out.println("서버측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
}
