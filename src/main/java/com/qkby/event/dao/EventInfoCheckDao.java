package com.qkby.event.dao;

import com.qkby.event.entity.EventInfoCheck;
import java.util.List;
import java.util.Map;

public interface EventInfoCheckDao {
	/**
	 * <pre>countByExample (查询总条数)
	 * Created by 家栋梁 on 2017年10月10日下午1:37:45  
	 *
	 * @param example
	 * @return</pre>
	 */
    int countByExample(EventInfoCheck example) throws Exception;
    /**
     * <pre>deleteByPrimaryKey (删除)
     * Created by 家栋梁 on 2017年10月10日下午1:37:51  
     *
     * @param id
     * @return</pre>
     */
    int deleteByPrimaryKey(Integer id) throws Exception;
    /**
     * <pre>insert (新增)
     * Created by 家栋梁 on 2017年10月10日下午1:37:57  
     *
     * @param record
     * @return</pre>
     */
    int insert(EventInfoCheck record) throws Exception;
    /**
     * <pre>selectByExample (查询全部)
     * Created by 家栋梁 on 2017年10月10日下午1:38:03  
     *
     * @param example
     * @return</pre>
     */
    List<EventInfoCheck> selectByExample(EventInfoCheck example) throws Exception;
    /**
     * <pre>selectByPrimaryKey (根据ID进行查询)
     * Created by 家栋梁 on 2017年10月10日下午1:38:09  
     *
     * @param id
     * @return</pre>
     */
    EventInfoCheck selectByPrimaryKey(Integer id) throws Exception;
    /**
     * <pre>updateByPrimaryKeySelective (修改)
     * Created by 家栋梁 on 2017年10月10日下午1:38:15  
     *
     * @param record
     * @return</pre>
     */
    int updateByPrimaryKeySelective(EventInfoCheck record) throws Exception;
    /**
     * 查询审核信息
     * @author 李帅
     * @return
     */
    EventInfoCheck selectCheckMessage(int id) throws Exception;
    /**
     * <pre>selectMessageCkeck (提示信息审核信息)
     * Created by 家栋梁 on 2017年11月21日下午3:35:36  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectMessageCkeck(int id) throws Exception;
    /**
     * <pre>selectMessageCkeckCount (提示信息审核信息的总条数)
     * Created by 家栋梁 on 2017年11月21日下午3:39:47  
     *
     * @param id
     * @return</pre>
     */
    int selectMessageCkeckCount(int id) throws Exception;
}