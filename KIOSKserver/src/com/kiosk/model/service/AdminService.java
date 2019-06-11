package com.kiosk.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kiosk.model.domain.Admin;


public interface AdminService {
	public Admin select(int admin_id);
	public void insert(Admin admin);
	public void update(Admin admin);
	public Admin login(Admin admin);
	public void delete(int admin_id);
	
	public List<Admin> selectAll();
	public List<Admin> checkId(Admin admin);
	public List<Admin> checkName(Admin admin);
}
