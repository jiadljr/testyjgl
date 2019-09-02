package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectType;

public interface ProjTypeDao {
	/**
	 * 查询所有项目类型
	 * 2018年1月31日 下午3:51:43
	 * @author 李帅
	 * @return List<ProjType>
	 */
	 List<ProjectType> selectProjTypeList()throws Exception;
	 
	 /**
	  * 根据id查询项目类型
	  * 2018年3月28日 下午3:34:02
	  * @李帅
	  * @param
	  */
	 ProjectType selectProjTypeByKey(int typeId)throws Exception;
	 
	 /**
	  * 添加项目类型
	  * 2018年3月28日 下午3:31:53
	  * @李帅
	  * @param
	  */
	 int insertProjType(ProjectType projType)throws Exception;
	 
	 /**
	  * 修改项目类型
	  * 2018年3月28日 下午3:32:16
	  * @李帅
	  * @param
	  */
	 int updateProjType(ProjectType projType)throws Exception;
	 
	 /**
	  * 删除项目类型
	  * 2018年3月28日 下午3:32:53
	  * @李帅
	  * @param
	  */
	 int deleteProjType(int typeId)throws Exception;
	 /**
	  * 
	  * @Description (查询左右项目类型的名字)
	  * @return
	  */
	 List<Map<String,Object>> selectProjTypeName();
}