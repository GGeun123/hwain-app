package com.api.rest.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
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
@Path("/DrivingLog")
@RequestMapping("/DrivingLog")
public class DrivingLogController {
	private RestAPIService restAPIService;
	
	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
	@Context UriInfo uriInfo;
	 
    @Context HttpServletRequest request;
    
	//------------------------------ Taewoon 운행일지 차종 코드 가져오기 -----------------------------
    @GET
    @Path("carCodeList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response carCodeList(){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		list= restAPIService.listControl("carCodeList", dataMap);

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
    //------------------------------ Taewoon 운행일지 차종 코드 가져오기 -----------------------------
    
    //------------------------------ Taewoon 운행일지 차량번호 가져오기 ------------------------------
    @GET
    @Path("equipCodeList/{equipCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response equipCodeList(@PathParam("equipCd") final String equipCd){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("equipCd", equipCd);
    		
    		list= restAPIService.listControl("equipCodeList", dataMap);

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
    
    //------------------------------ Taewoon 운행일지 차량번호 가져오기 ------------------------------
    @GET
    @Path("allEquipCodeList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allEquipCodeList(){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		list= restAPIService.listControl("allEquipCodeList", dataMap);

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
    
    //------------------------------ Taewoon 운행일지 최근 등록한 차량번호 가져오기 ------------------------------
    @GET
    @Path("regEquipCode/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response regEquipCode(@PathParam("userid") final String userid){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("userid", userid);
    		
    		map = restAPIService.mapDataControl("regEquipCode", dataMap);

    		jsonObject.put("datas", map);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------ Taewoon 운행일지 최근 등록한 차량번호 가져오기 ------------------------------
    
    //------------------------------ Taewoon 운행일지 최근 등록한 데이터 카운트 가져오기 ------------------------------
    @GET
    @Path("regEquipCodeCnt/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response regEquipCodeCnt(@PathParam("userid") final String userid){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("userid", userid);
    		
    		map = restAPIService.mapDataControl("regEquipCodeCnt", dataMap);

    		jsonObject.put("datas", map);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------ Taewoon 운행일지 최근 등록한 데이터 카운트 가져오기 ------------------------------
    
    //------------------------------ Taewoon 운행일지 최근 등록한 차량번호 가져오기 ------------------------------
    @GET
    @Path("searchPrevData/{userid}/{eqKey}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchPrevData(@PathParam("userid") final String userid, @PathParam("eqKey") final String eqKey){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("userid", userid);
    		dataMap.put("eqKey", eqKey);
    		
    		map = restAPIService.mapDataControl("searchPrevData", dataMap);

    		jsonObject.put("datas", map);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------ Taewoon 운행일지 최근 등록한 차량번호 가져오기 ------------------------------
    
    //------------------------------- Taewoon 운행일지 현황 카운트 가져오기 ---------------------------
    @GET
    @Path("drivingLogCnt/{sdate}/{edate}/{carCombokey}/{eqCombokey}/{authTp}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogCnt(@PathParam("sdate") final String sdate,
    						@PathParam("edate") final String edate,
    						@PathParam("carCombokey") final String carCombokey,
							@PathParam("eqCombokey") final String eqCombokey,
							@PathParam("authTp") final String authTp,
    						@PathParam("userid") final String userid)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("sdate", sdate);
    		dataMap.put("edate", edate);
    		dataMap.put("carCombokey", carCombokey);
    		dataMap.put("eqCombokey", eqCombokey);
    		dataMap.put("authTp", authTp);
    		dataMap.put("userId", userid);
    		
    		map= restAPIService.mapDataControl("drivingLogCnt", dataMap);
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
    //------------------------------- Taewoon 운행일지 현황 카운트 가져오기 ---------------------------
    
    //------------------------------- Taewoon 비가동 현황 카운트 가져오기 ---------------------------
    @GET
    @Path("drivingLogBreakCnt/{sdate}/{edate}/{carCombokey}/{eqCombokey}/{authTp}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogBreakCnt(@PathParam("sdate") final String sdate,
    						@PathParam("edate") final String edate,
    						@PathParam("carCombokey") final String carCombokey,
							@PathParam("eqCombokey") final String eqCombokey,
							@PathParam("authTp") final String authTp,
    						@PathParam("userid") final String userid)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("sdate", sdate);
    		dataMap.put("edate", edate);
    		dataMap.put("carCombokey", carCombokey);
    		dataMap.put("eqCombokey", eqCombokey);
    		dataMap.put("authTp", authTp);
    		dataMap.put("userId", userid);
    		
    		map= restAPIService.mapDataControl("drivingLogBreakCnt", dataMap);
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
    //------------------------------- Taewoon 비가동 현황 카운트 가져오기 ---------------------------
    
    //------------------------------- Taewoon 운행일지 NFC 태그 - ID 카운트 가져오기 ---------------------------
    @GET
    @Path("chkTagDataCnt/{tag_no : (.+)?}/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response chkTagDataCnt(@PathParam("tag_no") final String tag_no
    								, @PathParam("uid") final String uid)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("tag_no", tag_no);
    		dataMap.put("uid", uid);
    		
    		map= restAPIService.mapDataControl("chkTagDataCnt", dataMap);
    		
    		jsonObject.put("count", Integer.parseInt(map.get("ALL_CNT").toString()));
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------- Taewoon 운행일지 NFC 태그 - ID 카운트 가져오기 ---------------------------
    
	//----------------------------- Taewoon 운행일지 작업 운송 코드 가져오기 ----------------------------
    @GET
    @Path("transCodeList/{transCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response transCodeList(@PathParam("transCd") final String transCd){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("transCd", transCd);
    		
    		list= restAPIService.listControl("transCodeList", dataMap);

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
    //----------------------------- Taewoon 운행일지 작업 운송 코드 가져오기-----------------------------
    
    //------------------------------- Taewoon 운행일지 정보 가져오기 --------------------------------
    @GET
    @Path("drivingLogList/{sdate}/{edate}/{carCombokey}/{eqCombokey}/{authTp}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogList(@PathParam("sdate") final String sdate,
									@PathParam("edate") final String edate,
									@PathParam("carCombokey") final String carCombokey,
									@PathParam("eqCombokey") final String eqCombokey,
									@PathParam("authTp") final String authTp,
									@PathParam("userid") final String userid)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("sdate", sdate);
    		dataMap.put("edate", edate);
    		dataMap.put("carCombokey", carCombokey);
    		dataMap.put("eqCombokey", eqCombokey);
    		dataMap.put("authTp", authTp);
    		dataMap.put("userId", userid);
    		
    		list= restAPIService.listControl("drivingLogList", dataMap);

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
    //------------------------------- Taewoon 운행일지 정보 가져오기 --------------------------------
    
    //------------------------------- Taewoon 비가동 정보 가져오기 --------------------------------
    @GET
    @Path("drivingLogBreakList/{sdate}/{edate}/{carCombokey}/{eqCombokey}/{authTp}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogBreakList(@PathParam("sdate") final String sdate,
									@PathParam("edate") final String edate,
									@PathParam("carCombokey") final String carCombokey,
									@PathParam("eqCombokey") final String eqCombokey,
									@PathParam("authTp") final String authTp,
									@PathParam("userid") final String userid)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("sdate", sdate);
    		dataMap.put("edate", edate);
    		dataMap.put("carCombokey", carCombokey);
    		dataMap.put("eqCombokey", eqCombokey);
    		dataMap.put("authTp", authTp);
    		dataMap.put("userId", userid);
    		
    		list= restAPIService.listControl("drivingLogBreakList", dataMap);

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
    //------------------------------- Taewoon 비가동 정보 가져오기 --------------------------------
    
    //-------------------------- NEW NFC 태그 터치 --------------------------------
    @GET
    @Path("newTagDrivingLogData/{tag_no : (.+)?}/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newTagDrivingLogData(@PathParam("tag_no") final String tag_no, @PathParam("uid") final String uid) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("newTagDrivingLogData : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {
    		dataMap.put("tag_no", tag_no);
    		dataMap.put("uid", uid);
        	list= restAPIService.listControl("newTagDrivingLogInfo", dataMap);
        	if(list.size()>0) {
        		jsonObject.put("status", "Yes");
        	}else {
            	jsonObject.put("status", "No");
        	}
    		 		
    		jsonObject.put("list", list);
    		jsonObject.put("count", list.size());
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }
    //-------------------------- NEW NFC 태그 터치 --------------------------------
    
    //-------------------------- OLD NFC 태그 터치 --------------------------------
    @GET
    @Path("oldTagDrivingLogData/{tag_no : (.+)?}/{uid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response oldTagDrivingLogData(@PathParam("tag_no") final String tag_no, @PathParam("uid") final String uid) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("oldTagDrivingLogData : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {
    		dataMap.put("tag_no", tag_no);
    		dataMap.put("uid", uid);
        	list= restAPIService.listControl("oldTagDrivingLogInfo", dataMap);
        	if(list.size()>0) {
        		jsonObject.put("status", "Yes");
        	}else {
            	jsonObject.put("status", "No");
        	}

    		jsonObject.put("list", list);
    		jsonObject.put("count", list.size());
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }
    //-------------------------- OLD NFC 태그 터치 --------------------------------
    
    //--------------------------- Taewoon 운행일지 MASTER INSERT -------------------------------
    @POST
    @Path("drivingLogMasterInsert")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogMasterInsert(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("input_dt", params.getFirst("input_dt"));
    		dataMap.put("equip_cd", params.getFirst("equip_cd"));
    		dataMap.put("emp_cd", params.getFirst("emp_cd"));
    		dataMap.put("work_shift", params.getFirst("work_shift"));
    		dataMap.put("fuel_qty", params.getFirst("fuel_qty"));
    		dataMap.put("element_qty", params.getFirst("element_qty"));
    		dataMap.put("hr_start_m", params.getFirst("hr_start_m"));
    		dataMap.put("hr_end_m", params.getFirst("hr_end_m"));
    		dataMap.put("transfer_memo", params.getFirst("transfer_memo"));
    		dataMap.put("create_id", params.getFirst("create_id"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		
    		restAPIService.insertControl("drivingLogMasterInsert", dataMap);

    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //--------------------------- Taewoon 운행일지 MASTER INSERT -------------------------------
    
    //--------------------------- Taewoon 운행일지 MASTER UPDATE -------------------------------
    @PUT
	@Path("drivingLogMasterModify")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response drivingLogMasterModify(MultivaluedMap<String, String> params) {
		Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	try {    	
    		dataMap.put("input_dt", params.getFirst("input_dt"));
    		dataMap.put("equip_cd", params.getFirst("equip_cd"));
    		dataMap.put("emp_cd", params.getFirst("emp_cd"));
    		dataMap.put("work_shift", params.getFirst("work_shift"));
    		dataMap.put("fuel_qty", params.getFirst("fuel_qty"));
    		dataMap.put("element_qty", params.getFirst("element_qty"));
    		dataMap.put("hr_start_m", params.getFirst("hr_start_m"));
    		dataMap.put("hr_end_m", params.getFirst("hr_end_m"));
    		dataMap.put("transfer_memo", params.getFirst("transfer_memo"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		dataMap.put("idx", params.getFirst("idx").trim());
    		
			restAPIService.updateControl("drivingLogMasterModify", dataMap);
    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    		
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();             
	}
    //--------------------------- Taewoon 운행일지 MASTER UPDATE -------------------------------
    
    //--------------------------- Taewoon 운행일지 MASTER DELETE -------------------------------
    @DELETE
	@Path("drivingLogMasterDelete/{date}/{idx}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response drivingLogMasterDelete(@PathParam("date") String date, @PathParam("idx") String idx) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
		System.out.println("drivingLogMasterDelete : dataMap="+dataMap);
		
		dataMap.put("key", idx);
		dataMap.put("date", date);
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.deleteControl("drivingLogMasterDelete", dataMap);
			jsonObject.put("status", "success");
		
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();            
	}
    //--------------------------- Taewoon 운행일지 MASTER DELETE -------------------------------
    
    //--------------------------- Taewoon 운행일지 DETAIL DELETE -------------------------------
    @DELETE
	@Path("drivingLogDetailDelete/{idx}/{seq}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response drivingLogDetailDelete(@PathParam("idx") String idx
											,@PathParam("seq") String seq) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
		System.out.println("drivingLogDetailDelete : dataMap="+dataMap);
		
		dataMap.put("key", idx);
		dataMap.put("seq", seq);
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.deleteControl("drivingLogDetailDelete", dataMap);
			jsonObject.put("status", "success");
		
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();            
	}
    //--------------------------- Taewoon 운행일지 DETAIL DELETE -------------------------------

    //------------------------------- Taewoon 운행일지 DETAIL 카운트 가져오기 ---------------------------
    @GET
    @Path("drivingLogWorkCnt/{idx}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogWorkCnt(@PathParam("idx") final String idx)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("idx", idx);
    		
    		map= restAPIService.mapDataControl("drivingLogWorkCnt", dataMap);

    		jsonObject.put("datas", map);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------- Taewoon 운행일지 DETAIL 카운트 가져오기 ---------------------------
    
    //------------------------------ Taewoon 운행일지 DETAIL 정보 가져오기 ------------------------------
    @GET
    @Path("drivingLogWorkList/{eqKey}/{idx}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogWorkList(@PathParam("eqKey") final String eqKey,
									@PathParam("idx") final String idx,
									@PathParam("userid") final String userid)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("eqKey", eqKey);
    		dataMap.put("idx", idx);
    		dataMap.put("userid", userid);
    		list= restAPIService.listControl("drivingLogWorkList", dataMap);

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
    //------------------------------ Taewoon 운행일지 DETAIL 정보 가져오기 ------------------------------
    
    //--------------------------- Taewoon 운행일지 DETAIL INSERT -------------------------------
    @POST
    @Path("drivingLogDetailInsert")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogDetailInsert(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("idx", params.getFirst("idx"));
    		dataMap.put("trans_cd", params.getFirst("trans_cd"));
    		dataMap.put("trans_nm", params.getFirst("trans_nm"));
//    		dataMap.put("trans_tm", params.getFirst("trans_tm"));
    		dataMap.put("trans_qty", params.getFirst("trans_qty"));
    		dataMap.put("trans_remarks", params.getFirst("trans_remarks"));
    		dataMap.put("create_id", params.getFirst("create_id"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		
    		SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
    		
    		Date time = new Date();
    		
    		String strTime = format1.format(time);
    		
    		System.out.println("TIME : " + strTime);
    		
    		dataMap.put("trans_tm", strTime);
    		
    		restAPIService.insertControl("drivingLogDetailInsert", dataMap);

    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //--------------------------- Taewoon 운행일지 DETAIL INSERT -------------------------------
    
    //--------------------------- Taewoon 운행일지 DETAIL UPDATE -------------------------------
    @PUT
    @Path("drivingLogDetailUpdate")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogDetailUpdate(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("idx", params.getFirst("idx"));
    		dataMap.put("dSeq", params.getFirst("dSeq"));
    		dataMap.put("trans_cd", params.getFirst("trans_cd"));
    		dataMap.put("trans_nm", params.getFirst("trans_nm"));
    		dataMap.put("trans_tm", params.getFirst("trans_tm"));
    		dataMap.put("trans_qty", params.getFirst("trans_qty"));
    		dataMap.put("trans_remarks", params.getFirst("trans_remarks"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		
    		restAPIService.updateControl("drivingLogDetailUpdate", dataMap);

    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //--------------------------- Taewoon 운행일지 DETAIL UPDATE -------------------------------
    
    //-------------------------------- Taewoon 휴지정보 가져오기 ----------------------------------
    @GET
    @Path("breakCodeList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response breakCodeList() {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("breakCodeList : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		list= restAPIService.listControl("breakCodeList", dataMap);

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
    //-------------------------------- Taewoon 휴지정보 가져오기 ----------------------------------
    
    //------------------------------ Taewoon 휴지리스트 한개 가져오기 -------------------------------
    @GET
    @Path("breakInfoItem/{sdate}/{start_tm}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response breakInfoItem(@PathParam("sdate") final String sdate
    		,@PathParam("start_tm") final String start_tm) {	
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		
    		dataMap.put("sdate", sdate);
    		dataMap.put("start_tm", start_tm);
    		list= restAPIService.listControl("breakInfoItem", dataMap);

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
    //------------------------------ Taewoon 휴지리스트 한개 가져오기 -------------------------------
    
    //------------------------------ Taewoon 휴지리스트 한개 가져오기(TAB - idx) -------------------------------
    @GET
    @Path("breakTabInfoItem/{idx}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response breakTabInfoItem(@PathParam("idx") final String idx) {	
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		
    		dataMap.put("idx", idx);

    		list= restAPIService.listControl("breakTabInfoItem", dataMap);

    		jsonObject.put("list", list);
    		jsonObject.put("count", list.size());
    		jsonObject.put("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------ Taewoon 휴지리스트 한개 가져오기(TAB - idx) -------------------------------
    
    //------------------------------ Taewoon 휴지정보 INSERT -------------------------------
    @POST
    @Path("drivingLogBreakWrite")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
    public Response drivingLogBreakWrite(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	try {    	
        	System.out.println(params);
    		dataMap.put("dis_dt", params.getFirst("dis_dt"));
    		dataMap.put("break_cd", params.getFirst("break_cd"));
    		dataMap.put("login_id", params.getFirst("login_id").trim());
    		dataMap.put("start_td", params.getFirst("start_td").trim());
    		dataMap.put("end_td", params.getFirst("end_td").trim());
    		dataMap.put("txt_etc", params.getFirst("txt_etc"));
    		
			restAPIService.insertControl("drivingLogBreakWrite", dataMap);
    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    		
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
    	return Response.status(200).entity(jsonObject).build();
    }
    //------------------------------ Taewoon 휴지정보 INSERT -------------------------------

    //------------------------------ Taewoon 휴지정보 UPDATE -------------------------------
	@PUT
	@Path("drivingLogBreakModify")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response drivingLogBreakModify(MultivaluedMap<String, String> params) {
		Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	try {    	
        	System.out.println(params);
        	dataMap.put("idx", params.getFirst("idx"));
    		dataMap.put("sdate", params.getFirst("dis_dt"));
    		dataMap.put("start_td", params.getFirst("start_td").trim());    		
    		dataMap.put("start_tm", params.getFirst("start_tm").trim());
    		dataMap.put("break_cd", params.getFirst("break_cd"));
    		dataMap.put("login_id", params.getFirst("login_id").trim());
    		dataMap.put("end_tm", params.getFirst("end_tm").trim());
    		dataMap.put("end_td", params.getFirst("end_td").trim());
    		dataMap.put("txt_etc", params.getFirst("txt_etc"));
    		
			restAPIService.insertControl("drivingLogBreakModify", dataMap);
    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    		
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();             
	}
    //------------------------------ Taewoon 휴지정보 UPDATE -------------------------------

    //------------------------------ Taewoon 휴지정보 DELETE -------------------------------
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("drivingLogBreakDelete/{idx}")
	public Response drivingLogBreakDelete(@PathParam("idx") String idx) {
		
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		System.out.println("vehcBreakDelete : dataMap="+dataMap);
		dataMap.put("idx", idx);
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.deleteControl("drivingLogBreakDelete", dataMap);
			jsonObject.put("status", "success");
		
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();                  
	}
    //------------------------------ Taewoon 휴지정보 DELETE -------------------------------
}
