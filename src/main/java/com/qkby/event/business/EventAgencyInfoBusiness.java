package com.qkby.event.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.qkby.sysmanage.entity.SysUserInfo;


public interface EventAgencyInfoBusiness {
	public Map<String, Object> findEventAgencyInfo(Integer user_id,Integer dutyYes,Map<String, Object> pgMap,HttpServletRequest request) throws Exception;
	/**
	 * 查询处理人(对已在处理的人员进行过滤)
	 * @author 李帅
	 * @param pgMap
	 * @return
	 */
	public List<SysUserInfo> selectDealUser(Map<String, Object> pgMap) throws Exception;
	
	public Map<String, Object> addDealUser(int event_id,String[] dealUser) throws Exception;
}
