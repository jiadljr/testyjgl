package com.qkby.sysmanage.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.business.SysArrangeBusiness;
import com.qkby.sysmanage.business.SysDutyInfoBusiness;
import com.qkby.sysmanage.business.SysDutyRemarkBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.sysmanage.entity.SysDutyInfo;
import com.qkby.sysmanage.entity.SysDutyRemarkInfo;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月16日下午4:07:19 
 *     
 * @version </pre>
 */
@Controller
public class SysDutyInfoController {
	@Resource
    public SysDutyInfoBusiness sysDutyInfoBusiness;
	@Resource
	public SysUserInfoBusiness sysUserInfoBusiness;
	@Resource
	SysArrangeBusiness sysArrangeBusiness;
	@Resource
	SysDutyRemarkBusiness sysDutyRemarkBusiness;
	/**
	 * <pre>seleDuty 
	 * Created by 家栋梁 on 2017年9月16日下午4:19:26  
	 *
	 * @return</pre>
	 */
	@RequestMapping("/seleDuty.do")
	public ModelAndView seleDuty(){
		Map<String,Object> map = new HashMap<String,Object>();
		Date date = new Date();
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM");
		String format = sdft.format(date);
		SysDutyRemarkInfo selectDutyRemark = sysDutyRemarkBusiness.selectDutyRemark(format);
		map.put("selectDutyRemark", selectDutyRemark);
		return new ModelAndView("sys/sys_duty_list",map);
	}
	/**
	 * <pre>selectDuty 
	 * Created by 家栋梁 on 2017年9月16日下午4:09:30  
	 *
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDuty.do")
	@ResponseBody
	public List<SysDutyInfo> selectDuty() throws Exception{
		return sysDutyInfoBusiness.selectByExample();
	}
	@RequestMapping("/dutyInsert.do")
	public ModelAndView dutyInsert(HttpServletRequest request ) throws Exception{
		Map<String,Object> pgMap = new HashMap<String,Object>();
		pgMap.put("role_id", ConstantMenu.OPS_SIX);
		Map<String, Object> map = sysUserInfoBusiness.userByRoleType(pgMap,request);
		return new ModelAndView("sys/sys_duty_add",map);
	}
	/**
	 * <pre>selectByCond 
	 * Created by 家栋梁 on 2017年9月18日下午7:55:16  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectByCond.do")
	@ResponseBody
	public Map<String,Object> selectByCond(HttpServletRequest request, HttpServletResponse response) throws Exception{
		return sysDutyInfoBusiness.selectByCond(request, response);
	}
	/**
	 * <pre>submitDuty 
	 * Created by 家栋梁 on 2017年9月20日上午10:30:30  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/submitDuty.do")
	public void submitDuty(HttpServletRequest request, HttpServletResponse response) throws Exception{
		sysDutyInfoBusiness.submitDuty(request, response);
	}
	/**
	 * <pre>selectDutyDate (查询是否值班)
	 * Created by 家栋梁 on 2017年11月23日上午10:55:43  
	 *
	 * @param request
	 * @return</pre>
	 * @throws Exception 
	 */
	@RequestMapping("/selectDutyDate.do")
	@ResponseBody
	public Map<String,Object> selectDutyDate(HttpServletRequest request) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		int id = (int)session.getAttribute("user_id");
		Boolean selectArrangeByDate = sysArrangeBusiness.selectArrangeByDate(String.valueOf(id),request);
		map.put("dateDuty", selectArrangeByDate);
		return map;
	}
	@RequestMapping("/removeServletContext.do")
	public void removeServletContext(HttpServletRequest request){
		ServletContext context = request.getServletContext();
		context.removeAttribute("apply");
		context.removeAttribute("accept");
	}
}
