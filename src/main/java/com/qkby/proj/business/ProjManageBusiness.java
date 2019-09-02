package com.qkby.proj.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.qkby.proj.entity.ProjectInformation;

public interface ProjManageBusiness {
	
	/**
	 * 根据登陆人查询所有项目
	 * 2018年1月16日 上午11:32:32
	 * @李帅
	 * @param
	 */
	List<ProjectInformation> findProjInfoByUser(Map<String, Object> map) throws Exception;

	/**
	 * 添加项目
	 * 2018年1月16日 上午11:33:09
	 * @李帅
	 * @param
	 */
	int insertProjInfo(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 修改项目
	 * 2018年1月16日 上午11:33:23
	 * @李帅
	 * @param
	 */
	String updateProjInfo(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 项目状态变更
	 * 2018年2月26日 下午5:23:08
	 * @author 李帅
	 * @param projInfo
	 * @return
	 * int
	 */
	String updateProjStatus(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * 查询项目总数
	 * 2018年1月16日 上午11:33:34
	 * @李帅
	 * @param
	 */
	int countProjInfo(Map<String, Object> map)throws Exception;

	/**
	 * 删除项目
	 * 2018年1月16日 上午11:30:01
	 * @李帅
	 * @param id 项目id
	 * @throws Exception 
	 */
	String deleteProjInfo(String projCode) throws Exception;
	
	/**
	 * 查询单条项目信息
	 * 2018年1月18日 下午4:40:46
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	ProjectInformation selectProjByPrimaryKey(String projCode) throws Exception;

	/**
	 * 查询每种状态数量各多少
	 * 2018年2月28日 上午11:10:16
	 * @author 李帅
	 * @return
	 * Map<String,Object>
	 */
	Map<String, Object> selectProjEveryStatusCount(Map<String, Object> paramMap);

	/**
	 * 获取跳转修改页所需要的数据集合
	 * 2018年3月8日 上午11:47:41
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * Map<String,Object>
	 * @throws Exception 
	 */
	Map<String,Object> getUpdateProjList(Map<String, Object> paramMap) throws Exception;

	
	/**
	 * 查询项目监控List
	 * 2018年3月28日 上午10:07:29
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * Map<String,Object>
	 * @throws Exception 
	 */
	Map<String, Object> findProjCotrolList(Map<String, Object> paramMap) throws Exception;

	/**
	 * 所有任务的导出
	 * 2018年3月28日 上午10:54:37
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * Map<String,Object>
	 * @throws Exception 
	 */
	Map<String, Object> exportProjControl(Map<String, Object> paramMap) throws Exception;

	/**
	 * 2018年4月8日 下午3:50:04
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	Map<String, Object> getAddInform(Map<String, Object> paramMap) throws Exception;

	/**
	 * 筛选项目监控信息
	 * 2018年4月10日 下午2:54:50
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	List<ProjectInformation> selectProjControl(Map<String, Object> paramMap) throws Exception;

	/**
	 * 2018年4月10日 下午3:08:41
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	int countProjControl(Map<String, Object> paramMap) throws Exception;

	/**
	 * 查询项目列表信息
	 * 2018年4月12日 上午9:34:03
	 * @李帅
	 * @param
	 */
	Map<String, Object> getProjListInform(Map<String, Object> paramMap) throws Exception;

	/**
	 * 修改项目路径
	 * 2018年4月17日 下午4:28:22
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	void updateProjPath(ProjectInformation projInfo) throws Exception;

	void updateProjSpeedInfo(Map<String, Object> paramMap) throws Exception;

	/**
	 * 获取项目看板中的项目集合
	 * 2018年4月23日 下午1:31:46
	 * @李帅
	 * @param paramMap
	 * @throws Exception 
	 */
	Map<String,Object> getProjBoardList(Map<String, Object> paramMap) throws Exception;

	/**
	 * 修改项目看板状态（0：不是看板内容 1： 是看板内容 ）
	 * 2018年4月23日 下午7:12:04
	 * @李帅
	 */
	String changeProjBoardStatus(String addData,String delData);
	
	/**
	 * 
	 * @Description (项目看板页面的项目查询 )
	 * @return
	 */
	List<Map<String,Object>> selectProjectBoardProjectQuery();
	/**
	 * 
	 * @Description (项目看板里程碑任务 )
	 * @param projCode
	 * @return
	 */
	List<Map<String,Object>> selectProjectBoardMilestoneTask(HttpServletRequest request);
	/**
	 * 
	 * @Description (查询项目类型资金类型项目状态)
	 * @return
	 */
	List<Map<String,Object>> selectProjectTypeStatus();

	List<ProjectInformation> getAllProjectList(Map<String, Object> paramMap);

	List<Map<String, Object>> selectProjectBoardQueryTask(HttpServletRequest request);
}
