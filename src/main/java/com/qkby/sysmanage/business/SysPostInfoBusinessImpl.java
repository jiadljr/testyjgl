package com.qkby.sysmanage.business;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.sysmanage.dao.SysPostInfoDao;
import com.qkby.sysmanage.entity.SysPostInfo;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月20日下午4:13:34 
 *     
 * @version </pre>
 */
@Service("SysPostInfoBusiness")
public class SysPostInfoBusinessImpl implements SysPostInfoBusiness{
	@Resource
   public SysPostInfoDao sysPostInfoDao;

	@Override
	public List<SysPostInfo> selectPostAll() throws Exception {
		return sysPostInfoDao.selectPostAll();
	}

}
