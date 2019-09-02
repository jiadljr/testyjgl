package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;
import com.qkby.sysmanage.entity.SysCopInfo;

public interface SysCopInfoDao {
	//查询全部
	List<SysCopInfo> selectCmpyAll()throws Exception;
	//查询数量
    int countByExample(Map<String,Object> map)throws Exception;
    //根据条件删除
    int deleteByExample(SysCopInfo example)throws Exception;
    //根据Id删除
    int deleteByPrimaryKey(Map<String,Object> map)throws Exception;
    //添加
    int insert(SysCopInfo record)throws Exception;
    //根据条件添加
    int insertSelective(SysCopInfo record);
    //根据条件查询
    List<SysCopInfo> selectByExample(Map<String,Object> map)throws Exception;
    //根据Id查询
    SysCopInfo selectByPrimaryKey(Integer id)throws Exception;
    //根据Id修改
    int updateByPrimaryKeySelective(SysCopInfo record)throws Exception;
}