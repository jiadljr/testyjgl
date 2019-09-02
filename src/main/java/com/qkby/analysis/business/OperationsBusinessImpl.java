package com.qkby.analysis.business;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoCheckDao;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventInfoCheck;
import com.qkby.event.entity.EventInfoDeal;
import com.qkby.sysmanage.dao.SysArrangeDao;
import com.qkby.sysmanage.dao.SysAssetsInfoDao;
import com.qkby.sysmanage.dao.SysDutyInfoDao;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.entity.SysArrange;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月26日下午2:05:42 
 *     
 * @version </pre>
 */
@Service
public class OperationsBusinessImpl implements OperationsBusiness{
	@Resource
    EventInfoDao eventInfoDao;
	@Resource
	SysDutyInfoDao sysDutyInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	SysFileInfoDao sysFileInfoDao;
	@Resource
	SysAssetsInfoDao sysAssetsInfoDao;
	@Resource
	EventInfoCheckDao eventInfoCheckDao;
	@Resource
	SysArrangeDao sysArrangeDao;
	@Override
	public List<Map<String, Object>> untreated(Map<String, Object> pgMap,HttpServletRequest request) throws Exception {
		Properties prop = new Properties();
		HttpSession session = request.getSession();
		prop.load(new FileInputStream(new File(session.getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties")));
		List<Map<String, Object>> untreated = eventInfoDao.untreated(pgMap);
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Map<String, Object> event : untreated){
			//查询事件涉及的所有处理人
			int eventId = (int)event.get("eventId");
			List<EventInfoDeal> dealUserAll = eventInfoDealDao.selectDealUserAll(eventId);
			String user = "";
			if (dealUserAll != null && dealUserAll.size()!=0) {
				for (EventInfoDeal dealUser : dealUserAll) {
					if (dealUser != null) {
						user += dealUser.getExtend1()+",";
					}
				}
				String[] users = user.split(",");
				event.put("users", users);
			}
			Date responTime = (Date) event.get("dateCreate");//响应时间
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
			event.put("dealTime", sf.format(dealTime));
		}
		return untreated;
	}

	@Override
	public List<Map<String,Object>> dealInformation(int id) throws Exception {
		return eventInfoDao.dealInformation(id);
	}

	@Override
	public List<EventInfo> dealCount(int id) throws Exception {
		 List<EventInfo> dealCount = eventInfoDao.dealCount(id);
		 return dealCount;
	}

	@Override
	public List<SysArrange> findDutyArrangeByName(int id) throws Exception {
//		 List<SysDutyInfo> findDutyArrangeByName = sysDutyInfoDao.findDutyArrangeByName(id);
		Map<String, Object> pgMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		pgMap.put("id_user", id);
		pgMap.put("now_time", Utils.getYesterday());
		List<SysArrange> arrangeList = sysArrangeDao.selectByExample(pgMap);
		List<String> list = new ArrayList<String>();
		for(SysArrange arrange:arrangeList){
			list.add(sf.format(arrange.getDutyStartTime()));
			list.add(sf.format(arrange.getDutyEndTime()));
		}
		List newList = new ArrayList(new HashSet(list)); 
		return newList;
	}

	@Override
	public List<Map<String, Object>> selectProcessed(Map<String, Object> pgMap, HttpServletRequest request) throws Exception {
		Properties prop = new Properties();
		HttpSession session = request.getSession();
		prop.load(new FileInputStream(new File(session.getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties")));
		List<Map<String, Object>> untreated = eventInfoDao.selectProcessed(pgMap);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for(Map<String, Object> event : untreated){
			Date responTime = (Date) event.get("dateRespon");//响应时间
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
			event.put("responTime", sf.format(responTime));
			event.put("solveTime", priorityTime);
		}
		return untreated;
	}

	@Override
	public Map<String, Object> showOneself(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String eventId = request.getParameter("eventId");
		int id = Integer.valueOf(eventId);
		String dealId = request.getParameter("dealId");
		int dId = Integer.valueOf(dealId);
		EventInfo eventInfoOne = eventInfoDao.selectByPrimaryKey(id);// 通过id查询
		String eventStatus = eventInfoOne.getEventStatus();	
		int status = Integer.valueOf(eventStatus);
		EventInfo acceptList = eventInfoDao.selectEventAccept(id);
		List<Map<String,Object>> dealList = eventInfoDealDao.selectOneself(dId);//处理信息
		List<List<SysFileInfo>> fileLIst = new ArrayList<List<SysFileInfo>>();// 文件信息
		for (Map<String, Object> deal : dealList) {
			List<SysFileInfo> file = sysFileInfoDao.selectDealFile(dId);
			List<Map<String, Object>> dealFilesByOne = new ArrayList<Map<String, Object>>();
			for (SysFileInfo sysFileInfo : file) {
				String path = sysFileInfo.getPath();
				String fileType = path.substring(path.lastIndexOf(".") + 1);
				Map<String, Object> fileObject = new HashMap<>();
				fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
				fileObject.put("fileId", sysFileInfo.getId());
				dealFilesByOne.add(fileObject);
				deal.put("files", dealFilesByOne);
			}
			if (file.size() > 0 && file != null) {
				fileLIst.add(file);
			}
			List<SysAssetsInfo> assetsByDealId = sysAssetsInfoDao.selectAssetsByDealId(dId);
			String assets = "";
			if (assetsByDealId != null && assetsByDealId.size()>0) {
				for (int i = 0; i < assetsByDealId.size(); i++) {
					assets+=assetsByDealId.get(i).getName()+",";
				}
				assets = assets.substring(0,assets.lastIndexOf(","));
			}
			deal.put("assetsName", assets);
		}
		map.put("fileLIst", fileLIst);
		map.put("eventInfoOne", eventInfoOne);
		map.put("acceptList", acceptList);
		map.put("dealList", dealList);
		if (status == ConstantMenu.EVENT_ANALYSIS_PENDING) {// 70 确认信息
			EventInfoCheck sureList = eventInfoCheckDao.selectCheckMessage(id);
			map.put("sureList", sureList);
		}else if (status == ConstantMenu.EVENT_ANALYSIS_FINISH) {// 77评价信息
			EventInfoCheck sureList = eventInfoCheckDao.selectCheckMessage(id);
			EventInfo assessList = eventInfoDao.selectAssetsMessage(id);
			map.put("sureList", sureList);
			map.put("assessList", assessList);
		}
		return map;
	}

}
