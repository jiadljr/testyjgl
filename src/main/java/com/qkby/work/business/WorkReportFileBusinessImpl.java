package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.work.dao.WorkReportFileDao;
import com.qkby.work.entity.WorkReportFileInfo;
@Service
public class WorkReportFileBusinessImpl implements WorkReportFileBusiness{
	@Resource
	private WorkReportFileDao workReportFileDao;

	@Override
	public void insertReportFile(WorkReportFileInfo workReportFile) {
		workReportFileDao.insertReportFile(workReportFile);
	}

	@Override
	public List<Map<String, Object>> selectReportFile(Integer id) {
		return workReportFileDao.selectReportFile(id);
	}

	@Override
	public void deleteReportFile(Integer id) {
		workReportFileDao.deleteReportFile(id);
	}

}
