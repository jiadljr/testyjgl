package com.qkby.sysconfig.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysconfig.entity.SysRoleInfo;
/**
 * 
 * @author L.S
 */
public interface SysRoleInfoBusiness {
	/**
	 * 根据条件查询所有角色
	 * @author 李帅
	 */
	Map<String,Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
	 * 添加修改参数
	 * @author 李帅
	 */
    SysRoleInfo setRole(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 添加角色
	 * @author 李帅
	 */
    String insert(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 根据id查询单个角色
	 * @author 李帅
	 */
    SysRoleInfo selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
	 * 修改角色
	 * @author 李帅
	 */
    String updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 删除角色
	 * @author 李帅
	 */
    Map<String,Object> deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 查询所有
	 * @author 李帅
	 */
  	List<SysRoleInfo> selectRoleAll()throws Exception;
  	
	public List<SysRoleInfo> selectRo(int id) throws Exception;
}
