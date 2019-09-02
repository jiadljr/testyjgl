package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysAssetsInfo;

public interface SysAssetsInfoDao {
	  //根据ID删除
      int deleteByPrimaryKey(Map<String,Object> map)throws Exception;
	  //新增
      int insert(SysAssetsInfo record)throws Exception;
      //
      List<SysAssetsInfo> selectByExample(Map<String,Object> map)throws Exception;
      //根据ID进行查询
      SysAssetsInfo selectByPrimaryKey(Integer id)throws Exception;
      //修改
      int updateByPrimaryKeySelective(SysAssetsInfo record)throws Exception;
      //
      int updateByPrimaryKey(SysAssetsInfo record)throws Exception;
      //
      List<SysAssetsInfo> seleAssById(int id)throws Exception;
      //查询总条数
      int selectCount(Map<String,Object> map)throws Exception;
      //逻辑删除修改ds字段
      int updateDs(Map<String,Object> map)throws Exception;
      /**
       * 根据处理表id查询资产
       * @author 李帅
       * @param id
       * @return
       */
      List<SysAssetsInfo> selectAssetsByDealId(int id)throws Exception;
}