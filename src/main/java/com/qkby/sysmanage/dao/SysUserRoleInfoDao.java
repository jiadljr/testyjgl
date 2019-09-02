package com.qkby.sysmanage.dao;

import com.qkby.sysmanage.entity.SysUserRoleInfo;
import java.util.List;
import java.util.Map;

public interface SysUserRoleInfoDao {
    /**
	 * 查询人员角色表
	 * @author 李帅
	 */
    int deleteByExample(SysUserRoleInfo example)throws Exception;
    /**
	 * 根据Id删除人员角色表
	 * @author 李帅
	 */
    int deleteByPrimaryKey(Integer id)throws Exception;
    /**
	 * 添加人员角色表
	 * @author 李帅
	 */
    int insert(SysUserRoleInfo record)throws Exception;
    /**
	 * 根据条件查询人员角色表
	 * @author 李帅
	 */
    List<SysUserRoleInfo> selectByExample(Map<String, Object> paramMap)throws Exception;
    /**
	 * 根据Id查询人员角色表
	 * @author 李帅
	 */
    SysUserRoleInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 根据Id修改人员角色表
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysUserRoleInfo record)throws Exception;
    /**
	 * 根据人员id查询人员角色表
	 * @author 李帅
	 */
    List<SysUserRoleInfo> selectRoleByUserId(Integer id)throws Exception;
    /**
	 * 人员授权
	 * @author 李帅
	 */
    void submitUserRloe(SysUserRoleInfo userRole)throws Exception;
    int deleteUserRole(Map<String,Object> map)throws Exception;
    /**
     * 根据角色id查询人员
     * @author 李帅
     * @param role_id
     * @return
     */
    SysUserRoleInfo selectUserByRoleId(int role_id)throws Exception;
}