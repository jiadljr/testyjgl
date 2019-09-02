package com.qkby.event.dao;

import java.util.List;
import java.util.Map;

import com.qkby.event.entity.EventInfoDeal;

public interface EventInfoDealDao {
	/**
	 * <pre>countByExample (查询总条数)
	 * Created by 家栋梁 on 2017年10月10日下午1:35:35  
	 *
	 * @param example
	 * @return</pre>
	 */
    int countByExample(EventInfoDeal example) throws Exception;
    /**
     * <pre>deleteByPrimaryKey (删除)
     * Created by 家栋梁 on 2017年10月10日下午1:35:28  
     *
     * @param id
     * @return</pre>
     */
    int deleteByPrimaryKey(Integer id) throws Exception;
    /**
     * <pre>insert (新增)
     * Created by 家栋梁 on 2017年10月10日下午1:35:21  
     *
     * @param record
     * @return</pre>
     */
    int insert(EventInfoDeal record) throws Exception;
    /**
     * <pre>selectByExample (查询全部)
     * Created by 家栋梁 on 2017年10月10日下午1:35:13  
     *
     * @param example
     * @return</pre>
     */
    List<EventInfoDeal> selectByExample() throws Exception;
    /**
     * <pre>selectByPrimaryKey (根据ID进行查询)
     * Created by 家栋梁 on 2017年10月10日下午1:35:06  
     *
     * @param id
     * @return</pre>
     */
    EventInfoDeal selectByPrimaryKey(Integer id) throws Exception;
    /**
     * <pre>updateByPrimaryKeySelective (修改)
     * Created by 家栋梁 on 2017年10月10日下午1:34:59  
     *
     * @param record
     * @return</pre>
     */
    int updateByPrimaryKeySelective(EventInfoDeal record) throws Exception;
    /**
     * <pre>update 
     * Created by 家栋梁 on 2017年10月17日下午6:36:50  
     *
     * @param record
     * @return</pre>
     */
    int update(EventInfoDeal record) throws Exception;
    /**
     * 查询确定信息
     * @author 李帅
     * @param paramMap 
     * @param id
     * @return
     */
    List<Map<String,Object>> selectSureList(Map<String, Object> paramMap) throws Exception;

    /**
     * <pre>selectAssess 
     * Created by 家栋梁 on 2017年10月19日下午1:33:53  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectAssess(int id) throws Exception;
    List<EventInfoDeal> selectDealList(Map<String, Object> pgMap) throws Exception;
    /**
     * 一段时间内某个人处理的数量
     * @author 李帅
     * @param map
     * @return
     */
    List<Map<String, Object>> selectDealCountByDate(Map<String, Object> map) throws Exception;
    /**
     * 评价绩效
     * @author 李帅
     * @param map
     * @return
     */
    List<EventInfoDeal> selectAssisPerform(Map<String, Object> map) throws Exception;
    /**
     * 查询超时事件信息
     * @author 李帅
     * @param eventId
     * @return
     */
    List<Map<String,Object>> selectOverTimeList(int eventId) throws Exception;
    /**
     * 查询事件涉及的所有处理人
     * @author 李帅
     * @param id
     * @return
     */
    List<EventInfoDeal> selectDealUserAll(int id) throws Exception;
    /**
     * 查询当月指定处理人未处理总数
     * @author 李帅
     * @param id
     * @return
     */
    int selectDealNot(int id)throws Exception;
    /**
     * 查询当月指定处理人已处理总数
     * @author 李帅
     * @param id
     * @return
     */
    Map<String, Integer> selectDealEndOrNot(int id) throws Exception;
    /**
     * <pre>selectDealCount (查询处理数量统计)
     * Created by 家栋梁 on 2017年12月6日上午10:34:32  
     *
     * @return</pre>
     */
    List<Map<String,Object>> selectDealCount(Map<String,Object> map) throws Exception;
    /**
     * 查询单个处理信息
     * @author 李帅
     * @param dealId
     * @return
     */
    List<Map<String,Object>> selectOneself(int dealId) throws Exception;
    /**
     * 查询受理后的处理信息
     * @author 李帅
     * @param eventId
     * @return
     */
    List<Map<String,Object>> selectAcceptAfterDeal(int eventId) throws Exception;
    /**
     * 根据eventId查询处理时间
     * 2018年1月4日 下午12:04:28
     * @李帅
     * @param
     */
    List<EventInfoDeal> selectDatedealById(int id) throws Exception;
    /**
     * 查询转派原因
     * 2018年1月7日 下午3:42:36
     * @李帅
     * @param pgMap
     */
    List<EventInfoDeal> selectDealDescByRedeReinId(Map<String,Object> pgMap) throws Exception;
    /**
     * 
     * @param idEvent
     * @return
     */
    List<EventInfoDeal> selectDealDesc(int idEvent) throws Exception;
}