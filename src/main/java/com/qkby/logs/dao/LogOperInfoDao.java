package com.qkby.logs.dao;

import com.qkby.logs.entity.LogOperInfo;
import java.util.List;
import java.util.Map;

public interface LogOperInfoDao{
	/**
	 * <pre>insert (新增操作日志)
	 * Created by 家栋梁 on 2017年11月13日下午1:40:14  
	 *
	 * @return</pre>
	 */
     int insert(LogOperInfo logOperInfo)throws Exception;
     /**
      * <pre>selectOperAll (查询操作日志)
      * Created by 家栋梁 on 2017年11月14日上午11:33:02  
      *
      * @return</pre>
      */
     List<Map<String,Object>> selectOperAll(Map<String,Object> map)throws Exception;
     /**
      * <pre>selectOperCount (查询操作日志的总条数)
      * Created by 家栋梁 on 2017年12月4日下午5:21:07  
      *
      * @param map
      * @return</pre>
      */
     int selectOperCount(Map<String,Object> map)throws Exception;
}