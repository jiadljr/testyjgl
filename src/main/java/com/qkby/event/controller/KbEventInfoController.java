package com.qkby.event.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.event.business.KbEventFileInfoBusiness;
import com.qkby.event.business.KbEventInfoBusiness;
import com.qkby.event.entity.KbEventInfo;
import com.qkby.sysmanage.business.SysAssetsTypeBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.sysmanage.entity.SysAssetsType;
import com.qkby.utils.Token;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年11月27日下午1:39:49 
 *     
 * @version </pre>
 */
@Controller
public class KbEventInfoController {
	@Resource
    private KbEventInfoBusiness kbEventInfoBusiness;
	@Resource
	private KbEventFileInfoBusiness kbEventFileInfoBusiness;
	@Resource
	SysAssetsTypeBusiness sysAssetsTypeBusiness;
	@Resource
	SysUserInfoBusiness sysUserInfoBusiness;
	/**
	 * <pre>kbEvent (跳转至知识库页面)
	 * Created by 家栋梁 on 2017年11月30日下午6:39:36  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/kbEvent.do")
	public ModelAndView kbEvent(HttpServletRequest request) throws Exception{
		Map<String, Object> maps = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		List<Map<String,Object>> selectSystem = sysUserInfoBusiness.selectSystem();
		String id = "";
		for (Map<String, Object> map : selectSystem) {
			int ids = (int)map.get("id");
			id += ids+",";
		}
		String pages = request.getParameter("pages");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		maps.put("pages", pages);
		maps.put("pageNumber", pageNumber);
		maps.put("pageSize", pageSize);
		maps.put("totalPage", totalPage);
		maps.put("totalRow", totalRow);
		if(id.contains(String.valueOf(userId))){
			return new ModelAndView("kbEvent/kb_event_manager_list",maps);
		}else{
			return new ModelAndView("kbEvent/kb_event_user");
		}
	}
	/**
	 * <pre>selectKbEventAll (查询全部的知识库信息)
	 * Created by 家栋梁 on 2017年11月27日下午1:43:04  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectKbEventAll.do")
	@ResponseBody
	public Map<String,Object> selectKbEventAll(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> pgMap = new HashMap<String,Object>();
		String kbEvent = request.getParameter("kbEvent");
		if("".equals(kbEvent)){
			kbEvent = null;
		}
		map.put("eventCode", kbEvent);
		String pages = request.getParameter("pages");
		if(!"".equals(pages)){
			Utils.pages(request, map, pgMap);
		}else{
		int selectKbEventCount = kbEventInfoBusiness.selectKbEventCount(map);
		Utils.paging(request, selectKbEventCount, map, pgMap);
		}
		List<Map<String,Object>> selectKbEventAll = kbEventInfoBusiness.selectKbEventAll(map);
		pgMap.put("selectKbEventAll", selectKbEventAll);
		return pgMap;
	}
	/**
	 * <pre>selectKbEvent (跳转知识库页面)
	 * Created by 家栋梁 on 2017年11月27日下午1:55:25  
	 *
	 * @return</pre>
	 */
	@RequestMapping("/selectKbEvent.do")
	public String selectKbEvent(){
		return "";
	}
	/**
	 * <pre>selectKbEventCondition (根据关键字搜索)
	 * Created by 家栋梁 on 2017年11月28日下午4:41:15  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectKbEventCondition.do")
	@ResponseBody
	public List<Map<String,Object>> selectKbEventCondition(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String kbEventCondition = request.getParameter("kbEvent");
		if("".equals(kbEventCondition)){
			kbEventCondition = null;
		}
		String sourceId = request.getParameter("sourceId");
		if("".equals(sourceId)){
			sourceId = null;
		}
		map.put("kbEventCondition", kbEventCondition);
		map.put("sourceId", sourceId);
		List<Map<String, Object>> selectKbEventCondition = kbEventInfoBusiness.selectKbEventCondition(map);
		return selectKbEventCondition;
	}
	/**
	 * <pre>selectKbEventById (根据ID进行检索)
	 * Created by 家栋梁 on 2017年11月28日下午6:39:11  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/selectKbEventById.do")
	@ResponseBody
	public Map<String,Object> selectKbEventById(HttpServletRequest request) throws NumberFormatException, Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		KbEventInfo selectKbEventById = kbEventInfoBusiness.selectKbEventById(Integer.valueOf(id));
		List<Map<String, Object>> selectKbEventFileById = kbEventFileInfoBusiness.selectKbEventFileById(Integer.valueOf(id));
		map.put("selectKbEventById", selectKbEventById);
		map.put("selectKbEventFileById", selectKbEventFileById);
		return map;
	}
	/**
	 * <pre>insertKbEvent (跳转至新增页面)
	 * Created by 家栋梁 on 2017年12月1日上午11:28:42  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/insertKbEvent.do")
	@Token(remove=false, save = true)
	public ModelAndView insertKbEvent(HttpServletRequest request) throws Exception{
		Map<String, Object> insertKbEvent = kbEventInfoBusiness.insertKbEvent();
		String pages = request.getParameter("pages");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		insertKbEvent.put("pages", pages);
		insertKbEvent.put("pageNumber", pageNumber);
		insertKbEvent.put("pageSize", pageSize);
		insertKbEvent.put("totalPage", totalPage);
		insertKbEvent.put("totalRow", totalRow);
		return new ModelAndView("kbEvent/kb_event_add",insertKbEvent);
	}
	/**
	 * <pre>insertKbEventInfo (新增知识库)
	 * Created by 家栋梁 on 2017年12月1日下午1:59:36  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/insertKbEventInfo.do")
	@ResponseBody
	@Token(remove=true, save = false)
	public int insertKbEventInfo(HttpServletRequest request) throws Exception{
		KbEventInfo kbEventInfo = new KbEventInfo();
		String eventCode = request.getParameter("eventCode");
		String eventName = request.getParameter("eventNme");
		String assetType = request.getParameter("assetType");
		String eventLevel = request.getParameter("eventLevel");
		String eventCauses = request.getParameter("eventCauses");
		String eventDesc = request.getParameter("eventDesc");
		String eventResolvent = request.getParameter("eventResolvent");
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String format = sf.format(date);
		int i=(int)(Math.random()*900)+100;
		String kbCode = "KBET"+format+i;
		kbEventInfo.setKbCode(kbCode);
		kbEventInfo.setAssetType(Integer.valueOf(assetType));
		kbEventInfo.setEventCauses(eventCauses);
		kbEventInfo.setEventCode(eventCode);
		kbEventInfo.setEventDesc(eventDesc);
		kbEventInfo.setEventLevel(Integer.valueOf(eventLevel));
		kbEventInfo.setEventName(eventName);
		kbEventInfo.setEventResolvent(eventResolvent);
		int insert = kbEventInfoBusiness.insert(kbEventInfo);
		return insert;
	}
	/**
	 * <pre>deleteKbEvent (删除)
	 * Created by 家栋梁 on 2017年12月1日下午3:18:41  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/deleteKbEvent.do")
	@ResponseBody
	public int deleteKbEvent(HttpServletRequest request) throws NumberFormatException, Exception{
		String id = request.getParameter("id");
		int deleteKbEvent = kbEventInfoBusiness.deleteKbEvent(Integer.valueOf(id));
		return deleteKbEvent;
	}
	/**
	 * <pre>updateKbEvent (跳转至修改页面)
	 * Created by 家栋梁 on 2017年12月1日下午3:34:37  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/updateKbEventById.do")
	@ResponseBody
	public ModelAndView updateKbEventById(HttpServletRequest request) throws NumberFormatException, Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		String pages = request.getParameter("pages");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		KbEventInfo selectKbEventById = kbEventInfoBusiness.selectKbEventById(Integer.valueOf(id));
		List<SysAssetsType> AssList = sysAssetsTypeBusiness.selectAll();
		for(SysAssetsType assType:AssList){
			if (assType.getId() == selectKbEventById.getAssetType()) {
				map.put("typeId", assType.getId());
				map.put("typeName", assType.getName());
			}
		}
		map.put("selectKbEventById", selectKbEventById);
		map.put("pages", pages);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		return new ModelAndView("kbEvent/kb_event_mode",map);
	}
	@RequestMapping("/updateKbEventInfo.do")
	@ResponseBody
	public int updateKbEventInfo(HttpServletRequest request) throws Exception{
		KbEventInfo kbEventInfo = new KbEventInfo();
		String id = request.getParameter("kbId");
		String eventCode = request.getParameter("eventCode");
		String eventName = request.getParameter("eventName");
		String assetType = request.getParameter("assetType");
		String eventLevel = request.getParameter("eventLevel");
		String eventCauses = request.getParameter("eventCauses");
		String eventDesc = request.getParameter("eventDesc");
		String eventResolvent = request.getParameter("eventResolvent");
		kbEventInfo.setId(Integer.valueOf(id));
		kbEventInfo.setAssetType(Integer.valueOf(assetType));
		kbEventInfo.setEventCauses(eventCauses);
		kbEventInfo.setEventCode(eventCode);
		kbEventInfo.setEventDesc(eventDesc);
		kbEventInfo.setEventLevel(Integer.valueOf(eventLevel));
		kbEventInfo.setEventName(eventName);
		kbEventInfo.setEventResolvent(eventResolvent);
		int update = kbEventInfoBusiness.updateByPrimaryKeySelective(kbEventInfo);
		return update;
	}
	/**
	 * <pre>selectKbEventInfoById (根据ID查询)
	 * Created by 家栋梁 on 2017年12月4日下午1:45:28  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/selectKbEventInfoById.do")
	@ResponseBody
	public KbEventInfo selectKbEventInfoById(HttpServletRequest request) throws NumberFormatException, Exception{
		String id = request.getParameter("id");
		KbEventInfo selectKbEventById = kbEventInfoBusiness.selectKbEventById(Integer.valueOf(id));
	    return selectKbEventById;
	}
}
