package com.qkby.login.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.LoginUserMap;

/**
 * 
 * @author H.W
 *
 */

public class LoginFilter implements Filter {
	private static final String SHOW_LOGIN_PATH = "SHOW_LOGIN_PATH"; // 显示登录页面
	private static final String DO_LOGIN_PATH = "DO_LOGIN_PATH"; // 登录操作不能过滤掉
	private static final String LOGIN_PERSONNAME = "LOGIN_PERSONNAME"; // 登录用户在session中的属性key
																		// --
																		// session.setAttribute(key,value)
	private static final String IDENTIFYINGCODE_PATH = "IDENTIFYINGCODE_PATH"; // 验证码加载
	private static final String CHECKIMAGECODE_PATH = "CHECKIMAGECODE_PATH"; // 验证码验证
	private static final String TOIMG_PATH = "TOIMG_PATH";
	private static final String TOSCREEND_PATH = "TOSCREEND_PATH";// 大屏展示
	private static final String SELECTSCREEND_PATH = "SELECTSCREEND_PATH";// 大屏展示
	private static final String EVENTALERT_PATH = "EVENTALERT_PATH";// 大屏展示
	private static final String DEALSTATUS_PATH = "DEALSTATUS_PATH";// 大屏展示

	private String showloginPath;
	private String dologinPath;
	private String loginPersonName;
	private String identifyingCodePath;
	private String checkImageCodePath;
	private String toImgPath;
	private String toScreendPath;
	private String selectScreendPath;
	private String eventAlertPath;
	private String dealStatusPath;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpResp = (HttpServletResponse) response;
		httpResp.setContentType("text/html");
		// 判断是否是登陆页面
		String servletPath = httpReq.getServletPath();
		// flag:若为登陆页面的action路径 showloginPath/nologinPath,则赋值true，否则赋值false
		boolean flag = false;
		if (servletPath.equals(showloginPath) || servletPath.equals(dologinPath)
				|| servletPath.equals(identifyingCodePath) || servletPath.equals(checkImageCodePath)
				|| servletPath.equals(toImgPath) || servletPath.equals(toScreendPath)
				|| servletPath.equals(selectScreendPath) || servletPath.equals(eventAlertPath)
				|| servletPath.equals(dealStatusPath)) {
			chain.doFilter(request, response);
			flag = true;
			return;
		} else {
			// 如果不是登录页面，则需先判断用户是否已登录
			// String url = servletPath;
			Object loginName = httpReq.getSession().getAttribute(loginPersonName);
			SysUserInfo userInfo = (SysUserInfo) httpReq.getSession().getAttribute("user");
			Boolean isLogin = false;
			if (userInfo != null) {
				isLogin = LoginUserMap.isInLoginUsers(userInfo.getCode(), httpReq.getSession().getId());
			}
			if (loginName != null && isLogin) {// 如果不为空,则进行已登录处理
				if (servletPath.equals(showloginPath) || servletPath.equals(dologinPath)
						|| servletPath.equals("/views/login/login.jsp")) {
					httpResp.sendRedirect(httpReq.getContextPath() + "/index.do");
					return;
				}
				chain.doFilter(request, response);
			} else if (loginName != null && !isLogin) {// 用户在它处登录
				httpReq.getSession().invalidate();
				httpResp.sendRedirect(httpReq.getContextPath());
			} else {// 如果为空,则进行未登录处理
				if (httpReq.getQueryString() != null) {
					servletPath += "?" + httpReq.getQueryString();
				}
				httpReq.getSession().setAttribute("returnUri", servletPath);
				if (flag == false) {
					httpResp.sendRedirect(httpReq.getContextPath() + showloginPath);
				}
			}
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		showloginPath = config.getInitParameter(SHOW_LOGIN_PATH);
		dologinPath = config.getInitParameter(DO_LOGIN_PATH);
		loginPersonName = config.getInitParameter(LOGIN_PERSONNAME);
		identifyingCodePath = config.getInitParameter(IDENTIFYINGCODE_PATH);
		checkImageCodePath = config.getInitParameter(CHECKIMAGECODE_PATH);
		toImgPath = config.getInitParameter(TOIMG_PATH);
		toScreendPath = config.getInitParameter(TOSCREEND_PATH);
		selectScreendPath = config.getInitParameter(SELECTSCREEND_PATH);
		eventAlertPath = config.getInitParameter(EVENTALERT_PATH);
		dealStatusPath = config.getInitParameter(DEALSTATUS_PATH);
		if (showloginPath == null || showloginPath.equals("") || showloginPath.equals("null")) {
			throw new ServletException("登录页面配置出错...");
		}
	}

}
