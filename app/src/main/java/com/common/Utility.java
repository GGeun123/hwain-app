package com.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Utility {
		
	public static String jsAlertHref(String msg, String url){
		String result="<script>";
		result +="alert('" + msg + "');";
		result += "location.href='" +url+ "';";
		result +="</script>";
		
		return result;		
	}
	
	public static String jsAlertBack(String msg){
		String result="<script>";
		result +="alert('" + msg + "');";
		result += "history.back();";
		result +="</script>";
		
		return result;	
	}
	
	public static String getSysDate(){
		String nowdate="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		nowdate = sdf.format(new java.util.Date());
		return nowdate;		
	}
	
	public static String getSysTime(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss:SS");
		String datetime = sdf1.format(cal.getTime());
		datetime= datetime.substring(0, datetime.length()-1);
		
		return datetime;
	}	
	
	
}//class








