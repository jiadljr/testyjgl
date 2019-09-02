package com.qkby.analysis.business;

import java.util.List;

import com.qkby.analysis.entity.EventInfoMonth;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午2:59:22 
 *     
 * @version </pre>
 */
public interface EventInfoMonthBusiness {
	/**
	 * <pre>selectMonth 
	 * Created by 家栋梁 on 2017年10月25日下午2:59:29  
	 *
	 * @return</pre>
	 */
    List<EventInfoMonth> selectMonth() throws Exception;
}
