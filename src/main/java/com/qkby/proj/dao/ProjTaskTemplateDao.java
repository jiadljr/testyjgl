package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;
import com.qkby.proj.entity.TaskTemplate;

public interface ProjTaskTemplateDao {
	/**
	 * 根据id删除模板下的任务
	 * 2018年3月29日 下午2:18:20
	 * @author 李帅
	 * @param id
	 * @return
	 * int
	 */
    int deleteProjTemplateTask(Integer id)throws Exception;
    
    /**
     * 批量删除模板下的任务
     * 2018年4月19日 下午10:12:52
     * @李帅
     * @param taskIdList 模板任务的id集合
     * @return
     */
    int deleteTemplateTaskByids(List<Integer> taskIdList)throws Exception;

    /**
	 * 添加模板任务
	 * 2018年3月29日 下午2:18:20
	 * @author 李帅
	 * @param id
	 * @return
	 * int
	 */
    int insertTemplateTask(TaskTemplate projTaskTemplate)throws Exception;

    /**
	 * 根据id查询单个阶段任务的信息（包含任务名称，任务备注等信息）
	 * 2018年3月29日 下午2:18:20
	 * @author 李帅
	 * @param id
	 * @return
	 * int
	 */
    TaskTemplate selectTemplateTaskById(int templateId)throws Exception;

	/**
	 * 获取阶段任务的名称
	 * 2018年3月29日 下午6:04:04
	 * @李帅
	 * @param
	 */
	List<TaskTemplate> selectTemplateTaskNameById(int templateId);

	/**
	 * 根据id修改模板任务
	 * 2018年3月30日 下午1:21:16
	 * @李帅
	 * @param
	 */
	int updateTemplateTaskById(TaskTemplate projTaskTemplate);
}