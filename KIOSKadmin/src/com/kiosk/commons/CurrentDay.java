package com.kiosk.commons;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class CurrentDay{
	static int day,month,year;
	GregorianCalendar cal;
	static Calendar c=Calendar.getInstance(Locale.KOREA);;

	public static String getCurrentDate() {
		String str="";
		String str2="";
		day = c.get(Calendar.DATE);
		month = c.get(Calendar.MONTH)+1;
		year = c.get(Calendar.YEAR);
		if(day<10) {
			str2="0";
			str2 = str2+day;
		}else {
			str2 = Integer.toString(day);
		}
		if(month<10) {
			str="0";
			str = str+month;
			str = year+str+str2;
		}else {
			str = Integer.toString(year)+Integer.toString(month)+str2;
		}
		return str;
	}
	public static String getCurrnetTime() {
		int a=c.get(Calendar.MINUTE);
		int b=c.get(Calendar.HOUR_OF_DAY);
		System.out.println();
		System.out.println(b);
		String m=Integer.toString(a);
		String h=Integer.toString(b);
		if(a<10) {
			m="0";
			m = m+a;
		}else {
			m = Integer.toString(a);
		}
		if(b<10) {
			h="0";
			h = h+b;
			h = h+m;
		}else {
			h = h+m;
		}
		
		return (h);
	}
	public static void main(String[] args) {
		System.out.println(getCurrnetTime());
	}

}

