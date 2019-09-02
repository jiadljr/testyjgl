package com.qkby.sysconfig.business;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年11月8日下午2:04:14 
 *     
 * @version </pre>
 */
public interface SysAuditInfoBusiness {
      List<Map<String,Object>> selectLoginAll(Map<String,Object> map)throws Exception;
}
