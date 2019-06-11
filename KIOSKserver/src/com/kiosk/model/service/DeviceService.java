package com.kiosk.model.service;

import java.util.List;

import com.kiosk.model.domain.Device;

public interface DeviceService {
	public Device selectByName(String device_name);
	public Device select(int device_id);
	public List selectByAdminId(int admin_id);
	public void delete(int device_id);
	public void insert(Device device);
	public void update(Device device);
	public List<Device> selectAll();
	public Device selectByNameAndAdmin(Device device);
}
