package com.qkby.sysmanage.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.dao.SysUserPostInfoDao;
import com.qkby.sysmanage.entity.SysUserPostInfo;

@Service("SysUserPostInfoBusiness")
public class SysUserPostInfobusinessImpl implements SysUserPostInfoBusiness {
	@Resource
	SysUserPostInfoDao sysUserPostInfoDao;

	@Override
	public List<SysUserPostInfo> selectPostId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return sysUserPostInfoDao.selectPostId();
	}

	@Override
	public int insert(SysUserPostInfo record) throws Exception {
		return sysUserPostInfoDao.insert(record);
	}

	@Override
	public SysUserPostInfo selectPostByUserId(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String ids = request.getParameter("id");
		int id = Integer.parseInt(ids);
		SysUserPostInfo selectPostByUserId = sysUserPostInfoDao.selectPostByUserId(id);
		// if(selectPostByUserId == null){
		// throw new BusinessException();
		// }
		return selectPostByUserId;
	}

	@Override
	public List<SysUserPostInfo> selectUserNameById(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> idMap = new HashMap<String, Object>();
		String postId = request.getParameter("postId");
		int pId = Integer.valueOf(postId);
		idMap.put("postId", pId);
		List<SysUserPostInfo> userNameList = sysUserPostInfoDao.selectUserNameById(idMap);
		if (userNameList == null) {
			throw new BusinessException();
		}
		return userNameList;
	}

}
