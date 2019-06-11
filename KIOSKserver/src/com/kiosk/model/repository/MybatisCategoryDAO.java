package com.kiosk.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kiosk.model.domain.Category;

@Repository
public class MybatisCategoryDAO implements CategoryDAO{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Category select(int category_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Category category) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("Category.insert", category);
	}

	@Override
	public int update(Category category) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("Category.insert", category);
	}

	@Override
	public int delete(int category_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("Category.deleteByExist", category_id);
	}

	@Override
	public List<Category> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Category.selectAll");
	}

}
