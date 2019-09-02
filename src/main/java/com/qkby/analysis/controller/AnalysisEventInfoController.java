package com.qkby.analysis.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.analysis.business.AnalysisEventInfoBusiness;

@Controller
public class AnalysisEventInfoController {
	@Resource
	AnalysisEventInfoBusiness analysisEventInfoBusiness;
	
	/**
	 * 跳转事件查询页面
	 * @author 李帅
	 * @return
	 */
	@RequestMapping("/toAnalysisEvent.do")
	public String toAnalysisEvent(){
		return "analysis/ana_event_list";
	}
	/**
	 * 查询事件列表
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectAnalysisList.do")
	public Map<String, Object> selectAnalysisList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = analysisEventInfoBusiness.selectAnalysisList(request, response);
		return map;
	}
	/**
	 * 查询单个事件的流程
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/checkEventOne.do")
	public ModelAndView checkEventOne(HttpServletRequest request) throws Exception{
		Map<String, Object> map = analysisEventInfoBusiness.checkEventOne(request);
		return new ModelAndView("analysis/ana_event_info",map);
	}
}
