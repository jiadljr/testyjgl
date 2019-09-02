package com.qkby.sysconfig.business;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysconfig.entity.SysMenuInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
public interface SysMenuInfoBusiness{
	/**
	 * 查询所有菜单
	 * @author 李帅
	 */
	List<SysMenuInfo> selectByExampleAll()throws Exception;
	/**
	 * 根据条件查询所有菜单
	 * @author 李帅
	 */
	Map<String, Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception;
	/**
	 * 根据id查询单个菜单
	 * @author 李帅
	 */
    Map<String, Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
	 * 根据id删除菜
	 * @author 李帅
	 */
    String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 添加菜单
	 * @author 李帅
	 */
    void insertSysMenu(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
	 * 修改菜单
	 * @author 李帅
	 */
    void updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
	 * 添加修改参数
	 * @author 李帅
	 */
    SysMenuInfo setMenu(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
	 * 查询上级菜单
	 * @author 李帅
	 */
    List<SysMenuInfo> selectParName()throws Exception;
    
    /**
     * 查询用户对应菜单
     * @throws ParseException 
     * */
    Map<String,List<SysMenuInfo>> loadMenu(SysUserInfo user,Integer dutyYes,Integer userId,String basePath,HttpServletRequest request) throws Exception;
    
    /**
     * 根据等级查询菜单
     * @author 李帅
     * @param map
     * @return
     */
    List<SysMenuInfo> selectMenuByLevel(Map<String, Object> map)throws Exception;
}
