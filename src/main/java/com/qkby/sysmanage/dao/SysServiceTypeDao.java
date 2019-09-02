package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysServiceType;

public interface SysServiceTypeDao {
	/**
	 * 查询所有服务类型
	 * @author 李帅
	 * @return
	 */
	List<SysServiceType> selectServiceTypeAll()throws Exception;
	/**
	 * 服务类型统计
	 * @author 李帅
	 * @param map
	 * @return
	 */
	SysServiceType selectServiceApply(Map<String, Object> map)throws Exception;
	/**
	 * 根据id查询服务类型事件数
	 * @author 李帅
	 * @param id
	 * @return
	 */
	int selectCountByService(int id)throws Exception;
	/**
	 * 查询当月所有服务类型的统计
	 * @return
	 */
	List<Map<String, Object>> selectCountByMonth()throws Exception;
	/**
	 * 查询处理人处理服务类型总数
	 * @author 李帅
	 * @return
	 */
	List<SysServiceType> selectDealCountBySer(Map<String, Object> map)throws Exception;
	/**
	 * 查询服务类型对应处理时长
	 * 李帅
	 * @return
	 */
	List<SysServiceType> selectSerCounAll(Map<String, Object> map)throws Exception;
	/**
	 * 大屏展示: 服务类型申告统计
	 * @author 李帅
	 * @return
	 */
	List<SysServiceType> selectSerApply()throws Exception;
    int countByExample(SysServiceType example)throws Exception;

    int deleteByExample(SysServiceType example)throws Exception;

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(SysServiceType record)throws Exception;

    int insertSelective(SysServiceType record)throws Exception;

    List<SysServiceType> selectByExample(SysServiceType example)throws Exception;

    SysServiceType selectByPrimaryKey(Integer id)throws Exception;

    int updateByPrimaryKeySelective(SysServiceType record)throws Exception;

    int updateByPrimaryKey(SysServiceType record)throws Exception;
    /**
     * <pre>selectDepId (查询所有一级服务类型的ID)
     * Created by 家栋梁 on 2017年10月30日上午11:04:38  
     *
     * @return</pre>
     */
    List<SysServiceType> selectSerId()throws Exception;
    /**
     * <pre>selectPidAll (查询所有的pId)
     * Created by 家栋梁 on 2017年10月30日上午11:04:57  
     *
     * @return</pre>
     */
    List<SysServiceType> selectPidAll()throws Exception;
    /**
     * <pre>selectpIdByDepId (根据pId查询所有二级服务类型的ID)
     * Created by 家栋梁 on 2017年10月30日上午11:27:29  
     *
     * @return</pre>
     */
    List<SysServiceType> selectpIdBySerId(int id)throws Exception;
    /**
     * <pre>selectServiceTypeApplyAll 
     * Created by 家栋梁 on 2017年11月1日上午10:31:08  
     *
     * @param map
     * @return</pre>
     */
    List<Map<String,Object>> selectServiceTypeApplyAll(Map<String,Object> map)throws Exception;
    /**
     * <pre>selectServiceTypeApplyTime 
     * Created by 家栋梁 on 2017年12月6日下午1:02:17  
     *
     * @param map
     * @return</pre>
     */
    List<Map<String,Object>> selectServiceTypeApplyTime(Map<String,Object> map)throws Exception;
    /**
     * 大屏展示 最近三十天一级服务类型统计
     * @author 李帅
     * @param map
     * @return
     */
    SysServiceType selectServiceApplyByTime(Map<String,Object> map)throws Exception;
    
    /**
     * 根据特定时间查询服务类型占比：top5
     * @author 李帅
     * @param paramMap
     * @return
     * @throws Exception
     */
    List<SysServiceType> selectServicePercentByTime(Map<String,Object> paramMap)throws Exception;
    
    List<SysServiceType> selectServiceTypePareateId(Integer id);
 }