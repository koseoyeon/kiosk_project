package com.kiosk.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.ImageUpload;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;

public class ResponseController {
	MainFrame mainFrame;

	public ResponseController(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
	}

	public void adminRegist(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.adminRegistDialog
				.responseAdminRegist((boolean) jsonObject.get("result"));

	}

	public void checkId(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.adminRegistDialog.responseCheckId((boolean) jsonObject.get("result"));

	}

	public void checkName(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.adminRegistDialog.responseCheckName((boolean) jsonObject.get("result"));

	}

	public void adminDelete(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.responseAdminDelete(jsonObject);

	}

	public void adminEdit(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.responseAdminEdit(jsonObject);
	}

	public void deviceCheckByDeviceName(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.responseDeviceCheckByDeviceName(jsonObject);
	}

	public void deviceEdit(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.devicePanel.responseDeviceEdit(jsonObject);
	}

	public void deviceDelete(JSONObject jsonObject) {
		mainFrame.container.store.storeAccount.devicePanel.responseDeviceDelete(jsonObject);
	}

	public void menuRegist(JSONObject jsonObject) {
		mainFrame.container.menuContainer.menuInfo.responseMenuRegist(jsonObject);
	}

	public void deviceRegist(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(mainFrame, "디바이스 등록이 되었습니다.");
			mainFrame.container.store.storeAccount.devicePanel.deviceSelectAll();
		} else {
			JOptionPane.showMessageDialog(mainFrame, "디바이스 등록에 실패하였습니다.");
		}
	}

	public void Login(JSONObject jsonObject) {
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
					System.out.println(jsonObject.toString());
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

	public void adminSelectAll(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64AdminList = jsonObject.get("adminList").toString();
			byte[] serializedAdminList = Base64.getDecoder().decode(base64AdminList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedAdminList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					System.out.println("ois : " + ois);
					List<Admin> adminList = (List<Admin>) ois.readObject();
					jsonObject.remove("adminList");
					jsonObject.put("adminList", adminList);
					System.out.println("클라이언트쓰레드 / 리스트 : " + jsonObject.get("adminList"));
					mainFrame.container.store.storeTable.responseResult(jsonObject);

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

	public void deviceSelectAll(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64DeviceList = jsonObject.get("deviceList").toString();
			byte[] serializedDeviceList = Base64.getDecoder().decode(base64DeviceList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDeviceList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					System.out.println("ois : " + ois);
					List<Device> deviceList = (List<Device>) ois.readObject();
					jsonObject.remove("deviceList");
					jsonObject.put("deviceList", deviceList);
					System.out.println("클라이언트쓰레드 / 리스트 : " + jsonObject.get("deviceList"));
					mainFrame.container.store.storeAccount.devicePanel.responseResult(jsonObject);

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
			mainFrame.container.store.storeAccount.devicePanel.responseResult(jsonObject);
		}
	}

	// ===================상품관리=========================
	public void categoryRegist(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			JOptionPane.showMessageDialog(mainFrame, "카테고리 등록이 되었습니다.");

		} else {
			JOptionPane.showMessageDialog(mainFrame, "카테고리 등록에 실패하였습니다.");
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
					mainFrame.container.menuContainer.menuInfo.categoryPanel.responseResult(jsonObject);
					mainFrame.container.menuContainer.menuInfo.setChoice(categoryList);

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
			mainFrame.container.menuContainer.menuInfo.categoryPanel.responseResult(jsonObject);
		}
	}

	public void menuSelectAll(JSONObject jsonObject) {
		System.out.println("//////////////////////////////menuSelectAll메서드 접근");
		if ((boolean) jsonObject.get("result")) {
			String base64MenuList = jsonObject.get("menuList").toString();
			byte[] serializedMenuList = Base64.getDecoder().decode(base64MenuList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMenuList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					List<Menu> menuList = (List<Menu>) ois.readObject();
					for (int i = 0; i < menuList.size(); i++) {
						try {
							BufferedImage img = ImageUpload.createImg(menuList.get(i).getEncoderImg());
							String path = System.getProperty("user.dir");
							System.out.println(img);

							File file = new File(path + "/menu_res/" + menuList.get(i).getFile_name());
							file.delete();

							if (file.exists()) {
								System.out.println(file.getName());
								System.out.println("파일이 이미 존재 합니다");
								ImageIO.write(img, "jpg", file);
							} else {
								ImageIO.write(img, "jpg", file);
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					jsonObject.remove("menuList");
					System.out.println("클라이언트측 컨트롤러 메뉴 리스트 사이즈 : " + menuList.size());
					jsonObject.put("menuList", menuList);

					mainFrame.container.menuContainer.menuTable.responseMenuSelectAll(jsonObject);

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
	}

	// =================매출관리=======================

	public void salesSelectAll(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64OrderSummaryList = jsonObject.get("orderSummaryList").toString();
			byte[] serializedOrderSummaryList = Base64.getDecoder().decode(base64OrderSummaryList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderSummaryList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					List<OrderSummary> orderSummaryList = (List<OrderSummary>) ois.readObject();
					jsonObject.remove("orderSummaryList");
					jsonObject.put("orderSummaryList", orderSummaryList);
					// System.out.println("클라이언트쓰레드 / 리스트 : "+jsonObject.get("orderSummaryList"));
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

		} else {
			mainFrame.container.sales.responseResult(jsonObject);
		}
	}

	public void salesSelectAllByDate(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64OrderSummaryList = jsonObject.get("orderSummaryList").toString();
			byte[] serializedOrderSummaryList = Base64.getDecoder().decode(base64OrderSummaryList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderSummaryList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					List<OrderSummary> orderSummaryList = (List<OrderSummary>) ois.readObject();
					jsonObject.remove("orderSummaryList");
					jsonObject.put("orderSummaryList", orderSummaryList);
					// System.out.println("클라이언트쓰레드 / 리스트 : "+jsonObject.get("orderSummaryList"));
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

		} else {
			mainFrame.container.sales.responseSalesSelectAllByDate(jsonObject);
		}
	}

	public void orderDetailSelectAllByOrderSummary(JSONObject jsonObject) {
		if ((boolean) jsonObject.get("result")) {
			String base64OrderDetailList = jsonObject.get("orderDetailList").toString();
			byte[] serializedOrderDetailList = Base64.getDecoder().decode(base64OrderDetailList);

			try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderDetailList)) {
				try (ObjectInputStream ois = new ObjectInputStream(bais)) {
					// 역직렬화된 Member 객체를 읽어온다.
					List<OrderDetail> orderDetailList = (List<OrderDetail>) ois.readObject();
					jsonObject.remove("orderDetailList");
					jsonObject.put("orderDetailList", orderDetailList);
					System.out.println("클라이언트쓰레드 / 리스트 : " + jsonObject.get("orderDetailList"));
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

		} else {
			mainFrame.container.sales.orderDetailPanel.responseResult(jsonObject);
		}
	}

	public void categoryEdit(JSONObject jsonObject) {
		mainFrame.container.menuContainer.menuInfo.categoryPanel.responseCategoryEdit(jsonObject);
	}

	public void categoryDelete(JSONObject jsonObject) {
		mainFrame.container.menuContainer.menuInfo.categoryPanel.responseCategoryDelete(jsonObject);
	}

	public void menuEdit(JSONObject jsonObject) {
		mainFrame.container.menuContainer.menuInfo.responseMenuEdit(jsonObject);
	}

	public void menuDelete(JSONObject jsonObject) {
		mainFrame.container.menuContainer.menuInfo.responseMenuDelete(jsonObject);
	}
}