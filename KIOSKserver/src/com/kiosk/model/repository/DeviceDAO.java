package com.kiosk.model.repository;

import java.util.List;

import com.kiosk.model.domain.Device;

public interface DeviceDAO {
	public Device selectByName(String device_name);
	public Device select(int device_id);
	public List selectByAdminId(int admin_id);
	public int delete(int device_id);
	public int deleteByAdmin(int admin_id);
	public int insert(Device device);
	public int update(Device device);
	public List<Device> selectAll();
	public Device selectByNameAndAdmin(Device device);
}
