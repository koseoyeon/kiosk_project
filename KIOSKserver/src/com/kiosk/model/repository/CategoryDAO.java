package com.kiosk.model.repository;

import java.util.List;

import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;

public interface CategoryDAO {
	public Category select(int category_id);
	public int insert(Category category);
	public int update(Category category);
	public int delete(int category_id);
	public List<Category> selectAll();
}
