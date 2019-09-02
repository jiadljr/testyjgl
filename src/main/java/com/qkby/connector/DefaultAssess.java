/**
 * 
 */
package com.qkby.connector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.dao.EventLogInfoDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventInfoDeal;
import com.qkby.event.entity.EventLogInfo;
@Component
public class DefaultAssess {
	@Resource
	EventLogInfoDao eventLogInfoDao;
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	
	@Scheduled(cron = "0 0 0 1 * ?")
//	@Scheduled(cron = "0 */1 * * * ?")
	public void defaultAssets() throws Exception {
		updateAssess();
	}
	
	/**
	 * 默认评价
	 * 2018年1月3日 下午3:02:28
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@Transactional
	public void updateAssess() throws Exception{
		/**查询处理完成，未评价的事件*/
		Map<String, Object> pgMap = new HashMap<String,Object>();
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(ConstantMenu.EVENT_ANALYSIS_PENDING);
		pgMap.put("statusList", statusList);
		List<EventInfo> eventInfo = eventInfoDao.selectByExample(pgMap);
		ResourceBundle paramMap = ResourceBundle.getBundle("paramConfig");
		String assessOver = paramMap.getString("assessOver");
		Date now = new Date();
		if (eventInfo != null && eventInfo.size()>0) {
			for(EventInfo event:eventInfo){
				List<EventInfoDeal> dateDeal = eventInfoDealDao.selectDatedealById(event.getId());
				if (dateDeal != null && dateDeal.size()>0) {
					Date dateDe = dateDeal.get(dateDeal.size()-1).getDateDeal();
					Calendar c = Calendar.getInstance();
					c.setTime(dateDe);
					c.add(Calendar.DATE, Integer.valueOf(assessOver));
					if (now.getTime() > c.getTime().getTime()) {
						 Integer eventId = event.getId();
						 EventInfo eInfo = new EventInfo();
						 eInfo.setId(eventId);
						 eInfo.setEventAssess(5);
						 eInfo.setDateAssess(new Date());
						 eInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_ANALYSIS_FINISH));
						 eventInfoDao.updateAssets(eInfo);
						 
						 EventLogInfo eventLog = new EventLogInfo();
						 eventLog.setEventId(eventId);
						 eventLog.setEventCode(event.getEventCode());
						 eventLog.setEventName(event.getEventName());
						 eventLog.setEventStatus(ConstantMenu.EVENT_ANALYSIS_FINISH);
						 eventLog.setIdOperUser(Integer.valueOf(event.getId()));
						 eventLog.setDateOper(new Date());
						 eventLog.setRemark("默认评价");
						 eventLogInfoDao.insert(eventLog);
					}
				}
			}
		}
	}
}
