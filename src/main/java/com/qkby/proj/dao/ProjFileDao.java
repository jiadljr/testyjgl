package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectFile;

public interface ProjFileDao {
	/**
	 * 根据ID查询单个项目文件信息
	 */
	ProjectFile selectProjectFileInformationById(int id) throws Exception;

	/**
	 * 根据条件查询项目文件信息
	 */
	List<ProjectFile> selectProjectFileInformationByCondition(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据条件查询项目文件总数
	 * 2018年1月15日 下午6:30:07
	 * @李帅
	 * @param map
	 */
	int selectProjectFileCountByCondition(Map<String, Object> map) throws Exception;
	
	/**
	 * 添加项目文件
	 */
	int insertProjectFile(ProjectFile projFile) throws Exception;

	/**
	 * 修改项目文件
	 */
	int updateProjectFile(ProjectFile projFile) throws Exception;

	/**
	 * 删除项目文件
	 */
	int deleteProjectFile(int projFile) throws Exception;
}
