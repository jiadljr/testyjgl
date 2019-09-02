package com.qkby.work.business;

import com.qkby.work.entity.WorkUserReportInfo;

public interface WorkUserReportBusiness {
	
	public void insertUserReport(WorkUserReportInfo workUserReport);
	
	public void updateUserReportDs(WorkUserReportInfo workUserReport);
	
	public void updateUserReportExamine(WorkUserReportInfo workUserReport);

}
