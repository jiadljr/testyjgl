package com.qkby.logs.business;

import java.util.List;
import java.util.Map;

public interface LogLoginInfoBusiness {
	/**
	 * <pre>selectLoginAll (查询所有登陆日志)
	 * Created by 家栋梁 on 2017年12月4日下午4:25:15  
	 *
	 * @param map
	 * @return</pre>
	 */
	public List<Map<String, Object>> selectLoginAll(Map<String, Object> map) throws Exception;
	
	public int selectLogsCount(Map<String,Object> map)throws Exception;
}
