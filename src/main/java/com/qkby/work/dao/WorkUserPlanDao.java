package com.qkby.work.dao;

import java.util.List;
import java.util.Map;

import com.qkby.work.entity.WorkUserPlanInfo;

public interface WorkUserPlanDao {
	//新增推送人员中间表
	public void insertUserPlan(WorkUserPlanInfo workUserPlan);
	//推送人删除
	public void updateUserPlanDs(WorkUserPlanInfo workUserPlan);
	//查看标记
	public void updateUserPlanExamine(WorkUserPlanInfo workUserPlan);
	
	public List<Map<String,Object>> selectPlanUserById(Integer id);

}
