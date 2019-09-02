package com.qkby.sysmanage.dao;

import java.util.List;
import com.qkby.sysmanage.entity.SysFileTypeInfo;

public interface SysFileTypeInfoDao {
	//查询数量
    int countByExample(SysFileTypeInfo example)throws Exception;
 	//根据条件删除
    int deleteByExample(SysFileTypeInfo example)throws Exception;
    //根据Id删除
    int deleteByPrimaryKey(Integer id)throws Exception;
    //添加
    int insert(SysFileTypeInfo record)throws Exception;
    //根据条件查询
    List<SysFileTypeInfo> selectByExample(SysFileTypeInfo example)throws Exception;
    //根据Id查询
    SysFileTypeInfo selectByPrimaryKey(Integer id)throws Exception;
    //根据Id修改
    int updateByPrimaryKeySelective(SysFileTypeInfo record)throws Exception;
    //查找全部
    List<SysFileTypeInfo> selectAll()throws Exception;
}