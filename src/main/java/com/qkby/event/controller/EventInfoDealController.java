package com.qkby.event.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.event.business.EventInfoDealBusiness;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月13日下午4:12:10 
 *     
 * @version </pre>
 */
import com.qkby.utils.Token;
@Controller
public class EventInfoDealController {
	@Resource
	EventInfoDealBusiness eventInfoManageBusiness;
	/**
	 * <pre>queryDelNot (跳转至处理页面)
	 * Created by 家栋梁 on 2017年10月16日上午9:58:02  
	 *
	 * @return</pre>
	 */
	@RequestMapping("/queryDelNot.do")
	public ModelAndView queryDelNot(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		return new ModelAndView("service/ser_deal_list",map);
	}
	/**
	 * <pre>selectDealNot 
	 * Created by 家栋梁 on 2017年10月13日下午4:13:56  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDealNot.do")
	@ResponseBody
	public Map<String,Object> selectDealNot(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> selectDealNot = eventInfoManageBusiness.selectDealNot(request,response);
		return selectDealNot;
	}
	/**
	 * <pre>selectDealEnd 
	 * Created by 家栋梁 on 2017年10月13日下午4:38:41  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDealEnd.do")
	@ResponseBody
	public Map<String,Object> selectDealEnd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> selectDealEnd = eventInfoManageBusiness.selectDealEnd(request,response);
		return selectDealEnd;
	}
	@RequestMapping("/selectInformation.do")
	@ResponseBody
	public Map<String,Object> selectInformation(HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		Map<String, Object> selectInformation = eventInfoManageBusiness.selectInformation(userId);
         return selectInformation;
	}
	/**
	 * <pre>updateDealNot (处理接单)
	 * Created by 家栋梁 on 2017年10月13日下午6:07:34  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/updateDealNot.do")
	@ResponseBody
	public int updateDealNot(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return eventInfoManageBusiness.updateDealNot(request, response);
	}
	/**
	 * <pre>dealEnd (跳转至处理页面)
	 * Created by 家栋梁 on 2017年10月16日下午5:04:32  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/dealEnd.do")
	@ResponseBody
	public Map<String,Object> dealEnd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> dealEnd = eventInfoManageBusiness.dealEnd(request,response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String contact = request.getParameter("contact");
		String idDept = request.getParameter("idDept");
		String eventStatus = request.getParameter("eventStatus");
		dealEnd.put("startTime", startTime);
		dealEnd.put("endTime", endTime);
		dealEnd.put("contact", contact);
		dealEnd.put("idDept", idDept);
		dealEnd.put("eventStatus", eventStatus);
		dealEnd.put("pageNumber", pageNumber);
		dealEnd.put("pageSize", pageSize);
		dealEnd.put("totalPage", totalPage);
		dealEnd.put("totalRow", totalRow);
		return dealEnd;
	}
	/**
	 * <pre>queryDeal (跳转至处理页面)
	 * Created by 家栋梁 on 2017年12月13日上午11:45:52  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/queryDeal.do")
	@Token(remove=false, save = true)
	public ModelAndView queryDeal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> dealEnd = eventInfoManageBusiness.dealEnd(request,response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String contact = request.getParameter("contact");
		String idDept = request.getParameter("idDept");
		String eventStatus = request.getParameter("eventStatus");
		dealEnd.put("startTime", startTime);
		dealEnd.put("endTime", endTime);
		dealEnd.put("contact", contact);
		dealEnd.put("idDept", idDept);
		dealEnd.put("eventStatus", eventStatus);
		dealEnd.put("pageNumber", pageNumber);
		dealEnd.put("pageSize", pageSize);
		dealEnd.put("totalPage", totalPage);
		dealEnd.put("totalRow", totalRow);
		return new ModelAndView("service/ser_deal_info",dealEnd);
	}
	/**
	 * <pre>updateDealEnd 
	 * Created by 家栋梁 on 2017年10月16日下午6:39:29  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/updateDealEnd.do")
	@ResponseBody
	@Token(remove=true, save = false)
	public Map<String, Object> updateDealEnd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = eventInfoManageBusiness.updateDealEnd(request, response);
		return map;
	}
}
