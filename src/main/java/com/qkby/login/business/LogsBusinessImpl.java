package com.qkby.login.business;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qkby.constant.ConstantMenu;
import com.qkby.logs.dao.LogLoginInfoDao;
import com.qkby.logs.entity.LogLoginInfo;
import com.qkby.sysmanage.dao.SysDeptInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.dao.SysUserPostInfoDao;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.sysmanage.entity.SysUserPostInfo;
import com.qkby.utils.ClientInfoUtil;
import com.qkby.utils.LoginUserMap;
import com.qkby.utils.Utils;

@Service
public class LogsBusinessImpl implements LogsBusiness {
	@Resource
	public SysUserInfoDao sysUserInfoDao;
	@Resource
	public SysDeptInfoDao sysDeptInfoDao;
	@Resource
	LogLoginInfoDao logLoginInfoDao;
	@Resource
	SysUserPostInfoDao sysUserPostInfoDao;

	/**
	 * 用户登陆
	 * 
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> selectByExample(HttpServletRequest req, HttpServletResponse res) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// 登陆用户名
		String code = req.getParameter("userCode");
		// 登陆密码
		String password = req.getParameter("password");
		// // ip地址
		// InetAddress ia = null;
		// // mac地址
		// String localMac = "";
		LogLoginInfo loginInfo = new LogLoginInfo();
		// 验证码校验
		/*
		 * if(!checkCode(req)){ map.put("error", "登陆失败 验证码错误!"); return map; }
		 */
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("code", code);
		paramMap.put("password", Utils.crypt(password));
		List<SysUserInfo> sysUserInfo = null;
		try {
			sysUserInfo = sysUserInfoDao.selectLogin(paramMap);
		} catch (Exception e) {
			System.out.println(e);
		}

		if (sysUserInfo == null || sysUserInfo.size() == 0) {
			map.put("error", "登陆失败 请重新核对用户名、密码");
			return map;
		}
		if (sysUserInfo.size() > 1) {
			map.put("error", "登陆失败 您的账号信息重复 请联系管理员处理");
			return map;
		}
		HttpSession session = req.getSession();
		// ServletContext application = req.getSession().getServletContext();
		// for (SysUserInfo sysUser : sysUserInfo) {
		// if (sysUser != null) {
		SysUserInfo sysUser = sysUserInfo.get(0);
		int login_lock = sysUser.getDs();
		if (login_lock == ConstantMenu.LOCK_ONE) {
			map.put("error", "您的账号已被管理员锁定");
			return map;
		}
		if (new Date().after(sysUser.getPwdExpiredDate())) {
			map.put("error", "您的密码已失效 请联系管理员修改");
			return map;
		}
		SysUserPostInfo selectPostByUserId = sysUserPostInfoDao.selectPostByUserId(sysUser.getId());
		session.setAttribute("id_post", selectPostByUserId.getIdPost());
		session.setAttribute("user_id", sysUser.getId());
		session.setAttribute("user_name", sysUser.getName());
		session.setAttribute("user", sysUser);
		session.setAttribute("user_cal", sysUser.getCal());
		LoginUserMap.setLoginUsers(code, session.getId());// 保存sessionId到map中
		int depart_id = sysUser.getIdDept();
		String id = String.valueOf(depart_id);
		int dId = judgeParameter(id);
		SysDeptInfo dept = sysDeptInfoDao.selectByPrimaryKey(dId);
		String depart_name = "";
		String depart_tel = "";
		if (dept != null) {
			depart_name = dept.getName();
		}
		if (dept != null) {
			depart_tel = dept.getTel();
		}
		session.setAttribute("dep_id", depart_id);
		session.setAttribute("dep_name", depart_name);
		session.setAttribute("dep_tel", depart_tel);
		// 启动线程，记录登录日志
		String ip = ClientInfoUtil.getIpAddress(req);
		loginLog(ip, sysUser.getId(), sysUser.getName(), session);
		return map;
	}

	/**
	 * 启动线程，记录登录日志
	 */
	public void loginLog(String ip, Integer uid, String uname, HttpSession session) throws Exception {
		ThreadRunnable threadRunnable = new ThreadRunnable(ip, uid, uname, logLoginInfoDao, session);
		Thread thread = new Thread(threadRunnable);
		thread.start();
	}

	/**
	 * <pre>
	 * judgeParameter 
	 * Created by 家栋梁 on 2017年9月14日上午11:19:24  
	 *
	 * &#64;param id
	 * &#64;return
	 * </pre>
	 */
	private int judgeParameter(String id) {
		int dId = 0;
		try {
			dId = Integer.valueOf(id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return dId;
	}

	/**
	 * 验证码校验
	 */
	private Boolean checkCode(HttpServletRequest req) {
		// 获取验证码
		String verification = req.getParameter("verification").toUpperCase();
		// 获取cookie里面的验证码信息
		Cookie[] cookies = req.getCookies();
		String code = null;
		for (Cookie cookie : cookies) {
			if ("imagecode".equals(cookie.getName())) {
				code = cookie.getValue();
				break;
			}
		}
		// 判断验证码是否正确
		if (!StringUtils.isEmpty(verification) && verification.equals(code)) {
			return true;
		}
		return false;
	}
}
