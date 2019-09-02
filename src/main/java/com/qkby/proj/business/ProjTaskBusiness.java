package com.qkby.proj.business;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectTask;

public interface ProjTaskBusiness {

	/**
	 * 查询任务的总条数
	 * 2018年1月16日 上午11:03:35
	 * @李帅
	 * @param 
	 * @throws Exception 
	 */
	int countProjTask(Map<String, Object> paramMap) throws Exception;

	/**
	 * 查询任务集合
	 * 2018年1月16日 上午11:04:13
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	List<ProjectTask> findProjTaskByUser(Map<String, Object> paramMap) throws Exception;

	/**
	 * 添加任务
	 * 2018年1月16日 上午11:13:47
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String addProjTask(ProjectTask projTask) throws Exception;

	/** 
	 * 修改任务
	 * 2018年1月16日 上午11:15:55
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String updateProjTask(ProjectTask projTask) throws Exception;

	/**
	 * 删除任务
	 * 2018年1月16日 上午11:45:09
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String deleteProjTask(String[] idArray) throws Exception;
	
	/**
	 * 根据id查询任务
	 * 2018年1月31日 下午2:14:07
	 * @author 李帅
	 * @param id
	 * @return
	 * ProjTask
	 * @throws Exception 
	 */
	ProjectTask selectTaskByKey(int id) throws Exception;

	/**
	 * 查询不同状态下的任务数量 
	 * 2018年2月28日 下午3:03:31
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 */
	Map<String, Object> selectTaskStatusCount(Map<String, Object> paramMap)throws Exception;

	/**
	 * 获取任务集合
	 * 2018年3月8日 下午4:02:15
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * Map<String,Object>
	 * @throws Exception 
	 */
	Map<String, Object> getProjTaskList(Map<String, Object> paramMap) throws Exception;

	/**
	 * 获取跳转添加任务所需要的参数
	 * 2018年3月8日 下午4:19:04
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * Map<String,Object>
	 * @throws Exception 
	 */
	Map<String, Object> getToAddProjTaskList(Map<String, Object> paramMap) throws Exception;

	/**
	 * 修改任务进度
	 * 2018年3月9日 下午1:09:27
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * String
	 * @throws Exception 
	 */
	String updateProjSpeed(Map<String,Object> paramMap) throws Exception;

	Map<String,Object> getPopMessage(String projCode) throws Exception;

	/**
	 * 导出任务记录
	 * 2018年3月13日 下午4:40:56
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 */
	Map<String, Object> downLoadTaskRecordExport(Map<String, Object> paramMap) throws Exception;

	/**
	 * 查询项目监控
	 * 2018年3月20日 上午11:58:37
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> findProjCotrolList(Map<String, Object> paramMap)throws Exception;

	/**
	 * 导出项目监控集合
	 * 2018年3月21日 下午2:13:16
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> exportProjTaskControl(Map<String, Object> paramMap)throws Exception;

	/**
	 * 查询运维人员任务提醒集合
	 * 2018年3月21日 下午3:09:34
	 * @author 李帅
	 * @param user_id
	 * @return
	 * List<ProjTask>
	 * @throws Exception 
	 */
	List<ProjectTask> getTaskWarnList(int user_id) throws Exception;

	/**
	 * 查询所有子节点
	 * 2018年4月19日 下午12:01:56
	 * @李帅
	 * @param
	 */
	List<String> selectChildrenTask(Map<String, Object> paramMap);
	void importTask(Map<String,Object> map) throws Exception;
	/**
	 * 
	 * @Description (根据条件查询任务)
	 * @param map
	 * @return
	 */
	List<ProjectTask> selectProjTaskInfoByMap(Map<String, Object> map)throws Exception;
}
