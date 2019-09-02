package com.qkby.analysis.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.sysmanage.dao.SysServiceTypeDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysServiceType;
import com.qkby.sysmanage.entity.SysUserInfo;

@Service("UserPerformanceBusiness")
public class UserPerformanceBusinessImpl implements UserPerformanceBusiness{
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	SysServiceTypeDao sysServiceTypeDao;
	@Resource
	EventInfoDao eventInfoDao;
	
	@Override
	public List<Map<String, Object>> selectDealCount(Map<String,Object> map) throws Exception {
		return eventInfoDealDao.selectDealCount(map);
	}

	@Override
	public List<Map<String, Object>> selectAcceptCount(Map<String, Object> map) throws Exception {
		return eventInfoDao.selectAcceptNameCount(map);
	}

	@Override
	public List<SysServiceType> selectDealCountBySer(Map<String, Object> map) throws Exception {
		return sysServiceTypeDao.selectDealCountBySer(map);
	}

	@Override
	public List<SysServiceType> selectSerCounAll(Map<String, Object> map) throws Exception {
		return sysServiceTypeDao.selectSerCounAll(map);
	}

	@Override
	public List<Map<String, Object>> selectDealCountByDate(Map<String, Object> map) throws Exception {
		return eventInfoDealDao.selectDealCountByDate(map);
	}

	@Override
	public Map<String, Object> dealUser(Map<String, Object> pgMap) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> userAll = sysUserInfoDao.userByRoleType(map);// 运维人员
		map.put("userAll", userAll);
		return map;
	}
	
	
}
