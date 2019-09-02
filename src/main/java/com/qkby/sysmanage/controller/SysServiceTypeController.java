package com.qkby.sysmanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qkby.sysmanage.business.SysServiceTypeBusiness;
import com.qkby.sysmanage.entity.SysAssetsType;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysServiceType;
import com.qkby.utils.ChinesePinYin;
import com.qkby.utils.ChineseToInitials;

@Controller
public class SysServiceTypeController {

	@Resource
	SysServiceTypeBusiness sysServiceTypeBusiness;
	
	@ResponseBody
	@RequestMapping("/selectServiceTypeAll.do")
	public Map<String, Object> selectServiceTypeAll() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysServiceType> serviceTypeAll = sysServiceTypeBusiness.selectServiceTypeAll();
		List<SysServiceType> serOne = new ArrayList<SysServiceType>();
		List<SysServiceType> serTwo = new ArrayList<SysServiceType>();
		List<SysServiceType> serThree = new ArrayList<SysServiceType>();
		for(SysServiceType ser : serviceTypeAll){
			if (ser.getLevel() == 1) {
				serOne.add(ser);
			}else if (ser.getLevel() == 2) {
				serTwo.add(ser);
			}else if (ser.getLevel() == 3) {
				serThree.add(ser);
			}
			String allName=ChinesePinYin.toHanyuPinyin(ser.getName());
			ser.setExtend3(allName);
			String firstName=ChineseToInitials.getPYIndexStr(ser.getName(), true).toLowerCase();
			ser.setExtend2(firstName);
		}
		map.put("serviceOne", serOne);
		map.put("serviceTwo", serTwo);
		map.put("serviceThree", serThree);
		serOne = null;
		serTwo = null;
		serThree = null;
		return map;
	}
	@RequestMapping("/selectServiceTypePareateId.do")
	@ResponseBody
	public List<SysServiceType> selectServiceTypePareateId(HttpServletRequest req){
		String id = req.getParameter("id");
		List<SysServiceType> selectServiceTypePareateId = sysServiceTypeBusiness.selectServiceTypePareateId(Integer.valueOf(id));
		return selectServiceTypePareateId;
	}
}
