package com.kiosk.model.repository;

import java.util.List;

import com.kiosk.model.domain.Menu;


public interface MenuDAO {
	public Menu select(int menu_id);
	public int delete(int menu_id);
	public int insert(Menu menu);
	public int update(Menu menu);
	public List<Menu> selectAll();
	public List<Menu> selectAllByCategory(int category_id);
}
