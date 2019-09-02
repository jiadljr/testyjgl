package com.qkby.proj.business;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectType;

public interface ProjTypeBusiness {

	/**
	 * 查询所有项目类型
	 * 2018年1月31日 下午3:51:43
	 * @author 李帅
	 * @return List<ProjType>
	 * @throws Exception 
	 */
	 Map<String, Object> getProjTypeList() throws Exception;

	/**
	 * 2018年3月28日 下午3:54:16
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	ProjectType getProjType(Integer typeId) throws Exception;

	/**
	 * 修改项目类型信息
	 * 2018年3月28日 下午4:34:33
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	int updateProjTypeInform(ProjectType projType) throws Exception;

	/**
	 * 新增项目类型信息
	 * 2018年3月28日 下午4:50:03
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	int insertProjTypeInform(ProjectType projType) throws Exception;

	/**
	 * 删除
	 * 2018年3月28日 下午5:14:18
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	int delProjType(Integer typeId) throws Exception;

	/**
	 * 查询项目类型
	 * 2018年4月8日 上午9:28:47
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	List<ProjectType> projTypeList() throws Exception;
    /**
     * 
     * @Description (查询所有项目类型的名称)
     * @return
     */
	List<Map<String,Object>> selectProjTypeName();
}
