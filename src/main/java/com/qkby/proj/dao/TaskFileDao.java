package com.qkby.proj.dao;


import com.qkby.proj.entity.TaskFile;

public interface TaskFileDao {

    /**
     * 添加任务文件
     * 2018年2月9日 下午1:13:12
     * @author 李帅
     * @param taskFile
     * @return
     * int
     */
    int insertProjTaskFile(TaskFile taskFile) throws Exception;
}