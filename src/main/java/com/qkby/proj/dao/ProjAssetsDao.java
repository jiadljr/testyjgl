package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectAssets;

public interface ProjAssetsDao {
	/**
	 * 根据ID查询
	 * 
	 */
	ProjectAssets selectProjectAssetsInformationById(int id) throws Exception;

	/**
	 * 根据条件查询项目资产信息
	 */
	List<ProjectAssets> selectProjectAssetsInformationByConditions(Map<String, Object> map) throws Exception;

	/**
	 * 插入项目资产信息
	 */
	int insertProjectAssets(ProjectAssets projAssets) throws Exception;

	/**
	 * 修改项目资产信息
	 */
	int updateProjectAssets(ProjectAssets projAssets) throws Exception;

	/**
	 * 批量删除项目资产信息
	 */
	int delectProjectAssets(Map<String, Object> map) throws Exception;
}
