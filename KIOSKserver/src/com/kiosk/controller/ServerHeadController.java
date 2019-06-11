package com.kiosk.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.kiosk.commons.ImageUpload;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.service.AdminService;
import com.kiosk.model.service.CategoryService;
import com.kiosk.model.service.DeviceService;
import com.kiosk.model.service.MenuService;
import com.kiosk.model.service.OrderDetailService;
import com.kiosk.model.service.OrderSummaryService;

public class ServerHeadController {

	@Autowired
	@Qualifier("adminServiceImpl")
	private AdminService adminService;

	@Autowired
	@Qualifier("deviceServiceImpl")
	private DeviceService deviceService;

	@Autowired
	@Qualifier("categoryServiceImpl")
	private CategoryService categoryService;

	@Autowired
	@Qualifier("menuServiceImpl")
	private MenuService menuService;

	@Autowired
	@Qualifier("orderSummaryServiceImpl")
	private OrderSummaryService orderSummaryService;

	@Autowired
	@Qualifier("orderDetailServiceImpl")
	private OrderDetailService orderDetailService;

//===========adminRegist=======================
	public JSONObject adminRegist(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject = null;
		// System.out.println("base64Admin : " + obj.get("admin").toString());
		String base64Admin = obj.get("admin").toString();
		byte[] serializedMember = Base64.getDecoder().decode(base64Admin);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				// 역직렬화된 Member 객체를 읽어온다.
				System.out.println("ois : " + ois);
				Admin admin = (Admin) ois.readObject();
				System.out.println("adminService : " + adminService);
				try {
					adminService.insert(admin);
					flag = true;

				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "adminRegist");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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

	// ===========admin삭제========================
	public JSONObject adminDelete(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject = null;
		System.out.println("여기는 서버 컨트롤러 : " + obj.get("admin_id"));
		// int admin_id=Integer.parseInt((String) obj.get("admin_id"));
		try {
			// adminService.delete((int)obj.get("admin_id"));
			adminService.delete(Integer.parseInt((String) obj.get("admin_id")));
			flag = true;
			jsonObject = new JSONObject();
			jsonObject.put("responseType", "adminDelete");
			jsonObject.put("result", flag);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
			jsonObject = new JSONObject();
			jsonObject.put("responseType", "adminDelete");
			jsonObject.put("result", flag);
		}
		return jsonObject;
	}

//=========login============================
	public JSONObject headLogin(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject = null;
		Admin responseAdmin = null;
		String base64Admin = obj.get("admin").toString();
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
								jsonObject.put("responseType", "login");
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
						jsonObject.put("responseType", "login");
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

	// =============checkId==================
	public JSONObject checkId(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject = null;
		// System.out.println("base64Admin : " + obj.get("admin").toString());
		String base64Admin = obj.get("admin").toString();
		byte[] serializedMember = Base64.getDecoder().decode(base64Admin);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {

				Admin admin = (Admin) ois.readObject();

				try {
					List<Admin> admins = adminService.checkId(admin);
					if (admins.size() == 0) {
						flag = true;
					} else {
						flag = false;
					}

				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "checkId");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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

	// =============checkName==================
	public JSONObject checkName(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject = null;
		// System.out.println("base64Admin : " + obj.get("admin").toString());
		String base64Admin = obj.get("admin").toString();
		byte[] serializedMember = Base64.getDecoder().decode(base64Admin);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {

				Admin admin = (Admin) ois.readObject();

				try {
					List<Admin> admins = adminService.checkName(admin);
					if (admins.size() == 0) {
						flag = true;
					} else {
						flag = false;
					}

				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "checkName");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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

	// ============deviceCheckByDeviceName====================
	public JSONObject deviceCheckByDeviceName(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject = null;
		String device_name = obj.get("device_name").toString();
		try {
			Device device = deviceService.selectByName(device_name);
			if (device == null) {
				flag = true;
			} else {
				flag = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		jsonObject = new JSONObject();
		jsonObject.put("responseType", "deviceCheckByDeviceName");
		jsonObject.put("result", flag);
		return jsonObject;
	}

	// =============deviceRegist==================
	public JSONObject deviceRegist(JSONObject obj) {
		boolean flag = false;
		JSONObject jsonObject = null;
		// System.out.println("base64Admin : " + obj.get("admin").toString());
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				// 역직렬화된 Member 객체를 읽어온다.
				Device device = (Device) ois.readObject();
				// System.out.println(device.getAdmin().getName());
				try {

					deviceService.insert(device);
					System.out.println("서버deviceRegist 성공");
					flag = true;
				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "deviceRegist");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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

	// ============deviceDelete====================
	public JSONObject deviceDelete(JSONObject obj) {
		boolean flag = false;
		JSONObject jsonObject = null;
		String device_id = obj.get("device_id").toString();
		try {
			deviceService.delete(Integer.parseInt(device_id));
			flag = true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		jsonObject = new JSONObject();
		jsonObject.put("responseType", "deviceDelete");
		jsonObject.put("result", flag);
		return jsonObject;
	}

	// ================deviceEdit==================
	public JSONObject deviceEdit(JSONObject obj) {
		boolean flag = false;
		JSONObject jsonObject = null;
		// System.out.println("base64Admin : " + obj.get("admin").toString());
		String base64Device = obj.get("device").toString();
		byte[] serializedDevice = Base64.getDecoder().decode(base64Device);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedDevice)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				// 역직렬화된 Member 객체를 읽어온다.
				Device device = (Device) ois.readObject();
				try {

					deviceService.update(device);
					System.out.println("서버deviceEdit 성공");
					flag = true;
				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "deviceEdit");
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
	// ================deviceSelectAll================

	public JSONObject deviceSelectAll(JSONObject obj) {
		boolean flag = false;
		byte[] returnDeviceList = null;
		JSONObject jsonObject = null;
		List<Device> deviceList = new ArrayList<Device>();
		try {
			deviceList = deviceService.selectAll();
			flag = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(deviceList);
				returnDeviceList = baos.toByteArray();
				String encoderDeviceList = Base64.getEncoder().encodeToString(returnDeviceList);
				jsonObject = new JSONObject();
				jsonObject.put("responseType", "deviceSelectAll");
				jsonObject.put("deviceList", encoderDeviceList);
				jsonObject.put("result", flag);

				System.out.println("서버측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	// =============가맹점 관리=================

	public JSONObject adminSelectAll() {
		boolean flag = false;
		byte[] returnAdminList = null;
		JSONObject jsonObject = null;
		List<Admin> adminList = new ArrayList<Admin>();
		try {
			adminList = adminService.selectAll();
			System.out.println("서버 컨트롤러 : " + adminList.size());
			flag = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(adminList);
				returnAdminList = baos.toByteArray();
				String encoderAdminList = Base64.getEncoder().encodeToString(returnAdminList);
				jsonObject = new JSONObject();
				jsonObject.put("responseType", "adminSelectAll");
				jsonObject.put("adminList", encoderAdminList);
				jsonObject.put("result", flag);

				System.out.println("서버측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	// ================adminEdit==================
	public JSONObject adminEdit(JSONObject obj) {

		boolean flag = false;
		JSONObject jsonObject = null;

		String base64Admin = obj.get("admin").toString();
		byte[] serializedMember = Base64.getDecoder().decode(base64Admin);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMember)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {

				Admin admin = (Admin) ois.readObject();
				try {
					List<Admin> admins = adminService.checkName(admin);
					List<Admin> admins2 = adminService.checkId(admin);
					if (admins.size() == 0 && admins2.size() == 0) {
						adminService.update(admin);
						flag = true;
					}
				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "adminEdit");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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
	// ================상품관리==================

	public JSONObject categoryRegist(JSONObject obj) {
		boolean flag;
		JSONObject jsonObject = null;
		String base64Category = obj.get("category").toString();
		byte[] serializedCategory = Base64.getDecoder().decode(base64Category);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedCategory)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				Category category = (Category) ois.readObject();
				try {
					categoryService.insert(category);
					flag = true;

				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "categoryRegist");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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

	public JSONObject categorySelectAll(JSONObject obj) {
		boolean flag = false;
		byte[] returnCategoryList = null;
		JSONObject jsonObject = null;
		List<Category> categoryList = new ArrayList<Category>();
		try {
			categoryList = categoryService.selectAll();
			flag = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(categoryList);
				returnCategoryList = baos.toByteArray();
				String encoderCategoryList = Base64.getEncoder().encodeToString(returnCategoryList);
				jsonObject = new JSONObject();
				jsonObject.put("responseType", "categorySelectAll");
				jsonObject.put("categoryList", encoderCategoryList);
				jsonObject.put("result", flag);

				System.out.println("서버측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	// ===================매출 관리======================
	public JSONObject salesSelectAll(JSONObject obj) {
		boolean flag = false;
		byte[] returnOrderSummaryList = null;
		JSONObject jsonObject = null;
		List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();
		try {
			orderSummaryList = orderSummaryService.selectAll();
			System.out.println("여기는 서버 컨트롤러 : " + orderSummaryList.size());
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
				jsonObject.put("responseType", "salesSelectAll");
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
		int order_summary_id = Integer.parseInt((String) obj.get("order_summary_id"));
		try {
			orderDetailList = orderDetailService.selectAllByOrderSummary(order_summary_id);
			System.out.println("여기는 서버 어드민 컨트롤러 : " + orderDetailList.size());
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

	// ================MenuRegist====================
	public JSONObject menuRegist(JSONObject obj) {

		boolean flag = false;
		JSONObject jsonObject = null;
		System.out.println("서버menuRegist  : 반응");
		String base64Menu = obj.get("menu").toString();
		String base64Image = obj.get("image").toString();
		byte[] serializedMenu = Base64.getDecoder().decode(base64Menu);
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMenu)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {
				Menu menu = (Menu) ois.readObject();

				ImageUpload.decoding(base64Image, menu.getFile_name());

				// System.out.println(device.getAdmin().getName());
				try {
					menuService.insert(menu);
					flag = true;
				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "menuRegist");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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

	// ================menuSelectAll===================
	public JSONObject menuSelectAll(JSONObject obj) {
		System.out.println("서버 컨트롤러 menuSelectAll 접근");
		boolean flag = false;
		byte[] returnMenuList = null;
		JSONObject jsonObject = null;
		List<Menu> menuList = null;
		String path = System.getProperty("user.dir");

		String realPath = path + "/menu_res/";
		try {
			menuList = menuService.selectAll();
			for (int i = 0; i < menuList.size(); i++) {
				String file_name = menuList.get(i).getFile_name();
				File file = new File(realPath + file_name);
				menuList.get(i).setEncoderImg(ImageUpload.encoding(file));
			}
			flag = true;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			flag = false;
		}

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
				oos.writeObject(menuList);
				returnMenuList = baos.toByteArray();
				String encoderMenuList = Base64.getEncoder().encodeToString(returnMenuList);
				jsonObject = new JSONObject();
				jsonObject.put("responseType", "menuSelectAll");
				jsonObject.put("menuList", encoderMenuList);
				jsonObject.put("result", flag);

				System.out.println("서버측 요청 완료");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}

	// =======================categoryEdit=======================

	public JSONObject categoryEdit(JSONObject obj) {
		boolean flag = false;
		JSONObject jsonObject = null;

		String base64Category = obj.get("category").toString();
		byte[] serializedCategory = Base64.getDecoder().decode(base64Category);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedCategory)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {

				Category category = (Category) ois.readObject();

				try {
					categoryService.update(category);
					flag = true;

				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "categoryEdit");
				jsonObject.put("result", flag);
				// ServerThread.send(jsonObject.toString());

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

	// =======================categoryDelete=======================

	public JSONObject categoryDelete(JSONObject obj) {
		boolean flag = false;
		JSONObject jsonObject = null;
		String category_id = obj.get("category_id").toString();
		try {
			categoryService.delete(Integer.parseInt(category_id));
			flag = true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		jsonObject = new JSONObject();
		jsonObject.put("responseType", "categoryDelete");
		jsonObject.put("result", flag);
		return jsonObject;
	}

	// =======================salesSelectAllByDate==================
	public JSONObject salesSelectAllByDate(JSONObject obj) {
		OrderSummary orderSummary = null;
		boolean flag = false;
		byte[] returnOrderSummaryList = null;
		JSONObject jsonObject = null;
		List<OrderSummary> orderSummaryList = null;
		String base64OrderSummary = obj.get("orderSummary").toString();
		byte[] serializedOrderSummary = Base64.getDecoder().decode(base64OrderSummary);
		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedOrderSummary)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {

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

		try {
			orderSummaryList = orderSummaryService.salesSelectAllByDate(orderSummary);

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
				jsonObject.put("responseType", "salesSelectAllByDate");
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

	// ======================menuEdit==============
	public JSONObject menuEdit(JSONObject obj) {
		boolean flag = false;
		JSONObject jsonObject = null;

		String base64Menu = obj.get("menu").toString();
		byte[] serializedMenu = Base64.getDecoder().decode(base64Menu);

		try (ByteArrayInputStream bais = new ByteArrayInputStream(serializedMenu)) {
			try (ObjectInputStream ois = new ObjectInputStream(bais)) {

				Menu menu = (Menu) ois.readObject();

				try {
					menuService.update(menu);
					flag = true;

				} catch (RuntimeException e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
					flag = false;
				}

				jsonObject = new JSONObject();
				jsonObject.put("responseType", "menuEdit");
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

	// ======================menuDelete==============
	public JSONObject menuDelete(JSONObject obj) {
		boolean flag = false;
		JSONObject jsonObject = null;
		String menu_id = obj.get("menu_id").toString();
		try {
			menuService.delete(Integer.parseInt(menu_id));
			flag = true;
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			flag = false;
		}
		jsonObject = new JSONObject();
		jsonObject.put("responseType", "menuDelete");
		jsonObject.put("result", flag);
		return jsonObject;
	}

}