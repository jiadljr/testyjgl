package com.qkby.proj.dao;

import java.util.List;
import java.util.Map;

import com.qkby.proj.entity.ProjectTemplate;

public interface ProjTemplateDao {

	/**
	 * 根据id删除模板
	 * 2018年3月29日 下午2:21:17
	 * @author 李帅
	 * @param id
	 * @return
	 * int
	 */
    int deleteTemplateById(Integer id)throws Exception;

    /**
	 * 添加模板
	 * 2018年3月29日 下午2:21:17
	 * @author 李帅
	 * @param id
	 * @return
	 * int
	 */
    int insertTemplate(ProjectTemplate projTemplate)throws Exception;

    /**
	 * 查询所有模板
	 * 2018年3月29日 下午2:21:17
	 * @author 李帅
	 * @param id
	 * @return
	 * int
	 */
    List<ProjectTemplate> selectTemplateInfo()throws Exception;

    /**
	 * 修改模板信息
	 * 2018年3月29日 下午2:21:17
	 * @author 李帅
	 * @param id
	 * @return
	 * int
	 */
    int updateTemplateInfo(ProjectTemplate projTemplate)throws Exception;

	/**
	 * 根据id查询模板信息
	 * 2018年3月30日 下午3:39:23
	 * @李帅
	 * @param
	 */
	ProjectTemplate selectTemplateById(int templateId);
    
    
}