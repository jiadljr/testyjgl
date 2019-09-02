package com.qkby.sysmanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.sysmanage.business.SysDepInfoBusiness;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.utils.ChinesePinYin;
import com.qkby.utils.ChineseToInitials;
import com.qkby.utils.JsonResult;
import com.qkby.utils.Token;
import com.qkby.utils.Utils;

/**
 * 
 * @author L.S
 */
@Controller
public class SysDepInfoController {
	@Resource
    public SysDepInfoBusiness sysDepInfoBusiness;
	/**
	 * 跳转部门页面
	 * @author 李帅
	 */
	@RequestMapping("/sysDepart.do")
	public ModelAndView sysDepart(HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		return new ModelAndView("sys/sys_dept_list",map);
	}
	/**
	 * 根据条件查询部门信息
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectPrimAll.do")
    public Map<String, Object> selectPrimAll(HttpServletRequest request,HttpServletResponse response) throws Exception{
    	Map<String, Object> map = sysDepInfoBusiness.selectByExample(request, response);
    	return map;
    }
	/**
	 * 删除部门
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/deleteDeptByPrimaryKey.do")
	public JsonResult<String> deleteDeptByPrimaryKey(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String result = sysDepInfoBusiness.deleteByPrimaryKey(request, response);
		return new JsonResult<String>(result);
	}
	/**
	 * 跳转添加部门页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/toAddDept.do")
	@Token(remove=false, save = true)
	public ModelAndView toAddDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		List<Integer> levelList =new ArrayList<Integer>();
		Map<String, Object> listMap = new HashMap<String, Object>();
		List<SysDeptInfo> parentAll = sysDepInfoBusiness.selectParent(levelList, listMap);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		listMap.put("pageNumber", pageNumber);
		listMap.put("pageSize", pageSize);
		listMap.put("totalPage", totalPage);
		listMap.put("totalRow", totalRow);
		listMap.put("pages", pages);
		listMap.put("parentAll", parentAll);
		return new ModelAndView("sys/sys_dept_add",listMap);
	}
	/**
	 * 添加部门
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/AddDept.do")
	@Token(remove=true, save = false)
	public JsonResult<String> AddDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String mass = sysDepInfoBusiness.insert(request, response);
		return new JsonResult<String>(mass);
	}
	/**
	 * 跳转修改部门页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/toUpdDept.do")
	@Token(remove=false, save = true)
	public ModelAndView toUpdDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysDepInfoBusiness.selectByPrimaryKey(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("pages", pages);
		return new ModelAndView("sys/sys_dept_mod",map);
	}
	/**
	 * 修改部门
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/updDept.do")
	@Token(remove=true, save = false)
	public JsonResult<String> updDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String mass = sysDepInfoBusiness.updateByPrimaryKeySelective(request,response);
		return new JsonResult<String>(mass);
	}
	/**
	 * 查询一二级部门
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectDeptName.do")
	public JsonResult<Map<String, Object>> selectDeptName(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> deptMap = sysDepInfoBusiness.selectByExample(request, response);
		return new JsonResult<>(deptMap);
	}
	/**
	 * 查看部门
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/checkDept.do")
	public Map<String, Object> checkDept(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysDepInfoBusiness.selectByPrimaryKey(request, response);
		return map;
	}
	@ResponseBody
	@RequestMapping("/countDeptApply.do")
	public List<Map<String,Object>> countDeptApply(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		if("".equals(startTime)){
			startTime = null;
		}
		if("".equals(endTime)){
			endTime = null;
		}
		map.put("endTime", endTime);
		map.put("startTime", startTime);
		 List<Map<String,Object>> countDeptApply = sysDepInfoBusiness.countDeptApply(map);
		 return countDeptApply;
	}
	@ResponseBody
	@RequestMapping("/selectDeptByLevel.do")
	public List<SysDeptInfo> selectDeptByLevel(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		String deptLevel=request.getParameter("deptLevel");
		int levelId= Integer.valueOf(deptLevel);
		String dId=request.getParameter("deptId");
		if (Utils.isNum(dId)) {
			int deptId= Integer.valueOf(dId);
			map.put("deptId", deptId);
		}
		map.put("levelId", levelId);
		return sysDepInfoBusiness.selectDeptByLevel(map);
	}
	
	@ResponseBody
	@RequestMapping("findAllDept.do")
	public Map<String, Object> findAllDept() throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysDeptInfo> deptAll = sysDepInfoBusiness.selectDeptForTree();
		List<SysDeptInfo> deptOne = new ArrayList<SysDeptInfo>();
		List<SysDeptInfo> deptTwo = new ArrayList<SysDeptInfo>();
		List<SysDeptInfo> deptThree = new ArrayList<SysDeptInfo>();
		List<SysDeptInfo> deptFour = new ArrayList<SysDeptInfo>();
		for(SysDeptInfo dept : deptAll){
			if (dept.getLevel() == 1) {
				deptOne.add(dept);
			}else if (dept.getLevel() == 2) {
				deptTwo.add(dept);
			}else if (dept.getLevel() == 3) {
				deptThree.add(dept);
			}else if(dept.getLevel() == 4){
				deptFour.add(dept);
			}
			String allName=ChinesePinYin.toHanyuPinyin(dept.getName());
			dept.setExtend3(allName);
			String firstName=ChineseToInitials.getPYIndexStr(dept.getName(), true).toLowerCase();
			dept.setExtend2(firstName);
		}
		map.put("deptOne", deptOne);
		map.put("deptTwo", deptTwo);
		map.put("deptThree", deptThree);
		map.put("deptFour", deptFour);
		return map;
	}
	
	/**
	 * 根据科室统计事件，并导出Excel
	 * 2018年4月8日 下午1:59:16
	 * @李帅
	 * @param
	 */
	@RequestMapping("/deptEventReport.do")
	public void deptEventReport(HttpServletRequest req){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		String start_time = req.getParameter("start_time");
		String end_time = req.getParameter("end_time");
		paramMap.put("startTime", start_time);
		paramMap.put("endTime", end_time);
		sysDepInfoBusiness.deptEventExport(paramMap);
	}
	@RequestMapping("/selectDeptParentId.do")
	@ResponseBody
	public List<SysDeptInfo> selectDeptParentId(HttpServletRequest req){
		String id = req.getParameter("id");
		List<SysDeptInfo> selectDeptPareateId = sysDepInfoBusiness.selectDeptPareateId(Integer.valueOf(id));
		return selectDeptPareateId;
	}
}
