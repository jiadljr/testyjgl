package com.qkby.proj.business;

import com.qkby.proj.entity.TaskFile;

public interface TaskFileBusiness {
	
	/**
	 * 添加任务文件
	 * 2018年2月9日 下午1:17:23
	 * @author 李帅
	 * @param taskFile
	 * @return
	 * @throws Exception
	 * int
	 */
	int insertTaskFile(TaskFile taskFile) throws Exception;
	
}
