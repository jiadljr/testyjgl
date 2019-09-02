package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.qkby.work.entity.WorkReportFileInfo;

@Service
public interface WorkReportFileBusiness {
	//新增文件
	public void insertReportFile(WorkReportFileInfo workReportFile);
	//查询文件信息
	public List<Map<String,Object>> selectReportFile(Integer id);
	//删除文件
	public void deleteReportFile(Integer id);

}
