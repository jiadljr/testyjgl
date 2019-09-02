package com.qkby.event.business;

import java.util.List;
import java.util.Map;

import com.qkby.event.entity.KbEventInfo;

public interface KbEventInfoBusiness {
	/**
	 * <pre>selectKbEventAll (查询全部的知识库信息)
	 * Created by 家栋梁 on 2017年11月27日下午1:34:41  
	 *
	 * @param map
	 * @return</pre>
	 */
    List<Map<String,Object>> selectKbEventAll(Map<String,Object> map) throws Exception;
    /**
     * <pre>insert (处理新增知识库信息)
     * Created by 家栋梁 on 2017年11月27日下午2:44:41  
     *
     * @param kbEventInfo
     * @return</pre>
     */
    int insert(KbEventInfo kbEventInfo)throws Exception;
    /**
     * <pre>selectKbEventCondition (根据关键字搜索)
     * Created by 家栋梁 on 2017年11月28日下午4:37:07  
     *
     * @param map
     * @return</pre>
     */
    public List<Map<String,Object>> selectKbEventCondition(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectKbEventById (根据ID检索)
     * Created by 家栋梁 on 2017年11月28日下午6:35:43  
     *
     * @param id
     * @return</pre>
     */
    public KbEventInfo selectKbEventById(int id)throws Exception;
    /**
     * <pre>selectKbEventCount (查询总条数)
     * Created by 家栋梁 on 2017年12月1日上午11:00:19  
     *
     * @return</pre>
     */
    int selectKbEventCount(Map<String,Object> map)throws Exception;
    /**
     * <pre>insertKbEvent 
     * Created by 家栋梁 on 2017年12月1日下午12:18:18  
     *
     * @return</pre>
     */
    Map<String,Object> insertKbEvent()throws Exception;
    /**
     * <pre>deleteKbEvent (删除)
     * Created by 家栋梁 on 2017年12月1日下午2:52:17  
     *
     * @param id
     * @return</pre>
     */
    int deleteKbEvent(int id)throws Exception;
    /**
     * <pre>updateByPrimaryKeySelective (修改)
     * Created by 家栋梁 on 2017年12月4日上午10:27:02  
     *
     * @param id
     * @return</pre>
     */
    int updateByPrimaryKeySelective(KbEventInfo kbEventInfo)throws Exception;
}
