package com.qkby.proj.controller;

import java.io.File;
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
import com.qkby.proj.business.ProjFileBusiness;
import com.qkby.proj.business.ProjManageBusiness;
import com.qkby.proj.business.ProjMemberBusiness;
import com.qkby.proj.business.ProjTaskBusiness;
import com.qkby.proj.business.ProjTypeBusiness;
import com.qkby.proj.entity.ProjectFile;
import com.qkby.proj.entity.ProjectInformation;
import com.qkby.proj.entity.ProjectMembers;
import com.qkby.proj.entity.ProjectType;
import com.qkby.sysmanage.business.SysFileInfoBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.sysmanage.business.SysUserRoleInfoBusiness;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysUserRoleInfo;
import com.qkby.utils.ReadFile;
import com.qkby.utils.Token;
import com.qkby.utils.Utils;

@Controller
@Scope("prototype")
public class ProjManageController {
	@Resource
	private ProjManageBusiness projManageBusiness;
	@Resource
	private ProjTypeBusiness projTypeBusiness;
	@Resource
	private SysUserInfoBusiness sysUserInfoBusiness;
	@Resource
	private ProjMemberBusiness projMemberBusiness;
	@Resource
	private SysFileInfoBusiness sysFileInfoBusiness;
	@Resource
	private ProjFileBusiness projFileBusiness;
	@Resource
	private SysUserRoleInfoBusiness sysUserRoleInfoBusiness;
	@Resource
	ProjTaskBusiness projTaskBusiness;

	/**
	 * 跳转项目页面 2018年1月16日 上午11:33:52
	 * 
	 * @李帅
	 * @param
	 * @throws Exception
	 */
	@RequestMapping("/toProjPage.do")
	public ModelAndView toProjPage(HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String pages = request.getParameter("pages");
		if (pages != null && !"".equals(pages)) {
			String pageNumber = request.getParameter("pageNumber");
			String pageSize = request.getParameter("pageSize");
			String totalPage = request.getParameter("totalPage");
			String totalRow = request.getParameter("totalRow");
			String pStatus = request.getParameter("pStatus");
			map.put("pages", pages);
			map.put("pageNumber", pageNumber);
			map.put("pageSize", pageSize);
			map.put("totalPage", totalPage);
			map.put("totalRow", totalRow);
			map.put("pStatus", pStatus);
			String paramProjName = request.getParameter("paramProjName");
			String paramProjType = request.getParameter("paramProjType");
			String projControl = request.getParameter("projControl");
			map.put("paramProjName", paramProjName);
			map.put("paramProjType", paramProjType);
			map.put("projControl", projControl);
		}
		List<ProjectType> projTypeList = projTypeBusiness.projTypeList();
		map.put("projTypeList", projTypeList);
		return new ModelAndView("proj/proj_info_list", map);
	}

	/**
	 * 查询项目列表 2018年1月16日 上午11:33:52
	 * 
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("/findProjList.do")
	public Map<String, Object> findProjList(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("request", request);
		// 项目状态页签，根据不同状态，查询不同结果
		String pStatus = request.getParameter("pStatus");
		List<Integer> statusList = new ArrayList<Integer>();
		if ("未完成".equals(pStatus)) {
			statusList.add(ConstantMenu.PROJ_NORMAL);
		} else if ("冻结".equals(pStatus)) {
			statusList.add(ConstantMenu.PROJ_FREEZE);
		} else if ("完成".equals(pStatus)) {
			statusList.add(ConstantMenu.PROJ_FINISH);
		}else if("草稿".equals(pStatus)){
			statusList.add(ConstantMenu.PROJ_DRAFT);
		}
		if ("延期".equals(pStatus)) {
			paramMap.put("pf", 1);
			statusList.add(ConstantMenu.PROJ_NORMAL);
		}
		if ("全部".equals(pStatus) || pStatus == null || "".equals(pStatus)) {
			statusList = null;
		}
		paramMap.put("statusList", statusList);
		// 权限表查询参数，用来判断是否是管理员
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		paramMap.put("idUser", user_id);
		paramMap.put("idRole", ConstantMenu.OPS_PRO);
		// 放入查询参数，查询所需的项目List
		String pages = request.getParameter("pages");
		String projName = request.getParameter("projName");
		String projType = request.getParameter("projType");
		if (projName.equals("")) {
			projName = null;
		}
		if (projType.equals("")) {
			projType = null;
		}
		paramMap.put("projType", projType);
		paramMap.put("projName", projName);
		paramMap.put("pages", pages);
		// 判断是否在项目监控界面
		String projControl = request.getParameter("projControl");
		paramMap.put("projControl", projControl);
		Map<String, Object> map = projManageBusiness.getProjListInform(paramMap);
		map.put("pages", pages);
		return map;
	}

	/**
	 * 跳转项目添加页面 2018年1月16日 上午11:33:52
	 * 
	 * @李帅
	 * @param
	 */
	@Token(remove = false, save = true)
	@RequestMapping("/toAddProjPage.do")
	public ModelAndView toAddProjPage(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", ConstantMenu.OPS_SIX);
		// 查询跳转添加项目时需要的参数
		Map<String, Object> map = projManageBusiness.getAddInform(paramMap);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		String pStatus = request.getParameter("pStatus");// 用来判断，跳转的是新建，还是编辑页面
		String paramProjName = request.getParameter("paramProjName");
		String paramProjType = request.getParameter("paramProjType");
		map.put("pages", pages);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pStatus", pStatus);
		map.put("paramProjName", paramProjName);
		map.put("paramProjType", paramProjType);
		map.put("status", "add");
		return new ModelAndView("proj/proj_info_mod", map);
	}

	/**
	 * 添加项目 2018年1月16日 上午11:33:52
	 * 
	 * @李帅
	 * @param
	 */
	@Token(remove = true, save = false)
	@Transactional
	@ResponseBody
	@RequestMapping("/saveProjInfo.do")
	public Map<String, Object> saveProjInfo(HttpServletRequest requst, HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		// 项目编号：PROJ+人员编码+时间
		SimpleDateFormat sfCode = new SimpleDateFormat("yyMMddhhmmss");
		int user_id = (int) requst.getSession().getAttribute("user_id");
		Map<String, Object> user = sysUserInfoBusiness.selectNameById(user_id);
		String projCode = "PROJ" + user.get("code") + sfCode.format(new Date());
		String projName = (String) requst.getParameter("projName");
		// 设置参数
		String projType = (String) requst.getParameter("projType");
		String amtFrom = (String) requst.getParameter("amtFrom");
		String projAmt = (String) requst.getParameter("projAmt");
		String startTime = (String) requst.getParameter("startTime");
		String endTime = (String) requst.getParameter("endTime");
		String projManager = (String) requst.getParameter("projManager");
		String contCode = requst.getParameter("contCode");
		String projExplain = requst.getParameter("projExplain");
		// 项目模板
		String templateId = requst.getParameter("projTemplate");
		paramMap.put("templateId", templateId);
		// 项目成员
		String projMembers = requst.getParameter("projMembers");
		if (!"".equals(projMembers)) {
			String[] projMember = projMembers.split(",");
			ProjectMembers projMemberup = new ProjectMembers();
			projMemberup.setProjCode(projCode);
			for (int i = 0; i < projMember.length; i++) {
				projMemberup.setIdMember(Integer.valueOf(projMember[i]));
				projMemberBusiness.addProjMember(projMemberup);
			}
		}
		ProjectInformation projInfo = new ProjectInformation();
		//所属部门
		String idDept = requst.getParameter("idDept");
		if(Utils.isNum(idDept)){
			projInfo.setIdDept(Integer.valueOf(idDept));
		}
		//项目看板
		projInfo.setProjBoard(0);//默认不是项目看板内容
		if (projName.equals("")) {
			projName = null;
		}
		if (projAmt.equals("")) {
			projAmt = null;
		}
		if (contCode.equals("")) {
			contCode = null;
		}
		if (projExplain.equals("")) {
			projExplain = null;
		}
		if (Utils.isNum(projType)) {
			projInfo.setProjType(Integer.valueOf(projType));
		}
		if (!"".equals(startTime) && startTime != null) {
			projInfo.setDateStart(sf.parse(startTime));
		}
		if (!"".equals(endTime) && endTime != null) {
			projInfo.setDateEnd(sf.parse(endTime));
		}
		if (Utils.isNum(projManager)) {
			projInfo.setIdProjManager(Integer.valueOf(projManager));
		}
		projInfo.setProjCode(projCode);
		projInfo.setProjName(projName);
		projInfo.setAmtFrom(amtFrom);
		projInfo.setProjAmt(Double.valueOf(projAmt));
		projInfo.setDateCreate(new Date());
		projInfo.setIdUserCreate(user_id);
		projInfo.setProjStatus(ConstantMenu.PROJ_DRAFT);
		projInfo.setContCode(contCode);
		projInfo.setProjExplain(projExplain);
		projInfo.setPf(0);
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String domainName = resource.getString("domainName");
		String projPath = domainName + projCode;
		projInfo.setProjPath(projPath);
		File fileExsit = new File(projPath);
		if (!fileExsit.exists() && !fileExsit.isDirectory()) {
			fileExsit.mkdir();
		}
		// 时间戳
		Date date = new Date();
		String code = sfCode.format(date);
		projInfo.setVs(code);
		try {
			// 执行新建项目的sql
			paramMap.put("requset", requst);
			paramMap.put("projInfo", projInfo);
			projManageBusiness.insertProjInfo(paramMap);
			// 上传文件
			insertProjFile(requst, response, projCode);
			//判断是否存在项目文件夹，没有的话创建，并录入数据库
		} catch (Exception e) {
			System.out.println(e);
		}
		map.put("succ", "succ");
		map.put("projCode", projCode);
		return map;
	}

	/**
	 * 跳转项目修改页面 2018年1月16日 上午11:33:52
	 * 
	 * @李帅
	 * @param
	 */
	@RequestMapping("/toUpdateProj.do")
	public ModelAndView toUpdateProj(HttpServletRequest request) throws NumberFormatException, Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String state = request.getParameter("state");// 判断点击来源是项目监控界面，还是我的项目界面。通过这个状态控制指定功能的使用
		String projCode = request.getParameter("projCode");
		// 根据项目编号查询指定项目的项目信息，进行回显
		paramMap.put("projCode", projCode);
		Map<String, Object> map = projManageBusiness.getUpdateProjList(paramMap);
		// 将所需的分页，状态等参数传递到编辑页面
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		String paramProjName = request.getParameter("paramProjName");
		String paramProjType = request.getParameter("paramProjType");
		String projControl = request.getParameter("projControl");
		String pStatus = request.getParameter("pStatus");
		map.put("paramProjName", paramProjName);
		map.put("paramProjType", paramProjType);
		map.put("projControl", projControl);
		map.put("pages", pages);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("status", "mod");
		map.put("state", state);
		map.put("pStatus", pStatus);

		return new ModelAndView("proj/proj_info_mod", map);
	}

	/**
	 * 修改项目 2018年1月16日 上午11:33:52
	 * 
	 * @李帅
	 * @param
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/updateProj.do")
	public String updateProj(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		// 判断时间戳是否已经被修改
		String vs = request.getParameter("vs");
		String projCode = request.getParameter("projCode");
		ProjectInformation proj = projManageBusiness.selectProjByPrimaryKey(projCode);
		String dataSourceVs = proj.getVs();
		if (dataSourceVs != null && !"".equals(dataSourceVs)) {
			if (!vs.equals(dataSourceVs)) {
				return "unLike";// 与数据库中的数据不一致，说明已被别人更改，需要刷新页面
			}
		}
		// 然后修改项目
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		int user_id = (int) request.getSession().getAttribute("user_id");
		Integer id = Integer.valueOf(request.getParameter("id"));
		String projName = request.getParameter("projName");
		String projType = request.getParameter("projType");
		String amtFrom = request.getParameter("amtFrom");
		String projAmt = request.getParameter("projAmt");
		String startTime = request.getParameter("startTime");
		String projManager = request.getParameter("projManager");
		String contCode = request.getParameter("contCode");
		String projExplain = request.getParameter("projExplain");
		String idDept = request.getParameter("idDept");
		// 修改项目成员
		String projMember = (String) request.getParameter("projMembers");// 项目成员
		paramMap.put("projCode", projCode);
		paramMap.put("projMember", projMember);
		projMemberBusiness.updateProjMember(paramMap);

		// 项目文件：新增文件
		String fileProjCode = request.getParameter("projCode");
		insertProjFile(request, response, fileProjCode);
		// 项目文件：删除文件
		Map<String, Object> fileParamMap = new HashMap<String, Object>();
		String fileProjIds = request.getParameter("fileProjIds");
		String fileIds = request.getParameter("fileIds");
		if (!"".equals(fileIds) && fileIds != null) {
			String[] fileIdsArray = fileIds.split(",");
			fileParamMap.put("id", fileIdsArray);
			//删除本地文件
			List<Integer> deleteFileIdList =  new ArrayList<Integer>();
			for (int i = 0; i < fileIdsArray.length; i++) {
				Integer fileId = Integer.valueOf(fileIdsArray[i]).intValue();
				deleteFileIdList.add(fileId);
			}
			sysFileInfoBusiness.deleteLocalFile(deleteFileIdList);
			//删除sys_file表中的文件信息
			sysFileInfoBusiness.deleteProjFiles(fileParamMap);
			if (!"".equals(fileProjIds) && fileProjIds != null) {
				String[] fileProjIdArray = fileProjIds.split(",");
				for (int i = 0; i < fileProjIdArray.length; i++) {
					//删除proj_file中间表中的数据
					projFileBusiness.deleteProjFile(Integer.valueOf(fileProjIdArray[i]));
				}
				
			}
		}

		// 设置修改参数
		ProjectInformation projInfo = new ProjectInformation();
		if(Utils.isNum(idDept)){
			projInfo.setIdDept(Integer.valueOf(idDept));
		}
		if (projCode.equals("")) {
			projCode = null;
		}
		if (projName.equals("")) {
			projName = null;
		}
		if (projAmt.equals("")) {
			projAmt = null;
		}
		if (contCode.equals("")) {
			contCode = null;
		}
		if (projExplain.equals("")) {
			projExplain = null;
		}
		if (Utils.isNum(projType)) {
			projInfo.setProjType(Integer.valueOf(projType));
		}
		if (!"".equals(startTime) && startTime != null) {
			projInfo.setDateStart(sf.parse(startTime));
		}
		// 判断结束时间是否被修改，如果修改的时间大于当前时间，将延期字段修改为正常
		String endTime = request.getParameter("endTime");
		if (!"".equals(endTime) && endTime != null) {
			Date dateEnd = proj.getDateEnd();
			if(dateEnd != null && !"".equals(dateEnd)){
				String oldEndTime = sf.format(dateEnd);
				if (!oldEndTime.equals(endTime)) {
					if (sf.parse(endTime).getTime() > new Date().getTime()) {
						if (proj.getPf() == 1) {
							projInfo.setPf(0);
						}
					}
				}
				projInfo.setDateEnd(sf.parse(endTime));
			}
		}
		if (Utils.isNum(projManager)) {
			projInfo.setIdProjManager(Integer.valueOf(projManager));
		}
		projInfo.setProjCode(projCode);
		projInfo.setProjName(projName);
		projInfo.setAmtFrom(amtFrom);
		projInfo.setProjAmt(Double.valueOf(projAmt));
		projInfo.setDateUpdate(new Date());
		projInfo.setId(id);
		projInfo.setIdUserUpdate(user_id);
		projInfo.setContCode(contCode);
		projInfo.setProjExplain(projExplain);
		// 设置一个新的时间戳
		SimpleDateFormat sfVs = new SimpleDateFormat("yyyyMMddHHmmss");
		Date date = new Date();
		String newVs = sfVs.format(date);
		projInfo.setVs(newVs);
		paramMap.put("projInfo", projInfo);
		paramMap.put("request", request);
		projManageBusiness.updateProjInfo(paramMap);
		return "succ";
	}

	/**
	 * 删除项目 2018年1月16日 上午11:33:52
	 * 
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("deleteProjInfo.do")
	public String deleteProjInfo(HttpServletRequest request) throws Exception {
		String projCode = request.getParameter("projCode");
		projManageBusiness.deleteProjInfo(projCode);
		return "succ";
	}

	/**
	 * 上传项目文件 2018年2月9日 上午11:13:52
	 * @param projId 
	 * 
	 * @李帅
	 * @param
	 * @throws @throws
	 *             Exception
	 */
	public String insertProjFile(HttpServletRequest request, HttpServletResponse response, String fileProjCode)
			throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String file_name_from = "";
		String path_to = "";
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String domainName = resource.getString("domainName");
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			String path = domainName + fileProjCode;//项目文件夹
			File pFilePath = new File(path);//项目路径
			if(!pFilePath.exists() && !pFilePath.isDirectory()){//如果没有项目文件夹，直接存到默认的配置路径下
				path = domainName;
			}
			while (iter.hasNext()) {
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					String s = myFileName.substring(myFileName.indexOf(".") + 1);
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						file_name_from = myFileName;
						String file_name = file_name_from.split("\\.")[0];
						String newFileName = file_name + sf.format(new Date()) + "." + s;
						path = path + "/" + newFileName;
						path_to = path;
						File localFile = new File(path);
						file.transferTo(localFile);
						// 存入数据库
						ProjectFile projFile = new ProjectFile();
						SysFileInfo sysFileInf = new SysFileInfo();
						HttpSession session = request.getSession();
						int userId = (int) session.getAttribute("user_id");
						if (!"".equals(path_to)) {
							Date date = new Date();
							String format = sf.format(date);
							int i = (int) (Math.random() * 900) + 100;
							String fileCode = "PROJ" + format + i;
							sysFileInf.setCode(fileCode);
							sysFileInf.setIdFileType(5);
							sysFileInf.setPath(path_to);
							sysFileInf.setName(file_name);
							sysFileInf.setCreateDate(new Date());
							sysFileInf.setCreateUser(userId);
							sysFileInf.setRemark("项目下的文件");
							sysFileInfoBusiness.insertGeneralFile(sysFileInf);
							Integer id2 = sysFileInf.getId();
							projFile.setIdFile(id2);
							projFile.setProjCode(fileProjCode);
							projFileBusiness.addProjFile(projFile);
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * 项目操作：冻结、完成等状态的变更 2018年2月26日 下午2:00:24
	 * 
	 * @author 李帅
	 * @param req
	 * @return String
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/updateProjStatus.do")
	public String updateProjStatus(HttpServletRequest req) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String projId = req.getParameter("projId");
		String projStatus = req.getParameter("projStatus");
		String projCode = req.getParameter("projCode");
		ProjectInformation projInfo = new ProjectInformation();
		projInfo.setId(Integer.valueOf(projId));
		projInfo.setProjStatus(Integer.valueOf(projStatus));
		paramMap.put("projInfo", projInfo);
		paramMap.put("projCode", projCode);
		String str = projManageBusiness.updateProjStatus(paramMap);
		return str;
	}

	/**
	 * 调整项目进度 2018年3月9日 下午1:00:38
	 * 
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 *             String
	 */
	@ResponseBody
	@RequestMapping("/updateProjSpeed.do")
	public String updateProjSpeed(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String projId = request.getParameter("projId");
		String projNum = request.getParameter("projNum");
		int num = Integer.valueOf(projNum);
		float projSpeed = (float) (num * 0.01);
		ProjectInformation projInfo = new ProjectInformation();
		projInfo.setId(Integer.valueOf(projId));
		projInfo.setProjSpeed(projSpeed);
		paramMap.put("projInfo", projInfo);
		paramMap.put("request", request);
		projManageBusiness.updateProjSpeedInfo(paramMap);
		return "succ";
	}

	/**
	 * 跳转至项目监控页 2018年3月22日 上午11:34:41
	 * 
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 *             ModelAndView
	 */
	@RequestMapping("toProjControl.do")
	public ModelAndView toProjControl(HttpServletRequest request) throws Exception {
		// 查询所有负责人
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("role_id", ConstantMenu.OPS_SIX);
		Map<String, Object> map = sysUserInfoBusiness.userByRoleType(paramMap, request);
		String pages = request.getParameter("pages");
		if (!"".equals(pages)) {
			String pageNumber = request.getParameter("pageNumber");
			String pageSize = request.getParameter("pageSize");
			String totalPage = request.getParameter("totalPage");
			String totalRow = request.getParameter("totalRow");
			map.put("pageNumber", pageNumber);
			map.put("pageSize", pageSize);
			map.put("totalPage", totalPage);
			map.put("totalRow", totalRow);
			map.put("pages", pages);
		}
		return new ModelAndView("proj/proj_info_control", map);
	}

	/**
	 * 获取项目监控List 2018年3月22日 上午11:35:19
	 * 
	 * @author 李帅
	 * @param request
	 * @return
	 * @throws Exception
	 *             Map<String,Object>
	 */
	@ResponseBody
	@RequestMapping("/getProjControlList.do")
	public Map<String, Object> getProjControlList(HttpServletRequest request) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");// 用来格式化开始时间
		String projName = request.getParameter("projName");
		String pages = request.getParameter("pages");
		if ("".equals(projName)) {
			projName = null;
		}
		String idManager = request.getParameter("idManager");
		if (Utils.isNum(idManager)) {
			paramMap.put("idManager", idManager);
		}
		String startTime = request.getParameter("startTime");
		if (startTime.equals("")) {
			paramMap.put("startTime", sf.format(new Date()));
		} else {
			paramMap.put("startTime", startTime);
		}
		paramMap.put("request", request);
		paramMap.put("projName", projName);
		paramMap.put("pages", pages);
		Map<String, Object> projCotrolList = projManageBusiness.findProjCotrolList(paramMap);
		return projCotrolList;
	}

	/**
	 * 导出项目监控集合 2018年3月21日 下午2:11:58
	 * 
	 * @author 李帅
	 * @param request
	 *            void
	 */
	@ResponseBody
	@RequestMapping("projControlExprot.do")
	public void projControlExprot(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		String projName = request.getParameter("projName");
		if ("".equals(projName)) {
			projName = null;
		}
		String idManager = request.getParameter("idManager");
		if (Utils.isNum(idManager)) {
			paramMap.put("idManager", idManager);
		}
		String startTime = request.getParameter("startTime");
		if (startTime.equals("")) {
			paramMap.put("startTime", sf.format(new Date()));
		} else {
			paramMap.put("startTime", startTime);
		}
		paramMap.put("request", request);
		paramMap.put("projName", projName);

		Map<String, Object> map = projManageBusiness.exportProjControl(paramMap);
		ReadFile.downLoadFile(response, request, (String) map.get("filePath"), (String) map.get("fileName"));
	}

	/**
	 * 跳转项目配置界面 2018年3月28日 下午2:45:00
	 * 
	 * @李帅
	 * @param
	 */
	@RequestMapping("toProjConfig.do")
	public String toProjConfig() {

		return "proj/proj_info_config";
	}
	
	/**
	 * 
	 * @Description (项目看板页面的项目查询 )
	 * @return
	 */
	@RequestMapping("/selectProjectBoardProjectQuery.do")
	@ResponseBody
	public List<Map<String,Object>> selectProjectBoardProjectQuery(){
		 List<Map<String, Object>> selectProjectBoardProjectQuery = projManageBusiness.selectProjectBoardProjectQuery();
		 return selectProjectBoardProjectQuery;
	}
	/**
	 * 
	 * @Description (项目看板根据项目查询对应的任务)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectProjectBoardQueryTask.do")
	@ResponseBody
	public List<Map<String,Object>> selectProjectBoardQueryTask(HttpServletRequest request){
		List<Map<String, Object>> selectProjectBoardQueryTask = projManageBusiness.selectProjectBoardQueryTask(request);
		return  selectProjectBoardQueryTask;
	}
	/**
	 * 
	 * @Description (项目看板里程碑任务)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectProjectBoardMilestoneTask.do")
	@ResponseBody
	public List<Map<String,Object>> selectProjectBoardMilestoneTask(HttpServletRequest request){
		return projManageBusiness.selectProjectBoardMilestoneTask(request);
	}
	/**
	 * 
	 * @Description (查询各个项目类型的占比)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectProjectTypeStatus.do")
	@ResponseBody
	public Map<String,Integer> selectProjectTypeStatus(){
//		Map<String,List<Map<String,Object>>> map  = new HashMap<String,List<Map<String,Object>>>();
		Map<String,Integer> map  = new HashMap<>();
		List<Map<String, Object>> selectProjectTypeStatus = projManageBusiness.selectProjectTypeStatus();
		 for (Map<String, Object> map2 : selectProjectTypeStatus) {
			 String projTypeName = (String)map2.get("projTypeName");
			if(!map.containsKey(projTypeName)){
				map.put(projTypeName,new Integer(0));
			}
		        map.put(projTypeName,map.get(projTypeName).intValue()+1);
	    }
	return map;
	}
	/**
	 * 
	 * @Description (查询资金类型中各项目类型的数)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectProjectCapitalTypeCount.do")
	@ResponseBody
	public Map<String,Object> selectProjectCapitalTypeCount(){
//		Map<String,List<Map<String,Object>>> map  = new HashMap<String,List<Map<String,Object>>>();
		Map<String,List<Map<String,Object>>> map  = new HashMap<>();
		Map<String,Object> pMap = new HashMap<String,Object>();
		List<Map<String, Object>> selectProjectTypeStatus = projManageBusiness.selectProjectTypeStatus();
		 
		for (Map<String, Object> map2 : selectProjectTypeStatus) {
			Integer amtFrom = (Integer)map2.get("amtFrom");
			if(!map.containsKey(amtFrom+"")){
				List<Map<String,Object>> listMap = new ArrayList<>();
				listMap.add(map2);
				map.put(String.valueOf(amtFrom), listMap);
			}else{
				List<Map<String,Object>> listMap = new ArrayList<>();
				listMap.addAll(map.get(amtFrom+""));
				listMap.add(map2);
				map.put(String.valueOf(amtFrom), listMap);
			}
		}
		List<Map<String, Object>> selectProjTypeName = projTypeBusiness.selectProjTypeName();
		    Map<String,Integer> projTypeMap  = new HashMap<>();
			List<Map<String, Object>> list = map.get("1");
			for (Map<String, Object> map3 : list) {
				String projTypeName = (String)map3.get("projTypeName");
				if(!projTypeMap.containsKey(projTypeName)){
					projTypeMap.put(projTypeName,new Integer(0));
				}
				    projTypeMap.put(projTypeName,projTypeMap.get(projTypeName).intValue()+1);
			}
			Map<String,Integer> projTypeMaps  = new HashMap<>();
			List<Map<String, Object>> lists = map.get("2");
			for (Map<String, Object> map3 : lists) {
				String projTypeName = (String)map3.get("projTypeName");
				if(!projTypeMaps.containsKey(projTypeName)){
					projTypeMaps.put(projTypeName,new Integer(0));
				}
					projTypeMaps.put(projTypeName,projTypeMaps.get(projTypeName).intValue()+1);
				
			}
			for (Map<String, Object> map2 : selectProjTypeName) {
				String ptName = (String)map2.get("ptName");
				if(!projTypeMaps.containsKey(ptName)){
					projTypeMaps.put(ptName, new Integer(0));
				}
				if(!projTypeMap.containsKey(ptName)){
					projTypeMap.put(ptName, new Integer(0));
				}
			}
			pMap.put("2", projTypeMaps);
			pMap.put("1", projTypeMap);
	     return pMap;
	}
	/**
	 * 
	 * @Description (查询项目类型各资金项目的完成率)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectProjectTypeCount.do")
	@ResponseBody
	public List<Map<String,Object>> selectProjectTypeCount(){
//		Map<String,List<Map<String,Object>>> map  = new HashMap<String,List<Map<String,Object>>>();
		Map<String,List<Map<String,Object>>> map  = new HashMap<>();
		List<Map<String,Object>> percentageList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> selectProjectTypeStatus = projManageBusiness.selectProjectTypeStatus();
		 String mapKey = "";
		for (Map<String, Object> map2 : selectProjectTypeStatus) {
			String projTypeName = (String)map2.get("projTypeName");
			if(!map.containsKey(projTypeName)){
				mapKey+=projTypeName+",";
				List<Map<String,Object>> listMap = new ArrayList<>();
				listMap.add(map2);
				map.put(projTypeName, listMap);
			}else{
				List<Map<String,Object>> listMap = new ArrayList<>();
				listMap.addAll(map.get(projTypeName));
				listMap.add(map2);
				map.put(projTypeName, listMap);
			}
		}
		String[] split = mapKey.split(",");
		for (int i = 0; i < split.length; i++) {
			Map<String,Object> mapCount  = new HashMap<>();
			List<Map<String, Object>> list = map.get(split[i]);
			mapCount.put("projTypeName", split[i]);
			for (Map<String, Object> map2 : list) {
				Integer amtFrom = (Integer)map2.get("amtFrom");
				Integer projStatus = (Integer)map2.get("projStatus");
				if(!mapCount.containsKey(amtFrom+"")){
					mapCount.put(String.valueOf(amtFrom), new Integer(1));
					if(projStatus == 97){
						if(!mapCount.containsKey(projStatus+""+amtFrom)){
							mapCount.put(projStatus+""+amtFrom, new Integer(1));
						}else{
					mapCount.put(projStatus+""+amtFrom, ((Integer)mapCount.get(projStatus+""+amtFrom))+1);
						}
					}
				}else{
					mapCount.put(String.valueOf(amtFrom), ((Integer) mapCount.get(amtFrom+"")).intValue()+1);
					if(projStatus == 97){
						if(!mapCount.containsKey(projStatus+""+amtFrom)){
							mapCount.put(projStatus+""+amtFrom, new Integer(1));
						}else{
					    mapCount.put(projStatus+""+amtFrom, ((Integer)mapCount.get(projStatus+""+amtFrom)).intValue()+1);
						}
					}
				}
			}
			Map<String,Object> percentageMap = new HashMap<String,Object>();
			Integer integer1 = (Integer)mapCount.get(971+"");
			Integer integer2 = (Integer)mapCount.get(972+"");
			Integer amtFrom1 = (Integer)mapCount.get(1+"");
			Integer amtFrom2 = (Integer)mapCount.get(2+"");
			String projTypeName = (String)mapCount.get("projTypeName");
			Integer percentage1 = 0;
			if(integer1 != null && amtFrom1 != null){
			 percentage1 = (int)((float)integer1/amtFrom1*100);
			}
			Integer percentage2 = 0;
			if(integer2 != null && amtFrom2 != null){
			 percentage2 = (int)((float)integer2/amtFrom2*100);
			}
			percentageMap.put("projTypeName", projTypeName);
			percentageMap.put("amtFrom1", percentage1);
			percentageMap.put("amtFrom2", percentage2);
			percentageList.add(percentageMap);
		}
	     return percentageList;
	}
	
	@ResponseBody
	@RequestMapping("findProjectBoardList.do")
	public Map<String, Object> findProjectBoardList(HttpServletRequest req) throws Exception{
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String projType = req.getParameter("projType");//项目类型
		if("".equals(projType)){
			projType = null;
		}
		String amtFrom = req.getParameter("amtFrom");//资金来源
		if("".equals(amtFrom)){
			amtFrom = null;
		}
		String idDept = req.getParameter("idDept");//所属部门
		if("".equals(idDept)){
			idDept = null;
		}
		String projStatus = req.getParameter("projStatus");//项目状态
		boolean booStatus = true;
		if("".equals(projStatus)){
			projStatus = null;
			booStatus = false;
		}
		if(booStatus){
			if("1".equals(projStatus)){
				paramMap.put("pf", 1);//超期
			}else{
				List<Integer> statusList = new ArrayList<Integer>();
				if ("91".equals(projStatus)) {
					statusList.add(ConstantMenu.PROJ_DRAFT);
					statusList.add(ConstantMenu.PROJ_NORMAL);
				} else if ("96".equals(projStatus)) {
					statusList.add(ConstantMenu.PROJ_FREEZE);
				}
				paramMap.put("statusList", statusList);//冻结，正常
			}
		}
		String projName = req.getParameter("projName");
		if("".equals(projName)){
			projName = null;
		}
		String pages = req.getParameter("pages");
		paramMap.put("pages", pages);
		paramMap.put("req", req);
		paramMap.put("projType", projType);
		paramMap.put("amtFrom", amtFrom);
		paramMap.put("idDept", idDept);
		paramMap.put("projName", projName);
		//查询的结果集为项目看板（projBoard）
		paramMap.put("resultList", "projBoard");
		return projManageBusiness.getProjBoardList(paramMap);
	}
	
	/**
	 * 查询全部项目
	 */
	@ResponseBody
	@RequestMapping("findAllProjectList.do")
	public List<ProjectInformation> findAllProjectList(HttpServletRequest req){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		String projType = req.getParameter("projType");//项目类型
		if("".equals(projType)){
			projType = null;
		}
		String amtFrom = req.getParameter("amtFrom");//资金来源
		if("".equals(amtFrom)){
			amtFrom = null;
		}
		String idDept = req.getParameter("idDept");//所属部门
		if("".equals(idDept)){
			idDept = null;
		}
		String projStatus = req.getParameter("projStatus");//项目状态
		boolean booStatus = true;
		if("".equals(projStatus)){
			projStatus = null;
			booStatus = false;
		}
		if(booStatus){
			if("1".equals(projStatus)){
				paramMap.put("pf", 1);//超期
			}else{
				List<Integer> statusList = new ArrayList<Integer>();
				if ("91".equals(projStatus)) {
					statusList.add(ConstantMenu.PROJ_DRAFT);
					statusList.add(ConstantMenu.PROJ_NORMAL);
				} else if ("96".equals(projStatus)) {
					statusList.add(ConstantMenu.PROJ_FREEZE);
				}
				paramMap.put("statusList", statusList);//冻结，正常
			}
		}
		String projName = req.getParameter("projName");
		if("".equals(projName)){
			projName = null;
		}
		String pages = req.getParameter("pages");
		paramMap.put("pages", pages);
		paramMap.put("req", req);
		paramMap.put("projType", projType);
		paramMap.put("amtFrom", amtFrom);
		paramMap.put("idDept", idDept);
		paramMap.put("projName", projName);
		//查询的结果集为项目看板（projBoard）
		paramMap.put("resultList", "projBoard");
		List<ProjectInformation> allProjectList = projManageBusiness.getAllProjectList(paramMap);
		return allProjectList;
	}
}
