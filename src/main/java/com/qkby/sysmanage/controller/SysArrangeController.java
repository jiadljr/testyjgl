package com.qkby.sysmanage.controller;

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
import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.business.SysArrangeBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.sysmanage.entity.SysArrange;
import com.qkby.utils.JsonResult;
import com.qkby.utils.Utils;


@Controller
public class SysArrangeController {
	@Resource
	SysArrangeBusiness sysArrangeBusiness;
	@Resource
	SysUserInfoBusiness sysUserInfoBusiness;
	
	/**
	 * 跳转排班界面
	 * 2017年12月27日 下午6:12:24
	 * @李帅
	 */
	@RequestMapping("toArrangeList.do")
	public ModelAndView toArrangeList(HttpServletRequest request){
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
		return new ModelAndView("sys/sys_arrange_list",map);
	}
	/**
	 * 查询值班列表
	 * 2018年1月2日 下午3:17:55
	 * @李帅
	 * @param request
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("selectArrangeList.do")
	public Map<String, Object> selectArrangeList(HttpServletRequest request) throws Exception{
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String pages = request.getParameter("pages");
		String oper_id = request.getParameter("oper_id");
		String duty_start_time = request.getParameter("duty_start_time");
		String duty_end_time = request.getParameter("duty_end_time");
		if (Utils.isNum(oper_id)) {
			int id_user = Integer.valueOf(oper_id);
			pgMap.put("id_user", id_user);
		}
		if (duty_start_time.equals("")) {
			duty_start_time = null;
		}
		if (duty_end_time.equals("")) {
			duty_end_time = null;
		}
		pgMap.put("duty_start_time", duty_start_time);
		pgMap.put("duty_end_time", duty_end_time);
		pgMap.put("now_time", Utils.getYesterday());
		Map<String, Object> arrangeMap = sysArrangeBusiness.selectArrangeList(pgMap, pages, request);
		return arrangeMap;
	}
	/**
	 * 跳转添加页面
	 * 2018年1月2日 下午3:18:18
	 * @李帅
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("toAddArrange.do")
	public ModelAndView toAddArrange(HttpServletRequest request) throws Exception{
		Map<String,Object> pgMap = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		Map<String, Object> operList = sysUserInfoBusiness.userByRoleType(pgMap, request);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		operList.put("pageNumber", pageNumber);
		operList.put("pageSize", pageSize);
		operList.put("totalPage", totalPage);
		operList.put("totalRow", totalRow);
		operList.put("pages", pages);
		return new ModelAndView("sys/sys_arrange_add",operList);
	}
	/**
	 * 添加值班
	 * 2018年1月2日 下午3:18:33
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping("addArrange.do")
	public Map<String, Object> addArrange(HttpServletRequest request) throws Exception{
		/**参数*/
		String duty_user = request.getParameter("duty_user");
		String duty_start_time = request.getParameter("duty_start_time");
		String duty_end_time = request.getParameter("duty_end_time");
		String timeScope = request.getParameter("timeScope");
		Map<String, Object> resultMap = sysArrangeBusiness.insertArrange(duty_user,duty_start_time,duty_end_time,request,timeScope);
		return resultMap;
	}
	/**
	 * 跳转修改页面
	 * 2018年1月2日 下午3:18:45
	 * @李帅
	 * @param request
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/toUpdateArrange.do")
	public ModelAndView toUpdateArrange(HttpServletRequest request) throws NumberFormatException, Exception{
		String user_id = request.getParameter("user_id");
		Map<String, Object> operList = sysArrangeBusiness.selectByPrimaryKey(Integer.valueOf(user_id));
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		operList.put("pageNumber", pageNumber);
		operList.put("pageSize", pageSize);
		operList.put("totalPage", totalPage);
		operList.put("totalRow", totalRow);
		operList.put("pages", pages);
		return new ModelAndView("sys/sys_arrange_mod",operList);
	}
	/**
	 * 修改值班
	 * 2018年1月2日 下午3:19:04
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/updateArrange.do")
	public JsonResult<String> updateArrange(HttpServletRequest request) throws Exception{
		String duty_id = request.getParameter("duty_id");
		String duty_user = request.getParameter("duty_user");
		SysArrange arrange = new SysArrange();
		arrange.setId(Integer.valueOf(duty_id));
		arrange.setIdUser(Integer.valueOf(duty_user));
		sysArrangeBusiness.updateArrange(arrange);
		return new JsonResult<String>("success");
	}
	/**
	 * 判断是否有可以导出的数据
	 * 2018年1月14日 下午12:07:18
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("ifHaveData.do")
	public void ifHaveData() throws Exception{
		sysArrangeBusiness.ifHaveData();
	}
	/**
	 * 排班表导出
	 * 2018年1月3日 下午6:21:08
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/dutyExport.do")
	public void dutyExport(HttpServletResponse response,HttpServletRequest request) throws Exception{
		sysArrangeBusiness.dutyExport(response, request);
	}
	
	@ResponseBody
	@RequestMapping("/deleteArrange.do")
	public String deleteArrange(HttpServletRequest request) throws NumberFormatException, Exception{
		String dutyId = request.getParameter("dutyId");
		String deleteArrange = sysArrangeBusiness.deleteArrange(Integer.valueOf(dutyId));
		return deleteArrange;
	}
	
	@ResponseBody
	@RequestMapping("/selectDutyArrange.do")
	public List<SysArrange> selectDutyArrange(){
		return sysArrangeBusiness.selectDutyArrange();
	}
}
