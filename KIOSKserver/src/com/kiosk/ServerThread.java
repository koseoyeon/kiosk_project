package com.kiosk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Vector;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kiosk.controller.ServerAdminController;
import com.kiosk.controller.ServerHeadController;
import com.kiosk.controller.ServerKitchenController;
import com.kiosk.controller.ServerUserController;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.OrderDetail;

public class ServerThread extends Thread {

	KioskServer kioskServer;

	Socket client; // 이 서버로 들어올 클라이언트

	BufferedReader buffr;
	BufferedWriter buffw;

	String ip;
	Device device;
	
	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	boolean flag = true; // while문 제어하기 위한 장치

	@Autowired
	ServerHeadController serverHeadController;

	@Autowired
	ServerUserController serverUserController;

	@Autowired
	ServerAdminController serverAdminController;
	@Autowired
	ServerKitchenController serverKitchenController;

	public ServerThread(KioskServer kioskServer, Socket client, String ip) {

		ClassPathXmlApplicationContext context = null;
		String path = "com/kiosk/spring/context/db-context.xml";
		context = new ClassPathXmlApplicationContext(path);
		serverHeadController = (ServerHeadController) context.getBean("serverHeadController");
		serverUserController = (ServerUserController) context.getBean("serverUserController");
		serverAdminController = (ServerAdminController) context.getBean("serverAdminController");
		serverKitchenController = (ServerKitchenController) context.getBean("serverKitchenController");

		this.kioskServer = kioskServer;
		this.client = client;
		this.ip = ip;

		/* kioskController = new KioskController(); */
		System.out.println("서버측 : " + client);
		System.out.println("서버 스레드 컨트롤러 : " + serverHeadController);
		try {
			buffr = new BufferedReader(new InputStreamReader(client.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 계속 듣기
	public void listen() {
		System.out.println("ServerThread 듣고있는 중");
		String msg = null;
		try {
			msg = buffr.readLine();
			System.out.println("ServerThread 曰 : Client로부터 요청 메세지 : " + msg);
			kioskServer.area.append(msg + "\n");
			kioskServer.bar.setValue(kioskServer.bar.getMaximum());

			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(msg);
			// =================Head=====================
			if (obj.get("requestType").equals("adminRegist")) {
				JSONObject jsonObject = serverHeadController.adminRegist(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("checkName")) {
				JSONObject jsonObject = serverHeadController.checkName(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("checkId")) {
				JSONObject jsonObject = serverHeadController.checkId(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("login")) {
				JSONObject jsonObject = serverHeadController.headLogin(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("adminSelectAll")) {
				JSONObject jsonObject = serverHeadController.adminSelectAll();
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("categoryRegist")) {
				JSONObject jsonObject = serverHeadController.categoryRegist(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("categorySelectAll")) {
				JSONObject jsonObject = serverHeadController.categorySelectAll(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("adminDelete")) {
				System.out.println(obj);
				JSONObject jsonObject = serverHeadController.adminDelete(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("adminEdit")) {
				JSONObject jsonObject = serverHeadController.adminEdit(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("deviceRegist")) {
				JSONObject jsonObject = serverHeadController.deviceRegist(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("deviceSelectAll")) {
				JSONObject jsonObject = serverHeadController.deviceSelectAll(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("deviceCheckByDeviceName")) {
				JSONObject jsonObject = serverHeadController.deviceCheckByDeviceName(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("deviceEdit")) {
				JSONObject jsonObject = serverHeadController.deviceEdit(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("deviceDelete")) {
				JSONObject jsonObject = serverHeadController.deviceDelete(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("salesSelectAll")) {
				JSONObject jsonObject = serverHeadController.salesSelectAll(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("orderDetailSelectAllByOrderSummary")) {
				JSONObject jsonObject = serverHeadController.orderDetailSelectAllByOrderSummary(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("menuRegist")) {
				JSONObject jsonObject=serverHeadController.menuRegist(obj);
				send(jsonObject.toString());
			} else if(obj.get("requestType").equals("menuSelectAll")) {
				JSONObject jsonObject=serverHeadController.menuSelectAll(obj);
				send(jsonObject.toString());
			} else if(obj.get("requestType").equals("categoryEdit")) {
				JSONObject jsonObject=serverHeadController.categoryEdit(obj);
				send(jsonObject.toString());
			} else if(obj.get("requestType").equals("categoryDelete")) {
				JSONObject jsonObject=serverHeadController.categoryDelete(obj);
				send(jsonObject.toString());
			} else if(obj.get("requestType").equals("salesSelectAllByDate")) {
				JSONObject jsonObject=serverHeadController.salesSelectAllByDate(obj);
				send(jsonObject.toString());
			} else if(obj.get("requestType").equals("menuEdit")) {
				JSONObject jsonObject=serverHeadController.menuEdit(obj);
				send(jsonObject.toString());
			} else if(obj.get("requestType").equals("menuDelete")) {
				JSONObject jsonObject=serverHeadController.menuDelete(obj);
				send(jsonObject.toString());
			}

			// =================User=====================
			else if (obj.get("requestType").equals("deviceSelect")) {
				JSONObject jsonObject = new JSONObject();
				if (kioskServer.deviceList.size() == 0) {
					jsonObject = serverUserController.selectByNameListSize0(obj);
					Device device=(Device) jsonObject.get("checkDevice");
					this.setDevice(device);
					jsonObject.remove("checkDevice");
					if ((boolean) jsonObject.get("result")) {
						kioskServer.connectDevice(obj);
					}
					send(jsonObject.toString());
				} else if ((kioskServer.deviceList.size() != 0
						&& !kioskServer.checkDevice(obj))) {
					jsonObject = serverUserController.selectByName(obj);
					Device device=(Device) jsonObject.get("checkDevice");
					this.setDevice(device);
					jsonObject.remove("checkDevice");
					if ((boolean) jsonObject.get("result")) {
						kioskServer.connectDevice(obj);
					}
					send(jsonObject.toString());
				} else if (!(kioskServer.deviceList.size() == 0 && !(kioskServer.deviceList.size() != 0
						&& !kioskServer.checkDevice(obj)))) {

					jsonObject.put("responseType", "deviceSelect");
					jsonObject.put("msg", "2");
					jsonObject.put("result", false);
					send(jsonObject.toString());
				}
			} else if (obj.get("requestType").equals("disconnectDevice")) {
				for (int i = 0; i < kioskServer.deviceList.size(); i++) {
					if (obj.get("device_name").equals(kioskServer.deviceList.get(i))) {
						kioskServer.removeDevice(obj.get("device_name").toString());
					}
				}
			} else if (obj.get("requestType").equals("salesSelectAllByAdminAndDate")) {
				JSONObject jsonObject = serverUserController.salesSelectAllByAdminAndDate(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("salesRegist")) {
				JSONObject jsonObject = serverUserController.salesRegist(obj);
				List<OrderDetail> orderDetailList=(List<OrderDetail>) jsonObject.get("checkOrderDetailList");
				OrderDetail orderDetail = orderDetailList.get(0);
				Vector<ServerThread> serverThreadList=new Vector<ServerThread>();
				for(int i=0;i<kioskServer.list.size();i++) {
					ServerThread serverThread = kioskServer.list.get(i);
					if(serverThread.getDevice()!=null) {
						if(serverThread.getDevice().getDevice_id() ==(orderDetail.getOrderSummary().getDevice().getDevice_id())){
							serverThreadList.add(serverThread);
						}
					}
				}
				jsonObject.remove("checkOrderDetailList");
				System.out.println("serverThreadList사이즈"+serverThreadList.size());
				for(int i=0;i<serverThreadList.size();i++) {
					serverThreadList.get(i).send(jsonObject.toString());
				}
			}
			// =================Admin=====================
			else if (obj.get("requestType").equals("adminLogin")) {
				JSONObject jsonObject = serverAdminController.adminLogin(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("stockSelectAllByAdmin")) {
				JSONObject jsonObject = serverAdminController.stockSelectAllByAdmin(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("stockEdit")) {
				JSONObject jsonObject = serverAdminController.stockEdit(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("salesSelectAllByAdmin")) {
				JSONObject jsonObject = serverAdminController.salesSelectAllByAdmin(obj);
				send(jsonObject.toString());
			} else if (obj.get("requestType").equals("orderDetailSelectAllByOrderSummary")) {
				JSONObject jsonObject = serverAdminController.orderDetailSelectAllByOrderSummary(obj);
				send(jsonObject.toString());
			}
			//================Kitchen======================
			else if (obj.get("requestType").equals("kitchenDeviceSelect")) {
				JSONObject jsonObject = new JSONObject();
				if (kioskServer.kitchenDeviceList.size() == 0) {
					jsonObject = serverKitchenController.selectByNameListSize0(obj);
					Device device=(Device) jsonObject.get("checkDevice");
					this.setDevice(device);
					jsonObject.remove("checkDevice");
					if ((boolean) jsonObject.get("result")) {
						kioskServer.kitchenConnectDevice(obj);
					}
					send(jsonObject.toString());
				} else if ((kioskServer.kitchenDeviceList.size() != 0
						&& !kioskServer.kitchenCheckDevice(obj))) {
					jsonObject = serverKitchenController.selectByName(obj);
					Device device=(Device) jsonObject.get("checkDevice");
					this.setDevice(device);
					jsonObject.remove("checkDevice");
					if ((boolean) jsonObject.get("result")) {
						kioskServer.kitchenConnectDevice(obj);
					}
					send(jsonObject.toString());
				} else if (!(kioskServer.kitchenDeviceList.size() == 0 && !(kioskServer.kitchenDeviceList.size() != 0
						&& !kioskServer.kitchenCheckDevice(obj)))) {

					jsonObject.put("responseType", "kitchenDeviceSelect");
					jsonObject.put("msg", "2");
					jsonObject.put("result", false);
					send(jsonObject.toString());
				}
			} else if (obj.get("requestType").equals("kitchenDisconnectDevice")) {
				for (int i = 0; i < kioskServer.kitchenDeviceList.size(); i++) {
					if (obj.get("device_name").equals(kioskServer.kitchenDeviceList.get(i))) {
						kioskServer.kitchenRemoveDevice(obj.get("device_name").toString());
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// 서버에서 나간 ip 제거
			kioskServer.area.append(this.ip + "님 퇴장하셨습니다.\n");
			kioskServer.list.remove(this);
			kioskServer.area.append(kioskServer.list.size() + "명 접속중\n");
			flag = false; // while문 무한루프 멈추기
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	}

	// 계속 보내기
	public void send(String msg) {
		try {
			buffw.write(msg + "\n");
			buffw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (flag) {
			listen();
		}
	}
}
