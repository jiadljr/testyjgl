package com.qkby.work.controller;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.qkby.proj.entity.ProjectFile;
import com.qkby.sysmanage.business.SysFileInfoBusiness;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Utils;
import com.qkby.work.business.WorkPlanBusiness;
import com.qkby.work.business.WorkPlanFileBusiness;
import com.qkby.work.business.WorkReportBusiness;
import com.qkby.work.business.WorkUserPlanBusiness;
import com.qkby.work.entity.WorkPlanFileInfo;
import com.qkby.work.entity.WorkPlanInfo;
import com.qkby.work.entity.WorkUserPlanInfo;
@Controller
public class WorkPlanController {
	
	@Resource
	private WorkPlanBusiness workPlanBusiness;
	@Resource
	private SysFileInfoBusiness sysFileInfoBusiness;
	@Resource
	private WorkPlanFileBusiness workPlanFileBusiness;
	@Resource
	private WorkUserPlanBusiness workUserPlanBusiness;
	@Resource
	private WorkReportBusiness workReportBusiness;
	/**
	 * 查询个人计划部门计划列表
	 * @return
	 */
	@RequestMapping("selectWorkPlanAll.do")
	@ResponseBody
	public Map<String,Object> selectWorkPlanAll(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> pageMap = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		map.put("userId", userId);
		int totalRow  = workPlanBusiness.selectWorkCount(map);
		Utils.paging(request, totalRow, map, pageMap);
		List<Map<String,Object>> selectWorkPlanAll = workPlanBusiness.selectWorkPlanAll(map);
		pageMap.put("selectWorkPlanAll", selectWorkPlanAll);
		return pageMap;
	}
	@RequestMapping("addWorkPlan.do")
	public ModelAndView addWorkPlan() throws Exception{
		Map<String, Object> addWorkPlan = workPlanBusiness.addWorkPlan();
		return new ModelAndView("work/work_plan_add",addWorkPlan);
	}
	@RequestMapping("/insertWorkPlan.do")
	@Transactional
	@ResponseBody
	public int insertWorkPlan(HttpServletRequest request,HttpServletResponse response) throws Exception{
		WorkPlanInfo workPlan = new WorkPlanInfo();
		WorkUserPlanInfo workUserPlan = new WorkUserPlanInfo();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String planHeadline = request.getParameter("planHeadline");
		String workType = request.getParameter("workType");
		String planType = request.getParameter("planType");
		String planTime = request.getParameter("planTime");
		String planBeginTime = request.getParameter("planBeginTime");
		String planFinishTime = request.getParameter("planFinishTime");
		String remind = request.getParameter("remind");
		String remindTime = request.getParameter("remindTime");
		String remindWeek = request.getParameter("remindWeek");
		String remindMonth = request.getParameter("remindMonth");
		String content = request.getParameter("content");
		String principal = request.getParameter("principal");
		String idUserGroup = request.getParameter("idUserGroup");
		String nameUserGroup = request.getParameter("nameUserGroup");
		String userGroup = request.getParameter("userGroup");
		String userGroupName = request.getParameter("userGroupName");
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		Date finishTime = sf.parse(planFinishTime);
		Date beginTime = sf.parse(planBeginTime);
		Date planTimes = sf.parse(planTime);
		Date date = new Date();
		Date bt=sf.parse(planBeginTime); 
		if (bt.before(date)){
		  workPlan.setPlanState(ConstantMenu.PLAN_ONE_STATE);  
		}else{
		  workPlan.setPlanState(ConstantMenu.PLAN_TWO_STATE);
		}
		workPlan.setPlanner(userId);
		workPlan.setParticipantName(nameUserGroup);
		workPlan.setPushStaff(userGroup);
		workPlan.setPushStaffName(userGroupName);
		if(workType.equals(2)){
			workPlan.setParticipant(idUserGroup+","+principal);
		}
		if("2".equals(workType)){
			workPlan.setPrincipal(Integer.valueOf(principal));
		}
		workPlan.setContent(content);
		workPlan.setFoundTime(date);
		workPlan.setRecentUpdate(date);
		workPlan.setPlanBeginTime(beginTime);
		workPlan.setPlanFinishTime(finishTime);
		workPlan.setPlanHeadline(planHeadline);
		workPlan.setWorkType(Integer.valueOf(workType));
		workPlan.setPlanType(Integer.valueOf(planType));
		workPlan.setPlanTime(planTimes);
		workPlan.setRemind(Integer.valueOf(remind));
		if("5".equals(remind)){
			workPlan.setRemindTime(remindTime);
		}else if("3".equals(remind)){
			workPlan.setRemindTime(remindWeek);
		}else if("4".equals(remind)){
			workPlan.setRemindTime(remindMonth);
		}
		workPlan.setDs(ConstantMenu.DS_ZERO);
		int a = workPlanBusiness.insertWorkPlan(workPlan);
		int id = workPlan.getId();
		if(!"".equals(userGroup)){
			String[] split = userGroup.split(",");
			for (int i = 0; i < split.length; i++) {
				workUserPlan.setUserId(Integer.valueOf(split[i]));
				workUserPlan.setPlanId(id);
				workUserPlan.setDs(ConstantMenu.DS_TWO);
				workUserPlan.setExamine(ConstantMenu.EXAMINE_TWO);
				workUserPlanBusiness.insertUserPlan(workUserPlan);
			}
		}
		insertProjFile(request, response,id);
		return a;
	}
	
	@RequestMapping("/updatePlanState.do")
	@ResponseBody
	public void updatePlanState(HttpServletRequest request){
		Map<String,Object> map= new HashMap<String,Object>();
		String id = request.getParameter("id");
		map.put("id", id);
		map.put("planState", ConstantMenu.PLAN_THREE_STATE);
		workPlanBusiness.updatePlanState(map);
	}
	@RequestMapping("/deleteWorkPlan.do")
	@ResponseBody
	public void deleteWorkPlan(HttpServletRequest request){
		Map<String,Object> map= new HashMap<String,Object>();
		String id = request.getParameter("id");
		map.put("id", id);
		map.put("ds", ConstantMenu.DS_ON);
		workPlanBusiness.deleteWorkPlan(map);
	}
	@RequestMapping("/workPlanList.do")
	public String workPlanList(){
		return "work/work_plan_list";
	}
	@RequestMapping("sectionWorkPlanAll.do")
	@ResponseBody
	public Map<String,Object> sectionWorkPlanAll(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> pageMap = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		map.put("userId", userId);
		int totalRow  = workPlanBusiness.sectionWorkCount(map);
		Utils.paging(request, totalRow, map, pageMap);
		List<Map<String,Object>> sectionWorkPlanAll = workPlanBusiness.sectionWorkPlanAll(map);
		pageMap.put("sectionWorkPlanAll", sectionWorkPlanAll);
		return pageMap;
	}
	@RequestMapping("/queryCheck.do")
	public ModelAndView queryCheck(HttpServletRequest request){
		WorkUserPlanInfo workUserPlan = new WorkUserPlanInfo();
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id = request.getParameter("id");
		WorkPlanInfo selectCheckById = workPlanBusiness.selectCheckById(Integer.valueOf(id));
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workUserPlan.setPlanId(Integer.valueOf(id));
		workUserPlan.setUserId(userId);
		workUserPlan.setExamine(ConstantMenu.EXAMINE_ONE);
		workUserPlanBusiness.updateUserPlanExamine(workUserPlan);
		Date foundTime = selectCheckById.getFoundTime();
		String format = sf.format(foundTime);
		selectCheckById.setExtend1(format);
		List<Map<String, Object>> selectPlanFile = workPlanFileBusiness.selectPlanFile(Integer.valueOf(id));
		map.put("check", selectCheckById);
		map.put("selectPlanFile", selectPlanFile);
		return new ModelAndView("work/work_plan_check",map);
	}
	@RequestMapping("/queryCheckPlan.do")
	public ModelAndView queryCheckPlan(HttpServletRequest request){
		WorkUserPlanInfo workUserPlan = new WorkUserPlanInfo();
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id = request.getParameter("id");
		WorkPlanInfo selectCheckPlanById = workPlanBusiness.selectCheckPlanById(Integer.valueOf(id));
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workUserPlan.setPlanId(Integer.valueOf(id));
		workUserPlan.setUserId(userId);
		workUserPlan.setExamine(ConstantMenu.EXAMINE_ONE);
		workUserPlanBusiness.updateUserPlanExamine(workUserPlan);
		Date foundTime = selectCheckPlanById.getFoundTime();
		String format = sf.format(foundTime);
		selectCheckPlanById.setExtend1(format);
		List<Map<String, Object>> selectPlanFile = workPlanFileBusiness.selectPlanFile(Integer.valueOf(id));
		map.put("check", selectCheckPlanById);
		map.put("selectPlanFile", selectPlanFile);
		return new ModelAndView("work/work_plan_checks",map);
	}
	@RequestMapping("/queryWorkPlanMod.do")
	@ResponseBody
	public ModelAndView queryWorkPlanMod(HttpServletRequest request) throws Exception{
		Map<String, Object> addWorkPlan = workPlanBusiness.addWorkPlan();
		String id= request.getParameter("id");
		WorkPlanInfo selectCheckById = workPlanBusiness.selectCheckPlanById(Integer.valueOf(id));
		List<Map<String, Object>> selectPlanFile = workPlanFileBusiness.selectPlanFile(Integer.valueOf(id));
		addWorkPlan.put("selectPlanFile",selectPlanFile);
		addWorkPlan.put("check", selectCheckById);
		return new ModelAndView("work/work_plan_mod",addWorkPlan);
	}
	@RequestMapping("/updateWorkPlan.do")
	@ResponseBody
	public int updateWorkPlan(HttpServletRequest request,HttpServletResponse response) throws NumberFormatException, Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String planHeadline = request.getParameter("planHeadline");
		String workId = request.getParameter("workId");
		String workType = request.getParameter("workType");
		String planType = request.getParameter("planType");
		String planTime = request.getParameter("planTime");
		String planBeginTime = request.getParameter("planBeginTime");
		String planFinishTime = request.getParameter("planFinishTime");
		String remind = request.getParameter("remind");
		String remindTime = request.getParameter("remindTime");
		String remindWeek = request.getParameter("remindWeek");
		String remindMonth = request.getParameter("remindMonth");
		String content = request.getParameter("content");
		String principal = request.getParameter("principal");
		String idUserGroup = request.getParameter("idUserGroup");
		String nameUserGroup = request.getParameter("nameUserGroup");
		String hidPlanState = request.getParameter("hidPlanState");
		String userGroup = request.getParameter("userGroup");
		String userGroupName = request.getParameter("userGroupName");
		WorkPlanInfo workPlan = new WorkPlanInfo();
		Date date = new Date();
		Date finishTime = sf.parse(planFinishTime);
		Date beginTime = sf.parse(planBeginTime);
		Date planTimes = sf.parse(planTime);
		workPlan.setParticipantName(nameUserGroup);
		workPlan.setPlanState(Integer.valueOf(hidPlanState));
		workPlan.setId(Integer.valueOf(workId));
		workPlan.setPushStaff(userGroup);
		workPlan.setPushStaffName(userGroupName);
		workPlan.setParticipant(idUserGroup);
		workPlan.setPrincipal(Integer.valueOf(principal));
		workPlan.setContent(content);
		workPlan.setRecentUpdate(date);
		workPlan.setPlanBeginTime(beginTime);
		workPlan.setPlanFinishTime(finishTime);
		workPlan.setPlanHeadline(planHeadline);
		workPlan.setWorkType(Integer.valueOf(workType));
		workPlan.setPlanType(Integer.valueOf(planType));
		workPlan.setPlanTime(planTimes);
		workPlan.setRemind(Integer.valueOf(remind));
		if("5".equals(remind)){
			workPlan.setRemindTime(remindTime);
		}else if("3".equals(remind)){
			workPlan.setRemindTime(remindWeek);
		}else if("4".equals(remind)){
			workPlan.setRemindTime(remindMonth);
		}
		int coun = workPlanBusiness.updateWorkPlan(workPlan);
		WorkUserPlanInfo workUserPlan = new WorkUserPlanInfo();
		insertProjFile(request,response,Integer.valueOf(workId));
		Map<String, Object> fileParamMap = new HashMap<String, Object>();
		String fileProjIds = request.getParameter("fileProjIds");
		String fileIds = request.getParameter("fileIds");
		int id = workPlan.getId();
		List<Map<String, Object>> selectPlanUserById = workUserPlanBusiness.selectPlanUserById(id);
		if(selectPlanUserById.size() == 0){
			if(!"".equals(userGroup)){
				String[] split = userGroup.split(",");
				for (int i = 0; i < split.length; i++) {
					workUserPlan.setUserId(Integer.valueOf(split[i]));
					workUserPlan.setPlanId(id);
					workUserPlan.setDs(ConstantMenu.DS_TWO);
					workUserPlan.setExamine(ConstantMenu.EXAMINE_TWO);
					workUserPlanBusiness.insertUserPlan(workUserPlan);
				}
			}
		}
		if (!"".equals(fileIds) && fileIds!=null) {
			String[] fileIdsArray = fileIds.split(",");
			fileParamMap.put("id", fileIdsArray);
			sysFileInfoBusiness.deleteProjFiles(fileParamMap);//
			if(!"".equals(fileProjIds) && fileProjIds!=null){
				String[] fileProjIdArray = fileProjIds.split(",");
				for (int i = 0; i < fileProjIdArray.length; i++) {
					workPlanFileBusiness.deletePlanFile(Integer.valueOf(fileProjIdArray[i]));
				}
			}
		}
		return coun;
	}
	@RequestMapping("/selectLookUpAll.do")
	@ResponseBody
	public Map<String,Object> selectLookUpAll(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String, Object>();
		Map<String,Object> pageMap = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		map.put("pushStaff", userId);
		int totalRow = workPlanBusiness.selectLookUpCount(map);
		Utils.paging(request, totalRow, map, pageMap);
		List<Map<String, Object>> sectionWorkPlanAll = workPlanBusiness.selectLookUpAll(map);
		pageMap.put("workPlan", sectionWorkPlanAll);
		return pageMap;
	}
	@RequestMapping("/queryPlanCheckReport.do")
	public ModelAndView queryPlanCheckReport(HttpServletRequest request){
		WorkUserPlanInfo workUserPlan = new WorkUserPlanInfo();
		Map<String,Object> map = new HashMap<String,Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String id = request.getParameter("id");
		WorkPlanInfo selectCheckById = workPlanBusiness.selectCheckById(Integer.valueOf(id));
		Date foundTime = selectCheckById.getFoundTime();
		String format = sf.format(foundTime);
		selectCheckById.setExtend1(format);
		List<Map<String, Object>> selectPlanFile = workPlanFileBusiness.selectPlanFile(Integer.valueOf(id));
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workUserPlan.setPlanId(Integer.valueOf(id));
		workUserPlan.setUserId(userId);
		workUserPlan.setExamine(ConstantMenu.EXAMINE_ONE);
		workUserPlanBusiness.updateUserPlanExamine(workUserPlan);
		map.put("check", selectCheckById);
		map.put("selectPlanFile", selectPlanFile);
		return new ModelAndView("work/work_check_plan",map);
	}
	@RequestMapping("/deleteUserPlanDs.do")
	@ResponseBody
	public void deleteUserPlanDs(HttpServletRequest request){
		WorkUserPlanInfo workUserPlan = new WorkUserPlanInfo();
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		workUserPlan.setUserId(userId);
		workUserPlan.setPlanId(Integer.valueOf(id));
		workUserPlan.setDs(ConstantMenu.DS_ON);
		workUserPlanBusiness.updateUserPlanDs(workUserPlan);
	}
	@RequestMapping("/selectRemindTimeAll.do")
	@ResponseBody
	public Map<String,Object> selectRemindTimeAll(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		List<Map<String, Object>> selectRemindDay = workPlanBusiness.selectRemindDay(userId);
		List<Map<String, Object>> selectRemindCustom = workPlanBusiness.selectRemindCustom(userId);
		List<Map<String, Object>> selectRemindMonth = workPlanBusiness.selectRemindMonth(userId);
		List<Map<String, Object>> selectRemindWeek = workPlanBusiness.selectRemindWeek(userId);
		selectRemindDay.addAll(selectRemindCustom);
		selectRemindDay.addAll(selectRemindMonth);
		selectRemindDay.addAll(selectRemindWeek);
		map.put("selectRemindDay", selectRemindDay);
		return map;
	}
	@RequestMapping("/selectPushMessage.do")
	@ResponseBody
	public List<Map<String,Object>> selectPushMessage(HttpServletRequest request){
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		List<Map<String, Object>> selectPushMessage = workPlanBusiness.selectPushMessage(userId);
		List<Map<String, Object>> selectReportPushMessage = workReportBusiness.selectReportPushMessage(userId);
		selectPushMessage.addAll(selectReportPushMessage);
		return selectPushMessage;
	}
	//工作计划上传文件
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
                       	WorkPlanFileInfo workPlanFile = new WorkPlanFileInfo();
	               		SysFileInfo sysFileInf = new SysFileInfo();
	               		HttpSession session = request.getSession();
	               		int userId = (int) session.getAttribute("user_id");
	               		String file_name = file_name_from.split("\\.")[0];
	               		if(!"".equals(path_to)){
	               			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
	               			Date date = new Date();
	               			String format = sf.format(date);
	               			int i=(int)(Math.random()*900)+100;
	               			String fileCode = "GZJH"+format+i;
	               			sysFileInf.setCode(fileCode);
	               			sysFileInf.setIdFileType(5);
	               			sysFileInf.setPath(path_to);
	               			sysFileInf.setName(file_name);
	               			sysFileInf.setCreateDate(new Date());
	               			sysFileInf.setCreateUser(userId);
	               			sysFileInf.setRemark("工作计划下的文件");
	               			sysFileInfoBusiness.insertGeneralFile(sysFileInf);
	               			Integer id2 = sysFileInf.getId();
	               			workPlanFile.setFileId(id2);
	               			workPlanFile.setPlanId(fileProjCode);
	               			workPlanFileBusiness.insertPlanFile(workPlanFile);
	               		}  
                   }  
               }  
           }  
       }
	return null;
}
}
