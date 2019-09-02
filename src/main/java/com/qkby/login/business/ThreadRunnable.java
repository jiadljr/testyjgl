package com.qkby.login.business;

import java.util.Date;

import javax.servlet.http.HttpSession;

import com.qkby.logs.dao.LogLoginInfoDao;
import com.qkby.logs.entity.LogLoginInfo;
import com.qkby.utils.ClientInfoUtil;

public class ThreadRunnable implements Runnable {
	private String ip;
	private Integer uid;
	private String uname;
	private LogLoginInfoDao logLoginInfoDao;
	private HttpSession session;

	public ThreadRunnable(String ip, Integer uid, String uname, LogLoginInfoDao logLoginInfoDao, HttpSession session) {
		this.ip = ip;
		this.uid = uid;
		this.uname = uname;
		this.logLoginInfoDao = logLoginInfoDao;
		this.session = session;
	}

	@Override
	public void run() {
		try {
			LogLoginInfo loginInfo = new LogLoginInfo();
			String mac = ClientInfoUtil.getMac(this.ip);
			loginInfo.setIdUser(this.uid);
			loginInfo.setNameUser(this.uname);
			loginInfo.setDateLogin(new Date());
			loginInfo.setIpLogin(this.ip);
			loginInfo.setMacLogin(mac);
			loginInfo.setFgLogOut("n");
			logLoginInfoDao.insert(loginInfo);
			Integer log_id = loginInfo.getId();
			this.session.setAttribute("log_id", log_id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
