package com.api.rest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.api.rest.dao.RestAPIService;
import com.common.DataMap;

@SuppressWarnings("unchecked")
@Controller
@Path("/Personnel")
@RequestMapping("/Personnel")
public class PersonnelController {
	
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
    JSONObject globalJsonObject = new JSONObject();
    
    @Context
    UriInfo uriInfo;
 
    @Context
    HttpServletRequest request;
    
    /*-------------------------------- 인사정보 -------------------------------------*/
    //인사정보 데이터
    @GET
    @Path("PersonnelList/startRow={startRow}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response PersonnelList(@PathParam("startRow") final int startRow) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	final int ROW=20;
    	try {
    		int startRow_= (startRow*ROW)-ROW+1;
    		int endRow= ROW*startRow;
    		dataMap.put("startRow", startRow_);
    		dataMap.put("endRow", endRow);
    		System.out.println("dataMap="+dataMap);
    		list= restAPIService.listControl("personnelList", dataMap);

    		globalJsonObject.put("datas", list);
    		globalJsonObject.put("count", list.size());
    		globalJsonObject.put("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			globalJsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(globalJsonObject).build();
    }
    
    //인사정보 검색 데이터
    @GET
    @Path("PersonnelList/startRow={startRow}/search={search}/keyword={keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response PersonnelList(@PathParam("startRow") final int startRow, 
    		@PathParam("search") final String search, 
    		@PathParam("keyword") final String keyword) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	final int ROW=20;
    	try {
    		int startRow_= (startRow*ROW)-ROW+1;
    		int endRow= ROW*startRow;
    		dataMap.put("startRow", startRow_);
    		dataMap.put("endRow", endRow);
    		dataMap.put("search", search);
    		dataMap.put("keyword", keyword);
    		System.out.println("search= DataMap="+dataMap);
    		
    		list= restAPIService.listControl("personnelList", dataMap);

    		globalJsonObject.put("datas", list);
    		globalJsonObject.put("count", list.size());
    		globalJsonObject.put("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			globalJsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(globalJsonObject).build();
    }

	
}//c
