package com.qkby.analysis.business;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface AnalysisEventInfoBusiness {
	/**
	 * 事件查询的列表
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 */
	Map<String, Object> selectAnalysisList(HttpServletRequest request,HttpServletResponse response)throws Exception;
	/**
	 * 查询单个事件
	 * @author 李帅
	 * @param request
	 * @return
	 */
	Map<String, Object> checkEventOne(HttpServletRequest request)throws Exception;
	
}
