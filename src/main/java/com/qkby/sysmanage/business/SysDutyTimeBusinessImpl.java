package com.qkby.sysmanage.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.sysmanage.dao.SysDutyTimeDao;
import com.qkby.sysmanage.entity.SysArrange;
import com.qkby.sysmanage.entity.SysDutyTimeInfo;

@Service
public class SysDutyTimeBusinessImpl implements SysDutyTimeBusiness{
	
	@Resource
	private SysDutyTimeDao sysDutyTimeDao;
	@Override
	public void insert(SysDutyTimeInfo sysDutyTime) {
		sysDutyTimeDao.insert(sysDutyTime);
	}
	@Override
	public List<SysDutyTimeInfo> selectDutyTime() {
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd");
		List<SysDutyTimeInfo> butys = new ArrayList<SysDutyTimeInfo>();
			List<SysDutyTimeInfo> dutys = sysDutyTimeDao.selectDutyTime();
			if (dutys != null) {
				for (int j = 0; j < dutys.size(); j++) {
					dutys.get(j).setExtend1(sdft.format(dutys.get(j).getDutyTime()));
					butys.add(dutys.get(j));
				}
			}
		return butys;
	}
	@Override
	public void deleteDutyTime(String dutyTime) {
		sysDutyTimeDao.deleteDutyTime(dutyTime);
	}
	
	@Override
	public SysDutyTimeInfo selectDutyIdUser(String dutyTime) {
		return sysDutyTimeDao.selectDutyIdUser(dutyTime);
	}
	@Override
	public int updateDutyTime(String dutyTime,String dutyUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("dutyTime", dutyTime);
		map.put("idUser", dutyUser);
		int updateDutyTime = sysDutyTimeDao.updateDutyTime(map);
		return updateDutyTime;
	}
	
	@Override
	public List<SysDutyTimeInfo> selectDutyTimeByTime(String dutyTime) {
		return sysDutyTimeDao.selectDutyTimeByTime(dutyTime);
	}
	@Override
	public List<SysDutyTimeInfo> selectDutyTimeByIdUser(Integer idUser) {
		return sysDutyTimeDao.selectDutyTimeByIdUser(idUser);
	}
}
