package com.qkby.proj.dao;

import java.util.List;

import com.qkby.proj.entity.TaskRecord;

public interface ProjTaskRecordDao {
    
	/**
	 * 查询单条任务的任务记录集合
	 * 2018年3月9日 上午9:39:08
	 * @author 李帅
	 * @param idTask
	 * @return
	 * List<ProjTaskRecord>
	 */
    List<TaskRecord> selectTaskRecordList(int idTask);
    
    /**
     * 添加任务记录
     * 2018年3月9日 上午9:46:12
     * @author 李帅
     * @param projTaskRecord
     * @return
     * int
     */
    int insertTaskRecord(TaskRecord projTaskRecord);
}