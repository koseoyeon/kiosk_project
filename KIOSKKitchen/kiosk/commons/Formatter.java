package com.kiosk.commons;

import java.text.NumberFormat;
import java.text.ParseException;

//��ȭǥ�� ���� ó���� �ִ� Ŭ����
public class Formatter {
   // �� ǥ�ø� ������ ��ȭǥ�� ��ȯ �޼���
   public static String getCurrency(int num) {
      NumberFormat nf = NumberFormat.getCurrencyInstance();

      return nf.format(num);
   }
   public static int getOriginNum(String num) {
      NumberFormat nf = NumberFormat.getCurrencyInstance();
      Number originNum=null;
      try {
         originNum = nf.parse(num);
      } catch (ParseException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   
      return    Integer.parseInt(originNum.toString());
   }
}