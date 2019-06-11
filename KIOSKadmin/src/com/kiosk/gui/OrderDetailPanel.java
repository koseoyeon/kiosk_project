package com.kiosk.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.CurrentDay;
import com.kiosk.commons.TableDataTrans;
import com.kiosk.gui.common.TableModel;
import com.kiosk.model.domain.Device;
import com.kiosk.model.domain.OrderDetail;

public class OrderDetailPanel extends JDialog {
	MainFrame mainFrame;
	JTable table;
	JScrollPane scroll;
	TableModel orderDetailTableModel;
	JButton btn_excel;
	JPanel p;
	JPanel p_btn;
	public TableDataTrans tabeDataTrans = new TableDataTrans(); 
	int order_summary_id;
	
	public void setOrder_summary_id(int order_summary_id) {
		this.order_summary_id = order_summary_id;
	}
	
	public int getOrder_summary_id() {
		return order_summary_id;
	}

	List<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

	public void setDeviceList(List<OrderDetail> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public List<OrderDetail> getDeviceList() {
		return orderDetailList;
	}

	String[] columnName = { "No", "영수증 번호", "주문 날짜", "메뉴", "수량", "금액" };

	public OrderDetailPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		setLayout(new FlowLayout());
		//orderDetailSelectAllByOrderSummary();
		
		p = new JPanel();
		p.setPreferredSize(new Dimension(740, 420));

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(720, 380));
		scroll = new JScrollPane(table);

		p_btn = new JPanel();
		p_btn.setPreferredSize(new Dimension(760, 50));
		btn_excel = new JButton("엑셀로 변환");
		btn_excel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("excel 버튼 클릭");
				// TODO Auto-generated method stub
				
				try {
					File filePath = new File("C:/Users/user/Documents/Sales");
					if(!filePath.exists()) { 
						filePath.mkdirs();
						File excelFile = new File("C:/Users/user/Documents/Sales/"+CurrentDay.getCurrentDate()+CurrentDay.getCurrnetTime()+CurrentDay.getCurrnetTime()+mainFrame.getAdmin().getName()+" 상세.xls");
						tabeDataTrans.TabeDataExportToExcel(table, excelFile);
					}else {
						File excelFile = new File("C:/Users/user/Documents/Sales/"+CurrentDay.getCurrentDate()+CurrentDay.getCurrnetTime()+CurrentDay.getCurrnetTime()+mainFrame.getAdmin().getName()+" 상세.xls");
						tabeDataTrans.TabeDataExportToExcel(table, excelFile);
					}
					JOptionPane.showMessageDialog(mainFrame.container.sales.orderDetailPanel, "엑셀파일로 변환이 되었습니다.\n'내 문서'폴더를 확인해주세요.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(mainFrame.container.sales.orderDetailPanel, "엑셀파일로 변환 실패");
				}
				
			}
		});
		p.add(scroll);

		p_btn.add(btn_excel);

		add(p);
		add(p_btn);

		orderDetailTableModel = new TableModel();

		setTitle("주문 내역 상세 보기");
		this.setSize(780, 520);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setVisible(false);

	}

	public void renderOrderDetailList() {
		System.out.println("renderList 메서드 접근");
		setModelData();
		table.setModel(orderDetailTableModel);
		table.repaint();
		table.updateUI();
	}

	public void responseResult(JSONObject jsonObject) {
		System.out.println("orderDetailList : " + jsonObject.get("orderDetailList"));
		this.orderDetailList = (List<OrderDetail>) jsonObject.get("orderDetailList");
		// mainFrame.container.store.storeAccount.setAccount(adminList);
		for (int i = 0; i < orderDetailList.size(); i++) {
			System.out.println("디바이스 리스트에 있는 이름들 : " + orderDetailList.get(i).getOrder_detail_id());
		}
		setModelData();
	}

	public void setModelData() {
		int total = this.orderDetailList.size();
		System.out.println("setModelData() 메서드 접근 , 사이즈 : " + total);
		String[][] data = new String[total][6];
		for (int i = 0; i < total; i++) {
			OrderDetail orderDetail = orderDetailList.get(i);
			System.out.println("여기는 뽀문 : " + orderDetail.getOrder_detail_id());
			data[i][0] = Integer.toString(i + 1);
			data[i][1] = Integer.toString(orderDetail.getOrderSummary().getOrder_num());
			data[i][2] = orderDetail.getOrderSummary().getOrder_date().substring(0, 10);
			data[i][3] = orderDetail.getMenu().getMenu_name();
			data[i][4] = Integer.toString(orderDetail.getEa());
			data[i][5] = Integer.toString(orderDetail.getMenu().getPrice() * orderDetail.getEa());
		}
		orderDetailTableModel.columnName = columnName;
		orderDetailTableModel.data = data;
		table.setModel(orderDetailTableModel);
		table.repaint();
		table.updateUI();
	}

	public void orderDetailSelectAllByOrderSummary() {
		mainFrame.requestController.orderDetailSelectAllByOrderSummary(getOrder_summary_id());
	}

}
