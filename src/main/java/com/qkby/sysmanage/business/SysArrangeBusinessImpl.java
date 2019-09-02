package com.qkby.sysmanage.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.dao.SysArrangeDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysArrange;
import com.qkby.sysmanage.entity.SysDutyInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Holiday;
import com.qkby.utils.ParamConfigInfosUtil;
import com.qkby.utils.ReadFile;
import com.qkby.utils.Utils;

@Service
public class SysArrangeBusinessImpl implements SysArrangeBusiness {
	@Resource
	SysArrangeDao sysArrangeDao;
	@Resource
	SysUserInfoDao sysUserInfoDao;

	@Override
	public Map<String, Object> selectArrangeList(Map<String, Object> pgMap, String pages, HttpServletRequest req)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!"".equals(pages)) {
			Utils.pages(req, pgMap, map);
		} else {
			int totalRow = sysArrangeDao.countByExample(pgMap);
			Utils.paging(req, totalRow, pgMap, map);
		}
		List<SysArrange> arrangeList = sysArrangeDao.selectByExample(pgMap);
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> operList = sysUserInfoDao.userByRoleType(pgMap);
		map.put("arrangeList", arrangeList);
		map.put("operList", operList);
		return map;
	}

	@Override
	public Map<String, Object> insertArrange(String duty_user, String duty_start_time, String duty_end_time,
			HttpServletRequest request,String timeScope) throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		String resultMessage = "success";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyyMMdd");//节假日判断，所需格式
		SimpleDateFormat sf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 获取起始时间至结束时间所有日期时间
		List<Date> dateList = Utils.getBetweenDates(sf.parse(duty_start_time), sf.parse(duty_end_time));
		List<String> dateStrList = new ArrayList<String>();
		List<SysArrange> arrangeAll = sysArrangeDao.selectArrangeAll();
		for (int i = 1; i < dateList.size(); i++) {
			String startTime = sf.format(dateList.get(i).getTime());
			for (SysArrange arrange : arrangeAll) {
				if (startTime.equals(sf.format(arrange.getDutyEndTime()))) {
					dateStrList.add(startTime);
				}
			}
		}
		if (dateStrList != null && dateStrList.size() > 0) {
			resultMessage = "error";
			resultMap.put("resultMessage", resultMessage);
			resultMap.put("errorMessage",
					dateStrList.get(0) + "至" + dateStrList.get(dateStrList.size() - 1) + "已有人值班,请重新选择日期!");
			return resultMap;
		}
		//获取配置文件中,四个值班的时间段
		Map<String, String> paramMap = ParamConfigInfosUtil.getUserPwdFileUreNum(request);
		String firstStartTime = paramMap.get("firstStartTime");
		String firstEndTime = paramMap.get("firstEndTime");
		String secondStartTime = paramMap.get("secondStartTime");
		String secondEndTime = paramMap.get("secondEndTime");
		String thirdStartTime = paramMap.get("thirdStartTime");
		String thirdEndTime = paramMap.get("thirdEndTime");
		String fourthStartTime = paramMap.get("fourthStartTime");
		String fourthEndTime = paramMap.get("fourthEndTime");
		//获取日期字符串，
		String dateStr = "";
		for (int i = 0; i < dateList.size(); i++) {
			dateStr += sf2.format(dateList.get(i).getTime()) + ",";
		}
		String[] dateArray = dateStr.split(",");
		Date startTime = new Date();
		Date endTime = new Date();
		String[] dutyUsers = duty_user.split(",");//值班人员
		for (int j = 0; j < dateArray.length; j++) {
			if (j < dateArray.length - 1) {
				SysArrange sysArrange = new SysArrange();
				String user = dutyUsers[j % dutyUsers.length];
				int id_user = Integer.valueOf(user);
				
				if (timeScope.equals("1")) {
					startTime = sf3.parse(sf.format(sf2.parse(dateArray[j])) + " " + firstStartTime);
					endTime = sf3.parse(sf.format(sf2.parse(dateArray[j + 1])) + " " + firstEndTime);
				} else if (timeScope.equals("2")) {
					startTime = sf3.parse(sf.format(sf2.parse(dateArray[j])) + " " + secondStartTime);
					endTime = sf3.parse(sf.format(sf2.parse(dateArray[j + 1])) + " " + secondEndTime);
				} else if (timeScope.equals("3")) {
					startTime = sf3.parse(sf.format(sf2.parse(dateArray[j])) + " " + thirdStartTime);
					endTime = sf3.parse(sf.format(sf2.parse(dateArray[j + 1])) + " " + thirdEndTime);
				} else if (timeScope.equals("4")) {
					startTime = sf3.parse(sf.format(sf2.parse(dateArray[j])) + " " + fourthStartTime);
					endTime = sf3.parse(sf.format(sf2.parse(dateArray[j + 1])) + " " + fourthEndTime);
				}
				sysArrange.setIdUser(id_user);
				sysArrange.setDutyStartTime(startTime);
				sysArrange.setDutyEndTime(endTime);
				int in = sysArrangeDao.insert(sysArrange);
				if (in == 0) {
					throw new BusinessException("", "新增失败，请联系管理员");
				}
			}
		}
		resultMap.put("resultMessage", resultMessage);
		return resultMap;
	}

	@Override
	public Map<String, Object> selectByPrimaryKey(int user_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		SysArrange arrange = sysArrangeDao.selectByPrimaryKey(user_id);
		map.put("role_id", ConstantMenu.OPS_SIX);
		List<SysUserInfo> operList = sysUserInfoDao.userByRoleType(map);
		map.put("arrange", arrange);
		map.put("operList", operList);
		return map;
	}

	@Override
	public void updateArrange(SysArrange arrange) throws Exception {
		sysArrangeDao.updateByPrimaryKeySelective(arrange);
	}

	@Override
	public Boolean selectArrangeByDate(String user_id, HttpServletRequest request) throws Exception {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowTime = new Date();
		pgMap.put("id_user", user_id);
		pgMap.put("now", sf.format(nowTime));
		List<SysArrange> arrangeList = sysArrangeDao.selectArrangeByDate(pgMap);
		if (arrangeList!=null && arrangeList.size()>0) {
			if (user_id == null) {//如果传过来的是null，说明是要跳转代理人授权界面，作用是将当前值班人过滤
				request.getSession().setAttribute("duty", arrangeList.get(0).getIdUser());//当前值班人
			}
			return true;
		} else {
			pgMap.put("id", user_id);
			pgMap.put("arrangeProxy", ConstantMenu.LOCK_ZERO);
			List<SysUserInfo> userList = sysUserInfoDao.selectByUserExample(pgMap);
			if (userList != null && userList.size() > 0) {
				return true;
			}
		}

		return false;
	}

	/**
	 * @throws BusinessException
	 * @throws Exception
	 * 
	 */
	@Override
	public void dutyExport(HttpServletResponse response, HttpServletRequest request) throws Exception {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sf3 = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat sf4 = new SimpleDateFormat("yyyyMMddHHmmss");
		Map<String, Object> pgMap = new HashMap<String, Object>();
		pgMap.put("now_time", Utils.getYesterday());
		List<SysArrange> arrangeList = sysArrangeDao.selectByExample(pgMap);
		if (arrangeList == null || arrangeList.size() == 0) {
			throw new BusinessException("500", "没有可以导出的数据！");
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("值班表一");
		sheet.setDefaultColumnWidth(20);
		sheet.autoSizeColumn(0);
		sheet.setColumnWidth(0, 5120);
		sheet.setColumnWidth(1, 5120);
		sheet.setColumnWidth(2, 5120);
		sheet.setColumnWidth(3, 5120);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		HSSFCell cell = row.createCell((short) 2);
		cell.setCellStyle(style);
		cell.setCellValue("开始时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("结束时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("值班人");
		cell.setCellStyle(style);
		cell = row.createCell((short) 0);
		cell.setCellValue("日期");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		for (int i = 0; i < arrangeList.size(); i++) {
			row = sheet.createRow((int) i + 1);
			SysArrange arrange = arrangeList.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(sf3.format(arrange.getDutyStartTime()));
			row.createCell((short) 1).setCellValue(arrange.getExtend1());
			row.createCell((short) 2).setCellValue(sf2.format(arrange.getDutyStartTime()));
			row.createCell((short) 3).setCellValue(sf2.format(arrange.getDutyEndTime()));
		}
		// 第六步，将文件存到指定位置

		String fileName = "值班表" + sf4.format(new Date());
		String str = Utils.getLocalName(request);
		String filePath = str + "/dutyExcel/" + fileName + ".xls";// Excel模板所在的路径。
		File file = new File(str + "/dutyExcel/");
		if (!file.exists() && !file.isDirectory()) {
			file.mkdir();
		}
		FileOutputStream fout = new FileOutputStream(filePath);
		wb.write(fout);
		fout.close();
		// 下载文件
		ReadFile.downLoadFile(response, request, filePath, fileName);
	}

	@Override
	public String deleteArrange(int dutyId) throws Exception {
		//删除
		String result = "success";
		int de = sysArrangeDao.deleteArrange(dutyId);
		if (de == 0) {
			result = "error";
		}
		return result;
	}

	@Override
	public void ifHaveData() throws Exception {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		pgMap.put("now_time", Utils.getYesterday());
		List<SysArrange> arrangeList = sysArrangeDao.selectByExample(pgMap);
		if (arrangeList == null || arrangeList.size() == 0) {
			throw new BusinessException("", "没有可以导出的数据！");
		}
	}
	
	public static void main(String[] args) {
		
	}

	@Override
	public List<SysArrange> selectDutyArrange() {
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		List<SysArrange> butys = new ArrayList<SysArrange>();
			List<SysArrange> dutys = sysArrangeDao.selectDutyArrange();
			if (dutys != null) {
				for (int j = 0; j < dutys.size(); j++) {
					dutys.get(j).setExtend1(sdft.format(dutys.get(j).getDutyStartTime()) + "T" + "12:00:00");
					butys.add(dutys.get(j));
				}
			}
		return butys;
	}

}
