package com.qkby.contract.business;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.CapitalSource;

public interface CapitalSourceBusiness {
	
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
