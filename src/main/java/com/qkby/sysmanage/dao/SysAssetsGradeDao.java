package com.qkby.sysmanage.dao;

import java.util.List;

import com.qkby.sysmanage.entity.SysAssetsGrade;

public interface SysAssetsGradeDao {
	/**
	 * 查询资产等级
	 * @author 李帅
	 * @return
	 */
	List<SysAssetsGrade> selectAssetsGradeAll()throws Exception;
}