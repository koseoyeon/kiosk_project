package com.kiosk.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.kiosk.model.domain.Admin;

@Repository
public class MybatisAdminDAO implements AdminDAO{
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public Admin select(int admin_id) {
		System.out.println(sqlSessionTemplate);
		return sqlSessionTemplate.selectOne("Admin.select",admin_id);
	}

	@Override
	public int insert(Admin admin) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("Admin.insert", admin);
	}

	@Override
	public Admin login(Admin admin) {
		return sqlSessionTemplate.selectOne("Admin.login",admin);
	}

	@Override
	public List<Admin> selectAll() {
		// TODO Auto-generated method stub
		System.out.println("adminDAO Á¢±Ù");
		return sqlSessionTemplate.selectList("Admin.selectAll");
	}

	@Override
	public List<Admin> checkId(Admin admin) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Admin.checkId",admin);
	}

	@Override
	public List<Admin> checkName(Admin admin) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Admin.checkName",admin);
	}

	@Override
	public int delete(int admin_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.delete("Admin.delete",admin_id);
	}

	@Override
	public int update(Admin admin) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("Admin.update",admin);
	}

	@Override
	public int updateAuth(int admin_id) {
	
		return sqlSessionTemplate.update("Admin.updateAuth",admin_id);
	}
	
}
