package com.qkby.sysmanage.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.dao.SysDutyOrderDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysDutyOrderInfo;
import com.qkby.sysmanage.entity.SysUserInfo;

@Service
public class SysDutyOrderBusinessImpl implements SysDutyOrderBusiness{
	@Resource
    private SysUserInfoDao sysUserInfoDao;
	@Resource
	private SysDutyOrderDao sysDutyOrderDao;
	
	@Override
	public Map<String, Object> queryDutyTime() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> pgMap = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> userByRoleType = sysUserInfoDao.userByRoleType(pgMap);
		map.put("userByRoleType", userByRoleType);
		return map;
	}
	
	@Override
	public void insertDutyOrder(SysDutyOrderInfo sysDutyOrder) {
		sysDutyOrderDao.insert(sysDutyOrder);
	}
	
	@Override
	public List<SysDutyOrderInfo> selectDutyOrder() {
		return sysDutyOrderDao.selectDutyOrder();
	}

	@Override
	public SysDutyOrderInfo selectDutyOrderById(Integer id) {
		return sysDutyOrderDao.selectDutyOrderById(id);
	}

	@Override
	public void updateDutyOrder(Map<String, Object> map) {
		sysDutyOrderDao.updateDutyOrder(map);
	}

	@Override
	public void deleteDutyOrder(Integer id) {
		sysDutyOrderDao.deleteDutyOrder(id);
	}
     
}
