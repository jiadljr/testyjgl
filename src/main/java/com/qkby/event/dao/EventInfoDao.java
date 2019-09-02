package com.qkby.event.dao;

import com.qkby.event.entity.EventInfo;
import java.util.List;
import java.util.Map;

public interface EventInfoDao {
	/**
	 * <pre>countByExample (查询总条数)
	 * Created by 家栋梁 on 2017年10月10日下午1:36:24  
	 *
	 * @param example
	 * @return</pre>
	 */
    int countByExample(Map<String,Object> map) throws Exception;
    /**
     * <pre>deleteByPrimaryKey (删除)
     * Created by 家栋梁 on 2017年10月10日下午1:36:31  
     *
     * @param id
     * @return</pre>
     */
    int eventRepeal(Map<String,Object> map) throws Exception;
    /**
     * <pre>insert (新增)
     * Created by 家栋梁 on 2017年10月10日下午1:36:38  
     *
     * @param record
     * @return</pre>
     */
    int insert(EventInfo record) throws Exception;
    /**
     * <pre>selectByExample (查询全部)
     * Created by 家栋梁 on 2017年10月10日下午1:36:45  
     *
     * @param example
     * @return</pre>
     */
    List<EventInfo> selectByExample(Map<String,Object> map) throws Exception;
    /**
     * <pre>selectByPrimaryKey (根据ID进行查询)
     * Created by 家栋梁 on 2017年10月10日下午1:36:50  
     *
     * @param id
     * @return</pre>
     */
    EventInfo selectByPrimaryKey(Integer id) throws Exception;
    /**
     * <pre>updateByPrimaryKeySelective (修改)
     * Created by 家栋梁 on 2017年10月10日下午1:36:56  
     *
     * @param record
     * @return</pre>
     */
    int updateByPrimaryKeySelective(EventInfo record) throws Exception;
    /**
     * <pre>selectDealNot (查询未接单的处理信息)
     * Created by 家栋梁 on 2017年10月13日下午4:09:27  
     *
     * @return</pre>
     */
    List<EventInfo> selectDealNot(Map<String,Object> map) throws Exception;
    /**
     * <pre>selectDealEnd (查询已接单的处理信息)
     * Created by 家栋梁 on 2017年10月13日下午4:37:18  
     *
     * @return</pre>
     */
    List<Map<String,Object>> selectDealEnd(Map<String,Object> map) throws Exception;
    /**
     * <pre>countDealNot (查询未接单的处理信息总条数)
     * Created by 家栋梁 on 2017年10月13日下午5:55:49  
     *
     * @return</pre>
     */
    int countDealNot(Map<String,Object> map) throws Exception;
    /**
     * <pre>countDealEnd (查询已接单的处理信息总条数)
     * Created by 家栋梁 on 2017年10月13日下午5:57:20  
     *
     * @return</pre>
     */
    int countDealEnd(Map<String,Object> map) throws Exception;
    /**
     * 三级以上事件未审核列表查询
     * @author 李帅
     * @param map
     * @return
     */
    List<EventInfo> selectCheckList(Map<String,Object> map) throws Exception;
    /**
     * 三级以上事件未审核总数
     * @author 李帅
     * @param map
     * @return
     */
    int countNotCheck(Map<String,Object> map) throws Exception;
    /**
     * <pre>selectEventInfoAll (根据ID查询申告受理信息)
     * Created by 家栋梁 on 2017年10月16日下午5:44:21  
     *
     * @param id
     * @return</pre>
     */
    Map<String,Object> selectEventInfoAll(Map<String,Object> map) throws Exception;
    /**
     * <pre>countDealAll (查询当前登录人处理的总条数)
     * Created by 家栋梁 on 2017年11月17日下午1:33:16  
     *
     * @return</pre>
     */
    int countDealAll(Map<String,Object> map) throws Exception;
    /**
     * <pre>selectExamine (查看)
     * Created by 家栋梁 on 2017年10月17日下午1:19:32  
     *
     * @param id
     * @return</pre>
     */
    EventInfo selectExamine(int id) throws Exception;
	/**
	 * <pre>updateEventStatus (处理后修改事件状态)
	 * Created by 家栋梁 on 2017年10月18日上午11:05:12  
	 *
	 * @param eventInfo
	 * @return</pre>
	 */
    int updateEventStatus(EventInfo eventInfo) throws Exception;
    /**
     * <pre>selectAccept (查询受理信息)
     * Created by 家栋梁 on 2017年10月18日上午11:05:09  
     *
     * @param map
     * @return</pre>
     */
    List<EventInfo> selectAccept(Map<String,Object> map) throws Exception;
    /**
     * <pre>selectAsses ()
     * Created by 家栋梁 on 2017年10月18日上午11:05:37  
     *
     * @return</pre>
     */
    List<Map<String,Object>> selectAsses() throws Exception;
    /**
     * <pre>updateApply 
     * Created by 家栋梁 on 2017年10月18日下午2:33:35  
     *
     * @param eventInfo
     * @return</pre>
     */
    int updateApply(EventInfo eventInfo) throws Exception;
    /**
     * <pre>updateAssets 
     * Created by 家栋梁 on 2017年10月27日上午10:14:03  
     *
     * @param eventInfo
     * @return</pre>
     */
    int updateAssets(EventInfo eventInfo) throws Exception;
    /**
     * 查询待确定的信息
     * @author 李帅
     * @param map
     * @return
     */
    List<Map<String, Object>> selectNotSureList(Map<String, Object> map) throws Exception;
    /**
     * 查询待确定总条数
     * @author 李帅
     * @param map
     * @return
     */
    int countNotSure(Map<String, Object> map) throws Exception;
    /**
     * 添加受理信息
     * @author 李帅
     * @param eventInfo
     * @return
     */
    int updateAcceptByPrimaryKeySelective(EventInfo eventInfo) throws Exception;
    /**
     * 添加审核信息
     * @author 李帅
     * @param eventInfo
     * @return
     */
    int updateCheckByPrimaryKeySelective(EventInfo eventInfo) throws Exception;
    /**
     * 事件查询列表
     * @author 李帅
     * @param map
     * @return
     */
    List<Map<String, Object>> selectAnaListByExample(Map<String, Object> map) throws Exception;
    /**
     * 事件查询信息数量
     * @author 李帅
     * @param map
     * @return
     */
    int countAnaByExample(Map<String, Object> map) throws Exception;
    /**
     * 事件查询: 受理信息
     * @author 李帅
     * @param id
     * @return
     */
    EventInfo selectEventAccept(int id) throws Exception;
    /**
     * 事件查询: 审核信息
     * @author 李帅
     * @param id
     * @return
     */
    EventInfo selectEventCheck(int id) throws Exception;
    /**
     * 科室申告统计
     * @author 李帅
     * @return
     */
    List<EventInfo> selectDeptApply(Map<String, Object> map) throws Exception;
    /**
     * <pre>dateCreate 
     * Created by 家栋梁 on 2017年10月23日下午3:38:01  
     *
     * @param map
     * @return</pre>
     */
     int dateCreate(Map<String, Object> map) throws Exception;
     /**
      * <pre>selectLevel 
      * Created by 家栋梁 on 2017年10月23日下午3:47:06  
      *
      * @param map
      * @return</pre>
      */
     int selectLevel(Map<String,Object> map) throws Exception;
     /**
      * 查询今日新增事件
      * @author 李帅
      * @return
      */
     List<EventInfo> selectTodayEvent() throws Exception;
     /**
      * 新增事件总数
      * @author 李帅
      * @return
      */
     int countTodayEvent() throws Exception;
     /**
      * <pre>untreated 
      * Created by 家栋梁 on 2017年10月26日下午2:02:03  
      *
      * @return</pre>
      */
     List<Map<String,Object>> untreated(Map<String, Object> pgMap) throws Exception;
     /**
      * 查询已办事件
      * @author 李帅
      * @param id
      * @return
      */
     List<Map<String,Object>> selectProcessed(Map<String, Object> pgMap) throws Exception;
     /**
      * <pre>dealInformation 
      * Created by 家栋梁 on 2017年10月26日下午2:28:09  
      *
      * @return</pre>
      */
     List<Map<String,Object>> dealInformation(int id) throws Exception;
     /**
      * <pre>dealCount 
      * Created by 家栋梁 on 2017年10月26日下午4:04:40  
      *
      * @param id
      * @return</pre>
      */
     List<EventInfo> dealCount(int id) throws Exception;
     /**
      * 大屏展示: 今日事件等级统计
      * @author 李帅
      * @return
      */
     Map<String,Object> selectLevelCount() throws Exception;
     /**
      * 大屏展示: 事件等级统计
      * @author 李帅
      * @return
      */
     List<EventInfo> selectLevelSum() throws Exception;
     
     /**
      * 根据时间查询事件等级的数量
      * @author 李帅
      * @param paramMap
      * @return
      */
     Map<String, Object> selectLevelCountByTime(Map<String, Object> paramMap);
     /**
      * 大屏展示: 事件状态统计
      * @author 李帅
      * @return
      */
     List<EventInfo> selectStatusCount() throws Exception;
     
     /**
      * 获取处理用户的所有事件列表
      * */
     List<EventInfo> selectEventDealInfoByUser(Map<String, Object> pgMap) throws Exception;
     /**
      * 获取处理用户的所有事件列表总数
      * @author 李帅
      * @param pgMap
      * @return
      */
     int countEventDealInfoByUser(Map<String, Object> pgMap) throws Exception;
     /**
      * 获取服务台用户的所有事件列表
      * */
     List<EventInfo> selectEventAcceptInfoByUser(Map<String, Object> pgMap) throws Exception;
     /**
      * 获取服务台用户的所有事件列表
      * @author 李帅
      * @param pgMap
      * @return
      */
     int countEventAcceptInfoByUser(Map<String, Object> pgMap) throws Exception;
     /**
      * <pre>selectAcceptDuty (查询当天需要受理的信息)
      * Created by 家栋梁 on 2017年11月20日下午3:37:28  
      *
      * @param id
      * @return</pre>
      */
     List<Map<String,Object>> selectAcceptDuty() throws Exception;
     /**
      * <pre>selectAcceptCount (查询需要受理的总条数)
      * Created by 家栋梁 on 2017年11月21日上午11:47:54  
      *
      * @return</pre>
      */
     int selectAcceptCount(int id) throws Exception;
     /**
      * <pre>selectEvaluate (消息提示评价信息)
      * Created by 家栋梁 on 2017年11月21日下午1:51:24  
      *
      * @param id
      * @return</pre>
      */
     List<Map<String,Object>> selectEvaluate(int id) throws Exception;
     /**
      * <pre>selectEvaluateCount (消息提示评价总条数)
      * Created by 家栋梁 on 2017年11月21日下午1:56:56  
      *
      * @param id
      * @return</pre>
      */
     int selectEvaluateCount(int id);
     /**
      * <pre>selectMessageDeal (提示信息处理信息)
      * Created by 家栋梁 on 2017年11月21日下午3:09:03  
      *
      * @param id
      * @return</pre>
      */
     List<Map<String,Object>> selectMessageDeal(int id) throws Exception;
     /**
      * <pre>selectMessageDealCount (提示信息处理信息的总条数)
      * Created by 家栋梁 on 2017年11月21日下午3:11:09  
      *
      * @param id
      * @return</pre>
      */
     int selectMessageDealCount(int id) throws Exception;
     /**
      * <pre>selectMessageAnewDeal (消息提示确认驳回的处理信息)
      * Created by 家栋梁 on 2017年11月21日下午3:13:35  
      *
      * @param id
      * @return</pre>
      */
     List<Map<String,Object>> selectMessageAnewDeal(int id) throws Exception;
     /**
      * <pre>selectMessageAnewDealCount (消息提示确认驳回的处理信息总条数)
      * Created by 家栋梁 on 2017年11月21日下午3:14:15  
      *
      * @param id
      * @return</pre>
      */
     int selectMessageAnewDealCount(int id) throws Exception;
     /**
      * 查询评价信息
      * @author 李帅
      * @param id
      * @return
      */
     EventInfo selectAssetsMessage(int id) throws Exception;
     /**
     * 审核查看
     * @author 李帅
     * @param eventId
     * @return
     */
     Map<String,Object> selectAcceptGiveCheck(int eventId) throws Exception;
     /**
      * <pre>selectAcceptCount (查询受理数量)
      * Created by 家栋梁 on 2017年12月6日上午11:11:56  
      *
      * @param map
      * @return</pre>
      */
     List<Map<String,Object>> selectAcceptNameCount(Map<String,Object> map) throws Exception;
     /**
      * 一个月内每天的申告数量
      * @author 李帅
      * @return
      */
     List<EventInfo> selectMonthApplyCount() throws Exception;
     /**
      * 今日事件源
      * @author 李帅
      * @return
      */
     List<EventInfo> selectEventCause() throws Exception;
     /**
      * 查询未受理信息
      * 2018年1月9日 下午6:52:09
      * @李帅
      * @param pgMap
      */
     List<Map<String, Object>> selectNotAcceptList(Map<String, Object> pgMap) throws Exception;
     
     /**
      * 查询一段事件内的申告趋势
      * @author 李帅
      * @param paramMap
      * @return
      * @throws Exception
      */
     List<Map<String, Object>> selectApplyCountByTime(Map<String, Object> paramMap) throws Exception;
     
     List<Map<String,Object>> selectServiceDeptCount(Map<String,Object> map);
     
     //部门事件统计导出
     List<Map<String,Object>> exportDeptInform(Map<String,Object> map)throws Exception;
     
}