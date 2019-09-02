package com.qkby.princhome.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qkby.event.entity.EventInfo;

public interface ScreenDisplayBusiness {
	
	Map<String, Object> selectScreen(HttpServletRequest request) throws Exception;
	/**
	 * 查询事件提醒数据
	 * @author 李帅
	 * @param pgMap
	 * @return
	 */
	Map<String, Object> eventAlert(HttpServletRequest request) throws Exception;
	/**
	 * 处理人情况
	 * @author 
	 * @param pgMap
	 * @return
	 */
	Map<String, Object> allDealStatus(HttpServletRequest request)throws Exception;
	/**
	 * 查询待受理信息
	 * 2018年1月5日 下午2:00:20
	 * @李帅
	 * @param
	 */
	List<EventInfo> showEventAcceptList()throws Exception;
}
