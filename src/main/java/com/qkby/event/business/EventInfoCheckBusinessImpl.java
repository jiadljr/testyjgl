package com.qkby.event.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.dao.EventLogInfoDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.utils.Utils;

@Service("EventInfoCheckBusiness")
public class EventInfoCheckBusinessImpl implements EventInfoCheckBusiness {
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	EventLogInfoDao eventLogInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;

	@Override
	public Map<String, Object> selectCheckList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		if (startTime.equals("")) {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if (endTime.equals("")) {
			endTime = null;
		}
		String eventLevel = request.getParameter("eventLevel");
		if (eventLevel.equals("")) {
			eventLevel = null;
		}
		String eventPriority = request.getParameter("eventPriority");
		if (eventPriority.equals("")) {
			eventPriority = null;
		}
		String eventService = request.getParameter("eventService");
		if (eventService.equals("")) {
			eventService = null;
		}
		pgMap.put("startTime", startTime);
		pgMap.put("endTime", endTime);
		pgMap.put("eventLevel", eventLevel);
		pgMap.put("eventPriority", eventPriority);
		pgMap.put("eventService", eventService);
		String pages = request.getParameter("pages");
		if("1".equals(pages)){
			Utils.pages(request, pgMap, map);
		}else{
			int totalRow = eventInfoDao.countNotCheck(pgMap);
			Utils.paging(request, totalRow, pgMap, map);
		}
		List<EventInfo> checkList = eventInfoDao.selectCheckList(pgMap);
		map.put("checkList", checkList);
		return map;
	}

	@Override
	public Map<String, Object> selectCheck(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String eId = request.getParameter("id");
		int id = Integer.valueOf(eId);
		// 申告受理信息
		Map<String, Object> eventOne = eventInfoDao.selectAcceptGiveCheck(id);
		map.put("eventOne", eventOne);
		return map;
	}
}
