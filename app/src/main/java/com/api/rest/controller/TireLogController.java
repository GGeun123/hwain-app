package com.api.rest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
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
@Path("/TireLog")
@RequestMapping("/TireLog")
public class TireLogController {
	private RestAPIService restAPIService;
	
	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
	@Context UriInfo uriInfo;
	 
    @Context HttpServletRequest request;
    
    //------------------------------ Taewoon 타이어일지 차량번호 가져오기 ------------------------------
    @GET
    @Path("tireEquipCodeList/{equipCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireEquipCodeList(@PathParam("equipCd") final String equipCd){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("equipCd", equipCd);
    		
    		list= restAPIService.listControl("tireEquipCodeList", dataMap);

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
    //------------------------------ Taewoon 타이어일지 차량번호 가져오기 ------------------------------
    
    //------------------------------ Taewoon 타이어일지 전체 장비코드 가져오기 ------------------------------
    @GET
    @Path("allTireEquipCodeList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allTireEquipCodeList(){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		list= restAPIService.listControl("allTireEquipCodeList", dataMap);

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
    //------------------------------ Taewoon 타이어일지 전체 장비코드 가져오기 ------------------------------
    
    //------------------------------- Taewoon 타이어일지 현황 카운트 가져오기 ---------------------------
    @GET
    @Path("tireLogCnt/{sdate}/{edate}/{carCombokey}/{eqCombokey}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireLogCnt(@PathParam("sdate") final String sdate,
    						@PathParam("edate") final String edate,
    						@PathParam("carCombokey") final String carCombokey,
							@PathParam("eqCombokey") final String eqCombokey,
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
    		dataMap.put("userid", userid);
    		
    		map= restAPIService.mapDataControl("tireLogCnt", dataMap);
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
    //------------------------------- Taewoon 타이어일지 현황 카운트 가져오기 ---------------------------
    
    //------------------------------ Taewoon 타이어일지 발생장소 가져오기 ------------------------------
    /* -- 발생장소 삭제
    @GET
    @Path("tireEvPlaceList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireEvPlaceList(){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		list= restAPIService.listControl("tireEvPlaceList", dataMap);

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
    */
    //------------------------------ Taewoon 타이어일지 발생장소 가져오기 ------------------------------
    
	//----------------------------- Taewoon 타이어일지 작업 운송 코드 가져오기 ----------------------------
    @GET
    @Path("tireTransCodeList/{transCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireTransCodeList(@PathParam("transCd") final String transCd){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("transCd", transCd);
    		
    		list= restAPIService.listControl("tireTransCodeList", dataMap);

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
    //----------------------------- Taewoon 타이어일지 작업 운송 코드 가져오기-----------------------------
    
    //------------------------------- Taewoon 타이어일지 정보 가져오기 --------------------------------
    @GET
    @Path("tireLogList/{sdate}/{edate}/{carCombokey}/{eqCombokey}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireLogList(@PathParam("sdate") final String sdate,
									@PathParam("edate") final String edate,
									@PathParam("carCombokey") final String carCombokey,
									@PathParam("eqCombokey") final String eqCombokey,
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
    		dataMap.put("userid", userid);
    		list= restAPIService.listControl("tireLogList", dataMap);

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
    //------------------------------- Taewoon 타이어일지 정보 가져오기 --------------------------------
    
    //--------------------------- Taewoon 타이어일지 MASTER INSERT -------------------------------
    @POST
    @Path("tireLogMasterInsert")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireLogMasterInsert(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("input_dt", params.getFirst("input_dt"));
    		dataMap.put("equip_cd", params.getFirst("equip_cd"));
    		dataMap.put("emp_cd", params.getFirst("emp_cd"));
    		dataMap.put("hr_m", params.getFirst("hr_m"));
    		dataMap.put("input_tm", params.getFirst("input_tm"));
    		dataMap.put("end_tm", params.getFirst("end_tm"));
    		dataMap.put("occur_orign", params.getFirst("occur_orign"));
    		dataMap.put("create_id", params.getFirst("create_id"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		
    		restAPIService.insertControl("tireLogMasterInsert", dataMap);

    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //--------------------------- Taewoon 타이어일지 MASTER INSERT -------------------------------
    
    //--------------------------- Taewoon 타이어일지 MASTER UPDATE -------------------------------
    @PUT
	@Path("tireLogMasterModify")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response tireLogMasterModify(MultivaluedMap<String, String> params) {
		Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	try {    	
    		dataMap.put("input_dt", params.getFirst("input_dt"));
    		dataMap.put("hr_m", params.getFirst("hr_m"));
    		dataMap.put("input_tm", params.getFirst("input_tm"));
    		dataMap.put("end_tm", params.getFirst("end_tm"));
    		dataMap.put("occur_orign", params.getFirst("occur_orign"));
    		dataMap.put("place_cd", params.getFirst("place_cd"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		dataMap.put("idx", params.getFirst("idx").trim());
    		
			restAPIService.updateControl("tireLogMasterModify", dataMap);
    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    		
		} catch (Exception e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();             
	}
    //--------------------------- Taewoon 타이어일지 MASTER UPDATE -------------------------------
    
    //--------------------------- Taewoon 타이어일지 MASTER DELETE -------------------------------
    @DELETE
	@Path("tireLogMasterDelete/{date}/{idx}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response tireLogMasterDelete(@PathParam("date") String date, @PathParam("idx") String idx) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
		System.out.println("tireLogMasterDelete : dataMap="+dataMap);
		
		dataMap.put("key", idx);
		dataMap.put("date", date);
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.deleteControl("tireLogMasterDelete", dataMap);
			jsonObject.put("status", "success");
		
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();            
	}
    //--------------------------- Taewoon 타이어일지 MASTER DELETE -------------------------------
    
    //--------------------------- Taewoon 타이어일지 DETAIL DELETE -------------------------------
    @DELETE
	@Path("tireLogDetailDelete/{idx}/{seq}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response tireLogDetailDelete(@PathParam("idx") String idx
											,@PathParam("seq") String seq) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
		System.out.println("tireLogDetailDelete : dataMap="+dataMap);
		
		dataMap.put("key", idx);
		dataMap.put("seq", seq);
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.deleteControl("tireLogDetailDelete", dataMap);
			jsonObject.put("status", "success");
		
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();            
	}
    //--------------------------- Taewoon 타이어일지 DETAIL DELETE -------------------------------
    
	//------------------------------ Taewoon 타이어일지 그룹 정보 가져오기 ------------------------------
    @GET
    @Path("groupCodeList/{idx}/{equipCd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response groupCodeList(@PathParam("idx") final String idx,
    		@PathParam("equipCd") final String equipCd)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("idx", idx);
    		dataMap.put("equipCd", equipCd);
    		list= restAPIService.listControl("groupCodeList", dataMap);

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
    //------------------------------ Taewoon 타이어일지 그룹 정보 가져오기 ------------------------------

    //------------------------------ Taewoon 타이어일지 등록된 정보 가져오기 ------------------------------
    @GET
    @Path("tireLogWorkList/{idx}/{userid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireLogWorkList(@PathParam("idx") final String idx,
									@PathParam("userid") final String userid)
    {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("idx", idx);
    		dataMap.put("userid", userid);
    		list= restAPIService.listControl("tireLogWorkList", dataMap);

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
    //------------------------------ Taewoon 타이어일지 등록된 정보 가져오기 ------------------------------
    
    //--------------------------- Taewoon 타이어일지 DETAIL INSERT -------------------------------
    @POST
    @Path("tireLogDetailInsert")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireLogDetailInsert(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("idx", params.getFirst("idx"));
    		dataMap.put("tire_cd", params.getFirst("tire_cd"));
    		dataMap.put("tire_nm", params.getFirst("tire_nm"));
    		dataMap.put("tire_num", params.getFirst("tire_num"));
    		dataMap.put("tire_axis", params.getFirst("tire_axis"));
    		dataMap.put("tire_status", params.getFirst("tire_status"));
    		dataMap.put("tire_method", params.getFirst("tire_method"));
    		dataMap.put("create_id", params.getFirst("create_id"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		
    		restAPIService.insertControl("tireLogDetailInsert", dataMap);

    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //--------------------------- Taewoon 타이어일지 DETAIL INSERT -------------------------------
    
    //--------------------------- Taewoon 타이어일지 DETAIL UPDATE -------------------------------
    @PUT
    @Path("tireLogDetailUpdate")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireLogDetailUpdate(MultivaluedMap<String, String> params) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("idx", params.getFirst("idx"));
    		dataMap.put("dSeq", params.getFirst("dSeq"));
    		dataMap.put("tire_cd", params.getFirst("tire_cd"));
    		dataMap.put("tire_nm", params.getFirst("tire_nm"));
    		dataMap.put("tire_num", params.getFirst("tire_num"));
    		dataMap.put("tire_axis", params.getFirst("tire_axis"));
    		dataMap.put("tire_status", params.getFirst("tire_status"));
    		dataMap.put("tire_method", params.getFirst("tire_method"));
    		dataMap.put("change_id", params.getFirst("change_id"));
    		
    		restAPIService.insertControl("tireLogDetailUpdate", dataMap);

    		jsonObject.put("count", 1);
    		jsonObject.put("status", "success");
    	}catch(SQLException e) {
    		e.printStackTrace();
    		jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
    	}
    	
    	return Response.status(200).entity(jsonObject).build();
    }
    //--------------------------- Taewoon 타이어일지 DETAIL UPDATE -------------------------------
    
	//------------------------------ Taewoon 타이어일지 타이어 코드&규격 가져오기 -----------------------------
    @GET
    @Path("tireCodeList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireCodeList(){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {    		
    		list= restAPIService.listControl("tireCodeList", dataMap);

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
    //------------------------------ Taewoon 타이어일지 타이어 코드&규격 가져오기 -----------------------------
    
	//------------------------------ Taewoon 타이어일지 타이어 축&위치&상태 가져오기 -----------------------------
    @GET
    @Path("tireInfoList/{code}/{gubun}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireInfoList(@PathParam("code") final String code, @PathParam("gubun") final String gubun){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("code", code);
    		dataMap.put("gubun", gubun);
    		
    		list= restAPIService.listControl("tireInfoList2", dataMap);

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
    //------------------------------ Taewoon 타이어일지 타이어 축&위치&상태 가져오기 -----------------------------
    
    
	//------------------------------ Taewoon 타이어일지 타이어 상태 가져오기 -----------------------------
    @GET
    @Path("tireStatusList/{code}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response tireStatusList(@PathParam("code") final String code){
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	
    	try {
    		dataMap.put("code", code);
    		
    		list= restAPIService.listControl("tireInfoList", dataMap);

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
    //------------------------------ Taewoon 타이어일지 타이어 상태 가져오기 -----------------------------
}
