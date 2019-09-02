package com.qkby.logs.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.logs.dao.LogLoginInfoDao;

@Service
public class LogLoginInfoBusinessImpl implements LogLoginInfoBusiness{
	@Resource
	LogLoginInfoDao logLoginInfoDao;
	
	@Override
	public List<Map<String, Object>> selectLoginAll(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> selectLoginAll = logLoginInfoDao.selectLoginAll(map);
		return selectLoginAll;
	}

	@Override
	public int selectLogsCount(Map<String, Object> map) throws Exception {
		return logLoginInfoDao.selectLogsCount(map);
	}
}
