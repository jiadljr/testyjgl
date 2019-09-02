package com.qkby.sysmanage.dao;

import com.qkby.sysmanage.entity.SysUserPostInfo;

import java.util.List;
import java.util.Map;

public interface SysUserPostInfoDao {
	/**
	 * 根据条件查询人员总数
	 * @author 李帅
	 */
    int countByExample(SysUserPostInfo example)throws Exception;
    /**
	 * 根据条件删除人员
	 * @author 李帅
	 */
    int deleteByExample(SysUserPostInfo example)throws Exception;
    /**
	 * 根据Id删除人员
	 * @author 李帅
	 */
    int deleteByPrimaryKey(Map<String, Object> map)throws Exception;
    /**
	 * 添加人员
	 * @author 李帅
	 */
    int insert(SysUserPostInfo record)throws Exception;
    /**
	 * 根据条件查询人员
	 * @author 李帅
	 */
    List<SysUserPostInfo> selectByExample(SysUserPostInfo example)throws Exception;
    /**
	 * 根据Id查询人员
	 * @author 李帅
	 */
    SysUserPostInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 根据Id修改人员
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysUserPostInfo record)throws Exception;
    /**
	 * 查询岗位id
	 * @author 李帅
	 */
    List<SysUserPostInfo> selectPostId()throws Exception;
    /**
	 * 根据userId查询岗位id
	 * @author 李帅
	 */
    SysUserPostInfo selectPostByUserId(Integer id)throws Exception;
    /**
     * 根据postId查询人员姓名
     * @author 李帅
     * @return
     */
    List<SysUserPostInfo> selectUserNameById(Map<String, Object> map)throws Exception;
}