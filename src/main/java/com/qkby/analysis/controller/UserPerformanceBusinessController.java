package com.qkby.analysis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.analysis.business.UserPerformanceBusiness;
import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.entity.SysServiceType;

@Controller
public class UserPerformanceBusinessController {

	@Resource
	UserPerformanceBusiness userPerformanceBusiness;
	
	@RequestMapping("/userPer.do")
	public ModelAndView deptCount() throws Exception{
		Map<String,Object> pgMap = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		Map<String, Object> dealUser = userPerformanceBusiness.dealUser(pgMap);
		return new ModelAndView("analysis/ana_perform_info",dealUser);
	}
	
	@ResponseBody
	@RequestMapping("/EventCount.do")
	public Map<String, Object> EventCount(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> paMap = new HashMap<String,Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if("".equals(endTime)){
			endTime = null;
		}
		if("".equals(startTime)){
			startTime = null;
		}
		map.put("endTime", endTime);
		map.put("startTime", startTime);
		map.put("role_id", ConstantMenu.OPS_SIX);
		List<Map<String, Object>> selectDealCount = userPerformanceBusiness.selectDealCount(map);
		List<Map<String, Object>> selectAcceptCount = userPerformanceBusiness.selectAcceptCount(map);
		paMap.put("selectDealCount", selectDealCount);
		paMap.put("selectAcceptCount", selectAcceptCount);
		return paMap;
	}
	/**
	 * <pre>selectDealCountBySer 
	 * Created by 家栋梁 on 2017年12月6日上午11:52:16  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDealCountBySer.do")
	@ResponseBody
	public Map<String,Object> selectDealCountBySer(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> pgMap = new HashMap<String,Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String dealUser = request.getParameter("dealUser");
		if("".equals(endTime)){
			endTime = null;
		}
		if("".equals(startTime)){
			startTime = null;
		}
		if("".equals(dealUser)){
			dealUser = null;
		}
		map.put("endTime", endTime);
		map.put("startTime", startTime);
		map.put("dealId", dealUser);
		List<SysServiceType> selectDealCountBySer = userPerformanceBusiness.selectDealCountBySer(map);
		List<SysServiceType> selectSerCounAll = userPerformanceBusiness.selectSerCounAll(map);
		List<Map<String, Object>> selectDealCountByDate = userPerformanceBusiness.selectDealCountByDate(map);
		pgMap.put("selectDealCountBySer", selectDealCountBySer);
		pgMap.put("selectSerCounAll", selectSerCounAll);
		pgMap.put("selectDealCountByDate", selectDealCountByDate);
		return pgMap;
	}
}
