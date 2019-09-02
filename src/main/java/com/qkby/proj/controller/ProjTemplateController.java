package com.qkby.proj.controller;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.exception.BusinessException;
import com.qkby.proj.business.ProjTemplateBusiness;
import com.qkby.proj.entity.TaskTemplate;
import com.qkby.proj.entity.ProjectTemplate;
import com.qkby.utils.Utils;

@Controller
public class ProjTemplateController {
	@Resource
	ProjTemplateBusiness projTemplateBusiness;
	
	
	/**
	 * 跳转模板页
	 * 2018年3月29日 下午4:40:42
	 * @author 李帅
	 * @return
	 * String
	 */
	@RequestMapping("toProjTemplate.do")
	public String toProjTemplate(){
		
		return "proj/proj_info_template";
	}
	
	/**
	 * 查询模板名称集合
	 * 2018年3月29日 下午5:41:49
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("/findProjTemplateList.do")
	public Map<String, Object> findProjTemplateList(){
		
		try {
			return projTemplateBusiness.selectProjTemplateMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 查询阶段任务名称
	 * 2018年3月29日 下午6:05:33
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("findPhaseTaskNameList.do")
	public Map<String,Object> findPhaseTaskNameList(HttpServletRequest req){
		String templateId = req.getParameter("templateId");
		Map<String,Object> phaseTaskMap = null;
		try {
			phaseTaskMap= projTemplateBusiness.selectPhaseTaskNameList(Integer.valueOf(templateId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phaseTaskMap;
	}
	
	/**
	 * 查询阶段任务的信息
	 * 2018年3月30日 下午1:13:01
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("findPhaseTaskInform.do")
	public TaskTemplate findPhaseTaskList(HttpServletRequest req){
		String phaseTaskId = req.getParameter("phaseTaskId");
		TaskTemplate phaseTaskList = null;
		try {
			phaseTaskList= projTemplateBusiness.selectPhaseTask(Integer.valueOf(phaseTaskId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return phaseTaskList;
	}
	
	@ResponseBody
	@RequestMapping("savePhaseTaskInform.do")
	public String savePhaseTaskInform(HttpServletRequest req) throws Exception{
		String phaseTaskId = req.getParameter("phaseTaskId");
		String phaseTitle = req.getParameter("phaseTitle");//阶段标题
		String phaseRemark = req.getParameter("phaseRemark");//阶段内容
		String phaseShow = req.getParameter("phaseShow");//阶段成果/文档
		if("".equals(phaseRemark))
			phaseRemark = null;
		if("".equals(phaseShow))
			phaseShow = null;
		TaskTemplate projTaskTemplate = new TaskTemplate();
		projTaskTemplate.setId(Integer.valueOf(phaseTaskId));
		projTaskTemplate.setTaskName(phaseTitle);
		projTaskTemplate.setPhaseRemark(phaseRemark);
		projTaskTemplate.setPhaseShow(phaseShow);
		try {
			projTemplateBusiness.updatePhaseTaskInform(projTaskTemplate);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("500", "保存失败");
		}
		return "success";
	}
	
	/**
	 * 跳转新建模板页
	 * 2018年3月29日 下午4:42:15
	 * @author 李帅
	 * @return
	 * String
	 */
	@RequestMapping("/toAddTemplate.do")
	public String toAddTemplate(){
		
		return "proj/proj_add_template";
	}
	
	/**
	 * 添加模板
	 * 2018年3月30日 下午1:55:18
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("addTemplate.do")
	public String addTemplate(HttpServletRequest req){
		//添加模板名称表信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String taskStr = req.getParameter("taskStr");
		//判断任务名称是否重复
		java.util.List<String> taskNameList = new ArrayList<String>();
		for (int i = 0; i < taskStr.split(",").length; i++) {
			taskNameList.add(taskStr.split(",")[i]);
		}
		boolean hasSame = Utils.hasSame(taskNameList);
		if(!hasSame){
			return "nameError";
		}
		String numStr = req.getParameter("numStr");
		if("".equals(numStr)){
			numStr = null;
		}
		ProjectTemplate projTemplate = new ProjectTemplate();
		int idCreateUser = (int) req.getSession().getAttribute("user_id");
		String templateName = req.getParameter("templateName");
		if("".equals(templateName)){
			templateName = null;
		}
		projTemplate.setTemplateName(templateName);
		projTemplate.setIdCreateUser(idCreateUser);
		projTemplate.setDateCreate(new Date());
		paramMap.put("projTemplate", projTemplate);
		paramMap.put("taskStr", taskStr);
		paramMap.put("numStr", numStr);
		try {
			projTemplateBusiness.insert(paramMap);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 跳转编辑模板页
	 * 2018年3月29日 下午4:42:18
	 * @author 李帅
	 * @return
	 * String
	 * @throws Exception 
	 */
	@RequestMapping("/toUpdateTemplate.do")
	public ModelAndView toUpdateTemplate(HttpServletRequest req) throws Exception{
		//根基模板id查询模板名称和任务列表
		int templateId = Integer.valueOf(req.getParameter("templateId"));
		Map<String,Object> map = projTemplateBusiness.selectTemplateAndPhaseTaskList(templateId);
		map.put("templateId", templateId);
		return new ModelAndView("proj/proj_update_template",map);
	}
	/**
	 * 修改模板
	 * 2018年3月30日 下午1:55:18
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("updateTemplate.do")
	public String updateTemplate(HttpServletRequest req) throws Exception{
		//修改模板名称表信息
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String templateId = req.getParameter("templateId");
		String idStr = req.getParameter("idStr");
		if("".equals(idStr)){
			idStr = null;
		}
		String taskStr = req.getParameter("taskStr");
		//判断任务名称是否重复
		java.util.List<String> taskNameList = new ArrayList<String>();
		for (int i = 0; i < taskStr.split(",").length; i++) {
			taskNameList.add(taskStr.split(",")[i]);
		}
		boolean hasSame = Utils.hasSame(taskNameList);
		if(!hasSame){
			return "nameError";
		}
		String numStr = req.getParameter("numStr");
		if("".equals(numStr)){
			numStr = null;
		}
		ProjectTemplate projTemplate = new ProjectTemplate();
		int idCreateUser = (int) req.getSession().getAttribute("user_id");
		String templateName = req.getParameter("templateName");
		if("".equals(templateName)){
			templateName = null;
		}
		projTemplate.setId(Integer.valueOf(templateId));
		projTemplate.setTemplateName(templateName);
		projTemplate.setIdUpdateUser(idCreateUser);
		projTemplate.setDateUpdate(new Date());
		paramMap.put("projTemplate", projTemplate);
		paramMap.put("templateId", templateId);
		paramMap.put("idStr", idStr);
		paramMap.put("taskStr", taskStr);
		paramMap.put("numStr", numStr);
		projTemplateBusiness.update(paramMap);
		return "success";
	}
	
	/**
	 * 删除模板
	 * 2018年4月2日 上午9:08:58
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("deleteProjTemplate.do")
	public String deleteProjTemplate(HttpServletRequest req) throws Exception{
		int templateId = Integer.valueOf(req.getParameter("templateId"));
		projTemplateBusiness.delProjTemplate(templateId);
		return "success";
	}
	
	@RequestMapping("/showProjProcess.do")
	public ModelAndView showProjProcess(HttpServletRequest request,HttpServletResponse response){
		String templateId = request.getParameter("templateId");
		Map<String, Object> map = projTemplateBusiness.drawProjProcessPicture(Integer.valueOf(templateId));
		return new ModelAndView("proj/proj_info_process",map);
	}
}
