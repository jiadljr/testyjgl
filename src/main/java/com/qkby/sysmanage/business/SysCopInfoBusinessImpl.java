package com.qkby.sysmanage.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;
import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.sysmanage.dao.SysCopInfoDao;
import com.qkby.sysmanage.entity.SysCopInfo;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月14日下午2:36:18 
 *     
 * @version </pre>
 */
@Service
public class SysCopInfoBusinessImpl implements SysCopInfoBusiness{
   @Resource
   public SysCopInfoDao sysCmpyInfoDao;
   @Resource
   LogOperInfoDao logOperInfoDao;

@Override
public List<SysCopInfo> selectCmpyAll() throws Exception {
	List<SysCopInfo> selectCmpyAll = sysCmpyInfoDao.selectCmpyAll();
	return selectCmpyAll;
}

@Override
public int deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Map<String, Object> map = new HashMap<String, Object>();
	String[] id = request.getParameterValues("id");
	map.put("id", id);
	int de = sysCmpyInfoDao.deleteByPrimaryKey(map);
	for(int i = 0;i<id.length;i++){
		int pkId = Integer.valueOf(id[i]);
		LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId,"sys_cmpy", 3);
		logOperInfoDao.insert(logOperInfo);
	}
	return de;
}

@Override
public Map<String,Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Map<String,Object> map = new HashMap<String,Object>();
	Map<String,Object> pgMap = new HashMap<String,Object>();
	String comName = request.getParameter("comName");
	String comAbbre = request.getParameter("comAbbre");
	String comNum = request.getParameter("comNum");
	if("".equals(comName)){
		comName = null;
	}
	if("".equals(comAbbre)){
		comAbbre = null;
	}
	if("".equals(comNum)){
		comNum = null;
	}
	pgMap.put("name", comName);
	pgMap.put("code", comNum);
	pgMap.put("subName", comAbbre);
	String pages = request.getParameter("pages");
	if(!"".equals(pages)){
		Utils.pages(request, pgMap, map);
	}else{
		int totalRow = sysCmpyInfoDao.countByExample(pgMap);
		Utils.paging(request, totalRow, pgMap, map);
	}
	List<SysCopInfo> selectByExample = sysCmpyInfoDao.selectByExample(pgMap);
	if(selectByExample == null){
		throw new BusinessException("500","");
	}
	map.put("copList", selectByExample);
	return map;
}

@Override
public int insertCmpy(HttpServletRequest request, HttpServletResponse response) throws Exception {
	String meauName = request.getParameter("meauName");
	String meauGrade = request.getParameter("meauGrade");
	String meauUp = request.getParameter("meauUp");
	String meauLink = request.getParameter("meauLink");
	String meauDesc = request.getParameter("meauDesc");
	String meauImage = request.getParameter("meauImage");
	String remark = request.getParameter("remark");
	if(meauName == null || meauGrade == null || meauUp == null || meauLink == null || meauDesc == null || meauImage == null || remark == null){
		throw new BusinessException("", "新增单位信息失败!填写信息不全");
	}
	SysCopInfo record = new SysCopInfo();
	record.setAddr(meauImage);
	record.setCode(meauName);
	record.setName(meauGrade);
	record.setMail(meauDesc);
	record.setRemark(remark);
	record.setSubName(meauUp);
	record.setTel(meauLink);
	int sus = sysCmpyInfoDao.insert(record);
	Integer pkId = record.getId();
	LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId,"sys_cmpy", 1);
	logOperInfoDao.insert(logOperInfo);
	return sus;
}

@Override
public Map<String,Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Map<String,Object> map = new HashMap<String,Object>();
	String ids = request.getParameter("id");
	int id =Integer.valueOf(ids);
	SysCopInfo sysCmp = sysCmpyInfoDao.selectByPrimaryKey(id);
	if(sysCmp == null){
		throw new BusinessException("","查不到该单位的详细信息");
	}
	map.put("sysCmp", sysCmp);
	return map;
}

@Override
public int updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response) throws Exception {
	SysCopInfo sysCmp = new SysCopInfo();
	String compId = request.getParameter("compId");
	String meauCode = request.getParameter("meauCode");
	String meauName = request.getParameter("meauName");
	String meauGrade = request.getParameter("meauGrade");
	String meauUp = request.getParameter("meauUp");
	String meauLink = request.getParameter("meauLink");
	String meauDesc = request.getParameter("meauDesc");
	String remarks = request.getParameter("remark");
	if(compId == null || meauName == null || meauGrade == null || meauUp == null || meauLink == null || meauDesc == null || meauCode == null || remarks == null){
		throw new BusinessException("", "修改单位信息失败!填写信息不全");
	}
	sysCmp.setId(Integer.valueOf(compId));
	sysCmp.setCode(meauCode);
	sysCmp.setName(meauName);
	sysCmp.setSubName(meauGrade);
	sysCmp.setRemark(remarks);
	sysCmp.setMail(meauLink);
	sysCmp.setTel(meauUp);
	sysCmp.setAddr(meauDesc);
	int un = sysCmpyInfoDao.updateByPrimaryKeySelective(sysCmp);
	LogOperInfo logOperInfo = Utils.insertLogOper(request, Integer.valueOf(compId), "sys_cmpy", 2);
	logOperInfoDao.insert(logOperInfo);
	return un;
}
}
