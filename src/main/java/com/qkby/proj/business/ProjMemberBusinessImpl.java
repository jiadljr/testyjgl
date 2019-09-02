package com.qkby.proj.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.proj.dao.ProjMemberDao;
import com.qkby.proj.entity.ProjectMembers;

@Service
public class ProjMemberBusinessImpl implements ProjMemberBusiness {
	@Resource
	ProjMemberDao projMemberDao;
	

	@Override
	public List<ProjectMembers>projMemberList(Map<String, Object> paramMap) throws Exception {
		return projMemberDao.selectProjMembersByMap(paramMap);
	}

	@Override
	public String addProjMember(ProjectMembers projMember) throws Exception {
		projMemberDao.insertProjMember(projMember);
		return null;
	}

	@Override
	public String updateProjMember(ProjectMembers projMember) throws Exception {
		projMemberDao.updateProjMember(projMember);
		return null;
	}

	@Override
	public String deleteProjMember(int idMember) throws Exception {
		projMemberDao.delectProjMember(idMember);
		return null;
	}

	@Override
	public void updateProjMember(Map<String, Object> paramMap) throws Exception {
		List<ProjectMembers> projMemberList = projMemberDao.selectProjMembersByMap(paramMap);
		String projMember = (String) paramMap.get("projMember");
		String projCode = (String) paramMap.get("projCode");
		List<Integer> delList = new ArrayList<Integer>();
		List<String> addList = new ArrayList<String>();
		if (projMemberList!=null) {
			for (int i = 0; i < projMemberList.size(); i++) {
				if (projMember.indexOf(""+projMemberList.get(i).getIdMember()+"")==-1) {
					delList.add(projMemberList.get(i).getId());
				}
			}
		}
		String[] pMember = projMember.split(",");
		for (int i = 0; i < pMember.length; i++) {
			Boolean flag = true;
			if (!"".equals(pMember[i].trim())) {
				if (projMemberList!=null) {
					for (int j = 0; j < projMemberList.size(); j++) {
						int idMember = projMemberList.get(j).getIdMember();
						int valueOf = Integer.valueOf(pMember[i]);
						if ( idMember== valueOf) {
							flag = false;
							break;
						}
					}
				}
				if (flag) {
					addList.add(pMember[i]);
				}
			}
		}
		//删除
		for (int i = 0; i < delList.size(); i++) {
			projMemberDao.delectProjMember(delList.get(i));
		}
		//新增
		ProjectMembers projMemberup = new ProjectMembers();
		projMemberup.setProjCode(projCode);
		for (int i = 0; i < addList.size(); i++) {
			projMemberup.setIdMember(Integer.valueOf(addList.get(i)));
			projMemberDao.insertProjMember(projMemberup);
		}
	}

}
