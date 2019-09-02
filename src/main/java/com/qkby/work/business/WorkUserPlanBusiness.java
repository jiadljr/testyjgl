package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import com.qkby.work.entity.WorkUserPlanInfo;

public interface WorkUserPlanBusiness {
	
	public void insertUserPlan(WorkUserPlanInfo workUserPlan);
	
	public void updateUserPlanDs(WorkUserPlanInfo workUserPlan);
	
	public void updateUserPlanExamine(WorkUserPlanInfo workUserPlan);
	
	public List<Map<String,Object>> selectPlanUserById(Integer id);

}
