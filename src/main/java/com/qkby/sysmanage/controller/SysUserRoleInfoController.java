package com.qkby.sysmanage.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qkby.sysmanage.business.SysUserRoleInfoBusiness;
import com.qkby.sysmanage.entity.SysUserRoleInfo;
import com.qkby.utils.JsonResult;

@Controller
public class SysUserRoleInfoController {
	@Resource
	SysUserRoleInfoBusiness sysUserRoleInfoBusiness;
	/**
	 * 查询岗位id
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectByUser.do")
	public JsonResult<String> selectByUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SysUserRoleInfo> userRole = sysUserRoleInfoBusiness.selectRoleByUserId(request, response);
		String str="";
		for (int i = 0; i < userRole.size(); i++) {
			str+=userRole.get(i).getIdRole()+",";
		}
		return new JsonResult<String>(str);
	}
	/**
	 * 人员授权
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/updateRoleUser.do")
	public JsonResult<String> updateRoleUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		sysUserRoleInfoBusiness.submitUserRloe(request, response);
		return new JsonResult<String>();
	}
	
}
