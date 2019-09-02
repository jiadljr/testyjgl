package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.work.dao.WorkUserPlanDao;
import com.qkby.work.entity.WorkUserPlanInfo;
@Service
public class WorkUserPlanBusinessImpl implements WorkUserPlanBusiness{
	@Resource
	private WorkUserPlanDao workUserPlanDao;

	@Override
	public void insertUserPlan(WorkUserPlanInfo workUserPlan) {
		workUserPlanDao.insertUserPlan(workUserPlan);
	}

	@Override
	public void updateUserPlanDs(WorkUserPlanInfo workUserPlan) {
		workUserPlanDao.updateUserPlanDs(workUserPlan);
	}

	@Override
	public void updateUserPlanExamine(WorkUserPlanInfo workUserPlan) {
		workUserPlanDao.updateUserPlanExamine(workUserPlan);
	}

	@Override
	public List<Map<String, Object>> selectPlanUserById(Integer id) {
		return workUserPlanDao.selectPlanUserById(id);
	}

}
