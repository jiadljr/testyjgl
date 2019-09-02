package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysArrange;

public interface SysArrangeDao {
	/**
	 * 根据条件查询总数
	 * 2017年12月27日 下午9:11:52
	 * @李帅
	 * @param pgMap
	 */
    int countByExample(Map<String, Object> pgMap)throws Exception;

    int insert(SysArrange record)throws Exception;

    int insertSelective(SysArrange record)throws Exception;

    /**
     * 根据条件查询值班信息
     * 2017年12月27日 下午9:13:45
     * @李帅
     * @param pgMap
     */
    List<SysArrange> selectByExample(Map<String, Object> pgMap)throws Exception;
    /**
     * 查询所有值班日期
     * 2017年12月28日 下午5:04:24
     * @李帅
     * @param
     */
    List<SysArrange> selectArrangeAll()throws Exception;
    /**
     * 查询当前时间是否正在值班
     * 2018年1月1日 下午6:37:21
     * @李帅
     * @param pgMap
     */
    List<SysArrange> selectArrangeByDate(Map<String, Object> pgMap)throws Exception;
    
    SysArrange selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(SysArrange record)throws Exception;

    int updateByPrimaryKey(SysArrange record)throws Exception;
    
    int deleteArrange(int id)throws Exception;
    
    List<SysArrange> selectDutyArrange();
}