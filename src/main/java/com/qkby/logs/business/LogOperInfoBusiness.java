package com.qkby.logs.business;

import java.util.List;
import java.util.Map;

import com.qkby.logs.entity.LogOperInfo;

public interface LogOperInfoBusiness {
	/**
	 * <pre>insert (新增操作日志)
	 * Created by 家栋梁 on 2017年11月13日下午1:43:13  
	 *
	 * @param logOperInfo
	 * @return</pre>
	 */
	int insert(LogOperInfo logOperInfo)throws Exception;
	/**
	 * <pre>selectOperAll (查询操作日志)
	 * Created by 家栋梁 on 2017年11月14日上午11:34:04  
	 *
	 * @return</pre>
	 */
	List<Map<String,Object>> selectOperAll(Map<String,Object> map)throws Exception;
	/**
	 * <pre>selectOperCount (查询操作日志总条数)
	 * Created by 家栋梁 on 2017年12月4日下午5:22:04  
	 *
	 * @param map
	 * @return</pre>
	 */
	int selectOperCount(Map<String,Object> map)throws Exception;
}
