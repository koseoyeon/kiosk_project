package com.kiosk.model.service;

import java.util.List;

import com.kiosk.model.domain.Menu;

public interface MenuService {
	public Menu select(int menu_id);
	public void delete(int menu_id);
	public void insert(Menu menu);
	public void update(Menu menu);
	public List<Menu> selectAll();
}
