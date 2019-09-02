package com.qkby.princhome.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qkby.event.entity.EventInfo;
import com.qkby.princhome.business.ScreenDisplayBusiness;

@Controller
public class ScreenDisplayController {

	@Resource
	ScreenDisplayBusiness screenDisplayBusiness;

	@RequestMapping("/toScreenDisplay.do")
	public String toScreenDisplay() {
		return "homepage/screen_display";
	}

	@ResponseBody
	@RequestMapping("/selectScreen.do")
	public Map<String, Object> selectScreen(HttpServletRequest request) throws Exception {
		Map<String, Object> map = screenDisplayBusiness.selectScreen(request);
		return map;
	}

	@ResponseBody
	@RequestMapping("/eventAlert.do")
	public Map<String, Object> eventAlert(HttpServletRequest request) throws Exception{
		return screenDisplayBusiness.eventAlert(request);
	}

	@ResponseBody
	@RequestMapping("/allDealStatus.do")
	public Map<String, Object> allDealStatus(HttpServletRequest request) throws Exception {
		Map<String, Object> map = screenDisplayBusiness.allDealStatus(request);
		return map;
	}

	@ResponseBody
	@RequestMapping("/showEventAccept.do")
	public List<EventInfo> showEventAccept() throws Exception {
		List<EventInfo> notAcceptList = screenDisplayBusiness.showEventAcceptList();
		return notAcceptList;
	}
}
