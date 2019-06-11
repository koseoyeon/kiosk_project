package com.kiosk.model.repository;

import java.util.List;

import com.kiosk.model.domain.Admin;

public interface AdminDAO {
	public Admin select(int admin_id);
	public Admin login(Admin admin);
	public int insert(Admin admin);
	public int delete(int admin_id);
	public int update(Admin admin);
	public int updateAuth(int admin_id);
	public List<Admin> selectAll();
	public List<Admin> checkId(Admin admin);
	public List<Admin> checkName(Admin admin);
}
