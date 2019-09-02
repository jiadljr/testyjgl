package com.qkby.sysmanage.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.utils.ChineseToInitials;
import com.qkby.utils.Utils;

/**
 * 
 * @author L.S
 */
@Service
public class SysDepInfousinessImpl implements SysDepInfoBusiness{
	@Resource
   public SysDeptInfoDao sysDeptInfoDao;
	@Resource
    LogOperInfoDao logOperInfoDao;

	@Override
	//查询所有
	public List<SysDeptInfo> selectPrimAll(HttpServletRequest request,HttpServletResponse response) throws Exception {
		return sysDeptInfoDao.selectPrimAll();
	}
	//删除
	@Override
	public String deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//查询该部门下是否含有子部门
		List<SysDeptInfo> deptAll = sysDeptInfoDao.selectPrimAll();
		String parent_id="";
		for (SysDeptInfo dept : deptAll) {
			parent_id+=String.valueOf(dept.getParentId())+",";
		}
		String[] ids = request.getParameterValues("ids");
		String result="success";
		for (int i = 0; i < ids.length; i++) {
			if (parent_id.contains(ids[i])) {
				result="pError";
				return result;
			}
		}
		//逻辑删除
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		int delete = sysDeptInfoDao.updateLogic(map);
		if (delete == 0) {
			result = "error";
		}
		for(int i=0;i<ids.length;i++){
			int pkId = Integer.valueOf(ids[i]);
			LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_dept", 3);
			logOperInfoDao.insert(logOperInfo);
		}
		return result;
	}
	//添加
	@Override
	public String insert(HttpServletRequest request,HttpServletResponse response) throws Exception {
		SysDeptInfo dept = setDept(request, response);
		dept.setDs(ConstantMenu.LOCK_ZERO);
		int message = sysDeptInfoDao.insert(dept);
		String mass="添加成功";
		if (message==0) {
			
		}
		Integer pkId = dept.getId();
		LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_dept", 1);
		logOperInfoDao.insert(logOperInfo);
		return mass;
	}
	//根据id查询
	@Override
	public Map<String, Object> selectByPrimaryKey(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		String ids=request.getParameter("id");
		int id= Integer.parseInt(ids);
		List<SysDeptInfo> deptByParentId = sysDeptInfoDao.selectDeptByParentId(id);
		if(deptByParentId == null){
			throw new BusinessException();
		}
		String flag = "no";
		if (deptByParentId != null && deptByParentId.size()>0) {
			flag = "yes";
		}
		SysDeptInfo deptAll = sysDeptInfoDao.selectByPrimaryKey(id);
		if (deptAll == null) {
			throw new BusinessException("500", "数据不存在");
		}
		List<Integer> levelList =new ArrayList<Integer>();
		Map<String, Object> listMap = new HashMap<String, Object>();
		if (deptAll.getLevel() == 2){
			levelList.add(1);
		}else if (deptAll.getLevel() == 3){
			levelList.add(2);
		}else if (deptAll.getLevel() == 4){
			levelList.add(3);
		}
		List<SysDeptInfo> parentAll = selectParent(levelList, listMap);
		map.put("flag", flag);
		map.put("deptAll", deptAll);
		map.put("parentAll", parentAll);
		return map;
	}
	//修改
	@Override
	public String updateByPrimaryKeySelective(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String ids=request.getParameter("deptId");
		int id = Integer.parseInt(ids);
		SysDeptInfo dept = setDept(request, response);
		dept.setId(id);
		int message = sysDeptInfoDao.updateByPrimaryKeySelective(dept);
		String mass="修改成功";
		if (message==0) {
			mass="修改失败";
		}
		LogOperInfo logOperInfo = Utils.insertLogOper(request, id, "sys_dept", 2);
		logOperInfoDao.insert(logOperInfo);
		return mass;
	}
	//参数封装
	@Override
	public SysDeptInfo setDept(HttpServletRequest request,HttpServletResponse response) {
		SysDeptInfo dept=new SysDeptInfo();
		String code=request.getParameter("code");
		if ("".equals(code)) {
			code=null;
		}
		dept.setCode(code);
		String deptName=request.getParameter("deptName");
		if ("".equals(deptName)) {
			deptName=null;
		}
		dept.setName(deptName);
		String deptLevel=request.getParameter("level");
		if ("".equals(deptLevel) || deptLevel==null) {
			deptLevel=null;
		}else{
			int level=Integer.parseInt(deptLevel);
			dept.setLevel(level);
		}
		String deptTel=request.getParameter("deptTel");
		if ("".equals(deptTel)) {
			deptTel=null;
		}
		dept.setTel(deptTel);
		String deptParent=request.getParameter("deptParent");
		if ("".equals(deptParent) || deptParent==null) {
			deptParent=null;
		}else{
			int parentId=Integer.parseInt(deptParent);
			dept.setParentId(parentId);
		}
		String remark=request.getParameter("remark");
		if ("".equals(remark)) {
			remark=null;
		}
		dept.setRemark(remark);
		return dept;
	}
	//条件查询
	@Override
	public Map<String, Object> selectByExample(HttpServletRequest request,HttpServletResponse response) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> listMap=new HashMap<String, Object>();
		String deptName = request.getParameter("deptName");
		if ("".equals(deptName)) {
			deptName=null;
		}
		listMap.put("name", deptName);
		List<Integer> levelList=new ArrayList<Integer>();
		String deptLevel = request.getParameter("deptLevel");
		if ("".equals(deptLevel) || deptLevel==null) {
			deptLevel=null;
		}else{
			int level=Integer.parseInt(deptLevel);
			levelList.add(level);
			listMap.put("level", levelList);
		}
		String deptParent = request.getParameter("deptParent");
		if ("".equals(deptParent) || deptParent==null) {
			deptParent=null;
		}else{
			int pId=Integer.parseInt(deptParent);
			listMap.put("parentId", pId);
		}
		String pages = request.getParameter("pages");
		if(!"".equals(pages)){
			Utils.pages(request, listMap, map);
		}else{
			int totalRow = sysDeptInfoDao.countByExample(listMap);
			Utils.paging(request, totalRow, listMap, map);
		}
		
		List<SysDeptInfo> deptAll = sysDeptInfoDao.selectByExample(listMap);
		//查找所有一二级部门
		levelList.add(1);
		levelList.add(2);
		List<SysDeptInfo> parentAll = selectParent(levelList, listMap);
		map.put("deptAll",deptAll );
		map.put("parentAll",parentAll );
		return map;
	}
	//查找所有一二级部门
	@Override
	public List<SysDeptInfo> selectParent(List<Integer> levelList,Map<String, Object> listMap) throws Exception {
		listMap.clear();
		if (levelList.size() == 0) {
			levelList = null;
		}
		listMap.put("level", levelList);
		List<SysDeptInfo> parentAll = sysDeptInfoDao.selectByExample(listMap);
		if(parentAll == null){
			throw new BusinessException();
		}
		return parentAll;
	}
	@Override
	public List<Map<String,Object>> countDeptApply(Map<String, Object> map) throws Exception {
		List<Map<String,Object>> countDeptApply = sysDeptInfoDao.countDeptApply(map);
		return countDeptApply;
	}
	@Override
	public SysDeptInfo selectTelByDeptId(int id) throws Exception {
		return sysDeptInfoDao.selectByPrimaryKey(id);
	}
	@Override
	public List<SysDeptInfo> selectDeptByLevel(Map<String, Object> map) throws Exception {
		
		return sysDeptInfoDao.selectDeptByLevel(map);
	}
	@Override
	public Map<String, Object> selectDeptsTree() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysDeptInfo> deptList = sysDeptInfoDao.selectByExample(null);
		List<SysDeptInfo> deltListOne = new ArrayList<SysDeptInfo>();
		List<SysDeptInfo> deltListTwo = new ArrayList<SysDeptInfo>();
		List<SysDeptInfo> deltListThree = new ArrayList<SysDeptInfo>();
		for(SysDeptInfo dept:deptList){
			String name = dept.getName();
			dept.setExtend2(ChineseToInitials.getPYIndexStr(name, true).toLowerCase());//首字母
			Integer level = dept.getLevel();
			if (level == 1) {
				deltListOne.add(dept);
			}else if(level == 2){
				deltListTwo.add(dept);
			}else if (level == 3) {
				deltListThree.add(dept);
			}
		}
		map.put("deltListOne", deltListOne);
		map.put("deltListTwo", deltListTwo);
		map.put("deltListThree", deltListThree);
		return map;
	}
	@Override
	public List<SysDeptInfo> selectDeptForTree() throws Exception {
		return sysDeptInfoDao.selectPrimAll();
	}
	@Override
	public SysDeptInfo deptEvent(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void deptEventExport(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<SysDeptInfo> selectDeptPareateId(Integer id) {
		return sysDeptInfoDao.selectDeptPareateId(id);
	}
}
