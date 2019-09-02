package com.qkby.sysmanage.business;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qkby.event.dao.EventFileManageInfoDao;
import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.proj.dao.ProjTaskDao;
import com.qkby.proj.dao.ProjTaskRecordDao;
import com.qkby.proj.entity.TaskRecord;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.dao.SysFileTypeInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysFileTypeInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.FilesUpload;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月20日下午5:30:21 
 *     
 * @version </pre>
 */
@Service
public class SysFileInfoBusinessImpl implements SysFileInfoBusiness{
	@Resource
    public SysFileInfoDao sysFileInfoDao;
	@Resource
    public SysUserInfoDao sysUserInfoDao;
	@Resource
	public SysFileTypeInfoDao sysFileTypeInfoDao;
	@Resource
	LogOperInfoDao logOperInfoDao;
	@Resource
	EventFileManageInfoDao eventFileManageInfoDao;
	@Resource
	ProjTaskRecordDao projTaskRecordDao;
	@Resource
	ProjTaskDao projTaskDao;
	@Override
	public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
	Map<String,Object> pageMap = new HashMap<String,Object>();
	String fileCode = request.getParameter("fileCode");
	String fileName = request.getParameter("fileName");
	String fileType = request.getParameter("fileType");
	String fileUser = request.getParameter("fileUser");
	int createUser = 0;
	if("".equals(fileUser)){
		fileUser = null;
	}else {
	SysUserInfo selectByName = sysUserInfoDao.selectByName(fileUser);
	if(selectByName == null){
		throw new BusinessException("500","");
	}
	  createUser = selectByName.getId();
	}
	if("".equals(fileCode)){
		fileCode = null;
	}
	if("".equals(fileName)){
		fileName = null;
	}
	if("".equals(fileType)){
		fileType = null;
	}
	Map<String,Object> map = new HashMap<String,Object>();
	map.put("code", fileCode);
	map.put("name", fileName);
	map.put("id_file_type", fileType);
	map.put("create_user", createUser);
	String pages = request.getParameter("pages");
	if(!"".equals(pages)){
		Utils.pages(request, map, pageMap);
	}else{
		int totalRow = sysFileInfoDao.countByExample(map);
		Utils.paging(request, totalRow, map, pageMap);
	}
	List<SysFileInfo> selectAll = sysFileInfoDao.selectAll(map);
	if(selectAll == null){
		throw new BusinessException("500","");
	}
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	for (SysFileInfo sysFileInfo : selectAll) {
		Date createDate = sysFileInfo.getCreateDate();
		sysFileInfo.setExtend3(sf.format(createDate));
	}
	pageMap.put("selectAll", selectAll);
	return pageMap;
	}
	
	@Override
	public int insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String file_code = request.getParameter("file_code");
		String file_name = request.getParameter("file_name");
		String file_type = request.getParameter("file_type");
		String file_remark = request.getParameter("file_remark");
		FilesUpload file = new FilesUpload();
		Map<String, Object> fileUpload = file.fileUpload(request,response);
		String path = (String)fileUpload.get("path_to");
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		SysFileInfo sysFileInf = new SysFileInfo();
		sysFileInf.setCode(file_code);
		sysFileInf.setName(file_name);
		sysFileInf.setPath(path);
		sysFileInf.setIdFileType(Integer.valueOf(file_type));
		sysFileInf.setRemark(file_remark);
		sysFileInf.setCreateDate(new Date());
		sysFileInf.setCreateUser(userId);
		int insert = sysFileInfoDao.insert(sysFileInf);
		Integer pkId = sysFileInf.getId();
		LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_file", 1);
		logOperInfoDao.insert(logOperInfo);
		return insert;
	}
	@Transactional
	@Override
	public int deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String[] ids = request.getParameterValues("id");
		map.put("id", ids);
		SysFileInfo selectByIdAll = sysFileInfoDao.selectByIdAll(map);
		String path = selectByIdAll.getPath();
		Utils.deleteFile(path);
		int deleteByPrimaryKey = sysFileInfoDao.deleteByPrimaryKey(map);
		for(int i=0;i<ids.length;i++){
			int pkId = Integer.valueOf(ids[i]);
			LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_file", 3);
			logOperInfoDao.insert(logOperInfo);
		}
		return deleteByPrimaryKey;
	}
	@Override
	public Map<String,Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> seleMap = new HashMap<String,Object>();
		String id = request.getParameter("id");
		List<SysFileTypeInfo> selectAll = sysFileTypeInfoDao.selectAll();
		SysFileInfo sysFile = sysFileInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		if(sysFile == null){
			throw new BusinessException("500","");
		}
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date createDate = sysFile.getCreateDate();
		sysFile.setExtend3(sf.format(createDate));
		seleMap.put("sysFile", sysFile);
		seleMap.put("selectAll", selectAll);
		return seleMap;
	}
	@Override
	public int updateByPrimaryKeySelective(HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysFileInfo sysFileInfo = new SysFileInfo();
		String fileId = request.getParameter("fileId");
		String code = request.getParameter("code");
		String name = request.getParameter("name");
		String fileType = request.getParameter("fileType");
		String remark = request.getParameter("remark");
		sysFileInfo.setId(Integer.valueOf(fileId));
		sysFileInfo.setCode(code);
		sysFileInfo.setIdFileType(Integer.valueOf(fileType));
		sysFileInfo.setName(name);
		sysFileInfo.setRemark(remark);
		int us = sysFileInfoDao.updateByPrimaryKeySelective(sysFileInfo);
		LogOperInfo logOperInfo = Utils.insertLogOper(request, Integer.valueOf(fileId), "sys_file", 2);
		logOperInfoDao.insert(logOperInfo);
		return us;
	}

	@Override
	public Map<String, Object> selectAllFileType() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysFileTypeInfo> selectAll = sysFileTypeInfoDao.selectAll();
		map.put("selectAll", selectAll);
		return map;
	}

	@Override
	public SysFileInfo selectDealFile(int id) throws Exception {
		SysFileInfo downloadDealFile = sysFileInfoDao.downloadDealFile(id);
		 if(downloadDealFile == null){
			   throw new BusinessException();
		 }
		return downloadDealFile;
	}
	@Override
	public int insertGeneralFile(SysFileInfo sysFile) throws Exception {
		return sysFileInfoDao.insert(sysFile);
	}

	@Override
	public List<SysFileInfo> selectFileByProjCode(Map<String,Object> paramMap) {
		return sysFileInfoDao.selectFileByProjCode(paramMap);
	}

	@Override
	public int deleteByExample(Map<String, Object> fileParamMap) throws Exception {
		//删除本地文件
		String id = (String) fileParamMap.get("id");
		SysFileInfo sysFile = sysFileInfoDao.selectByPrimaryKey(Integer.valueOf(id));
		String path = sysFile.getPath();
		File file = new File(path);
		file.delete();
		return sysFileInfoDao.deleteByExample(fileParamMap);
	}

	@Override
	public int deleteProjFiles(Map<String, Object> fileParamMap) throws Exception {
		return sysFileInfoDao.deleteByPrimaryKey(fileParamMap);
		
	}

	@Override
	public List<TaskRecord> getTaskRecordList(Integer taskId) {
		return projTaskRecordDao.selectTaskRecordList(taskId);
	}

	@Override
	public void deleteLocalFile(List<Integer> deleteFileIdList) throws Exception {
		//查询出所有需要删除的文件id
		List<String> selectAllDeleteId = sysFileInfoDao.selectDeleteLocalFileList(deleteFileIdList);
		File file = null;
		for (int i = 0,size = selectAllDeleteId.size();i < size; i++) {
			file = new File(selectAllDeleteId.get(i));
			file.delete();
		}
	}

}
