package com.qkby.work.business;

import java.util.List;
import java.util.Map;

import com.qkby.work.entity.WorkPlanInfo;

public interface WorkPlanBusiness {
	//查询个人计划信息
	public List<Map<String,Object>> selectWorkPlanAll(Map<String,Object> map);
	//查询个人计划总条数
	public int selectWorkCount(Map<String,Object> map);
	//跳转至新增页面
	public Map<String,Object> addWorkPlan()throws Exception;
	//新增
	public int insertWorkPlan(WorkPlanInfo workPlan);
	//修改
	public void updatePlanState(Map<String,Object> map);
	//删除
	public void deleteWorkPlan(Map<String,Object> map);
	//查询部门计划信息
	public List<Map<String,Object>> sectionWorkPlanAll(Map<String,Object> map);
	//查询部门计划总条数
	public int sectionWorkCount(Map<String,Object> map);
	//修改工作计划
	public int updateWorkPlan(WorkPlanInfo workPlanInfo);
	//根据ID查询
	public WorkPlanInfo selectCheckById(Integer id);
	//
	public WorkPlanInfo selectCheckPlanById(Integer id);
	//我的查阅
	public List<Map<String,Object>> selectLookUpAll(Map<String,Object> map);
	//
	public int selectLookUpCount(Map<String,Object> map);
	//查询每天的提醒
	public List<Map<String,Object>> selectRemindDay(Integer userId);
	//查询每周提醒
	public List<Map<String,Object>> selectRemindWeek(Integer userId);
	//查询自定义提醒
	public List<Map<String,Object>> selectRemindCustom(Integer userId);
	//查询每月提醒
	public List<Map<String,Object>> selectRemindMonth(Integer userId);
	//查询推送信息
	public List<Map<String,Object>> selectPushMessage(Integer userId);
}
