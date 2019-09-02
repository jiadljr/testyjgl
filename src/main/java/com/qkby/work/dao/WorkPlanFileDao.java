package com.qkby.work.dao;

import java.util.List;
import java.util.Map;

import com.qkby.work.entity.WorkPlanFileInfo;

public interface WorkPlanFileDao {
	//新增文件
	public void insertPlanFile(WorkPlanFileInfo workPlanFile);
	//根据ID查询文件信息
	public List<Map<String,Object>> selectPlanFile(Integer id);
	//删除文件
	public void deletePlanFile(Integer id);

}
