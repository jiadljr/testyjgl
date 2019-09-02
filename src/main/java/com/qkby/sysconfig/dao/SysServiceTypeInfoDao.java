package com.qkby.sysconfig.dao;

import java.util.List;

import com.qkby.sysconfig.entity.SysServiceTypeInfo;

public interface SysServiceTypeInfoDao {
    //删除
    int deleteByPrimaryKey(Integer id);
    //新增
    int insert(SysServiceTypeInfo record);
    //查询
    List<SysServiceTypeInfo> selectByExample(SysServiceTypeInfo example);
    //根据ID进行查询
    SysServiceTypeInfo selectByPrimaryKey(Integer id);
    //修改
    int updateByPrimaryKeySelective(SysServiceTypeInfo record);
}