package com.api.rest.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.api.rest.controller.BoardController.FCM_Setting;
import com.api.rest.dao.RestAPIService;
import com.common.DataMap;
//import com.oreilly.servlet.MultipartRequest;
//import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@SuppressWarnings("unchecked")
@Path("/Safe")
@RequestMapping("/Safe")
public class SafeController {
	
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	    
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
	
	
	//----------------------------------- 안전관리 ----------------------------------------
    @GET
    @Path("safe_lCategoryList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response safe_lCategoryList() {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_lCategoryList : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		list= restAPIService.listControl("safe_lCategoryList", dataMap);

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
    
    @GET
    @Path("safe_mCategoryList/large_cd={large_cd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response safe_mCategoryList(@PathParam("large_cd") final String large_cd) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("equipInfoList : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		dataMap.put("large_cd", large_cd);
    		list= restAPIService.listControl("safe_mCategoryList", dataMap);

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
    
    //안전 점검 작성
    @RequestMapping(value="/safe_write.do",method=RequestMethod.GET)
    public ModelAndView getBoardData(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_write : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;
    	List<Map<String, Object>> chk_resultMlist=null;
    	Map<String, Object> map=null;
    	Map<String, Object> user_map=null;
    	try {
    		list= restAPIService.listControl("safe_chk_itemList", dataMap);
    		chk_resultMlist= restAPIService.listControl("safe_chk_result_mList", dataMap);
    		map= restAPIService.mapDataControl("safe_mCategoryMap", dataMap);
    		
    		//유저정보
    		dataMap.put("sid", dataMap.get("sabun_no"));
    		user_map= restAPIService.mapDataControl("sid_check_ajax", dataMap);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    	ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.addObject("chk_resultMlistSize", chk_resultMlist.size()+1);
        mv.addObject("map", map);
        mv.addObject("user_map", user_map);
        mv.addObject("dataMap", dataMap);
        mv.addObject("mode", "insert");
        mv.setViewName("/safe/safe_write");
        return mv;
    }
    
    //안전점검 작성 팁 이미지
    @GET
    @Path("safeWriteTipImage/large_cd/{large_cd}/mid_cd/{mid_cd}/chk_cd/{chk_cd}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response safeWriteTipImage(@PathParam("large_cd") final String large_cd
    		,@PathParam("mid_cd") final String mid_cd, @PathParam("chk_cd") final String chk_cd) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("equipInfoList : dataMap="+dataMap);
    	
    	JSONObject jsonObject = new JSONObject();
    	try {
			dataMap.put("large_cd", large_cd);
			dataMap.put("mid_cd", mid_cd);
			dataMap.put("chk_cd", chk_cd);
			Map<String, Object> map=restAPIService.mapDataControl("safe_chk_itemList", dataMap);	

    		jsonObject.put("datas", map);
    		jsonObject.put("status", "success");
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
       
    	return Response.status(200).entity(jsonObject).build();
    }    
    
    //안전 점검 저장
    @RequestMapping(value="/safe_chk_resultInsert.do",method=RequestMethod.POST)
    public ModelAndView safe_chk_result(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_chk_resultInsert : dataMap="+dataMap);
    	String[] chk_cd = request.getParameterValues("chk_cd");
    	String mode = request.getParameter("mode");
    	System.out.println(mode);
    	
    	try {
    		if(mode.equals("insert")) {
        		restAPIService.insertControl("safe_chk_result_mInsert", dataMap);
            	Map<String, Object> map= restAPIService.mapDataControl("get_safe_chk_idx", dataMap);
            	dataMap.put("idx", map.get("IDX"));
            	
    		}else {
    			dataMap.put("idx", dataMap.get("idx"));
    			restAPIService.updateControl("safe_chk_result_mUpdate", dataMap);
    		}
    		System.out.println("safe_chk_resultInsert after : dataMap="+dataMap);
    		
    		for (int i = 0; i < chk_cd.length ; i++) {
    			dataMap.put("chk_cd", chk_cd[i]);
    			dataMap.put("chk_measure", dataMap.get("chk_measure["+(i+1)+"]"));
    			dataMap.put("max_date", dataMap.get("max_date["+(i+1)+"]"));
    			dataMap.put("improve_user", dataMap.get("improve_user["+(i+1)+"]"));
    			dataMap.put("mea_etc", dataMap.get("mea_etc["+(i+1)+"]"));
    			dataMap.put("chk_rst_cd", dataMap.get("status["+(i+1)+"]"));
    			
    			if(mode.equals("insert")) {
        			restAPIService.insertControl("safe_chk_result_dInsert", dataMap);

    			}else {
    				restAPIService.updateControl("safe_chk_result_dUpdate", dataMap);
    			}
    			
			}
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    	ModelAndView mv = new ModelAndView();
        mv.addObject("dataMap", dataMap);
        
        mv.setViewName("jsonView");
        return mv;
    }
    
	/**
	 * 파일 업로드
	 * @throws SQLException 
	 */
	@RequestMapping("fileUpload.do")    
	public boolean fileUpload(MultipartHttpServletRequest mRequest) throws SQLException {

		boolean isSuccess = false;
		
		String uploadPath = "safeFile/";
		
		File dir = new File(uploadPath);
		System.out.println(uploadPath);

		if (!dir.isDirectory()) {
			dir.mkdirs();
		}
		
		Iterator<String> iter = mRequest.getFileNames();
		while(iter.hasNext()) {
			String uploadFileName = iter.next();
			
			MultipartFile mFile = mRequest.getFile(uploadFileName);
			String originalFileName = mFile.getOriginalFilename();
			String saveFileName = originalFileName;
			
			if(saveFileName != null && !saveFileName.equals("")) {
				if(new File(uploadPath + saveFileName).exists()) {
					String fileName=saveFileName.substring(0, saveFileName.lastIndexOf('.'));				
					String ext = saveFileName.substring(saveFileName.lastIndexOf('.')); // 확장자
					saveFileName = fileName + "_" + System.currentTimeMillis()+ext;
				}
				
				try {					
					mFile.transferTo(new File(uploadPath + saveFileName));
					isSuccess = true;
					
					System.out.println(mFile.getName());
					System.out.println(originalFileName);
					System.out.println(mFile.getSize());
					Map dataMap = DataMap.getDataMap(mRequest);
					System.out.println("dataMap="+dataMap);	
					
					dataMap.put("file_nm", saveFileName);
					dataMap.put("file_nm_org", originalFileName);
					dataMap.put("file_size", mFile.getSize());
					//restAPIService.insertControl("draw_file_info_insert", dataMap);
				} catch (IllegalStateException e) {
					e.printStackTrace();
					isSuccess = false;
				} catch (IOException e) {
					e.printStackTrace();
					isSuccess = false;
				}
			} // if end
		} // while end
		return isSuccess;
	} // fileUpload end
	
	
    //안전 점검 수정
    @RequestMapping(value="/safe_writeModify.do",method=RequestMethod.GET)
    public ModelAndView safe_writeModify(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_writeModify : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;
    	Map<String, Object> map=null;
    	Map<String, Object> user_map=null;
    	Map<String, Object> modifyMasterMap=null;
    	List<Map<String, Object>> modifyDetailList=null;
    	try {
    		list= restAPIService.listControl("safe_chk_itemList", dataMap);
    		map= restAPIService.mapDataControl("safe_mCategoryMap", dataMap);
    		
    		modifyMasterMap= restAPIService.mapDataControl("safe_chk_result_mList", dataMap);
    		modifyDetailList= restAPIService.listControl("safe_chk_result_dList", dataMap);
    		System.out.println(modifyMasterMap);
    		
    		//유저정보
    		dataMap.put("sid", dataMap.get("sabun_no"));
    		user_map= restAPIService.mapDataControl("sid_check_ajax", dataMap);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    	ModelAndView mv = new ModelAndView();
        mv.addObject("list", list);
        mv.addObject("map", map);
        mv.addObject("user_map", user_map);
        mv.addObject("dataMap", dataMap);
        mv.addObject("modifyMasterMap", modifyMasterMap);
        mv.addObject("modifyDetailList", modifyDetailList);
        mv.addObject("mode", "modify");
        mv.setViewName("/safe/safe_write");
        return mv;
    }	
    
    //안전 점검 이력 화면
    @RequestMapping(value="/safe_check_history.do",method=RequestMethod.GET)
    public ModelAndView safe_check_history(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_check_history : dataMap="+dataMap);
            	
    	List<Map<String, Object>> lCate_list=null;

    	try {
    		lCate_list= restAPIService.listControl("safe_lCategoryList", dataMap);

		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("lCate_list", lCate_list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("/safe/safe_check_history");
        return mv;
    }
    
    //안전 점검 이력 카테고리 목록
    @RequestMapping(value="/safeCategoryList.do")
    public ModelAndView safeCategoryList(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safeCategoryList : dataMap="+dataMap);
            	
    	List<Map<String, Object>> mCate_list=null;

    	try {
    		mCate_list= restAPIService.listControl("safe_mCategoryList", dataMap);

		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("mCate_list", mCate_list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }
    
    //안전 점검 이력 리스트
    @RequestMapping(value="/safe_checkList.do",method=RequestMethod.POST)
    public ModelAndView safe_check_historyList(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_checkList : dataMap="+dataMap);
            	
    	List<Map<String, Object>> chk_resultlist=null; 	

    	try {
    		if(dataMap.get("idx")!=null){
    			//디테일
    			chk_resultlist= restAPIService.listControl("safe_chk_result_dList", dataMap);

    		}else{
    			//마스터
    			chk_resultlist= restAPIService.listControl("safe_chk_result_mList", dataMap);
    			
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
    
    //안전 점검 이력 삭제
    @RequestMapping(value="/safe_chk_resultDelete.do")
    public ModelAndView safe_chk_resultDelete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_chk_resultDelete : dataMap="+dataMap);
            	
    	ModelAndView mv = new ModelAndView();
    	try {
    		restAPIService.deleteControl("safe_chk_result_mDelete", dataMap);
    		restAPIService.deleteControl("safe_chk_result_dDelete", dataMap);
    		mv.addObject("status", "success");

		} catch (SQLException e) {
			mv.addObject("status", "failed");
			e.printStackTrace();
		}
    	
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }	  
	
    
  //------------------------------------------------------- 작업기준 ----------------------------------------------------------

    //작업기준 메뉴 데이터
    @GET
    @Path("workMenuList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response workMenuList() {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
//    	System.out.println("workMenuList dataMap : "+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		list= restAPIService.listControl("workMenuList", dataMap);

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
    
    //해당 작업기준 데이터
      @GET
      @Path("workStandardList/{idx}")
      @Produces(MediaType.APPLICATION_JSON)
      public Response workStandardList(@PathParam("idx") final String idx) {
      	Map<String, Object> dataMap = DataMap.getDataMap(request);
      	dataMap.put("manual_kind", idx);
      	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      	
      	JSONObject jsonObject = new JSONObject();
      	try {   		
      		list= restAPIService.listControl("workStandardList", dataMap);
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
      
      //작업기준 검색 데이터
      @GET
      @Path("workStandardList/{idx}/search={search}/keyword={keyword}")
      @Produces(MediaType.APPLICATION_JSON)
      public Response workStandardList(@PathParam("idx") final String idx, 
      		@PathParam("search") final String search, 
      		@PathParam("keyword") final String keyword) {
      	Map<String, Object> dataMap = DataMap.getDataMap(request);
      	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      	JSONObject jsonObject = new JSONObject();
      	
      	try {
      		dataMap.put("manual_kind", idx);
      		dataMap.put("search", search);
      		dataMap.put("keyword", keyword);
      		System.out.println("search= DataMap="+dataMap);
      		
      		list= restAPIService.listControl("workStandardList", dataMap);
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
      
      
  	//----------------------------------- 자율상호주의 ----------------------------------------
      //불안전한행동유형 코드 데이터
      @GET
      @Path("peerLoveCodeList")
      @Produces(MediaType.APPLICATION_JSON)
      public Response peerLoveCodeList() {
      	Map<String, Object> dataMap = DataMap.getDataMap(request);
      	System.out.println("peerLoveCodeList : dataMap="+dataMap);
      	
      	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      	JSONObject jsonObject = new JSONObject();
      	try {    		   		
      		list= restAPIService.listControl("base_unact_code", dataMap);

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
      
      //자율상호주의 리스트
      @GET
      @Path("peerLoveCardList/{sdate}/{edate}")
      @Produces(MediaType.APPLICATION_JSON)
      public Response peerLoveCardList(@PathParam("sdate") final String sdate
      		,@PathParam("edate") final String edate) {
      	Map<String, Object> dataMap = DataMap.getDataMap(request);
      	System.out.println("peerLoveCardList : dataMap="+dataMap);
      	
      	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      	JSONObject jsonObject = new JSONObject();
      	try {    		   		
    		dataMap.put("sdate", sdate);
    		dataMap.put("edate", edate);
      		list= restAPIService.listControl("peerLoveCardList", dataMap);

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
      
      //자율상호주의 작성
  	@POST
  	@Path("peerLoveCardWrite")
  	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  	@Produces(MediaType.APPLICATION_JSON)
  	public Response peerLoveCardWrite(MultivaluedMap<String, String> params) {
  	    
  		System.out.println("params="+params);
  		
  		Map<String, Object> dataMap = DataMap.getDataMap(request);
  		
  		dataMap.put("writer_sabun", params.getFirst("writer_sabun"));
  		dataMap.put("writer_name", params.getFirst("writer_name").trim());
  		dataMap.put("peer_date", params.getFirst("peer_date"));
  		dataMap.put("peer_per", params.getFirst("peer_per").trim());
  		dataMap.put("unact_cd", params.getFirst("unact_cd"));
  		dataMap.put("peer_etc", params.getFirst("peer_etc"));
  		
  		JSONObject jsonObject = new JSONObject();
  		int resultKey=0;
  		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
  		try {
  			resultKey= restAPIService.resultKeyInsertControl("peerLoveCardWrite", dataMap);
  			
  			jsonObject.put("status", "success");
  			if(resultKey>0){
  				jsonObject.put("resultKey", resultKey);
  				dataMap.put("sabun_no", dataMap.get("peer_per"));
  				list= restAPIService.listControl("peerPushDataList", dataMap);
  				
  				if(list.size()>0){
  					Map<String, Object> map = new HashMap<String, Object>();
  		    		map= restAPIService.mapDataControl("base_unact_code", dataMap);
  					for (int i = 0; i < list.size(); i++) {
  	  					FCM_Setting.send_FCM_Notification( list.get(i).get("token").toString(), "자율상호주의", map.get("unact_nm").toString());
  					}
  					jsonObject.put("pushSend", "success");
  					jsonObject.put("pendingPath", "자율상호주의");
  				}else{
  					jsonObject.put("pushSend", "empty");
  				}
  			}
  		} catch (SQLException e) {
  			jsonObject.put("status", "failed");
  			e.printStackTrace();
  			throw new WebApplicationException(Response.Status.NOT_FOUND);
  		}
  		return Response.status(200).entity(jsonObject).build();                   
  	}
  	
      //자율상호주의 상세
      @GET
      @Path("peerLoveCardDetail/{idx}")
      @Produces(MediaType.APPLICATION_JSON)
      public Response peerLoveCardDetail(@PathParam("idx") final String idx) {
      	Map<String, Object> dataMap = DataMap.getDataMap(request);
      	System.out.println("peerLoveCardDetail dataMap : "+dataMap);
      	dataMap.put("peer_key", idx);
      	
      	Map<String, Object> map = new HashMap<String, Object>();
      	JSONObject jsonObject = new JSONObject();
      	try {   		
      		map= restAPIService.mapDataControl("peerLoveCardListDetail", dataMap);
      		JSONArray array = new JSONArray();
      		array.add(map);
      		jsonObject.put("datas", array);
      		jsonObject.put("status", "success");
  		} catch (SQLException e) {
  			e.printStackTrace();
  			jsonObject.put("status", "failed");
  			throw new WebApplicationException(Response.Status.NOT_FOUND);
  		}
         
      	return Response.status(200).entity(jsonObject).build();
      }    
      
      //자율상호주의 수정
  	@PUT
  	@Path("peerLoveCardModify/{idx}")
  	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  	@Produces(MediaType.APPLICATION_JSON)
  	public Response peerLoveCardModify(@PathParam("idx") final String idx, MultivaluedMap<String, String> params) {
  	    
  		System.out.println("params="+params);
  		Map<String, Object> dataMap = DataMap.getDataMap(request);
  		dataMap.put("peer_key", idx);
  		
  		dataMap.put("writer_sabun", params.getFirst("writer_sabun"));
  		dataMap.put("writer_name", params.getFirst("writer_name").trim());
  		dataMap.put("peer_date", params.getFirst("peer_date"));
  		dataMap.put("peer_per", params.getFirst("peer_per").trim());
  		dataMap.put("unact_cd", params.getFirst("unact_cd"));
  		dataMap.put("peer_etc", params.getFirst("peer_etc"));
  		
  		JSONObject jsonObject = new JSONObject();
  		try {
  			restAPIService.updateControl("peerLoveCardUpdate", dataMap);
  			jsonObject.put("status", "success");
  			jsonObject.put("peer_key", idx);
  		
  		} catch (SQLException e) {
  			jsonObject.put("status", "failed");
  			e.printStackTrace();
  			throw new WebApplicationException(Response.Status.NOT_FOUND);
  		}
  		System.out.println(jsonObject);
  	   return Response.status(200).entity(jsonObject).build();                   
  	}	
  	
  	//자율상호주의 삭제
  	@DELETE
  	@Produces(MediaType.APPLICATION_JSON)
  	@Path("peerLoveCardDelete/{idx}")
  	public Response peerLoveCardDelete(@PathParam("idx") String idx) {
  		
  		Map<String, Object> dataMap = DataMap.getDataMap(request);
  		System.out.println("peerLoveCardDelete : dataMap="+dataMap);
  		dataMap.put("peer_key", idx);
  		
  		JSONObject jsonObject = new JSONObject();
  		try {
  			restAPIService.deleteControl("peerLoveCardDelete", dataMap);
  			jsonObject.put("status", "success");
  		
  		} catch (SQLException e) {
  			e.printStackTrace();
  			jsonObject.put("status", "failed");
  			throw new WebApplicationException(Response.Status.NOT_FOUND);
  		}
  		System.out.println(jsonObject);
  		return Response.status(200).entity(jsonObject).build();                  
  	}      
      
}//c
