package com.kiosk.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.ImageUpload;
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

	// ===============admin로그인===========================
	public void adminLogin(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64Admin = jsonObject.get("admin").toString();
			byte[] serializedMember = Base64.getDecoder().decode(base64Admin);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					System.out.println("ois : " + ois);
					Admin admin = (Admin) ois.readObject();
					jsonObject.remove("admin");
					jsonObject.put("admin", admin);
					// System.out.println(jsonObject.toString());
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

		} else {
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

	public void salesSelectAllByAdminAndDate(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64OrderSummaryList = jsonObject.get("orderSummaryList").toString();
			byte[] serializedOrderSummaryList = Base64.getDecoder().decode(base64OrderSummaryList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderSummaryList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					List<OrderSummary> orderSummaryList = (List<OrderSummary>) ois.readObject();
					jsonObject.remove("orderSummaryList");
					jsonObject.put("orderSummaryList", orderSummaryList);
					System.out.println("클라이언트쓰레드 / 리스트 : " + jsonObject.get("orderSummaryList"));
					mainFrame.myWin.btnPanel.responseResult(jsonObject);

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

		} else {
			mainFrame.myWin.btnPanel.responseResult(jsonObject);
		}
	}

	public void categorySelectAll(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64CategoryList = jsonObject.get("categoryList").toString();
			byte[] serializedCategoryList = Base64.getDecoder().decode(base64CategoryList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedCategoryList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					System.out.println("ois : " + ois);
					List<Category> categoryList = (List<Category>) ois.readObject();
					jsonObject.remove("categoryList");
					jsonObject.put("categoryList", categoryList);
					System.out.println("클라이언트쓰레드 / 리스트 : " + jsonObject.get("categoryList"));
					mainFrame.myWin.menuPanel.menuNavi.responseResult(jsonObject);

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

		} else {
			mainFrame.myWin.menuPanel.menuNavi.responseResult(jsonObject);
		}
	}

	public void stockSelectAllByAdmin(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64StockList = jsonObject.get("stockList").toString();

			byte[] serializedStockList = Base64.getDecoder().decode(base64StockList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedStockList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {

					// 역직렬화된 Member 객체를 읽어온다.
					List<Stock> stockList = (List<Stock>) ois.readObject();

					for (int i = 0; i < stockList.size(); i++) {
						BufferedImage img = ImageUpload.createImg(stockList.get(i).getMenu().getEncoderImg());
						String path = System.getProperty("user.dir");
						System.out.println(img);

						File file = new File(path + "/menu_res/" + stockList.get(i).getMenu().getFile_name());
						file.delete();

						if (file.exists()) {
							System.out.println(file.getName());
							System.out.println("파일이 이미 존재 합니다");
							ImageIO.write(img, "jpg", file);
						} else {
							ImageIO.write(img, "jpg", file);
						}

					}
					jsonObject.remove("stockList");
					jsonObject.put("stockList", stockList);
					// System.out.println("클라이언트쓰레드 / 리스트 : "+jsonObject.get("stockList"));
					mainFrame.myWin.menuPanel.menuContainer.responseResult(jsonObject);

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

		} else {
			mainFrame.myWin.menuPanel.menuContainer.responseResult(jsonObject);
		}
	}

	public void salesRegist(JSONObject jsonObject) {

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
		mainFrame.myWin.btnPanel.salesRegistResponseResult(jsonObject);
	}
}