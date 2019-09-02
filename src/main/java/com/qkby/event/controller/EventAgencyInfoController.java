package com.qkby.event.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.constant.ConstantMenu;
import com.qkby.event.business.EventAgencyInfoBusiness;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Token;

@Controller
@Scope("prototype")
public class EventAgencyInfoController {
	@Resource
	EventAgencyInfoBusiness eventAgencyInfoBusiness;
	
	@RequestMapping("/toAgencyPage.do")
	public ModelAndView toEventDealPage(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		if ("1".equals(pages)) {
			String startTime = request.getParameter("startTime");
			String endTime = request.getParameter("endTime");
			String contact = request.getParameter("contact");
			String idDept = request.getParameter("idDept");
			String eventStatus = request.getParameter("eventStatus");
			
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("contact", contact);
			map.put("idDept", idDept);
			map.put("eventStatus", eventStatus);
		}
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		return new ModelAndView("service/event_agency_list",map);
	}
	
	@ResponseBody
	@RequestMapping("/findAgrncyList.do")
	public Map<String, Object> findAgrncyList(HttpServletRequest request) throws Exception{
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		if (startTime.equals("")) {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if (endTime.equals("")) {
			endTime = null;
		}
		String contact = request.getParameter("contact");
		if (contact.equals("")) {
			contact = null;
		}
		String idDept = request.getParameter("idDept");
		if (idDept.equals("")) {
			idDept = null;
		}
		List<Integer> eventStatus = new ArrayList<>();
		String status = request.getParameter("eventStatus");
		if (!"".equals(status)) {
			eventStatus.add(Integer.valueOf(status));
		}
		if (eventStatus.size()==0) {
			eventStatus = null;
		}
		pgMap.put("startTime", startTime);
		pgMap.put("endTime", endTime);
		pgMap.put("contact", contact);
		pgMap.put("idDept", idDept);
		pgMap.put("eventStatus", eventStatus);
		Integer user_id = (Integer)request.getSession().getAttribute("user_id");
		ServletContext servletContext = request.getSession().getServletContext();
		Object dutyY = servletContext.getAttribute("dutyYes");
		int dutyYes = 0;
		if(dutyY != null && dutyY != ""){
			dutyYes = Integer.parseInt(dutyY.toString());
		}
		return eventAgencyInfoBusiness.findEventAgencyInfo(user_id,dutyYes,pgMap,request);
	}
	/**
	 * 查询处理人(已对正在处理的人进行过滤)
	 * @author 李帅
	 * @param request
	 * @return 
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectDealUser.do")
	public Map<String, Object> selectDealUser(HttpServletRequest request) throws Exception{
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String event_id = request.getParameter("event_id");
		pgMap.put("event_id", event_id);
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> selectDealUser = eventAgencyInfoBusiness.selectDealUser(pgMap);
		pgMap.put("selectDealUser", selectDealUser);
		return pgMap;
	}
	/**
	 * 添加处理人
	 * @author 李帅
	 * @return String
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/addDealUser.do")
	public Map<String, Object> addDealUser(HttpServletRequest request) throws Exception{
		String dealUsers = request.getParameter("dealUsers");
		String event_id = request.getParameter("event_id");
		int eventId = Integer.valueOf(event_id);
		String[] dealUser = dealUsers.split(",");
		ServletContext context = request.getServletContext();
		context.setAttribute("addUser", "有一条新的事件需要增援!"+dealUsers);
		return eventAgencyInfoBusiness.addDealUser(eventId, dealUser);
	}
}
