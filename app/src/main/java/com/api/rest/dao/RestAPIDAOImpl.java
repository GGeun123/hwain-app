package com.api.rest.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

@SuppressWarnings("unchecked")
public class RestAPIDAOImpl  extends SqlMapClientDaoSupport implements RestAPIDAO{

	//리스트 컨트롤(모든 리스트 관련된 것들을 queryName에 쿼리명을 넣고, 나머지 값들은 map에 담아 넘김	
	@Override
	public List<Map<String, Object>> listControl(String queryName, Map<String, Object> dataMap) throws SQLException{
		// TODO Auto-generated method stub
		System.out.println(queryName);
		return super.getSqlMapClientTemplate().getSqlMapClient().queryForList(queryName, dataMap);
	}

	@Override
	public Map<String, Object> mapDataControl(String queryName, Map<String, Object> dataMap) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println(queryName);
		return (Map<String, Object>) super.getSqlMapClientTemplate().getSqlMapClient().queryForObject(queryName, dataMap);
	}

	//체크
	public int check(String queryName,Map<String, Object> dataMap) throws SQLException{
		System.out.println(queryName);
		return (Integer) super.getSqlMapClientTemplate().getSqlMapClient().queryForObject(queryName, dataMap);
	}
	
	@Override
	public int resultKeyInsertControl(String queryName, Map<String, Object> dataMap) throws SQLException {
		System.out.println(queryName);
		return (Integer) super.getSqlMapClientTemplate().getSqlMapClient().insert(queryName, dataMap);	
	}	

	@Override
	public void insertControl(String queryName, Map<String, Object> dataMap) throws SQLException {
		System.out.println(queryName);
		super.getSqlMapClientTemplate().getSqlMapClient().insert(queryName, dataMap);	
	}
	
	@Override
	public void updateControl(String queryName, Map<String, Object> dataMap) throws SQLException{
		// TODO Auto-generated method stub
		System.out.println(queryName);
		super.getSqlMapClientTemplate().getSqlMapClient().update(queryName, dataMap);
	}	

	@Override
	public void deleteControl(String queryName, Map<String, Object> dataMap) throws SQLException {
		System.out.println(queryName);
		super.getSqlMapClientTemplate().getSqlMapClient().delete(queryName, dataMap);		
	}

	@Override
	public void procedureControl(String queryName, Map<String, Object> dataMap) throws SQLException {
		System.out.println(queryName);
		super.getSqlMapClientTemplate().getSqlMapClient().queryForObject(queryName, dataMap);
		
	}
}
