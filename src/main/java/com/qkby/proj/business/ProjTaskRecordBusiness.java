package com.qkby.proj.business;

import com.qkby.proj.entity.TaskRecord;

public interface ProjTaskRecordBusiness {

	/**
	 * 新增任务记录
	 * 2018年3月9日 上午10:19:08
	 * @author 李帅
	 * @param projTaskRecord
	 * @return
	 * int
	 */
	public int insert(TaskRecord projTaskRecord);
}
