package com.qkby.sysconfig.business;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.sysconfig.dao.SysRoleInfoDao;
import com.qkby.sysconfig.dao.SysRoleMenuInfoDao;
import com.qkby.sysconfig.entity.SysRoleInfo;
import com.qkby.sysconfig.entity.SysRoleMenuInfo;

@Service("SysRoleMenuInfoBusiness")
public class SysRoleMenuInfoBusinessImpl implements SysRoleMenuInfoBusiness{
	@Resource
	SysRoleMenuInfoDao sysRoleMenuInfoDao;
	@Resource
	SysRoleInfoDao sysRoleInfoDao;
	/**
	 * 根据菜单ID进行查询菜单角色
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	public List<SysRoleMenuInfo> selectByMenuKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String menuId = request.getParameter("menuId");
		int mId=Integer.parseInt(menuId);
		List<SysRoleMenuInfo> roleMenu = sysRoleMenuInfoDao.selectByMenuKey(mId);
		if(roleMenu == null){
			throw new BusinessException("500","");
		}
		return roleMenu;
	}
	
	/**
	 * 添加授权
	 * @author 李帅
	 * @throws Exception 
	 */
	@Override
	@Transactional( value = "transactionManager_1", readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void submitMenu(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String menuId = request.getParameter("menuId");
		String roleStr = request.getParameter("roleStr");
		int menu_id=Integer.parseInt(menuId);
		List<Integer> delList = new ArrayList<Integer>();
		List<String> addList = new ArrayList<String>();
		List<SysRoleMenuInfo> roleList = sysRoleMenuInfoDao.selectByMenuIdKey(menu_id);
		if(roleList == null){
			throw new BusinessException("500","");
		}
		for (int i = 0; i < roleList.size(); i++) {
			if (roleList.get(i).getIdRole() != ConstantMenu.OPS_SUP) {
				if (roleStr.indexOf(""+roleList.get(i).getIdRole()+"")==-1) {
					delList.add(roleList.get(i).getId());
				}
			}
		}
		String[] role = roleStr.split(",");
		for (int i = 0; i < role.length; i++) {
			Boolean flag = true;
			if (role[i] != null && !"".equals(role[i].trim())) {
				for (int j = 0; j < roleList.size(); j++) {
					if (roleList.get(j).getIdRole() == Integer.valueOf(role[i].trim())) {
						flag = false;
						break;
					}
				}
				if (flag) {
					addList.add(role[i]);
				}
			}
		}
		
		//进行删除
		for (int i = 0; i < delList.size(); i++) {
			sysRoleMenuInfoDao.deleteByPrimaryKey(delList.get(i));
		}
		
		//进行修改
		for (int i = 0; i < addList.size(); i++) {
			String roles=addList.get(i);
			int roleId=Integer.parseInt(roles);
			SysRoleMenuInfo roleMenu=new SysRoleMenuInfo();
			roleMenu.setIdRole(roleId);
			roleMenu.setIdMenu(menu_id);
			sysRoleMenuInfoDao.insert(roleMenu);
		}
	}

	@Override
	public SysRoleInfo selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idStr=request.getParameter("id");
		int id=Integer.valueOf(idStr);
		SysRoleInfo roleOne = sysRoleInfoDao.selectByPrimaryKey(id);
		if(roleOne == null){
			throw new BusinessException("500","");
		}
		return roleOne;
	}

}
