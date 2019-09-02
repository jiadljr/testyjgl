package com.qkby.event.dao;

import com.qkby.event.entity.EventLogInfo;
import java.util.List;
import java.util.Map;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月10日下午1:34:46 
 *     
 * @version </pre>
 */
public interface EventLogInfoDao {
	/**
	 * <pre>countByExample (查询总条数)
	 * Created by 家栋梁 on 2017年10月10日下午1:33:29  
	 *
	 * @param example
	 * @return</pre>
	 */
    int countByExample(EventLogInfo example) throws Exception;
    /**
     * <pre>deleteByPrimaryKey (删除)
     * Created by 家栋梁 on 2017年10月10日下午1:33:45  
     *
     * @param id
     * @return</pre>
     */
    int deleteByPrimaryKey(Integer id) throws Exception;
    /**
     * <pre>insert (新增)
     * Created by 家栋梁 on 2017年10月10日下午1:34:03  
     *
     * @param record
     * @return</pre>
     */
    int insert(EventLogInfo record) throws Exception;
    /**
     * <pre>selectByExample (查询全部)
     * Created by 家栋梁 on 2017年10月10日下午1:34:14  
     *
     * @param example
     * @return</pre>
     */
    List<EventLogInfo> selectByExample(EventLogInfo example) throws Exception;
    /**
     * <pre>selectByPrimaryKey (根据ID进行查询)
     * Created by 家栋梁 on 2017年10月10日下午1:34:23  
     *
     * @param id
     * @return</pre>
     */
    EventLogInfo selectByPrimaryKey(Integer id) throws Exception;
    /**
     * <pre>updateByPrimaryKeySelective (修改)
     * Created by 家栋梁 on 2017年10月10日下午1:34:32  
     *
     * @param record
     * @return</pre>
     */
    int updateByPrimaryKeySelective(EventLogInfo record) throws Exception;
    /**
     * <pre>selectApply 
     * Created by 家栋梁 on 2017年10月17日下午2:49:52  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectApply(int id) throws Exception;
    /**
     * <pre>selectAsses 
     * Created by 家栋梁 on 2017年10月31日下午5:27:50  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectAsses(int id) throws Exception;
    /**
     * <pre>selectAccept 
     * Created by 家栋梁 on 2017年10月17日下午3:24:15  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectAccept(int id) throws Exception;
    /**
     * <pre>selectDeal 
     * Created by 家栋梁 on 2017年10月17日下午4:18:09  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectDeal(int id) throws Exception;
    /**
     * <pre>selectSure 
     * Created by 家栋梁 on 2017年10月23日上午11:13:45  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectSure(int id) throws Exception;
    /**
     * <pre>selectAnewAsses 
     * Created by 家栋梁 on 2017年11月3日上午11:35:58  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectAnewAsses(int id) throws Exception;
    /**
     * <pre>selectAnewDeal 
     * Created by 家栋梁 on 2017年11月3日上午10:57:22  
     *
     * @return</pre>
     */
    EventLogInfo selectAnewDeal(int id) throws Exception;
    /**
     * 根据event_id查询log表状态
     * @author 李帅
     * @param id
     * @return
     */
    List<EventLogInfo> selectLogStatus(int id) throws Exception;
    /**
     * 查询处理完成未评价的事件
     * 2018年1月3日 下午4:14:28
     * @李帅
     * @param
     */
    List<EventLogInfo> selectNotAssessTime() throws Exception;
}