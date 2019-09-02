package com.qkby.proj.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.constant.ConstantMenu;
import com.qkby.contract.dao.CapitalSoureDao;
import com.qkby.contract.entity.CapitalSource;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.proj.dao.ProjAssetsDao;
import com.qkby.proj.dao.ProjFileDao;
import com.qkby.proj.dao.ProjInfoDao;
import com.qkby.proj.dao.ProjMemberDao;
import com.qkby.proj.dao.ProjTaskDao;
import com.qkby.proj.dao.ProjTaskRecordDao;
import com.qkby.proj.dao.ProjTaskTemplateDao;
import com.qkby.proj.dao.ProjTemplateDao;
import com.qkby.proj.dao.ProjTypeDao;
import com.qkby.proj.entity.ProjectInformation;
import com.qkby.proj.entity.ProjectMembers;
import com.qkby.proj.entity.ProjectTask;
import com.qkby.proj.entity.TaskRecord;
import com.qkby.proj.entity.TaskTemplate;
import com.qkby.proj.entity.ProjectTemplate;
import com.qkby.proj.entity.ProjectType;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.dao.SysUserRoleInfoDao;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.sysmanage.entity.SysUserRoleInfo;
import com.qkby.utils.Utils;

@Service
public class ProjManageBusinessImpl implements ProjManageBusiness {
	@Resource
	private ProjInfoDao projInfoDao;
	@Resource
	private ProjMemberDao projMemberDao;
	@Resource
	private ProjAssetsDao projAssetsDao;
	@Resource
	private ProjFileDao projFileDao;
	@Resource
	private SysFileInfoDao sysFileInfoDao;
	@Resource
	private ProjTypeDao projTypeDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;
	@Resource
	LogOperInfoDao logOperInfoDao;
	@Resource
	ProjTaskDao projTaskDao;
	@Resource
	ProjTaskRecordDao projTaskRecordDao;
	@Resource
	ProjTemplateDao projTemplateDao;
	@Resource
	ProjTaskTemplateDao projTaskTemplateDao;
	@Resource
	SysUserRoleInfoDao sysUserRoleInfoDao;
	@Resource
	CapitalSoureDao capitalSourceDao;

	@Override
	public List<ProjectInformation> findProjInfoByUser(Map<String, Object> map) throws Exception {
		return projInfoDao.selectProjInfoByMap(map);
	}

	@Override
	@Transactional
	public int insertProjInfo(Map<String, Object> paramMap) throws Exception {
		ProjectInformation projInfo = (ProjectInformation) paramMap.get("projInfo");
		// 添加模板任务
		String templateId = (String) paramMap.get("templateId");
		if (!"".equals(templateId)) {// 如果模板名称不为空，继续添加模板任务
			SimpleDateFormat sf = new SimpleDateFormat("yyMMddHHmmss");
			ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
			String domainName = resource.getString("domainName"); 
			File file = null;
			//添加项目文件夹
			String projPath = domainName+projInfo.getProjCode();
			file = new File(projPath);
			if(!file.exists() && !file.isDirectory()){
				file.mkdir();
			}
			projInfo.setProjPath(projPath);
			// 1：查询所有模板任务
			List<TaskTemplate> templateTaskList = projTaskTemplateDao
					.selectTemplateTaskNameById(Integer.valueOf(templateId));
			for (TaskTemplate taskTemplate : templateTaskList) {
				ProjectTask projTask = new ProjectTask();
				projTask.setNameTask(taskTemplate.getTaskName());
				projTask.setProjCode(projInfo.getProjCode());
				String codeTask = "";
				codeTask = "TASK" + sf.format(new Date());
				projTask.setCodeTask(codeTask);
				projTask.setLevel(1);
				projTask.setIdTaskHead(projInfo.getIdProjManager());
				projTask.setDateStart(projInfo.getDateStart());
				projTask.setDateEnd(projInfo.getDateEnd());
				projTask.setDateCreate(new Date());
				projTask.setTaskDesc(taskTemplate.getPhaseRemark());
				projTask.setPhaseShow(taskTemplate.getPhaseShow());
				projTask.setMilestoneTask(1);
				projTask.setTaskSpeed(Float.valueOf(0));
				projTask.setPf(0);
				String taskPath = projPath+"/"+taskTemplate.getTaskName();
				file = new File(taskPath);
				if(!file.exists() && !file.isDirectory()){
					file.mkdir();
				}
				projTask.setTaskPath(taskPath);
				// 时间戳
				SimpleDateFormat sfVs = sf;
				Date date = new Date();
				String code = sfVs.format(date);
				projTask.setVs(code);
				projTask.setTaskStatus(ConstantMenu.TASK_NORMAL);
				projTaskDao.insertProjTask(projTask);
			}
		}
		projInfoDao.insertProjInfo(projInfo);
		return projInfo.getId();
	}

	@Transactional
	@Override
	public String updateProjInfo(Map<String, Object> paramMap) throws Exception {
		ProjectInformation projInfo = (ProjectInformation) paramMap.get("projInfo");
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		try {
			projInfoDao.updateProjInfo(projInfo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int countProjInfo(Map<String, Object> map) throws Exception {
		return projInfoDao.selectProjCountByMap(map);
	}

	@Transactional
	@Override
	public String deleteProjInfo(String projCode) throws Exception {
		/**
		 * 删除项目文件夹（包含的所有文件一并删除）
		 * 先查询文件夹路径
		 */
		ProjectInformation proj = projInfoDao.selectProjInfoById(projCode);
		String projPath = proj.getProjPath();
		Utils.delFolder(projPath);
		//删除数据库数据
		projInfoDao.delectProjByProjCode(projCode);
		
		return null;
	}

	@Override
	public ProjectInformation selectProjByPrimaryKey(String projCode) throws Exception {
		return projInfoDao.selectProjInfoById(projCode);
	}

	@Override
	public String updateProjStatus(Map<String, Object> paramMap) throws Exception {
		ProjectInformation projInfo = (ProjectInformation) paramMap.get("projInfo");
		List<ProjectTask> projTaskList = projTaskDao.selectProjTaskInfoByMap(paramMap);
		if (projTaskList.size() == 0 || projTaskList == null) {
			return "noTask";
		}
		projInfoDao.updateProjStatus(projInfo);

		return "success";
	}

	@Override
	public Map<String, Object> selectProjEveryStatusCount(Map<String, Object> paramMap) {
		String projControl = (String) paramMap.get("projControl");
		if (projControl.equals("0")) {
			return projInfoDao.selectEveryProjStatusCount(paramMap);
		}
		return projInfoDao.fromProjInfoSelectProjControlEveryStatusCount(paramMap);
	}

	@Override
	public Map<String, Object> getUpdateProjList(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 查询项目信息
		String projCode = (String) paramMap.get("projCode");
		ProjectInformation proj = projInfoDao.selectProjInfoById(projCode);
		map.put("proj", proj);
		map.put("projStatus", proj.getProjStatus());
		List<ProjectType> projTypeList = projTypeDao.selectProjTypeList();
		boolean ifHave = false;
		Integer projTypeId = proj.getProjType();
		for (ProjectType proType : projTypeList) {
			if (proType.getId() == projTypeId) {
				map.put("proTypeRemark", proType.getRemark());
				ifHave = true; // 如果查到的类型包含在类型表中，说明该类型没有被逻辑删除
			}
		}
		if (!ifHave) {
			ProjectType projType = new ProjectType();
			projType.setId(projTypeId);
			projType.setName(proj.getExtend2());
			projTypeList.add(projType);
		}
		map.put("projTypeList", projTypeList);
		//查询所有运维人员
		map.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> userByRoleType = sysUserInfoDao.userByRoleType(map);
		map.put("userByRoleType", userByRoleType);
		//资金来源
		List<CapitalSource> capitalSourceAll = capitalSourceDao.selectCapitalSourceAll();
		map.put("capitalSourceAll", capitalSourceAll);
		// 根据code查询项目下的文件
		List<SysFileInfo> projFileList = sysFileInfoDao.selectFileByProjCode(paramMap);
		List<Map<String, Object>> dealFilesByOne = new ArrayList<Map<String, Object>>();
		for (SysFileInfo sysFileInfo : projFileList) {
			String path = sysFileInfo.getPath();
			String fileType = path.substring(path.lastIndexOf(".") + 1);
			Map<String, Object> fileObject = new HashMap<>();
			fileObject.put("fileName", sysFileInfo.getName() + "." + fileType);
			fileObject.put("fileId", sysFileInfo.getId());
			fileObject.put("projFileId", sysFileInfo.getExtend1());
			dealFilesByOne.add(fileObject);
		}
		map.put("files", dealFilesByOne);
		// 查询项目成员
		List<ProjectMembers> memberList = projMemberDao.selectProjMembersByMap(paramMap);
		map.put("memberList", memberList);
		return map;
	}

	@Override
	public Map<String, Object> findProjCotrolList(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		String pages = (String) paramMap.get("pages");
		if (!"".equals(pages)) {
			Utils.pages(request, paramMap, map);
		} else {
			int totalRow = projInfoDao.selectProjControlCount(paramMap);
			Utils.paging(request, totalRow, paramMap, map);
		}
		try {
			List<ProjectInformation> projControlList = projInfoDao.selectProjControlInfo(paramMap);
			map.put("projControlList", projControlList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> exportProjControl(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sf4 = new SimpleDateFormat("yyyyMMddHHmmss");
		List<Map<String, Object>> projCotrolReportList = projTaskDao.selectTaskCotrolReportList(paramMap);
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("项目监控");
		sheet.setDefaultColumnWidth(20);
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, 7000);// 项目名称
		sheet.setColumnWidth(1, 4000);// 项目负责人
		sheet.setColumnWidth(2, 4000);// 项目类型
		sheet.setColumnWidth(3, 5120);// 项目起时间
		sheet.setColumnWidth(4, 5120);// 项目止时间
		sheet.setColumnWidth(5, 7000);// 任务名称(里程碑标记)
		sheet.setColumnWidth(6, 4000);// 任务负责人
		sheet.setColumnWidth(7, 5120);// 任务起时间
		sheet.setColumnWidth(8, 5120);// 任务止时间
		sheet.setColumnWidth(9, 5120);// 任务进度
		sheet.setColumnWidth(10, 5120);// 任务实际完成时间（年月日时分秒）
		sheet.setColumnWidth(11, 20480);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
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
		HSSFCell cell = row.createCell(0);// 第一行
		cell.setCellStyle(style);
		cell.setCellValue("项目名称");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("项目负责人");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("项目类型");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("项目起始时间");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("项目终止时间");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("任务名称");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("任务负责人");
		cell.setCellStyle(style);
		cell = row.createCell(7);
		cell.setCellValue("任务起始时间");
		cell.setCellStyle(style);
		cell = row.createCell(8);
		cell.setCellValue("任务终止时间");
		cell.setCellStyle(style);
		cell = row.createCell(9);
		cell.setCellValue("任务进度");
		cell.setCellStyle(style);
		cell = row.createCell(10);
		cell.setCellValue("任务实际完成时间");
		cell.setCellStyle(style);
		cell = row.createCell(11);
		cell.setCellValue("任务记录信息");
		cell.setCellStyle(style);

		// 样式二
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		HSSFFont font2 = wb.createFont();
		font2.setFontName("宋体");
		font2.setFontHeightInPoints((short) 11);// 设置字体大小
		style2.setFont(font2);
		style2.setWrapText(true);
		// 一、设置背景色：
		HSSFCellStyle style3 = wb.createCellStyle();
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		style3.setFillForegroundColor(HSSFColor.RED.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style3.setFillBackgroundColor(HSSFColor.RED.index);
		style3.setFont(font2);
		style3.setWrapText(true);
		// 加粗
		HSSFCellStyle style4 = wb.createCellStyle();
		style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		HSSFFont font3 = wb.createFont();
		font3.setFontName("宋体");
		font3.setFontHeightInPoints((short) 11);// 字号
		font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style4.setFont(font3);
		style4.setWrapText(true);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < projCotrolReportList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Map<String, Object> projCotrol = projCotrolReportList.get(i);
			// 第四步，创建单元格，并设置值
			HSSFCell cell0 = row.createCell(0);
			HSSFCell cell1 = row.createCell(1);
			HSSFCell cell2 = row.createCell(2);
			HSSFCell cell3 = row.createCell(3);
			HSSFCell cell4 = row.createCell(4);
			HSSFCell cell5 = row.createCell(5);
			HSSFCell cell6 = row.createCell(6);
			HSSFCell cell7 = row.createCell(7);
			HSSFCell cell8 = row.createCell(8);
			HSSFCell cell9 = row.createCell(9);
			HSSFCell cell10 = row.createCell(10);
			HSSFCell cell11 = row.createCell(11);
			int milestoneTask = (int) projCotrol.get("milestoneTask");
			HSSFCellStyle styleNormal = style2;
			if (milestoneTask == 1) {
				styleNormal = style3;
			}
			int pf = (int) projCotrol.get("pf");
			if (pf == 1) {
				styleNormal = style4;
			}
			cell0.setCellStyle(styleNormal);
			cell1.setCellStyle(styleNormal);
			cell2.setCellStyle(styleNormal);
			cell3.setCellStyle(styleNormal);
			cell4.setCellStyle(styleNormal);
			cell5.setCellStyle(styleNormal);
			cell6.setCellStyle(styleNormal);
			cell7.setCellStyle(styleNormal);
			cell8.setCellStyle(styleNormal);
			cell9.setCellStyle(styleNormal);
			cell10.setCellStyle(styleNormal);
			cell11.setCellStyle(styleNormal);
			cell0.setCellValue((String) projCotrol.get("projName"));
			cell1.setCellValue((String) projCotrol.get("projManagerName"));
			cell2.setCellValue((String) projCotrol.get("projTypeName"));
			cell3.setCellValue(sf2.format((Date) projCotrol.get("projStart")));
			cell4.setCellValue(sf2.format((Date) projCotrol.get("projEnd")));
			cell5.setCellValue((String) projCotrol.get("taskName"));
			cell6.setCellValue((String) projCotrol.get("taskHeadName"));
			cell7.setCellValue(sf2.format((Date) projCotrol.get("taskStart")));
			cell8.setCellValue(sf2.format((Date) projCotrol.get("taskEnd")));
			float taskSpeed = (Float) projCotrol.get("taskSpeed") * 100;
			cell9.setCellValue(String.valueOf((int) (taskSpeed)) + "%");
			Object object = projCotrol.get("taskReal");
			if (object == null) {
				cell10.setCellValue("");
			} else {

				cell10.setCellValue(sf2.format((Date) object));
			}
			// 遍历任务记录
			int taskId = (int) projCotrol.get("taskId");
			List<TaskRecord> taskRecord = projTaskRecordDao.selectTaskRecordList(taskId);
			String recordStr = "";
			for (int j = 0; j < taskRecord.size(); j++) {
				recordStr += "[" + taskRecord.get(j).getExtend1() + " " + sf.format(taskRecord.get(j).getCreateTime())
						+ " " + taskRecord.get(j).getTaskRecord() + "]";
			}
			cell11.setCellValue(recordStr);
		}
		// 第六步，将文件存到指定位置

		String fileName = "项目监控" + sf4.format(new Date());
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		String str = Utils.getLocalName(request);
		String filePath = str + "/projControlExcel/" + fileName + ".xls";// Excel模板所在的路径。
		File file = new File(str + "/projControlExcel/");
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

	@Override
	public Map<String, Object> getAddInform(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 运维人员
		List<SysUserInfo> userByRoleType = sysUserInfoDao.userByRoleType(paramMap);
		map.put("userByRoleType", userByRoleType);
		// 项目类型
		List<ProjectType> projTypeList = projTypeDao.selectProjTypeList();
		map.put("projTypeList", projTypeList);
		map.put("proTypeRemark", projTypeList.get(0).getRemark());
		//资金来源
		List<CapitalSource> capitalSourceAll = capitalSourceDao.selectCapitalSourceAll();
		map.put("capitalSourceAll", capitalSourceAll);
		// 查询所有模板
		List<ProjectTemplate> projTemplateList = projTemplateDao.selectTemplateInfo();
		map.put("projTemplateList", projTemplateList);
		return map;
	}

	@Override
	public List<ProjectInformation> selectProjControl(Map<String, Object> paramMap) throws Exception {
		return projInfoDao.fromProjInfoSelectControlInfoByMap(paramMap);
	}

	@Override
	public int countProjControl(Map<String, Object> paramMap) throws Exception {
		return projInfoDao.fromProjInfoSelectControlCountByMap(paramMap);
	}

	@Override
	public Map<String, Object> getProjListInform(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 如果不是项目管理员，只查询自己负责的项目，添加当前登陆人参数
		List<SysUserRoleInfo> whetherProjPerson = sysUserRoleInfoDao.selectByExample(paramMap);
		if (whetherProjPerson.size() == 0 || whetherProjPerson == null) {// 如果不是项目管理员，查询自己负责的项目
			paramMap.put("id_user", (String) paramMap.get("idUser"));
		}
		String projControl = (String) paramMap.get("projControl");
		// 计算列表分页参数
		int totalRow = 0;// 总条数
		if (projControl.equals("0")) {
			totalRow = projInfoDao.selectProjCountByMap(paramMap);
		} else {
			totalRow = projInfoDao.fromProjInfoSelectControlCountByMap(paramMap);
		}
		String pages = (String) paramMap.get("pages");
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		if (!"".equals(pages) && pages != null) {
			Utils.pages(request, paramMap, map);
			map.put("totalRow", totalRow);
		} else {
			Utils.paging(request, totalRow, paramMap, map);
		}
		// 列表
		List<ProjectInformation> projInfoList = null;
		Map<String, Object> statusCountMap = null;
		if (projControl.equals("0")) {
			// 项目列表
			projInfoList = projInfoDao.selectProjInfoByMap(paramMap);
			// 不同状态的数量
			statusCountMap = projInfoDao.selectEveryProjStatusCount(paramMap);
		} else {
			projInfoList = projInfoDao.fromProjInfoSelectControlInfoByMap(paramMap);
			statusCountMap = projInfoDao.fromProjInfoSelectProjControlEveryStatusCount(paramMap);
		}
		map.put("projInfoList", projInfoList);
		map.put("statusCountMap", statusCountMap);
		return map;
	}

	@Override
	public void updateProjPath(ProjectInformation projInfo) throws Exception {
		projInfoDao.updateProjInfo(projInfo);
	}

	@Override
	public void updateProjSpeedInfo(Map<String, Object> paramMap) throws Exception {
		ProjectInformation projInfo = (ProjectInformation) paramMap.get("projInfo");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		float projSpeed = projInfo.getProjSpeed();
		if (projSpeed == 1) {
			projInfo.setProjStatus(ConstantMenu.PROJ_FINISH);
			projInfo.setDateReal(sf.parse(sf.format(new Date())));
		} else {
			projInfo.setProjStatus(ConstantMenu.PROJ_NORMAL);
			projInfo.setDateReal(null);
		}

		projInfoDao.updateProjInfo(projInfo);
	}


	@Override
	public List<Map<String, Object>> selectProjectBoardProjectQuery() {
		return projInfoDao.selectProjectBoardProjectQuery();
	}

	@Override
	public List<Map<String, Object>> selectProjectBoardMilestoneTask(HttpServletRequest request) {
		String projCode = request.getParameter("projCode");
		return projInfoDao.selectProjectBoardMilestoneTask(projCode);
	}

	@Override
	public List<Map<String, Object>> selectProjectTypeStatus() {
		List<Map<String, Object>> selectProjectTypeStatus = projInfoDao.selectProjectTypeStatus();
		return selectProjectTypeStatus;
	}
	@Override
	public Map<String,Object> getProjBoardList(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String pages = (String) paramMap.get("pages");
		HttpServletRequest req = (HttpServletRequest) paramMap.get("req");
		int totalRow = projInfoDao.selectProjCountByMap(paramMap);
		if (!"".equals(pages) && pages != null) {
			Utils.pages(req, paramMap, map);
		} else {
			Utils.paging(req, totalRow, paramMap, map);
		}
		List<ProjectInformation> projInfoList = projInfoDao.selectProjInfoByMap(paramMap);
		map.put("projInfoList", projInfoList);
		return map ;
	}

	@Override
	public String changeProjBoardStatus(String addData,String delData) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(!"".equals(addData)){
			String[] addArray = addData.split(",");
			map.put("addArray", addArray);
			projInfoDao.updateProjectBoardIsYes(map);
		}
		if(!"".equals(delData)){
			String[] delArray = delData.split(",");
			map.put("delArray", delArray);
			projInfoDao.updateProjectBoardIsNo(map);
		}
		return null;
	}

	@Override
	public List<ProjectInformation> getAllProjectList(Map<String, Object> paramMap) {
		return projInfoDao.selectAllProjectInformation(paramMap);
	}

	@Override
	public List<Map<String, Object>> selectProjectBoardQueryTask(HttpServletRequest request) {
		String projCode = request.getParameter("projCode");
		return projInfoDao.selectProjectBoardQueryTask(projCode);
	}

}
