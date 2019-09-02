package com.qkby.analysis.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qkby.analysis.entity.EventInfoDay;
import com.qkby.sysmanage.entity.SysServiceType;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午6:29:31 
 *     
 * @version </pre>
 */
public interface EventInfoDayBusiness {
	/**
	 * <pre>selectDay 
	 * Created by 家栋梁 on 2017年10月25日下午6:30:01  
	 *
	 * @return</pre>
	 */
    List<EventInfoDay> selectDay() throws Exception;
    /**
     * <pre>selectDepId (查询服务类型所有的一级ID)
     * Created by 家栋梁 on 2017年10月30日上午11:05:22  
     *
     * @return</pre>
     */
    List<SysServiceType> selectDepId(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>selectSerCounAll 
     * Created by 家栋梁 on 2017年10月30日下午3:09:55  
     *
     * @return</pre>
     */
    List<Map<String,Object>> selectSerCounAll(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectServiceTypeApplyAll 
     * Created by 家栋梁 on 2017年11月1日上午10:33:42  
     *
     * @param map
     * @return</pre>
     */
    List<Map<String,Object>> selectServiceTypeApplyAll(Map<String,Object> map)throws Exception;
}
