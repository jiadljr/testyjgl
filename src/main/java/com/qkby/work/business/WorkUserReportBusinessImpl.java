package com.qkby.work.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.work.dao.WorkUserReportDao;
import com.qkby.work.entity.WorkUserReportInfo;
@Service
public class WorkUserReportBusinessImpl implements WorkUserReportBusiness{
	@Resource
	private WorkUserReportDao workUserReportDao;

	@Override
	public void insertUserReport(WorkUserReportInfo workUserReport) {
		workUserReportDao.insertUserReport(workUserReport);
	}

	@Override
	public void updateUserReportDs(WorkUserReportInfo workUserReport) {
		workUserReportDao.updateUserReportDs(workUserReport);
	}

	@Override
	public void updateUserReportExamine(WorkUserReportInfo workUserReport) {
		workUserReportDao.updateUserReportExamine(workUserReport);
	}

}
