package com.qkby.analysis.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.analysis.business.OperationsBusiness;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.proj.business.ProjTaskBusiness;
import com.qkby.proj.dao.ProjTaskDao;
import com.qkby.proj.entity.ProjectTask;
import com.qkby.sysmanage.entity.SysArrange;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月26日下午2:05:30 
 *     
 * @version </pre>
 */
@Controller
public class OperationsController {
	@Resource
	OperationsBusiness operationsBusiness;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	ProjTaskBusiness projTaskBusiness;
	
	/**
	 * <pre>untreated (查询所有未处理的事件)
	 * Created by 家栋梁 on 2017年10月26日下午2:07:28  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/untreated.do")
	@ResponseBody
	public List<Map<String, Object>> untreated(HttpServletRequest request) throws Exception{
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String eventUser = request.getParameter("eventUser");
		if (eventUser.equals("")) {
			eventUser = null;
		}
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		pgMap.put("eventUser", eventUser);
		pgMap.put("id_user_deal", userId);
		return operationsBusiness.untreated(pgMap,request);
	}
//	/**
//	 * <pre>dealInformation (本月已处理和未处理数量统计)
//	 * Created by 家栋梁 on 2017年10月26日下午2:29:46  
//	 *
//	 * @return</pre>
//	 */
//	@RequestMapping("/dealInformation.do")
//	@ResponseBody
//	public List<EventInfo> dealInformation(HttpServletRequest request, HttpServletResponse response){
//		HttpSession session = request.getSession();
//		int userId = (int) session.getAttribute("user_id");
//		return operationsBusiness.dealInformation(userId);
//	}
	/**
	 * <pre>dealCount (当月处理数量统计)
	 * Created by 家栋梁 on 2017年10月26日下午4:06:48  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/dealCount.do")
	@ResponseBody
	public List<EventInfo> dealCount(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		return operationsBusiness.dealCount(userId);
	}
	/**
	 * <pre>findDutyArrangeByName (我的排班表)
	 * Created by 家栋梁 on 2017年10月26日下午4:27:46  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/findDutyArrangeByName.do")
	@ResponseBody
	public List<SysArrange> findDutyArrangeByName(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		return operationsBusiness.findDutyArrangeByName(userId);
	}
	/**
	 * <pre>goOperations 
	 * Created by 家栋梁 on 2017年11月2日下午1:17:38  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/goOperations.do")
	public ModelAndView goOperations(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		Map<String,Integer> finishDealCount = eventInfoDealDao.selectDealEndOrNot(userId);
		map.put("finishDealCount",finishDealCount);
		 return new ModelAndView("analysis/ana_operation_info",map);
	}
	@ResponseBody
	@RequestMapping("/selectProcessed.do")
	public List<Map<String, Object>> selectProcessed(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String eventUser = request.getParameter("eventUser");
		if (eventUser.equals("")) {
			eventUser = null;
		}
		pgMap.put("eventUser", eventUser);
		pgMap.put("id_user_deal", userId);
		List<Map<String, Object>> map = operationsBusiness.selectProcessed(pgMap, request);
		return map;
	}
	/**
     * 查看自己的处理信息
     * @author 李帅
     * @param request
     * @return
	 * @throws Exception 
     */
	@RequestMapping("/showOneself.do")
	public ModelAndView showOneself(HttpServletRequest request) throws Exception{
		Map<String, Object> map = operationsBusiness.showOneself(request);
		return new ModelAndView("analysis/ana_event_info", map);
	}
	
	@ResponseBody
	@RequestMapping("getTaskWarnList.do")
	public List<ProjectTask> getTaskWarnList(HttpServletRequest request) throws Exception{
		int user_id = (int) request.getSession().getAttribute("user_id");
		List<ProjectTask> taskWarnList = projTaskBusiness.getTaskWarnList(user_id);
		
		return taskWarnList;
	}
}
