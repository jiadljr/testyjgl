package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;
import com.qkby.sysmanage.entity.SysServiceType;

public interface SysServiceTypeBusiness {

	/**
	 * 查询所有服务类型
	 * @author 李帅
	 * @return
	 */
	List<SysServiceType> selectServiceTypeAll() throws Exception;
	/**
	 * <pre>selectServiceApply 
	 * Created by 家栋梁 on 2017年10月25日下午5:49:37  
	 *
	 * @param map
	 * @return</pre>
	 */
	SysServiceType selectServiceApply(Map<String,Object> map) throws Exception;
	
	/**
	 * 根据特定时间查询服务类型占比：top5
	 * @author 李帅
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	List<SysServiceType> selectServicePercentByTime(Map<String, Object> paramMap) throws Exception;
	
	List<SysServiceType> selectServiceTypePareateId(Integer id);
}
