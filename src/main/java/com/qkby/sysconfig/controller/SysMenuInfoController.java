package com.qkby.sysconfig.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.exception.BusinessException;
import com.qkby.sysconfig.business.SysMenuInfoBusiness;
import com.qkby.sysconfig.business.SysRoleInfoBusiness;
import com.qkby.sysconfig.entity.SysMenuInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.JsonResult;
import com.qkby.utils.Token;
import com.qkby.utils.Utils;

/**
 * 
 * @author L.S
 */
@Controller
public class SysMenuInfoController {
	@Resource
	private SysMenuInfoBusiness sysMenuInfoBusiness;
	@Resource
	private SysRoleInfoBusiness sysRoleInfoBusiness;
	
	/**
	 * 跳转到菜单页面
	 * @author 李帅
	 */
	@RequestMapping("/toConfigureMenu.do")
	public ModelAndView toConfigureMenu (HttpServletRequest request, HttpServletResponse response){
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
		return new ModelAndView("/configure/con_menu_list",map);
	}
	/**
	 * 根据条件查询菜单
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectByExample.do")
	public Map<String, Object> selectByExample(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysMenuInfoBusiness.selectByExample(request, response);
		return map;
	}
	/**
	 * 根据id删除菜单
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/deleteByPrimaryKey.do")
	public JsonResult<String> deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String result= sysMenuInfoBusiness.deleteByPrimaryKey(request, response);
		return new JsonResult<String>(result);
	}
	/**
	 * 跳转到添加菜单页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/toAddMenu.do")
	@Token(remove=false, save = true)
	public ModelAndView toAddMenu (HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map=new HashMap<String, Object>();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		List<SysMenuInfo> parName = sysMenuInfoBusiness.selectParName();
		map.put("parName", parName);
		return new ModelAndView("configure/con_menu_add",map);
	}
	/**
	 * 添加菜单操作
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/insertMenu.do")
	@Token(remove=true, save = false)
	public void insertMenu (HttpServletRequest request, HttpServletResponse response) throws Exception{
		sysMenuInfoBusiness.insertSysMenu(request, response);
	}
	/**
	 * 跳转修改菜单页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/toModMenu.do")
	@Token(remove=false, save = true)
	public ModelAndView toModMenu (HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysMenuInfoBusiness.selectByPrimaryKey(request, response);
		List<SysMenuInfo> parName = sysMenuInfoBusiness.selectParName();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		map.put("pageNumber", pageNumber);
		map.put("pageSize", pageSize);
		map.put("totalPage", totalPage);
		map.put("totalRow", totalRow);
		map.put("parName", parName);
		return new ModelAndView("configure/con_menu_mod", map);
	}
	/**
	 * 修改菜单操作
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/modMenu.do")
	@Token(remove=true, save = false)
	public void modMenu (HttpServletRequest request, HttpServletResponse response) throws Exception{
		sysMenuInfoBusiness.updateByPrimaryKeySelective(request, response);
	}
	/**
	 * 查看单条菜单信息
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/checkMenu.do")
	public JsonResult<Map<String, Object>> checkMenu (HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysMenuInfoBusiness.selectByPrimaryKey(request, response);
		return new JsonResult<Map<String, Object>>(map);
	}
	
	@ResponseBody
	@RequestMapping("/loadMenu.do")
	public Map<String,List<SysMenuInfo>> loadMenu(HttpServletRequest request) throws Exception{
		SysUserInfo user = (SysUserInfo)(request.getSession().getAttribute("user"));
		Integer userId = (Integer)request.getSession().getAttribute("user_id");
		List<String> list = new ArrayList<String>();
		request.setAttribute("mode", list);
		ServletContext servletContext = request.getSession().getServletContext();
		Object dutyY = servletContext.getAttribute("dutyYes");
		int dutyYes = 0;
		if(dutyY != null && dutyY != ""){
			dutyYes = Integer.parseInt(dutyY.toString());
		}
		String basePath = request.getSession().getServletContext().getRealPath("/");
		return sysMenuInfoBusiness.loadMenu(user,dutyYes,userId,basePath,request);
	}
	@ResponseBody
	@RequestMapping("/selectMenuByLevel.do")
	public List<SysMenuInfo> selectMenuByLevel(HttpServletRequest request) throws Exception{
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String levelId = request.getParameter("meauGrade");
		pgMap.put("levelId", levelId);
		String mId = request.getParameter("menuId");
		if (Utils.isNum(mId)) {
			int menuId = Integer.valueOf(mId);
			pgMap.put("menuId", menuId);
		}
		List<SysMenuInfo> menuByLevel = sysMenuInfoBusiness.selectMenuByLevel(pgMap);
		if(menuByLevel == null){
			throw new BusinessException("500","");
		}
		return menuByLevel;
	}
}
