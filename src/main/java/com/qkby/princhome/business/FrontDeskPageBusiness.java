package com.qkby.princhome.business;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface FrontDeskPageBusiness {
	/**
	 * 查询服务台首页信息
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public Map<String, Object> selectFrontDesk(HttpServletRequest request) throws Exception, IOException;
	
	/**
	 * 查询超时事件信息
	 * @author 李帅
	 * @param eventId
	 * @return
	 */
	public Map<String, Object> selectOverTimeSeeList(int eventId)throws Exception;
}
