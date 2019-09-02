package com.qkby.sysconfig.dao;

import java.util.List;

import com.qkby.sysconfig.entity.SysOperTypeInfo;

public interface SysOperTypeInfoDao {
    //删除
    int deleteByPrimaryKey(Integer id)throws Exception;
    //新增
    int insert(SysOperTypeInfo record);
    //查询
    List<SysOperTypeInfo> selectByExample(SysOperTypeInfo example)throws Exception;
    //根据ID进行查询
    SysOperTypeInfo selectByPrimaryKey(Integer id)throws Exception;
    //修改
    int updateByPrimaryKeySelective(SysOperTypeInfo record)throws Exception;
}