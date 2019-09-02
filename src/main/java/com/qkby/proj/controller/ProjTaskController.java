package com.qkby.proj.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.proj.business.ProjFileBusiness;
import com.qkby.proj.business.ProjManageBusiness;
import com.qkby.proj.business.ProjTaskBusiness;
import com.qkby.proj.business.ProjTaskRecordBusiness;
import com.qkby.proj.entity.ProjectFile;
import com.qkby.proj.entity.ProjectTask;
import com.qkby.proj.entity.TaskRecord;
import com.qkby.sysmanage.business.SysFileInfoBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.utils.ExcelUtil;
import com.qkby.utils.FilesUpload;
import com.qkby.utils.ReadFile;
import com.qkby.utils.Token;
import com.qkby.utils.Utils;
@Controller
@Scope("prototype")
public class ProjTaskController {
	@Resource
	private ProjTaskBusiness projTaskBusiness;
	@Resource
	private ProjManageBusiness projManageBusiness;
	@Resource 
	SysUserInfoBusiness sysUserInfoBusiness;
	@Resource 
	SysFileInfoBusiness sysFileInfoBusiness;
	@Resource 
	ProjFileBusiness projFileBusiness;
	@Resource 
	ProjTaskRecordBusiness projTaskRecordBusiness;
	
	private final static String EXPORT_TEMPLATE_EXCEL_NAME = "项目批量导入模板.xlsx";
	
	/**
	 * 跳转任务管理页面
	 * 2018年1月16日 上午11:41:26
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@RequestMapping("/toProjTaskPage.do")
	public ModelAndView toProjTaskPage(HttpServletRequest request) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		String pStatus = request.getParameter("pStatus");//任务状态的回显
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		map.put("pStatus", pStatus);
		return new ModelAndView("proj/proj_task_list",map);
	}
	/**
	 * 查询当前登录人的任务列表
	 * 2018年1月16日 上午11:41:47
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("projTaskList.do")
	public Map<String, Object> projTaskList(HttpServletRequest request) throws Exception{
		//Map<String, Object> map = sysUserInfoBusiness.userByRoleType(ConstantMenu.OPS_SIX, request);//查询所有运维人员
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String pages = request.getParameter("pages");
		String taskName = request.getParameter("taskName");
		String idTaskHead = request.getParameter("idTaskHead");
		String milestoneTask = request.getParameter("milestoneTask");
		String projCode = request.getParameter("projCode");//项目编号
		String state = request.getParameter("state");
		//不同状态显示不同数据
		if(state.equals(ConstantMenu.TASK_STATE)){//我的项目跳转至任务列表
			paramMap.put("id_user", user_id);
			String pStatus = request.getParameter("pStatus");
			List<Integer> statusList = new ArrayList<Integer>();
			if("正常".equals(pStatus)){
				statusList.add(ConstantMenu.TASK_NORMAL);
			}else if("完成".equals(pStatus)){
				statusList.add(ConstantMenu.TASK_FINISH);
			}else if("已超期".equals(pStatus)){
				paramMap.put("pf", 1);
				statusList = null;
			}
			if ("全部".equals(pStatus)||"".equals(pStatus)||pStatus==null) {
				statusList = null;
			}
			paramMap.put("statusList", statusList);
		}else{//我的任务跳转至任务列表
			paramMap.put("id_user", null);
		}
		//查询所需的任务列表List
		String freezeTask = request.getParameter("freezeTask");
		if(!"".equals(freezeTask)){//如果freezeTask不为空，则证明我在我的任务界面，这时候要过滤掉冻结的项目下的任务
			paramMap.put("freezeTask", freezeTask);
		}
		if (taskName.equals("")) {
			taskName = null;
		}
		if("".equals(idTaskHead)){
			idTaskHead = null;
		}
		if("".equals(milestoneTask)){
			milestoneTask = null;
		}
		if ("".equals(projCode)) {
			projCode = null;
		}
		paramMap.put("taskName", taskName);
		paramMap.put("projCode", projCode);
		paramMap.put("idTaskHead", idTaskHead);
		paramMap.put("milestoneTask", milestoneTask);
		paramMap.put("state", state);
		paramMap.put("request", request);
		paramMap.put("pages", pages);
		Map<String, Object> map = projTaskBusiness.getProjTaskList(paramMap);
		map.put("state", state);
		return map;
	}
	
	/**
	 * 跳转任务添加页面
	 * 2018年1月16日 上午11:42:26
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@Token(remove=false, save = true)
	@RequestMapping("toAddProjTask.do")
	public ModelAndView toAddProjTask(HttpServletRequest request) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String projCode = request.getParameter("projCode");
		paramMap.put("projCode", projCode);
		Map<String, Object> map = projTaskBusiness.getToAddProjTaskList(paramMap);
		//添加子任务操作
		String taskId = request.getParameter("taskId");
		if (Utils.isNum(taskId)) {
			ProjectTask task = projTaskBusiness.selectTaskByKey(Integer.valueOf(taskId));
			map.put("task", task);
		}
		//参数
		map.put("projCode", projCode);//用于项目关联
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
		return new ModelAndView("proj/proj_task_add",map);
	}
	
	@ResponseBody
	@RequestMapping("/taskNameWhetherRepetition.do")
	public String taskNameWhetherRepetition(HttpServletRequest request){
		//通过父节点查询所有子节点，判断是否存在重名现象
		String projCode = request.getParameter("projCode");
		String parentId = request.getParameter("parentId");//上级任务
		if("".equals(parentId)){
			parentId = null;
		}
		String taskId = request.getParameter("taskId");//修改任务的时候去掉当前id的name
		if("".equals(taskId)){
			taskId = null;
		}
		String taskName = request.getParameter("taskName");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pId", parentId);
		paramMap.put("pCode", projCode);
		paramMap.put("taskId", taskId);
		List<String> taskStringList = projTaskBusiness.selectChildrenTask(paramMap);//返回任务名称集合
		if(taskStringList.size()>0 && taskStringList != null){
			for (int i = 0; i < taskStringList.size(); i++) {
				if(taskStringList.get(i).equals(taskName)){
					return "nameError";
				}
			}
		}
		return "nameSuccess";
	}
	/**
	 * 添加任务
	 * 2018年1月16日 上午11:42:26
	 * @李帅
	 * @param
	 */
	@Token(remove=true, save = false)
	@Transactional
	@ResponseBody
	@RequestMapping("addProjTask.do")
	public String addProjTask(HttpServletRequest request) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String parentId = request.getParameter("parentId");//上级任务
		ProjectTask projTask = new ProjectTask();
		String taskName = request.getParameter("taskName");
		if (taskName.equals("")) {
			taskName = null;
		}
		String projCode = request.getParameter("projCode");
		String idTaskHead = request.getParameter("idTaskHead");
		if (Utils.isNum(idTaskHead)) {
			projTask.setIdTaskHead(Integer.valueOf(idTaskHead));
		}
		String startTime = request.getParameter("startTime");
		if (!"".equals(startTime) && startTime != null) {
			projTask.setDateStart(sf.parse(startTime));
		}
		String endTime = request.getParameter("endTime");
		if (!"".equals(endTime) && endTime != null) {
			projTask.setDateEnd(sf.parse(endTime));
		}
		String milestoneTask = request.getParameter("milestoneTask");//是否是里程碑任务
		String taskDesc = request.getParameter("taskDesc");
		if (taskDesc.equals("")) {
			taskDesc = null;
		}
		String phaseShow = request.getParameter("phaseShow");
		if (phaseShow.equals("")) {
			phaseShow = null;
		}
		//获取上传路径参数
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String domainName = resource.getString("domainName");
		StringBuilder taskPath = new StringBuilder();
		if (Utils.isNum(parentId)) {
			Integer pId = Integer.valueOf(parentId);
			projTask.setParentId(pId);
			ProjectTask task = projTaskBusiness.selectTaskByKey(pId);//任务
			Integer pLevel = task.getLevel();
			projTask.setLevel(pLevel+1);
			String parentTaskPath = task.getTaskPath();//上级任务路径
			taskPath.append(parentTaskPath+"/" +taskName);
			projTask.setTaskPath(taskPath.toString());
		}else{
			projTask.setLevel(1);
			//如果是一级任务，添加任务路径为
			taskPath.append(domainName+projCode+"/"+taskName);
			projTask.setTaskPath(taskPath.toString());
		}
		//创建文件夹
		File file = new File(taskPath.toString());
		if(!file.exists() && !file.isDirectory()){
			file.mkdir();
		}
		String codeTask = "";
		codeTask = "TASK"+new SimpleDateFormat("yyMMddHHmmss").format(new Date());
		projTask.setCodeTask(codeTask);
		projTask.setProjCode(projCode);
		projTask.setNameTask(taskName);
		projTask.setTaskStatus(ConstantMenu.TASK_NORMAL);
		projTask.setMilestoneTask(Integer.valueOf(milestoneTask));
		projTask.setDateCreate(new Date());
		projTask.setTaskDesc(taskDesc);
		projTask.setPhaseShow(phaseShow);
		projTask.setTaskSpeed(Float.valueOf(0));
		projTask.setPf(0);
		//时间戳
		SimpleDateFormat sfVs = new SimpleDateFormat("yyMMddHHmmss");
		Date date = new Date();
		String code = sfVs.format(date);
		projTask.setVs(code);
		projTaskBusiness.addProjTask(projTask);
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/batchImportTask.do")
	@SuppressWarnings("all")
	public String batchImportTask(MultipartFile file,HttpServletRequest req) throws Exception{
		if(file == null)
			return "error";
		InputStream in = null;
		byte[] bytes = file.getBytes();
		in = file.getInputStream();
		in = new ByteArrayInputStream(bytes);
		//获取上传路径参数
		Map<String, Object> fileUploadOne = FilesUpload.fileUploadOne(file);
		String path = (String)fileUploadOne.get("path_to");
		String file_name = (String)fileUploadOne.get("file_name_from");
		String domainName = (String)fileUploadOne.get("domainName");
		String fileSuffix = file_name.substring(file_name.lastIndexOf("."), file_name.length());
		if (!".xlsx".equals(fileSuffix.toLowerCase()) && !".xls".equals(fileSuffix.toLowerCase())) {
			throw new BusinessException("500", "请选择正确的文件格式，进行项目的导入！");
		}
		File projFile = new File(path);
		in = new FileInputStream(projFile);
		Map<String, List<List<String>>> projectData = ExcelUtil.getbatchImportProjExcelData(in, file_name);
		fileUploadOne.put("projectData", projectData);
		//获取人员编号
		int user_id = (int) req.getSession().getAttribute("user_id");
		Map<String, Object> user = sysUserInfoBusiness.selectNameById(user_id);
		fileUploadOne.put("domainName", domainName);
		fileUploadOne.put("userCode", user.get("code"));
		fileUploadOne.put("userId", user_id);
		projTaskBusiness.importTask(fileUploadOne);
		projFile.delete();
		return "success";
	}
	
	/**
	 * 跳转任务修改页面
	 * 2018年1月16日 上午11:42:26
	 * @李帅
	 * @param
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("toUpdateProjTask.do")
	public ModelAndView toUpdateProjTask(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String projCode = request.getParameter("projCode");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		//获取跳转添加任务所需要的参数
		paramMap.put("projCode", projCode);//用来查询当前项目中所有的项目成员
		paramMap.put("idTask", id);
		Map<String, Object> map = projTaskBusiness.getToAddProjTaskList(paramMap);
		map.put("projCode", projCode);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		ProjectTask task = projTaskBusiness.selectTaskByKey(Integer.valueOf(id));//数据回显，并把上级任务的自身任务过滤
		map.put("task", task);
		return new ModelAndView("proj/proj_task_mod",map);
	}
	
	/**
	 * 修改任务
	 * 2018年1月16日 上午11:42:26
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("updateProjTask.do")
	public String updateProjTask(HttpServletRequest request) throws Exception{
		String id = request.getParameter("id");
		String vs = request.getParameter("vs");
		ProjectTask task = projTaskBusiness.selectTaskByKey(Integer.valueOf(id));
		String tVs = task.getVs();
		if(tVs != null && !"".equals(tVs)){
			if(!vs.equals(tVs)){
				return "unLike";
			}
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		ProjectTask projTask = new ProjectTask();
		String taskName = request.getParameter("taskName");
		if (taskName.equals("")) {
			taskName = null;
		}
		String idTaskHead = request.getParameter("idTaskHead");
		if (Utils.isNum(idTaskHead)) {
			projTask.setIdTaskHead(Integer.valueOf(idTaskHead));
		}
		String startTime = request.getParameter("startTime");
		if (!"".equals(startTime) && startTime != null) {
			projTask.setDateStart(sf.parse(startTime));
		}
		String endTime = request.getParameter("endTime");
		if (!"".equals(endTime) && endTime != null) {
			Date dateEnd = task.getDateEnd();
			String oldEndTime = sf.format(dateEnd);
			if(!oldEndTime.equals(endTime)){
				if(sf.parse(endTime).getTime() > new Date().getTime()){
					if(task.getPf() == 1){
						projTask.setPf(0);
					}
				}
			}
			projTask.setDateEnd(sf.parse(endTime));
		}
		String milestoneTask = request.getParameter("milestoneTask");//是否是里程碑任务
		String taskDesc = request.getParameter("taskDesc");
		if (taskDesc.equals("")) {
			taskDesc = null;
		}
		String phaseShow = request.getParameter("phaseShow");
		if (phaseShow.equals("")) {
			phaseShow = null;
		}
		String parentId = request.getParameter("parentId");//上级任务
		if (Utils.isNum(parentId)) {
			Integer pId = Integer.valueOf(parentId);
			projTask.setParentId(pId);
			ProjectTask ptask = projTaskBusiness.selectTaskByKey(pId);//任务
			Integer pLevel = ptask.getLevel();
			projTask.setLevel(pLevel+1);
		}else{
			projTask.setLevel(1);
		}
		
		projTask.setId(Integer.valueOf(id));
		projTask.setNameTask(taskName);
		projTask.setMilestoneTask(Integer.valueOf(milestoneTask));
		projTask.setTaskDesc(taskDesc);
		projTask.setPhaseShow(phaseShow);
		projTask.setDateUpdate(new Date());
		projTask.setTaskSpeed(null);
		//设置新的时间戳
		SimpleDateFormat newSf = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String newVs = newSf.format(date);
		projTask.setVs(newVs);
		projTaskBusiness.updateProjTask(projTask);
		return "success";
	}
	
	/**
	 * 是否可以删除任务
	 * 2018年1月16日 上午11:42:26
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("whetherProjTask.do")
	public String whetherProjTask(HttpServletRequest request) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String id = request.getParameter("id");
		paramMap.put("parentId", id);
		List<ProjectTask> projTaskList = projTaskBusiness.findProjTaskByUser(paramMap);//所有上级任务
		if (projTaskList.size()>0 && projTaskList!=null) {
			return "error";
		}
		projTaskBusiness.deleteProjTask(id.split(","));
		return "success";
	}
	/**
	 * 删除任务
	 * 2018年1月16日 上午11:42:26
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("deleteProjTask.do")
	public String deleteProjTask(HttpServletRequest request) throws Exception{
		String ids = request.getParameter("ids");
		String[] idArray = ids.split(",");
		projTaskBusiness.deleteProjTask(idArray);
		return "success";
	}
	
	/**
	 * 项目列表操作：任务列表操作
	 * 2018年2月28日 下午4:43:43
	 * @author 李帅
	 * @param request
	 * @return
	 * ModelAndView
	 * @throws Exception 
	 */
	@RequestMapping("toProjTaskPop.do")
	public ModelAndView toProjTaskPop(HttpServletRequest request) throws Exception{
		
		String projCode = request.getParameter("projCode");
		Map<String, Object> map = projTaskBusiness.getPopMessage(projCode);
		
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		String state = request.getParameter("state");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		map.put("projCode", projCode);
		map.put("state", state);
		return new ModelAndView("proj/proj_task_pop",map);
	}
	
	/**
	 * 任务处理
	 * 2018年2月28日 下午4:44:49
	 * @author 李帅
	 * @param request
	 * @return
	 * ModelAndView
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/toProjTaskDeal.do")
	public ModelAndView toProjTaskDeal(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		String cancel = request.getParameter("cancel");//项目列表-任务列表-任务处理-定义一个状态用来返回不同的页面
		String projCode = request.getParameter("projCode");
		String pStatus = request.getParameter("pStatus");
		String projStatus = request.getParameter("projStatus");//根据项目状态禁用操作，如果是冻结或者是完成不可以对文件进行上传，还有任务记录的填写
		map.put("projStatus", projStatus);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		map.put("cancel", cancel);
		map.put("projCode", projCode);
		String taskId = request.getParameter("taskId");
		String state = request.getParameter("state");
		ProjectTask task = projTaskBusiness.selectTaskByKey(Integer.valueOf(taskId));//项目名称
		float taskSpeed = task.getTaskSpeed()*100;
		task.setExtend3(String.valueOf((int)(taskSpeed))+"%");
		map.put("task", task);
		map.put("taskId", taskId);
		map.put("pStatus", pStatus);
		map.put("state", state);
		//用状态来判断，跳转不同的界面
		String view = "";
		if (ConstantMenu.MINE_TASK.equals(state) || ConstantMenu.PROJ_MINE_TASK.equals(state)) {
			view = "proj/mine_task_deal";
		}else{
			view = "proj/proj_task_deal";
		}
		return new ModelAndView(view,map);
	}
	
	@Transactional
	@ResponseBody
	@RequestMapping("/uploaddingTaskFile.do")
	public String uploaddingTaskFile(HttpServletRequest request) throws Exception{
		insertTaskFile(request);
		return "succ";
	}
	/**
	 * 上传任务文件
	 * 2018年2月9日 上午11:13:52
	 * @李帅
	 * @param
	 * @throws  
	 * @throws Exception 
	 */
	public String insertTaskFile(HttpServletRequest request) throws Exception{
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String file_name_from = "";
		String path_to = "";
		//创建一个通用的多部分解析器  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
		//判断 request 是否有文件上传,即多部分请求  
		if(multipartResolver.isMultipart(request)){  
			//如果有文件查询我需要上传的路径
			ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
			String domainName = resource.getString("domainName");
			String taskId = request.getParameter("taskId");
			ProjectTask task = projTaskBusiness.selectTaskByKey(Integer.valueOf(taskId));//任务
			String taskPath = task.getTaskPath();//任务文件夹
			if(taskPath == null){
				taskPath = domainName;
			}
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
						file_name_from = myFileName;//带后缀的文件名
						String file_name = file_name_from.split("\\.")[0];//文件名
						String newFileName = taskPath +"/"+file_name+"."+s;//新的文件名+时间code
						path_to = newFileName;
						File localFile = new File(path_to); 
						try {
							file.transferTo(localFile);  
							
						} catch (Exception e) {
							System.out.println(e);
						}
						//存入数据库
						ProjectFile projFile = new ProjectFile();
						SysFileInfo sysFileInf = new SysFileInfo();
						HttpSession session = request.getSession();
						int userId = (int) session.getAttribute("user_id");
						if(!"".equals(path_to)){
							Date date = new Date();
							String format = sf.format(date);
							int i=(int)(Math.random()*900)+100;
							String fileCode = "TASK"+format+i;
							sysFileInf.setCode(fileCode);
							sysFileInf.setIdFileType(6);
							sysFileInf.setPath(path_to);
							sysFileInf.setName(file_name);
							sysFileInf.setCreateDate(new Date());
							sysFileInf.setCreateUser(userId);
							sysFileInf.setRemark("任务下的文件");
							sysFileInfoBusiness.insertGeneralFile(sysFileInf);
							Integer id2 = sysFileInf.getId();
							projFile.setIdTask(Integer.valueOf(taskId));
							projFile.setIdFile(id2);
							projFileBusiness.addProjFile(projFile);
						}  
					}  
				}  
			}  
		}
		return null;
	}
	
	/**
	 * 查询任务文件集合
	 * 2018年3月2日 下午1:04:13
	 * @author 李帅
	 * @param request
	 * @return
	 * Map<String,Object>
	 * @throws BusinessException 
	 */
	@ResponseBody
	@RequestMapping("/taskFileList.do")
	public Map<String, Object> taskFileList(HttpServletRequest request) throws BusinessException{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String taskId = request.getParameter("taskId");
		paramMap.put("idTask", taskId);
		List<SysFileInfo> taskFileList = sysFileInfoBusiness.selectFileByProjCode(paramMap);
		List<Map<String, Object>> dealFilesByOne = new ArrayList<Map<String, Object>>();
		 if(taskFileList.size() != 0){
		 for (SysFileInfo sysFileInfo : taskFileList) {
				String path = sysFileInfo.getPath();
				String fileType = path.substring(path.lastIndexOf(".") + 1);
				Map<String, Object> fileObject = new HashMap<>();
				fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
				fileObject.put("fileId", sysFileInfo.getId());
				fileObject.put("projFileId", sysFileInfo.getExtend1());
				dealFilesByOne.add(fileObject);
			}
		 }	
		paramMap.put("files", dealFilesByOne);
		return paramMap;
	}
	
	
	/**
	 * 添加任务记录
	 * 2018年3月22日 上午11:55:10
	 * @author 李帅
	 * @param request
	 * @return
	 * String
	 */
	@ResponseBody
	@RequestMapping("insertTaskRecord.do")
	public String insertTaskRecord(HttpServletRequest request){
		int user_id = (int) request.getSession().getAttribute("user_id");
		TaskRecord projTaskRecord = new TaskRecord();
		String taskRecord = request.getParameter("taskRecord");
		String taskId = request.getParameter("taskId");
		if ("".equals(taskRecord)) {
			taskRecord = null;
		}
		projTaskRecord.setTaskRecord(taskRecord);
		projTaskRecord.setIdTask(Integer.valueOf(taskId));
		projTaskRecord.setCreateTime(new Date());
		projTaskRecord.setIdCreateUser(user_id);
		projTaskRecordBusiness.insert(projTaskRecord);
		return "succ";
	}
	
	/**
	 * 跳转任务记录div
	 * 2018年3月22日 上午11:55:23
	 * @author 李帅
	 * @param request
	 * @return
	 * ModelAndView
	 */
	@RequestMapping("/toProjTaskRecord.do")
	public ModelAndView toProjTaskRecord(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String taskId = request.getParameter("taskId");
		String taskName = request.getParameter("taskName");
		map.put("taskId", taskId);
		map.put("taskName", taskName);
		return new ModelAndView("proj/proj_task_record",map);
	}
	
	/**
	 * 查询任务记录集合
	 * 2018年3月9日 上午9:59:12
	 * @author 李帅
	 * @param request
	 * @return
	 * Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/getTaskRecordList.do")
	public Map<String,Object> getTaskRecordList(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String taskId = request.getParameter("taskId");
		List<TaskRecord> taskRecordList = sysFileInfoBusiness.getTaskRecordList(Integer.valueOf(taskId));
		map.put("taskRecordList", taskRecordList);
		return map;
	}
	
	/**
	 * 修改任务进度
	 * 2018年3月9日 下午1:00:38
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 * String
	 */
	@ResponseBody
	@RequestMapping("/updateTaskSpeed.do")
	public String updateTaskSpeed(HttpServletRequest request) throws Exception{
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String taskId = request.getParameter("taskId");
		String taskNum = request.getParameter("taskNum");
		int num = Integer.valueOf(taskNum);
		float taskSpeed = (float) (num*0.01);
		ProjectTask taskInfo = new ProjectTask();
		taskInfo.setId(Integer.valueOf(taskId));
		taskInfo.setTaskSpeed(taskSpeed);
		paramMap.put("taskInfo", taskInfo);
		projTaskBusiness.updateProjSpeed(paramMap);
		return "succ";
	}
	
	/**
	 * 任务记录导出
	 * 2018年3月13日 下午4:09:40
	 * @author 李帅
	 * @param request
	 * void
	 * @throws Exception 
	 */
	@RequestMapping("/taskRecordExport.do")
	public void taskRecordExport(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String taskId = request.getParameter("taskId");
		paramMap.put("taskId", taskId);
		paramMap.put("request", request);
		Map<String, Object> map = projTaskBusiness.downLoadTaskRecordExport(paramMap);
		ReadFile.downLoadFile(response, request, (String)map.get("filePath"), (String)map.get("fileName"));
	}
	
	/**
	 * 跳转至任务监控页
	 * 2018年3月22日 上午11:34:41
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 * ModelAndView
	 */
	@RequestMapping("toProjTaskControl.do")
	public ModelAndView toProjTaskControl(HttpServletRequest request) throws Exception{
		//查询所有负责人
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", ConstantMenu.OPS_SIX);
		Map<String, Object> map = sysUserInfoBusiness.userByRoleType(paramMap, request);
		String projCode = request.getParameter("projCode");
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("projCode", projCode);
		return new ModelAndView("proj/proj_task_control",map);
	}
	
	/**
	 * 获取任务监控List
	 * 2018年3月22日 上午11:35:19
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 * Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/getProjTaskControlList.do")
	public Map<String,Object> getProjControlList(HttpServletRequest request) throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");//用来格式化开始时间
		String taskName = request.getParameter("taskName");
		if("".equals(taskName)){
			taskName = null;
		}
		String idTaskHead = request.getParameter("idTaskHead");
		if(Utils.isNum(idTaskHead)){
			paramMap.put("idTaskHead", idTaskHead);
		}
		String startTime = request.getParameter("startTime");
		if(startTime.equals("")){
			paramMap.put("startTime", sf.format(new Date()));
		}else{
			paramMap.put("startTime", startTime);
		}
		String projCode = request.getParameter("projCode");//查询该项目下的所有任务
		paramMap.put("projCode", projCode);
		paramMap.put("request", request);
		paramMap.put("taskName", taskName);
		Map<String, Object> projTaskCotrolList = projTaskBusiness.findProjCotrolList(paramMap);
		return projTaskCotrolList;
	}
	
	/**
	 * 导出项目监控集合
	 * 2018年3月21日 下午2:11:58
	 * @author 李帅
	 * @param request
	 * void
	 */
	@ResponseBody
	@RequestMapping("projTaskControlExprot.do")
	public void projControlExprot(HttpServletRequest request,HttpServletResponse response)throws Exception{
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String taskName = request.getParameter("taskName");
		if("".equals(taskName)){
			taskName = null;
		}
		String idTaskHead = request.getParameter("idTaskHead");
		if(Utils.isNum(idTaskHead)){
			paramMap.put("idTaskHead", idTaskHead);
		}
		String startTime = request.getParameter("startTime");
		if(startTime.equals("")){
			paramMap.put("startTime", sf.format(new Date()));
		}else{
			paramMap.put("startTime", startTime);
		}
		String projCode = request.getParameter("projCode");
		paramMap.put("request", request);
		paramMap.put("taskName", taskName);
		paramMap.put("projCode", projCode);
		
		Map<String, Object> map = projTaskBusiness.exportProjTaskControl(paramMap);
		ReadFile.downLoadFile(response, request, (String)map.get("filePath"), (String)map.get("fileName"));
	}
	
	@ResponseBody
	@RequestMapping("/getProjBoardInsideTaskList.do")
	public List<ProjectTask> getProjBoardInsideTaskList(HttpServletRequest req) throws Exception{
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String projCode = req.getParameter("projCode");
		paramMap.put("projCode", projCode);
		return projTaskBusiness.findProjTaskByUser(paramMap);
	}
	/**
	 * 判断文件是否存在
	 * 2018年1月2日 下午1:53:46
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping( value = "/projectExcelExport")
    public void projectExcelExport(HttpServletRequest request,
           HttpServletResponse response) throws Exception{
		/*String str = Utils.getLocalName(request);
		str = str +"/template/PROJECTImport.xlsx";//Excel模板所在的路径。
        File f = new File(str);
        boolean exists = f.exists();
        if (!exists) {
			throw new BusinessException("", "系统发生异常 ，模板下载失败！");
		}
        str = "success";*/
		XSSFWorkbook xs=new XSSFWorkbook();
		/**
		 * 样式
		 */
		XSSFCellStyle style = xs.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		XSSFFont font = xs.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 11);// 设置字体大小
		style.setFont(font);
		style.setWrapText(true);
		//日期样式
		XSSFCellStyle dateStyle = xs.createCellStyle();
		dateStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		dateStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		dateStyle.setFont(font);
		dateStyle.setWrapText(true);
		CreationHelper createHelper = xs.getCreationHelper();
	        dateStyle.setDataFormat(
	        	       createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
	    /**
	     * 创建两个项目
	     */
	        String num = null;
		for (int i = 1; i < 3; i++) {
			
			if(i == 1)
				num = "一";
			if(i == 2)
				num = "二";
			XSSFSheet sheet=xs.createSheet("项目"+num);
			sheet.setDefaultColumnWidth(20);
			sheet.autoSizeColumn(0);
			sheet.setColumnWidth(0, 7000);
			sheet.setColumnWidth(1, 5120);
			sheet.setColumnWidth(2, 5120);
			sheet.setColumnWidth(3, 4000);
			sheet.setColumnWidth(4, 8000);
			sheet.setColumnWidth(5, 8000);
		   XSSFRow row=sheet.createRow((short)0);
		   XSSFCell cell = row.createCell((short)0);
		   cell.setCellValue("任务名称");   
		   cell.setCellStyle(style);
		   cell =  row.createCell((short)1);
		   cell.setCellValue("开始时间");
		   cell.setCellStyle(style);
		   cell = row.createCell((short)2);
		   cell.setCellValue("结束时间");
		   cell.setCellStyle(style);
		   cell = row.createCell((short)3);
		   cell.setCellValue("里程碑任务");
		   cell.setCellStyle(style);
		   cell = row.createCell((short)4);
		   cell.setCellValue("任务说明");
		   cell.setCellStyle(style);
		   cell = row.createCell((short)5);
		   cell.setCellValue("阶段成果/文档 ");
		   cell.setCellStyle(style);
	   
		   /**
		    * 创建两个个范例
		    */
		   for (int j = 1; j < 3; j++) {
			   ExcelUtil.setSheetCellSelect(sheet);
			   XSSFRow secondRow=sheet.createRow((short)j);
			   XSSFCell secondRowCell = secondRow.createCell((short)0);
			   secondRowCell.setCellStyle(style);
			   secondRowCell.setCellValue("任务"+j+"名称");
			   secondRowCell = secondRow.createCell((short)1);
			   secondRowCell.setCellStyle(dateStyle);
			   secondRowCell.setCellValue("2018-09-22");
			   secondRowCell = secondRow.createCell((short)2);
			   secondRowCell.setCellStyle(dateStyle);
			   secondRowCell.setCellValue("2018-10-12");
			   secondRowCell = secondRow.createCell((short)3);
			   secondRowCell.setCellStyle(style);
			   secondRowCell.setCellValue("是");
			   secondRowCell = secondRow.createCell((short)4);
			   secondRowCell.setCellStyle(style);
			   secondRowCell.setCellValue("任务"+j+"说明");
			   secondRowCell = secondRow.createCell((short)5);
			   secondRowCell.setCellStyle(style);
			   secondRowCell.setCellValue("任务"+j+"文档");
		   }
	   }
	   String    mimetype = "application/x-msdownload";
	   response.setContentType(mimetype);
	   response.setContentType("application/vnd.ms-excel;charset=utf-8");
	   response.setHeader("Content-Disposition", "attachment;filename="+ new String(EXPORT_TEMPLATE_EXCEL_NAME.getBytes(), "iso-8859-1"));//下载文件的名称
	   OutputStream out=response.getOutputStream();
	   xs.write(out);
	   out.flush();
	   out.close();
	}
	/*@RequestMapping( value = "/projectDownExcel")
	public void projectDownExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
//		String str = Utils.getLocalName(request);
//		str = str +"/template/PROJECTImport.xlsx";//Excel模板所在的路径。
//        File f = new File(str);
//        FilesUpload.downLoadExcelFile(f, response);
        
		
		*//**********************//*
//	        HSSFWorkbook wb = new HSSFWorkbook();
//	        HSSFSheet sheet = wb.createSheet("项目监控");
//	        
//	        byte[] bytes = wb.getBytes();
//	        FilesUpload.downLoadExcelByBytes(bytes, response);
		
		   XSSFWorkbook xs=new XSSFWorkbook();
		   XSSFSheet sheet=xs.createSheet("exprot data");
		   XSSFRow row=sheet.createRow((short)0);
		   row.createCell((short)0).setCellValue("序号");   
		   row.createCell((short)1).setCellValue("网点编码");
		   row.createCell((short)2).setCellValue("网点代码");
		   row.createCell((short)3).setCellValue("网点名称");
		   row.createCell((short)4).setCellValue("归属网格");
		   row.createCell((short)5).setCellValue("归属区县 ");
		   row.createCell((short)6).setCellValue("合作起始日期");
		   row.createCell((short)7).setCellValue("退出日期");
		   row.createCell((short)8).setCellValue("星级");
		  
		   row.createCell((short)9).setCellValue("专营");
		   row.createCell((short)10).setCellValue("郊区");
		   row.createCell((short)11).setCellValue("申请超量奖励");
		   row.createCell((short)12).setCellValue("是否沃店");
		   row.createCell((short)13).setCellValue("是否有效");
		  
		   String is_spec;
		   String is_city;
		   String is_rewa;
		   String is_wo;
		   String is_valied;
		  
		   String    mimetype = "application/x-msdownload";
		   response.setContentType(mimetype);
		       String downFileName = "dataFile.xlsx";
		   String inlineType = "attachment"; // 是否内联附件
		   response.setHeader("Content-Disposition", inlineType
		    + ";filename=\"" + downFileName + "\"");
		   OutputStream out=response.getOutputStream();
		   xs.write(out);
		   out.flush();
		   out.close();
        
	}*/
}
