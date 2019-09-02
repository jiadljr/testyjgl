package com.qkby.sysmanage.dao;

import com.qkby.sysmanage.entity.SysDutyInfo;
import java.util.List;
import java.util.Map;

public interface SysDutyInfoDao {

    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(SysDutyInfo record);

    List<SysDutyInfo> selectDuty(Map<String,Object> map)throws Exception;

    List<SysDutyInfo> selectByMap(Map<String,Object> map)throws Exception;

    int updateByPrimaryKeySelective(SysDutyInfo record)throws Exception;
    /**
     * <pre>findDutyArrangeByName 
     * Created by 家栋梁 on 2017年10月26日下午4:25:03  
     *
     * @param id
     * @return</pre>
     */
    List<SysDutyInfo> findDutyArrangeByName(int id)throws Exception;
    /**
     * <pre>selectDutyDate (查询今天是否值班)
     * Created by 家栋梁 on 2017年11月23日上午10:51:44  
     *
     * @param id
     * @return</pre>
     */
    List<Map<String,Object>> selectDutyDate(int id)throws Exception;
}