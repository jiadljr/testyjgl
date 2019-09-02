package com.qkby.sysconfig.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qkby.sysconfig.business.SysRoleMenuInfoBusiness;
import com.qkby.sysconfig.entity.SysRoleMenuInfo;
import com.qkby.utils.JsonResult;

@Controller
public class SysRoleMenuInfoController {
	@Resource
	SysRoleMenuInfoBusiness sysRoleMenuInfoBusiness;
	/**
	 * 根据菜单ID进行查询菜单角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectByMenu.do")
	public JsonResult<String> selectByMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		List<SysRoleMenuInfo> roleMenu = sysRoleMenuInfoBusiness.selectByMenuKey(request, response);
		String strs="";
		for (SysRoleMenuInfo sysRoleInfo : roleMenu) {
			if (sysRoleInfo.getIdRole() != null) {
				strs+=sysRoleInfo.getIdRole()+",";
			}
		}
		return new JsonResult<String>(strs);
	}
	/**
	 * 添加授权
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/updateRoleMenu.do")
	public JsonResult<String> updateRoleMenu(HttpServletRequest request, HttpServletResponse response) throws Exception{
		sysRoleMenuInfoBusiness.submitMenu(request, response);
		return new JsonResult<String>();
	}
}
