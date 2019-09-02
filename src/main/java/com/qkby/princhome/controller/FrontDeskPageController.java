package com.qkby.princhome.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.princhome.business.FrontDeskPageBusiness;

@Controller
public class FrontDeskPageController {

	@Resource
	FrontDeskPageBusiness frontDeskPageBusiness;
	
	@RequestMapping("/toFrontDesk.do")
	public String toFrontDesk(){
		
		return "homepage/front_desk";
	}
	
	/**
	 * 查询服务台首页的数据
	 * @author 李帅
	 * @return
	 * @throws Exception 
	 * @throws IOException 
	 */
	@ResponseBody
	@RequestMapping("/selectFrontDesk.do")
	public Map<String, Object> selectFrontDesk(HttpServletRequest request) throws IOException, Exception{
		Map<String, Object> map = frontDeskPageBusiness.selectFrontDesk(request);
		return map;
	}
	
	@RequestMapping("/overTimeSee.do")
	public ModelAndView overTimeSee(HttpServletRequest request) throws NumberFormatException, Exception{
		String eventId = request.getParameter("eventId");
		Map<String, Object> map = frontDeskPageBusiness.selectOverTimeSeeList(Integer.valueOf(eventId));
		return new ModelAndView("homepage/over_time_list",map);
	}
}
