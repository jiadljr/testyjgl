package com.qkby.sysmanage.business;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.sysmanage.dao.SysDutyRemarkDao;
import com.qkby.sysmanage.entity.SysDutyRemarkInfo;

@Service
public class SysDutyRemarkBusinessImpl implements SysDutyRemarkBusiness{
	
	@Resource
	private SysDutyRemarkDao sysDutyRemarkDao;

	@Override
	public void insert(SysDutyRemarkInfo sysDutyRemark) {
		sysDutyRemarkDao.insert(sysDutyRemark);
	}

	@Override
	public SysDutyRemarkInfo selectDutyRemark(String dutyTime) {
		return sysDutyRemarkDao.selectDutyRemark(dutyTime);
	}

	@Override
	public void deleteDutyRemark(String dutyTime) {
		sysDutyRemarkDao.deleteDutyRemark(dutyTime);
	}

	@Override
	public void updateDutyRemark(Map<String, Object> map) {
		sysDutyRemarkDao.updateDutyRemark(map);
	}

}
