package com.qkby.event.dao;

import java.util.List;
import java.util.Map;

import com.qkby.event.entity.KbEventInfo;

public interface KbEventInfoDao {
	/**
	 * <pre>selectKbEventAll (查询知识库全部信息)
	 * Created by 家栋梁 on 2017年11月27日下午2:38:30  
	 *
	 * @param map
	 * @return</pre>
	 */
   List<Map<String,Object>> selectKbEventAll(Map<String,Object> map) throws Exception;
   /**
    * <pre>insert (处理新增知识库信息)
    * Created by 家栋梁 on 2017年11月27日下午2:41:50  
    *
    * @param kbEventInfo
    * @return</pre>
    */
   int insert(KbEventInfo kbEventInfo) throws Exception;
   /**
    * <pre>selectKbEventCondition (根据关键字搜索)
    * Created by 家栋梁 on 2017年11月28日下午4:35:02  
    *
    * @param map
    * @return</pre>
    */
   List<Map<String,Object>> selectKbEventCondition(Map<String,Object> map) throws Exception;
   /**
    * <pre>selectKbEventById (根据ID查询)
    * Created by 家栋梁 on 2017年11月28日下午6:34:55  
    *
    * @param id
    * @return</pre>
    */
   KbEventInfo selectKbEventById(int id) throws Exception;
   /**
    * <pre>selectKbEventCount (查询总条数)
    * Created by 家栋梁 on 2017年12月1日上午10:59:38  
    *
    * @param map
    * @return</pre>
    */
   int selectKbEventCount(Map<String,Object> map);
   /**
    * <pre>deleteKbEvent (删除)
    * Created by 家栋梁 on 2017年12月1日下午2:51:08  
    *
    * @param id
    * @return</pre>
    */
   int deleteKbEvent(int id);
   /**
    * <pre>updateByPrimaryKeySelective (修改)
    * Created by 家栋梁 on 2017年12月4日上午10:06:39  
    *
    * @param id
    * @return</pre>
    */
   int updateByPrimaryKeySelective(KbEventInfo kbEventInfo);
   
   int selectIdByCode(Map<String,Object> map);
}