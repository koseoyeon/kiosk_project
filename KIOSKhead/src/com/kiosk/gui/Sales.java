package com.kiosk.gui;

import java.awt.Choice;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.metal.MetalIconFactory.FolderIcon16;

import org.json.simple.JSONObject;

import com.kiosk.MainFrame;
import com.kiosk.commons.CurrentDay;
import com.kiosk.commons.TableDataTrans;
import com.kiosk.model.domain.OrderSummary;

public class Sales extends JPanel {
	MainFrame mainFrame;

	JLabel lb_info;
	JTable table;
	JScrollPane scroll;
	TableModel salesTableModel;
	JPanel p_date;
	JLabel lb_date;
	JLabel lb;
	Choice ch_year_start;
	Choice ch_month_start;
	Choice ch_day_start;
	Choice ch_year_end;
	Choice ch_month_end;
	Choice ch_day_end;
	JButton btn_find;
	JPanel p_blank;
	JPanel p_btn_allSales;
	JButton btn_allSales;
	JPanel p_btn;
	JButton btn_excel;
	JLabel lb_allSales;
	JTextField t_allSales;
	OrderSummary orderSummary;
	public OrderDetailPanel orderDetailPanel;
	TableDataTrans tabeDataTrans = new TableDataTrans();
	List<OrderSummary> orderSummaryList = new ArrayList<OrderSummary>();

	boolean flag = false;
	
	String[] columnName = { "No", "주문 번호", "주문 시간", "결제 수단", "결제 금액", "장치 이름", "가맹점" };

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
	
	public Sales(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		salesSelectAll();

		FlowLayout flowLayout = new FlowLayout();
		flowLayout.setHgap(0);
		flowLayout.setVgap(5);
		setLayout(flowLayout);

		lb_info = new JLabel("※ 주문 건을 클릭시에 상세 매출을 확인할 수 있습니다.");
		lb_info.setPreferredSize(new Dimension(1000, 60));
		lb_info.setForeground(Color.RED);
		lb_info.setFont(new Font(null, Font.BOLD, 14));
		orderSummary = new OrderSummary();

		p_date = new JPanel();
		p_date.setPreferredSize(new Dimension(1000, 50));
		lb_date = new JLabel("조회할 기간을 선택해 주세요 : ");
		lb_date.setFont(new Font(null, Font.BOLD, 14));

		ch_year_start = new Choice();
		ch_year_start.setPreferredSize(new Dimension(80, 30));
		ch_month_start = new Choice();
		ch_month_start.setPreferredSize(new Dimension(80, 30));
		ch_day_start = new Choice();
		ch_day_start.setPreferredSize(new Dimension(80, 30));
		
		lb = new JLabel(" ~ ");
		lb.setFont(new Font(null, Font.BOLD, 14));

		ch_year_end = new Choice();
		ch_year_end.setPreferredSize(new Dimension(80, 30));
		ch_month_end = new Choice();
		ch_month_end.setPreferredSize(new Dimension(80, 30));
		ch_day_end = new Choice();
		ch_day_end.setPreferredSize(new Dimension(80, 30));
		
		btn_find = new JButton("조회");
		
		btn_allSales = new JButton("실시간 매출 확인");
		btn_allSales.setHorizontalAlignment(SwingConstants.RIGHT);
		
		table = new JTable();
		table.setPreferredScrollableViewportSize(new Dimension(1000, 480));
		scroll = new JScrollPane(table);

		salesTableModel = new TableModel();

		p_btn = new JPanel();
		p_btn.setPreferredSize(new Dimension(800, 50));
		btn_excel = new JButton("엑셀로 변환");
		
		p_blank = new JPanel();
		p_blank.setPreferredSize(new Dimension(800, 50));
		p_btn_allSales = new JPanel();
		p_btn_allSales.setPreferredSize(new Dimension(1200, 50));
		
		lb_allSales = new JLabel("총 매출 : ");

		// 현재 년도 구하기
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
		LocalDate localDate = LocalDate.now();
		System.out.println("현재 년도..? : " + dtf.format(localDate));
		int nowYear = Integer.parseInt(dtf.format(localDate));

		for (int i = nowYear; i >= 1950; i--) {
			ch_year_start.add("" + i + "");
		}
		ch_year_start.select(nowYear+"");
		for (int i = 1; i <= 12; i++) {
			if(i<10) {
				ch_month_start.add("0" + i + "");
			}else {
				ch_month_start.add("" + i + "");
			}
		}
		ch_month_start.select(6);
		for (int i = 1; i <= 31; i++) {
			if(i<10) {
				ch_day_start.add("0" + i + "");
			}else {
				ch_day_start.add("" + i + "");
			}
		}
		ch_day_start.select(15);

		for (int i = nowYear; i >= 1950; i--) {
			ch_year_end.add("" + i + "");
		}
		ch_year_end.select(nowYear+"");
		for (int i = 1; i <= 12; i++) {
			if(i<10) {
				ch_month_end.add("0" + i + "");
			}else {
				ch_month_end.add("" + i + "");
			}
		}
		ch_month_end.select(6);
		for (int i = 1; i <= 31; i++) {
			if(i<10) {
				ch_day_end.add("0" + i + "");
			}else {
				ch_day_end.add("" + i + "");
			}
		}
		ch_day_end.select(15);

		p_date.add(lb_date);

		p_date.add(ch_year_start);
		p_date.add(ch_month_start);
		p_date.add(ch_day_start);
		p_date.add(lb);
		p_date.add(ch_year_end);
		p_date.add(ch_month_end);
		p_date.add(ch_day_end);
		p_date.add(btn_find);
		
		p_btn_allSales.add(p_blank);
		p_btn_allSales.add(btn_allSales);
		
		p_btn.add(btn_excel);

		add(lb_info);
		add(p_date);
		add(p_btn_allSales);
		add(scroll);
		add(p_btn);

		renderMenuList();
		
		btn_find.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flag = false;
				salesSelectAllByDate();
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
		// setBackground(Color.black);
		setPreferredSize(new Dimension(1200, 800));
		setVisible(false);
		btn_excel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("excel 버튼 클릭");
				// TODO Auto-generated method stub
				
				try {
					File filePath = new File("C:/Users/user/Documents/Sales");
					if(!filePath.exists()) { 
						filePath.mkdirs();
						File excelFile = new File("C:/Users/user/Documents/Sales/"+CurrentDay.getCurrentDate()+" 요약.xls");
						tabeDataTrans.TabeDataExportToExcel(table, excelFile);
					}else {
						File excelFile = new File("C:/Users/user/Documents/Sales/"+CurrentDay.getCurrentDate()+" 요약.xls");
						tabeDataTrans.TabeDataExportToExcel(table, excelFile);
					}
					JOptionPane.showMessageDialog(mainFrame, "엑셀파일로 변환이 되었습니다.\n'내 문서'폴더를 확인해주세요.");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(mainFrame, "엑셀파일로 변환 실패");
				}
				
			}
		});
	}

	public void renderMenuList() {
		System.out.println("renderList 메서드 접근");
		setModelData();
		table.setModel(salesTableModel);
		table.repaint();
		table.updateUI();
	}

	public void responseResult(JSONObject jsonObject) {
		System.out.println("orderSummaryList : " + jsonObject.get("orderSummaryList"));
		this.orderSummaryList = (List<OrderSummary>) jsonObject.get("orderSummaryList");
		System.out.println("orderDetailList : " + orderSummaryList);
		// mainFrame.container.store.storeAccount.setAccount(adminList);
		for (int i = 0; i < orderSummaryList.size(); i++) {
			System.out.println("매출 리스트 : " + orderSummaryList.get(i).getOrder_summary_id());
		}
		setModelData();
	}

	public void setModelData() {
		int total = this.orderSummaryList.size();
		System.out.println("setModelData() 메서드 접근 , 사이즈 : " + total);
		String[][] data = new String[total][7];
		for (int i = 0; i < total; i++) {
			OrderSummary orderSummary = orderSummaryList.get(i);
			data[i][0] = Integer.toString(i + 1);
			data[i][1] = Integer.toString(orderSummary.getOrder_num());
			data[i][2] = orderSummary.getOrder_date();
			data[i][3] = orderSummary.getPayType().getPay_method();
			data[i][4] = Integer.toString(orderSummary.getOrder_price());
			data[i][5] = orderSummary.getDevice().getDevice_name();
			data[i][6] = orderSummary.getDevice().getAdmin().getName();

		}
		salesTableModel.columnName = columnName;
		salesTableModel.data = data;
		table.setModel(salesTableModel);
		table.repaint();
		table.updateUI();
	}

	public void salesSelectAll() {
		mainFrame.requestController.salesSelectAll();
	}
	
	public void salesSelectAllByDate() {
		String day_start = ch_year_start.getSelectedItem() +"-"+ ch_month_start.getSelectedItem() +"-"+ ch_day_start.getSelectedItem(); 
		System.out.println("조회 시작 날짜 : "+day_start);
		String day_end = ch_year_end.getSelectedItem() +"-"+ ch_month_end.getSelectedItem() +"-"+ ch_day_end.getSelectedItem(); 
		System.out.println("조회 끝 날짜 : "+day_end);
		int start = Integer.parseInt(ch_year_start.getSelectedItem()) + Integer.parseInt(ch_month_start.getSelectedItem()) + Integer.parseInt(ch_day_start.getSelectedItem());
		int end = Integer.parseInt(ch_year_end.getSelectedItem()) + Integer.parseInt(ch_month_end.getSelectedItem()) + Integer.parseInt(ch_day_end.getSelectedItem()); 
		System.out.println("시작날짜 : "+start+", 끝날짜 : "+end);
		if(start > end) {
			JOptionPane.showMessageDialog(mainFrame, "입력오류\n조회할 마지막 값이 처음값보다 작습니다.");
		}
		orderSummary.setDay_start(day_start);
		orderSummary.setDay_end(day_end);
		mainFrame.requestController.salesSelectAllByDate(orderSummary);
	}
	public void responseSalesSelectAllByDate(JSONObject jsonObject) {
	
		this.orderSummaryList = (List<OrderSummary>) jsonObject.get("orderSummaryList");

		setModelData();
	}
}
