package com.qkby.event.business;

import javax.servlet.http.HttpServletRequest;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventLogInfo;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月19日上午10:31:05 
 *     
 * @version </pre>
 */
public interface EventAssessBusiness {
  public String updateDealAsses(HttpServletRequest request,EventInfo eventInfo,EventLogInfo eventLogInfo) throws Exception;
}
