package com.qkby.work.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.work.dao.WorkPlanDao;
import com.qkby.work.entity.WorkPlanInfo;
@Service
public class WorkPlanBusinessImpl implements WorkPlanBusiness{
	
	@Resource
	private WorkPlanDao workPlanDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;

	@Override
	public List<Map<String,Object>> selectWorkPlanAll(Map<String,Object> map) {
		
		return workPlanDao.selectWorkPlanAll(map);
		
	}

	@Override
	public int selectWorkCount(Map<String, Object> map) {
		return workPlanDao.selectWorkCount(map);
	}

	@Override
	public Map<String, Object> addWorkPlan() throws Exception {
		Map<String,Object> pgMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> userByRoleType = sysUserInfoDao.userByRoleType(pgMap);
		map.put("userByRoleType", userByRoleType);
		return map;
	}

	@Override
	public int  insertWorkPlan(WorkPlanInfo workPlan) {
		return workPlanDao.insertWorkPlan(workPlan);
	}

	@Override
	public void updatePlanState(Map<String, Object> map) {
		workPlanDao.updatePlanState(map);
	}

	@Override
	public void deleteWorkPlan(Map<String, Object> map) {
		workPlanDao.deleteWorkPlan(map);
	}

	@Override
	public List<Map<String,Object>> sectionWorkPlanAll(Map<String, Object> map) {
		
		return workPlanDao.sectionWorkPlanAll(map);
	}

	@Override
	public int sectionWorkCount(Map<String, Object> map) {
		
		return workPlanDao.sectionWorkCount(map);
	}

	@Override
	public int updateWorkPlan(WorkPlanInfo workPlanInfo) {
		return workPlanDao.updateWorkPlan(workPlanInfo);
	}

	@Override
	public WorkPlanInfo selectCheckById(Integer id) {
		return workPlanDao.selectCheckById(id);
	}

	@Override
	public List<Map<String, Object>> selectLookUpAll(Map<String, Object> map) {
		return workPlanDao.selectLookUpAll(map);
	}

	@Override
	public int selectLookUpCount(Map<String, Object> map) {
		return workPlanDao.selectLookUpCount(map);
	}

	@Override
	public List<Map<String, Object>> selectRemindDay(Integer userId) {
		return workPlanDao.selectRemindDay(userId);
	}

	@Override
	public List<Map<String, Object>> selectRemindWeek(Integer userId) {
		return workPlanDao.selectRemindWeek(userId);
	}

	@Override
	public List<Map<String, Object>> selectRemindCustom(Integer userId) {
		return workPlanDao.selectRemindCustom(userId);
	}

	@Override
	public List<Map<String, Object>> selectRemindMonth(Integer userId) {
		return workPlanDao.selectRemindMonth(userId);
	}

	@Override
	public List<Map<String, Object>> selectPushMessage(Integer userId) {
		return workPlanDao.selectPushMessage(userId);
	}

	@Override
	public WorkPlanInfo selectCheckPlanById(Integer id) {
		return workPlanDao.selectCheckPlanById(id);
	}

}
