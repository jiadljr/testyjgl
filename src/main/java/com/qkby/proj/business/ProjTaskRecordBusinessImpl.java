package com.qkby.proj.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.proj.dao.ProjTaskRecordDao;
import com.qkby.proj.entity.TaskRecord;

@Service("ProjTaskRecordBusiness")
public class ProjTaskRecordBusinessImpl implements ProjTaskRecordBusiness{

	@Resource
	ProjTaskRecordDao projTaskRecordDao;

	@Override
	public int insert(TaskRecord projTaskRecord) {
		return projTaskRecordDao.insertTaskRecord(projTaskRecord);
	}
	
}
