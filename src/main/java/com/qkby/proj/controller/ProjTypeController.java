package com.qkby.proj.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.proj.business.ProjTypeBusiness;
import com.qkby.proj.entity.ProjectType;

@Controller
public class ProjTypeController {

	@Resource
	ProjTypeBusiness projTypeBusiness;
	
	/**
	 * 跳转项目类型配置界面
	 * 2018年3月28日 下午2:45:00
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@RequestMapping("toProjTypeConfig.do")
	public ModelAndView toProjTypeConfig() throws Exception{
		Map<String, Object> projTypeMap = projTypeBusiness.getProjTypeList();//查询项目类型List
		return new ModelAndView("proj/proj_info_type",projTypeMap);
	}
	
	/**
	 * 查询单个项目类型的全部信息
	 * 2018年3月28日 下午3:53:04
	 * @李帅
	 * @param
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@ResponseBody
	@RequestMapping("findProjTypeInform.do")
	public ProjectType findProjTypeInform(HttpServletRequest req) throws NumberFormatException, Exception{
		String typeId = req.getParameter("typeId");
		return projTypeBusiness.getProjType(Integer.valueOf(typeId));
	}
	
	/**
	 * 新建项目类型
	 * 2018年3月28日 下午3:56:48
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("insertProjTypeInform.do")
	public String insertProjType(HttpServletRequest req){
		//设置参数
		String typeName = req.getParameter("typeName");
		if("".equals(typeName)){
			typeName = null;
		}
		String typeRemark = req.getParameter("typeRemark");
		if("".equals(typeRemark)){
			typeRemark = null;
		}
		String typeColor = req.getParameter("typeColor");
		if("".equals(typeColor)){
			typeColor = null;
		}
		ProjectType projType = new ProjectType();
		projType.setName(typeName);
		projType.setColor(typeColor);
		int userId = (int) req.getSession().getAttribute("user_id");
		projType.setIdCreateUser(userId);
		projType.setDateCreate(new Date());
		projType.setRemark(typeRemark);
		projType.setDs(0);
		try {
			projTypeBusiness.insertProjTypeInform(projType);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/updateProjTypeInform.do")
	public String saveProjTypeInform(HttpServletRequest req) throws Exception{
		String typeId = req.getParameter("typeId");
		String typeName = req.getParameter("typeName");
		if("".equals(typeName)){
			typeName = null;
		}
		String typeRemark = req.getParameter("typeRemark");
		if("".equals(typeRemark)){
			typeRemark = null;
		}
		String typeColor = req.getParameter("typeColor");
		if("".equals(typeColor)){
			typeColor = null;
		}
		ProjectType projType = new ProjectType();
		projType.setId(Integer.valueOf(typeId));
		projType.setName(typeName);
		projType.setColor(typeColor);
		int userId = (int) req.getSession().getAttribute("user_id");
		projType.setIdUpdateUser(userId);
		projType.setDateUpdate(new Date());
		projType.setRemark(typeRemark);
		try {
			projTypeBusiness.updateProjTypeInform(projType);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "success";
	}
	/**
	 * 删除项目类型
	 * 2018年3月28日 下午6:13:39
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("deleteProjType.do")
	public String deleteProjType(HttpServletRequest req){
		String typeId = req.getParameter("typeId");
		try {
			projTypeBusiness.delProjType(Integer.valueOf(typeId));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("changeProjTypeRemark.do")
	public ProjectType changeProjTypeRemark(HttpServletRequest req) throws Exception{
		String typeId = req.getParameter("typeId");
		return projTypeBusiness.getProjType(Integer.valueOf(typeId));
	}
	
	@ResponseBody
	@RequestMapping("findProjTypeList.do")
	public List<ProjectType> findProjTypeList() throws Exception{
		return projTypeBusiness.projTypeList();
	}
}