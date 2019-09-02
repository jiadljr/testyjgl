package com.qkby.logs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qkby.logs.business.LogOperInfoBusiness;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security2017119    
 * Created by 家栋梁 on 2017年11月13日下午1:57:17 
 *     
 * @version </pre>
 */
@Controller
public class LogOperInfoController {
	@Resource
    LogOperInfoBusiness logOperInfoBusiness;
	/**
	 * <pre>operLog (跳转至审计管理页面)
	 * Created by 家栋梁 on 2017年12月4日下午7:45:17  
	 *
	 * @return</pre>
	 */
	@RequestMapping("/operLog.do")
	public String operLog(){
		return "configure/audit_login_list";
	}
	/**
	 * <pre>selectOperAll (查询操作日志)
	 * Created by 家栋梁 on 2017年11月14日上午11:36:38  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectOperAll.do")
	@ResponseBody
	public Map<String,Object> selectOperAll(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> pagMap = new HashMap<String,Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String staffContact = request.getParameter("staffContact");
		String staffName = request.getParameter("staffName");
		String operationType = request.getParameter("operationType");
		String operationTable = request.getParameter("operationTable");
		if("".equals(operationTable)){
			operationTable = null;
		}
		if("".equals(operationType)){
			operationType = null;
		}
		if("".equals(staffName)){
			staffName = null;
		}
		if("".equals(staffContact)){
			staffContact = null;
		}
		if("".equals(endTime)){
			endTime = null;
		}
		if("".equals(startTime)){
			startTime = null;
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("staffContact", staffContact);
		map.put("staffName", staffName);
		map.put("operationType", operationType);
		map.put("operationTable", operationTable);
		int selectOperCount = logOperInfoBusiness.selectOperCount(map);
		Utils.paging(request, selectOperCount, map, pagMap);
		List<Map<String, Object>> selectOperAll = logOperInfoBusiness.selectOperAll(map);
		pagMap.put("selectOperAll", selectOperAll);
		return pagMap;
	}
}
