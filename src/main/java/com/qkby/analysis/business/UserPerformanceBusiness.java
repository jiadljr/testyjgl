package com.qkby.analysis.business;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysServiceType;

public interface UserPerformanceBusiness {
	/**
	 * <pre>selectDealCount (查询人员处理数量统计)
	 * Created by 家栋梁 on 2017年12月6日上午10:36:12  
	 *
	 * @return</pre>
	 */
    List<Map<String,Object>> selectDealCount(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectAcceptCount (查询人员受理数量)
     * Created by 家栋梁 on 2017年12月6日上午11:14:39  
     *
     * @param map
     * @return</pre>
     */
    List<Map<String,Object>> selectAcceptCount(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectDealCountBySer 
     * Created by 家栋梁 on 2017年12月6日上午11:40:36  
     *
     * @param map
     * @return</pre>
     */
    List<SysServiceType> selectDealCountBySer(Map<String, Object> map)throws Exception;
    /**
     * <pre>selectSerCounAll 
     * Created by 家栋梁 on 2017年12月6日上午11:45:48  
     *
     * @param map
     * @return</pre>
     */
    List<SysServiceType> selectSerCounAll(Map<String, Object> map)throws Exception;
    /**
     * <pre>selectDealCountByDate 
     * Created by 家栋梁 on 2017年12月6日上午11:49:13  
     *
     * @param map
     * @return</pre>
     */
    List<Map<String, Object>> selectDealCountByDate(Map<String, Object> map)throws Exception;
    /**
     * <pre>dealUser 
     * Created by 家栋梁 on 2017年12月6日下午12:30:49  
     *
     * @param id
     * @return</pre>
     */
    Map<String,Object> dealUser(Map<String, Object> pgMap)throws Exception;
}
