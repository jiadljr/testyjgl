package com.qkby.analysis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qkby.analysis.business.EventInfoDayBusiness;
import com.qkby.analysis.entity.EventInfoDay;
import com.qkby.sysmanage.entity.SysServiceType;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午6:32:48 
 *     
 * @version </pre>
 */
@Controller
public class EventInfoDayController {
	@Resource
   EventInfoDayBusiness eventInfoDaoBusiness;
	/**
	 * <pre>selectDay 
	 * Created by 家栋梁 on 2017年10月25日下午6:34:39  
	 * 科室申告数量统计
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDay.do")
	@ResponseBody
	public List<EventInfoDay> selectDay() throws Exception{
		List<EventInfoDay> selectDay = eventInfoDaoBusiness.selectDay();
		return selectDay;
	}
	/**
	 * <pre>selectDepId 
	 * Created by 家栋梁 on 2017年10月30日上午11:32:41  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDepId.do")
	@ResponseBody
	public List<SysServiceType> selectDepId(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SysServiceType> selectDepId = eventInfoDaoBusiness.selectDepId(request,response);
		return selectDepId;
	}
	/**
	 * <pre>selectSerCounAll 
	 * Created by 家栋梁 on 2017年10月30日下午3:16:35  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectServiceTime.do")
	@ResponseBody
	public List<Map<String,Object>> selectSerCounAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(startTime == ""){
			startTime = null;
		}
		if(endTime == ""){
			endTime = null;
		}
		map.put("endTime", endTime);
		map.put("startTime", startTime);
		List<Map<String,Object>> selectSerCounAll = eventInfoDaoBusiness.selectSerCounAll(map);
		return selectSerCounAll;
	}
	/**
	 * <pre>selectServiceTypeApplyAll 
	 * Created by 家栋梁 on 2017年11月1日上午10:37:31  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectServiceTypeApplyAll.do")
	@ResponseBody
	public List<Map<String,Object>> selectServiceTypeApplyAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> pMap = new HashMap<String,Object>();
		Map<String,Object> map = new HashMap<String,Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if(startTime == ""){
			startTime = null;
		}
		if(endTime == ""){
			endTime = null;
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		Utils.paging(request, 0, map, pMap);
		List<Map<String,Object>> selectServiceTypeApplyAll = eventInfoDaoBusiness.selectServiceTypeApplyAll(map);
		return selectServiceTypeApplyAll;
	}
}
