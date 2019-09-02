package com.qkby.sysconfig.business;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysconfig.entity.SysRoleInfo;
import com.qkby.sysconfig.entity.SysRoleMenuInfo;

public interface SysRoleMenuInfoBusiness {
	/**
	 * 根据菜单ID进行查询菜单角色
	 * @author 李帅
	 */
	List<SysRoleMenuInfo> selectByMenuKey(HttpServletRequest request, HttpServletResponse response) throws Exception;
	/**
	 * 添加授权
	 * @author 李帅
	 */
	void submitMenu(HttpServletRequest request, HttpServletResponse response) throws Exception;
	/**
	 * 根据id查询角色
	 * @author 李帅
	 */
    SysRoleInfo selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
