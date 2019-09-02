package com.qkby.proj.business;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.TaskTemplate;
import com.qkby.proj.entity.ProjectTemplate;

public interface ProjTemplateBusiness {

	/**
	 * 2018年3月29日 下午5:05:08
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	Map<String, Object> selectProjTemplateMap() throws Exception;

	/**
	 * 2018年3月29日 下午5:44:13
	 * @李帅
	 * @param
	 */
	Map<String,Object> selectPhaseTaskNameList(int integer) throws Exception;

	/**
	 * 2018年3月29日 下午6:06:10
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	TaskTemplate selectPhaseTask(Integer phaseTaskId) throws Exception;

	/**
	 * 修改任务信息
	 * 2018年3月30日 下午1:19:44
	 * @李帅
	 * @param
	 */
	int updatePhaseTaskInform(TaskTemplate projTaskTemplate);

	/**
	 * 2018年3月30日 下午1:59:52
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String insert(Map<String, Object> paramMap) throws Exception;

	/**
	 * 2018年3月30日 下午3:24:49
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	void update(Map<String, Object> paramMap) throws Exception;

	/**
	 * 查询模板名称和任务列表
	 * 2018年3月30日 下午3:33:39
	 * @李帅
	 * @param
	 */
	Map<String, Object> selectTemplateAndPhaseTaskList(int templateId)throws Exception;

	/**
	 * 2018年4月2日 上午9:19:44
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	void delProjTemplate(int templateId) throws Exception;

	/**
	 * 绘制流程图
	 * 2018年4月2日 下午4:11:55
	 * @李帅
	 * @param
	 */
	Map<String, Object> drawProjProcessPicture(int templateId);

}
