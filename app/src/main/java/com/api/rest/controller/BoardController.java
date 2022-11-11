package com.api.rest.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.json.JSONException;
import com.api.rest.dao.RestAPIService;
import com.common.DataMap;
import com.common.Utility;
import com.sun.jersey.api.view.Viewable;

@SuppressWarnings("unchecked")
@Controller
@Path("/Board")
@RequestMapping("/Board")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
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
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
      return "Hello Jersey";
    }    
    
    @GET
    @Path("notice_board/skey={skey}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getSource(@PathParam("skey") final String skey) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> model = new HashMap<String, Object>();
    	dataMap.put("skey", skey);
        
    	Map<String, Object> map= null;
    	try {
    		map= restAPIService.mapDataControl("test", dataMap);
    		System.out.println(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}        
        
        model.put("title", "Hello!");
        model.put("message", "Hello, World!");
        model.put("map", map);
        
        return new Viewable("/WEB-INF/view/notice_board/write.jsp", model);
    }    
    
    @RequestMapping(value="/notice_board/write.do",method=RequestMethod.GET)
    public ModelAndView getBoardData(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
        
    	dataMap.put("skey", dataMap.get("skey"));
    	Map<String, Object> map= null;
    	try {
    		map= restAPIService.mapDataControl("test", dataMap);
    		System.out.println(map);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}        
    	
    	ModelAndView mv = new ModelAndView();
        mv.addObject("map", map);
        mv.setViewName("/notice_board/write");
        return mv;
    }
    
    /*-------------------------------- 공지사항 -------------------------------------*/    
    //공지사항 공장별/직책별 데이터
    @GET
    @Path("noticeBoardCodeList/push_target/{push_target}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response penaltyCodeList(@PathParam("push_target") final String push_target) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	dataMap.put("push_target", push_target);
    	System.out.println("noticeBoardCodeList : dataMap="+dataMap);
    	
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {
    		if(!push_target.equals("A")){
        		list= restAPIService.listControl("noticeBoardCodeList", dataMap);
    		}

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
    
    //공지사항 목록
    @GET
    @Path("noticeBoardList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSampleListData() {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	JSONObject jsonObject = new JSONObject();
    	try {    		   		
    		list= restAPIService.listControl("noticeBoardList", dataMap);

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
  
    //공지사항 작성
	@POST
	@Path("noticeBoardWrite")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response noticeBoardWrite(MultivaluedMap<String, String> params) {
	    
		System.out.println("params="+params);
		
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		String nowDate= Utility.getSysDate();
		
		dataMap.put("push_date", nowDate);
		dataMap.put("push_target", params.getFirst("push_target"));
		dataMap.put("detail_target", params.getFirst("detail_target"));
		dataMap.put("push_title", params.getFirst("push_title"));
		dataMap.put("push_text", params.getFirst("push_text"));
		dataMap.put("writer_sabun", params.getFirst("writer_sabun").trim());
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.insertControl("noticeBoardInsert", dataMap);
			jsonObject.put("status", "success");
		} catch (SQLException e) {
			jsonObject.put("status", "failed");
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(jsonObject);
	   return Response.status(200).entity(jsonObject).build();                   
	}
	
    //공지사항 상세
    @GET
    @Path("noticeBoardDetail/{idx}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response noticeBoardDetail(@PathParam("idx") final String idx) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	dataMap.put("push_key", idx);
    	Map<String, Object> map = new HashMap<String, Object>();
    	JSONObject jsonObject = new JSONObject();
    	try {   		
    		map= restAPIService.mapDataControl("noticeBoardList", dataMap);
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
    
    //공지사항 수정
	@PUT
	@Path("noticeBoardModify/{idx}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response noticeBoardModify(@PathParam("idx") final String idx, MultivaluedMap<String, String> params) {
	    
		System.out.println("params="+params);
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		String nowDate= Utility.getSysDate();
		
		dataMap.put("push_key", idx);		
		dataMap.put("push_date", nowDate);
		dataMap.put("push_target", params.getFirst("push_target"));
		dataMap.put("detail_target", params.getFirst("detail_target"));
		dataMap.put("push_title", params.getFirst("push_title"));
		dataMap.put("push_text", params.getFirst("push_text"));
		dataMap.put("writer_sabun", params.getFirst("writer_sabun").trim());
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.updateControl("noticeBoardUpdate", dataMap);
			jsonObject.put("status", "success");
			jsonObject.put("push_key", idx);
		
		} catch (SQLException e) {
			jsonObject.put("status", "failed");
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return Response.status(200).entity(jsonObject).build();                   
	}
	
	//공지사항 삭제
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("noticeBoardDelete/{idx}")
	public Response noticeBoardDelete(@PathParam("idx") String idx) {
		
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		System.out.println("noticeBoardDelete : dataMap="+dataMap);
		dataMap.put("push_key", idx);
		
		JSONObject jsonObject = new JSONObject();
		try {
			restAPIService.deleteControl("noticeBoardDelete", dataMap);
			jsonObject.put("status", "success");
		
		} catch (SQLException e) {
			e.printStackTrace();
			jsonObject.put("status", "failed");
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();                  
	}
	
	@POST
	@Path("fcmTokenData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response fcmTokenData(MultivaluedMap<String, String> params) {
	    
//		System.out.println("fcmTokenData params="+params.get("Token"));
		
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		Map<String, Object> tokenCountMap = new HashMap<String, Object>();
		Map<String, Object> andIdCountMap = new HashMap<String, Object>();
		String nowDate= Utility.getSysDate();
		
		dataMap.put("token_date", nowDate);
		dataMap.put("token", params.getFirst("Token"));
		dataMap.put("phone_num", params.getFirst("phone_num"));
		dataMap.put("sabun_no", params.getFirst("sabun_no"));
		dataMap.put("and_id", params.getFirst("and_id"));
		
		JSONObject jsonObject = new JSONObject();
		try {
			tokenCountMap= restAPIService.mapDataControl("tokenDataCheck", dataMap);
			
			if(tokenCountMap.get("CNT").toString().equals("0")){
				andIdCountMap= restAPIService.mapDataControl("AndIDCheck", dataMap);
				
				if(andIdCountMap.get("CNT").toString().equals("0")){
//					restAPIService.deleteControl("tokenDataDelete", dataMap);
					restAPIService.insertControl("tokenDataInsert", dataMap);					
				}else{
					restAPIService.updateControl("tokenDataUpdate", dataMap);
				}
			}else{
				restAPIService.updateControl("tokenDataUpdate", dataMap);

			}
			jsonObject.put("status", "success");
		} catch (SQLException e) {
			jsonObject.put("status", "failed");
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
//		System.out.println(jsonObject);
		return Response.status(200).entity(jsonObject).build();                   
	}	
	
	@POST
	@Path("sendPushData")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response pushData(MultivaluedMap<String, String> params) {
	    
		logger.info("sendPushData params="+params);
		
		Map<String, Object> dataMap = DataMap.getDataMap(request);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String nowDate= Utility.getSysDate();
		
		dataMap.put("token_date", nowDate);
		dataMap.put("push_target", params.getFirst("push_target"));
		dataMap.put("detail_target", params.getFirst("detail_target"));
		dataMap.put("sabun_no", params.getFirst("sabun_no"));
		dataMap.put("and_id", params.getFirst("and_id"));
		logger.info("sabun_no="+params.getFirst("sabun_no"));
		logger.info("push_target="+params.getFirst("push_target"));
		
		JSONObject jsonObject = new JSONObject();
		try {
			list= restAPIService.listControl("pushDataList", dataMap);
			logger.info("list="+list);
			//Method to send Push Notification
			String title= params.getFirst("title");
			String message= params.getFirst("message");
			
			if(list.size()>0) {
				jsonObject.put("pushSend", "success");
			}else {
				jsonObject.put("pushSend", "empty");
			}
			
			jsonObject.put("count", list.size());
    		jsonObject.put("status", "successOnPush");
			
			for (int i = 0; i < list.size(); i++) {
//				logger.info("token="+list.get(i).get("token").toString());
				FCM_Setting.send_FCM_Notification( list.get(i).get("token").toString(), title, message);
			}
		} catch (SQLException e) {
			jsonObject.put("status", "failed");
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}

		return Response.status(200).entity(jsonObject).build();                   
	}
	
    public static class FCM_Setting {

        final static private String FCM_URL = "https://fcm.googleapis.com/fcm/send";
        //final static private String SERVER_KEY = "AIzaSyAQQ8nkAzI_K1opuVeWA5TGmn_CW1z0xHI";
        final static private String SERVER_KEY = "AAAApCKCqSs:APA91bFgKk32LshVGViYAsO1ZEpxKY5hMLdnCaIPXlqKPFHHInDXtu4aen70dDEKeS6bB4koiWe_JPrgvDkAalP5E0ityDqEl3g-jUwGYRs0GGdYxrkwqhEI-_mmq-IFM0SwtnXv1GXJ";
        /**
         *
         * Method to send push notification to Android FireBased Cloud messaging Server.
         *
         * @param tokenId Generated and provided from Android Client Developer
         * @param title Key which is Generated in FCM Server
         * @param message which contains actual information.
         *
         */
        static void send_FCM_Notification(String tokenId, String title, String message){
            try{

                // Create URL instance.
                URL url = new URL(FCM_URL);

                // create connection.
                HttpURLConnection conn;
                conn = (HttpURLConnection) url.openConnection();
                conn.setUseCaches(false);
                conn.setDoInput(true);
                conn.setDoOutput(true);

                //set method as POST or GET
                conn.setRequestMethod("POST");

                //pass FCM server key
                conn.setRequestProperty("Authorization","key="+SERVER_KEY);

                //Specify Message Format
                conn.setRequestProperty("Content-Type","application/json");

                //Create JSON Object & pass value
                JSONObject infoJson = new JSONObject();

                infoJson.put("title", title);
                infoJson.put("message", message);

                JSONObject json = new JSONObject();

                json.put("to",tokenId.trim());
                json.put("data", infoJson);

                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(json.toString());
                wr.flush();

                int status = 0;
                if( null != conn ){
                    status = conn.getResponseCode();
                }

                if( status != 0){
                    if( status == 200 ){
                        //SUCCESS message
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                        System.out.println("Android Notification Response : " + reader.readLine());

                    }else if(status == 401){
                        //client side error
                        logger.info("Notification Response : TokenId : " + tokenId + " Error occurred :");

                    }else if(status == 501){
                        //server side error
                    	logger.info("Notification Response : [ errorCode=ServerError ] TokenId : " + tokenId);

                    }else if( status == 503){
                        //server side error
                    	logger.info("Notification Response : FCM Service is Unavailable  TokenId : " + tokenId);
                    }
                }

            }catch(MalformedURLException mlfexception){
                // Prototcal Error
                System.out.println("Error occurred while sending push Notification!.." + mlfexception.getMessage());

            }catch(IOException mlfexception){
                //URL problem
                System.out.println("Reading URL, Error occurred while sending push Notification!.." + mlfexception.getMessage());

            }catch(JSONException jsonexception){
                //Message format error
                System.out.println("Message Format, Error occurred while sending push Notification!.." + jsonexception.getMessage());

            }catch (Exception exception) {
                //General Error or exception.
                System.out.println("Error occurred while sending push Notification!.." + exception.getMessage());

            }

        }

    }//class FCM	

}//c
