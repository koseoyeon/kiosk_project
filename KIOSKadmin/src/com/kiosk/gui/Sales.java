package com.kiosk.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.xml.stream.events.StartDocument;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.CurrentDay;
import com.kiosk.commons.TableDataTrans;
import com.kiosk.gui.common.TableModel;
import com.kiosk.model.domain.OrderDetail;
import com.kiosk.model.domain.OrderSummary;
import com.kiosk.model.domain.Stock;

public class Sales extends JPanel {
	MainFrame mainFrame;

	JLabel lb_info;
	JTable table;
	JScrollPane scroll;
	TableModel salesTableModel;
	JPanel p_btn;
	JPanel p_btn_allSales;
	JPanel p_blank;
	JButton btn_allSales;
	JButton btn_excel;
	OrderSummary orderSummary;
	public OrderDetailPanel orderDetailPanel;
	public TableDataTrans tabeDataTrans = new TableDataTrans();
	List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();
	boolean flag = false;
	
	Thread thread = new Thread() {
		@Override
		public void run() {
			while(flag) {
				try {
					System.out.println("run메서드 호출");
					Thread.sleep(10000);
					salesSelectAll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	};
	
	String[] columnName = { "No", "장치 이름", "주문 번호", "주문 시간", "결제 수단", "결제 금액" };

	public Sales(MainFrame mainFrame) {

		// TODO Auto-generated constructor stub
		this.mainFrame = mainFrame;

		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(10);
		setLayout(flowLayout);

		lb_info = new JLabel("※ 주문 건을 클릭시에 상세 매출을 확인할 수 있습니다.");
		lb_info.setPreferredSize(new Dimension(1000, 35));
		lb_info.setForeground(Color.RED);
		lb_info.setFont(new Font(null, Font.BOLD, 14));
		orderSummary = new OrderSummary();
		
		btn_allSales = new JButton("실시간 매출 확인");
		btn_allSales.setHorizontalAlignment(SwingConstants.RIGHT);		
		
		p_blank = new JPanel();
		p_blank.setPreferredSize(new Dimension(700, 40));
		p_btn_allSales = new JPanel();
		p_btn_allSales.setPreferredSize(new Dimension(1000, 40));

		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(1000, 580));
		scroll = new JScrollPane(table);

		salesTableModel = new TableModel();

		p_btn = new JPanel();
		p_btn.setPreferredSize(new Dimension(1000, 50));
		btn_excel = new JButton("엑셀로 변환");
		btn_excel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("excel 버튼 클릭");
				// TODO Auto-generated method stub

				try {
					File filePath = new File("C:/Users/user/Documents/Sales");
					if (!filePath.exists()) {
						filePath.mkdirs();
						File excelFile = new File("C:/Users/user/Documents/Sales/" + CurrentDay.getCurrentDate()
								+ CurrentDay.getCurrnetTime() + CurrentDay.getCurrnetTime()
								+ mainFrame.getAdmin().getName() + " 요약.xls");
						tabeDataTrans.TabeDataExportToExcel(table, excelFile);
					} else {
						File excelFile = new File("C:/Users/user/Documents/Sales/" + CurrentDay.getCurrentDate()
								+ CurrentDay.getCurrnetTime() + CurrentDay.getCurrnetTime()
								+ mainFrame.getAdmin().getName() + " 요약.xls");
						tabeDataTrans.TabeDataExportToExcel(table, excelFile);
					}
					JOptionPane.showMessageDialog(mainFrame.container.sales, "엑셀파일로 변환이 되었습니다.\n'내 문서'폴더를 확인해주세요.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(mainFrame.container.sales, "엑셀파일로 변환 실패");
				}

			}
		});
		
		p_btn_allSales.add(p_blank);
		p_btn_allSales.add(btn_allSales);
		
		add(lb_info);
		p_btn.add(btn_excel);
		add(p_btn_allSales);
		add(scroll);
		add(p_btn);

		renderMenuList();

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				orderSummary = orderSummaryList.get(table.getSelectedRow());
				orderDetailPanel = new OrderDetailPanel(mainFrame);
				orderDetailPanel.setOrder_summary_id(orderSummary.getOrder_summary_id());
				orderDetailPanel.orderDetailSelectAllByOrderSummary();
				orderDetailPanel.renderOrderDetailList();
				orderDetailPanel.setVisible(true);
			}
		});
		
		btn_allSales.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = true;
				thread.start();
			}
		});

		// setBackground(Color.pink);
		setPreferredSize(new Dimension(1200, 800));
		setVisible(false);
	}

	public void renderMenuList() {
		System.out.println("renderList 메서드 접근");
		setModelData();
		table.setModel(salesTableModel);
		table.repaint();
		table.updateUI();
	}

	public void responseResult(JSONObject jsonObject) {
		this.orderSummaryList = (List<OrderSummary>) jsonObject.get("orderSummaryList");
		setModelData();
	}

	public void setModelData() {
		int total = this.orderSummaryList.size();
		System.out.println("setModelData() 메서드 접근 , 사이즈 : " + total);
		String[][] data = new String[total][6];
		for (int i = 0; i < total; i++) {
			OrderSummary orderSummary = orderSummaryList.get(i);
			data[i][0] = Integer.toString(i + 1);
			data[i][1] = orderSummary.getDevice().getDevice_name();
			data[i][2] = Integer.toString(orderSummary.getOrder_num());
			data[i][3] = orderSummary.getOrder_date();
			data[i][4] = orderSummary.getPayType().getPay_method();
			data[i][5] = Integer.toString(orderSummary.getOrder_price());

		}
		salesTableModel.columnName = columnName;
		salesTableModel.data = data;
		table.setModel(salesTableModel);
		table.repaint();
		table.updateUI();
	}

	public void salesSelectAll() {
		mainFrame.requestController.salesSelectAllByAdmin(mainFrame.getAdmin().getAdmin_id());
	}

	/*
	 * @Override public void run() { try { System.out.println("run메서드 호출");
	 * salesSelectAll(); Thread.sleep(30000); } catch (InterruptedException e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); } }
	 */
}
