package com.qkby.contract.dao;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.CapitalSource;

/**
 * 
 * @ClassName CapitalSoureDao
 * @Description TODO(资金来源Dao)
 * @author Administrator
 * @Date 2018年5月3日 上午11:46:31
 * @version 1.0.0
 */
public interface CapitalSoureDao {
	/**
	 * 
	 * @Description (查询所有资金来源信息（带条件）)
	 * @param map
	 * @return
	 */
	List<CapitalSource> selectCapitalSourceAllByCondition(Map<String,Object> map);
	/**
	 * 
	 * @Description (查询所有资金来源信息（不带条件）)
	 * @param map
	 * @return
	 */
	List<CapitalSource> selectCapitalSourceAll();
	/**
	 * 
	 * @Description (根据ID查询资金来源)
	 * @param id
	 * @return
	 */
	CapitalSource selectCapitalSourceById(String id);
	/**
	 * 
	 * @Description (新增资金来源)
	 * @param capitalSource
	 * @return
	 */
	int insertCapitalSource(CapitalSource capitalSource);
	/**
	 * 
	 * @Description (修改资金来源)
	 * @param capitalSource
	 * @return
	 */
	int updateCapitalSource(CapitalSource capitalSource);
	/**
	 * 
	 * @Description (删除资金来源)
	 * @param id
	 * @return
	 */
	int deleteCapitalSource(String id);

}
