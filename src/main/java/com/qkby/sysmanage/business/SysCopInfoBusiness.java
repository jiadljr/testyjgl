package com.qkby.sysmanage.business;


import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.qkby.sysmanage.entity.SysCopInfo;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月14日下午5:16:52 
 *     
 * @version </pre>
 */
public interface SysCopInfoBusiness {
	/**
	 * <pre>selectCmpyAll (查询全部)
	 * Created by 家栋梁 on 2017年9月14日下午5:16:57  
	 *
	 * @return</pre>
	 */
   List<SysCopInfo> selectCmpyAll()throws Exception;
	/**
	 * <pre>deleteByPrimaryKey (根据ID进行删除)
	 * Created by 家栋梁 on 2017年9月15日上午11:06:09  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 */
    int deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>selectByExample (根据条件进行查询)
     * Created by 家栋梁 on 2017年9月15日下午2:47:28  
     *
     * @param map
     * @return</pre>
     */
    Map<String,Object> selectByExample(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>insertCmpy (添加)
     * Created by 家栋梁 on 2017年9月17日下午2:26:24  
     *
     * @param request
     * @param response
     * @return</pre>
     */
    int insertCmpy(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>selectByPrimaryKey (根据ID进行查询)
     * Created by 家栋梁 on 2017年9月17日下午3:25:16  
     *
     * @param id
     * @return</pre>
     */
    Map<String,Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response)throws Exception;
    /**
     * <pre>updateByPrimaryKeySelective (修改)
     * Created by 家栋梁 on 2017年9月17日下午4:46:40  
     *
     * @param request
     * @param response</pre>
     */
    int updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
