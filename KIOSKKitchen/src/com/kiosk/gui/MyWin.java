package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.Stock;

public class MyWin extends JPanel {
	MainFrame mainFrame;
	Receipt receipt;


	public MyWin(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;
		receipt = new Receipt(mainFrame);
		

	

		setPreferredSize(new Dimension(600, 900));
		setBackground(new Color(250, 224, 212));
		setVisible(true);
	}
	public void salesRegistResponseResult(JSONObject jsonObject) {
		if((boolean) jsonObject.get("result")) {
			List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
			orderDetailList = (List<OrderDetail>) jsonObject.get("orderDetailList");
			System.out.println("여기는 response메서드 // orderDetail 사이즈 : "+orderDetailList.size());
			mainFrame.myWin.receipt.setVisible(false);
			mainFrame.myWin.receipt.fillReceipt(orderDetailList);
			mainFrame.myWin.receipt.setVisible(true);

		}else {
			JOptionPane.showMessageDialog(mainFrame, "결제 실패\n매장 직원에게 문의해주세요.");
		}
	}
}
