package com.qkby.utils;

import java.io.File;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.qkby.event.entity.EventLogInfo;
import com.qkby.logs.entity.LogOperInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Utils {
	private static ThreadLocal S_User = new ThreadLocal();

	// 全部菜单 即信息安全主管领导【超级管理员】权限菜单
	public static final int[] allmenu = { 1, 2, 3, 4, 5 };
	public static final int[] allfunc = { 11, 12, 13, 14, 21, 22, 23, 24, 25, 31, 33, 41, 42, 43, 44, 45, 53, 54, 55 };
	// 信息安全管理员【管理员】权限菜单
	public static final int[] role2menu = { 1, 2, 3, 4 };
	public static final int[] role2func = { 11, 13, 14, 21, 22, 23, 24, 25, 31, 33, 45 };
	// 信息安全操作员【操作员】 权限菜单
	public static final int[] role3menu = { 4 };
	public static final int[] role3func = { 41, 42, 43, 44 };
	// 信息安全审计员【审计员】权限菜单
	public static final int[] role4menu = { 5 };
	public static final int[] role4func = { 53 };
	// 普通运维员工权限菜单
	public static final int[] role5menu = { 1, 2, 3 };
	public static final int[] role5func = { 11, 12, 13, 21, 22, 23, 25, 31 };
	// 普通人员权限菜单
	public static final int[] role6menu = { 2 };
	public static final int[] role6func = { 21, 25 };
	// MD5加盐
	private static final String salt = "QkBJ0427";

	public static void setUser(UserInfo user) {
		S_User.set(user);
	}

	public static UserInfo getUser() {
		return (UserInfo) S_User.get();
	}

	// 生成密文密码
	public static String crypt(String pwd) {
		return DigestUtils.md5Hex(pwd + salt);
	}

	public static boolean isNum(String num) {
		if (num == null || "".equals(num.trim())) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(num).matches();
	}

	public static Date getDateByString(String sdate, String reg) throws Exception {
		String regx = "yyyy-MM-dd";
		if (sdate == null || "".equals(sdate.trim())) {
			return null;
		}
		if (reg != null && !"".equals(reg.trim())) {
			regx = reg;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(regx);
		Date date = sdf.parse(sdate);
		return date;
	}

	public static String getStringByDate(Date date, String reg) {
		String SDate = null;
		String regx = "yyyy-MM-dd";

		if (reg != null && !"".equals(reg.trim())) {
			regx = reg;
		}
		if (date != null || date.getTime() != 0) {
			SimpleDateFormat sdf = new SimpleDateFormat(regx);
			SDate = sdf.format(date);
		}
		return SDate;
	}

	public static int getDatesMonth(int year, int month) {
		Calendar CalDate = Calendar.getInstance();
		CalDate.set(year, month + 1, 0);
		return CalDate.get(Calendar.DAY_OF_MONTH);
	}

	// ctoday.add(Calendar.DAY_OF_MONTH, 1); 明天时间

	// 获得当天0点时间
	public static Date getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	// 获得当天24点时间
	public static Date getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date date = cal.getTime();
		return date;
	}

	// 获得本周一0点时间
	public static Date getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date date = cal.getTime();
		return date;
	}

	// 获得本周日24点时间
	public static Date getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date date = cal.getTime();
		return date;
	}

	// 获得本月第一天0点时间
	public static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date date = cal.getTime();
		return date;
	}

	// 获得本月最后一天24点时间
	public static Date getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		Date date = cal.getTime();
		return date;
	}
	//获取前一天
	public static String getYesterday(){
		Date dNow = new Date();   //当前时间
		Date dBefore = new Date();
		Calendar calendar = Calendar.getInstance(); //得到日历
		calendar.setTime(dNow);//把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
		dBefore = calendar.getTime();   //得到前一天的时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); //设置时间格式
		String defaultStartDate = sdf.format(dBefore);    //格式化前一天
		return defaultStartDate;
	}

	// 分页参数
	public static void paging(HttpServletRequest request, int totalRow, Map<String, Object> map,
			Map<String, Object> pgMap) {
		String parameter = request.getParameter("pageNumber");
		String size = request.getParameter("pageSize");
		Integer pageNumber = 0;
		if (parameter == null || "0".equals(parameter) || "".equals(parameter)) {
			pageNumber = 1;
		} else {
			pageNumber = Integer.valueOf(parameter);
		}
		int pageSize = 0;
		if (size == null || "".equals(size)) {
			pageSize = 10;
		} else {
			pageSize = Integer.valueOf(size);
		}
		int startPos = (pageNumber - 1) * pageSize;
		int totalPage = totalRow / pageSize;
		if (totalRow % pageSize != 0) {
			totalPage += 1;
		} else if (totalRow / pageSize == 0) {
			totalPage = 1;
		}
		map.put("startPos", startPos);
		map.put("pageSize", pageSize);
		pgMap.put("pageSize", pageSize);
		pgMap.put("pageNumber", pageNumber);
		pgMap.put("totalPage", totalPage);
		pgMap.put("totalRow", totalRow);
	}

	public static void pages(HttpServletRequest request, Map<String, Object> pgMap, Map<String, Object> map) {
		String pgNumber = request.getParameter("pagenumber");
		int pageNumber = Integer.valueOf(pgNumber);
		int pageSize = Integer.valueOf(request.getParameter("pagesize"));
		String totalPage = request.getParameter("totalpage");
		String totalRows = request.getParameter("totalrow");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRows);
		int startPos = (pageNumber - 1) * pageSize;
		pgMap.put("startPos", startPos);
		pgMap.put("pageSize", pageSize);
	}

	// 获取本机的mac地址
	public static String getLocalMac(InetAddress ia) throws SocketException {
		// 获取网卡，获取地址
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// 字节转换为整数
			int temp = mac[i] & 0xff;
			String str = Integer.toHexString(temp);
			if (str.length() == 1) {
				sb.append("0" + str);
			} else {
				sb.append(str);
			}
		}
		String macs = sb.toString().toUpperCase();
		return macs;
	}

	/**
	 * log参数封装
	 * 
	 * @param request
	 * @param status
	 * @return
	 */
	public static EventLogInfo setLog(HttpServletRequest request, int status) {
		EventLogInfo log = new EventLogInfo();
		String eventId = request.getParameter("eventId");
		int id = Integer.valueOf(eventId);
		String eventCode = request.getParameter("eventCode");
		if (eventCode.equals("")) {
			eventCode = null;
		}
		String eventName = request.getParameter("eventName");
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		log.setEventId(id);
		log.setEventCode(eventCode);
		log.setEventName(eventName);
		log.setIdOperUser(userId);
		log.setDateOper(new Date());
		log.setEventStatus(status);

		return log;
	}

	/**
	 * <pre>
	 * insertLogOper (新增操作日志)
	 * Created by 家栋梁 on 2017年11月14日下午12:19:06  
	 *
	 * &#64;param request
	 * &#64;param pkValue
	 * &#64;param table
	 * &#64;param type
	 * &#64;return
	 * </pre>
	 */
	public static LogOperInfo insertLogOper(HttpServletRequest request, int pkValue, String table, int type) {
		HttpSession session = request.getSession();
		int userId = (int) session.getAttribute("user_id");
		String userName = (String) session.getAttribute("user_name");
		LogOperInfo logOperInfo = new LogOperInfo();
		logOperInfo.setIdUser(userId);
		logOperInfo.setDateOper(new Date());
		logOperInfo.setNameUser(userName);
		logOperInfo.setTableOper(table);
		logOperInfo.setPkValue(pkValue);
		logOperInfo.setTypeOper(type);
		return logOperInfo;
	}

	/**
	 * 判断时间是否在时间段内
	 * 
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		begin.setTime(beginTime);

		Calendar end = Calendar.getInstance();
		end.setTime(endTime);

		if (date.after(begin) && date.before(end)) {
			return true;
		} else {
			return false;
		}
	}

	public static void deleteFile(String fullFilePath) {
		File deleteFile = new File(fullFilePath);
		deleteFile.delete();
	}

	/**
	 * 获取两个日期之间的日期
	 * 
	 * @param start
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return 日期集合
	 */
	public static List<Date> getBetweenDates(Date begin, Date end) {
		List<Date> result = new ArrayList<Date>();
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(begin);
		/*
		 * Calendar tempEnd = Calendar.getInstance();
		 * tempStart.add(Calendar.DAY_OF_YEAR, 1); tempEnd.setTime(end); while
		 * (tempStart.before(tempEnd)) { result.add(tempStart.getTime());
		 * tempStart.add(Calendar.DAY_OF_YEAR, 1); }
		 */
		while (begin.getTime() <= end.getTime()) {
			result.add(tempStart.getTime());
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
			begin = tempStart.getTime();
		}
		return result;
	}
	
	public static String getLocalName(HttpServletRequest request) {
		URL save = Thread.currentThread().getContextClassLoader().getResource("");
        String str = save.toString();
        str=str.substring(6,str.length());
        str=str.replaceAll("%20", " ");  
        ServletContext servletContext = request.getSession().getServletContext();
        String proPath=servletContext.getRealPath("/");
        proPath = proPath.substring(0,proPath.length()-1);
        String proName = proPath.substring(proPath.lastIndexOf("\\")+1,proPath.length());
        int num = str.lastIndexOf(proName);//wgbs 为项目名，应用到不同的项目中，这个需要修改！
        str=str.substring(0, num+proName.length());
		return str;
	}
	public static void main(String[] args) {
		String a = "{'status':1,'msg':'','rowsPerPage':1000,'page': 1,'listData':[{'systemUserId':'58e0c0eb-4a40-4e6e-b51d-f7d00957c1ab','userName':'45301','fullName':'孟庆玲','mobilePhone':'','createdOn':'2009-05-11','createdBy':'','businessUnitId':'0f3d1803-7c40-493c-99c8-241823668344','objectTypeCode':'8','datatype':'systemUser'}]}";
		JSONObject jsonObject = JSONObject.fromObject(a);
		JSONArray jsonArray = (JSONArray) jsonObject.get("listData");
		System.out.println(jsonArray);
	}
	public static String getContentType(String fileType) {
		String contentType = "";
		switch (fileType) {
			case "txt":
				contentType = "text/plain";
				break;
			case "doc":
				contentType = "application/msword";
				break;
			case "docx":
				contentType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
				break;
			case "xls":
				contentType = "application/vnd.ms-excel";
				break;
			case "xlsx":
				contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
				break;
			case "jpg":
				contentType = "image/jpeg";
				break;
			case "png":
				contentType = "image/png";
				break;
			case "gif":
				contentType = "image/gif";
				break;
			case "jpgm":
				contentType = "video/jpm";
				break;
			case "jpgv":
				contentType = "video/jpeg";
				break;
			case "jpm":
				contentType = "video/jpm";
				break;
			case "mp4":
				contentType = "video/mp4";
				break;
			case "mp3":
				contentType = "audio/mpeg";
				break;

		}
		return contentType;
	}
	
	/**
	 * 删除文件夹下所有的文件
	 * 
	 */
	public static boolean delAllFile(String path) {  
	       boolean flag = false;  
	       File file = new File(path);  
	       if (!file.exists()) {  
	         return flag;  
	       }  
	       if (!file.isDirectory()) {  
	         return flag;  
	       }  
	       String[] tempList = file.list();  
	       File temp = null;  
	       for (int i = 0; i < tempList.length; i++) {  
	          if (path.endsWith(File.separator)) {  
	             temp = new File(path + tempList[i]);  
	          } else {  
	              temp = new File(path + File.separator + tempList[i]);  
	          }  
	          if (temp.isFile()) {  
	             temp.delete();  
	          }  
	          if (temp.isDirectory()) {  
	             delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件  
	             delFolder(path + "/" + tempList[i]);//再删除空文件夹  
	             flag = true;  
	          }  
	       }  
	       return flag;  
	     }  
		//删除文件夹  
		public static void delFolder(String folderPath) {  
		   try {  
		      delAllFile(folderPath); //删除完里面所有内容  
		      String filePath = folderPath;  
		      filePath = filePath.toString();  
		      java.io.File myFilePath = new java.io.File(filePath);  
		      myFilePath.delete(); //删除空文件夹  
		   } catch (Exception e) {  
		     e.printStackTrace();   
		   }  
		} 
	//判断list中的元素是否重复
	public static boolean hasSame(List<? extends Object> list)  
	{  
	    if(null == list)  
	        return false;  
	    return list.size() == new HashSet<Object>(list).size();  
	}
}  
