package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.dao.SysServiceTypeDao;
import com.qkby.sysmanage.entity.SysServiceType;

@Service("SysServiceTypeBusiness")
public class SysServiceTypeBusinessImpl implements SysServiceTypeBusiness{

	@Resource
	SysServiceTypeDao sysServiceTypeDao;
	
	@Override
	public List<SysServiceType> selectServiceTypeAll() throws Exception {
		return sysServiceTypeDao.selectServiceTypeAll();
	}

	@Override
	public SysServiceType selectServiceApply(Map<String,Object> map) throws Exception {
		SysServiceType selectServiceApply = sysServiceTypeDao.selectServiceApply(map);
		if(selectServiceApply == null){
			throw new BusinessException();
		}
		return selectServiceApply;
	}

	@Override
	public List<SysServiceType> selectServicePercentByTime(Map<String, Object> paramMap) throws Exception {
		return sysServiceTypeDao.selectServicePercentByTime(paramMap);
	}

	@Override
	public List<SysServiceType> selectServiceTypePareateId(Integer id) {
		return sysServiceTypeDao.selectServiceTypePareateId(id);
	}

}
