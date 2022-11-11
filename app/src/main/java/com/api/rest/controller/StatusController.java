package com.api.rest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
//import com.sun.jersey.api.view.Viewable;

@SuppressWarnings("unchecked")
@Controller
@Path("/Status")
@RequestMapping("/Status")
public class StatusController {
	
	private RestAPIService restAPIService;

	public void setrestAPIService(RestAPIService restAPIService) {
		this.restAPIService = restAPIService;
	}
	
    JSONObject globalJsonObject = new JSONObject();
    
    @Context
    UriInfo uriInfo;
 
    @Context
    HttpServletRequest request;
    
    

    @RequestMapping(value="/taewoon_an_trans_status.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_trans_status(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_trans_status");
        return mv;
    }
    

    @RequestMapping(value="/trans_status_list.do",method=RequestMethod.POST)
    public ModelAndView trans_status_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("trans_status_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("trans_status_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    
    
    
    @RequestMapping(value="/taewoon_an_trans_result.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_trans_result(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_trans_result");
        return mv;
    }

    @RequestMapping(value="/trans_result_list.do",method=RequestMethod.POST)
    public ModelAndView trans_result_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("trans_result_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("trans_result_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    

    @RequestMapping(value="/trans_result_modal.do",method=RequestMethod.GET)
	public ModelAndView trans_result_modal(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView();

		Map dataMap = DataMap.getDataMap(request);
		
		
		System.out.println("dataMap:"+dataMap);
		mv.addObject("dataMap",dataMap);

    	List<Map<String, Object>> list=null;
		try {
			list= restAPIService.listControl("trans_result_modal_list", dataMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	List<Map<String, Object>> real_list=null;
		
		if(list.size()!=0){
			real_list=new ArrayList();
			for(int i=0;i<list.size();i++){
				Map map = (HashMap)list.get(i);
				Map new_map=new HashMap();
				new_map.put("jcode",map.get("장비코드"));
				new_map.put("jdate",map.get("점검일자"));
				new_map.put("jname",map.get("점검항목명칭"));
				new_map.put("jdetail",map.get("의뢰내용"));
				real_list.add(new_map);
			}
		}
		
    	mv.addObject("list", real_list);
		
		
		
		mv.setViewName("/status/trans_result_modal");
		return mv;
	}
    
    /*
    @RequestMapping(value="/trans_result_modal_list.do",method=RequestMethod.POST)
    public ModelAndView trans_result_modal_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("trans_result_modal_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("trans_result_modal_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);

        mv.setViewName("jsonView");
        return mv;
    }    
    */

    @RequestMapping(value="/taewoon_an_repair_day.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_repair_day(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_repair_day");
        return mv;
    }
    

    @RequestMapping(value="/repair_day_list.do",method=RequestMethod.POST)
    public ModelAndView repair_day_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("repair_day_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("repair_day_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);

        mv.setViewName("jsonView");
        return mv;
    }    

    @RequestMapping(value="/repair_day_modal.do",method=RequestMethod.GET)
	public ModelAndView repair_day_modal(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView();

		Map dataMap = DataMap.getDataMap(request);
		
		
		System.out.println("dataMap:"+dataMap);
		mv.addObject("dataMap",dataMap);

		mv.setViewName("/status/repair_day_modal");
		return mv;
	}

    
    @RequestMapping(value="/repair_day_save_ajax.do",method=RequestMethod.POST)
    public ModelAndView repair_day_save_ajax(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("repair_day_save_ajax : dataMap="+dataMap);
            	
//    	List<Map<String, Object>> list=null;
    	String error="";
    	try {
    		restAPIService.insertControl("repair_day_save_ajax", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
			error=e.getMessage();
		}      
    	ModelAndView mv = new ModelAndView();
    	
    	mv.addObject("error", error);

        mv.setViewName("jsonView");
        return mv;
    }    
    
    
    
    @RequestMapping(value="/taewoon_an_repair_self.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_repair_self(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_repair_self");
        return mv;
    }

	
    @RequestMapping(value="/repair_self_list.do",method=RequestMethod.POST)
    public ModelAndView repair_self_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("repair_self_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("repair_self_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    
    

    @RequestMapping(value="/repair_self_modal.do",method=RequestMethod.GET)
	public ModelAndView repair_self_modal(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv=new ModelAndView();

		Map dataMap = DataMap.getDataMap(request);
		
		
		System.out.println("dataMap:"+dataMap);
		mv.addObject("dataMap",dataMap);

    	List<Map<String, Object>> list=null;
		try {
			list= restAPIService.listControl("repair_self_modal_list", dataMap);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
    	List<Map<String, Object>> real_list=null;
		
		if(list.size()!=0){
			real_list=new ArrayList();
			for(int i=0;i<list.size();i++){
				Map map = (HashMap)list.get(i);
				Map new_map=new HashMap();
				new_map.put("jcode",map.get("장비코드"));
				new_map.put("jdate",map.get("정비일자"));
				new_map.put("jname",map.get("정비항목명"));
				new_map.put("jdetail",map.get("정비내용세부"));
				real_list.add(new_map);
			}
		}
		
    	mv.addObject("list", real_list);
		
		
		
		mv.setViewName("/status/repair_self_modal");
		return mv;
	}
    

    @RequestMapping(value="/taewoon_an_materi_status.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_materi_status(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_materi_status");
        return mv;
    }
    

    @RequestMapping(value="/materi_status_list.do",method=RequestMethod.POST)
    public ModelAndView materi_status_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("materi_status_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("materi_status_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    
    @RequestMapping(value="/taewoon_an_materi_cost.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_materi_cost(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_materi_cost");
        return mv;
    }

    @RequestMapping(value="/materi_cost_list.do",method=RequestMethod.POST)
    public ModelAndView materi_cost_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("materi_cost_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("materi_cost_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    

    @RequestMapping(value="/taewoon_an_materi_lack.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_materi_lack(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_materi_lack");
        return mv;
    }

    @RequestMapping(value="/materi_lack_list.do",method=RequestMethod.POST)
    public ModelAndView materi_lack_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("materi_lack_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("materi_lack_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    

    @RequestMapping(value="/taewoon_an_safe_status.do",method=RequestMethod.GET)
    public ModelAndView taewoon_an_safe_status(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	ModelAndView mv = new ModelAndView();
        mv.setViewName("/status/taewoon_an_safe_status");
        return mv;
    }
    


    @RequestMapping(value="/safe_status_list.do",method=RequestMethod.POST)
    public ModelAndView safe_status_list(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> dataMap = DataMap.getDataMap(request);
    	System.out.println("safe_status_list : dataMap="+dataMap);
            	
    	List<Map<String, Object>> list=null;

    	try {
    		list= restAPIService.listControl("safe_status_list", dataMap);
		} catch (SQLException e) {
			e.printStackTrace();
		}      
    	ModelAndView mv = new ModelAndView();
    	mv.addObject("list", list);
    	mv.addObject("dataMap", dataMap);

        mv.setViewName("jsonView");
        return mv;
    }    
    
    
    
}//c
