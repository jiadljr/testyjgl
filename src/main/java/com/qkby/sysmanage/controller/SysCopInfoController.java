package com.qkby.sysmanage.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.sysmanage.business.SysCopInfoBusiness;
import com.qkby.utils.JsonResult;
import com.qkby.utils.Token;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月14日下午2:47:30 
 *     
 * @version </pre>
 */
@Controller
public class SysCopInfoController {
	@Resource
   public SysCopInfoBusiness sysCmpyInfoBusiness;
	/**
	 * <pre>selectCmpy (跳转到查询页面)
	 * Created by 家栋梁 on 2017年9月16日下午12:46:25  
	 *
	 * @return</pre>
	 */
	@RequestMapping("/selectCmpy.do")
	public ModelAndView selectCmpy(HttpServletRequest request){
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
		return new ModelAndView("sys/sys_comp_list",map);
	}
	/**
	 * <pre>sysCmpy (条件查询加分页)
	 * Created by 家栋梁 on 2017年9月14日下午2:48:14  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/sysCmpy.do")
	@ResponseBody
	public Map<String,Object> sysCmpy(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return sysCmpyInfoBusiness.selectByExample(request,response);
	}
	/**
	 * <pre>deleteByPrimaryKey (根据ID进行删除)
	 * Created by 家栋梁 on 2017年9月15日上午10:58:09  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/deleteCmpy.do")
	@ResponseBody
	public int deleteByPrimaryKey(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int de = sysCmpyInfoBusiness.deleteByPrimaryKey(request, response);
		return de;
	}
    /**
     * <pre>insertCmpy (新增)
     * Created by 家栋梁 on 2017年9月17日下午2:35:47  
     *
     * @param request
     * @param response
     * @return</pre>
     * @throws Exception 
     */
	@RequestMapping("/insertCmpy.do")
	@ResponseBody
	@Token(remove=true, save = false)
	public JsonResult<Integer> insertCmpy(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int sus = sysCmpyInfoBusiness.insertCmpy(request, response);
		return new JsonResult<Integer>(sus);
	}
	/**
	 * <pre>insertPage (跳转到新增页面)
	 * Created by 家栋梁 on 2017年9月17日下午3:29:56  
	 *
	 * @return</pre>
	 */
	@RequestMapping("/insertPage.do")
	@Token(remove=false, save = true)
	public ModelAndView insertPage(HttpServletRequest request){
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
		return new ModelAndView("sys/sys_comp_add",map);
	}
	/**
	 * <pre>updateQuer (跳转到修改页面)
	 * Created by 家栋梁 on 2017年9月17日下午4:04:58  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/updateQuer.do")
	@ResponseBody
	public ModelAndView updateQuer(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> selectByPrimaryKey = sysCmpyInfoBusiness.selectByPrimaryKey(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		selectByPrimaryKey.put("pageNumber", pageNumber);
		selectByPrimaryKey.put("pageSize", pageSize);
		selectByPrimaryKey.put("totalPage", totalPage);
		selectByPrimaryKey.put("totalRow", totalRow);
		selectByPrimaryKey.put("pages", pages);
		return new ModelAndView("sys/sys_comp_mod",selectByPrimaryKey);
	}
	/**
	 * <pre>selectById (根据ID进行查询)
	 * Created by 家栋梁 on 2017年9月18日下午4:35:29  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectById.do")
	@ResponseBody
	public Map<String, Object> selectById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, Object> selectByPrimaryKey = sysCmpyInfoBusiness.selectByPrimaryKey(request, response);
		return selectByPrimaryKey;
	}
	/**
	 * <pre>updateCmpy (修改)
	 * Created by 家栋梁 on 2017年9月17日下午4:48:07  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/updateCmpy.do")
	@ResponseBody
	public JsonResult<Integer> updateCmpy(HttpServletRequest request, HttpServletResponse response) throws Exception{
            int un = sysCmpyInfoBusiness.updateByPrimaryKeySelective(request, response);
		return new JsonResult<Integer>(un);
	}
}
