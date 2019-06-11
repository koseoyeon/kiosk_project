package com.kiosk.model.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiosk.commons.ImageUpload;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.Stock;
import com.kiosk.model.repository.AdminDAO;
import com.kiosk.model.repository.MenuDAO;
import com.kiosk.model.repository.StockDAO;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	@Qualifier("mybatisMenuDAO")
	private MenuDAO menuDAO;

	@Autowired
	@Qualifier("mybatisAdminDAO")
	private AdminDAO adminDAO;

	@Autowired
	@Qualifier("mybatisStockDAO")
	private StockDAO stockDAO;

	@Override
	public Menu select(int menu_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional("transactionManager")
	public void delete(int menu_id) {
		// 해당 메뉴의 exist를 0으로 변경
		menuDAO.delete(menu_id);

		// 해당 메뉴들과 연관이 있는 stock 테이블 삭제
		stockDAO.deleteByMenu(menu_id);
		Menu menu=menuDAO.select(menu_id);
		try {
			String file_name=menu.getFile_name();
			String path = System.getProperty("user.dir");
			File file = new File(path + "/menu_res/" + file_name);
			file.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void insert(Menu menu) {
		menuDAO.insert(menu);
		List<Admin> adminList = adminDAO.selectAll();
		for (int i = 0; i < adminList.size(); i++) {
			Stock stock = new Stock();
			Admin admin = adminList.get(i);
			stock.setMenu(menu);
			stock.setAdmin(admin);
			stockDAO.insert(stock);
		}
	}

	@Override
	@Transactional("transactionManager")
	public void update(Menu menu) {
		System.out.println("유저가 보낸 menu_id : "+menu.getMenu_id());

		// 해당 메뉴의 exist를 0으로 변경
		menuDAO.delete(menu.getMenu_id());

		// 해당 메뉴들과 연관이 있는 stock 테이블 삭제
		stockDAO.deleteByMenu(menu.getMenu_id());
		Menu deleteMenu=menuDAO.select(menu.getMenu_id());
		try {
			String file_name=menu.getFile_name();
			String path = System.getProperty("user.dir");
			File file = new File(path + "/menu_res/" + file_name);
			file.delete();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 새로운 메뉴를 insert
		menuDAO.insert(menu);
		String encodingImg=menu.getEncoderImg();
		ImageUpload.decoding(encodingImg, menu.getFile_name());
		// 해당 메뉴의 stock insert
		List<Admin> adminList = adminDAO.selectAll();
		for (int k = 0; k < adminList.size(); k++) {
			Stock stock = new Stock();
			Admin admin = adminList.get(k);
			stock.setMenu(menu);
			stock.setAdmin(admin);
			stockDAO.insert(stock);
		}
	}

	@Override
	public List<Menu> selectAll() {

		return menuDAO.selectAll();
	}

}
