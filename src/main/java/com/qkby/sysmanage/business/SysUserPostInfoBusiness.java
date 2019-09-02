package com.qkby.sysmanage.business;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysmanage.entity.SysUserPostInfo;

public interface SysUserPostInfoBusiness {
	/**
	 * 查询岗位id
	 * @author 李帅
	 */
    List<SysUserPostInfo> selectPostId(HttpServletRequest request, HttpServletResponse response)throws Exception;
    int insert(SysUserPostInfo record) throws Exception;
    /**
	 * 根据userId查询岗位id
	 * @author 李帅
	 */
    SysUserPostInfo selectPostByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * 根据postId查询人员姓名
     * @author 李帅
     * @return
     */
    List<SysUserPostInfo> selectUserNameById(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
