package com.qkby.work.dao;

import java.util.List;
import java.util.Map;

import com.qkby.work.entity.WorkReportFileInfo;

public interface WorkReportFileDao {
	//新增文件
	public void insertReportFile(WorkReportFileInfo workReportFile);
	//根据ID查询文件
	public List<Map<String,Object>> selectReportFile(Integer id);
	//删除问价
	public void deleteReportFile(Integer id);
}
