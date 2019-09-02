package com.qkby.event.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventDealAssetsDao;
import com.qkby.event.dao.EventFileManageInfoDao;
import com.qkby.event.dao.EventInfoCheckDao;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.dao.EventLogInfoDao;
import com.qkby.event.dao.KbEventFileInfoDao;
import com.qkby.event.dao.KbEventInfoDao;
import com.qkby.event.entity.EventDealAssets;
import com.qkby.event.entity.EventFileManageInfo;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventInfoDeal;
import com.qkby.event.entity.EventLogInfo;
import com.qkby.event.entity.KbEventFileInfo;
import com.qkby.event.entity.KbEventInfo;
import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.dao.SysAssetsInfoDao;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.dao.SysPostInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysPostInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.FilesUpload;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>
 * 项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月13日下午2:52:32 
 *     
 * &#64;version
 * </pre>
 */
@Service
public class EventInfoDealBusinessImpl implements EventInfoDealBusiness {
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	SysDeptInfoDao sysDeptInfoDao;
	@Resource
	EventInfoCheckDao eventInfoCheckDao;
	@Resource
	EventLogInfoDao eventLogInfoDao;
	@Resource
	SysPostInfoDao sysPostInfoDao;
	@Resource
	EventFileManageInfoDao eventFileManageInfoDao;
	@Resource
	SysFileInfoDao sysFileInfoDao;
	@Resource
	EventDealAssetsDao eventDealAssetsDao;
	@Resource
	SysAssetsInfoDao sysAssetsInfoDao;
	@Resource
	KbEventInfoDao kbEventInfoDao;
	@Resource
	private KbEventFileInfoDao kbEventFileInfoDao;

	@Override
	public Map<String, Object> selectDealNot(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		if (startTime == "") {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if (endTime == "") {
			endTime = null;
		}
		String eventLevel = request.getParameter("eventLevel");
		if (eventLevel == "") {
			eventLevel = null;
		}
		String eventPriority = request.getParameter("eventPriority");
		if (eventPriority == "") {
			eventPriority = null;
		}
		String idDept = request.getParameter("idDept");
		if (idDept == "") {
			idDept = null;
		}
		pageMap.put("startTime", startTime);
		pageMap.put("endTime", endTime);
		pageMap.put("eventLevel", eventLevel);
		pageMap.put("eventPriority", eventPriority);
		pageMap.put("idDept", idDept);
		int totalRow = eventInfoDao.countDealNot(pageMap);
		Utils.paging(request, totalRow, pageMap, map);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<EventInfo> selectDealNot = eventInfoDao.selectDealNot(pageMap);
		for (EventInfo dealNot : selectDealNot) {
			Date dateCreate = dealNot.getDateCreate();
			String format = sf.format(dateCreate);
			dealNot.setExtend3(format);
		}
		map.put("selectDealNot", selectDealNot);
		return map;
	}

	@Override
	public Map<String, Object> selectDealEnd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		String startTime = request.getParameter("startTime");
		if (startTime == "") {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if (endTime == "") {
			endTime = null;
		}
		String eventLevel = request.getParameter("eventLevel");
		if (eventLevel == "") {
			eventLevel = null;
		}
		String eventPriority = request.getParameter("eventPriority");
		if (eventPriority == "") {
			eventPriority = null;
		}
		String idDept = request.getParameter("idDept");
		if (idDept == "") {
			idDept = null;
		}
		pageMap.put("userId", userId);
		pageMap.put("startTime", startTime);
		pageMap.put("endTime", endTime);
		pageMap.put("eventLevel", eventLevel);
		pageMap.put("eventPriority", eventPriority);
		pageMap.put("idDept", idDept);
		String pages = request.getParameter("pages");
		if ("1".equals(pages)) {
			Utils.pages(request, pageMap, map);
		} else {
			int totalRow = eventInfoDao.countDealEnd(pageMap);
			Utils.paging(request, totalRow, pageMap, map);
		}
		List<Map<String, Object>> selectDealEnd = eventInfoDao.selectDealEnd(pageMap);
		map.put("selectDealEnd", selectDealEnd);
		return map;
	}

	@Override
	public Map<String, Object> selectInformation(int id) throws Exception {
		Map<String, Object> pageMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		pageMap.put("userId", id);
		List<Map<String,Object>> selectDealEnd = eventInfoDao.selectMessageDeal(id);
		List<Map<String, Object>> selectAcceptDuty = eventInfoDao.selectAcceptDuty();
		int countDealAll = eventInfoDao.selectMessageDealCount(id);
		int selectAcceptCount = eventInfoDao.selectAcceptCount(id);
		List<Map<String, Object>> selectEvaluate = eventInfoDao.selectEvaluate(id);
		int selectEvaluateCount = eventInfoDao.selectEvaluateCount(id);
		List<Map<String, Object>> selectMessageAnewDeal = eventInfoDao.selectMessageAnewDeal(id);
		int selectMessageAnewDealCount = eventInfoDao.selectMessageAnewDealCount(id);
		List<Map<String, Object>> selectMessageCkeck = eventInfoCheckDao.selectMessageCkeck(id);
		int selectMessageCkeckCount = eventInfoCheckDao.selectMessageCkeckCount(id);
		map.put("selectMessageCkeck", selectMessageCkeck);
		map.put("selectMessageCkeckCount", selectMessageCkeckCount);
		map.put("selectMessageAnewDeal", selectMessageAnewDeal);
		map.put("selectMessageAnewDealCount", selectMessageAnewDealCount);
		map.put("selectEvaluateCount", selectEvaluateCount);
		map.put("selectEvaluate", selectEvaluate);
		map.put("selectDealEnd", selectDealEnd);
		map.put("selectAcceptDuty", selectAcceptDuty);
		map.put("countDealAll", countDealAll);
		map.put("selectAcceptCount", selectAcceptCount);
		return map;
	}

	@Override
	public int updateDealNot(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		EventInfoDeal eventInfoDeal = new EventInfoDeal();
		eventInfoDeal.setId(Integer.valueOf(id));
		eventInfoDeal.setDealStatus(ConstantMenu.DEAL_ZERO);
		eventInfoDeal.setDateRespon(new Date());
		int up = eventInfoDealDao.update(eventInfoDeal);
		return up;
	}

	@Override
	public Map<String, Object> dealEnd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> userMap = new HashMap<String, Object>();
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String oper = request.getParameter("oper");
		String id = request.getParameter("id");// 事件id
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		if ("".equals(id)) {
			id = null;
		}
		userMap.put("id", id);
		userMap.put("idUserDeal", userId);
		Map<String, Object> selectEventInfoAll = eventInfoDao.selectEventInfoAll(userMap);
		if (selectEventInfoAll == null) {
			throw new BusinessException("", "数据异常！请联系系统管理员");
		}
		Integer event_id = (Integer) selectEventInfoAll.get("id");
		int idDept = (int) selectEventInfoAll.get("id_dept");
		int idUserAplly = (int) selectEventInfoAll.get("id_user_aplly");
		int idUserAccept = (int) selectEventInfoAll.get("id_user_accept");
		int dealId = (int) selectEventInfoAll.get("deal_id");
		List<SysFileInfo> file = sysFileInfoDao.selectDealFile(Integer.valueOf(dealId));
		List<Map<String, Object>> dealFilesByOne = new ArrayList<Map<String, Object>>();
		for (SysFileInfo sysFileInfo : file) {
			String path = sysFileInfo.getPath();
			String fileType = path.substring(path.lastIndexOf(".") + 1);
			Map<String, Object> fileObject = new HashMap<>();
			fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
			fileObject.put("fileId", sysFileInfo.getId());
			dealFilesByOne.add(fileObject);
			map.put("files", dealFilesByOne);
		}
		List<SysAssetsInfo> assetsByDealId = sysAssetsInfoDao.selectAssetsByDealId(dealId);
		String assets = "";
		String assetsId = "";
		if (assetsByDealId != null && assetsByDealId.size() > 0) {
			for (int i = 0; i < assetsByDealId.size(); i++) {
				assets += assetsByDealId.get(i).getName() + ",";
				assetsId += assetsByDealId.get(i).getId() + ",";

			}
			assets = assets.substring(0, assets.lastIndexOf(","));
			assetsId = assetsId.substring(0, assetsId.lastIndexOf(","));
		}
		map.put("assetsName", assets);
		map.put("assetsId", assetsId);

		SysUserInfo userApply = sysUserInfoDao.userEventId(idUserAplly);
		SysUserInfo userAccept = sysUserInfoDao.userEventId(idUserAccept);
		SysDeptInfo deptName = sysDeptInfoDao.deptEvent(idDept);
		List<SysPostInfo> selectPostAll = sysPostInfoDao.selectPostAll();
		pgMap.put("event_id", event_id);
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> userByRoleType = sysUserInfoDao.selectDealUser(pgMap);
		EventLogInfo selectAnewDeal = eventLogInfoDao.selectAnewDeal(Integer.valueOf(id));
		/** 获取转派原因 */
		Integer id_user_deal = (Integer) selectEventInfoAll.get("id_user_deal");
		pgMap.put("rede_rein_id", id_user_deal);
		List<EventInfoDeal> dealDesc = eventInfoDealDao.selectDealDescByRedeReinId(pgMap);
		if (dealDesc.size()>0 && dealDesc!=null) {
			map.put("dealDesc", dealDesc.get(dealDesc.size()-1));
		}
		map.put("userByRoleType", userByRoleType);
		map.put("selectAnewDeal", selectAnewDeal);
		map.put("selectEventInfoAll", selectEventInfoAll);
		map.put("selectPostAll", selectPostAll);
		map.put("userApply", userApply);
		map.put("userAccept", userAccept);
		map.put("deptName", deptName);
		map.put("oper", oper);
		map.put("id", id);
		return map;
	}

	@Transactional
	@Override
	public Map<String, Object> updateDealEnd(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		insertFile(request, response);
		Map<String, Object> map = new HashMap<String, Object>();
		EventLogInfo eventLogInfo = new EventLogInfo();
		EventInfoDeal eventInfoDeal = new EventInfoDeal();
		EventInfo eventInfo = new EventInfo();
		KbEventInfo kbEventInfo = new KbEventInfo();
		KbEventFileInfo kbEventFileInfo = new KbEventFileInfo();
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		String dealResult = request.getParameter("dealResult");
		map.put("dealResult", Integer.valueOf(dealResult));
		String remark = request.getParameter("redeployRemark");
		if ("".equals(remark)) {
			remark = request.getParameter("dealRemark");
		}
		String eventCause = request.getParameter("eventCause");
		String id = request.getParameter("id");// 处理表id 到时候和资产表id 同时添加到关联表即可
		String idEvent = request.getParameter("idEvent");
		String eventCode = request.getParameter("eventCode");
		String eventName = request.getParameter("eventName");
		String userDeal = request.getParameter("userDeal");
		String eventLevel = request.getParameter("eventLevel");
		String eventDesc = request.getParameter("eventDesc");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String code = sf.format(date);
		String kbCode = "KBET" + code;
		if ("2".equals(dealResult)) {
			List<Integer> addNewAssets = new ArrayList<Integer>();
			List<Integer> delOldAssets = new ArrayList<Integer>();
			String assets_id = request.getParameter("assets_id");
			if (!"".equals(assets_id) && assets_id != null) {
				String[] assetsArray = assets_id.split(",");
				List<SysAssetsInfo> oldAssetsIds = sysAssetsInfoDao.selectAssetsByDealId(Integer.valueOf(id));
				if (oldAssetsIds != null && oldAssetsIds.size() > 0) {// 删除资产
					for (SysAssetsInfo oldAssId : oldAssetsIds) {
						for (int i = 0; i < assetsArray.length; i++) {
							if (assetsArray[i].equals(String.valueOf(oldAssId.getId()))) {
								continue;
							} else {
								delOldAssets.add(Integer.valueOf(oldAssId.getExtend1()));
							}
						}
					}
				}
				for (int i = 0; i < assetsArray.length; i++) {// 添加资产
					Boolean flag = true;
					if (assetsArray[i] != null && !"".equals(assetsArray[i].trim())) {
						for (int j = 0; j < oldAssetsIds.size(); j++) {
							if (oldAssetsIds.get(j).getId() == Integer.valueOf(assetsArray[i].trim())) {
								flag = false;
								break;
							}
						}
						if (flag) {
							addNewAssets.add(Integer.valueOf(assetsArray[i]));
						}
					}
				}
				if (delOldAssets != null && delOldAssets.size() > 0) {
					for (int i = 0; i < delOldAssets.size(); i++) {
						eventDealAssetsDao.deleteByPrimaryKey(delOldAssets.get(i));
					}
				}
				if (addNewAssets != null && addNewAssets.size() > 0) {
					for (int i = 0; i < addNewAssets.size(); i++) {
						EventDealAssets dealAssets = new EventDealAssets();
						dealAssets.setIdDeal(Integer.valueOf(id));
						dealAssets.setIdAssets(addNewAssets.get(i));
						eventDealAssetsDao.insert(dealAssets);
					}
				}
			}
			eventInfoDeal.setId(Integer.valueOf(id));
			eventInfoDeal.setDateDeal(new Date());
			eventInfoDeal.setIdUserDeal(userId);
			eventInfoDeal.setDealDesc(remark);
			eventInfoDeal.setEventCause(eventCause);
			eventInfoDeal.setDealStatus(Integer.valueOf(dealResult));
			int up = eventInfoDealDao.updateByPrimaryKeySelective(eventInfoDeal);
			Map<String, Object> pgMap = new HashMap<String, Object>();
			pgMap.put("id_event", idEvent);
			pgMap.put("e_id", id);
			List<EventInfoDeal> dealStatus = eventInfoDealDao.selectDealList(pgMap);
			Boolean dealStatusAll = true;
			if (dealStatus != null && dealStatus.size() > 0) {
				for (int i = 0; i < dealStatus.size(); i++) {
					Integer status = dealStatus.get(i).getDealStatus();
					if (status == 1) {
						dealStatusAll = false;
					}
				}
			}
			if (dealStatusAll) {
				// 所有人都处理完成之后向log表填数据
				eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_ANALYSIS_PENDING));
				eventInfo.setId(Integer.valueOf(idEvent));
				eventInfoDao.updateEventStatus(eventInfo);
				eventLogInfo.setEventId(Integer.valueOf(idEvent));
				eventLogInfo.setEventName(eventName);
				eventLogInfo.setEventCode(eventCode);
				eventLogInfo.setEventStatus(ConstantMenu.EVENT_ANALYSIS_PENDING);
				eventLogInfo.setIdOperUser(Integer.valueOf(userId));
				eventLogInfo.setDateOper(new Date());
				eventLogInfo.setRemark(remark);
				eventLogInfoDao.insert(eventLogInfo);
				List<EventInfoDeal> selectDealDesc = eventInfoDealDao.selectDealDesc(Integer.valueOf(idEvent));
				Map<String, Object> idMap = new HashMap<String, Object>();
				String resolvent = "";
				String ids = "";
				for (EventInfoDeal infoDeal : selectDealDesc) {
					resolvent += infoDeal.getDealDesc() + ",";
					ids += infoDeal.getId() + ",";
				}
				String[] idArray = ids.split(",");
				idMap.put("ids", idArray);
				resolvent = resolvent.substring(0, resolvent.length() - 1);
				kbEventInfo.setKbCode(kbCode);
				kbEventInfo.setEventCode(eventCode);
				kbEventInfo.setEventName(eventName);
				kbEventInfo.setEventDesc(eventDesc);
				kbEventInfo.setEventLevel(Integer.valueOf(eventLevel));
				kbEventInfo.setEventResolvent(resolvent);
				kbEventInfo.setEventCauses(eventCause);
				kbEventInfoDao.insert(kbEventInfo);
				Integer idDeal = kbEventInfo.getId();
				List<EventFileManageInfo> selectFileId = eventFileManageInfoDao.selectFileId(idMap);
				for (EventFileManageInfo kbEventFileInfo2 : selectFileId) {
					Integer fileId = kbEventFileInfo2.getIdFile();
					kbEventFileInfo.setFileId(fileId);
					kbEventFileInfo.setKbId(idDeal);
					kbEventFileInfoDao.insert(kbEventFileInfo);
				}
				/*
				 * ServletContext context = request.getServletContext();
				 * context.setAttribute("affirm", "您有一条新的评价信息!");
				 */
			}
			map.put("up", up);
			return map;
		}
		/** 判断选择的人员是否正在执行该事件 */
		String result = "success";
		String alreadyChoose = "";
		map.put("role_id", ConstantMenu.OPS_SIX);
		map.put("event_id", idEvent);
		List<SysUserInfo> operUser = sysUserInfoDao.selectNowDealUser(map);
		for (SysUserInfo oper : operUser) {
			if (oper.getId() == Integer.valueOf(userDeal)) {
				alreadyChoose += oper.getName();
			}
		}
		if (alreadyChoose != null && !"".equals(alreadyChoose)) {
			result = "error";
			map.put("result", result);
			map.put("alreadyChoose", alreadyChoose);
			return map;
		}
		eventInfoDeal.setId(Integer.valueOf(id));
		eventInfoDeal.setDateDeal(new Date());
		eventInfoDeal.setIdUserDeal(userId);
		eventInfoDeal.setIdRedeRein(Integer.valueOf(userDeal));
		eventInfoDeal.setDealDesc(remark);
		eventInfoDeal.setDealStatus(Integer.valueOf(dealResult));
		int up = eventInfoDealDao.updateByPrimaryKeySelective(eventInfoDeal);
		// 添加转派人的处理信息
		int dealId = Integer.valueOf(userDeal);
		EventInfoDeal deal = new EventInfoDeal();
		deal.setIdEvent(Integer.valueOf(idEvent));
		deal.setIdUserDeal(dealId);
		deal.setDateRespon(new Date());
		deal.setDealStatus(ConstantMenu.DEAL_ONE);
		eventInfoDealDao.insert(deal);
		ServletContext context = request.getServletContext();
		context.setAttribute("redeploy", "您有一条新的处理信息!" + dealId);
		map.put("up", up);
		return map;
	}

	public void insertFile(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		EventFileManageInfo eventFileManage = new EventFileManageInfo();
		SysFileInfo sysFileInf = new SysFileInfo();
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		FilesUpload file = new FilesUpload();
		Map<String, Object> fileUpload = file.upload2(request,response);
		String path_to = (String)fileUpload.get("path_to");
		String file_name = (String)fileUpload.get("file_name");
		if(!"".equals(path_to)){
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date date = new Date();
			String format = sf.format(date);
			int i=(int)(Math.random()*900)+100;
			String fileCode = "FJ"+format+i;
			sysFileInf.setCode(fileCode);
			sysFileInf.setIdFileType(4);
			sysFileInf.setPath(path_to);
			sysFileInf.setName(file_name);
			sysFileInf.setCreateDate(new Date());
			sysFileInf.setCreateUser(userId);
			sysFileInf.setRemark("附件");
			sysFileInfoDao.insert(sysFileInf);
			Integer id2 = sysFileInf.getId();
			eventFileManage.setIdDeal(Integer.valueOf(id));
			eventFileManage.setIdFile(id2);
			eventFileManageInfoDao.insert(eventFileManage);
		}
	}
}
