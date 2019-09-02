package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectMembers;

public interface ProjMemberDao {
	/**
	 * 根据参数查询每个项目中的成员
	 * 2018年4月19日 下午9:40:05
	 * @李帅
	 * @param paramMap 
	 * @return
	 * List<ProjectMembers> 成员集合
	 */
	List<ProjectMembers> selectProjMembersByMap(Map<String, Object> paramMap) throws Exception;

	/**
	 * 添加项目成员
	 * 2018年4月19日 下午9:46:15
	 * @李帅
	 * @param
	 * @return
	 * int
	 */
	int insertProjMember(ProjectMembers projMember) throws Exception;

	/**
	 * 修改项目成员
	 * 2018年4月19日 下午9:46:15
	 * @李帅
	 * @param
	 * @return
	 * int
	 */
	int updateProjMember(ProjectMembers projMember) throws Exception;

	/**
	 * 删除项目成员
	 * 2018年4月19日 下午9:46:15
	 * @李帅
	 * @param
	 * @return
	 * int
	 */
	int delectProjMember(int idMember) throws Exception;
	
}
