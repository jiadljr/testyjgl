package com.qkby.sysconfig.controller;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.sysconfig.business.ChangePasswordBusiness;
import com.qkby.utils.JsonResult;
@Controller
public class ChangePasswordController {
	@Resource
	ChangePasswordBusiness changePasswordBusiness;
	
	@RequestMapping("/toPassword.do")
	public ModelAndView toPassword (HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		HttpSession session = request.getSession();
		int userId = (int)session.getAttribute("user_id");
		map.put("userId", userId);
		return new ModelAndView("configure/con_password",map);
	}
	
	@ResponseBody
	@RequestMapping("/submitPassword.do")
	public JsonResult<String> submitPassword(HttpServletRequest request) throws Exception{
		String result = changePasswordBusiness.submitPassword(request);
		return new JsonResult<String>(result);
	}
}
