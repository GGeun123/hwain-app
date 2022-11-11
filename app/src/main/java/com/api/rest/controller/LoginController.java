package com.api.rest.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.api.rest.dao.RestAPIService;
import com.common.DataMap;

@SuppressWarnings("unchecked")
@Controller
@Path("/Login")
@RequestMapping("/Login")
public class LoginController {
	
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
    JSONObject globalJsonObject = new JSONObject();
    
    @Context
    UriInfo uriInfo;
 
    @Context
    HttpServletRequest request;
    
    final String LATEST_APP_VER= "1.2.2";

    //로그인 체크
    @GET
    @Path("sid_check_ajax/sid={sid}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response sid_check_ajax(@PathParam("sid") final String sid) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	dataMap.put("sid", sid);

    	try {
			Map<String, Object> user_infoMap=restAPIService.mapDataControl("sid_check_ajax", dataMap);	
			if(user_infoMap!=null){
//	    		array.add(user_infoMap);
				globalJsonObject.put("datas", user_infoMap);
				globalJsonObject.put("status", true);
			}else{
				globalJsonObject.put("status", false); 
			}

		} catch (SQLException e) {
			e.printStackTrace();
			globalJsonObject.put("status", false);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}    
        
    	return Response.status(200).entity(globalJsonObject).build();
    }
    
    //로그인 체크
    @GET
    @Path("loginCheck/sid={sid}/password={password}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response loginDataCheck(@PathParam("sid") final String sid, @PathParam("password") final String password) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	dataMap.put("sid", sid);
    	dataMap.put("password", password);
    	
        int result = 0;
        try{
            result = restAPIService.login_check(dataMap);
            System.out.println((new StringBuilder("로그인 처리,result=")).append(result).toString());   
            globalJsonObject.put("status", true);        
            globalJsonObject.put("result", result);	
        }
        catch(SQLException e){
            System.out.println("로그인 처리 실패");
			e.printStackTrace();
			globalJsonObject.put("status", false);
			throw new WebApplicationException(Response.Status.NOT_FOUND);            
        }
        
        if(result == RestAPIService.LOGIN_OK){
        	try {
//				Map<String, Object> user_infoMap=restAPIService.mapDataControl("sid_check_ajax", dataMap);	
//	    		array.add(user_infoMap);
//	    		jsonObject.put("datas", user_infoMap);
				

    			//user_auth 쿠키로 저장

    			HttpSession session = request.getSession(false);
				session.setMaxInactiveInterval(-1);

				session.setAttribute("sid", sid);
        		
        		
			} catch (Exception e) {
				e.printStackTrace();
			}         
            
        }else if(result == RestAPIService.PWD_MISMATCH){
        	System.out.println("PWD_MISMATCH");
//        	jsonObject.put("datas", array);
            
        }else if(result == RestAPIService.NONE_ID){
        	System.out.println("NONE_ID");
//        	jsonObject.put("datas", array);
        }
        
    	return Response.status(200).entity(globalJsonObject).build();
    }
    
    //로그인 체크 앱
    @GET
    @Path("loginCheckApp/sid={sid}/password={password}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response loginDataCheckApp(@PathParam("sid") final String sid, @PathParam("password") final String password) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	dataMap.put("sid", sid);
    	dataMap.put("password", password);
    	JSONObject jsonObject = new JSONObject();
    	
        int result = 0;
        try{
            result = restAPIService.login_check(dataMap);
            System.out.println((new StringBuilder("로그인 처리,result=")).append(result).toString());   
    		jsonObject.put("status", true);
    		jsonObject.put("LATEST_APP_VER", LATEST_APP_VER);
    		jsonObject.put("result", result);
        }
        catch(SQLException e){
            System.out.println("로그인 처리 실패");
			e.printStackTrace();
			jsonObject.put("status", false);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        
        if(result == RestAPIService.LOGIN_OK){
        	System.out.println("LOGIN_OK");
            
        }else if(result == RestAPIService.PWD_MISMATCH){
        	System.out.println("PWD_MISMATCH");
//        	jsonObject.put("datas", array);
            
        }else if(result == RestAPIService.NONE_ID){
        	System.out.println("NONE_ID");
//        	jsonObject.put("datas", array);
        }
        
    	return Response.status(200).entity(jsonObject).build();
    }    
    

    @RequestMapping(value="/main.do",method=RequestMethod.GET)
    public ModelAndView getMonthCalculate(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/main");
        return mv;
    }
    
    //유저 검색
    @GET
    @Path("searchUserData/gubun={gubun}/param={param}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response searchUserData(@PathParam("gubun") final String gubun, @PathParam("param") final String param) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("searchUserData : dataMap="+dataMap);
    	
		dataMap.put("gubun", gubun);
		dataMap.put("param", param);
    	JSONObject jsonObject = new JSONObject();

    	try {
			List<Map<String, Object>> list = restAPIService.listControl("searchUserData", dataMap);	
			if(list!=null){
	    		jsonObject.put("datas", list);
	    		jsonObject.put("status", true);
			}else{
				jsonObject.put("status", false); 
			}

		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", false);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}    
        
    	return Response.status(200).entity(jsonObject).build();
    }    
    
    // 2018-08-28 비콘 세팅값 획득
    @GET
    @Path("beaconSetting/beacon_id={beacon_id}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
    public Response getBeaconSetting(@PathParam("beacon_id") final String beacon_id) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	
    	System.out.println("beacon_id = " + beacon_id);
    	
    	dataMap.put("beacon_id", beacon_id);
		//dataMap.put("param", param);
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		List<Map<String, Object>> list = restAPIService.listControl("getBeaconSetting", dataMap);
    		if (list != null) {
    			jsonObject.put("datas", list);
    			jsonObject.put("status", true);
    		} else {
    			jsonObject.put("status", false);
    		}
    		
    	} catch (SQLException e) {
    		e.printStackTrace();
			jsonObject.put("status", false);
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }

}//c
