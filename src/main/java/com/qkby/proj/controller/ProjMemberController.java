package com.qkby.proj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.proj.business.ProjMemberBusiness;
import com.qkby.proj.entity.ProjectMembers;
import com.qkby.utils.Utils;

@Controller
@Scope("prototype")
public class ProjMemberController {
	@Resource
	private ProjMemberBusiness projMemberBusiness;
	
	/**
	 * 跳转项目成员管理界面
	 * 2018年1月16日 上午11:35:40
	 * @李帅
	 * @param
	 */
	@RequestMapping("toProjMemberPage.do")
	public ModelAndView toProjMemberPage(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		return new ModelAndView("proj/proj_member_list",map);
	}
	
	
	/**
	 * 跳转添加项目成员管理页面
	 * 2018年1月16日 上午11:35:40
	 * @李帅
	 * @param
	 */
	@RequestMapping("toAddProjMember.do")
	public ModelAndView toAddProjMember (HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		return new ModelAndView("proj/proj_member_add",map);
	}
	
	/**
	 * 添加项目成员
	 * 2018年1月16日 上午11:35:40
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("addProjMember.do")
	public String addProjMember(HttpServletRequest request) throws Exception{
		ProjectMembers projMember = new ProjectMembers();
		
		projMemberBusiness.addProjMember(projMember);
		return null;
	}
	
	/**
	 * 跳转修改项目成员管理页面
	 * 2018年1月16日 上午11:35:40
	 * @李帅
	 * @param
	 */
	@RequestMapping("toUpdateProjMember.do")
	public ModelAndView toUpdateProjMember(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		return new ModelAndView("proj/proj_member_mod",map);
	}
	
	/**
	 * 修改项目成员
	 * 2018年1月16日 上午11:35:40
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("updateProjMember.do")
	public String updateProjMember(HttpServletRequest request) throws Exception{
		ProjectMembers projMember = new ProjectMembers();
		projMemberBusiness.updateProjMember(projMember);
		return null;
	}
	
	/**
	 * 删除项目成员
	 * 2018年1月16日 上午11:35:40
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("deleteProjMember.do")
	public String deleteProjMember(HttpServletRequest request) throws Exception{
		String memver_ids = request.getParameter("memver_ids");
		projMemberBusiness.deleteProjMember(Integer.valueOf(memver_ids));
		return null;
	}
}
