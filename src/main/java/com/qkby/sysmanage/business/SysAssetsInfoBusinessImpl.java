package com.qkby.sysmanage.business;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.logs.dao.LogOperInfoDao;
import com.qkby.logs.entity.LogOperInfo;
import com.qkby.sysmanage.dao.SysAssetsGradeDao;
import com.qkby.sysmanage.dao.SysAssetsInfoDao;
import com.qkby.sysmanage.dao.SysAssetsTypeDao;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysAssetsGrade;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.sysmanage.entity.SysAssetsType;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月22日下午3:08:53 
 *     
 * @version </pre>
 */
@Service
public class SysAssetsInfoBusinessImpl implements SysAssetsInfoBusiness{
	   @Resource
       public SysAssetsInfoDao sysAccetsInfoDao;
	   @Resource
	   public SysAssetsTypeDao sysAssetsTypeDao;
	   @Resource
	   public SysDeptInfoDao sysDeptInfoDao;
	   @Resource
	   LogOperInfoDao logOperInfoDao;
	   @Resource
	   SysUserInfoDao sysUserInfoDao;
	   @Resource
	   SysAssetsGradeDao sysAssetsGradeDao;

		@Override
		public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			Map<String,Object> pgMap = new HashMap<String,Object>();
			String asManuDept = request.getParameter("asManuDept");
			String asModel = request.getParameter("asModel");
			String name = request.getParameter("name");
			String code = request.getParameter("code");
			if("".equals(asManuDept)){
				asManuDept = null;
			}
			if("".equals(asModel)){
				asModel = null;
			}
			if("".equals(name)){
				name = null;
			}
			if("".equals(code)){
				code = null;
			}
			pgMap.put("name", name);
			pgMap.put("code", code);
			pgMap.put("asModel", asModel);
			pgMap.put("idDept", asManuDept);
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String pages = request.getParameter("pages");
			if(!"".equals(pages)){
			 Utils.pages(request, pgMap, map);
			}else{
				//查询总条数
				int totalRow = sysAccetsInfoDao.selectCount(pgMap);
				//分页查询
				Utils.paging(request, totalRow, pgMap, map);
			}
			List<SysAssetsInfo> assetsList = sysAccetsInfoDao.selectByExample(pgMap);
			if(assetsList == null){
				throw new BusinessException("500","");
			}
			for (SysAssetsInfo sysAssetsInfo : assetsList) {
				Date createDate = sysAssetsInfo.getCreateDate();
				String format = sf.format(createDate);
				sysAssetsInfo.setExtend3(format);			
			}
			map.put("assetsList", assetsList);
			return map;
		}

		@Override
		public int insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			int userId = (int)session.getAttribute("user_id");
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			String asModel = request.getParameter("asModel");
			String sourceId = request.getParameter("sourceId");
			String asManuf = request.getParameter("asManuf");
			String asManuDept = request.getParameter("idDept");
			String status = request.getParameter("status");
			if(code == null || name == null || asModel == null || sourceId == null || asManuf == null || asManuDept == null || status == null){
				throw new BusinessException("", "");
			}
			SysAssetsInfo sysAssetsInfo = new SysAssetsInfo();
			sysAssetsInfo.setCode(code);
			sysAssetsInfo.setName(name);
			sysAssetsInfo.setAsModel(asModel);
			sysAssetsInfo.setIdType(Integer.valueOf(sourceId));
			sysAssetsInfo.setAsManuf(asManuf);
			sysAssetsInfo.setCreateDate(new Date());
			sysAssetsInfo.setIdDept(Integer.valueOf(asManuDept));
			sysAssetsInfo.setStatus(Integer.valueOf(status));
			sysAssetsInfo.setCreateUser(userId);
			int insert = sysAccetsInfoDao.insert(sysAssetsInfo);
			Integer pkId = sysAssetsInfo.getId();
			LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_assets", 1);
			logOperInfoDao.insert(logOperInfo);
			return insert;
		}

		@Override
		public int deleteAssets(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			String[] id = request.getParameterValues("id");
			map.put("ds",ConstantMenu.DS_ONE);
			map.put("id", id);
			int de = sysAccetsInfoDao.updateDs(map);
			if(de == 0){
				throw new BusinessException("", "删除资产失败！未找到该资产信息");
			}
			for(int i = 0;i<id.length;i++){
				int pkId = Integer.valueOf(id[i]);
				LogOperInfo logOperInfo = Utils.insertLogOper(request, pkId, "sys_assets", 3);
				logOperInfoDao.insert(logOperInfo);
			}
			return de;
		}

		@Override
		public Map<String,Object> selectByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			String id = request.getParameter("id");
			SysAssetsInfo seleAss = sysAccetsInfoDao.selectByPrimaryKey(Integer.valueOf(id));
			if(seleAss == null){
				throw new BusinessException("500","");
			}
			Integer idDept = seleAss.getIdDept();
			Integer deptId = 0;
			String deptName = "";
			List<SysDeptInfo> deptList = sysDeptInfoDao.selectPrimAll();
			for (SysDeptInfo sysDeptInfo : deptList) {
				 deptId = sysDeptInfo.getId();
				 if(idDept == deptId){
					 deptName = sysDeptInfo.getName();
					 map.put("deptId", deptId);
					 map.put("deptName", deptName);
				 }
			}
			map.put("deptList", deptList);
			map.put("seleAss", seleAss);
			return map;
		}
		@Override
		public Map<String, Object> addAssets() throws Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			List<SysAssetsType> AassList = sysAssetsTypeDao.selectAll();
			List<SysDeptInfo> deptList = sysDeptInfoDao.selectPrimAll();
			map.put("assList", AassList);
			map.put("deptList", deptList);
			return map;
		}

		@Override
		public int updateAssets(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession();
			int userId = (int)session.getAttribute("user_id");
			String code = request.getParameter("code");
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String asModel = request.getParameter("asModel");
			String sourceId = request.getParameter("sourceId");
			String asManuf = request.getParameter("asManuf");
			String asManuDept = request.getParameter("idDept");
			String status = request.getParameter("status");
			if(code == null || id == null || name == null || asModel == null
		       || sourceId == null || asManuf == null || asManuDept == null  || status == null){
				throw new BusinessException("", "修改信息失败！填写的信息不完整");
			}
			SysAssetsInfo sysAssetsInfo = new SysAssetsInfo();
			sysAssetsInfo.setId(Integer.valueOf(id));
			sysAssetsInfo.setCode(code);
			sysAssetsInfo.setName(name);
			sysAssetsInfo.setAsModel(asModel);
			if(!"".equals(sourceId)){
				sysAssetsInfo.setIdType(Integer.valueOf(sourceId));
			}
			sysAssetsInfo.setAsManuf(asManuf);
			sysAssetsInfo.setUpdateDate(new Date());
			if(!"".equals(asManuDept)){
				sysAssetsInfo.setIdDept(Integer.valueOf(asManuDept));
			}
			sysAssetsInfo.setStatus(Integer.valueOf(status));
			sysAssetsInfo.setUpdateUser(userId);
			int as = sysAccetsInfoDao.updateByPrimaryKeySelective(sysAssetsInfo);
			LogOperInfo logOperInfo = Utils.insertLogOper(request, Integer.valueOf(id), "sys_assets", 2);
			logOperInfoDao.insert(logOperInfo);
			return as;
		}

		@Override
		public Map<String, Object> seleAssets() throws Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			List<SysDeptInfo> deptList = sysDeptInfoDao.selectPrimAll();
			map.put("deptList", deptList);
			return map;
		}

		@Override
		public Map<String, Object> seleAssById(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, Exception {
			Map<String,Object> map = new HashMap<String,Object>();
			String id = request.getParameter("id");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<SysAssetsInfo> seleAss = sysAccetsInfoDao.seleAssById(Integer.valueOf(id));
			 if(seleAss == null){
				 throw new BusinessException("500","该资产信息不存在");
			 }
			for (SysAssetsInfo sysAssetsInfo : seleAss) {
				Date createDate = sysAssetsInfo.getCreateDate();
				String format = sf.format(createDate);
				sysAssetsInfo.setExtend3(format);			
			}
			map.put("seleAss", seleAss);
			return map;
		}

		@Override
		public List<SysAssetsInfo> selectAssetsByThreeLayer(int typeId) throws Exception {
			Map<String, Object> pgMap = new HashMap<String, Object>();
			pgMap.put("idType", typeId);
			return sysAccetsInfoDao.selectByExample(pgMap);
		}

		@Override
		public int insertAssetsByList(List<List<String>> excelList,HttpServletRequest req) throws Exception {
			int insert = 0;
			//查询所有资产类型
			List<SysAssetsType> assetsTypeAll = sysAssetsTypeDao.selectPrimAll();
			/*//查询所有人员
			List<SysUserInfo> userAll = sysUserInfoDao.selectUserAll();
			//查询所有部门
			List<SysDeptInfo> deptAll = sysDeptInfoDao.selectPrimAll();*/
			//资产等级
			List<SysAssetsGrade> gradeAll = sysAssetsGradeDao.selectAssetsGradeAll();
			Date today = new Date();//创建日期
			int createUser = (int) req.getSession().getAttribute("user_id");//创建人员
			for(List<String> s :excelList){
				SysAssetsInfo assets = new SysAssetsInfo();
				assets.setCreateDate(today);
				assets.setCreateUser(createUser);
				assets.setStatus(ConstantMenu.LOCK_ZERO);
				assets.setDs(ConstantMenu.LOCK_ZERO);
				if (!"".equals(s.get(0))) {
					assets.setCode(s.get(0));//code
				}
				if (!"".equals(s.get(1))) {
					assets.setName(s.get(1));//name
				}
				
				/**资产等级*/
				if (!"".equals(s.get(5))) {
					for(SysAssetsGrade g:gradeAll){
						if (g.getName().equals(s.get(5).trim())) {
							assets.setIdGrade(g.getId());
						}
					}
				}
				/**资产类型*/
				int layer = 1;
				String assetsType = s.get(2).trim();
				if (!"".equals(s.get(3).trim())) {
					assetsType = s.get(3).trim();
					layer = 2;
				}
				if (!"".equals(s.get(4).trim())) {
					assetsType = s.get(4).trim();
					layer = 3;
				}
				
				if (!"".equals(assetsType)) {
					for(SysAssetsType type:assetsTypeAll){
						Integer layer2 = type.getLayer();
						String name = type.getName();
						if (name.equals(assetsType) && layer2 == layer) {
							assets.setIdType(type.getId());
						}
					}
				}
				
				
				if (!"".equals(s.get(6))) {
					assets.setAsModel(s.get(6));
				}
				if (!"".equals(s.get(7))) {
					assets.setAsIp(s.get(7));
				}
				if (!"".equals(s.get(8))) {
					assets.setRemark(s.get(8));
				}
				if (!"".equals(s.get(9))) {
					assets.setAsManuf(s.get(9));
				}
				if (!"".equals(s.get(10))) {
					assets.setAsAddr(s.get(10));
				}
				/**资产所属人员*//*
				for (SysUserInfo user : userAll) {
					if (user.getName().equals(s.get(11).trim())) {
						assets.setIdUser(user.getId());;
					}
				}
				*//**资产所属部门*//*
				for(SysDeptInfo dept: deptAll){
					if (dept.getName().equals(s.get(12).trim())) {
						assets.setIdDept(dept.getId());
					}
				}*/
				insert = sysAccetsInfoDao.insert(assets);
	    	}
			return insert;
		}

		/**
		 * 
		 */
		@Override
		public void downLoadExcel(File f ,HttpServletResponse response) throws IOException{
			// 设置response参数，可以打开下载页面
		       response.reset();
		       response.setContentType("application/vnd.ms-excel;charset=utf-8");
		       try {
		           response.setHeader("Content-Disposition", "attachment;filename="+ new String(("资产批量导入模板" + ".xlsx").getBytes(), "iso-8859-1"));//下载文件的名称
		       } catch (UnsupportedEncodingException e) {
		           e.printStackTrace();
		       }
		       BufferedInputStream bis = null;
		       BufferedOutputStream bos = null;
		       try {
		    	   ServletOutputStream out = response.getOutputStream();
		           bis = new BufferedInputStream(new FileInputStream(f));
		           bos = new BufferedOutputStream(out);
		           byte[] buff = new byte[2048];
		           int bytesRead;
		           while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		               bos.write(buff, 0, bytesRead);
		           }
		       } catch (final IOException e) {
		           throw e;
		       } finally {
		           if (bis != null){
		        	   bis.close();
		           }
		           if (bos != null){
		        	   bos.close();
		           }
		       }
		}
      
  }
