package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.work.dao.WorkReportDao;
import com.qkby.work.entity.WorkReportInfo;

@Service
public class WorkReportBusinessImpl implements WorkReportBusiness{
	@Resource
	private WorkReportDao workReportDao;

	@Override
	public List<WorkReportInfo> selectWorkReport(Map<String,Object> map) {
		return workReportDao.selectWorkReport(map);
	}

	@Override
	public int selectWorkReportCount(Map<String, Object> map) {
		return workReportDao.selectWorkReportCount(map);
	}

	@Override
	public int addWorkReport(WorkReportInfo workReport) {
		return workReportDao.addWorkReport(workReport);
	}

	@Override
	public WorkReportInfo selectWorkReportById(Integer id) {
		
		return workReportDao.selectWorkReportById(id);
	}

	@Override
	public void updateWorkReportDs(Map<String, Object> map) {
		workReportDao.updateWorkReportDs(map);
	}

	@Override
	public void deleteWorkReport(Integer id) {
		workReportDao.deleteWorkReport(id);
	}

	@Override
	public int updateWorkReport(WorkReportInfo workReport) {
		return workReportDao.updateWorkReport(workReport);
	}

	@Override
	public List<Map<String,Object>> selectPlanReport(Map<String,Object> map) {
		return workReportDao.selectPlanReport(map);
	}

	@Override
	public int selectPlanReportCount(Map<String, Object> map) {
		return workReportDao.selectPlanReportCount(map);
	}

	@Override
	public List<Map<String, Object>> selectReportPushMessage(Integer userId) {
		return workReportDao.selectReportPushMessage(userId);
	}

}
