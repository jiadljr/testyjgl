package com.qkby.logs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qkby.exception.BusinessException;
import com.qkby.logs.business.LogLoginInfoBusiness;
import com.qkby.utils.Utils;

@Controller
public class LogLoginInfoController {
	@Resource
	LogLoginInfoBusiness logLoginInfoBusiness;
	/**
	 * <pre>selectLoginAll 
	 * Created by 家栋梁 on 2017年11月8日下午2:27:18  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectLoginAll.do")
	@ResponseBody
	public Map<String,Object> selectLoginAll(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> pgMap = new HashMap<String, Object>();
		String loginStartTime = request.getParameter("loginStartTime");
		String loginEndTime = request.getParameter("loginEndTime");
		String logOutStartTime = request.getParameter("logOutStartTime");
		String logOutEndTime = request.getParameter("logOutEndTime");
		String staffContact = request.getParameter("staffContact");
		String staffName = request.getParameter("staffName");
		String clientIP = request.getParameter("clientIP");
		String clientMac = request.getParameter("clientMac");
		if("".equals(loginStartTime)){
			loginStartTime = null;
		}
		if("".equals(clientMac)){
			clientMac = null;
		}
		if("".equals(clientIP)){
			clientIP = null;
		}
		if("".equals(staffName)){
			staffName = null;
		}
		if("".equals(staffContact)){
			staffContact = null;
		}
		if("".equals(logOutEndTime)){
			logOutEndTime = null;
		}
		if("".equals(logOutStartTime)){
			logOutStartTime = null;
		}
		if("".equals(loginEndTime)){
			loginEndTime = null;
		}
		map.put("loginStartTime", loginStartTime);
		map.put("loginEndTime", loginEndTime);
		map.put("logOutStartTime", logOutStartTime);
		map.put("logOutEndTime", logOutEndTime);
		map.put("staffContact", staffContact);
		map.put("staffName", staffName);
		map.put("clientIP", clientIP);
		map.put("clientMac", clientMac);
		int selectLogsCount = logLoginInfoBusiness.selectLogsCount(map);
		Utils.paging(request, selectLogsCount, map, pgMap);
		List<Map<String, Object>> selectLoginAll = logLoginInfoBusiness.selectLoginAll(map);
		if(selectLoginAll == null){
			throw new BusinessException("","");
		}
		pgMap.put("selectLoginAll", selectLoginAll);
		return pgMap;
	}
}
