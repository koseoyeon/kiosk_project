package com.kiosk.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiosk.controller.ServerHeadController;
import com.kiosk.model.domain.Admin;
import com.kiosk.model.domain.Menu;
import com.kiosk.model.domain.Stock;
import com.kiosk.model.repository.AdminDAO;
import com.kiosk.model.repository.DeviceDAO;
import com.kiosk.model.repository.MenuDAO;
import com.kiosk.model.repository.MybatisAdminDAO;
import com.kiosk.model.repository.StockDAO;

@Service
public class AdminServiceImpl implements AdminService {
   @Autowired
   @Qualifier("mybatisAdminDAO")
   private AdminDAO adminDAO;
   
   @Autowired
   @Qualifier("mybatisStockDAO")
   private StockDAO stockDAO;

   @Autowired
   @Qualifier("mybatisMenuDAO")
   private MenuDAO menuDAO; 
   
   @Autowired
   @Qualifier("mybatisDeviceDAO")
   private DeviceDAO deviceDAO;
   
   
   
   @Override
   public Admin select(int admin_id) {
      return adminDAO.select(admin_id);
   }

   @Override
   public void insert(Admin admin) throws RuntimeException {
      // TODO Auto-generated method stub

      int result = adminDAO.insert(admin);
      
      List<Menu> menuList = menuDAO.selectAll();
      if(menuList.size()!=0) {
         for (int i = 0; i < menuList.size(); i++) {
            Stock stock = new Stock();
            stock.setAdmin(admin);
            Menu menu = new Menu();
            menu = menuList.get(i);
            stock.setMenu(menu);
            stockDAO.insert(stock);
         }
      }

      System.out.println("서비스에서의 result : " + result);

   }

   @Override
   public Admin login(Admin admin) throws RuntimeException {
      Admin returnAdmin = null;
      try {
         returnAdmin = adminDAO.login(admin);

      } catch (RuntimeException e) {
         throw new RuntimeException("로그인 실패");
      }
      return returnAdmin;
   }

   @Override
   public List<Admin> selectAll() {
      // TODO Auto-generated method stub
      return adminDAO.selectAll();
   }

   @Override
   public List<Admin> checkId(Admin admin) {
      // TODO Auto-generated method stub
      return adminDAO.checkId(admin);
   }

   @Override
   public List<Admin> checkName(Admin admin) {
      // TODO Auto-generated method stub
      return adminDAO.checkName(admin);
   }

   @Override
   @Transactional("transactionManager")
   public void delete(int admin_id) {
      // TODO Auto-generated method stub
      adminDAO.delete(admin_id);
      stockDAO.deleteByAdmin(admin_id);
   }
   
   @Override
   @Transactional("transactionManager")
   public void update(Admin admin) {
      try {
         adminDAO.delete(admin.getAdmin_id());        
         stockDAO.deleteByAdmin(admin.getAdmin_id());
         adminDAO.insert(admin);
         
         List<Menu> menuList = menuDAO.selectAll();
         if(menuList.size()!=0) {
            for (int i = 0; i < menuList.size(); i++) {
               Stock stock = new Stock();
               stock.setAdmin(admin);
               Menu menu = new Menu();
               menu = menuList.get(i);
               stock.setMenu(menu);
               stockDAO.insert(stock);
            }
         }
      } catch (RuntimeException e) {
         throw new RuntimeException("수정 실패");
      }
      
   }

}