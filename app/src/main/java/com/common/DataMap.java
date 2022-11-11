package com.common;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.oreilly.servlet.MultipartRequest;

@SuppressWarnings("unchecked")
public class DataMap {
	public static Map<String, Object> getDataMap(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>(); 

		String key; 
		//System.out.println(request.getParameterNames());
		for(Enumeration<String> e = request.getParameterNames(); e.hasMoreElements(); map.put(key, request.getParameter(key))) { 
		key = (String)e.nextElement(); 
		} 
		return map;
	}
	
	public static Map<String, Object> getDataMap(MultipartRequest multi){
		Map<String, Object> map = new HashMap<String, Object>(); 

		String key; 
		//System.out.println(request.getParameterNames());
		for(Enumeration<String> e = multi.getParameterNames(); e.hasMoreElements(); map.put(key, multi.getParameter(key))) { 
		key = (String)e.nextElement(); 
		} 
		return map;
	}
}
