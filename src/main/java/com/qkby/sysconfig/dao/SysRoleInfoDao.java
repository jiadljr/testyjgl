package com.qkby.sysconfig.dao;

import java.util.List;
import java.util.Map;
import com.qkby.sysconfig.entity.SysRoleInfo;

public interface SysRoleInfoDao {
	/**
	 * 根据条件查询角色总数
	 * @author 李帅
	 */
	int selectCount(Map<String,Object> map)throws Exception;
	/**
	 * 查询所有角色
	 * @author 李帅
	 */
	List<SysRoleInfo> selectRoleAll()throws Exception;
	/**
	 * 删除角色
	 * @author 李帅
	 */
    int deleteByPrimaryKey(Map<String,Object> map)throws Exception;
    /**
	 * 添加角色
	 * @author 李帅
	 */
    int insert(SysRoleInfo record)throws Exception;
    /**
	 * 根据条件查询角色
	 * @author 李帅
	 */
    List<SysRoleInfo> selectByExample(Map<String,Object> map)throws Exception;
    /**
	 * 根据id查询角色
	 * @author 李帅
	 */
    SysRoleInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 修改角色
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysRoleInfo record)throws Exception;
    List<SysRoleInfo> selectRo(int id)throws Exception;
}