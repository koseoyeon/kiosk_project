package com.kiosk.model.service;

import java.util.List;

import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Category;

public interface CategoryService {
	public Category select(int category_id);
	public void insert(Category category);
	public void update(Category category);
	public void delete(int category_id);
	public List<Category> selectAll();
}
