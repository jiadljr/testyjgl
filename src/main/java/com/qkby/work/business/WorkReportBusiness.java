package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import com.qkby.work.entity.WorkReportInfo;

public interface WorkReportBusiness {
	//查询工作汇报
	public List<WorkReportInfo> selectWorkReport(Map<String,Object> map);
	//查询工作汇报总条数
	public int selectWorkReportCount(Map<String,Object> map);
	//新增
	public int addWorkReport(WorkReportInfo workReport);
	//根据ID进行查询
	public WorkReportInfo selectWorkReportById(Integer id);
	//工作汇报完成逻辑删除
	public void updateWorkReportDs(Map<String,Object> map);
	//草稿物理删除
	public void deleteWorkReport(Integer id);
	//修改
	public int updateWorkReport(WorkReportInfo workReport);
	//查询推送
	public List<Map<String,Object>> selectPlanReport(Map<String,Object> map);
	//查询推送总条数
	public int selectPlanReportCount(Map<String,Object> map);
	//查询推送信息
	public List<Map<String,Object>> selectReportPushMessage(Integer userId);
}
