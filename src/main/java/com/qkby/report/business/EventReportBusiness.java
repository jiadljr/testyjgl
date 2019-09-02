package com.qkby.report.business;

import java.util.Map;

public interface EventReportBusiness {
	
	/**
	 * 获取word动态数据
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	Map<String, Object> getDataMap(Map<String, Object> paramMap) throws Exception;

}
