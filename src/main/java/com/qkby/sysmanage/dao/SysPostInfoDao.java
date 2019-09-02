package com.qkby.sysmanage.dao;

import java.util.List;
import com.qkby.sysmanage.entity.SysPostInfo;

public interface SysPostInfoDao {
	/**
	 * 查询所有岗位
	 * @author 李帅
	 */
	List<SysPostInfo> selectPostAll()throws Exception;
	/**
	 * 根据条件查询岗位总数
	 * @author 李帅
	 */
    int countByExample(SysPostInfo example)throws Exception;
    /**
	 * 根据条件删除岗位
	 * @author 李帅
	 */
    int deleteByExample(SysPostInfo example)throws Exception;
    /**
	 * 根据id删除岗位
	 * @author 李帅
	 */
    int deleteByPrimaryKey(Integer id)throws Exception;
    /**
	 * 添加岗位
	 * @author 李帅
	 */
    int insert(SysPostInfo record)throws Exception;
    /**
	 * 根据条件查询岗位信息
	 * @author 李帅
	 */
    List<SysPostInfo> selectByExample(SysPostInfo example)throws Exception;
    /**
	 * 根据id查询岗位信息
	 * @author 李帅
	 */
    SysPostInfo selectByPrimaryKey(Integer id)throws Exception;
    /**
	 * 修改岗位信息
	 * @author 李帅
	 */
    int updateByPrimaryKeySelective(SysPostInfo record)throws Exception;
}