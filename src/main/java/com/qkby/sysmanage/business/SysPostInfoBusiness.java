package com.qkby.sysmanage.business;

import java.util.List;
import com.qkby.sysmanage.entity.SysPostInfo;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月20日下午4:12:28 
 *     
 * @version </pre>
 */
public interface SysPostInfoBusiness {
	/**
	 * 查询所有
	 * @author 李帅
	 * @return
	 */
	List<SysPostInfo> selectPostAll()throws Exception;
}
