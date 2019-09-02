package com.qkby.analysis.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qkby.analysis.business.EventInfoMonthBusiness;
import com.qkby.analysis.entity.EventInfoMonth;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午3:01:48 
 *     
 * @version </pre>
 */
@Controller
public class EventInfoMonthController {
  @Resource
  EventInfoMonthBusiness eventInfoMonthBusiness;
  /**
   * <pre>selectMonth 
   * Created by 家栋梁 on 2017年10月25日下午3:04:11  
   * 月度时间统计
   * @return</pre>
 * @throws Exception 
   */
  @RequestMapping("/selectMonth.do")
  @ResponseBody
  public List<EventInfoMonth> selectMonth() throws Exception{
	  return eventInfoMonthBusiness.selectMonth();
  }
}
