package com.qkby.sysconfig.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.sysconfig.business.SysRoleInfoBusiness;
import com.qkby.sysconfig.entity.SysRoleInfo;
import com.qkby.utils.JsonResult;
import com.qkby.utils.Token;

@Controller
public class SysRoleInfoController {
	@Resource
	SysRoleInfoBusiness sysRoleInfoBusiness;
	
	@RequestMapping("/toRoleConfig.do")
	public ModelAndView toRoleConfig(HttpServletRequest request){
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
		return new ModelAndView("/configure/con_role_list",map);
	}
	/**
	 * 根据条件查询角色信息
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectRole.do")
	public Map<String,Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map = sysRoleInfoBusiness.selectByExample(request, response);
		return map;
	}
	
	/**
	 * 跳转到角色添加页面
	 * @author 李帅
	 */
	@RequestMapping("/toAddRole.do")
	@Token(remove=false, save = true)
	public ModelAndView toAddRole(HttpServletRequest request){
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
		return new ModelAndView("/configure/con_role_add",map);
	}
	/**
	 * 添加角色操作
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/insertRole.do")
	@Token(remove=true, save = false)
	public JsonResult<String> insertRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String mass = sysRoleInfoBusiness.insert(request, response);
		return new JsonResult<String>(mass);
	}
	
	/**
	 * 跳转到修改角色页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/toUpdRole.do")
	public ModelAndView toUpdRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		SysRoleInfo roleOne = sysRoleInfoBusiness.selectByPrimaryKey(request, response);
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
		map.put("roleOne", roleOne);
		return new ModelAndView("configure/con_role_mod",map);
	}
	
	/**
	 * 修改角色操作
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/updRole.do")
	public JsonResult<String> updRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String mass = sysRoleInfoBusiness.updateByPrimaryKeySelective(request, response);
		return new JsonResult<String>(mass);
	}
	
	/**
	 * 删除角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/deleteByRoleKey.do")
	public JsonResult<Map<String,Object>> deleteByRoleKey(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysRoleInfoBusiness.deleteByPrimaryKey(request, response);
		return new JsonResult<Map<String,Object>>(map);
	}
	/**
	 * 查看角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/checkRole.do")
	public SysRoleInfo checkRole(HttpServletRequest request, HttpServletResponse response) throws Exception{
		SysRoleInfo roleOne = sysRoleInfoBusiness.selectByPrimaryKey(request, response);
		return roleOne;
	}
}
