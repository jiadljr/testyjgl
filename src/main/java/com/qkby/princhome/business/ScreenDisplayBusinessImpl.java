package com.qkby.princhome.business;

import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.qkby.analysis.dao.EventInfoDayDao;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysServiceTypeDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysServiceType;
import com.qkby.utils.Utils;
@Service("ScreenDisplayBusiness")
public class ScreenDisplayBusinessImpl implements ScreenDisplayBusiness{
	@Resource
	SysDeptInfoDao sysDeptInfoDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	SysServiceTypeDao sysServiceTypeDao;
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	EventInfoDayDao eventInfoDayDao;
	@Override
	public Map<String, Object> selectScreen(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pgMap = new HashMap<String, Object>();
		List<SysDeptInfo> countDeptApply = sysDeptInfoDao.deptCountSum();
		Map<String,Object> levelCount = eventInfoDao.selectLevelCount();
		
		int apply = ConstantMenu.LEVEL_STATUS_INNIT;//申告
		int accept = ConstantMenu.LEVEL_STATUS_INNIT;//已受理
		int deal = ConstantMenu.LEVEL_STATUS_INNIT;//已处理
		int finish = ConstantMenu.LEVEL_STATUS_INNIT;//已完成
		int high = ConstantMenu.LEVEL_STATUS_INNIT;
		int middle = ConstantMenu.LEVEL_STATUS_INNIT;
		int low = ConstantMenu.LEVEL_STATUS_INNIT;
		List<EventInfo> statusCount = eventInfoDao.selectStatusCount();
		List<Integer> statusList = new ArrayList<Integer>();
		List<Integer> priorityList = new ArrayList<Integer>();
		if (statusCount != null) {
			statusList.add(ConstantMenu.EVENT_TWENTY);
			statusList.add(ConstantMenu.EVENT_DEAL_PENDING);
			statusList.add(ConstantMenu.EVENT_ANALYSIS_PENDING);
			statusList.add(ConstantMenu.EVENT_ANALYSIS_FINISH);
			for (EventInfo eventStatus : statusCount) {
				String status = eventStatus.getEventStatus();
				Integer priority = eventStatus.getEventPriority();
//				20,50,61,60,70,77
				for (int i = 0; i < statusList.size(); i++) {
					int sta = statusList.get(i);
					if (status.equals(String.valueOf(sta))) {
						apply++;
					}
				}
				if (status.equals(String.valueOf(ConstantMenu.EVENT_DEAL_PENDING))
						|| status.equals(String.valueOf(ConstantMenu.EVENT_ANALYSIS_PENDING)) || status.equals(String.valueOf(ConstantMenu.EVENT_ANALYSIS_FINISH))) {
					accept++;
				}
				if (status.equals(String.valueOf(ConstantMenu.EVENT_ANALYSIS_PENDING)) || status.equals(String.valueOf(ConstantMenu.EVENT_ANALYSIS_FINISH))) {
					deal++;
				}
				if (status.equals(String.valueOf(ConstantMenu.EVENT_ANALYSIS_PENDING)) || status.equals(String.valueOf(ConstantMenu.EVENT_ANALYSIS_FINISH))) {
					finish++;
				}
				if (priority == ConstantMenu.EVENT_PRIORITY_HIGH) {
					high++;
				}
				if (priority == ConstantMenu.EVENT_PRIORITY_MIDDLE) {
					middle++;
				}
				if (priority == ConstantMenu.EVENT_PRIORITY_LOW) {
					low++;
				}
			}
			statusList.clear();
			statusList.add(apply);
			statusList.add(accept);
			statusList.add(deal);
			statusList.add(finish);
			priorityList.add(high);
			priorityList.add(middle);
			priorityList.add(low);
		}
		List<EventInfo> timeDay = eventInfoDao.selectMonthApplyCount();
		
		List<SysServiceType> sysList = new ArrayList<SysServiceType>();
		List<SysServiceType> selectPidAll = sysServiceTypeDao.selectPidAll();
		String pId = "";
		for (SysServiceType sysServiceType : selectPidAll) {
			if (sysServiceType != null) {
				pId += sysServiceType.getParentId()+",";
			}else{
				continue;
			}
		}
		String parentId = pId.substring(0, pId.length()-1);
		//查询所有一级部门的
		List<SysServiceType> selectSerId = sysServiceTypeDao.selectSerId();
		for (SysServiceType serId : selectSerId) {
			if (serId == null) {
				continue;
			}
			List<Object> pIdList = new ArrayList<Object>();
			String sId = String.valueOf(serId.getId());
			if(parentId.contains(sId)){
				  pIdList.add(sId);
				  List<SysServiceType> selectpIdBySerId = sysServiceTypeDao.selectpIdBySerId(Integer.valueOf(sId));
				  for (SysServiceType twoSerId : selectpIdBySerId) {
					  if (twoSerId == null) {
						  continue;
					  }
					  String serIdTwo = String.valueOf(twoSerId.getId());
					  pIdList.add(serIdTwo);
					  if(parentId.contains(serIdTwo)){
						  List<SysServiceType> depIdByTwo = sysServiceTypeDao.selectpIdBySerId(Integer.valueOf(serIdTwo));
					    for (SysServiceType sysServiceType : depIdByTwo) {
					    	if (sysServiceType == null) {
								continue;
							}
					    	pIdList.add(sysServiceType.getId());
						}
					  }
				  }
				  map.put("pIdList", pIdList);
				  Utils.paging(request, 0, map, pgMap);
				  SysServiceType selectServiceApply = sysServiceTypeDao.selectServiceApply(map);
				  sysList.add(selectServiceApply);
			}else{
				pIdList.add(sId);
				map.put("pIdList", pIdList);
				Utils.paging(request, 0, map, pgMap);
				SysServiceType selectServiceApply = sysServiceTypeDao.selectServiceApplyByTime(map);
				sysList.add(selectServiceApply);
			}
		}
		/*
		 * 
		 */
		map.put("levelList", levelCount);//大屏展示: 事件等级统计（近一个月的）
		map.put("statusList", statusList);//大屏展示: 事件状态统计（近一个月的）优先级（近一个月的）
		map.put("priorityList", priorityList);//大屏展示: 优先级（近一个月的）
		map.put("countDeptApply", countDeptApply);//科室申告统计
		map.put("timeDay", timeDay);//一个月内的事件统计
		map.put("sysList", sysList);//大屏展示: 一级服务类型统计
		return map;
	}
	@Override
	public Map<String, Object> eventAlert(HttpServletRequest request) throws Exception {
		//事件提醒
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pgMap = new HashMap<String, Object>();
		Properties prop = new Properties();
		HttpSession session = request.getSession();
		prop.load(new FileInputStream(new File(session.getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties")));
		List<Map<String, Object>> untreated = eventInfoDao.untreated(pgMap);
		List<Map<String, Object>> dealList = new ArrayList<Map<String, Object>>();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long nowTime = new Date().getTime();
		for(Map<String, Object> event : untreated){
			Date responTime = (Date) event.get("dateCreate");//申告时间
			c.setTime(responTime);
			String priorityTime = "";
			Integer priority = (Integer) event.get("eventPriority");
			String eventPriority = String.valueOf(priority);
			if (eventPriority.equals("1")) {
				priorityTime = prop.getProperty("highTime");
			}else if (eventPriority.equals("2")) {
				priorityTime = prop.getProperty("middleTime");
			}else if (eventPriority.equals("3")) {
				priorityTime = prop.getProperty("lowTime");
			}
			c.add(Calendar.MINUTE, Integer.valueOf(priorityTime));
			Date dealTime = c.getTime();
			long dealSum = dealTime.getTime();
			long differ = dealSum-nowTime;
			long minutes = differ / (1000 * 60);
			event.put("minutes", minutes);
			event.put("dealTime", sf.format(dealTime));
			dealList.add(event);
		}
		/**未受理事件*/
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(ConstantMenu.EVENT_TWENTY);
		pgMap.put("statusList", statusList);
		List<Map<String, Object>> notAcceptList = eventInfoDao.selectNotAcceptList(pgMap);
		for (int i = notAcceptList.size()-1; i >= 0; i--) {
			Map<String, Object>	event = notAcceptList.get(i);
			dealList.add(0,event);
		}
		/**对未处理事件和超时事件合在一起*/ 
		map.put("dealList", dealList);//事件提醒
		return map;
	}
	@Override
	public Map<String, Object> allDealStatus(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Map<String, Object> pgMap = new HashMap<>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		List<Map<String, Object>> operStatus = sysUserInfoDao.selectEventCountByStatus(pgMap);//未处理、未确定和已完成数量
		map.put("operStatus", operStatus);
		return map;
	}
	/**
	 * @throws Exception 
	 * 
	 */
	@Override
	public List<EventInfo> showEventAcceptList() throws Exception {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(ConstantMenu.EVENT_TWENTY);
		pgMap.put("statusList", statusList);
		List<EventInfo> notAcceptList = eventInfoDao.selectByExample(pgMap);
		return notAcceptList;
	}
	public static void main(String[] args) {
		String c = "2018-1-29 11:10:10";
	    long newDate = new Date().getTime();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = null;
		try {
			parse = sf.parse(c);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long dealTime = parse.getTime();
		long differ = dealTime-newDate;
		long minutes = differ / (1000 * 60);
		System.out.println(minutes);
	}
}
