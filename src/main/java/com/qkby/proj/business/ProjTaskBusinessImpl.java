package com.qkby.proj.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.constant.ConstantMenu;
import com.qkby.proj.dao.ProjInfoDao;
import com.qkby.proj.dao.ProjMemberDao;
import com.qkby.proj.dao.ProjTaskDao;
import com.qkby.proj.dao.ProjTaskRecordDao;
import com.qkby.proj.entity.ProjectInformation;
import com.qkby.proj.entity.ProjectMembers;
import com.qkby.proj.entity.ProjectTask;
import com.qkby.proj.entity.TaskRecord;
import com.qkby.utils.ExcelUtil;
import com.qkby.utils.Utils;

@Service
public class ProjTaskBusinessImpl implements ProjTaskBusiness {

	@Resource
	ProjTaskDao projTaskDao;
	
	@Resource
	ProjInfoDao projInfoDao;
	
	@Resource
	ProjMemberDao projMemberDao;
	
	@Resource
	ProjTaskRecordDao projTaskRecordDao;
	
	@Override
	public int countProjTask(Map<String, Object> paramMap) throws Exception {
		return projTaskDao.selectProjTaskCountByMap(paramMap);
	}

	@Override
	public List<ProjectTask> findProjTaskByUser(Map<String, Object> paramMap) throws Exception {
		return projTaskDao.selectProjTaskInfoByMap(paramMap);
	}

	@Override
	public String addProjTask(ProjectTask projTask) throws Exception {
		projTaskDao.insertProjTask(projTask);
		return null;
	}

	@Override
	public String updateProjTask(ProjectTask projTask) throws Exception {
		projTaskDao.updateProjTask(projTask);
		return null;
	}

	@Transactional
	@Override
	public String deleteProjTask(String[] idArray) throws Exception {
		for (int i = 0; i < idArray.length; i++) {
			projTaskDao.delectProjTask(Integer.valueOf(idArray[i]));
		}
		return null;
	}

	@Override
	public ProjectTask selectTaskByKey(int id) throws Exception {
		return projTaskDao.selectProjTaskInfoById(id);
	}

	@Override
	public Map<String, Object> selectTaskStatusCount(Map<String, Object> paramMap) throws Exception {
		return projTaskDao.selectTaskStatusCount(paramMap);
	}

	@Override
	public Map<String, Object> getProjTaskList(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		String pages = (String) paramMap.get("pages");
		String state = (String) paramMap.get("state");
		if(!ConstantMenu.PROJ_STATE.equals(state)&&!ConstantMenu.PROJ_CONTROL.equals(state)){
			int totalRow = projTaskDao.selectProjTaskCountByMap(paramMap);
			if (!"".equals(pages) && pages != null) {
				Utils.pages(request, paramMap, map);
				map.put("totalRow", totalRow);
			} else {
				Utils.paging(request, totalRow, paramMap, map);
			}
			//查询项目成员
			List<ProjectMembers> memberList = projMemberDao.selectProjMembersByMap(paramMap);
			map.put("memberList", memberList);
		}
		List<ProjectTask> projTaskList = projTaskDao.selectProjTaskInfoByMap(paramMap);
		map.put("projTaskList", projTaskList);
		//查询不同状态下的任务数量 
		if(ConstantMenu.TASK_STATE.equals(state)){
			try {
				Map<String, Object> taskStatusCount = projTaskDao.selectTaskStatusCount(paramMap);
				map.put("taskStatusCount", taskStatusCount);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return map;
	}

	@Override
	public Map<String, Object> getToAddProjTaskList(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProjectTask> projTaskList = projTaskDao.selectProjTaskInfoByMap(paramMap);
		map.put("projTaskList", projTaskList);
		//查询项目成员
		List<ProjectMembers> memberList = projMemberDao.selectProjMembersByMap(paramMap);
		map.put("memberList", memberList);
		return map;
	}

	@Transactional
	@Override
	public String updateProjSpeed(Map<String, Object> paramMap) throws Exception {
		ProjectTask taskInfo = (ProjectTask) paramMap.get("taskInfo");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		float taskSpeed = taskInfo.getTaskSpeed();
		if (taskSpeed == 1) {
			taskInfo.setTaskStatus(ConstantMenu.TASK_FINISH);
			taskInfo.setDateReal(sf.parse(sf.format(new Date())));
		}else{
			taskInfo.setTaskStatus(ConstantMenu.TASK_NORMAL);
			taskInfo.setDateReal(null);
		}
		projTaskDao.updateProjTask(taskInfo);
		return null;
	}

	@Override
	public Map<String, Object> getPopMessage(String projCode) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		ProjectInformation proj = projInfoDao.selectProjInfoById(projCode);
		//查询项目成员
		Integer idProjManager = proj.getIdProjManager();
		paramMap.put("idProjManager", idProjManager);
		paramMap.put("projCode", projCode);
		List<ProjectMembers> memberList = projMemberDao.selectProjMembersByMap(paramMap);
		ProjectMembers projMember = new ProjectMembers();
		projMember.setIdMember(proj.getIdProjManager());
		projMember.setExtend1(proj.getExtend1());
		memberList.add(projMember);
		map.put("memberList", memberList);
		return map;
	}

	@Override
	public Map<String, Object> downLoadTaskRecordExport(Map<String, Object> paramMap) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		SimpleDateFormat sf4 = new SimpleDateFormat("yyyyMMddHHmmss");
		String taskId = (String) paramMap.get("taskId");
		List<TaskRecord> taskRecordList = projTaskRecordDao.selectTaskRecordList(Integer.valueOf(taskId));
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("任务记录");
		sheet.setDefaultColumnWidth(20);
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, 7000);
		sheet.setColumnWidth(1, 5120);
		sheet.setColumnWidth(2, 20480);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
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
		font.setFontHeightInPoints((short) 10);
		font.setColor(HSSFColor.RED.index);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);// 设置字体大小
		style.setFont(font);
		style.setWrapText(true); 
		HSSFCell cell = row.createCell(0);//第一行
		cell.setCellStyle(style);
		cell.setCellValue("时间");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("记录人");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("记录信息");
		cell.setCellStyle(style);
		

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
		for (int i = 0; i < taskRecordList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			TaskRecord taskRecord = taskRecordList.get(i);
			// 第四步，创建单元格，并设置值
			HSSFCell cell0 = row.createCell(0);
			HSSFCell cell1 = row.createCell(1);
			HSSFCell cell2 = row.createCell(2);
			cell0.setCellStyle(style2);
			cell1.setCellStyle(style2);
			cell2.setCellStyle(style2);
			cell0.setCellValue(sf.format(taskRecord.getCreateTime()));
			cell1.setCellValue(taskRecord.getExtend1());
			cell2.setCellValue(taskRecord.getTaskRecord());
		}
		// 第六步，将文件存到指定位置

		String fileName = "任务记录" + sf4.format(new Date());
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		String str = Utils.getLocalName(request);
		String filePath = str + "/taskRecordExcel/" + fileName + ".xls";// Excel模板所在的路径。
		File file = new File(str + "/taskRecordExcel/");
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
	public Map<String, Object> findProjCotrolList(Map<String, Object> paramMap)throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		HttpServletRequest request = (HttpServletRequest) paramMap.get("request");
		int totalRow = projTaskDao.countProjTaskControlList(paramMap);
		Utils.paging(request, totalRow, paramMap, map);
		try {
			List<Map<String, Object>> projTaskControlList = projTaskDao.selectProjTaskControlList(paramMap);
			map.put("projTaskControlList", projTaskControlList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> exportProjTaskControl(Map<String, Object> paramMap) throws Exception {
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
		
		sheet.setColumnWidth(0, 7000);//任务名称(里程碑标记)
		sheet.setColumnWidth(1, 4000);//任务负责人
		sheet.setColumnWidth(2, 5120);//任务起时间
		sheet.setColumnWidth(3, 5120);//任务止时间
		sheet.setColumnWidth(4, 5120);//任务进度
		sheet.setColumnWidth(5, 5120);//任务实际完成时间（年月日时分秒）
		sheet.setColumnWidth(6, 20480);
		
		// 第三步，在sheet中添加表头第1行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 1);
		// 第四步，创建单元格，并设置值表头 设置表头居中
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
		HSSFCell cell = row.createCell(1);//第二行
		cell = row.createCell(0);
		cell.setCellValue("任务名称");
		cell.setCellStyle(style);
		cell = row.createCell(1);
		cell.setCellValue("任务负责人");
		cell.setCellStyle(style);
		cell = row.createCell(2);
		cell.setCellValue("任务起始时间");
		cell.setCellStyle(style);
		cell = row.createCell(3);
		cell.setCellValue("任务终止时间");
		cell.setCellStyle(style);
		cell = row.createCell(4);
		cell.setCellValue("任务进度");
		cell.setCellStyle(style);
		cell = row.createCell(5);
		cell.setCellValue("任务实际完成时间");
		cell.setCellStyle(style);
		cell = row.createCell(6);
		cell.setCellValue("任务记录信息");
		cell.setCellStyle(style);
		
		

		//样式二
		HSSFCellStyle style2 = wb.createCellStyle();
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		HSSFFont font2 = wb.createFont();
		font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 11);// 设置字体大小
		style2.setFont(font2);
		style2.setWrapText(true); 
		
		//项目信息<!-- ------------------------------------------ --!>
		
		HSSFRow projRow = sheet.createRow((int) 0);//第一行
		HSSFCell projCell = projRow.createCell(0);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6)); //合并单元格
		String projStr = "";
		projStr += "项目名称："+(String)projCotrolReportList.get(0).get("projName")+"  "+"项目负责人："+(String)projCotrolReportList.get(0).get("projManagerName")
				+"  "+"项目类型："+(String)projCotrolReportList.get(0).get("projTypeName")+"  " +"项目开始时间"+sf2.format((Date)projCotrolReportList.get(0).get("projStart"))
				+"  "+"项目结束时间"+sf2.format((Date)projCotrolReportList.get(0).get("projEnd"));
		projCell.setCellValue(projStr);
		projCell.setCellStyle(style2);
		
		//一、设置背景色：
		HSSFCellStyle style3 = wb.createCellStyle();
		style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
		style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		style3.setFillForegroundColor(HSSFColor.RED.index);
		style3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); 
		style3.setFillBackgroundColor(HSSFColor.RED.index);
		style3.setFont(font2);
		style3.setWrapText(true); 
		//加粗
		HSSFCellStyle style4 = wb.createCellStyle();
		style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
		style4.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		HSSFFont font3  = wb.createFont();    
		font3.setFontName("宋体");
		font3.setFontHeightInPoints((short) 11);// 字号   
		font3.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style4.setFont(font3);   
		style4.setWrapText(true); 

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < projCotrolReportList.size(); i++) {
			row = sheet.createRow((int) i + 2);
			Map<String, Object> projCotrol = projCotrolReportList.get(i);
			// 第四步，创建单元格，并设置值
			HSSFCell cell0 = row.createCell(0);
			HSSFCell cell1 = row.createCell(1);
			HSSFCell cell2 = row.createCell(2);
			HSSFCell cell3 = row.createCell(3);
			HSSFCell cell4 = row.createCell(4);
			HSSFCell cell5 = row.createCell(5);
			HSSFCell cell6 = row.createCell(6);
			int milestoneTask = (int) projCotrol.get("milestoneTask");
			HSSFCellStyle styleNormal = style2;
			if(milestoneTask == 1){
				styleNormal = style3;
			}
			int pf = (int) projCotrol.get("pf");
			if(pf == 1){
				styleNormal = style4;
			}
			cell0.setCellStyle(styleNormal);
			cell1.setCellStyle(styleNormal);
			cell2.setCellStyle(styleNormal);
			cell3.setCellStyle(styleNormal);
			cell4.setCellStyle(styleNormal);
			cell5.setCellStyle(styleNormal);
			cell6.setCellStyle(styleNormal);
			cell0.setCellValue((String)projCotrol.get("taskName"));
			cell1.setCellValue((String)projCotrol.get("taskHeadName"));
			cell2.setCellValue(sf2.format((Date)projCotrol.get("taskStart")));
			cell3.setCellValue(sf2.format((Date)projCotrol.get("taskEnd")));
			float taskSpeed = (Float)projCotrol.get("taskSpeed")*100;
			cell4.setCellValue(String.valueOf((int)(taskSpeed))+"%");
			Object object = projCotrol.get("taskReal");
			if(object == null){
				cell5.setCellValue("");
			}else{
				
				cell5.setCellValue(sf2.format((Date)object));
			}
			//遍历任务记录
			int taskId = (int)projCotrol.get("taskId");
			List<TaskRecord> taskRecord = projTaskRecordDao.selectTaskRecordList(taskId);
			String recordStr = "";
			for (int j=0;j<taskRecord.size();j++) {
				recordStr += "["+taskRecord.get(j).getExtend1()+" "+sf.format(taskRecord.get(j).getCreateTime())+" "+taskRecord.get(j).getTaskRecord()+"]";
			}
			cell6.setCellValue(recordStr);
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
	public List<ProjectTask> getTaskWarnList(int user_id) throws Exception {
		return projTaskDao.selectTaskWarnList(user_id);
	}

	@Override
	public List<String> selectChildrenTask(Map<String, Object> paramMap) {
		return projTaskDao.selectChildrenTaskByParentId(paramMap);
	}

	@Override
	@SuppressWarnings("all")
	public void importTask(Map<String,Object> paramMap) throws Exception {
		Map<String, List<List<String>>> projectData = (Map<String, List<List<String>>>) paramMap.get("projectData");
		String userCode = (String) paramMap.get("userCode");
		int userId = (Integer) paramMap.get("userId");
		ProjectInformation ProjectInfo = null;
		ProjectTask ProjectTask = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sfCode = new SimpleDateFormat("yyMMddHHmmss");
		String domainName = (String) paramMap.get("domainName");
		String codeTask = null;
		String taskPath = null;
		String projCode = null;
		File file = null;
		int mapSize = projectData.size();
		for (Entry<String, List<List<String>>> entry : projectData.entrySet()) {
			String projName = entry.getKey();
			List<List<String>> taskList = entry.getValue();
			ProjectInfo = new ProjectInformation();
			projCode = "PROJ" + userCode + sfCode.format(new Date())+mapSize;//项目编号
			ProjectInfo.setProjName(projName);
			ProjectInfo.setProjCode(projCode);
			ProjectInfo.setIdUserCreate(userId);
			ProjectInfo.setDateCreate(new Date());
			ProjectInfo.setPf(0);
			ProjectInfo.setProjStatus(ConstantMenu.PROJ_DRAFT);
			//项目看板
			ProjectInfo.setProjBoard(0);//默认不是项目看板内容
			String projPath = domainName + projCode;
			ProjectInfo.setProjPath(projPath);
			File fileExsit = new File(projPath);
			if (!fileExsit.exists() && !fileExsit.isDirectory()) {
				fileExsit.mkdir();
			}
			// 时间戳
			String projVsCode = sfCode.format(new Date());
			ProjectInfo.setVs(projVsCode);
			projInfoDao.insertProjInfo(ProjectInfo);
			mapSize--;
			for (int j = 0; j < taskList.size(); j++) {//循环任务
				//任务实体
				ProjectTask = new ProjectTask();
				//项目编码
				ProjectTask.setProjCode(projCode);
				//任务编码
				codeTask = "TASK"+sfCode.format(new Date())+j;
				ProjectTask.setCodeTask(codeTask);
				//任务等级
				ProjectTask.setLevel(1);
				//任务状态
				ProjectTask.setTaskStatus(ConstantMenu.TASK_NORMAL);
				//任务创建时间
				ProjectTask.setDateCreate(new Date());
				//初始任务进度
				ProjectTask.setTaskSpeed(Float.valueOf(0));
				//任务超期状态
				ProjectTask.setPf(0);
				//时间戳
				Date date = new Date();
				String taskVsCode = sfCode.format(new Date());
				ProjectTask.setVs(taskVsCode);
				
				if(!"".equals(taskList.get(j).get(0)))
				ProjectTask.setNameTask(taskList.get(j).get(0));
				//添加任务文件存放路径并创建文件夹
				taskPath = domainName+projCode+"/"+taskList.get(j).get(0);
				ProjectTask.setTaskPath(taskPath.toString());
				//创建文件夹
				file = new File(taskPath);
				if(!file.exists() && !file.isDirectory()){
					file.mkdir();
				}
				//开始时间
				if(!"".equals(taskList.get(j).get(1)))
				ProjectTask.setDateStart(sf.parse(taskList.get(j).get(1)));
				//结束时间
				if(!"".equals(taskList.get(j).get(2)))
				ProjectTask.setDateEnd(sf.parse(taskList.get(j).get(2)));
				//里程碑任务
				if("是".equals(taskList.get(j).get(3))){
					ProjectTask.setMilestoneTask(1);
				}else{
					ProjectTask.setMilestoneTask(0);
				}
				//任务说明
				if(!"".equals(taskList.get(j).get(4)))
				ProjectTask.setTaskDesc(taskList.get(j).get(4));
				//阶段成果文档
				if(!"".equals(taskList.get(j).get(5)))
				ProjectTask.setPhaseShow(taskList.get(j).get(5));
				projTaskDao.insertProjTask(ProjectTask);
			}
		}
	}
	@Override
	public List<ProjectTask> selectProjTaskInfoByMap(Map<String, Object> map) throws Exception {
		return projTaskDao.selectProjTaskInfoByMap(map);
	}
}
