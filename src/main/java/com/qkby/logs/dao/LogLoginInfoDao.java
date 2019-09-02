package com.qkby.logs.dao;

import java.util.List;
import java.util.Map;

import com.qkby.logs.entity.LogLoginInfo;

public interface LogLoginInfoDao {
    /**
     * <pre>insert 
     * Created by 家栋梁 on 2017年11月7日下午2:00:01  
     *
     * @param record
     * @return</pre>
     */
    int insert(LogLoginInfo record)throws Exception;
    /**
     * <pre>update 
     * Created by 家栋梁 on 2017年11月7日下午2:00:10  
     *
     * @param map
     * @return</pre>
     */
    int update(LogLoginInfo record)throws Exception;
    /**
     * <pre>selectLoginAll 
     * Created by 家栋梁 on 2017年11月8日下午1:47:10  
     *
     * @return</pre>
     */
    List<Map<String,Object>> selectLoginAll(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectLogsCount (查询登陆日志总条数)
     * Created by 家栋梁 on 2017年12月4日下午4:22:12  
     *
     * @param map
     * @return</pre>
     */
    int selectLogsCount(Map<String,Object> map)throws Exception;
}