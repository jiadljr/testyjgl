package com.qkby.event.business;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.dao.EventInfoDao;
import com.qkby.event.dao.EventInfoDealDao;
import com.qkby.event.dao.EventLogInfoDao;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventInfoDeal;
import com.qkby.event.entity.EventLogInfo;
import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.business.SysArrangeBusiness;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysDutyInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysDutyInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Utils;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月10日下午2:48:53 
 *     
 * @version </pre>
 */
@Service
public class EventInfoApplyBusinessImpl implements EventInfoApplyBusiness{
	@Resource
    EventInfoDao eventInfoDao;
	@Resource
	SysDeptInfoDao sysDeptInfoDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	EventLogInfoDao eventLogInfoDao;
	@Resource
	EventInfoDealDao eventInfoDealDao;
	@Resource
	SysDutyInfoDao sysDutyInfoDao;
	@Resource
	SysArrangeBusiness sysArrangeBusiness;
	
	@Override
	public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		List<Integer> statusList = new ArrayList<Integer>();
		Map<String,Object> pageMap = new HashMap<String,Object>();
		String startTime = request.getParameter("startTime");
		if ("".equals(startTime)) {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if ("".equals(endTime)) {
			endTime = null;
		}
		String tel = request.getParameter("tel");
		if ("".equals(tel)) {
			tel = null;
		}
		String idDept = request.getParameter("idDept");
		if ("".equals(idDept)) {
			idDept = null;
		}
		String eventStatus = request.getParameter("eventStatus");
		if ("".equals(eventStatus)) {
			eventStatus = null;
		}
		if(eventStatus != null){
			statusList.add(Integer.valueOf(eventStatus));
			map.put("statusList", statusList);
		}
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		map.put("startTime", startTime);
		map.put("userId", userId);
		map.put("tel", tel);
		map.put("endTime", endTime);
		map.put("idDept", idDept);
		map.put("eventStatus", ConstantMenu.EVENT_ANALYSIS_FINISH);
		String pages = request.getParameter("pages");
		if(!"".equals(pages)){
         Utils.pages(request, map, pageMap);
		}else{
			int totalRow = eventInfoDao.countByExample(map);
			Utils.paging(request, totalRow, map, pageMap);
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<EventInfo> selectAll = eventInfoDao.selectByExample(map);
		for (EventInfo sysAssetsInfo : selectAll) {
			Date createDate = sysAssetsInfo.getDateCreate();
			String format = sf.format(createDate);
			sysAssetsInfo.setExtend3(format);			
		}
		pageMap.put("selectAll", selectAll);
		return pageMap;
	}
    
	@Override
	public int deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		map.put("id", id);
		int de = eventInfoDao.eventRepeal(map);
		if(de == 0){
			throw new BusinessException("", "该条事件不存在");
		}
		return de;
	}

	@Override
	public Map<String, Object> serviceAdd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysDeptInfo> selectAll = sysDeptInfoDao.selectPrimAll();
		HttpSession session = request.getSession();
		int dId = (int) session.getAttribute("dep_id");
		int uId = (int)session.getAttribute("user_id");
		List<SysUserInfo> selectUser = sysUserInfoDao.selectDeptUser(dId);
		SysUserInfo userOne = sysUserInfoDao.selectByPrimaryKey(uId);
		map.put("selectAll", selectAll);
		map.put("userOne", userOne);
		map.put("selectUser", selectUser);
		return map;
	}

	@Override
	public  Map<String,Object> selectDeptUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		List<SysUserInfo> selectUser = sysUserInfoDao.selectDeptUser(Integer.valueOf(id));
		SysDeptInfo selectDept = sysDeptInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		String tel = selectDept.getTel();
		map.put("selectUser", selectUser);
		map.put("tel", tel);
		return map;
	}

	@Override
	public int addEventInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EventInfo eventInfo = new EventInfo();
		EventLogInfo eventLogInfo = new EventLogInfo();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		String idDept = request.getParameter("idDept");
		String eventUser = request.getParameter("eventUser");
		String eventContact = request.getParameter("callApply");
		String eventName = request.getParameter("eventName");
		String eventDesc = request.getParameter("eventDesc");
		if(idDept == null || eventUser == null || eventContact == null || eventName == null || eventDesc == null){
			throw new BusinessException("","事件信息添加失败!填写信息不全");
		}
		/**设置code*/
		String userCode = sysUserInfoDao.selectByPrimaryKey(Integer.valueOf(eventUser)).getCode();
		SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String code = sf.format(date);
		String eCode = code+userCode;
		eventInfo.setEventCode(eCode);
		eventInfo.setEventDesc(eventDesc);
		eventInfo.setEventName(eventName);
		eventInfo.setDateCreate(new Date());
		eventInfo.setIdUserCreate(userId);
		eventInfo.setIdDept(Integer.valueOf(idDept));
		eventInfo.setIdUserAplly(Integer.valueOf(eventUser));
		eventInfo.setEventContact(eventContact);
		eventInfo.setEventTs(code);
		eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_TWENTY));
		int in = eventInfoDao.insert(eventInfo);
		Integer id = eventInfo.getId();
		eventLogInfo.setEventId(id);
		eventLogInfo.setEventCode(eCode);
		eventLogInfo.setEventName(eventName);
		eventLogInfo.setEventStatus(ConstantMenu.EVENT_TWENTY);
		eventLogInfo.setIdOperUser(Integer.valueOf(eventUser));
		eventLogInfo.setDateOper(new Date());
		eventLogInfo.setRemark(eventDesc);
		session.removeAttribute("message");
		eventLogInfoDao.insert(eventLogInfo);
		ServletContext context = request.getServletContext();
		context.setAttribute("apply", "您有一条新的受理信息!");
		return in;
	}

	@Override
	public Map<String,Object> updateEventInfo(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		EventInfo selectById = eventInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		List<SysDeptInfo> selectAll = sysDeptInfoDao.selectPrimAll();
		Integer idDept = selectById.getIdDept();
		SysDeptInfo septTel = sysDeptInfoDao.selectByPrimaryKey(idDept);
		List<SysUserInfo> selectUser = sysUserInfoDao.selectDeptUser(idDept);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("selectAll", selectAll);
		map.put("selectById", selectById);
		map.put("selectUser", selectUser);
		map.put("septTel", septTel);
		return map;
	}

	@Override
	public int updateEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		EventInfo eventInfo = new EventInfo();
		String eventDept = request.getParameter("idDept");
		String id = request.getParameter("id");
		String eventUser = request.getParameter("userApply");
		String eventWay = request.getParameter("eventWay");
		String eventContact = request.getParameter("eventContact");
		String eventName = request.getParameter("eventName");
		String eventDesc = request.getParameter("eventDesc");
		if(eventDept == null || id == null || eventUser == null || eventWay == null || eventContact == null || eventName == null || eventDesc == null){
			throw new BusinessException("","修改事件信息失败!填写不完整");
		}
		eventInfo.setId(Integer.valueOf(id));
		eventInfo.setEventDesc(eventDesc);
		eventInfo.setEventName(eventName);
		eventInfo.setIdDept(Integer.valueOf(eventDept));
		eventInfo.setIdUserAplly(Integer.valueOf(eventUser));
		eventInfo.setEventWay(Integer.valueOf(eventWay));
		eventInfo.setEventContact(eventContact);
		int in = eventInfoDao.updateApply(eventInfo);
		return in;
	}
    /**
     * 查询全部部门
     * @throws Exception 
     */
	@Override
	public List<SysDeptInfo> selectDeptAll() throws Exception {
		List<SysDeptInfo> selectAll = sysDeptInfoDao.selectPrimAll();
		return selectAll;
	}

	@Override
	public Map<String, Object> examine(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Map<String,Object> map = new  HashMap<String,Object>();
		String id = request.getParameter("id");
		EventInfo selectEventInfoAll = eventInfoDao.selectExamine(Integer.valueOf(id));
			if(selectEventInfoAll == null){
				throw new BusinessException("", "未找到该事件的详细信息");
			}
		int idEvent = selectEventInfoAll.getId();
		int idDept = selectEventInfoAll.getIdDept();
		int idUserAplly = selectEventInfoAll.getIdUserAplly();
		SysUserInfo userAccept = sysUserInfoDao.userEventId(idUserAplly);
		SysDeptInfo deptName = sysDeptInfoDao.deptEvent(idDept);
		Map<String,Object> pgMap = new HashMap<String,Object>();
		pgMap.put("id_event", idEvent);
		List<EventInfoDeal> dealUsers = eventInfoDealDao.selectDealList(pgMap);
		List<String> dealUsersList = new ArrayList<String>();
		String dUser = "";
		Set dealSet = new  HashSet(); 
		for (EventInfoDeal dealUser : dealUsers) {
			if(dealSet.add(dealUser.getExtend3())){
				dealUsersList.add(dealUser.getExtend2());
			}
		}
		for (int i = 0; i < dealUsersList.size(); i++) {
			dUser+=dealUsersList.get(i)+",";
		}
		String[] dUserAll = dUser.split(",");
		List<EventLogInfo> logStatus = eventLogInfoDao.selectLogStatus(idEvent);
		/*
		List<Map<String, Object>> selectApply = eventLogInfoDao.selectApply(idEvent);
		List<Map<String, Object>> selectAccept = eventLogInfoDao.selectAccept(idEvent);
		List<Map<String, Object>> selectDeal = eventLogInfoDao.selectDeal(idEvent);
		List<Map<String, Object>> selectSure = eventLogInfoDao.selectSure(idEvent);
		List<Map<String, Object>> selectAsses = eventLogInfoDao.selectAsses(idEvent);
		List<Map<String, Object>> selectAnewAsses = eventLogInfoDao.selectAnewAsses(idEvent);
		map.put("selectAnewAsses", selectAnewAsses);
		map.put("selectAsses", selectAsses);
		map.put("selectDeal", selectDeal);
		map.put("selectSure", selectSure);
		map.put("selectAccept", selectAccept);
		map.put("selectApply", selectApply);*/
		map.put("logStatus", logStatus);
		map.put("dUserAll", dUserAll);
		map.put("selectEventInfoAll", selectEventInfoAll);
		map.put("userAccept", userAccept);
		map.put("deptName", deptName);
		return map;
	}

	@Override
	public Map<String, Object> serviceRepe(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		EventInfo selectById = eventInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		List<SysDeptInfo> selectAll = sysDeptInfoDao.selectPrimAll();
		Integer idDept = selectById.getIdDept();
		SysDeptInfo septTel = sysDeptInfoDao.selectByPrimaryKey(idDept);
		List<SysUserInfo> selectUser = sysUserInfoDao.selectDeptUser(idDept);
		map.put("selectAll", selectAll);
		map.put("selectById", selectById);
		map.put("selectUser", selectUser);
		map.put("septTel", septTel);
		return map;
	}

	@Override
	public Map<String,Object> dateCreate() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String firstday = "";
		String lastday = "";
		c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		firstday = format.format(c.getTime());
		// 获取前月的最后一天
		c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 0);
		lastday = format.format(c.getTime());
		Map<String, Object> datemap = new HashMap<String, Object>();
		datemap.put("fromTime", firstday);
		datemap.put("toTime", lastday);
		//本月申告的总条数
		int dateCreate = eventInfoDao.dateCreate(datemap);
		//申告一级事件的个数
		int selectLevel = eventInfoDao.selectLevel(datemap);
		map.put("dateCreate", dateCreate);
		map.put("selectLevel", selectLevel);
		return map;
	}

	@Override
	public SysUserInfo userById(int id) throws Exception {
		SysUserInfo userEventId = sysUserInfoDao.userEventId(id);
		return userEventId;
	}

	@Override
	public String checkDutyForApply(Integer dutyYes,Integer userId) throws Exception {
		String dutyNo = "yes";
		Boolean checkDuty = this.checkDutyByUser(dutyYes,userId);
		if (!checkDuty) {
			dutyNo = "no";
		}
		return dutyNo;
	}
	
	/**
	 * 判断是否值班人员
	 * */
	public  Boolean checkDutyByUser(Integer dutyYes,Integer userId) throws Exception{
		if (userId.equals(dutyYes)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public EventInfo selectEventTs(HttpServletRequest request) throws NumberFormatException, Exception {
		String id = request.getParameter("id");
		EventInfo selectById = eventInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		return selectById;
	}
}
