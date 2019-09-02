package com.qkby.proj.business;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectFile;

public interface ProjFileBusiness {
	/**
	 * 查询文件集合
	 * 2018年1月15日 上午11:40:47
	 * @李帅
	 * @param paramMap
	 * @throws Exception 
	 */
	List<ProjectFile> projFileList(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 查询文件总数
	 * 2018年1月15日 下午6:26:11
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	int countProjInfoFile(Map<String,Object> paramMap) throws Exception;
	
	/**
	 * 添加项目文件
	 * 2018年1月15日 下午6:36:31
	 * @李帅
	 * @param projFile
	 * @throws Exception 
	 */
	String addProjFile(ProjectFile projFile) throws Exception;

	/**
	 * 删除项目信息
	 * 2018年1月15日 下午6:49:18
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String deleteProjFile(int projFile) throws Exception;

	/**
	 * 2018年1月15日 下午6:53:44
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String updateProjFile(ProjectFile projFile) throws Exception;
	
}
