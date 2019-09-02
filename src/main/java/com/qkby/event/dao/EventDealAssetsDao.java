package com.qkby.event.dao;

import java.util.List;

import com.qkby.event.entity.EventDealAssets;

public interface EventDealAssetsDao {
    int countByExample(EventDealAssets example) throws Exception;

    int deleteByExample(EventDealAssets example) throws Exception;

    int deleteByPrimaryKey(Integer id) throws Exception;

    /**
     * 添加处理表对应的资产
     * @author 李帅
     * @param record
     * @return
     */
    int insert(EventDealAssets record) throws Exception;

    int insertSelective(EventDealAssets record) throws Exception;

    List<EventDealAssets> selectByExample(EventDealAssets example) throws Exception;
    
    EventDealAssets selectByPrimaryKey(Integer id) throws Exception;

    int updateByPrimaryKeySelective(EventDealAssets record) throws Exception;

    int updateByPrimaryKey(EventDealAssets record) throws Exception;
}