package com.qkby.sysmanage.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.business.SysDutyOrderBusiness;
import com.qkby.sysmanage.business.SysDutyRemarkBusiness;
import com.qkby.sysmanage.business.SysDutyTimeBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.sysmanage.entity.SysDutyOrderInfo;
import com.qkby.sysmanage.entity.SysDutyRemarkInfo;
import com.qkby.sysmanage.entity.SysDutyTimeInfo;
import com.qkby.utils.Token;

@Controller
public class SysDutyTimeController {
	 @Resource
	 SysDutyOrderBusiness sysDutyOrderBusiness;
	 @Resource
	 SysDutyTimeBusiness sysDutyTimeBusiness;
	 @Resource
	 SysUserInfoBusiness sysUserInfoBusiness;
	 @Resource
	 SysDutyRemarkBusiness sysDutyRemarkBusiness;
	  /**
	   * 跳转添加排班页面
	   * @return
	   * @throws Exception
	   */
	  @Transactional
	  @RequestMapping("/queryInsertDuty.do")
	  @Token(remove=false, save = true)
	  public ModelAndView queryInsertDuty() throws Exception{
		  Map<String, Object> queryDutyTime = sysDutyOrderBusiness.queryDutyTime();
		  List<SysDutyOrderInfo> selectDutyOrder = sysDutyOrderBusiness.selectDutyOrder();
		  queryDutyTime.put("selectDutyOrder", selectDutyOrder);
		  return new ModelAndView("sys/sys_duty_time_add",queryDutyTime);
	  }
	  @RequestMapping("/seleMonth.do")
	  @ResponseBody
	  public List<String> selectMonth(HttpServletRequest request){
		  List<String> list = new ArrayList<String>();
		  List<String> month = month();
		  String year = request.getParameter("dutyTime");
		  SimpleDateFormat sf = new SimpleDateFormat("yyyy");
		  String format = "";
		  if("".equals(year)){
			  Date date = new Date();
			  format = sf.format(date);
		  }else{
			  format = year;
		  }
		  List<SysDutyTimeInfo> selectDutyTimeByTime = sysDutyTimeBusiness.selectDutyTimeByTime(format);
		  for (int i = 0; i < selectDutyTimeByTime.size(); i++) {
			Date dutyTime = selectDutyTimeByTime.get(i).getDutyTime();
			String format2 = sf.format(dutyTime);
			String substring = format2.substring(format2.length()-2);
			list.add(substring);
		}
		  month.removeAll(list);
		  return month;
	  }
	  public static List<String> month(){
		  List<String> list = new ArrayList<String>();
		  list.add("01");
		  list.add("02");
		  list.add("03");
		  list.add("04");
		  list.add("05");
		  list.add("06");
		  list.add("07");
		  list.add("08");
		  list.add("09");
		  list.add("10");
		  list.add("11");
		  list.add("12");
		  return list;
	  }
	  @Transactional
	  @ResponseBody
	  @RequestMapping("/addDutyTime.do")
	  @Token(remove=true, save = false)
	  public void addDutyTime(HttpServletRequest request) throws Exception{
		  SysDutyRemarkInfo sysDutyRemark = new SysDutyRemarkInfo();
		  String year = request.getParameter("yearSelect");
		  String month = request.getParameter("monthSelect");
		  String spanHidden = request.getParameter("spanHidden");
		  String remark = request.getParameter("remark");
		  SimpleDateFormat sfs = new SimpleDateFormat("yyyyMM");
		  String dates = "";
		  if(month.length() == 1){
			  dates = year+"0"+month;
		  }else{
			  dates = year+month;
		  }
		  Date dutyTimes = sfs.parse(dates);
		  sysDutyRemark.setDutyRemark(remark);
		  sysDutyRemark.setDutyTime(dutyTimes);
		  sysDutyRemarkBusiness.insert(sysDutyRemark);
		  String date = "";
		  if(month.length() == 1){
			  date =  year+"-"+"0"+month+"-"+"11";
		  }else{
			  date =  year+"-"+month+"-"+"11";
		  }
		  SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		  String[] monthDays = getMonthDays(date);
		  String[] dutyUsers = spanHidden.split(",");
		  String dateStr = "";
		  for(String value: monthDays){
			  if(month.length() == 1){
				  dateStr += year+"0"+month+value+",";
			  }else{
				  dateStr += year+month+value+",";
			  }
		  }
		  String[] dateArray = dateStr.split(",");
		  Date dutyTime = new Date();
		  for (int j = 0; j < dateArray.length; j++) {
			  if (j < dateArray.length) {
					SysDutyTimeInfo sysDutyTime = new SysDutyTimeInfo();
					String user = dutyUsers[j % dutyUsers.length];
					int id_user = Integer.valueOf(user);
					dutyTime = sf.parse(dateArray[j]);
					sysDutyTime.setDutyTime(dutyTime);
					sysDutyTime.setIdUser(id_user);
					try {
						sysDutyTimeBusiness.insert(sysDutyTime);
					} catch (Exception e) {
						System.out.println(e);
					}
			  }
		  }
	  }
	  
	  private static String[] getMonthDays(String date) {
		  Calendar calendar = Calendar.getInstance();
		  calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		  calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(5, 7)) -1);
		  int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		  String[] days = new String[maxDay];
		  
		  for(int i = 1; i <= maxDay; i++){
			  String a = String.valueOf(i);
			  if(a.length() == 1){
				  days[i-1] = "0"+a;
			  }else{
				  days[i-1] = a;
			  }
		   
		  }
		  return days;
		 }
	  
	    @ResponseBody
		@RequestMapping("/selectDutyTime.do")
		public List<SysDutyTimeInfo> selectDutyTime(){
	    	return sysDutyTimeBusiness.selectDutyTime();
		}
	    @Transactional
	    @RequestMapping("/deleteTime.do")
	    @ResponseBody
	    public void deleteTime(HttpServletRequest request){
	    	String dutyDate = request.getParameter("dutyDate");
	    	sysDutyTimeBusiness.deleteDutyTime(dutyDate);
	    	sysDutyRemarkBusiness.deleteDutyRemark(dutyDate);
	    }
	    @Transactional
	    @RequestMapping("/dutyTimeInsert.do")
	    public ModelAndView dutyTimeInsert(HttpServletRequest request) throws Exception{
	    	String parameter = request.getParameter("time");
	    	Map<String,Object> pgMap = new HashMap<String,Object>();
			pgMap.put("role_id", ConstantMenu.OPS_SIX);
			SysDutyTimeInfo selectDutyIdUser = sysDutyTimeBusiness.selectDutyIdUser(parameter);
			Map<String, Object> map = sysUserInfoBusiness.userByRoleType(pgMap,request);
			map.put("selectDutyIdUser", selectDutyIdUser);
			return new ModelAndView("sys/sys_duty_time_mod",map);
	    }
	    @RequestMapping("/updateDutyTime.do")
	    @ResponseBody
	    public int updateDutyTime(HttpServletRequest request){
	    	String dutyTime = request.getParameter("dutyTime");
	    	String dutyUser = request.getParameter("dutyUser");
	    	int updateDutyTime = sysDutyTimeBusiness.updateDutyTime(dutyTime,dutyUser);
	    	return updateDutyTime;
	    }
	    @RequestMapping("/selectDutyTimeByIdUser.do")
	    @ResponseBody
	    public List<SysDutyTimeInfo> selectDutyTimeByIdUser(HttpServletRequest request){
	    	HttpSession session = request.getSession();
			int userId = (int) session.getAttribute("user_id");
			return sysDutyTimeBusiness.selectDutyTimeByIdUser(userId);
	    }
	    @RequestMapping("/selectDutyTimeIsNull.do")
	    @ResponseBody
	    public String selectDutyTimeIsNull(HttpServletRequest request){
	    	String dutyTime = request.getParameter("dutyTime");
	    	List<SysDutyTimeInfo> selectDutyTimeByTime = sysDutyTimeBusiness.selectDutyTimeByTime(dutyTime);
	    	String success = "";
	    	if(selectDutyTimeByTime == null){
	    		success = "1";
	    	}
	    	return success;
	    }
}
