package com.qkby.proj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.contract.business.CapitalSourceBusiness;
import com.qkby.contract.entity.CapitalSource;
import com.qkby.proj.business.ProjManageBusiness;

@Controller
public class ProjectBoardController {
	@Resource
	ProjManageBusiness projManageBusiness;
	@Resource
	private CapitalSourceBusiness capitalSourceBusiness;
	
	@RequestMapping("/toProjBoardConfig.do")
	public ModelAndView toProjBoardConfig(){
		List<CapitalSource> capitalSourceAll = capitalSourceBusiness.selectCapitalSourceAll();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("capitalSourceAll", capitalSourceAll);
		return new ModelAndView("proj/proj_board_list",map );
	}
	
	@ResponseBody
	@RequestMapping("updateProjectBoard.do")
	public String updateProjectBoard(HttpServletRequest req){
		String addData = req.getParameter("addData");
		String delData = req.getParameter("delData");
		projManageBusiness.changeProjBoardStatus(addData,delData);
		return "";
	}
}
