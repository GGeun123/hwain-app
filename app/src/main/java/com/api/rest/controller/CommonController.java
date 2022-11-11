package com.api.rest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.rest.dao.RestAPIService;
import com.common.DataMap;
import com.common.Utility;


@SuppressWarnings("unchecked")
@Controller
@Path("/Common")
@RequestMapping("/Common")
public class CommonController {
	
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	    
    @Context
    UriInfo uriInfo;
 
    @Context
    HttpServletRequest request;
    
    
    //메뉴 로그 등록
    @POST
    @Path("menuLogInsert")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
    public Response menuLogInsert(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	
 	
    	try {    	
        	String date= Utility.getSysDate();
        	String time= Utility.getSysTime();
//        	System.out.println(time);
        	dataMap.put("date", date);
        	dataMap.put("time", time);
    		dataMap.put("module_nm", params.getFirst("module_nm"));
    		dataMap.put("sabun_no", params.getFirst("sabun_no"));
        	
        	System.out.println(dataMap);    		
    		restAPIService.insertControl("menuLogInsert", dataMap);
    		
    		jsonObject.put("datas", dataMap);
    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }    
    
	
	//무재해현황판
	@GET
    @Path("accidentView")
    @Produces(MediaType.APPLICATION_JSON)
    public Response accidentView() {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("accidentView : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		list= restAPIService.listControl("proc_accident_viewK", dataMap);
    		list2= restAPIService.listControl("proc_accident_viewP", dataMap);

    		jsonObject.put("datasK", list);
    		jsonObject.put("datasP", list2);
    		jsonObject.put("countK", list.size());
    		jsonObject.put("countP", list2.size());
    		jsonObject.put("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }
	
	//분석현황 메뉴권한
	@GET
    @Path("getMenuAuth/{sabun_no}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenuAuth(@PathParam("sabun_no") final String sabun_no) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("getMenuAuth : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	dataMap.put("sabun_no", sabun_no);
    	try {    		   		
        	list= restAPIService.listControl("getAnalyMenuAuth", dataMap);

    		jsonObject.put("datas", list);
    		jsonObject.put("count", list.size());
    		jsonObject.put("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }		
	
	//테스트
	@GET
    @Path("getMenuAuth")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMenuAuth1(@QueryParam("sabun_no") final String sabun_no) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("getMenuAuth : dataMap="+dataMap);
    	
    	System.out.println(sabun_no);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map=null;
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		//map= restAPIService.mapDataControl("gear_m_info", dataMap);


    		jsonObject.put("datas", list);
    		jsonObject.put("count", list.size());
    		jsonObject.put("status", "success");
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }	
    	
    
}//c
