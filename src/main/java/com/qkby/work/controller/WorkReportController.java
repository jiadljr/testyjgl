package com.qkby.work.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.business.SysFileInfoBusiness;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.utils.Utils;
import com.qkby.work.business.WorkPlanBusiness;
import com.qkby.work.business.WorkReportBusiness;
import com.qkby.work.business.WorkReportFileBusiness;
import com.qkby.work.business.WorkUserReportBusiness;
import com.qkby.work.entity.WorkPlanFileInfo;
import com.qkby.work.entity.WorkReportFileInfo;
import com.qkby.work.entity.WorkReportInfo;
import com.qkby.work.entity.WorkUserPlanInfo;
import com.qkby.work.entity.WorkUserReportInfo;

@Controller
public class WorkReportController {
	@Resource
	private WorkReportBusiness workReportBusiness;
	@Resource
	private SysFileInfoBusiness sysFileInfoBusiness;
	@Resource
	private WorkReportFileBusiness workReportFileBusiness;
	@Resource
	private WorkPlanBusiness workPlanBusiness;
	@Resource
	private WorkUserReportBusiness workUserReportBusiness;
	
	@RequestMapping("/workReportList.do")
	public String workReportList(){
		return "work/work_report_list";
	}
	@RequestMapping("/selectWorkReport.do")
	@ResponseBody
	public Map<String,Object> selectWorkReport(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> pageMap = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		map.put("userId", userId);
		int totalRow = workReportBusiness.selectWorkReportCount(map);
		Utils.paging(request, totalRow, map, pageMap);
		List<WorkReportInfo> selectWorkReport = workReportBusiness.selectWorkReport(map);
		pageMap.put("selectWorkReport", selectWorkReport);
		return pageMap;
	}
	@RequestMapping("/addWorkReport.do")
	public ModelAndView addWorkReport() throws Exception{
		Map<String, Object> addWorkPlan = workPlanBusiness.addWorkPlan();
		return new ModelAndView("work/work_report_add",addWorkPlan);
	}
	@RequestMapping("/addSubWorkReport.do")
	@ResponseBody
	@Transactional
	public int addSubWorkReport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		WorkReportInfo workReport = new WorkReportInfo();
		WorkUserReportInfo workUserReport = new WorkUserReportInfo();
		String reportName = request.getParameter("reportName");
		String reportType = request.getParameter("reportType");
		String reportContent = request.getParameter("reportContent");
		String reportStaff = request.getParameter("idUserGroup");
		String reportStaffName = request.getParameter("nameUserGroup");
		String draft = request.getParameter("draft");
		Date date = new Date();
		if("1".equals(draft)){
			workReport.setReportState(ConstantMenu.DRAFT_ONE);
		}else if("2".equals(draft)){
			workReport.setReportState(ConstantMenu.DRAFT_TWO);
		}
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workReport.setReportUser(userId);
		workReport.setReportStaffName(reportStaffName);
		workReport.setSubTime(date);
		workReport.setReportContent(reportContent);
		workReport.setReportName(reportName);
		workReport.setReportStaff(reportStaff);
		workReport.setReportType(Integer.valueOf(reportType));
		workReport.setDs(ConstantMenu.DS_ZERO);
		int a = workReportBusiness.addWorkReport(workReport);
		int id = workReport.getId();
		if(!"".equals(reportStaff)){
			String[] split = reportStaff.split(",");
			for (int i = 0; i < split.length; i++) {
				workUserReport.setUserId(Integer.valueOf(split[i]));
				workUserReport.setReportId(id);
				workUserReport.setDs(ConstantMenu.DS_TWO);
				workUserReport.setExamine(ConstantMenu.EXAMINE_TWO);
				workUserReportBusiness.insertUserReport(workUserReport);
			}
		}
		insertProjFile(request,response,id);
		return a;
	}
	@RequestMapping("/workReportCheck.do")
	public ModelAndView workReportCheck(HttpServletRequest request){
		WorkUserReportInfo workUserReport = new WorkUserReportInfo();
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		WorkReportInfo selectWorkReportById = workReportBusiness.selectWorkReportById(Integer.valueOf(id));
		List<Map<String, Object>> selectPlanFile = workReportFileBusiness.selectReportFile(Integer.valueOf(id));
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workUserReport.setReportId(Integer.valueOf(id));
		workUserReport.setUserId(userId);
		workUserReport.setExamine(ConstantMenu.EXAMINE_ONE);
		workUserReportBusiness.updateUserReportExamine(workUserReport);
		map.put("reportCheck", selectWorkReportById);
		map.put("selectPlanFile", selectPlanFile);
	    return new ModelAndView("work/work_report_check",map);	
	}
	@RequestMapping("/deleteWorkReport.do")
	@ResponseBody
	public void deleteWorkReport(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		String reportState = request.getParameter("reportState");
		if("草稿".equals(reportState)){
			workReportBusiness.deleteWorkReport(Integer.valueOf(id));
		}else if("完成".equals(reportState)){
			map.put("id", id);
			map.put("ds", ConstantMenu.DS_ON);
			workReportBusiness.updateWorkReportDs(map);
		}
	}
	@RequestMapping("/queryWorkReportMod.do")
	public ModelAndView queryWorkReportMod(HttpServletRequest request) throws Exception{
		Map<String, Object> addWorkPlan = workPlanBusiness.addWorkPlan();
		String reportId = request.getParameter("reportId");
		WorkReportInfo selectWorkReportById = workReportBusiness.selectWorkReportById(Integer.valueOf(reportId));
		List<Map<String, Object>> selectPlanFile = workReportFileBusiness.selectReportFile(Integer.valueOf(reportId));
		addWorkPlan.put("selectPlanFile",selectPlanFile);
		addWorkPlan.put("report", selectWorkReportById);
		return new ModelAndView("work/work_report_mod",addWorkPlan);
	}
	@RequestMapping("/updateWorkReport.do")
	@ResponseBody
	public int updateWorkReport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		WorkReportInfo workReport = new WorkReportInfo();
		String reportName = request.getParameter("reportName");
		String id = request.getParameter("workId");
		String reportType = request.getParameter("reportType");
		String reportContent = request.getParameter("reportContent");
		String reportStaff = request.getParameter("idUserGroup");
		String reportStaffName = request.getParameter("nameUserGroup");
		String draft = request.getParameter("draft");
		Date date = new Date();
		if("1".equals(draft)){
			workReport.setReportState(ConstantMenu.DRAFT_ONE);
		}else if("2".equals(draft)){
			workReport.setReportState(ConstantMenu.DRAFT_TWO);
		}
		workReport.setId(Integer.valueOf(id));
		workReport.setReportStaffName(reportStaffName);
		workReport.setSubTime(date);
		workReport.setReportContent(reportContent);
		workReport.setReportName(reportName);
		workReport.setReportStaff(reportStaff);
		workReport.setReportType(Integer.valueOf(reportType));
		int a = workReportBusiness.updateWorkReport(workReport);
		insertProjFile(request,response,Integer.valueOf(id));
		Map<String, Object> fileParamMap = new HashMap<String, Object>();
		String fileProjIds = request.getParameter("fileProjIds");
		String fileIds = request.getParameter("fileIds");
		if (!"".equals(fileIds) && fileIds!=null) {
			String[] fileIdsArray = fileIds.split(",");
			fileParamMap.put("id", fileIdsArray);
			sysFileInfoBusiness.deleteProjFiles(fileParamMap);//
			if(!"".equals(fileProjIds) && fileProjIds!=null){
				String[] fileProjIdArray = fileProjIds.split(",");
				for (int i = 0; i < fileProjIdArray.length; i++) {
					workReportFileBusiness.deleteReportFile(Integer.valueOf(fileProjIdArray[i]));
				}
			}
		}
		return a;
	}
	@RequestMapping("/queryWorkSelect.do")
	public ModelAndView queryWorkSelect(){
		return new ModelAndView("work/work_plan_report");
	}
	@RequestMapping("/workPlanReport.do")
	@ResponseBody
	public Map<String,Object> workPlanReport(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> pageMap = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		map.put("reportStaff", userId);
		int totalRow = workReportBusiness.selectPlanReportCount(map);
		Utils.paging(request, totalRow, map, pageMap);
		List<Map<String,Object>> selectPlanReport = workReportBusiness.selectPlanReport(map);
		pageMap.put("planReport", selectPlanReport);
		return pageMap;
	}
	@RequestMapping("/queryPlanReportCheck.do")
	public ModelAndView queryPlanReportCheck(HttpServletRequest request){
		WorkUserReportInfo workUserReport = new WorkUserReportInfo();
		Map<String,Object> map = new HashMap<String,Object>();
		String id = request.getParameter("id");
		WorkReportInfo selectWorkReportById = workReportBusiness.selectWorkReportById(Integer.valueOf(id));
		List<Map<String, Object>> selectPlanFile = workReportFileBusiness.selectReportFile(Integer.valueOf(id));
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workUserReport.setReportId(Integer.valueOf(id));
		workUserReport.setUserId(userId);
		workUserReport.setExamine(ConstantMenu.EXAMINE_ONE);
		workUserReportBusiness.updateUserReportExamine(workUserReport);
		map.put("reportCheck", selectWorkReportById);
		map.put("selectPlanFile", selectPlanFile);
		return new ModelAndView("work/work_check_report",map);
	}
	@RequestMapping("/deleteUserReportDs.do")
	@ResponseBody
	public void deleteUserReportDs(HttpServletRequest request){
		WorkUserReportInfo workUserReport = new WorkUserReportInfo();
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workUserReport.setUserId(userId);
		workUserReport.setReportId(Integer.valueOf(id));
		workUserReport.setDs(ConstantMenu.DS_ON);
		workUserReportBusiness.updateUserReportDs(workUserReport);
	}
	public String insertProjFile(HttpServletRequest request,HttpServletResponse response,Integer fileProjCode) throws Exception{
	    String file_name_from = "";
		String path_to = "";
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String domainName = resource.getString("domainName");  
	    //创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
           //转换成多部分request    
           MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
           //取得request中的所有文件名  
           Iterator<String> iter = multiRequest.getFileNames();  
           while(iter.hasNext()){  
               //取得上传文件  
               MultipartFile file = multiRequest.getFile(iter.next());  
               if(file != null){  
                   //取得当前上传文件的文件名称  
                   String myFileName = file.getOriginalFilename();  
					String s = myFileName.substring(myFileName.indexOf(".")+1);
                   //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
                   if(myFileName.trim() !=""){  
                	   file_name_from = myFileName;
						String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
				        uuid = uuid.replace("-", "");
						String newFileName = uuid +"."+s;
						String path = domainName+ newFileName;
						path_to = path;
                       File localFile = new File(path); 
                       file.transferTo(localFile);  
                       //存入数据库
                       	WorkReportFileInfo workReportFile = new WorkReportFileInfo();
	               		SysFileInfo sysFileInf = new SysFileInfo();
	               		HttpSession session = request.getSession();
	               		int userId = (int) session.getAttribute("user_id");
	               		String file_name = file_name_from.split("\\.")[0];
	               		if(!"".equals(path_to)){
	               			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	               			Date date = new Date();
	               			String format = sf.format(date);
	               			int i=(int)(Math.random()*900)+100;
	               			String fileCode = "GZHB"+format+i;
	               			sysFileInf.setCode(fileCode);
	               			sysFileInf.setIdFileType(5);
	               			sysFileInf.setPath(path_to);
	               			sysFileInf.setName(file_name);
	               			sysFileInf.setCreateDate(new Date());
	               			sysFileInf.setCreateUser(userId);
	               			sysFileInf.setRemark("工作汇报下的文件");
	               			sysFileInfoBusiness.insertGeneralFile(sysFileInf);
	               			Integer id2 = sysFileInf.getId();
	               			workReportFile.setFileId(id2);
	               			workReportFile.setReportId(fileProjCode);
	               			workReportFileBusiness.insertReportFile(workReportFile);
	               		}
                   }  
               }  
           }  
       }
	return null;
}
}
