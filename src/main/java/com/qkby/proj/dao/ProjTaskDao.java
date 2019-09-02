package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectTask;

public interface ProjTaskDao {
	/**
	 * 根据id查询单个任务信息
	 * 2018年4月19日 下午9:48:51
	 * @李帅
	 * @param
	 * @return
	 * ProjectTask 任务实体
	 */
	ProjectTask selectProjTaskInfoById(int id) throws Exception;

	/**
	 * 根据参数查询任务信息的集合
	 * 2018年4月19日 下午9:49:45
	 * @李帅
	 * @param paramMap 任务列表中的
	 * @return
	 * List 
	 */
	List<ProjectTask> selectProjTaskInfoByMap(Map<String, Object> paramMap) throws Exception;

	/**
	 * 添加任务
	 * 2018年4月19日 下午10:01:12
	 * @李帅
	 * @param 
	 * @return
	 * int
	 */
	int insertProjTask(ProjectTask projTask) throws Exception;

	/**
	 * 修改任务
	 * 2018年4月19日 下午10:01:33
	 * @李帅
	 * @param
	 * @return
	 */
	int updateProjTask(ProjectTask projTask) throws Exception;

	/**
	 * 删除任务
	 * 2018年4月19日 下午10:01:42
	 * @李帅
	 * @param
	 * @return
	 */
	int delectProjTask(int id) throws Exception;

	/**
	 * 根据条件查询任务列表数量
	 * 2018年1月16日 上午11:05:54
	 * @李帅
	 * @param
	 */
	int selectProjTaskCountByMap(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 查询不同状态下的任务数量 
	 * 2018年2月28日 下午3:02:31
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 */
	Map<String, Object> selectTaskStatusCount(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * 修改任务超期字段的值（0：正常 1：超期）
	 * 2018年4月19日 下午10:02:50
	 * @李帅
	 * @param
	 * @return
	 */
	int updateTaskPf(ProjectTask projTask) throws Exception;
	
	/**
	 * 查询任务超时提醒集合（运维人员主页超时提醒功能）
	 * 2018年3月16日 上午11:17:48
	 * @author 李帅
	 * @param idTaskHead
	 * @return
	 * List<ProjTask>
	 */
	List<ProjectTask> selectTaskWarnList(int idTaskHead)throws Exception;
	
	/**
	 * 查询任务监控列表
	 * 2018年3月20日 下午1:42:50
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * List<ProjTask>
	 */
	List<Map<String, Object>> selectProjTaskControlList(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * 查询任务监控数量
	 * 2018年3月20日 下午1:43:20
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * int
	 */
	int countProjTaskControlList(Map<String, Object> paramMap)throws Exception;

	/**
	 * 查询任务监控导出的集合
	 * 2018年3月20日 下午1:43:20
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * int
	 */
	List<Map<String, Object>> selectTaskCotrolReportList(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * 查询父任务下的所有子任务
	 * 2018年4月19日 上午11:52:22
	 * @李帅
	 * @param
	 */
	List<String> selectChildrenTaskByParentId(Map<String, Object> paramMap);
}
