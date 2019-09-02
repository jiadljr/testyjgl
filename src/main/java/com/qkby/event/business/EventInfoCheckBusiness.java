package com.qkby.event.business;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface EventInfoCheckBusiness {
	/**
	 * 查询未审核列表
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String, Object> selectCheckList(HttpServletRequest request,HttpServletResponse response)throws Exception;
	/**
	 * 查询审核所需信息
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 */
	public Map<String,Object> selectCheck(HttpServletRequest request,HttpServletResponse response)throws Exception;
}
