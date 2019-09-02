package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysmanage.entity.SysUserRoleInfo;

public interface SysUserRoleInfoBusiness {
	/**
	 * 根据人员id查询角色
	 * @author 李帅
	 */
    List<SysUserRoleInfo> selectRoleByUserId(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
	 * 人员授权
	 * @author 李帅
	 */
    void submitUserRloe(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * 查询角色是否存在
     * 2018年2月26日 上午11:59:08
     * @author 李帅
     * @param paramMap
     * @return
     * List<SysUserRoleInfo>
     */
	List<SysUserRoleInfo> selectByExample(Map<String, Object> paramMap)throws Exception;
}
