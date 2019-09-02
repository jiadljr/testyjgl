package com.qkby.proj.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.proj.dao.ProjTaskTemplateDao;
import com.qkby.proj.dao.ProjTemplateDao;
import com.qkby.proj.entity.TaskTemplate;
import com.qkby.proj.entity.ProjectTemplate;

@Service("ProjTemplateBusiness")
public class ProjTemplateBusinessImpl implements ProjTemplateBusiness{
	
	@Resource
	ProjTaskTemplateDao projTaskTemplateDao;
	@Resource
	ProjTemplateDao projTemplateDao;
	
	
	@Override
	public Map<String, Object> selectProjTemplateMap() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//查询所有项目名称List
		List<ProjectTemplate> projTemplateList = projTemplateDao.selectTemplateInfo();
		//获取第一个阶段任务，拿到id查询阶段任务展示在页面
		if(projTemplateList.size()>0 && projTemplateList != null){
			Integer templateId = projTemplateList.get(0).getId();
			List<TaskTemplate> templateTaskList = projTaskTemplateDao.selectTemplateTaskNameById(templateId);
			map.put("templateTaskList", templateTaskList);
			//第一个阶段任务信息
			if(templateTaskList.size()>0 && templateTaskList != null){
				Integer phaseTaskId = templateTaskList.get(0).getId();
				TaskTemplate phaseTask= projTaskTemplateDao.selectTemplateTaskById(phaseTaskId);
				map.put("phaseTask", phaseTask);
			}
		}
		
		map.put("projTemplateList", projTemplateList);
		return map;
	}


	@Override
	public Map<String,Object> selectPhaseTaskNameList(int templateId) throws Exception {
		Map<String,Object> map = new HashMap<>();
		List<TaskTemplate> templateTaskList = projTaskTemplateDao.selectTemplateTaskNameById(templateId);
		if(templateTaskList.size() > 0 && templateTaskList != null){
			Integer phaseTaskId = templateTaskList.get(0).getId();
			TaskTemplate phaseTask= projTaskTemplateDao.selectTemplateTaskById(phaseTaskId);
			map.put("phaseTask", phaseTask);
		}
		map.put("templateTaskList", templateTaskList);
		return map;
	}


	@Override
	public TaskTemplate selectPhaseTask(Integer phaseTaskId) throws Exception {
		TaskTemplate projTaskTemplate= projTaskTemplateDao.selectTemplateTaskById(phaseTaskId);
		return projTaskTemplate;
	}


	@Override
	public int updatePhaseTaskInform(TaskTemplate projTaskTemplate) {
		return projTaskTemplateDao.updateTemplateTaskById(projTaskTemplate);
	}


	@Transactional
	@Override
	public String insert(Map<String, Object> paramMap) throws Exception {
		//添加模板名称
		ProjectTemplate projTemplate = (ProjectTemplate) paramMap.get("projTemplate");
		projTemplateDao.insertTemplate(projTemplate);
		//添加任务
		String taskStr = (String) paramMap.get("taskStr");
		String numStr = (String) paramMap.get("numStr");
		String[] taskArray = taskStr.split(",");
		String[] numArray = numStr.split(",");
		TaskTemplate projTaskTemplate = new TaskTemplate();
		Integer templateId = projTemplate.getId();
		projTaskTemplate.setTemplateId(templateId);
		for (int i = 0; i < taskArray.length; i++) {
			projTaskTemplate.setTaskName(taskArray[i]);
			projTaskTemplate.setNumber(Integer.valueOf(numArray[i]));
			projTaskTemplateDao.insertTemplateTask(projTaskTemplate);
		}
		return "success";
	}

	@Override
	public Map<String, Object> selectTemplateAndPhaseTaskList(int templateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		ProjectTemplate projTemplate = projTemplateDao.selectTemplateById(templateId);
		map.put("projTemplate", projTemplate);
		List<TaskTemplate> templateTaskList = projTaskTemplateDao.selectTemplateTaskNameById(templateId);
		map.put("templateTaskList", templateTaskList);
		return map;
	}

	@Transactional
	@Override
	public void update(Map<String, Object> paramMap) throws Exception {
		ProjectTemplate projTemplate = (ProjectTemplate) paramMap.get("projTemplate");
		projTemplateDao.updateTemplateInfo(projTemplate);
		String templateId = (String) paramMap.get("templateId");
		String idStr = (String) paramMap.get("idStr");
		String taskStr = (String) paramMap.get("taskStr");
		String numStr = (String) paramMap.get("numStr");
		String[] idArray = idStr.split(",");
		String[] taskArray = taskStr.split(",");
		String[] numArray = numStr.split(",");
		List<Integer> delList = new ArrayList<Integer>();
		List<TaskTemplate> templateTaskList = projTaskTemplateDao.selectTemplateTaskNameById(Integer.valueOf(templateId));
		if(templateTaskList.size() > 0 && templateTaskList!=null){
			//删除任务
			for (int i = 0; i < templateTaskList.size(); i++) {
				if(idStr.indexOf(""+templateTaskList.get(i).getId()+"") == -1){
					delList.add(templateTaskList.get(i).getId());
				}
			}
			for (int i = 0; i < delList.size(); i++) {
				projTaskTemplateDao.deleteProjTemplateTask(delList.get(i));
			}
		}
		//添加和修改任务
		for (int i = 0; i < idArray.length; i++) {
			int id = Integer.valueOf(idArray[i]);
			if(id == 0){//添加任务
				TaskTemplate projTaskTemplate = new TaskTemplate();
				projTaskTemplate.setTaskName(taskArray[i]);
				projTaskTemplate.setNumber(Integer.valueOf(numArray[i]));
				projTaskTemplate.setTemplateId(Integer.valueOf(templateId));
				projTaskTemplateDao.insertTemplateTask(projTaskTemplate);
			}else{//修改任务
				TaskTemplate projTaskTemplate = new TaskTemplate();
				projTaskTemplate.setId(Integer.valueOf(idArray[i]));
				projTaskTemplate.setTaskName(taskArray[i]);
				projTaskTemplate.setNumber(Integer.valueOf(numArray[i]));
				projTaskTemplateDao.updateTemplateTaskById(projTaskTemplate);
			}
		}
	}


	@Transactional
	@Override
	public void delProjTemplate(int templateId) throws Exception {
		//删除模板名称
		projTemplateDao.deleteTemplateById(templateId);
		
		//删除模板下的任务 (产看该模板下是否有任务，如果没有，提示没有可删任务)
		List<TaskTemplate> templateTaskList = projTaskTemplateDao.selectTemplateTaskNameById(templateId);
		if(templateTaskList.size() > 0 && templateTaskList != null){
			List<Integer> taskList = new ArrayList<Integer>();
			for(TaskTemplate templateTask:templateTaskList){
				taskList.add(templateTask.getId());
			}
			projTaskTemplateDao.deleteTemplateTaskByids(taskList);
		}
	}


	@Override
	public Map<String, Object> drawProjProcessPicture(int templateId) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获取模板信息
		ProjectTemplate projTemlate = projTemplateDao.selectTemplateById(templateId);
		//获取任务信息
		List<TaskTemplate> templateTaskList = projTaskTemplateDao.selectTemplateTaskNameById(templateId);
		map.put("projTemlate", projTemlate);
		map.put("templateTaskList", templateTaskList);
		return map;
	}
}
