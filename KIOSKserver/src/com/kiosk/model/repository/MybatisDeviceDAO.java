package com.kiosk.model.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kiosk.model.domain.Device;

@Repository
public class MybatisDeviceDAO implements DeviceDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public Device selectByName(String device_name) {
		System.out.println("sqlººº«∆—≈‰∏Æ : "+sqlSessionTemplate);
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("Device.selectByName", device_name);
	}

	@Override
	public Device select(int device_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(int device_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("Device.delete",device_id);
	}

	@Override
	public int insert(Device device) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("Device.insert", device);
	}

	@Override
	public int update(Device device) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("Device.update", device);
	}

	@Override
	public List<Device> selectAll() {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectList("Device.selectAll");
	}

	@Override
	public List selectByAdminId(int admin_id) {
	
		return sqlSessionTemplate.selectList("Device.selectByAdminId",admin_id);
	}

	@Override
	public int deleteByAdmin(int admin_id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("Device.deleteByAdmin",admin_id);
	}

	@Override
	public Device selectByNameAndAdmin(Device device) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("Device.selectByNameAndAdmin",device);
	}

}
