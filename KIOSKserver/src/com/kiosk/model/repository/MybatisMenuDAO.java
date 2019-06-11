package com.kiosk.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kiosk.model.domain.Menu;

@Repository
public class MybatisMenuDAO implements MenuDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Menu select(int menu_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int menu_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("Menu.updateByExist", menu_id);
	}

	@Override
	public int insert(Menu menu) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("Menu.insert",menu);
	}

	@Override
	public int update(Menu menu) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Menu> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Menu.selectAll");
	}

	@Override
	public List<Menu> selectAllByCategory(int category_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Menu.selectAllByCategory", category_id);
	}

}
