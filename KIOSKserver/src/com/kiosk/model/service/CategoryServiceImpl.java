package com.kiosk.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.Stock;
import com.kiosk.model.repository.AdminDAO;
import com.kiosk.model.repository.CategoryDAO;
import com.kiosk.model.repository.MenuDAO;
import com.kiosk.model.repository.StockDAO;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	@Qualifier("mybatisAdminDAO")
	private AdminDAO adminDAO;

	@Autowired
	@Qualifier("mybatisStockDAO")
	private StockDAO stockDAO;

	@Autowired
	@Qualifier("mybatisMenuDAO")
	private MenuDAO menuDAO;

	@Override
	public Category select(int category_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Category category) {
		// TODO Auto-generated method stub
		try {
			int result = categoryDAO.insert(category);

		} catch (RuntimeException e) {
			throw new RuntimeException("등록 실패");
		}
	}

	@Override
	@Transactional("transactionManager")
	public void update(Category category) {
		// 먼저 해당 카테고리 pk로 메뉴를 다 조회
		List<Menu> menuList = menuDAO.selectAllByCategory(category.getCategory_id());

		// 해당 카테고리 exist를 0으로 바꿈
		categoryDAO.delete(category.getCategory_id());
		// 새 카테고리 생성
		categoryDAO.insert(category);

		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);
			// 해당 카테고리 pk를 가진 메뉴의 exist를 모두 0으로 바꿈
			menuDAO.delete(menu.getMenu_id());

			// 해당 메뉴들과 연관이 있는 stock 테이블 삭제
			stockDAO.deleteByMenu(menu.getMenu_id());

			// 해당 카테고리에 있던 메뉴들을 새롭게 insert된 category_id로 insert시키기
			menu.setCategory(category);
			menuDAO.insert(menu);
		}
		List<Menu> menuList2 = menuDAO.selectAllByCategory(category.getCategory_id());
		// 모든 가맹점 수 구하기
		List<Admin> adminList = adminDAO.selectAll();
		for (int i = 0; i < menuList2.size(); i++) {
			Menu menu = menuList2.get(i);
			for (int k = 0; k < adminList.size(); k++) {
				Stock stock = new Stock();
				Admin admin = adminList.get(k);
				stock.setMenu(menu);
				stock.setAdmin(admin);
				stockDAO.insert(stock);
			}
		}
	}

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		return categoryDAO.selectAll();
	}

	@Override
	@Transactional("transactionManager")
	public void delete(int category_id) {
		// 먼저 해당 카테고리 pk로 메뉴를 다 조회
		List<Menu> menuList = menuDAO.selectAllByCategory(category_id);

		// 해당 카테고리 exist를 0으로 바꿈
		categoryDAO.delete(category_id);

		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);
			// 해당 카테고리 pk를 가진 메뉴의 exist를 모두 0으로 바꿈
			menuDAO.delete(menu.getMenu_id());

			// 해당 메뉴들과 연관이 있는 stock 테이블 삭제
			stockDAO.deleteByMenu(menu.getMenu_id());
		}
	}
}
