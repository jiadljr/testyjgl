package com.qkby.event.controller;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.event.business.EventInfoAcceptbusiness;
import com.qkby.sysmanage.business.SysFileInfoBusiness;
import com.qkby.sysmanage.business.SysUserPostInfoBusiness;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysUserPostInfo;
import com.qkby.utils.JsonResult;
import com.qkby.utils.ReadFile;
import com.qkby.utils.Token;

@Controller
public class EventInfoAcceptController {
	@Resource
	EventInfoAcceptbusiness eventInfoAcceptbusiness;
	@Resource
	SysUserPostInfoBusiness sysUserPostInfoBusiness;
	@Resource
	SysFileInfoBusiness sysFileInfoBusiness;

	/**
	 * 去受理页面
	 * 
	 * @author 李帅
	 */
	@RequestMapping("/toEventInfoAccept.do")
	public ModelAndView toEventInfoAccept(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
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
		return new ModelAndView("service/ser_accept_list", map);
	}

	/**
	 * 查询受理页面信息
	 * 
	 * @author 李帅
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/selectAcceptAll.do")
	public Map<String, Object> selectAcceptAll(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = eventInfoAcceptbusiness.selectByExample(request, response);
		return map;
	}

	/**
	 * 跳转受理页面
	 * 
	 * @author 李帅
	 * @throws Exception
	 */
	@RequestMapping("/eventAccept.do")
	@Token(remove = false, save = true)
	public ModelAndView eventAccept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = eventInfoAcceptbusiness.selectAcceptAll(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String startTime = request.getParameter("startTime");
		String front = request.getParameter("front");
		if (startTime == "") {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if (endTime == "") {
			endTime = null;
		}
		String contact = request.getParameter("contact");
		if (contact == "") {
			contact = null;
		}
		String idDept = request.getParameter("idDept");
		if (idDept == "") {
			idDept = null;
		}
		String eventStatus = request.getParameter("eventStatus");
		if (eventStatus == "") {
			eventStatus = null;
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("contact", contact);
		map.put("idDept", idDept);
		map.put("eventStatus", eventStatus);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("front", front);
		return new ModelAndView("service/ser_accept_info", map);
	}

	/**
	 * 点击岗位切换人员
	 * 
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/changeUser.do")
	public List<SysUserPostInfo> changeUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<SysUserPostInfo> userNameList = sysUserPostInfoBusiness.selectUserNameById(request, response);
		return userNameList;
	}

	/**
	 * 受理提交
	 * 
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/accept.do")
	@Token(remove = true, save = false)
	public String accept(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mass = eventInfoAcceptbusiness.updateAccept(request, response);
		return mass;
	}

	/**
	 * 查看确认信息
	 * 
	 * @author 李帅
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkEvent.do")
	@Token(remove = false, save = true)
	public ModelAndView checkEvent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = eventInfoAcceptbusiness.checkEvent(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String startTime = request.getParameter("startTime");
		String front = request.getParameter("front");
		if (startTime == "") {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if (endTime == "") {
			endTime = null;
		}
		String contact = request.getParameter("contact");
		if (contact == "") {
			contact = null;
		}
		String idDept = request.getParameter("idDept");
		if (idDept == "") {
			idDept = null;
		}
		String eventStatus = request.getParameter("eventStatus");
		if (eventStatus == "") {
			eventStatus = null;
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("contact", contact);
		map.put("idDept", idDept);
		map.put("eventStatus", eventStatus);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("front", front);
		return new ModelAndView("service/ser_accept_sure", map);
	}

	/**
	 * 确认提交
	 * 
	 * @author 李帅
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/sureSubmit.do")
	@Token(remove = true, save = false)
	public String sureSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String mass = eventInfoAcceptbusiness.sureSubmit(request);
		return mass;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @author 李帅
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/exportDocLoad.do")
	public JsonResult<String> exportDoc(HttpServletRequest request) throws Exception {
		String result = "success";
		String fileId = request.getParameter("fileId");
		int id = Integer.valueOf(fileId);
		SysFileInfo file = sysFileInfoBusiness.selectDealFile(id);
		String filePath = "";
		if (file != null) {
			filePath = file.getPath();
		}
		File fileIo = new File(filePath);
		boolean exists = fileIo.exists();
		if (!exists) {
			result = "error";
		}
		return new JsonResult<String>(result);
	}

	/**
	 * 条状申告受理页
	 * 
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toApplyAndAccept.do")
	@Token(remove = false, save = true)
	public ModelAndView toApplyAndAccept(HttpServletRequest request) throws Exception {
		Map<String, Object> map = eventInfoAcceptbusiness.applyAndAccept(request);
		return new ModelAndView("service/ser_accept_apply", map);
	}

	@ResponseBody
	@RequestMapping("/addApplyAndAccept.do")
	@Token(remove = true, save = false)
	public String addApplyAndAccept(HttpServletRequest request) throws Exception {
		String mass = eventInfoAcceptbusiness.addApplyAndAccept(request);
		return mass;
	}

	@RequestMapping("/downLoad.do")
	public void downLoad(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileId = request.getParameter("fileId");
		int id = Integer.valueOf(fileId);
		SysFileInfo file = sysFileInfoBusiness.selectDealFile(id);
		String fileName = "";
		String filePath = "";
		if (file != null) {
			fileName = file.getName();
			filePath = file.getPath();
		}
		ReadFile.downLoadFile(response, request, filePath, fileName);
	}

	@RequestMapping("/selectServiceDeptCount.do")
	@ResponseBody
	public List<Map<String, Object>> selectServiceDeptCount(HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		if ("".equals(startTime)) {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if ("".equals(endTime)) {
			endTime = null;
		}
		String deptId = request.getParameter("deptId");
		if ("".equals(deptId)) {
			deptId = null;
		}
		String sourceId = request.getParameter("sourceId");
		if ("".equals(sourceId)) {
			sourceId = null;
		}
		String serviceId = request.getParameter("serviceId");
		if ("".equals(serviceId)) {
			serviceId = null;
		}
		String deptName = request.getParameter("deptName");
		if("".equals(deptName)){
			deptName = null;
		}
		int[] ints = null;
		if (deptId != null) {
			String[] split = deptId.split(",");
			ints = new int[split.length];
			for (int i = 0; i < split.length; i++) {
				ints[i] = Integer.parseInt(split[i]);
			}
		}
		int [] souId = null;
		if(sourceId != null){
			String[] split = sourceId.split(",");
			souId = new int[split.length];
			for(int i =0;i<split.length;i++){
				souId[i] = Integer.parseInt(split[i]);
			}
		}
		int [] serId = null;
		if(serviceId != null){
			String[] split = serviceId.split(",");
			serId = new int[split.length];
			for(int i =0;i<split.length;i++){
				serId[i] = Integer.parseInt(split[i]);
			}
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("deptId", ints);
		map.put("sourceId", souId);
		map.put("serviceId", serId);
		map.put("depName", deptName);
		Map<String,Object> idMap = new HashMap<String,Object>();
		String serDeName = "";
		List<Map<String, Object>> selectServiceDeptCount = eventInfoAcceptbusiness.selectServiceDeptCount(map);
		for(int i= 0 ; i <selectServiceDeptCount.size();i++){
			serDeName += selectServiceDeptCount.get(i).get("deptName")+",";
		}
		String[] split = serDeName.split(",");
		String[] result_minus = null;
		if(deptName != null){
			String[] split2 = deptName.split(",");
			result_minus = minus(split, split2);
		}
		idMap.put("idDe", result_minus);
		selectServiceDeptCount.add(idMap);
		return selectServiceDeptCount;
	}

	private String[] minus(String[] split, String[] split2) {
		 LinkedList<String> list = new LinkedList<String>();   
	        LinkedList<String> history = new LinkedList<String>();   
	        String[] longerArr = split;   
	        String[] shorterArr = split2;   
	        //找出较长的数组来减较短的数组   
	        if (split.length > split2.length) {   
	            longerArr = split2;   
	            shorterArr = split;   
	        }   
	        for (String str : longerArr) {   
	            if (!list.contains(str)) {   
	                list.add(str);   
	            }   
	        }   
	        for (String str : shorterArr) {   
	            if (list.contains(str)) {   
	                history.add(str);   
	                list.remove(str);   
	            } else {   
	                if (!history.contains(str)) {   
	                    list.add(str);   
	                }   
	            }   
	        }   
	  
	        String[] result = {};   
	        return list.toArray(result);   
	}
	@RequestMapping("exportDeptCount.do")
	public void exportDeptCount(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String startTime = request.getParameter("startTime");
		if ("".equals(startTime)) {
			startTime = null;
		}
		String endTime = request.getParameter("endTime");
		if ("".equals(endTime)) {
			endTime = null;
		}
		String deptId = request.getParameter("deptId");
		if ("".equals(deptId)) {
			deptId = null;
		}
		String sourceId = request.getParameter("sourceId");
		if ("".equals(sourceId)) {
			sourceId = null;
		}
		String serviceId = request.getParameter("serviceId");
		if ("".equals(serviceId)) {
			serviceId = null;
		}
		int[] ints = null;
		if (deptId != null) {
			String[] split = deptId.split(",");
			ints = new int[split.length];
			for (int i = 0; i < split.length; i++) {
				ints[i] = Integer.parseInt(split[i]);
			}
		}
		int [] souId = null;
		if(sourceId != null){
			String[] split = sourceId.split(",");
			souId = new int[split.length];
			for(int i =0;i<split.length;i++){
				souId[i] = Integer.parseInt(split[i]);
			}
		}
		int [] serId = null;
		if(serviceId != null){
			String[] split = serviceId.split(",");
			serId = new int[split.length];
			for(int i =0;i<split.length;i++){
				serId[i] = Integer.parseInt(split[i]);
			}
		}
		String deptName = request.getParameter("deptName");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("deptId", ints);
		map.put("sourceId", souId);
		map.put("serviceId", serId);
		map.put("depName", deptName);
		map.put("request", request);
		Map<String, Object> fileMap = eventInfoAcceptbusiness.exportDeptCountMethod(map);
		ReadFile.downLoadFile(response, request, (String) fileMap.get("filePath"), (String) fileMap.get("fileName"));
	}
}
