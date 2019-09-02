package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.work.dao.WorkPlanFileDao;
import com.qkby.work.entity.WorkPlanFileInfo;

@Service
public class WorkPlanFileBusinessImpl implements WorkPlanFileBusiness{
	@Resource WorkPlanFileDao workPlanFileDao;

	@Override
	public void insertPlanFile(WorkPlanFileInfo workPlanFile) {
		workPlanFileDao.insertPlanFile(workPlanFile);
	}

	@Override
	public List<Map<String, Object>> selectPlanFile(Integer id) {
		return workPlanFileDao.selectPlanFile(id);
	}

	@Override
	public void deletePlanFile(Integer id) {
		workPlanFileDao.deletePlanFile(id);
	}
}
