package com.qkby.proj.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.proj.dao.ProjFileDao;
import com.qkby.proj.dao.ProjInfoDao;
import com.qkby.proj.entity.ProjectFile;

@Service
public class ProjFileBusinessImpl implements ProjFileBusiness {
	@Resource
	ProjFileDao projFileDao;
	@Resource
	ProjInfoDao projInfoDao;
	
	@Override
	public List<ProjectFile> projFileList(Map<String, Object> paramMap) throws Exception {
		return projFileDao.selectProjectFileInformationByCondition(paramMap);
	}

	@Override
	public int countProjInfoFile(Map<String, Object> paramMap) throws Exception {
		return projFileDao.selectProjectFileCountByCondition(paramMap);
	}

	@Override
	public String addProjFile(ProjectFile projFile) throws Exception {
		projFileDao.insertProjectFile(projFile);
		return null;
	}

	@Override
	public String deleteProjFile(int projFile) throws Exception {
		projFileDao.deleteProjectFile(projFile);
		return null;
	}

	@Override
	public String updateProjFile(ProjectFile projFile) throws Exception {
		projFileDao.updateProjectFile(projFile);
		return null;
	}
}
