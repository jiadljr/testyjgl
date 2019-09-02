package com.qkby.sysmanage.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.sysmanage.dao.SysAssetsTypeDao;
import com.qkby.sysmanage.entity.SysAssetsType;
import com.qkby.utils.ChineseToInitials;
import com.qkby.utils.Utils;

@Service("SysAssetsTypeBusiness")
public class SysAssetsTypeBusinessImpl implements SysAssetsTypeBusiness{
	@Resource
	SysAssetsTypeDao sysAssetsTypeDao;
	@Resource
	LogOperInfoDao logOperInfoDao;

	@Override
	@Transactional
	public Map<String, Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		//参数封装
		Map<String, Object> pgMap=new HashMap<String, Object>();
		String assetsType = request.getParameter("assetsType");
		if ("".equals(assetsType)) {
			assetsType=null;
		}
		String assetsTypeParent = request.getParameter("assetsTypeParent");
		if (Utils.isNum(assetsTypeParent)) {
			int pId=Integer.parseInt(assetsTypeParent);
			pgMap.put("parentId", pId);
		}
		String assetsTypeLevel = request.getParameter("assetsTypeLevel");
		if (Utils.isNum(assetsTypeLevel)) {
			int level=Integer.parseInt(assetsTypeLevel);
			pgMap.put("layer", level);
		}
		pgMap.put("name", assetsType);
		String pages = request.getParameter("pages");
		if(!"".equals(pages)){
		 Utils.pages(request, pgMap, map);
		}else{
			int totalRow = sysAssetsTypeDao.countByExample(pgMap);
			Utils.paging(request, totalRow, pgMap, map);
		}
		//查询所有资产类型
		List<SysAssetsType> assetsTypeAll = sysAssetsTypeDao.selectByExample(pgMap);
		map.put("assetsTypeAll", assetsTypeAll);
		//查询上级资产
		List<SysAssetsType> parentName = sysAssetsTypeDao.selectParentName();
		map.put("parentName", parentName);
		return map;
	}

	@Override
	public List<SysAssetsType> selectParentName(int modelId) throws Exception {
		Map<String, Object> pgMap = new HashMap<String, Object>();
		if (modelId != 0) {
			pgMap.put("layer", modelId-1);
		}
		List<SysAssetsType> assetsType = sysAssetsTypeDao.selectByExample(pgMap);
		if(assetsType == null){
			throw new BusinessException();
		}
		return assetsType;
	}

	@Override
	public void insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysAssetsType assetsType = setAssetsType(request, response);
		int insert = sysAssetsTypeDao.insert(assetsType);
		if (insert==0) {
			throw new 	BusinessException("", "新增资产类别失败！");
		}
		Integer pkId = assetsType.getId();
		LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_asset_type", 1);
		logOperInfoDao.insert(logOperInfo);
	}

	@Override
	public SysAssetsType setAssetsType(HttpServletRequest request, HttpServletResponse response) {
		SysAssetsType assetsType=new SysAssetsType();
		String code = request.getParameter("code");
		if (code.equals("")) {
			code=null;
		}
		assetsType.setCode(code);
		String name = request.getParameter("name");
		if (name.equals("")) {
			name=null;
		}
		assetsType.setName(name);
		String parentName = request.getParameter("parentName");
		if (Utils.isNum(parentName)) {
			int pName=Integer.parseInt(parentName);
			assetsType.setParentId(pName);
		}
		String level = request.getParameter("layer");
		if (Utils.isNum(level)) {
			int layer=Integer.valueOf(level);
			assetsType.setLayer(layer);
		}
		String remark = request.getParameter("remark");
		if (remark.equals("")) {
			remark=null;
		}
		assetsType.setRemark(remark);
		return assetsType;
	}

	@Override
	public SysAssetsType selectByPrimaryKey(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String ids = request.getParameter("id");
		int id = Integer.valueOf(ids);
		SysAssetsType assetsTypeOne = sysAssetsTypeDao.selectByPrimaryKey(id);
		if(assetsTypeOne == null){
			throw new BusinessException("","没找到该资产类别的信息");
		}
		return assetsTypeOne;
	}

	@Override
	public void updateByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysAssetsType assetsType = setAssetsType(request, response);
		String assetsTypeId = request.getParameter("assetsTypeId");
		int typeId = Integer.parseInt(assetsTypeId);
		assetsType.setId(typeId);
		int update = sysAssetsTypeDao.updateByPrimaryKey(assetsType);
		if (update == 0) {
			throw new BusinessException("", "修改资产类别失败！");
		}
		LogOperInfo logOperInfo = Utils.insertLogOper(request, typeId, "sys_asset_type", 2);
		logOperInfoDao.insert(logOperInfo);
	}

	@Override
	@Transactional
	public String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		//查询子资产
		List<SysAssetsType> assetsTypeAll = sysAssetsTypeDao.selectPrimAll();
		String result="success";
		String parent_id="";
		for (SysAssetsType sysAssetsType : assetsTypeAll) {
			parent_id += String.valueOf(sysAssetsType.getParentId())+",";
		}
		String[] ids = request.getParameterValues("ids");
		for (int i = 0; i < ids.length; i++) {
			if (parent_id.contains(ids[i])) {
				result="pError";
				return result;
			}
		}
		map.put("ids", ids);
		int delete = sysAssetsTypeDao.deleteByPrimaryKey(map);
		if (delete == 0) {
			result="error";
		}
		for(int i = 0;i<ids.length;i++){
			int pkId = Integer.valueOf(ids[i]);
			LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_asset_type", 3);
			logOperInfoDao.insert(logOperInfo);
		}
		return result;
	}

	@Override
	public List<SysAssetsType> selectAssetsTypeByparentId(int id) throws Exception {
		List<SysAssetsType> selectAssetsTypeByparentId = sysAssetsTypeDao.selectAssetsTypeByparentId(id);
		if(selectAssetsTypeByparentId == null){
			throw new BusinessException("500","");
		}
		return selectAssetsTypeByparentId;
	}

	@Override
	public List<SysAssetsType> selectAssetsTypeByLevel(Map<String, Object> map) throws Exception {
		return sysAssetsTypeDao.selectAssetsTypeByLevel(map);
	}

	@Override
	public List<SysAssetsType> selectPrimAll() throws Exception {
		return sysAssetsTypeDao.selectPrimAll();
	}

	@Override
	public Map<String, Object> selectAssetsTypeForTree() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysAssetsType> selectPrimAll = sysAssetsTypeDao.selectPrimAll();
		List<SysAssetsType> assetsTypeOne = new ArrayList<SysAssetsType>();
		List<SysAssetsType> assetsTypeTwo = new ArrayList<SysAssetsType>();
		List<SysAssetsType> assetsTypeThree = new ArrayList<SysAssetsType>();
		for(SysAssetsType type:selectPrimAll){
			String name = type.getName();
			type.setExtend2(ChineseToInitials.getPYIndexStr(name, true).toLowerCase());//首字母
			Integer layer = type.getLayer();
			if (layer == 1) {
				assetsTypeOne.add(type);
			}else if(layer == 2){
				assetsTypeTwo.add(type);
			}else if (layer == 3) {
				assetsTypeThree.add(type);
			}
		}
		map.put("selectPrimAll", selectPrimAll);
		map.put("assetsTypeOne", assetsTypeOne);
		map.put("assetsTypeTwo", assetsTypeTwo);
		map.put("assetsTypeThree", assetsTypeThree);
		return map;
	}
	
	public List<SysAssetsType> selectAll() throws Exception{
		List<SysAssetsType> selectAll = sysAssetsTypeDao.selectAll();
		return selectAll;
	}

	@Override
	public List<SysAssetsType> selectAssetsTypePareateId(Integer id) {
		return sysAssetsTypeDao.selectAssetsTypePareateId(id);
	}

}
