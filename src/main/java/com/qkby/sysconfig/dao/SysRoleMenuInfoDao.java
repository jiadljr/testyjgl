package com.qkby.sysconfig.dao;

import java.util.List;
import java.util.Map;
import com.qkby.sysconfig.entity.SysRoleMenuInfo;

public interface SysRoleMenuInfoDao {
	/**
	 * 根据id删除角色菜单表的数据
	 * @author 李帅
	 */
    int deleteByPrimaryKey(int id)throws Exception;
    
    int deleteByMap(Map<String,Object> map)throws Exception;
    /**
	 * 新增角色菜单表的数据
	 * @author 李帅
	 */
    int insert(SysRoleMenuInfo record)throws Exception;
    /**
	 * 根据条件查询角色菜单表的数据
	 * @author 李帅
	 */
    List<SysRoleMenuInfo> selectByExample(SysRoleMenuInfo example)throws Exception;
    /**
	 * 根据id查询角色菜单表的数据
	 * @author 李帅
	 */
    SysRoleMenuInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 修改角色菜单表的数据
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysRoleMenuInfo record)throws Exception;
    /**
	 * 根据菜单id进行查询菜单角色
	 * @author 李帅
	 */
    List<SysRoleMenuInfo> selectByMenuKey(Integer id)throws Exception;
    /**
	 * 根据菜单ID进行查询id_role
	 * @author 李帅
	 */
    List<SysRoleMenuInfo> selectByMenuIdKey(Integer id)throws Exception;
    /**
   	 * 根据id_role删除中间表数据
   	 * @author 李帅
   	 */
    int deleteMenuRole(Map<String,Object> map)throws Exception;
}