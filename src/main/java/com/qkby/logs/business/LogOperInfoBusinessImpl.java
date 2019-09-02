package com.qkby.logs.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;

@Service
public class LogOperInfoBusinessImpl implements LogOperInfoBusiness{
	@Resource
    LogOperInfoDao logOperInfoDao;
	
	@Override
	public int insert(LogOperInfo logOperInfo) throws Exception {
		int insert = logOperInfoDao.insert(logOperInfo);
		return insert;
	}

	@Override
	public List<Map<String, Object>> selectOperAll(Map<String,Object> map) throws Exception {
		return logOperInfoDao.selectOperAll(map);
	}

	@Override
	public int selectOperCount(Map<String, Object> map) throws Exception {
		return logOperInfoDao.selectOperCount(map);
	}
    
}
