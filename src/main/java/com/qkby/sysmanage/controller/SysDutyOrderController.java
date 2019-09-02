package com.qkby.sysmanage.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.sysmanage.business.SysDutyOrderBusiness;
import com.qkby.sysmanage.entity.SysDutyOrderInfo;

@Controller
public class SysDutyOrderController {
	@Resource
	private SysDutyOrderBusiness sysDutyOrderBusiness;
	/**
	 * 新增人员排班组
	 * @param request
	 */
	@RequestMapping("/insertDutyOrder.do")
	@ResponseBody
	public Map<String,Object> insertDutyOrder(HttpServletRequest request){
		String groupName = request.getParameter("groupName");
		String idUserGroup = request.getParameter("idUserGroup");
		String nameUserGroup = request.getParameter("nameUserGroup");
		String id = request.getParameter("id");
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> hasMap = new HashMap<String,Object>();
		if("".equals(id)){
			SysDutyOrderInfo sysDutyOrder = new SysDutyOrderInfo();
			sysDutyOrder.setGroupName(groupName);
			sysDutyOrder.setIdUserGroup(idUserGroup);
			sysDutyOrder.setNameUserGroup(nameUserGroup);
			sysDutyOrderBusiness.insertDutyOrder(sysDutyOrder);
			return null;
		}else{
			map.put("id", id);
			map.put("groupName", groupName);
			map.put("idUserGroup", idUserGroup);
			map.put("nameUserGroup", nameUserGroup);
			sysDutyOrderBusiness.updateDutyOrder(map);
			SysDutyOrderInfo selectDutyOrderById = sysDutyOrderBusiness.selectDutyOrderById(Integer.valueOf(id));
			List<SysDutyOrderInfo> selectDutyOrder = sysDutyOrderBusiness.selectDutyOrder();
			hasMap.put("dutyOrder", selectDutyOrder);
			hasMap.put("selectDutyOrderById", selectDutyOrderById);
			return hasMap;
		}
	}
	/**
	 * 查询排班组
	 * @return
	 */
	@RequestMapping("/selectDutyOrder.do")
	@ResponseBody
	public SysDutyOrderInfo selectDutyOrder(HttpServletRequest request){
		String id = request.getParameter("id");
		int ids = Integer.valueOf(id);
		SysDutyOrderInfo selectDutyOrderById = sysDutyOrderBusiness.selectDutyOrderById(ids);
		return selectDutyOrderById;
	}
	@RequestMapping("/deleteDutyOrder.do")
	@ResponseBody
	public void deleteDutyOrder(HttpServletRequest request){
		String id = request.getParameter("id");
		int ids = Integer.valueOf(id);
		sysDutyOrderBusiness.deleteDutyOrder(ids);
	}
	@RequestMapping("/addOrder.do")
	public ModelAndView addOrder() throws Exception{
		 Map<String, Object> queryDutyTime = sysDutyOrderBusiness.queryDutyTime();
		  List<SysDutyOrderInfo> selectDutyOrder = sysDutyOrderBusiness.selectDutyOrder();
		  queryDutyTime.put("selectDutyOrder", selectDutyOrder);
		  return new ModelAndView("sys/sys_duty_order",queryDutyTime);
	}
}
