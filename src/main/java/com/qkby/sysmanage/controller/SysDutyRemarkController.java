package com.qkby.sysmanage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.sysmanage.business.SysDutyRemarkBusiness;
import com.qkby.sysmanage.entity.SysDutyRemarkInfo;

@Controller
public class SysDutyRemarkController {
	
	@Resource
	private SysDutyRemarkBusiness sysDutyRemarkBusiness;
	
	@RequestMapping("/selectDutyRemark.do")
	@ResponseBody
	public Map<String,Object> selectDutyRemark(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String dutyDate = request.getParameter("dutyDate");
		SysDutyRemarkInfo selectDutyRemark = sysDutyRemarkBusiness.selectDutyRemark(dutyDate);
		if(selectDutyRemark != null){
			String dutyRemark = selectDutyRemark.getDutyRemark();
			int id = selectDutyRemark.getId();
			map.put("dutyRemark", dutyRemark);
			map.put("id", id);
		}else{
			map.put("dutyRemark", 1);
		}
		return map;
	}
	@RequestMapping("/queryDutyList.do")
	public ModelAndView queryDutyList(){
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM");
		String format = sdft.format(date);
		SysDutyRemarkInfo selectDutyRemark = sysDutyRemarkBusiness.selectDutyRemark(format);
		map.put("selectDutyRemark", selectDutyRemark);
		return new ModelAndView("sys/sys_duty_time_list",map);
	}
	@RequestMapping("/updateDutyRemark.do")
	@ResponseBody
	public void updateDutyRemark(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String remark = request.getParameter("remark");
		String remarkId = request.getParameter("remarkId");
		map.put("dutyRemark", remark);
		map.put("id", remarkId);
		sysDutyRemarkBusiness.updateDutyRemark(map);
	}
}
