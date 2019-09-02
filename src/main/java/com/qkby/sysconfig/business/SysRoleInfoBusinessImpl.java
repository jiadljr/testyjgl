package com.qkby.sysconfig.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.sysconfig.dao.SysRoleInfoDao;
import com.qkby.sysconfig.dao.SysRoleMenuInfoDao;
import com.qkby.sysconfig.entity.SysRoleInfo;
import com.qkby.sysmanage.dao.SysUserRoleInfoDao;
import com.qkby.utils.Utils;
/**
 * 
 * @author L.S
 */
@Service("SysRoleInfoBusiness")
public class SysRoleInfoBusinessImpl implements SysRoleInfoBusiness{
	@Resource
	SysRoleInfoDao sysRoleInfoDao;
	
	@Resource
	SysUserRoleInfoDao sysUserRoleInfoDao;
	
	@Resource
	SysRoleMenuInfoDao SysRoleMenuInfoDao;
	@Resource
	LogOperInfoDao logOperInfoDao;
	
	/**
	 * 根据条件查询所有角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public Map<String,Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> listMap=new HashMap<String,Object>();
		String roleCode = request.getParameter("roleCode");
		if ("".equals(roleCode)) {
			roleCode=null;
		}
		listMap.put("code",roleCode);
		String roleName = request.getParameter("roleName");
		if ("".equals(roleName)) {
			roleName=null;
		}
		listMap.put("name",roleName);
		String pages = request.getParameter("pages");
		if(!"".equals(pages)){
		 Utils.pages(request, listMap, map);
		}else{
			int totalRow = sysRoleInfoDao.selectCount(listMap);
			Utils.paging(request, totalRow, listMap, map);
		}
		List<SysRoleInfo> roleAll = sysRoleInfoDao.selectByExample(listMap);
		if(roleAll == null){
			throw new BusinessException("500","");
		}
		map.put("roleAll", roleAll);
		return map;
	}
	/**
	 * 增加删除所需参数
	 * @author 李帅
	 */
	@Override
	public SysRoleInfo setRole(HttpServletRequest request, HttpServletResponse response) {
		SysRoleInfo role=new SysRoleInfo();
		String roleCode = request.getParameter("roleCode");
		if ("".equals(roleCode)) {
			roleCode=null;
		}
		role.setCode(roleCode);
		String roleName = request.getParameter("roleName");
		if ("".equals(roleName)) {
			roleName=null;
		}
		role.setName(roleName);
		String roleRemark = request.getParameter("roleRemark");
		if ("".equals(roleRemark)) {
			roleRemark=null;
		}
		role.setRemark(roleRemark);
		return role;
	}

	/**
	 * 添加角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public String insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysRoleInfo role = setRole(request, response);
		int in = sysRoleInfoDao.insert(role);
		String mass="添加成功";
		if (in==0) {
			mass="添加失败";
		}
		Integer pkId = role.getId();
		LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_role", 1);
		logOperInfoDao.insert(logOperInfo);
		return mass;
	}
	/**
	 * 查询单个角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public SysRoleInfo selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		int roleId=Integer.parseInt(id);
		SysRoleInfo roleOne = sysRoleInfoDao.selectByPrimaryKey(roleId);
		if(roleOne == null){
			throw new BusinessException("500","");
		}
		return roleOne;
	}
	/**
	 * 角色修改
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public String updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysRoleInfo role = setRole(request, response);
		String roleId = request.getParameter("roleId");
		int id= 0;
		if ("".equals(roleId) || roleId==null) {
			roleId=null;
		}else{
			id=Integer.parseInt(roleId);
			role.setId(id);
		}
		int in = sysRoleInfoDao.updateByPrimaryKeySelective(role);
		String mass="修改成功";
		if (in==0) {
			mass="修改失败";
		}
		LogOperInfo logOperInfo = Utils.insertLogOper(request, id, "sys_role", 2);
		logOperInfoDao.insert(logOperInfo);
		return mass;
	}
	/**
	 * 角色删除
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public Map<String,Object> deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		String[] ids = request.getParameterValues("ids");
		map.put("ids", ids);
		int in = sysRoleInfoDao.deleteByPrimaryKey(map);
		sysUserRoleInfoDao.deleteUserRole(map);
		SysRoleMenuInfoDao.deleteMenuRole(map);
		String mass="success";
		if (in==0) {
			mass="error";
		}
		map.put("mass", mass);
		for(int i = 0;i<ids.length;i++){
            int pkId = Integer.valueOf(ids[i]);
            LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_role", 3);
    		logOperInfoDao.insert(logOperInfo);
		}
		return map;
	}
	/**
	 * 查询所有角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public List<SysRoleInfo> selectRoleAll() throws Exception {
		return sysRoleInfoDao.selectRoleAll();
	}
	@Override
	public List<SysRoleInfo> selectRo(int id) throws Exception {
		List<SysRoleInfo> selectRo = sysRoleInfoDao.selectRo(id);
		if(selectRo == null){
			throw new BusinessException("500","");
		}
		return selectRo;
	}
}
