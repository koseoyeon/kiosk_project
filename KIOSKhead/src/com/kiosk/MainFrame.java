package com.kiosk;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kiosk.controller.RequestController;
import com.kiosk.controller.ResponseController;
import com.kiosk.gui.Container;
import com.kiosk.gui.LoginForm;
import com.kiosk.model.domain.Admin;

public class MainFrame extends JFrame {

//================================================
   int port = 9876;
   InetAddress inetAddess;
   //String ip;
   String ip="192.168.54.166";
   Socket client;
   public ClientThread clientThread;
   public LoginForm loginForm;
   public RequestController requestController = new RequestController(this);
   public ResponseController responseController = new ResponseController(this);
//================================================
   public Admin admin;
   
   public Admin getAdmin() {
      return admin;
   }

   public void setAdmin(Admin admin) {
      this.admin = admin;
   }

   public Container container;
   public MainFrame() {
      //getIp(); // 현재 접속중인 컴퓨터의 ip얻어오기
      connect(); // kioskServer와 연결
      container =new Container(this);
      loginForm = new LoginForm(this);
      add(container);      
      setSize(1200, 855);
      setLocationRelativeTo(null);
      setResizable(false);
      setTitle("Kiosk");
      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosed(WindowEvent e) {
            // TODO Auto-generated method stub
            System.exit(0);
   
         }
      });
      setDefaultCloseOperation(EXIT_ON_CLOSE);
   }

   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
//========================network==========================
   // 접속 ip얻어오기
   public void getIp() {
      try {
         inetAddess = InetAddress.getLocalHost();
         ip = inetAddess.getHostAddress();
      } catch (UnknownHostException e) {
         e.printStackTrace();
      }
      System.out.println("접속한 ip : " + ip);
   }

   // kioskServer와 연결
   public void connect() {
      System.out.println("connect() 메서드 실행");
      try {
         client = new Socket(ip, port);
         System.out.println("client : " + client);
         clientThread = new ClientThread(this, client);
         System.out.println("=====Client_thread가동=====");
         // 쓰레드 가동 시작
         clientThread.start();
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
//========================================================
}