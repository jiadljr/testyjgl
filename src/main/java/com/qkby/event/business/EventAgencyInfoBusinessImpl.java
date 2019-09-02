package com.qkby.event.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventInfoDeal;
import com.qkby.sysmanage.business.SysArrangeBusiness;
import com.qkby.sysmanage.dao.SysDutyInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.dao.SysUserRoleInfoDao;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Utils;

@Service("EventAgencyInfoBusinessImpl")
public class EventAgencyInfoBusinessImpl implements EventAgencyInfoBusiness {
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	SysUserRoleInfoDao sysUserRoleInfoDao;
	@Resource
	SysDutyInfoDao sysDutyInfoDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	SysArrangeBusiness sysArrangeBusiness;
	
	/**
	 * @throws Exception 
	 * 
	 * */
	@Override
	public Map<String, Object> findEventAgencyInfo(Integer user_id,Integer dutyYes,Map<String, Object> pgMap,HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<EventInfo> eventList = new ArrayList<EventInfo>();
		pgMap.put("user_id", user_id);
		String duty = "yes";
		if(dutyYes.equals(user_id)){
			String pages = request.getParameter("pages");
			if ("1".equals(pages)) {
				Utils.pages(request, pgMap, map);
			} else {
				int totalRow = eventInfoDao.countEventAcceptInfoByUser(pgMap);
				Utils.paging(request, totalRow, pgMap, map);
			}
			eventList = eventInfoDao.selectEventAcceptInfoByUser(pgMap);
			duty = "yes";
		}else{
			String pages = request.getParameter("pages");
			if ("1".equals(pages)) {
				Utils.pages(request, pgMap, map);
			} else {
				int totalRow = eventInfoDao.countEventDealInfoByUser(pgMap);
				Utils.paging(request, totalRow, pgMap, map);
			}
			duty = "no";
			eventList = eventInfoDao.selectEventDealInfoByUser(pgMap);
		}
		map.put("eventList", eventList);
		map.put("duty", duty);
		return map;
	}

	@Override
	public List<SysUserInfo> selectDealUser(Map<String, Object> pgMap) throws Exception {
		return sysUserInfoDao.selectDealUser(pgMap);
	}
	@Transactional
	@Override
	public Map<String, Object> addDealUser(int event_id, String[] dealUser) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = "success";
		map.put("event_id", event_id);
		map.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> operUser = sysUserInfoDao.selectNowDealUser(map);
		String alreadyChoose = "";
		if (dealUser!=null && !"".equals(dealUser)) {
			for (int i = 0; i < dealUser.length; i++) {
				if(operUser.size()>0 && operUser!=null){
					for (SysUserInfo oper : operUser) {
						if (oper.getId() == Integer.valueOf(dealUser[i])) {
							alreadyChoose+=oper.getName();
						}
					}
				}
			}
		}
		if (!"".equals(alreadyChoose) && alreadyChoose!=null) {
			result = "error";
			map.put("alreadyChoose", alreadyChoose);
			map.put("result", result);
			return map;
		}
		EventInfoDeal deal = new EventInfoDeal();
		for (int i = 0; i < dealUser.length; i++) {
			int dealId = Integer.valueOf(dealUser[i]);
			deal.setIdUserDeal(dealId);
			deal.setIdEvent(event_id);
			deal.setDateRespon(new Date());
			deal.setDealStatus(ConstantMenu.DEAL_ONE);
			eventInfoDealDao.insert(deal);
		}
		map.put("result", result);
		return map;
	}
	
	

}
