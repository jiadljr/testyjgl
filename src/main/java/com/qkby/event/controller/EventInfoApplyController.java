package com.qkby.event.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.event.business.EventInfoApplyBusiness;
import com.qkby.event.entity.EventInfo;
import com.qkby.sysmanage.business.SysDepInfoBusiness;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.ChineseToInitials;
import com.qkby.utils.Token;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月10日下午2:49:18 
 *     
 * @version </pre>
 */
@Controller
public class EventInfoApplyController {
	@Resource
   EventInfoApplyBusiness eventInfoBusiness;
	@Resource
   SysDepInfoBusiness sysDeptInfoBusiness;
	/**
	 * <pre>eventInfoList (跳转申告页面) 
	 * Created by 家栋梁 on 2017年10月10日下午2:58:07  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/eventInfoList.do")
	public ModelAndView eventInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		ServletContext servletContext = request.getSession().getServletContext();
		Object dutyY = servletContext.getAttribute("dutyYes");
		int dutyYes = 0;
		if(dutyY != null && dutyY != ""){
			dutyYes = Integer.parseInt(dutyY.toString());
		}
		int userId = (int)request.getSession().getAttribute("user_id");
		String dutyNo = eventInfoBusiness.checkDutyForApply(dutyYes,userId);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		map.put("dutyNo", dutyNo);
		return new ModelAndView("service/ser_apply_list",map);
	}
	@RequestMapping("/updateSessionDuty.do")
	@ResponseBody
	public String updateSessionDuty(HttpServletRequest request){
		String attribute = (String)request.getParameter("duty");
		HttpSession session = request.getSession();
		ServletContext application = request.getSession().getServletContext();
		Integer userId = (Integer)session.getAttribute("user_id");
		String userName = (String)session.getAttribute("user_name");
		//1为服务台，0为普通运维人员
		if("1".equals(attribute)){
			if(application.getAttribute("dutyYes") != null) {
				application.removeAttribute("dutyYes");
			   }
			if(application.getAttribute("dutyName") != null){
				application.removeAttribute("dutyName");
			}
			application.setAttribute("dutyYes", userId);
			application.setAttribute("dutyName", userName);
			return "1";
		}else{
			if(application.getAttribute("dutyYes") != null) {
				application.removeAttribute("dutyYes");
			   }
			if(application.getAttribute("dutyName") != null){
				application.removeAttribute("dutyName");
			}
			application.setAttribute("dutyYes", null);
			application.setAttribute("dutyName", null);
			return "0";
		}
		
	}
	@RequestMapping("/queryApplication.do")
	@ResponseBody
	public Map<String,Object> queryApplication(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		ServletContext application = request.getSession().getServletContext();
		Object dutyYes = application.getAttribute("dutyYes");
		Object dutyName = application.getAttribute("dutyName");
		map.put("dutyYes", dutyYes);
		map.put("dutyName", dutyName);
		return map;
	}
		
	
	
	/**
	 * <pre>selectAll (查询申告信息)
	 * Created by 家栋梁 on 2017年10月10日下午3:51:40  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectEventInfoAll.do")
	@ResponseBody
	public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> selectAll = eventInfoBusiness.selectAll(request,response);
		return selectAll;
	}
	/**
	 * <pre>deleteEventInfo (批量删除)
	 * Created by 家栋梁 on 2017年10月10日下午6:34:03  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/deleteEventInfo.do")
	@ResponseBody
	public int deleteEventInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int de = eventInfoBusiness.deleteByPrimaryKey(request, response);
		return de;
	}
	/**
	 * <pre>serviceAdd (跳转新增页面)
	 * Created by 家栋梁 on 2017年10月11日上午10:35:05  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/serviceAdd.do")
	@ResponseBody
	@Token(remove=false, save = true)
	public ModelAndView serviceAdd(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> serviceAdd = eventInfoBusiness.serviceAdd(request,response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		serviceAdd.put("pageNumber", pageNumber);
		serviceAdd.put("pageSize", pageSize);
		serviceAdd.put("totalPage", totalPage);
		serviceAdd.put("totalRow", totalRow);
		return new ModelAndView("service/ser_apply_add",serviceAdd);
	}
	/**
	 * <pre>serviceRepe 
	 * Created by 家栋梁 on 2017年10月17日下午4:36:53  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/serviceRepe.do")
	@ResponseBody
	public ModelAndView serviceRepe(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> serviceRepe = eventInfoBusiness.serviceRepe(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		serviceRepe.put("pageNumber", pageNumber);
		serviceRepe.put("pageSize", pageSize);
		serviceRepe.put("totalPage", totalPage);
		serviceRepe.put("totalRow", totalRow);
		serviceRepe.put("pages", pages);
		return new ModelAndView("service/ser_apply_repe",serviceRepe);
	}
	/**
	 * <pre>selectEventUser (根据部门ID查询人员信息)
	 * Created by 家栋梁 on 2017年10月11日上午11:36:08  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDeptUser.do")
	@ResponseBody
	public Map<String,Object> selectDeptUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String,Object> selectDeptUser = eventInfoBusiness.selectDeptUser(request, response);
		return selectDeptUser;
	}
	/**
	 * <pre>addEventInfo (新增)
	 * Created by 家栋梁 on 2017年10月11日下午2:13:12  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/addEventInfo.do")
	@ResponseBody
	@Token(remove=true, save = false)
	public int addEventInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int in = eventInfoBusiness.addEventInfo(request, response);
		return in;
	}
	/**
	 * <pre>updateEventInfo (跳转至修改页面)
	 * Created by 家栋梁 on 2017年10月11日下午3:33:02  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/updateEventInfo.do")
	@ResponseBody
	public ModelAndView updateEventInfo(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> updateEventInfo = eventInfoBusiness.updateEventInfo(request, response);
		return new ModelAndView("service/ser_apply_mod",updateEventInfo);
	}
	/**
	 * <pre>updateEvent (修改)
	 * Created by 家栋梁 on 2017年10月11日下午5:19:00  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/updateEvent.do")
	@ResponseBody
	public int updateEvent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int in = eventInfoBusiness.updateEvent(request, response);
		return in;
	}
	/**
	 * <pre>examine (查看)
	 * Created by 家栋梁 on 2017年10月17日下午12:01:54  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/examine.do")
	@ResponseBody
	public Map<String,Object> examine(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> examine = eventInfoBusiness.examine(request, response);
		return examine;
	}
	/**
	 * <pre>dateCreate (查询本月所有的申告的总条数)
	 * Created by 家栋梁 on 2017年10月23日下午2:58:13  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/dateCreate.do")
	@ResponseBody
	public Map<String,Object> dateCreate() throws Exception{
		Map<String,Object> dateCreate = eventInfoBusiness.dateCreate();
		return dateCreate;
	}
	/**
	 * <pre>userById 
	 * Created by 家栋梁 on 2017年10月31日下午1:57:18  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/userById.do")
	@ResponseBody
	public Map<String,Object> userById(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		SysUserInfo userCal = null;
		String userId = request.getParameter("userId");
		if (userId != "") {
			userCal = eventInfoBusiness.userById(Integer.valueOf(userId));
		}
		Integer idDept = userCal.getIdDept();
		SysDeptInfo deptEvent = sysDeptInfoBusiness.deptEvent(idDept);
		map.put("userCal", userCal);
		map.put("deptEvent", deptEvent);
		return map;
	}
	/**
	 * <pre>selectDeptAll (查询全部部门)
	 * Created by 家栋梁 on 2017年10月12日下午5:52:10  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDeptAll.do")
	@ResponseBody
	public List<SysDeptInfo> selectDeptAll() throws Exception{
		List<SysDeptInfo> selectDeptAll = eventInfoBusiness.selectDeptAll();
		for(SysDeptInfo dept:selectDeptAll){
			dept.setExtend2(ChineseToInitials.getPYIndexStr(dept.getName(), true).toLowerCase());
		}
		return selectDeptAll;
	}
	
	@RequestMapping("/selectEventTs.do")
	@ResponseBody
	public EventInfo selectEventTs(HttpServletRequest request) throws Exception{
		return eventInfoBusiness.selectEventTs(request);
	}
}
