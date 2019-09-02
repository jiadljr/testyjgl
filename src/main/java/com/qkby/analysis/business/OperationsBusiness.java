package com.qkby.analysis.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.qkby.event.entity.EventInfo;
import com.qkby.proj.entity.ProjectTask;
import com.qkby.sysmanage.entity.SysArrange;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月26日下午2:03:40 
 *     
 * @version </pre>
 */
public interface OperationsBusiness {
	/**
	 * <pre>untreated 
	 * Created by 家栋梁 on 2017年10月26日下午2:04:14  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	List<Map<String, Object>> untreated(Map<String, Object> pgMap,HttpServletRequest request) throws Exception;
	/**
	 * 查询已办事件
	 * @author 李帅
	 * @param id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	List<Map<String, Object>> selectProcessed(Map<String, Object> pgMap,HttpServletRequest request) throws Exception;
    /**
     * <pre>dealInformation 
     * Created by 家栋梁 on 2017年10月26日下午2:28:28  
     *
     * @return</pre>
     */
    List<Map<String,Object>> dealInformation(int id)throws Exception;
    /**
     * <pre>dealCount 
     * Created by 家栋梁 on 2017年10月26日下午4:04:59  
     *
     * @param id
     * @return</pre>
     */
    List<EventInfo> dealCount(int id)throws Exception;
    /**
     * <pre>findDutyArrangeByName 
     * Created by 家栋梁 on 2017年10月26日下午4:25:37  
     *
     * @param id
     * @return</pre>
     */
    List<SysArrange> findDutyArrangeByName(int id)throws Exception;
    /**
     * 查看自己的处理信息
     * @author 李帅
     * @param id
     * @return
     */
    Map<String, Object> showOneself(HttpServletRequest request)throws Exception;
}
