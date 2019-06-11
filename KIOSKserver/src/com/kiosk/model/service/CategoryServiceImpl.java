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
			throw new RuntimeException("��� ����");
		}
	}

	@Override
	@Transactional("transactionManager")
	public void update(Category category) {
		// ���� �ش� ī�װ� pk�� �޴��� �� ��ȸ
		List<Menu> menuList = menuDAO.selectAllByCategory(category.getCategory_id());

		// �ش� ī�װ� exist�� 0���� �ٲ�
		categoryDAO.delete(category.getCategory_id());
		// �� ī�װ� ����
		categoryDAO.insert(category);

		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);
			// �ش� ī�װ� pk�� ���� �޴��� exist�� ��� 0���� �ٲ�
			menuDAO.delete(menu.getMenu_id());

			// �ش� �޴���� ������ �ִ� stock ���̺� ����
			stockDAO.deleteByMenu(menu.getMenu_id());

			// �ش� ī�װ��� �ִ� �޴����� ���Ӱ� insert�� category_id�� insert��Ű��
			menu.setCategory(category);
			menuDAO.insert(menu);
		}
		List<Menu> menuList2 = menuDAO.selectAllByCategory(category.getCategory_id());
		// ��� ������ �� ���ϱ�
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
		// ���� �ش� ī�װ� pk�� �޴��� �� ��ȸ
		List<Menu> menuList = menuDAO.selectAllByCategory(category_id);

		// �ش� ī�װ� exist�� 0���� �ٲ�
		categoryDAO.delete(category_id);

		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);
			// �ش� ī�װ� pk�� ���� �޴��� exist�� ��� 0���� �ٲ�
			menuDAO.delete(menu.getMenu_id());

			// �ش� �޴���� ������ �ִ� stock ���̺� ����
			stockDAO.deleteByMenu(menu.getMenu_id());
		}
	}
}
