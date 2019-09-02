package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectInformation;

public interface ProjInfoDao {
	/**
	 * 根据id查询单个项目信息
	 * 2018年4月19日 下午8:30:24
	 * @李帅
	 * @param projCode 项目编码
	 */
	ProjectInformation selectProjInfoById(String projCode) throws Exception;

	/**
	 * 根据参数查询项目个数
	 * 2018年4月19日 下午8:33:17
	 * @李帅
	 * @param paramMap 项目信息列表中参数的集合
	 */
	int selectProjCountByMap(Map<String, Object> paramMap) throws Exception;

	/**
	 * 根据条件查询项目信息
	 * 2018年4月19日 下午8:42:09
	 * @李帅
	 * @param paramMap 项目信息列表中参数的集合
	 * @return
	 * List<ProjectInformation> 项目列表中信息集合
	 */
	List<ProjectInformation> selectProjInfoByMap(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 从项目信息中筛选出监控信息数量
	 * 2018年4月10日 下午2:48:18
	 * @李帅
	 * @param paramMap 需要监控的项目数量
	 */
	int fromProjInfoSelectControlCountByMap (Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 从项目信息中筛选出监控信息
	 * 2018年4月10日 下午2:48:18
	 * @李帅
	 * @return  
	 * List<ProjectInformation> 需要监控的项目集合(列表中信息为项目信息)
	 * @param paramMap 项目列表中的参数集合
	 */
	List<ProjectInformation> fromProjInfoSelectControlInfoByMap (Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 从项目信息中筛选出监控信息（每个状态的总数）
	 * 2018年4月10日 下午3:24:42
	 * @李帅
	 * @param 项目列表中的参数集合
	 */
	Map<String, Object> fromProjInfoSelectProjControlEveryStatusCount(Map<String, Object> paramMap);
	
	/**
	 * 新建项目信息
	 * 2018年4月19日 下午8:47:04
	 * @李帅
	 * @param projInfo 添加项目的实体（具体参数在项目添加页）
	 */
	int insertProjInfo(ProjectInformation projInfo) throws Exception;

	/**
	 * 修改项目信息
	 * 2018年4月19日 下午8:47:54
	 * @李帅
	 * @param projInfo 修改项目的实体（具体参数在项目修改页）
	 */
	int updateProjInfo(ProjectInformation projInfo) throws Exception;
	
	/**
	 * 修改项目状态
	 * 2018年4月19日 下午8:48:20
	 * @李帅
	 * @param projInfo 项目实体（包含状态，和项目id等信息）
	 */
	int updateProjStatus(ProjectInformation projInfo)throws Exception;

	/**
	 * 根据项目编码删除项目
	 * 2018年4月19日 下午8:49:24
	 * @李帅
	 * @param projCode 项目编码
	 */
	int delectProjByProjCode(String projCode) throws Exception;

	/**
	 * 根据条件查询每个项目状态的数量
	 * 2018年2月28日 上午11:08:56
	 * @author 李帅
	 * @return 
	 * Map 
	 * @param paramMap 项目列表中的参数集合
	 */
	Map<String, Object> selectEveryProjStatusCount(Map<String, Object> paramMap);

	/**
	 * 修改项目的延期字段（0：为正常  1：为延期）
	 * 2018年4月19日 下午9:00:08
	 * @李帅
	 * @param projInfo 项目实体（包括延期字段，以及项目id等参数）
	 */
	int updateProjPf(ProjectInformation projInfo);
	
	/**
	 * 查询项目监控列表
	 * 2018年3月20日 下午1:42:50
	 * @author 李帅
	 * @param paramMap
	 * @return 项目监控信息
	 * List<ProjInfo>
	 */
	List<ProjectInformation> selectProjControlInfo(Map<String, Object> paramMap)throws Exception;
	
	/**
	 * 查询项目监控数量
	 * 2018年3月20日 下午1:43:20
	 * @author 李帅
	 * @param paramMap 项目监控列表中的参数集合
	 * @return
	 * int
	 */
	int selectProjControlCount(Map<String, Object> paramMap)throws Exception;

	
	/**
	 * 
	 * @Description (项目看板页面的项目查询 )
	 * @return
	 */
	List<Map<String,Object>> selectProjectBoardProjectQuery();
	/**
	 * 
	 * @Description (项目看板根据项目查询对应的任务)
	 * @param projCode
	 * @return
	 */
	List<Map<String,Object>> selectProjectBoardQueryTask(String projCode);
	/**
	 * 
	 * @Description (项目看板里程碑任务 )
	 * @param projCode
	 * @return
	 */
	List<Map<String,Object>> selectProjectBoardMilestoneTask(String projCode);
	/**
	 * 
	 * @Description (查询项目类型资金类型项目状态)
	 * @return
	 */
	List<Map<String,Object>> selectProjectTypeStatus();
	/**
	 * 根据修改项目看板为是状态（0：是   1：不是）
	 * 2018年4月23日 下午7:05:50
	 * @李帅
	 */
	int updateProjectBoardIsYes(Map<String,Object> map);
	/**
	 * 根据修改项目看板为不是状态（0：是   1：不是）
	 * 2018年4月23日 下午7:05:50
	 * @李帅
	 */
	int updateProjectBoardIsNo(Map<String,Object> map);

	List<ProjectInformation> selectAllProjectInformation(Map<String,Object> paramMap);
}
