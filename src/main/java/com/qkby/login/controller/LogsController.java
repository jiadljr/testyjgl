package com.qkby.login.controller;

import java.util.Date;
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

import com.qkby.login.business.LogsBusiness;
import com.qkby.logs.dao.LogLoginInfoDao;
import com.qkby.logs.entity.LogLoginInfo;
import com.qkby.utils.JsonResult;
import com.qkby.utils.LoginUserMap;

/**
 * 
 * <pre>
 * 项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月14日上午11:14:45 
 *     
 * &#64;version
 * </pre>
 */
@Controller
public class LogsController {
	@Resource
	public LogsBusiness logsBusiness;
	@Resource
	LogLoginInfoDao logLoginInfoDao;

	/**
	 * <pre>
	 * toLogin 
	 * Created by 家栋梁 on 2017年9月13日下午5:30:33  
	 *
	 * 
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/toLogin.do")
	public ModelAndView toLogin() {
		return new ModelAndView("login/login");
	}

	/**
	 * <pre>
	 * selectByExample 
	 * Created by 家栋梁 on 2017年9月13日下午2:46:58  
	 *         登陆
	 * &#64;param req
	 * &#64;param res
	 * &#64;return
	 * </pre>
	 * @throws Exception 
	 */
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<Map<String, Object>> selectByExample(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Object> selectByExample = logsBusiness.selectByExample(req, res);
		return new JsonResult<Map<String, Object>>(selectByExample);
	}

	/**
	 * <pre>
	 * index 
	 * Created by 家栋梁 on 2017年9月13日下午4:05:33  
	 *
	 * &#64;return
	 * </pre>
	 */
	@RequestMapping("/index.do")
	public ModelAndView index() {
		return new ModelAndView("index/index");
	}

	/**
	 * <pre>
	 * Logout 
	 * Created by 家栋梁 on 2017年9月13日下午5:23:15  
	 *      退出登陆清除session信息
	 * &#64;param request
	 * &#64;param response
	 * &#64;throws Exception
	 * </pre>
	 * @throws Exception 
	 */
	@RequestMapping("/logout.do")
	public void Logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		LogLoginInfo loginInfo = new LogLoginInfo();
		int log_id = (int) session.getAttribute("log_id");
		loginInfo.setId(log_id);
		loginInfo.setFgLogOut("y");
		loginInfo.setDateLoginOut(new Date());
		logLoginInfoDao.update(loginInfo);
		//清空application
		ServletContext application = request.getSession().getServletContext();
		if(application.getAttribute("dutyYes") != null) {
			application.removeAttribute("dutyYes");
		   }
		if(application.getAttribute("dutyName") != null){
			application.removeAttribute("dutyName");
		}
		// 注销session信息
		session.removeAttribute("user_id");
		session.removeAttribute("user_name");
		session.removeAttribute("dep_id");
		session.removeAttribute("dep_name");
		session.invalidate();
		LoginUserMap.removeUser(session.getId());
	}
}
