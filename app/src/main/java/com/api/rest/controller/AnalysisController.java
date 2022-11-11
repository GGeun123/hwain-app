package com.api.rest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.api.rest.dao.RestAPIService;
import com.common.DataMap;

@SuppressWarnings("unchecked")
@Controller
@Path("/Analysis")
@RequestMapping("/Analysis")
public class AnalysisController {
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
	@Context UriInfo uriInfo;
	 
	@Context HttpServletRequest request;

    //------------------------------- Taewoon 장비가동현황 리스트 카운트 가져오기 ---------------------------
    @GET
    @Path("analysisEquipCnt/{sdate}/{comboKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response analysisEquipCnt(@PathParam("sdate") final String sdate,
    						@PathParam("comboKey") final String comboKey)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("dates", sdate);
    		dataMap.put("comboKey", comboKey);
    		
    		map= restAPIService.mapDataControl("analysisEquipCnt", dataMap);
    		JSONArray array = new JSONArray();
    		array.add(map);

    		jsonObject.put("datas", array);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------- Taewoon 장비가동현황 리스트 카운트 가져오기 ---------------------------
    
    //------------------------------ Taewoon 운행일지 차량번호 가져오기 ------------------------------
    @GET
    @Path("analysisBreak/{sdate}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response analysisBreak(@PathParam("sdate") final String sdate){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("dates", sdate);
    		
    		list= restAPIService.listControl("analysisBreak", dataMap);

    		jsonObject.put("datas", list);
    		jsonObject.put("count", list.size());
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------ Taewoon 운행일지 차량번호 가져오기 ------------------------------
    
	//------------------------------- Taewoon 장비가동현황 리스트 가져오기 -------------------------------
	@GET
	@Path("analysisEquipList/{sdate}/{comboKey}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response analysisEquipList(@PathParam("sdate") final String sdate,
										@PathParam("comboKey") final String comboKey)
	{
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONObject jsonObject = new JSONObject();
    	
		try {
			dataMap.put("dates", sdate);
			dataMap.put("comboKey", comboKey);
			
			list = restAPIService.listControl("analysisEquipList", dataMap);

			jsonObject.put("list", list);
			jsonObject.put("count", list.size());
			jsonObject.put("status", "success");
		}catch(SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return Response.status(200).entity(jsonObject).build();
	}
	//------------------------------- Taewoon 장비가동현황 근무구분 가져오기 -------------------------------
	
	//------------------------------- Taewoon S/P CARRIER 실적 리스트 가져오기 -------------------------------
	@GET
	@Path("analysisSLAGPOTList/{sdate}/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response analysisSLAGPOTList(@PathParam("sdate") final String sdate,
										@PathParam("userId") final String userId)
	{
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONObject jsonObject = new JSONObject();
    	
		try {
			dataMap.put("dates", sdate);
			dataMap.put("userId", userId);
			
			map = restAPIService.mapDataControl("analysisSLAGPOTListCnt", dataMap);
			
			if(Integer.parseInt(map.get("CNT").toString()) > 0) {
				list = restAPIService.listControl("analysisSLAGPOTList", dataMap);	
			}else {
				list = restAPIService.listControl("analysisSLAGPOTNoList", dataMap);	
			}

			jsonObject.put("list", list);
			jsonObject.put("count", list.size());
			jsonObject.put("status", "success");
		}catch(SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		
		return Response.status(200).entity(jsonObject).build();
	}
	//------------------------------- Taewoon 장비가동현황 근무구분 가져오기 -------------------------------
}
