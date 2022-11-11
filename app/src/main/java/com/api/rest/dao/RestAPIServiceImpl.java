package com.api.rest.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

//서비스단 (컨트롤러 부분의 작업을 줄이기 위해서 쓰이는 페이지)
public class RestAPIServiceImpl implements RestAPIService{
	
	private RestAPIDAO restAPIDAO;

    public void setrestAPIDAO(RestAPIDAO restAPIDAO){
        this.restAPIDAO = restAPIDAO;
    }

	public List<Map<String, Object>> listControl(String queryName, Map<String, Object> dataMap) throws SQLException{
        return restAPIDAO.listControl(queryName, dataMap);
    }

    public Map<String, Object> mapDataControl(String queryName, Map<String, Object> dataMap) throws SQLException{
        return restAPIDAO.mapDataControl(queryName, dataMap);
    }
    
    public int resultKeyInsertControl(String queryName, Map<String, Object> dataMap)throws SQLException{
    	return restAPIDAO.resultKeyInsertControl(queryName, dataMap);
    }    

    public void insertControl(String queryName, Map<String, Object> dataMap)throws SQLException{   	
    	restAPIDAO.insertControl(queryName, dataMap);
    }

    public void updateControl(String queryName, Map<String, Object> dataMap)throws SQLException{
    	restAPIDAO.updateControl(queryName, dataMap);
    }

    public void deleteControl(String queryName, Map<String, Object> dataMap)throws SQLException{
    	restAPIDAO.deleteControl(queryName, dataMap);
    }

	//로그인시 아이디 패스워드 체크
	public int login_check(Map<String, Object> dataMap) throws SQLException{
		//입력한 아이디의 DB 패스워드 조회
//		System.out.println("login_check : dataMap="+dataMap);
		Map<String, Object> db_map= restAPIDAO.mapDataControl("login_check_logic", dataMap);
		String user_pw="";
		if(db_map!=null) user_pw = (String) db_map.get("user_pw").toString().trim();
		String param_pwd = (String) dataMap.get("password");
		int result=0;
		if(user_pw!=null&&!user_pw.isEmpty()){
			if(user_pw.equals(param_pwd)){
				result=LOGIN_OK;
			}else{
				result=PWD_MISMATCH;
			}			
		}else{
			result=NONE_ID;
		}
		return result;
	}
   
}
