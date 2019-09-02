package com.qkby.proj.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.proj.business.ProjFileBusiness;
import com.qkby.proj.business.ProjManageBusiness;
import com.qkby.proj.entity.ProjectFile;
import com.qkby.proj.entity.ProjectInformation;
import com.qkby.proj.entity.ProjectTask;
import com.qkby.sysmanage.business.SysFileInfoBusiness;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.utils.CreateZip;
import com.qkby.utils.ReadFile;
import com.qkby.utils.Utils;

@Controller
@Scope("prototype")
public class ProjFileController {
	@Resource
	private ProjFileBusiness projFileBusiness;
	@Resource
	private SysFileInfoBusiness sysFileInfoBusiness;
	
	@Resource
	private ProjManageBusiness ProjManagerBusiness;
	
	/**
	 * 跳转项目文件管理页面
	 * 2018年1月16日 上午11:16:55
	 * @李帅
	 * @param
	 */
	@RequestMapping("toProjFilePage.do")
	public ModelAndView toProjFilePage(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		return new ModelAndView("proj/proj_file_list",map);
	}
	
	/**
	 * 加载项目文件列表
	 * 2018年1月16日 上午11:17:33
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("projFileList.do")
	public Map<String, Object> projFileList(HttpServletRequest request) throws Exception{
		String user_id = String.valueOf(request.getSession().getAttribute("user_id"));
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		String goodLevel = request.getParameter("goodLevel");
		String projName = request.getParameter("projName");
		if (projName.equals("")) {
			projName = null;
		}
		if (goodLevel.equals("")) {
			goodLevel = null;
		}
		paramMap.put("goodLevel", goodLevel);
		paramMap.put("projName", projName);
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		if (!"".equals(pages) && pages != null) {
			Utils.pages(request, paramMap, map);
		} else {
			int totalRow = projFileBusiness.countProjInfoFile(paramMap);
			Utils.paging(request, totalRow, paramMap, map);
		}
		paramMap.put("id_user", user_id);
		List<ProjectFile> projFileList = projFileBusiness.projFileList(paramMap);
		map.put("projFileList", projFileList);
		return map;
	}
	
	/**
	 * 跳转添加项目文件页面
	 * 2018年1月16日 上午11:20:39
	 * @李帅
	 * @param
	 */
	@RequestMapping("toAddProjFile.do")
	public ModelAndView toAddProjFile(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		return new ModelAndView("proj/proj_file_add",map);
	}
	
	/**
	 * 添加项目文件
	 * 2018年1月16日 上午11:21:04
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("addProjFile.do")
	public String addProjFile(HttpServletRequest request) throws Exception{
		ProjectFile projFile = new ProjectFile();
		projFileBusiness.addProjFile(projFile);
		return null;
	}
	
	/**
	 * 跳转修改页面
	 * 2018年1月16日 上午11:21:16
	 * @李帅
	 * @param
	 */
	@RequestMapping("toUpdateProjFile.do")
	public ModelAndView toUpdateProjFile(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("pages", pages);
		return new ModelAndView("proj/proj_file_mod",map);
	}
	
	/**
	 * 修改
	 * 2018年1月16日 上午11:21:33
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("updateProjFile.do")
	public String updateProjFile(HttpServletRequest request) throws Exception{
		ProjectFile projFile = new ProjectFile();
		projFileBusiness.updateProjFile(projFile);
		return null;
	}
	
	/**
	 * 删除文件
	 * 2018年1月16日 上午11:35:17
	 * @李帅
	 * @param
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/deleteFile.do")
	public String deleteProjFile(HttpServletRequest request) throws Exception{
		String fileId = request.getParameter("fileId");
		String projFileId = request.getParameter("projFileId");
		projFileBusiness.deleteProjFile(Integer.valueOf(projFileId));
		Map<String, Object> fileParamMap = new HashMap<String, Object>();
		fileParamMap.put("id", fileId);
		sysFileInfoBusiness.deleteByExample(fileParamMap);
		return "succ";
	}
	
	@ResponseBody
	@RequestMapping("/whetherOrNotFile.do")
	public Map<String, Object> whetherOrNotFile(HttpServletRequest req) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String projCode = req.getParameter("projCode");
		ProjectInformation proj = ProjManagerBusiness.selectProjByPrimaryKey(projCode);
	 	String sourceFilePath = proj.getProjPath(); //项目文件夹
	 	ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String zipName = resource.getString("zipName");
        String fileName = proj.getProjName();  
        String zipFilePath = zipName+"/" + fileName + ".zip";  
        String result = CreateZip.createZip(sourceFilePath, zipFilePath);
        map.put("result", result);
        map.put("zipFilePath", zipFilePath);
        map.put("fileName", fileName);
		return map;
	}
	
	/**
	 * 导出所有的项目文件
	 * 2018年4月17日 下午4:11:34
	 * @李帅
	 * @param
	 */
	@RequestMapping("exportAllFile.do")
	public void exportAllFile(HttpServletRequest req,HttpServletResponse resp) throws Exception{
		String zipFilePath = req.getParameter("zipFilePath");
		String fileName = req.getParameter("fileName");
		ReadFile.downProjZipFile(resp, req, zipFilePath, fileName, this);
	}
	
	//回调函数
	public void delteProjZipFile(String zipFilePath) {
		File file = new File(zipFilePath);
		file.delete();
	}
}
