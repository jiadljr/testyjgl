package com.qkby.sysconfig.business;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.qkby.constant.ConstantMenu;
import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.utils.Utils;

@Service("ChangePasswordBusiness")

public class ChangePasswordBusinessImpl implements ChangePasswordBusiness{
	@Resource 
	SysUserInfoDao sysUserInfoDao;
	
	@Override
	public String submitPassword(HttpServletRequest request) throws Exception {
		String result = "oldError";
		//判断原密码是否正确
		String userId = request.getParameter("userId");
		int uId = Integer.valueOf(userId);
		SysUserInfo userOne = sysUserInfoDao.selectByPrimaryKey(uId);
		if(userOne == null){
			throw new BusinessException("500","");
		}
		String Password = userOne.getPassword();
		String oldPword = request.getParameter("oldPword");
		if (oldPword.equals("")) {
			oldPword = null;
		}
		oldPword = Utils.crypt(oldPword);
		if (oldPword.equals(Password)) {
			result = "success";
		}else{
			return result;
		}
		SysUserInfo user = new SysUserInfo();
		String pwdAging="";
		HttpSession session = request.getSession();
		String basePath = session.getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties";
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in =new FileInputStream(basePath);
		   prop.load(in);
			Set<Object> keyValue= prop.keySet();
			for(Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
			   String key = (String) it.next();
			   if(key.equals("pwdAging")){
				   pwdAging = (String) prop.get(key);
			   }
			}
		} catch (Exception e) {
		   e.printStackTrace();
		} finally {  
			if (in != null) {  
				try {  
					in.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		} 
		int pastPass=Integer.parseInt(pwdAging);
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, pastPass);
		user.setPwdExpiredDate(calendar.getTime());
		user.setId(uId);
		String newPword = request.getParameter("newPword");
		if (newPword.equals("")) {
			newPword = null;
		}
		user.setPassword(Utils.crypt(newPword));
		user.setUpdateDate(new Date());
		int up = sysUserInfoDao.updateByPrimaryKeySelective(user);
		if (up == ConstantMenu.EXECUTE_FAIL) {
			result = "error";
		}
		return result;
	}
	
}
