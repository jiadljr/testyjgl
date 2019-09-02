package com.qkby.event.business;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月13日下午2:52:37 
 *     
 * @version </pre>
 */
public interface EventInfoDealBusiness {
	/**
	 * <pre>eventDealAll (查询全部)
	 * Created by 家栋梁 on 2017年10月13日下午2:56:14  
	 *
	 * @return</pre>
	 */
    public Map<String,Object> selectDealNot(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>selectDealEnd (查询当前登录人处理信息)
     * Created by 家栋梁 on 2017年10月13日下午4:37:42  
     *
     * @return</pre>
     */
    public Map<String,Object> selectDealEnd(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>updateDealNot (处理接单)
     * Created by 家栋梁 on 2017年10月13日下午5:16:30  
     *
     * @return</pre>
     */
    public int updateDealNot(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>insertDeal (跳转至处理页面)
     * Created by 家栋梁 on 2017年10月16日下午5:45:47  
     *
     * @param request
     * @param response</pre>
     */
    public Map<String,Object> dealEnd(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>updateDealEnd (处理提交)
     * Created by 家栋梁 on 2017年10月16日下午6:32:50  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    public Map<String, Object> updateDealEnd(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>selectInformation 
     * Created by 家栋梁 on 2017年11月21日下午12:01:40  
     *
     * @param id
     * @return</pre>
     */
    public Map<String,Object> selectInformation(int id) throws Exception;
}
