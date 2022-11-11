package com.api.rest.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface RestAPIDAO {

	//리스트 컨트롤(모든 리스트 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public List<Map<String, Object>> listControl(String queryName, Map<String, Object> dataMap) throws SQLException;

	//bean 컨트롤(모든 bean 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public Map<String, Object> mapDataControl(String queryName, Map<String, Object> dataMap) throws SQLException;
	
	public int resultKeyInsertControl(String queryName, Map<String, Object> dataMap) throws SQLException;
	
	public void insertControl(String queryName, Map<String, Object> dataMap) throws SQLException;
	
	public void updateControl(String queryName, Map<String, Object> dataMap) throws SQLException;
	
	
	public void deleteControl(String queryName, Map<String, Object> dataMap) throws SQLException;	
	
	//procedure 컨트롤(모든 procedure 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김
	public void procedureControl(String queryName, Map<String, Object> dataMap) throws SQLException;	
	
	//체크
	public int check(String queryName,Map<String, Object> dataMap) throws SQLException;
}
