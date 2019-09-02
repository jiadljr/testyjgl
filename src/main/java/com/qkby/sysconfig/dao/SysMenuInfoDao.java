package com.qkby.sysconfig.dao;

import java.util.List;
import java.util.Map;


import com.qkby.sysconfig.entity.SysMenuInfo;

public interface SysMenuInfoDao {
	/**
	 * 根据条件查询菜单总条数
	 * @author 李帅
	 * @param map
	 */
	int selectCount(Map<String, Object> map)throws Exception;
	/**
	 * 根据id删除菜单
	 * @author 李帅
	 * @param map
	 */
    int deleteByPrimaryKey(Map<String, Object> map)throws Exception;
    /**
	 * 添加菜单
	 * @author 李帅
	 */
    int insertSysMenu(SysMenuInfo record)throws Exception;
    /**
	 * 查询所有菜单
	 * @author 李帅
	 */
  	List<SysMenuInfo> selectByExampleAll()throws Exception;
  	/**
	 * 根据条件查询所有菜单
	 * @author 李帅
	 */
    List<SysMenuInfo> selectByExample(Map<String, Object> example)throws Exception;
    /**
	 * 根据id查询菜单
	 * @author 李帅
	 */
    SysMenuInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 修改菜单
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysMenuInfo record)throws Exception;
    /**
	 * 查询上级菜单
	 * @author 李帅
	 */
    List<SysMenuInfo> selectParName()throws Exception;
    
    /**
     * 查询用户对应菜单
     * */
    List<SysMenuInfo> selectByUserId(Integer userId)throws Exception;
    /**
     * 根据父id查询
     * @author 李帅
     * @param id
     * @return
     */
    List<SysMenuInfo> selectMenuByParentId(int id)throws Exception;
    /**
     * 根据等级查询菜单
     * @author 李帅
     * @param map
     * @return
     */
    List<SysMenuInfo> selectMenuByLevel(Map<String, Object> map)throws Exception;
}