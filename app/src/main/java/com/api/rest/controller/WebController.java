package com.api.rest.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;

import com.api.rest.dao.RestAPIService;
import com.common.DataMap;
import com.sun.jersey.api.view.Viewable;

@SuppressWarnings("unchecked")
@Controller
@Path("/Web")
public class WebController {
	
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
 
    @GET
    @Path("sample/skey={skey}")
    @Produces(MediaType.TEXT_HTML)
    public Viewable getSource(@PathParam("skey") final String skey) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	Map<String, Object> model = new HashMap<String, Object>();
    	dataMap.put("skey", skey);
        
    	Map<String, Object> map= null;
    	try {
    		map= restAPIService.mapDataControl("test", dataMap);
    		
		} catch (SQLException e) {
			e.printStackTrace();
		}        
        
        model.put("title", "Hello!");
        model.put("message", "Hello, World!");
        model.put("map", map);
        
        return new Viewable("/WEB-INF/jsp/test.jsp", model);
    }

}//c
