package com.qkby.connector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.qkby.constant.ConstantMenu;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysUserInfo;

@Component
public class CancelAuthor {
	@Resource
	SysUserInfoDao sysUserInfoDao;
	
	@Scheduled(cron = "0 0 16 * * ?")
//	@Scheduled(cron = "0 */1 * * * ?")
	public void defaultCancel() throws Exception{
		cancelAuthor();
	}
	
	/**
	 * 定时取消代理人权限
	 * 2018年1月10日 下午1:30:15
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	public void cancelAuthor() throws Exception{
		Map<String, Object> pgMap = new HashMap<>();
		pgMap.put("arrangeProxy", ConstantMenu.LOCK_ZERO);
		List<SysUserInfo> proxyUser = sysUserInfoDao.selectByUserExample(pgMap);
		if (proxyUser.size()>0 &&proxyUser !=null) {
			SysUserInfo user = new SysUserInfo();
			user.setId(proxyUser.get(0).getId());
			user.setArrangeProxy(ConstantMenu.LOCK_ONE);
			sysUserInfoDao.updateByPrimaryKeySelective(user);
		}
	}
}
