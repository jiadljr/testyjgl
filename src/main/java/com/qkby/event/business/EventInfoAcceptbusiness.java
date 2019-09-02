package com.qkby.event.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface EventInfoAcceptbusiness {
	/**
	 * 查询受理列表
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 */
	Map<String, Object> selectByExample(HttpServletRequest request,HttpServletResponse response) throws Exception;
	/**
	 * 查询受理时所需要的信息
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 */
	Map<String, Object> selectAcceptAll(HttpServletRequest request,HttpServletResponse response) throws Exception;
	
	/**
	 * 提交受理信息
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 */
	String updateAccept(HttpServletRequest request,HttpServletResponse response) throws Exception;
	/**
	 * 查看确认信息
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 */
	Map<String, Object> checkEvent(HttpServletRequest request,HttpServletResponse response) throws Exception;
	/**
	 * 提交确定信息
	 * @author 李帅
	 * @param request
	 * @return
	 */
	String sureSubmit(HttpServletRequest request) throws Exception;
	/**
	 * 申告并受理
	 * @author 李帅
	 * @param request
	 * @return
	 */
	Map<String, Object> applyAndAccept(HttpServletRequest request) throws Exception;
	/**
	 * 提交申告受理信息
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	String addApplyAndAccept(HttpServletRequest request) throws Exception;
	
	List<Map<String,Object>> selectServiceDeptCount(Map<String,Object> map);
	/**
	 * 2018年4月11日 下午12:43:22
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	Map<String, Object> exportDeptCountMethod(Map<String, Object> map) throws Exception;
}
