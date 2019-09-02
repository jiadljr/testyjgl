package com.qkby.analysis.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import com.qkby.analysis.dao.EventInfoDayDao;
import com.qkby.analysis.entity.EventInfoDay;
import com.qkby.sysmanage.dao.SysServiceTypeDao;
import com.qkby.sysmanage.entity.SysServiceType;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午6:31:06 
 *     
 * @version </pre>
 */
@Service
public class EventInfoDayBusinessImpl implements EventInfoDayBusiness{
    @Resource
	EventInfoDayDao eventInfoDayDao;
    @Resource
    SysServiceTypeDao sysServiceTypeDao;
	
    @Override
	public List<EventInfoDay> selectDay() throws Exception {
		return eventInfoDayDao.selectDay();
	}

	@Override
	public List<SysServiceType> selectDepId(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysServiceType> sysList = new ArrayList<SysServiceType>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> pMap = new HashMap<String,Object>();
		String pId = "";
		//查询所有服务类型的pId
		if("".equals(startTime)){
			startTime = null;
		}
		if("".equals(endTime)){
			endTime = null;
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		List<SysServiceType> selectPidAll = sysServiceTypeDao.selectPidAll();
		for (SysServiceType sysServiceType : selectPidAll) {
			pId += sysServiceType.getParentId()+",";
		}
		String parentId = pId.substring(0, pId.length()-1);
		
		//查询所有一级部门的
		List<SysServiceType> selectDepId = sysServiceTypeDao.selectSerId();
		for (SysServiceType depId : selectDepId) {
			List<Object> pIdList = new ArrayList<Object>();
			String deptId = String.valueOf(depId.getId());
			if(parentId.contains(deptId)){
				  pIdList.add(deptId);
	            List<SysServiceType> selectpIdByDepId = sysServiceTypeDao.selectpIdBySerId(Integer.valueOf(deptId));
				  for (SysServiceType twoDepId : selectpIdByDepId) {
					  String depIdTwo = String.valueOf(twoDepId.getId());
					  pIdList.add(depIdTwo);
					  if(parentId.contains(depIdTwo)){
						  List<SysServiceType> depIdByTwo = sysServiceTypeDao.selectpIdBySerId(Integer.valueOf(depIdTwo));
					    for (SysServiceType sysServiceType : depIdByTwo) {
					    	pIdList.add(sysServiceType.getId());
						}
					  }
				}
				  map.put("pIdList", pIdList);
				  Utils.paging(request, 0, map, pMap);
				  SysServiceType selectServiceApply = sysServiceTypeDao.selectServiceApply(map);
				  sysList.add(selectServiceApply);
			}else{
				pIdList.add(deptId);
				map.put("pIdList", pIdList);
				Utils.paging(request, 0, map, pMap);
				SysServiceType selectServiceApply = sysServiceTypeDao.selectServiceApply(map);
				sysList.add(selectServiceApply);
			}
		}
		return sysList;
	}

	@Override
	public List<Map<String,Object>> selectSerCounAll(Map<String,Object> map) throws Exception {
		List<Map<String,Object>> selectSerCounAll = sysServiceTypeDao.selectServiceTypeApplyTime(map);
		return selectSerCounAll;
	}

	@Override
	public List<Map<String,Object>> selectServiceTypeApplyAll(Map<String, Object> map) throws Exception {
		List<Map<String,Object>> selectServiceTypeApplyAll = sysServiceTypeDao.selectServiceTypeApplyAll(map);
		return selectServiceTypeApplyAll;
	}
}
