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
import com.qkby.sysconfig.business.SysRoleInfoBusiness;
import com.qkby.sysmanage.business.SysArrangeBusiness;
import com.qkby.sysmanage.business.SysCopInfoBusiness;
import com.qkby.sysmanage.business.SysDepInfoBusiness;
import com.qkby.sysmanage.business.SysPostInfoBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.sysmanage.business.SysUserPostInfoBusiness;
import com.qkby.sysmanage.entity.SysCopInfo;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysPostInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.sysmanage.entity.SysUserPostInfo;
import com.qkby.utils.JsonResult;
import com.qkby.utils.Token;

/**
 * 
 * @author L.S
 */
@Controller
public class SysUserInfoController {
	@Resource SysUserInfoBusiness sysUserInfoBusiness;
	@Resource SysDepInfoBusiness sysDepInfousiness;
	@Resource SysCopInfoBusiness sysCopInfoBusiness;
	@Resource SysPostInfoBusiness sysPostInfoBusiness;
	@Resource SysRoleInfoBusiness sysRoleInfoBusiness;
	@Resource SysUserPostInfoBusiness sysUserPostInfoBusiness;
	@Resource SysArrangeBusiness sysArrangeBusiness;
	
	/**
	 * 跳转人员页面
	 * @author 李帅
	 */
	@RequestMapping("/sysUser.do")
	public ModelAndView sysUser(HttpServletRequest request, HttpServletResponse response){
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
		return new ModelAndView("/sys/sys_user_list",map);
	}
	
	/**
	 * 根据条件查询人员信息
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectUserAll.do")
	public Map<String, Object> selectUserAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysUserInfoBusiness.selectByExample(request, response);
		return map;
	}
	
	/**
	 * 跳转添加人员页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/toAddUser.do")
	@Token(remove=false, save = true)
	public ModelAndView toAddUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
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
        List<SysDeptInfo> selectPrimAll = sysDepInfousiness.selectPrimAll(request, response);
		List<SysCopInfo> selectCmpyAll = sysCopInfoBusiness.selectCmpyAll();
		List<SysPostInfo> postAll = sysPostInfoBusiness.selectPostAll(); 
		map.put("selectPrimAll", selectPrimAll);
		map.put("selectCmpyAll", selectCmpyAll);
		map.put("postAll", postAll);
		return new ModelAndView("/sys/sys_user_add",map);
	}
	/**
	 * 添加人员
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/addUser.do")
	@Token(remove=true, save = false)
	public JsonResult<String> addUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String mass = sysUserInfoBusiness.insert(request, response);
		return new JsonResult<String>(mass);
	}
	/**
	 * 跳转修改人员页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/toUpdUser.do")
	@Token(remove=false, save = true)
	public ModelAndView toUpdUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map=sysUserInfoBusiness.selectByPrimaryKey(request, response);
		List<SysDeptInfo> selectPrimAll = sysDepInfousiness.selectPrimAll(request, response);
		int deptId = 0;
		String deptName = "";
		String depTel = "";
		int object = (int)map.get("idDept");
		for (SysDeptInfo sysDeptInfo : selectPrimAll) {
			deptId = sysDeptInfo.getId();
			if(object == deptId){
				deptName = sysDeptInfo.getName();
				depTel = sysDeptInfo.getTel();
				map.put("deptId", deptId);
				map.put("deptName", deptName);
				map.put("depTel", depTel);
				continue;
			}
		}
		List<SysCopInfo> selectCmpyAll = sysCopInfoBusiness.selectCmpyAll();
		List<SysPostInfo> postAll = sysPostInfoBusiness.selectPostAll();
		SysUserPostInfo userPostAll = sysUserPostInfoBusiness.selectPostByUserId(request, response);
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
		map.put("selectPrimAll", selectPrimAll);
		map.put("selectCmpyAll", selectCmpyAll);
		map.put("postAll", postAll);
		map.put("userPostAll", userPostAll);
		return new ModelAndView("/sys/sys_user_mod",map);
	}
	/**
	 * 修改人员
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/updUser.do")
	@Token(remove=true, save = false)
	public void updUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		sysUserInfoBusiness.updateByPrimaryKeySelective(request, response);
	}
	/**
	 * 删除人员
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/deleteUser.do")
	public JsonResult<Map<String, Object>> deleteUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysUserInfoBusiness.deleteByPrimaryKey(request, response);
		return new JsonResult<Map<String, Object>>(map);
	}
	/**
	 * 查看人员
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/checkUser.do")
	public Map<String, Object> checkUser(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> userMap = sysUserInfoBusiness.selectByPrimaryKey(request, response);
		return userMap;
	}
	
	@ResponseBody
	@RequestMapping("/selectDeptTel.do")
	public SysDeptInfo selectDeptTel(HttpServletRequest request) throws Exception{
		String deptId = request.getParameter("deptId");
		int dId=Integer.valueOf(deptId);
		SysDeptInfo dept = sysDepInfousiness.selectTelByDeptId(dId);
		return dept;
	}
	/**
	 * <pre>selectAudit (查询四级以上事件的审核人)
	 * Created by 家栋梁 on 2017年11月30日上午11:20:07  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectAudit.do")
	public Map<String,Object> selectAudit() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysUserInfo> selectAudit = sysUserInfoBusiness.selectAudit();
		map.put("selectAudit", selectAudit);
		return map;
	}
	/**
	 * <pre>selectSystem (查询系统管理人员)
	 * Created by 家栋梁 on 2017年12月4日下午2:30:59  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectSystem.do")
	public Map<String,Object> selectSystem() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> selectSystem = sysUserInfoBusiness.selectSystem();
		map.put("selectSystem", selectSystem);
		return map;
	}
	@ResponseBody
	@RequestMapping("/personalUpdate.do")
	public int personalUpdate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return sysUserInfoBusiness.personalUpdate(request, response);
	}
	/**
	 * 跳转授权代理人页面
	 * 2018年1月8日 下午1:09:42
	 * @李帅
	 * @param request
	 * @throws Exception 
	 */
	@RequestMapping("/toAccreditProxy.do")
	public ModelAndView toAccreditProxy(HttpServletRequest request) throws Exception{
		int user_id = (int) request.getSession().getAttribute("user_id");
		Map<String,Object> pgMap = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		pgMap.put("arrangeProxy", ConstantMenu.LOCK_ZERO);
		pgMap = sysUserInfoBusiness.userByRoleType(pgMap, request);
		sysArrangeBusiness.selectArrangeByDate(null,request);//是不是值班人,将值班人过滤
		pgMap.put("user_id", user_id);
		return new ModelAndView("sys/sys_accredit_proxy", pgMap);
	}
	/**
	 * 添加代理人
	 * 2018年1月8日 下午5:40:41
	 * @李帅
	 * @param request
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/accreditProxy.do")
	public Map<String, Object> accreditProxy(HttpServletRequest request) throws Exception{
		String result= "success";
		String oper_id = request.getParameter("oper_id");
		String dutyNo = request.getParameter("dutyNo");
		Map<String, Object> proxyMap = sysUserInfoBusiness.accreditProxy(Integer.valueOf(oper_id),dutyNo,request);
		if (proxyMap.get("proxyList") != null && !"".equals(proxyMap.get("proxyList"))) {//说明当前存在代理人
			result = "error";
		}
		proxyMap.put("result", result);
		return proxyMap;
	}
	
	@ResponseBody
	@RequestMapping("/continueAccreditProxy.do")
	public String continueAccreditProxy(HttpServletRequest request) throws NumberFormatException, Exception{
		String result = "success";
		String user_id = request.getParameter("user_id");
		String oper_id = request.getParameter("oper");
		int up = sysUserInfoBusiness.continueAccreditProxy(Integer.valueOf(user_id), Integer.valueOf(oper_id),request);
		if (up == 0) {
			result = "error";
		}
		return result;
	}
	/**
	 * 跳转取消代理人界面
	 * 2018年1月9日 上午11:30:14
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	@RequestMapping("/toDistroyProxy.do")
	public ModelAndView toDistroyProxy() throws Exception{
		/**查询当前代理人*/
		Map<String, Object> proxy = sysUserInfoBusiness.toDistroyProxy();
		
		return new ModelAndView("sys/sys_distroy_proxy", proxy);
	}
	
	@ResponseBody
	@RequestMapping("/distroyProxy.do")
	public String distroyProxy(HttpServletRequest request) throws Exception{
		String result= "success";
		String user_id = request.getParameter("user_id");
		SysUserInfo user = new SysUserInfo();
		user.setId(Integer.valueOf(user_id));
		user.setArrangeProxy(ConstantMenu.LOCK_ONE);
		int de = sysUserInfoBusiness.distroyProxy(user,request);
		if (de == 0) {
			result = "error";
		}
		return result;
	}
	@ResponseBody
	@RequestMapping("/selectDeptByUserName.do")
	public List<Map<String,Object>> selectDeptByUserName(HttpServletRequest request){
		String userName = request.getParameter("userName");
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userName", userName);
		List<Map<String, Object>> selectDeptByUserName = sysUserInfoBusiness.selectDeptByUserName(map);
		return selectDeptByUserName;
	}
}
