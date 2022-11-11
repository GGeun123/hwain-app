package com.api.rest.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RestAPIService{
	
    public static final int LOGIN_OK = 1;
    public static final int PWD_MISMATCH = 2;
    public static final int NONE_ID = 3;
    
    public abstract void setrestAPIDAO(RestAPIDAO restAPIDAO);

	//리스트 컨트롤(모든 리스트 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public List<Map<String, Object>> listControl(String queryName, Map<String, Object> dataMap) throws SQLException;
	
	public int resultKeyInsertControl(String queryName, Map<String, Object> dataMap) throws SQLException;
	
	//insert 컨트롤(모든 insert 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public void insertControl(String queryName, Map<String, Object> dataMap) throws SQLException;
	
	//delete 컨트롤(모든 delete 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public void deleteControl(String queryName, Map<String, Object> conditionMap) throws SQLException;

	//데이터 컨트롤(모든 데이터 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public Map<String, Object> mapDataControl(String queryName, Map<String, Object> conditionMap) throws SQLException;
		
	//update 컨트롤(모든 update 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public void updateControl(String queryName, Map<String, Object> dataMap) throws SQLException;

    public abstract int login_check(Map<String, Object> map) throws SQLException;


}
