package com.qkby.princhome.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.sysmanage.dao.SysAssetsInfoDao;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.dao.SysServiceTypeDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysUserInfo;

@Service("FrontDeskPageBusiness")
public class FrontDeskPageBusinessImpl implements FrontDeskPageBusiness{

	@Resource
	SysServiceTypeDao sysServiceTypeDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	SysAssetsInfoDao sysAssetsInfoDao;
	@Resource
	SysFileInfoDao sysFileInfoDao;
	@Override
	public Map<String, Object> selectFrontDesk(HttpServletRequest request) throws Exception, IOException {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		//1.待受理事件
		List<Integer> statusList = new ArrayList<Integer>();
		statusList.add(ConstantMenu.EVENT_TWENTY);
		pgMap.put("statusList", statusList);
		List<EventInfo> acceptList = eventInfoDao.selectByExample(pgMap);
		map.put("acceptList", acceptList);
		//2.待确定事件
		/*statusList.remove(0);
		statusList.add(ConstantMenu.EVENT_SURE_PENDING);
		List<EventInfo> notSureList= eventInfoDao.selectByExample(pgMap);
		map.put("notSureList", notSureList);*/
		//3.超时事件
		Properties prop = new Properties();
		HttpSession session = request.getSession();
		prop.load(new FileInputStream(new File(session.getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties")));
		statusList.remove(0);
		statusList.add(ConstantMenu.EVENT_DEAL_PENDING);
		statusList.add(ConstantMenu.EVENT_DEALSURE_REJECT);
		List<EventInfo> eventList= eventInfoDao.selectByExample(pgMap);
		List<EventInfo> overTimeList = new ArrayList<EventInfo>();//超时事件列表
		Calendar c = Calendar.getInstance();
		long nowCreated = new Date().getTime();//当前时间的毫秒值
		for(EventInfo event : eventList){
			Date responTime = event.getDateCreate();//申告时间
			c.setTime(responTime);
			String priorityTime = "";
			Integer priority = event.getEventPriority();
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
			long dealTimeCreated = dealTime.getTime();
			if (dealTimeCreated < nowCreated) {
				overTimeList.add(event);
			}
		}
		map.put("overTimeList", overTimeList);
		//4.今日新增事件
		List<EventInfo> todayEvent = eventInfoDao.selectTodayEvent();
		map.put("todayEvent", todayEvent);
		//5.事件等级分布
		List<EventInfo> levelCount = eventInfoDao.selectLevelSum();
		List<Integer> levelList = new ArrayList<Integer>();
		int levelOne = ConstantMenu.LEVEL_STATUS_INNIT;
		int levelTwo = ConstantMenu.LEVEL_STATUS_INNIT;
		int levelThree = ConstantMenu.LEVEL_STATUS_INNIT;
		int levelFour = ConstantMenu.LEVEL_STATUS_INNIT;
		if (levelCount != null) {
			for (EventInfo eventLevel : levelCount) {
				Integer level = eventLevel.getEventLevel();
				if (level == ConstantMenu.EVENT_ONE) {
					levelOne++;
				}
				if (level == ConstantMenu.EVENT_TWO) {
					levelTwo++;
				}
				if (level == ConstantMenu.EVENT_THREE) {
					levelThree++;
				}
				if (level == ConstantMenu.EVENT_FOUR) {
					levelFour++;
				}
			}
		}
		levelList.add(levelOne);
		levelList.add(levelTwo);
		levelList.add(levelThree);
		levelList.add(levelFour);
		map.put("levelList", levelList);
		//6.运维人员当前情况
		List<SysUserInfo> notDealAll = sysUserInfoDao.selectNotDealAll(ConstantMenu.OPS_SIX);
		map.put("notDealAll", notDealAll);
		return map;
	}
	@Override
	public Map<String, Object> selectOverTimeSeeList(int id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		EventInfo eventInfoOne = eventInfoDao.selectByPrimaryKey(id);
		EventInfo acceptList = eventInfoDao.selectEventAccept(id);
		List<Map<String, Object>> overTimeList = eventInfoDealDao.selectOverTimeList(id);
		List<List<SysFileInfo>> fileLIst = new ArrayList<List<SysFileInfo>>();// 文件信息
		if(overTimeList.size()>0 && overTimeList!=null){
			for (Map<String, Object> overTime : overTimeList) {
				int dealId = (int) overTime.get("id");
				List<SysFileInfo> file = sysFileInfoDao.selectDealFile(dealId);
				List<Map<String, Object>> dealFilesByOne = new ArrayList<Map<String, Object>>();
				if(file.size()>0 && file!=null){
					for (SysFileInfo sysFileInfo : file) {
						String path = sysFileInfo.getPath();
						String fileType = path.substring(path.lastIndexOf(".") + 1);
						Map<String, Object> fileObject = new HashMap<>();
						fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
						fileObject.put("fileId", sysFileInfo.getId());
						dealFilesByOne.add(fileObject);
						overTime.put("files", dealFilesByOne);
					}
					fileLIst.add(file);
				}
				List<SysAssetsInfo> assetsByDealId = sysAssetsInfoDao.selectAssetsByDealId(dealId);
				String assets = "";
				if (assetsByDealId != null && assetsByDealId.size()>0) {
					for (int i = 0; i < assetsByDealId.size(); i++) {
						assets+=assetsByDealId.get(i).getName()+",";
					}
					assets = assets.substring(0,assets.lastIndexOf(","));
				}
				overTime.put("assetsName", assets);
			}
		}
		map.put("fileLIst", fileLIst);
		map.put("eventInfoOne", eventInfoOne);
		map.put("acceptList", acceptList);
		map.put("dealList", overTimeList);
		
		return map;
	}
}
