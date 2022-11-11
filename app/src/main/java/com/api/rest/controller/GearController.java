package com.api.rest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.common.Utility;

@SuppressWarnings("unchecked")
@Controller
@Path("/Gear")
@RequestMapping("/Gear")
public class GearController {
	
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
    JSONObject globalJsonObject = new JSONObject();
    
    // The @Context annotation allows us to have certain contextual objects
    // injected into this class.
    // UriInfo object allows us to get URI information (no kidding).
    @Context
    UriInfo uriInfo;
 
    // Another "injected" object. This allows us to use the information that's
    // part of any incoming request.
    // We could, for example, get header information, or the requestor's address.
    @Context
    HttpServletRequest request;


//------------------------------------------------------- 장비 ----------------------------------------------------------
    
    //장비 카테고리 구분
    @GET
    @Path("gearGubunList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gearGubunList() {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("gearGubunList : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		list= restAPIService.listControl("gearGubunList", dataMap);

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
    
    //장비 카테고리 장비명
    @GET
    @Path("gearInfoList/gear_cd={gear_cd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gearInfoList(@PathParam("gear_cd") final String gear_cd) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("gearInfoList : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		dataMap.put("gear_cd", gear_cd);
    		list= restAPIService.listControl("gearInfoList", dataMap);

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
    
    //장비 운전자 변경
    @GET
    @Path("gearDriverList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gearDriverList() {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("gearDriverList : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		list= restAPIService.listControl("gearDriver", dataMap);

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
    
    //장비 운전자 검색
    @GET
    @Path("gearDriverList/search={search}/keyword={keyword}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response gearDriverList(@PathParam("search") final String search, @PathParam("keyword") final String keyword) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {
    		dataMap.put("search", search);
    		dataMap.put("keyword", keyword);
    		list= restAPIService.listControl("gearDriver", dataMap);

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
    
    //장비 메인 목록 화면
    @RequestMapping(value="/gearList.do",method=RequestMethod.GET)
    public ModelAndView gearList(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
            	
    	Map<String, Object> map=null;
    	try {
    		map= restAPIService.mapDataControl("gear_m_info", dataMap);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    	
    	ModelAndView mv = new ModelAndView();
        mv.addObject("map", map);
        mv.addObject("dataMap", dataMap);
        
        mv.setViewName("/gear/gear_list");
        return mv;
    }
    
    //해당 장비 날짜별 리스트
    @RequestMapping(value="/gear_dList.do")
    public ModelAndView safeCategoryList(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("gear_dList : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("gear_d_info", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    
    
    //장비 체크 작성 화면
    @RequestMapping(value="/gearCheck.do",method=RequestMethod.GET)
    public ModelAndView gearCheck(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("gearCheck : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;
    	String nowDate= Utility.getSysDate();
    	String mode="";
    	try {
//    		list= restAPIService.listControl("gearCheckList", dataMap);
    		
        	Map<String, Object> map= restAPIService.mapDataControl("gearCheckDate", dataMap);
        	if(map!=null) {
            	dataMap.put("input_date", map.get("input_date"));
            	list= restAPIService.listControl("gearCheckListRstCd", dataMap);
        	}else {
        		list= restAPIService.listControl("gearCheckList", dataMap);
        	}
        	
//    		dataMap.put("input_date", nowDate);
    		list= restAPIService.listControl("gearCheckListLoad", dataMap);
    		if(list.size()==0){
    			mode="first";
    			list= restAPIService.listControl("gearCheckList", dataMap);
    		}else{
    			mode="load";
    			list= restAPIService.listControl("gearCheckListLoad2", dataMap);
    		}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    	
    	ModelAndView mv = new ModelAndView();
        mv.addObject("dataMap", dataMap);
        mv.addObject("list", list);
        mv.addObject("listSize", list.size());
        mv.addObject("mode", mode);
        
        mv.setViewName("/gear/gear_check");
        return mv;
    }
    
    //장비 점검 저장
    @RequestMapping(value="/gearCheckInsert.do",method=RequestMethod.POST)
    public ModelAndView gearCheckInsert(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	
    	String[] check_cd = request.getParameterValues("check_cd");
    	String[] etc = request.getParameterValues("etc");
    	
    	try {
	    	System.out.println("gearCheckMasterInsert : dataMap="+dataMap);
    		restAPIService.insertControl("gearCheckMasterInsert", dataMap);
        	Map<String, Object> map= restAPIService.mapDataControl("get_gear_idx", dataMap);
        	
    		dataMap.put("idx", map.get("IDX"));
    		for (int i = 0; i < check_cd.length ; i++) {
    			dataMap.put("check_cd", check_cd[i]);
    			dataMap.put("chk_rst", dataMap.get("status["+(i+1)+"]"));
    			if(dataMap.get("status["+(i+1)+"]").equals("Y")){
    				dataMap.put("chk_rst_cd", 1);
    				dataMap.put("etc", etc[i]);
    				
    			}else{	//아니오, 긴급
    				if(dataMap.get("check["+(i+1)+"]")!=null) {
        				if(dataMap.get("check["+(i+1)+"]").equals("Y")) {
        					dataMap.put("chk_rst_cd", 1);
//        					dataMap.put("chk_rst", "Y");
        					dataMap.put("etc", etc[i]);
        				}else {
        					dataMap.put("chk_rst_cd", 2);
        					dataMap.put("etc", etc[i]);
        				}    					
    				}else {
    					dataMap.put("chk_rst_cd", 2);
    					dataMap.put("etc", etc[i]);    					
    				}
  				
    			}   			
//    	    	System.out.println("gearCheckInsert : dataMap="+dataMap);    			
    			restAPIService.insertControl("gearCheckInsert", dataMap);	
			}	
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	ModelAndView mv = new ModelAndView();
        mv.addObject("dataMap", dataMap);
        
        mv.setViewName("jsonView");
        return mv;
    }
    
    
    //장비 이력 화면
    @RequestMapping(value="/gear_check_history.do",method=RequestMethod.GET)
    public ModelAndView gear_check_history(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("gear_check_history.do : dataMap="+dataMap);
 
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("/gear/gear_check_history");
        return mv;
    }
    
    //장비점검 이력 리스트
    @RequestMapping(value="/gearCheckHistory.do",method=RequestMethod.POST)
    public ModelAndView gear_check_historyList(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("gearCheckHistory : dataMap="+dataMap);
            	
    	List<Map<String, Object>> chk_resultlist=null; 	

    	try {
    		if(dataMap.get("gear_item")!=null){
    			//디테일
    			chk_resultlist= restAPIService.listControl("gearCheckHistoryDetail", dataMap);

    		}else{
    			//마스터
    			chk_resultlist= restAPIService.listControl("gearCheckHistoryMaster", dataMap);
    			System.out.println(chk_resultlist);
    		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("chk_resultlist", chk_resultlist);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }
    
    //트랜잭션 테스트
    @RequestMapping(value="/queryTest.do",method=RequestMethod.GET)
    public void queryTest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("queryTest.do : dataMap="+dataMap);
    	
    	restAPIService.insertControl("queryTest", dataMap);
    	throw new Exception("강제로 오류를 발생시켜봄!!");

    }
   

}//c
