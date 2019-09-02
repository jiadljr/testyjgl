package com.qkby.event.business;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventLogInfoDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventLogInfo;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月18日上午11:07:05 
 *     
 * @version </pre>
 */
@Service
public class EventAssessBusinessImpl implements EventAssessBusiness{
    @Resource
    EventInfoDao eventInfoDao;
    @Resource
    EventLogInfoDao eventLogInfoDao;

	@Transactional
	@Override
	public String updateDealAsses(HttpServletRequest request, EventInfo eventInfo,EventLogInfo eventLogInfo) throws Exception {
		String mass = "success";
		int up = eventInfoDao.updateAssets(eventInfo);
		if (up == 0) {
			mass = "error";
		}
		eventLogInfoDao.insert(eventLogInfo);
		return mass;
	}
}
