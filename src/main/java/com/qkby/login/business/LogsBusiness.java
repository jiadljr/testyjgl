package com.qkby.login.business;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月14日上午11:18:49 
 *     
 * @version </pre>
 */
public interface LogsBusiness {
	/**
	 * <pre>selectByExample 
	 * Created by 家栋梁 on 2017年9月14日上午11:18:53  
	 *
	 * @param req
	 * @param res
	 * @return</pre>
	 */
	Map<String,Object> selectByExample(HttpServletRequest req,HttpServletResponse res)throws Exception;
}
