package com.qkby.analysis.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoCheckDao;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.sysmanage.dao.SysAssetsInfoDao;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.utils.Utils;

@Service("AnalysisEventInfoBusiness")
public class AnalysisEventInfoBusinessImpl implements AnalysisEventInfoBusiness {
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	EventInfoCheckDao eventInfoCheckDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	SysFileInfoDao sysFileInfoDao;
	@Resource
	SysAssetsInfoDao sysAssetsInfoDao;

	@Override
	public Map<String, Object> selectAnalysisList(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		String eventContact = request.getParameter("eventContact");
		if (eventContact.equals("")) {
			eventContact = null;
		}
		String eventCode = request.getParameter("eventCode");
		if (eventCode.equals("")) {
			eventCode = null;
		}
		String idService = request.getParameter("idService");
		if (Utils.isNum(idService)) {
			int serviceId = Integer.valueOf(idService);
			pgMap.put("serviceId", serviceId);
		}
		String idDept = request.getParameter("idDept");
		if (Utils.isNum(idDept)) {
			int deptId = Integer.valueOf(idDept);
			pgMap.put("deptId", deptId);
		}
		List<Integer> eventStatus = new ArrayList<>();
		String eventSource = request.getParameter("eventSource");
		if (!"".equals(eventSource)) {
			eventStatus.add(Integer.valueOf(eventSource));
		}
		if (eventStatus.size() == 0) {
			eventStatus = null;
		}
		String eventLevel = request.getParameter("eventLevel");
		if (Utils.isNum(eventLevel)) {
			int level = Integer.valueOf(eventLevel);
			pgMap.put("level", level);
		}
		pgMap.put("startTime", startTime);
		pgMap.put("endTime", endTime);
		pgMap.put("eventContact", eventContact);
		pgMap.put("eventCode", eventCode);
		pgMap.put("eventStatus", eventStatus);

		int totalRow = eventInfoDao.countAnaByExample(pgMap);
		Utils.paging(request, totalRow, pgMap, map);
		List<Map<String, Object>> eventList = eventInfoDao.selectAnaListByExample(pgMap);
		map.put("eventList", eventList);
		return map;
	}

	@Override
	public Map<String, Object> checkEventOne(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String eventId = request.getParameter("eventId");
		int id = Integer.valueOf(eventId);
		String eventStatus = request.getParameter("status");
		pgMap.put("id_event", eventId);
		// 通过id查询
		EventInfo eventInfoOne = eventInfoDao.selectByPrimaryKey(id);
		int status = Integer.valueOf(eventStatus);
		// 通过状态判断需要查的信息
		if (status == ConstantMenu.EVENT_TWENTY) {
			map.put("eventInfoOne", eventInfoOne);
		}
		if (status == ConstantMenu.EVENT_DEAL_PENDING || status == ConstantMenu.EVENT_ANALYSIS_PENDING || status == ConstantMenu.EVENT_ANALYSIS_FINISH) {// 未处理50
			EventInfo acceptList = eventInfoDao.selectEventAccept(id);
			List<Map<String, Object>> dealList = eventInfoDealDao.selectAcceptAfterDeal(id);
			List<List<SysFileInfo>> fileLIst = new ArrayList<List<SysFileInfo>>();// 文件信息
			if (dealList.size() > 0 && dealList != null) {
				for (Map<String, Object> deal : dealList) {
					int dealId = (int) deal.get("id");
					List<SysFileInfo> file = sysFileInfoDao.selectDealFile(dealId);
					List<Map<String, Object>> dealFilesByOne = new ArrayList<Map<String, Object>>();
					if (file.size() > 0 && file != null) {
						for (SysFileInfo sysFileInfo : file) {
							String path = sysFileInfo.getPath();
							String fileType = path.substring(path.lastIndexOf(".") + 1);
							Map<String, Object> fileObject = new HashMap<>();
							fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
							fileObject.put("fileId", sysFileInfo.getId());
							dealFilesByOne.add(fileObject);
							deal.put("files", dealFilesByOne);
						}
						fileLIst.add(file);
					}
					List<SysAssetsInfo> assetsByDealId = sysAssetsInfoDao.selectAssetsByDealId(dealId);
					String assets = "";
					if (assetsByDealId != null && assetsByDealId.size() > 0) {
						for (int i = 0; i < assetsByDealId.size(); i++) {
							assets += assetsByDealId.get(i).getName() + ",";
						}
						assets = assets.substring(0, assets.lastIndexOf(","));
					}
					deal.put("assetsName", assets);
				}
			}
			map.put("fileLIst", fileLIst);
			map.put("dealList", dealList);
			map.put("eventInfoOne", eventInfoOne);
			map.put("acceptList", acceptList);
		}
		if (status == ConstantMenu.EVENT_ANALYSIS_FINISH) {// 77评价信息
			EventInfo assessList = eventInfoDao.selectAssetsMessage(id);
			map.put("assessList", assessList);// 评价信息
		}
		return map;
	}

}
