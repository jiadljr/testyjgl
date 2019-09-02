package com.qkby.proj.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.proj.dao.TaskFileDao;
import com.qkby.proj.entity.TaskFile;

@Service("TaskFileBusiness")
public class TaskFileBusinessImpl implements TaskFileBusiness{

	@Resource
	TaskFileDao taskFileDao;
	
	@Override
	public int insertTaskFile(TaskFile taskFile) throws Exception {
		return taskFileDao.insertProjTaskFile(taskFile);
	}

}
