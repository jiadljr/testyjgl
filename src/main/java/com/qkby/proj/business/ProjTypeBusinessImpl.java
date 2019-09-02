package com.qkby.proj.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.proj.dao.ProjTypeDao;
import com.qkby.proj.entity.ProjectType;

@Service("ProjTypeBusiness")
public class ProjTypeBusinessImpl implements ProjTypeBusiness{
	
	@Resource
	ProjTypeDao projTypeDao;

	@Override
	public Map<String, Object> getProjTypeList() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ProjectType> projTypeList = projTypeDao.selectProjTypeList();
		map.put("projTypeList", projTypeList);
		//查询第一个类型的信息，展示在页面。
		if(projTypeList.size()>0 && projTypeList != null){
			Integer typeId = projTypeList.get(0).getId();
			ProjectType projTypeOne = projTypeDao.selectProjTypeByKey(typeId);
			map.put("projTypeOne", projTypeOne);
		}
		return map;
	}

	@Override
	public ProjectType getProjType(Integer typeId) throws Exception {
		return projTypeDao.selectProjTypeByKey(typeId);
	}

	@Override
	public int updateProjTypeInform(ProjectType projType) throws Exception {
		return projTypeDao.updateProjType(projType);
	}

	@Override
	public int insertProjTypeInform(ProjectType projType) throws Exception {
		return projTypeDao.insertProjType(projType);
	}

	@Override
	public int delProjType(Integer typeId) throws Exception {
		return projTypeDao.deleteProjType(typeId);
	}

	@Override
	public List<ProjectType> projTypeList() throws Exception {
		return projTypeDao.selectProjTypeList();
	}

	@Override
	public List<Map<String, Object>> selectProjTypeName() {
		return projTypeDao.selectProjTypeName();
	}
}
