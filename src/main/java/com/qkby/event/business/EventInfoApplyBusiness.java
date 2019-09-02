package com.qkby.event.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.event.entity.EventInfo;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysUserInfo;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月10日下午2:48:57 
 *     
 * @version </pre>
 */
public interface EventInfoApplyBusiness {
	/**
	 * <pre>selectAll (查询全部)
	 * Created by 家栋梁 on 2017年10月10日下午2:51:46  
	 *
	 * @return</pre>
	 */
    public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>deleteByPrimaryKey (删除)
     * Created by 家栋梁 on 2017年10月10日下午6:25:44  
     *
     * @param id
     * @return</pre>
     */
    public int deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception ;
    /**
     * <pre>serviceAdd (跳转新增页面)
     * Created by 家栋梁 on 2017年10月11日上午10:31:17  
     *
     * @return</pre>
     */
    public Map<String,Object> serviceAdd(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>selectDeptUser (根据部门ID进行查询人员)
     * Created by 家栋梁 on 2017年10月11日上午11:32:57  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    public  Map<String,Object> selectDeptUser(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>addEventInfo (新增)
     * Created by 家栋梁 on 2017年10月11日下午3:24:40  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    public int addEventInfo(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>updateEventInfo (修改)
     * Created by 家栋梁 on 2017年10月11日下午3:25:43  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    public Map<String,Object> updateEventInfo(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>updateEvent (修改)
     * Created by 家栋梁 on 2017年10月11日下午5:13:36  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    public int updateEvent(HttpServletRequest request, HttpServletResponse response) throws Exception;
    /**
     * <pre>selectDeptAll (查询全部部门)
     * Created by 家栋梁 on 2017年10月17日上午11:24:55  
     *
     * @return</pre>
     */
    public List<SysDeptInfo> selectDeptAll()throws Exception;
    /**
     * <pre>examine (查看)
     * Created by 家栋梁 on 2017年10月17日上午11:49:34  
     *
     * @return</pre>
     */
    public Map<String,Object> examine(HttpServletRequest request, HttpServletResponse response) throws Exception ;
    /**
     * <pre>serviceAdd (跳转至重复申告页面)
     * Created by 家栋梁 on 2017年10月17日下午4:33:25  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    public Map<String,Object> serviceRepe(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>dateCreate (查询本月所有的申告的总条数)
     * Created by 家栋梁 on 2017年10月23日下午2:54:13  
     *
     * @return</pre>
     */
    public Map<String,Object> dateCreate()throws Exception;
    /**
     * <pre>userById (根据人员ID查询电话)
     * Created by 家栋梁 on 2017年10月31日下午1:48:48  
     *
     * @return</pre>
     */
    public SysUserInfo userById(int id)throws Exception;
    /**
     * 跳转申告页，判断是否为值班人
     * @return
     * @throws Exception 
     */
    public String checkDutyForApply(Integer dutyYes,Integer userId)throws Exception;
    
    EventInfo selectEventTs(HttpServletRequest request)throws Exception;
}
