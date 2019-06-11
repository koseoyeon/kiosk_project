package com.kiosk.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.kiosk.model.domain.Device;
import com.kiosk.model.repository.DeviceDAO;

@Service
public class DeviceServiceImpl implements DeviceService{

	@Autowired
	@Qualifier("mybatisDeviceDAO")
	private DeviceDAO deviceDAO;
	
	@Override
	public Device selectByName(String device_name) {
		Device returnDevice = null;
		try {
			returnDevice = deviceDAO.selectByName(device_name);
			System.out.println("DAO : "+returnDevice);

		} catch (RuntimeException e) {
			throw new RuntimeException("장치 등록 실패");
		}
		return returnDevice;
	}

	@Override
	public Device select(int device_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(int device_id) {
		deviceDAO.delete(device_id);
	}

	@Override
	public void insert(Device device) {
		// TODO Auto-generated method stub
		deviceDAO.insert(device);
	}

	@Override
	public void update(Device device) {
		// TODO Auto-generated method stub
		deviceDAO.update(device);
	}

	@Override
	public List<Device> selectAll() {
		// TODO Auto-generated method stub
		return deviceDAO.selectAll();
	}

	@Override
	public List selectByAdminId(int admin_id) {
		
		return deviceDAO.selectByAdminId(admin_id);
	}

	@Override
	public Device selectByNameAndAdmin(Device device) {

		return deviceDAO.selectByNameAndAdmin(device);
	}

}
