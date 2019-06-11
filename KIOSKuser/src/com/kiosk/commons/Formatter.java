package com.kiosk.commons;

import java.text.NumberFormat;
import java.text.ParseException;

//통화표시 등을 처리해 주는 클래스
public class Formatter {
   // 원 표시를 포함한 통화표시 반환 메서드
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