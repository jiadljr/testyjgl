package com.qkby.proj.business;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectMembers;

public interface ProjMemberBusiness {


	/**
	 * 查询成员列表
	 * 2018年1月15日 下午6:47:26
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	List<ProjectMembers> projMemberList(Map<String, Object> paramMap) throws Exception;

	/**
	 * 添加项目成员
	 * 2018年1月16日 上午9:51:25
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String addProjMember(ProjectMembers projMember) throws Exception;

	/**
	 * 修改项目成员
	 * 2018年1月16日 上午10:58:12
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String updateProjMember(ProjectMembers projMember) throws Exception;

	/**
	 * 删除项目成员
	 * 2018年1月16日 上午11:40:02
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	String deleteProjMember(int idMember) throws Exception;

	/**
	 * 修改项目成员
	 * 2018年3月22日 上午11:29:22
	 * @author 李帅
	 * @param paramMap
	 * void
	 * @throws Exception 
	 */
	void updateProjMember(Map<String, Object> paramMap) throws Exception;

}
