package com.qkby.event.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.event.business.EventInfoCheckBusiness;

@Controller
public class EventInfoCheckController {
	@Resource
	EventInfoCheckBusiness eventInfoCheckBusiness;
	/**
	 * 去审核列表界面
	 * @author 李帅
	 * @return
	 */
	@RequestMapping("toCheckList.do")
	public ModelAndView toCheckList(HttpServletRequest request) {
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
		return new ModelAndView("service/ser_check_list",map);
	}
	/**
	 * 查询审核列表信息
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectCheckList.do")
	public Map<String, Object> selectCheckList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = eventInfoCheckBusiness.selectCheckList(request, response);
		return map;
	}
	/**
	 * 去审核页面
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toCheck.do")
	public ModelAndView toCheck(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = eventInfoCheckBusiness.selectCheck(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		return new ModelAndView("service/ser_check_info",map);
		
	}
}
