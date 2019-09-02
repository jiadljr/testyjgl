package com.qkby.event.business;

import java.io.File;
import java.io.FileOutputStream;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoCheckDao;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.dao.EventLogInfoDao;
import com.qkby.event.dao.KbEventFileInfoDao;
import com.qkby.event.dao.KbEventInfoDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventInfoCheck;
import com.qkby.event.entity.EventInfoDeal;
import com.qkby.event.entity.EventLogInfo;
import com.qkby.event.entity.KbEventFileInfo;
import com.qkby.event.entity.KbEventInfo;
import com.qkby.exception.BusinessException;
import com.qkby.proj.entity.TaskRecord;
import com.qkby.sysmanage.dao.SysAssetsInfoDao;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.dao.SysPostInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.dao.SysUserPostInfoDao;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Utils;

@Service("EventInfoAcceptbusiness")
public class EventInfoAcceptbusinessImpl implements EventInfoAcceptbusiness {
	@Resource
	EventInfoDao eventInfoDao;
	@Resource
	SysPostInfoDao sysPostInfoDao;
	@Resource
	SysUserPostInfoDao sysUserPostInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	EventLogInfoDao eventLogInfoDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	EventInfoCheckDao eventInfoCheckDao;
	@Resource
	SysDeptInfoDao sysDeptInfoDao;
	@Resource
	SysFileInfoDao sysFileInfoDao;
	@Resource
	SysAssetsInfoDao sysAssetsInfoDao;
	@Resource
	KbEventInfoDao kbEventInfoDao;
	@Resource
	private KbEventFileInfoDao kbEventFileInfoDao;
	@Override
	public Map<String, Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 查询参数
		Map<String, Object> pMap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Integer> statusList = new ArrayList<Integer>();
		String startTime = request.getParameter("startTime");
		if ("".equals(startTime)) {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if ("".equals(endTime)) {
			endTime = null;
		}
		String idDept = request.getParameter("idDept");
		if ("".equals(idDept)) {
			idDept = null;
		}
		String eventPer = request.getParameter("eventUser");
		if ("".equals(eventPer)) {
			eventPer = null;
		}
		String eventSource = request.getParameter("eventSource");// 来源
		if ("".equals(eventSource)) {
			eventSource = null;
		}

		String eventStatus = request.getParameter("statusNotSure");// 状态
		if ("".equals(eventStatus)) {
			eventStatus = null;
		}
		pMap.put("startTime", startTime);
		pMap.put("endTime", endTime);
		pMap.put("idDept", idDept);
		pMap.put("eventPer", eventPer);
		String STATUS = request.getParameter("STATUS");
		if (STATUS.equals("notAccept")) {
			if (eventSource != null) {
				statusList.add(Integer.valueOf(eventSource));
				pMap.put("statusList", statusList);
			} else {
				statusList.add(ConstantMenu.EVENT_TWENTY);// 未受理20
				statusList.add(ConstantMenu.EVENT_CHECK_REJECT);// 审核被驳回受理31
			}
			// 待受理数据
			pMap.put("statusList", statusList);
			String pages = request.getParameter("pages");
			if ("1".equals(pages)) {
				Utils.pages(request, pMap, map);
			} else {
				int totalRow = eventInfoDao.countByExample(pMap);
				Utils.paging(request, totalRow, pMap, map);
			}
			List<EventInfo> notAcceptList = eventInfoDao.selectAccept(pMap);
			map.put("notAcceptList", notAcceptList);
		} else if (STATUS.equals("notSure")) {
			pMap.put("eventStatus", eventStatus);
			String pages = request.getParameter("pages");
			if ("1".equals(pages)) {
				Utils.pages(request, pMap, map);
			}else{
			int totalRow = eventInfoDao.countNotSure(pMap);
			Utils.paging(request, totalRow, pMap, map);
			}
			List<Map<String, Object>> notSureList = eventInfoDao.selectNotSureList(pMap);
			map.put("notSureList", notSureList);
		}

		return map;
	}

	@Override
	public Map<String, Object> selectAcceptAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询申告信息,受理信息,审核信息
		String eventId = request.getParameter("eventId");
		if (!"".equals(eventId) && eventId != null) {
			int id = Integer.valueOf(eventId);
			EventInfo eventOne = eventInfoDao.selectByPrimaryKey(id);
			if (eventOne.getEventStatus().equals(String.valueOf(ConstantMenu.EVENT_CHECK_REJECT))) {
				Integer acceptId = eventOne.getIdUserAccept();
				Integer centerUerId = eventOne.getIdUserCheckCenter();
				String eventTs = eventOne.getEventTs();
				SysUserInfo userOne = sysUserInfoDao.selectByPrimaryKey(centerUerId);
				SysUserInfo acceptUserOne = sysUserInfoDao.selectByPrimaryKey(acceptId);
				map.put("userOne", userOne);// 审核人
				map.put("acceptUserOne", acceptUserOne);// 受理人
				map.put("eventTs", eventTs);
			}
			map.put("eventOne", eventOne);
		}
		// 查询服务类型
		Map<String,Object> pgMap = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> userAll = sysUserInfoDao.userByRoleType(pgMap);// 运维人员
		map.put("userAll", userAll);
		return map;
	}

	@Transactional
	@Override
	public String updateAccept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mass = "success";
		EventInfo eventInfo = new EventInfo();
		EventInfoDeal eventInfoDeal = new EventInfoDeal();
		EventLogInfo eventLogInfo = new EventLogInfo();
		String eventId = request.getParameter("eventId");
		int id = Integer.valueOf(eventId);
		String eventLevel = request.getParameter("eventLevel");
		int level = Integer.valueOf(eventLevel);
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		String serviceId = request.getParameter("serviceId");
		if (Utils.isNum(serviceId)) {
			int service = Integer.valueOf(serviceId);
			eventInfo.setEventService(service);
		}
		String sourceId = request.getParameter("sourceId");
		if (Utils.isNum(sourceId)) {
			int sourId = Integer.valueOf(sourceId);
			eventInfo.setEventSource(sourId);
		}
		String eventPriority = request.getParameter("eventPriority");
		if (Utils.isNum(eventPriority)) {
			int priority = Integer.valueOf(eventPriority);
			eventInfo.setEventPriority(priority);
		}
		String acceptDesc = request.getParameter("acceptDesc");
		if ("".equals(acceptDesc)) {
			acceptDesc = null;
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String eventTs = sf.format(date);
		eventInfo.setAcceptDesc(acceptDesc);
		eventInfo.setId(id);// 主表受理信息
		eventInfo.setIdUserAccept(userId);
		eventInfo.setEventLevel(level);
		eventInfo.setDateAccept(new Date());
		eventInfo.setEventTs(eventTs);
		eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_DEAL_PENDING));
		int update = eventInfoDao.updateAcceptByPrimaryKeySelective(eventInfo);
		if (update == ConstantMenu.EXECUTE_FAIL) {
			mass = "error";
		}
		String idUserDeal = request.getParameter("idUserDeal");// 处理表
		int dealUserId = 0;
		if (Utils.isNum(idUserDeal)) {
			dealUserId = Integer.valueOf(idUserDeal);
			eventInfoDeal.setIdUserDeal(dealUserId);
		}
		eventInfoDeal.setIdEvent(id);
		eventInfoDeal.setDealStatus(ConstantMenu.SURE_ONE);
		eventInfoDeal.setDateRespon(new Date());
		int insert = eventInfoDealDao.insert(eventInfoDeal);
		if (insert == ConstantMenu.EXECUTE_FAIL) {
			mass = "error";
		}

		EventInfo eventOne = eventInfoDao.selectByPrimaryKey(id);// log表参数
		eventLogInfo.setEventId(id);
		eventLogInfo.setEventCode(eventOne.getEventCode());
		eventLogInfo.setEventName(eventOne.getEventName());
		eventLogInfo.setIdOperUser(userId);
		eventLogInfo.setDateOper(new Date());
		eventLogInfo.setRemark(acceptDesc);
		eventLogInfo.setEventStatus(ConstantMenu.EVENT_DEAL_PENDING);
		eventLogInfoDao.insert(eventLogInfo);
		ServletContext context = request.getServletContext();
		if(level == 1 || level == 2 || level == 3){
			context.setAttribute("check", "有一条紧急事件需要审核!审核,"+idUserDeal);
		}else{
			context.setAttribute("accept", "您有一条新的处理信息!"+idUserDeal);
		}
		return mass;
	}

	@Override
	public Map<String, Object> checkEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String eventId = request.getParameter("eventId");
		int id = Integer.valueOf(eventId);
		pgMap.put("id_event", eventId);
		pgMap.put("status_id", ConstantMenu.EVENT_CHECKSURE_PENDING);
		List<Map<String, Object>> sureList = eventInfoDealDao.selectSureList(pgMap);
		Map<String, Object> eventOne = eventInfoDao.selectAcceptGiveCheck(id);
		int dealStatus = (int) sureList.get(sureList.size() - 1).get("dealStatus");
		int checkId = (int) sureList.get(sureList.size() - 1).get("checkId");
		int idEvent = (int) sureList.get(sureList.size() - 1).get("id_event");
		Object idRedeRein = sureList.get(sureList.size() - 1).get("idRedeRein");
		if (idRedeRein != null && !"".equals(idRedeRein)) {
			int RedeRein = (int) idRedeRein;
			map.put("idRedeRein", RedeRein);// 转派和增援 处理人id
		}
		List<List<SysFileInfo>> fileLIst = new ArrayList<List<SysFileInfo>>();// 文件信息
		List<Integer> dealIdList = new ArrayList<Integer>();
		List<Integer> fileIdList = new ArrayList<Integer>();
		for (Map<String, Object> sure : sureList) {
			int dealId = (int) sure.get("id");
			dealIdList.add(dealId);
			List<SysFileInfo> file = sysFileInfoDao.selectDealFile(dealId);
			List<Map<String, Object>> dealFilesByOne = new ArrayList<Map<String, Object>>();
			for (SysFileInfo sysFileInfo : file) {
				Integer fileId = sysFileInfo.getId();
				fileIdList.add(fileId);
				String path = sysFileInfo.getPath();
				String fileType = path.substring(path.lastIndexOf(".") + 1);
				Map<String, Object> fileObject = new HashMap<>();
				fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
				fileObject.put("fileId", fileId);
				dealFilesByOne.add(fileObject);
				sure.put("files", dealFilesByOne);
			}
			if (file.size() > 0 && file != null) {
				List<Map<String, Object>> dealFilesByOne1 = new ArrayList<Map<String, Object>>();
				for (SysFileInfo sysFileInfo : file) {
					String path = sysFileInfo.getPath();
					String fileType = path.substring(path.lastIndexOf(".") + 1);
					Map<String, Object> fileObject = new HashMap<>();
					fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
					fileObject.put("fileId", sysFileInfo.getId());
					dealFilesByOne1.add(fileObject);
					sure.put("files", dealFilesByOne1);
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
			sure.put("assetsName", assets);
		}
		map.put("dealIdList", dealIdList);
		map.put("SolveList", sureList);// 正常确认信息
		map.put("eventOne", eventOne);// 申告受理信息
		map.put("dealStatus", dealStatus);// 处理状态
		map.put("checkId", checkId);// 审核id
		map.put("idEvent", idEvent);// 事件id
		map.put("fileLIst", fileLIst);// 查询处理上传的文件
		map.put("fileIdList", fileIdList);
		return map;
	}

	@Transactional
	@Override
	public String sureSubmit(HttpServletRequest request) throws Exception {
		EventLogInfo eventLogInfo = new EventLogInfo();
		EventInfoCheck eventInfoCheck = new EventInfoCheck();
		EventInfoDeal eventInfoDeal = new EventInfoDeal();
		EventInfo eventInfo = new EventInfo();
		KbEventInfo kbEventInfo = new KbEventInfo();
		KbEventFileInfo kbEventFileInfo = new KbEventFileInfo();
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date();
		String code = sf.format(date);
		String kbCode = "KBET"+code;
		String mass = "success";
		String sureDesc = request.getParameter("sureDesc");
		if (sureDesc.equals("")) {
			sureDesc = null;
		}
		String eventCode = request.getParameter("eventCode");
		if (eventCode.equals("")) {
			eventCode = null;
		}
		String eventName = request.getParameter("eventName");
		if (eventName.equals("")) {
			eventName = null;
		}
		String result = request.getParameter("result");
		String eventDesc = request.getParameter("eventDesc");
		String eventLevel = request.getParameter("eventLevel");
		String dealId = request.getParameter("dealId");
		dealId = dealId.replaceAll("\\[", "");
		dealId = dealId.replaceAll("\\]", "");
		String[] man_deals = dealId.split(",");
		String resolvent = "";
		String eventCause = "";
		if(man_deals !=null && man_deals.length > 0){
		for(int i = 0;i < man_deals.length; i ++ ){
			String dealId1 = man_deals[i].trim();
			if(!"".equals(dealId1) && Utils.isNum(dealId1)){
			String acceptDesc = request.getParameter("acceptDesc_"+dealId1);
			String cause = request.getParameter("eventCause_"+dealId1);
			resolvent += acceptDesc+",";
			eventCause += cause+",";
			}
		  }
		}
		resolvent = resolvent.substring(0,resolvent.length() - 1);
		eventCause = eventCause.substring(0,eventCause.length() - 1);
		int resultId = Integer.valueOf(result);
		String eventId = request.getParameter("eventId");
		int eId = Integer.valueOf(eventId);
		eventInfo.setId(eId);
		/*
		 * 审核表
		 */
		String checkId = request.getParameter("checkId");
		HttpSession session = request.getSession();
		eventInfoCheck.setId(Integer.valueOf(checkId));
		int userId = (int) session.getAttribute("user_id");
		eventInfoCheck.setIdUserCheck(userId);// 当前登陆人为确认人(审核人)
		eventInfoCheck.setDateCheck(new Date());// 当前确认日期
		eventInfoCheck.setCheckDesc(sureDesc);// 确认信息
		/*
		 * log表 eventLogInfo
		 */
		eventLogInfo.setEventId(eId);
		eventLogInfo.setEventCode(eventCode);
		eventLogInfo.setEventName(eventName);
		eventLogInfo.setIdOperUser(userId);
		eventLogInfo.setDateOper(new Date());
		eventLogInfo.setRemark(sureDesc);
		if (resultId == ConstantMenu.SURE_ONE) {// 通过将主表的状态改为待评价
			eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_ANALYSIS_PENDING));// 主表状态
			eventInfoCheck.setCheckStatus(ConstantMenu.EVENT_CHECKSURE_FINISH);
			eventLogInfo.setEventStatus(ConstantMenu.EVENT_ANALYSIS_PENDING);
			kbEventInfo.setKbCode(kbCode);
			kbEventInfo.setEventCode(eventCode);
			kbEventInfo.setEventName(eventName);
			kbEventInfo.setEventDesc(eventDesc);
			kbEventInfo.setEventLevel(Integer.valueOf(eventLevel));
			kbEventInfo.setEventResolvent(resolvent);
			kbEventInfo.setEventCauses(eventCause);
		} else {
			eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_DEALSURE_REJECT));// 主表状态
			eventInfoCheck.setCheckStatus(ConstantMenu.EVENT_DEALSURE_REJECT);
			eventLogInfo.setEventStatus(ConstantMenu.EVENT_DEALSURE_REJECT);
			eventInfoDeal.setDealStatus(ConstantMenu.DEAL_ONE);
			Map<String,Object> pgMap = new HashMap<String,Object>();
			pgMap.put("id_event", eId);
 			List<EventInfoDeal> deals = eventInfoDealDao.selectDealList(pgMap);
			for(EventInfoDeal deal:deals){
				eventInfoDeal.setId(deal.getId());
				eventInfoDealDao.update(eventInfoDeal);// 更改处理表中的处理状态为1
			}
		}
		eventInfoCheckDao.updateByPrimaryKeySelective(eventInfoCheck);
		eventLogInfoDao.insert(eventLogInfo);
		eventInfoDao.updateEventStatus(eventInfo);
		kbEventInfoDao.insert(kbEventInfo);
		Integer id = kbEventInfo.getId();
		String fileIdList = request.getParameter("fileIdList");
		fileIdList = fileIdList.replaceAll("\\[", "");
		fileIdList = fileIdList.replaceAll("\\]", "");
		String[] man_file = fileIdList.split(",");
		if(man_file !=null && man_file.length > 0){
		for(int i = 0;i < man_file.length; i ++ ){
			String file1 = man_file[i].trim();
			if(!"".equals(file1) && Utils.isNum(file1)){
			String fileId = request.getParameter("fileId_"+file1);
			kbEventFileInfo.setFileId(Integer.valueOf(fileId));
			kbEventFileInfo.setKbId(id);
			kbEventFileInfoDao.insert(kbEventFileInfo);
			}
		}
		}
		return mass;
	}

	@Override
	public Map<String, Object> applyAndAccept(HttpServletRequest request) throws Exception {
		// 查询生申告基本信息
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysDeptInfo> selectAll = sysDeptInfoDao.selectPrimAll();
		HttpSession session = request.getSession();
		int dId = (int) session.getAttribute("dep_id");
		int uId = (int) session.getAttribute("user_id");
		List<SysUserInfo> selectUser = sysUserInfoDao.selectDeptUser(dId);
		SysUserInfo userOne = sysUserInfoDao.selectByPrimaryKey(uId);
		map.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> userAll = sysUserInfoDao.userByRoleType(map);// 运维人员
		map.put("selectAll", selectAll);
		map.put("selectUser", selectUser);
		map.put("userAll", userAll);
		map.put("userOne", userOne);
		return map;
	}

	@Transactional
	@Override
	public String addApplyAndAccept(HttpServletRequest request) throws Exception {
		EventInfo eventInfo = new EventInfo();
		String mass = "success";
		EventLogInfo eventLogInfo = new EventLogInfo();
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		String idDept = request.getParameter("idDept");
		String eventUser = request.getParameter("eventUser");
		String eventContact = request.getParameter("callApply");
		String eventName = request.getParameter("eventName");
		String eventDesc = request.getParameter("eventDesc");
		/**设置code*/
		String userCode = sysUserInfoDao.selectByPrimaryKey(Integer.valueOf(eventUser)).getCode();
		SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String appCode = sf.format(date);
		String code = appCode+userCode;
		eventInfo.setEventCode(code);
		eventInfo.setEventDesc(eventDesc);
		eventInfo.setEventName(eventName);
		eventInfo.setDateCreate(new Date());
		eventInfo.setIdUserCreate(userId);
		if (Utils.isNum(idDept)) {
			eventInfo.setIdDept(Integer.valueOf(idDept));
		}
		if (Utils.isNum(eventUser)) {
			eventInfo.setIdUserAplly(Integer.valueOf(eventUser));
			eventLogInfo.setIdOperUser(Integer.valueOf(eventUser));
		}
		if (eventContact.equals("")) {
			eventContact = null;
		}
		eventInfo.setEventContact(eventContact);
		eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_TWENTY));
		int in = eventInfoDao.insert(eventInfo);
		if (in != 1) {
			mass = "error";
		}
		Integer id = eventInfo.getId();
		eventLogInfo.setEventId(id);
		eventLogInfo.setEventCode(code);
		eventLogInfo.setEventName(eventName);
		eventLogInfo.setEventStatus(ConstantMenu.EVENT_TWENTY);
		eventLogInfo.setDateOper(new Date());
		eventLogInfo.setRemark(eventDesc);
		eventLogInfoDao.insert(eventLogInfo);
		eventInfo.setIdUserAccept(userId);
		eventInfo.setDateAccept(new Date());// 受理时间
		String eventLevel = request.getParameter("eventLevel");// 事件等级
		if (Utils.isNum(eventLevel)) {
			int level = Integer.valueOf(eventLevel);
			String idUserDeal = request.getParameter("idUserDeal");// 处理表
			eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_DEAL_PENDING));
			eventLogInfo.setEventStatus(ConstantMenu.EVENT_DEAL_PENDING);// log状态
			EventInfoDeal eventInfoDeal = new EventInfoDeal();// 待处理数据
			eventInfoDeal.setIdUserDeal(Integer.valueOf(idUserDeal));
			eventInfoDeal.setIdEvent(id);
			eventInfoDeal.setDealStatus(ConstantMenu.DEAL_ONE);
			eventInfoDeal.setDateRespon(new Date());
			eventInfoDealDao.insert(eventInfoDeal);
			eventInfo.setEventLevel(level);
		}
		String serviceId = request.getParameter("serviceId");
		if (Utils.isNum(serviceId)) {
			int service = Integer.valueOf(serviceId);
			eventInfo.setEventService(service);
		}
		String eventPriority = request.getParameter("eventPriority");// 优先级
		if (Utils.isNum(eventPriority)) {
			int priority = Integer.valueOf(eventPriority);
			eventInfo.setEventPriority(priority);
		}
		String sourceId = request.getParameter("sourceId");// 事件源
		if (Utils.isNum(sourceId)) {
			int sId = Integer.valueOf(sourceId);
			eventInfo.setEventSource(sId);
		}
		
		String acceptDesc = request.getParameter("acceptDesc");// 受理描述
		if ("".equals(acceptDesc)) {
			acceptDesc = null;
		}
		SimpleDateFormat sfs = new SimpleDateFormat("yyyyMMddHHmmss");
		Date dates = new Date();
		String eventTs = sfs.format(dates);
		eventInfo.setAcceptDesc(acceptDesc);
		eventInfo.setEventTs(eventTs);
		int update = eventInfoDao.updateAcceptByPrimaryKeySelective(eventInfo);
		if (update == ConstantMenu.EXECUTE_FAIL || in == ConstantMenu.EXECUTE_FAIL) {
			throw new BusinessException("", "添加失败！");
		}
		// log表参数
		eventLogInfo.setEventId(id);
		eventLogInfo.setEventCode(appCode);
		eventLogInfo.setEventName(eventName);
		eventLogInfo.setIdOperUser(userId);
		eventLogInfo.setDateOper(new Date());
		eventLogInfo.setRemark(acceptDesc);
		eventLogInfoDao.insert(eventLogInfo);
		return mass;
	}

	@Override
	public List<Map<String, Object>> selectServiceDeptCount(Map<String, Object> map) {
		return eventInfoDao.selectServiceDeptCount(map);
	}

	@Override
	public Map<String, Object> exportDeptCountMethod(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		SimpleDateFormat sf4 = new SimpleDateFormat("yyyyMMddHHmmss");
		//部门名称以及部门事件数量
		List<Map<String, Object>> serviceDeptCount = eventInfoDao.selectServiceDeptCount(paramMap);
		//部门下的事件
		List<Map<String, Object>> exportDeptInform = eventInfoDao.exportDeptInform(paramMap);
		// 第一步，创建一个webbook，对应一个Excel文件
				HSSFWorkbook wb = new HSSFWorkbook();
				// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
				HSSFSheet sheet = wb.createSheet("部门事件统计");
				sheet.setDefaultColumnWidth(20);
				sheet.autoSizeColumn(0);
				
				sheet.setColumnWidth(0, 5120);//部门名称
				sheet.setColumnWidth(1, 4000);//申告人姓名
				sheet.setColumnWidth(2, 5120);//事件源
				sheet.setColumnWidth(3, 5120);//事件源
				sheet.setColumnWidth(4, 5120);//事件名称
				sheet.setColumnWidth(5, 5120);//创建时间
				sheet.setColumnWidth(6, 5120);//处理人姓名
				sheet.setColumnWidth(7, 5120);//处理时间
				
				// 第三步，在sheet中添加表头第1行,注意老版本poi对Excel的行数列数有限制short
				HSSFCellStyle style = wb.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
				style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
				style.setFillForegroundColor(HSSFColor.YELLOW.index);
				style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
				style.setFillBackgroundColor(HSSFColor.YELLOW.index); 
				style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style.setBorderTop(HSSFCellStyle.BORDER_THIN); 
				HSSFFont font = wb.createFont();
				font.setColor(HSSFColor.RED.index);
				font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
				font.setFontName("宋体");
		        font.setFontHeightInPoints((short) 11);// 设置字体大小
				style.setFont(font);
				style.setWrapText(true); 
				

				//样式二
				HSSFCellStyle style2 = wb.createCellStyle();
				style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
				style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
				HSSFFont font2 = wb.createFont();
				font2.setFontName("宋体");
		        font2.setFontHeightInPoints((short) 11);// 设置字体大小
				style2.setFont(font2);
				style2.setWrapText(true); 
				
				// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
				//记录要在第几行添加部门名称和事件数信息
				int recordDept = -1;//行数
				for(Map<String, Object> deptCount : serviceDeptCount){//外层部门名称和事件数量
					
					recordDept++;
					HSSFRow projRow = sheet.createRow((int) recordDept);
					HSSFCell projCell = projRow.createCell(0);
					sheet.addMergedRegion(new CellRangeAddress(recordDept, recordDept, 0, 6)); //合并单元格
					String deptStr = "";
					deptStr += "部门名称："+(String)deptCount.get("deptName")+"  "+"事件数："+(Long) deptCount.get("coun");
					projCell.setCellValue(deptStr);
					projCell.setCellStyle(style2);
					//每个部门的表头
					recordDept++;
					HSSFRow row = sheet.createRow((int) recordDept);
					HSSFCell cell = row.createCell(1);
					cell = row.createCell(0);
					cell.setCellValue("部门名称");
					cell.setCellStyle(style);
					cell = row.createCell(1);
					cell.setCellValue("申告人姓名");
					cell.setCellStyle(style);
					cell = row.createCell(2);
					cell.setCellValue("事件源");
					cell.setCellStyle(style);
					cell = row.createCell(3);
					cell.setCellValue("服务类型");
					cell.setCellStyle(style);
					cell = row.createCell(4);
					cell.setCellValue("事件名称");
					cell.setCellStyle(style);
					cell = row.createCell(5);
					cell.setCellValue("创建时间");
					cell.setCellStyle(style);
					cell = row.createCell(6);
					cell.setCellValue("处理人姓名");
					cell.setCellStyle(style);
					cell = row.createCell(7);
					cell.setCellValue("处理时间");
					cell.setCellStyle(style);
					int idDept = (int) deptCount.get("idDept");//判断两个list中部门id是否相等，相等把它们放在一起
					for (int i = 0; i < exportDeptInform.size(); i++) {
						int deptId = (int) exportDeptInform.get(i).get("idDept");
						if(idDept == deptId){
							recordDept++;
							HSSFRow deptRow = sheet.createRow(recordDept);
							Map<String, Object> deptInform = exportDeptInform.get(i);
							// 第四步，创建单元格，并设置值
							HSSFCell cell0 = deptRow.createCell(0);
							HSSFCell cell1 = deptRow.createCell(1);
							HSSFCell cell2 = deptRow.createCell(2);
							HSSFCell cell3 = deptRow.createCell(3);
							HSSFCell cell4 = deptRow.createCell(4);
							HSSFCell cell5 = deptRow.createCell(5);
							HSSFCell cell6 = deptRow.createCell(6);
							HSSFCell cell7 = deptRow.createCell(7);
							HSSFCellStyle styleNormal = style2;
							cell0.setCellStyle(styleNormal);
							cell1.setCellStyle(styleNormal);
							cell2.setCellStyle(styleNormal);
							cell3.setCellStyle(styleNormal);
							cell4.setCellStyle(styleNormal);
							cell5.setCellStyle(styleNormal);
							cell6.setCellStyle(styleNormal);
							cell7.setCellStyle(styleNormal);
							cell0.setCellValue((String)deptInform.get("deptName"));
							cell1.setCellValue((String)deptInform.get("applyUserName"));
							cell2.setCellValue((String)deptInform.get("sourceName"));
							
							cell3.setCellValue((String)deptInform.get("serviceName"));
							cell4.setCellValue((String)deptInform.get("eventName"));
							cell5.setCellValue(sf2.format((Date)deptInform.get("dateCreate")));
							cell6.setCellValue((String)deptInform.get("dealUserName"));
							Object dateDeal = deptInform.get("dateDeal");
							if(dateDeal != null && dateDeal != ""){
								cell7.setCellValue(sf2.format((Date)dateDeal));
							}else{
								cell7.setCellValue("");
							}
						}
						
					}
					
				}
				
				
		// 第六步，将文件存到指定位置

		String fileName = "部门事件统计" + sf4.format(new Date());
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		String str = Utils.getLocalName(request);
		String filePath = str + "/deptCountExcel/" + fileName + ".xls";// Excel模板所在的路径。
		File file = new File(str + "/deptCountExcel/");
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		FileOutputStream fout = new FileOutputStream(filePath);
		wb.write(fout);
		fout.close();
		map.put("filePath", filePath);
		map.put("fileName", fileName);
		return map;
		
		
	}
}
