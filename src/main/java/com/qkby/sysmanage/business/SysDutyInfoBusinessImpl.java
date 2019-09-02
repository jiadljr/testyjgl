package com.qkby.sysmanage.business;

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
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.dao.SysDutyInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysDutyInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月16日下午4:06:32 
 *     
 * @version </pre>
 */
@Service
public class SysDutyInfoBusinessImpl implements SysDutyInfoBusiness{
	@Resource
	public SysDutyInfoDao sysDutyInfoDao;
	@Resource
	public SysUserInfoDao sysUserInfoDao;
	@Override
	@Transactional
	public List<SysDutyInfo> selectByExample() throws Exception {
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		List<SysDutyInfo> butys = new ArrayList<SysDutyInfo>();
		Map<String,Object> map = new HashMap<String,Object>();
		int b_duty_orde = ConstantMenu.DAY_ONE;
		int y_duty_orde = ConstantMenu.NIGHT_TWO;
		if (b_duty_orde == ConstantMenu.DAY_ONE) {
			map.put("duty_orde", ConstantMenu.DAY_ONE);
			List<SysDutyInfo> dutys = sysDutyInfoDao.selectDuty(map);
			if (dutys != null) {
				for (int j = 0; j < dutys.size(); j++) {
					dutys.get(j).setExtend1(sdft.format(dutys.get(j).getDutyDate()) + "T" + "12:00:00");
					butys.add(dutys.get(j));
				}
			}
		}
		if (y_duty_orde == ConstantMenu.NIGHT_TWO) {
			map.put("duty_orde", ConstantMenu.NIGHT_TWO);
			List<SysDutyInfo> dutys = sysDutyInfoDao.selectDuty(map);
			if (dutys != null) {
				for (int j = 0; j < dutys.size(); j++) {
					dutys.get(j).setExtend1(sdft.format(dutys.get(j).getDutyDate()) + "T" + "18:00:00");
					butys.add(dutys.get(j));
				}
			}
		}
		return butys;
	}
	@Override
	public Map<String,Object> selectByCond(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String time = request.getParameter("time");
		Map<String, Object> map = new HashMap<String, Object>();
		Date timeDate = new Date();
		if (time != null && !"".equals(time.trim())) {
			try {
				timeDate = Utils.getDateByString(time, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map<String,Object> qmap = new HashMap<String,Object>();
		qmap.put("duty_date", timeDate);
		List<SysDutyInfo> dutys = sysDutyInfoDao.selectByMap(qmap);
		String nightStaff = "";
		String dayStaff = "";
		if (dutys != null) {
			for (int i = 0; i < dutys.size(); i++) {
				if (dutys.get(i).getDutyOrder() == 1) {
					dayStaff += dutys.get(i).getIdUser() + ",";
				} else if (dutys.get(i).getDutyOrder() == 2) {
					nightStaff += dutys.get(i).getIdUser() + ",";
				}
			}
		}
		map.put("dayStaff", dayStaff);
		map.put("nightStaff", nightStaff);
		return map;
	}
	@Override
	@Transactional
	public void submitDuty(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String inpDuty = request.getParameter("inpDuty");
		String inpNight = request.getParameter("inpNight");
		if("1".equals(inpDuty)){
		String dutyTime = request.getParameter("dutyTime");
		String dayDuty = request.getParameter("dayDuty");
		String[] user = dayDuty.split(",");
		List<Integer> delList = new ArrayList<Integer>();
		List<String> addList = new ArrayList<String>();
		Date duty_date = null;
		try {
			duty_date = Utils.getDateByString(dutyTime, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> qmap = new HashMap<String,Object>();
		qmap.put("duty_date", duty_date);
		qmap.put("duty_order", inpDuty);
		List<SysDutyInfo> selectByMap = sysDutyInfoDao.selectByMap(qmap);
		for (int j = 0; j < selectByMap.size(); j++) {
			if (dayDuty.indexOf("," + selectByMap.get(j).getIdUser() + ",") == -1) {
				delList.add(selectByMap.get(j).getId());
			}
		}	
		for (int i = 0; i < user.length; i++) {
			Boolean flag = true;
			if (user[i] != null && !"".equals(user[i].trim())) {
				for (int j = 0; j < selectByMap.size(); j++) {
					if (selectByMap.get(j).getIdUser() == Integer.valueOf(user[i].trim())) {
						flag = false;
						break;
					}
				}
				if (flag) {
					addList.add(user[i]);
				}
			}
		}
		for (int i = 0; i < delList.size(); i++) {
			sysDutyInfoDao.deleteByPrimaryKey(delList.get(i));
		}
		for (int i = 0; i < addList.size(); i++) {
			String string = addList.get(i);
			int id = Integer.valueOf(string);
			SysUserInfo use = sysUserInfoDao.selectByPrimaryKey(id);
			if (use != null) {
				SysDutyInfo newduty = new SysDutyInfo();
				newduty.setIdUser(use.getId());
				newduty.setDutyDate(duty_date);
				newduty.setDutyOrder(Integer.valueOf(inpDuty));
				sysDutyInfoDao.insert(newduty);
			}
		}
	}
		if("2".equals(inpNight)){
			String dutyTime = request.getParameter("dutyTime");
			String nightDuty = request.getParameter("nightDuty");
			String[] user = nightDuty.split(",");
			List<Integer> delList = new ArrayList<Integer>();
			List<String> addList = new ArrayList<String>();
			Date duty_date = null;
			try {
				duty_date = Utils.getDateByString(dutyTime, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String,Object> qmap = new HashMap<String,Object>();
			qmap.put("duty_date", duty_date);
			qmap.put("duty_order", inpNight);
			List<SysDutyInfo> selectByMap = sysDutyInfoDao.selectByMap(qmap);
			for (int j = 0; j < selectByMap.size(); j++) {
				if (nightDuty.indexOf("," + selectByMap.get(j).getIdUser() + ",") == -1) {
					delList.add(selectByMap.get(j).getId());
				}
			}	
			for (int i = 0; i < user.length; i++) {
				Boolean flag = true;
				if (user[i] != null && !"".equals(user[i].trim())) {
					for (int j = 0; j < selectByMap.size(); j++) {
						if (selectByMap.get(j).getIdUser() == Integer.valueOf(user[i].trim())) {
							flag = false;
							break;
						}
					}
					if (flag) {
						addList.add(user[i]);
					}
				}
			}
			for (int i = 0; i < delList.size(); i++) {
				sysDutyInfoDao.deleteByPrimaryKey(delList.get(i));
			}
			for (int i = 0; i < addList.size(); i++) {
				String string = addList.get(i);
				int id = Integer.valueOf(string);
				SysUserInfo use = sysUserInfoDao.selectByPrimaryKey(id);
				if (use != null) {
					SysDutyInfo newduty = new SysDutyInfo();
					newduty.setIdUser(use.getId());
					newduty.setDutyDate(duty_date);
					newduty.setDutyOrder(Integer.valueOf(inpNight));
					sysDutyInfoDao.insert(newduty);
				}
			}
		}
	}
	@Override
	public Map<String,Object> selectDutyDate(int id) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,Object> map = new HashMap<String,Object>();
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		//白班开始时间
		String fDayShift = resource.getString("fDayShift");
		String tDayShift = resource.getString("tDayShift");
		//夜班开始时间
		String fNightShift = resource.getString("fNightShift");
		String tNightShift = resource.getString("tNightShift");
		Date nightShiftf = null;
		Date nightShiftt = null;
		Date dayShiftf = null;
		Date dayShiftt = null;
		try {
			nightShiftf = df.parse(fNightShift+":00");
			nightShiftt = df.parse(tNightShift+":00");
		    dayShiftf = df.parse(fDayShift+":00");
			dayShiftt = df.parse(tDayShift+":00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date dateDay = null;
		try {
			dateDay = df.parse(df.format(new Date()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean dayDate = Utils.belongCalendar(dateDay, dayShiftf, dayShiftt);
		boolean nightDate = Utils.belongCalendar(dateDay, nightShiftf, nightShiftt);
		List<Map<String, Object>> selectDutyDate = sysDutyInfoDao.selectDutyDate(id);
		for (Map<String, Object> dutyDate : selectDutyDate) {
			Date object = (Date)dutyDate.get("dutyDate");
			String dateFormat = sf.format(object);
			Date date = new Date();
			String format = sf.format(date);
			if(dateFormat.equals(format)){
					map.put("dayDate", dayDate);
					map.put("nightDate", nightDate);
			}else{
				map.put("dayDate", false);
				map.put("nightDate", false);
			}
		}
		return map;
	}
}
