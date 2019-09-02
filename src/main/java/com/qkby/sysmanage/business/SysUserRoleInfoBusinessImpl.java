package com.qkby.sysmanage.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.dao.SysUserRoleInfoDao;
import com.qkby.sysmanage.entity.SysUserRoleInfo;

@Service("SysUserRoleInfoBusiness")
public class SysUserRoleInfoBusinessImpl implements SysUserRoleInfoBusiness{
	@Resource
	SysUserRoleInfoDao sysUserRoleInfoDao;

	@Override
	public List<SysUserRoleInfo> selectRoleByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		int id=Integer.parseInt(userId);
		List<SysUserRoleInfo> userRole = sysUserRoleInfoDao.selectRoleByUserId(id);
		if(userRole == null){
			throw new BusinessException("500","");
		}
		return userRole;
	}

	@Override
	@Transactional( value = "transactionManager_1", readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void submitUserRloe(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String roleStr = request.getParameter("roleStr");
		int uId=Integer.parseInt(userId);
		List<Integer> delList = new ArrayList<Integer>();
		List<String> addList = new ArrayList<String>();
		List<SysUserRoleInfo> userRole = sysUserRoleInfoDao.selectRoleByUserId(uId);
		if(userRole == null){
			throw new BusinessException("500","");
		}
		if (userRole != null) {
			for (int i = 0; i < userRole.size(); i++) {
				if (roleStr.indexOf(""+userRole.get(i).getIdRole()+"")==-1) {
					delList.add(userRole.get(i).getId());
				}
			}
		}
		if (!"null".equals(roleStr)) {
			String[] role = roleStr.split(",");
			for (int i = 0; i < role.length; i++) {
				Boolean flag = true;
				if (role[i] != null && !"".equals(role[i].trim())) {
					for (int j = 0; j < userRole.size(); j++) {
						if (userRole.get(j).getIdRole() == Integer.valueOf(role[i].trim())) {
							flag = false;
							break;
						}
					}
					if (flag) {
						addList.add(role[i]);
					}
				}
			}
		}
		
		//删除新增
		for (int i = 0; i < delList.size(); i++) {
			sysUserRoleInfoDao.deleteByPrimaryKey(delList.get(i));
		}
		for (int i = 0; i < addList.size(); i++) {
			String roles = addList.get(i);
			int idRole=Integer.parseInt(roles);
			SysUserRoleInfo sysUserRole=new SysUserRoleInfo();
			sysUserRole.setIdRole(idRole);
			sysUserRole.setIdUser(uId);
			sysUserRoleInfoDao.insert(sysUserRole);
		}
	}
	@Override
	public List<SysUserRoleInfo> selectByExample(Map<String, Object> paramMap) throws Exception {
		return sysUserRoleInfoDao.selectByExample(paramMap);
	}
}
