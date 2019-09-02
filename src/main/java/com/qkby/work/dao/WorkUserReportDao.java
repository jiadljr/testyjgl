package com.qkby.work.dao;

import com.qkby.work.entity.WorkUserReportInfo;

public interface WorkUserReportDao {
	//新增
	public void insertUserReport(WorkUserReportInfo workUserReport);
	//删除
	public void updateUserReportDs(WorkUserReportInfo workUserReport);
	//查看
	public void updateUserReportExamine(WorkUserReportInfo workUserReport); 

}
