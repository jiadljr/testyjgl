package com.qkby.event.controller;

import java.util.Date;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.qkby.constant.ConstantMenu;
import com.qkby.event.business.EventAssessBusiness;
import com.qkby.event.entity.EventInfo;
import com.qkby.event.entity.EventLogInfo;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月18日上午11:08:43 
 *     
 * @version </pre>
 */
@Controller
public class EventAssesController {
	 @Resource
     EventAssessBusiness eventAssessBusiness;
	 /**
	  * <pre>queryAssess 
	  * Created by 家栋梁 on 2017年10月18日上午11:11:58  
	  *
	  * @return</pre>
	  */
	 @RequestMapping("/queryAssess.do")
	 public String queryAssess(){
		 return "service/ser_assess_list";
	 }
	/**
	 * <pre>updateDealAsses 
	 * Created by 家栋梁 on 2017年10月19日下午6:39:18  
	 *
	 * @param request
	 * @param response
	 * @return</pre>
	 * @throws Exception 
	 */
	 @RequestMapping("/updateDealAsses.do")
	 @ResponseBody
	 public String updateDealAsses(HttpServletRequest request) throws Exception{
		 EventInfo eventInfo = new EventInfo();
		 EventLogInfo eventLogInfo = new EventLogInfo();
		 int eventId = 0;
		 String idEvent = request.getParameter("idEvent");
		 if(idEvent != null){
			 eventId = Integer.valueOf(idEvent);
		 }
		 String event_assess = request.getParameter("event_assess");
		 int eventAssess = Integer.valueOf(event_assess);
		 String remark = request.getParameter("remark");
		 if (remark.equals("")) {
			remark = null;
		}
		 String codeEvent = request.getParameter("codeEvent");
		 if (codeEvent.equals("")) {
			 codeEvent = null;
		 }
		 String nameEvent = request.getParameter("nameEvent");
		 if (nameEvent.equals("")) {
			 nameEvent = null;
		 }
		 eventInfo.setId(eventId);
		 eventInfo.setEventAssess(eventAssess);
		 eventInfo.setDateAssess(new Date());
		 eventInfo.setAssessDesc(remark);
		 eventInfo.setEventStatus(String.valueOf(ConstantMenu.EVENT_ANALYSIS_FINISH));
		 eventLogInfo.setEventId(eventId);
		 eventLogInfo.setEventCode(codeEvent);
		 eventLogInfo.setEventName(nameEvent);
		 eventLogInfo.setIdOperUser((int)request.getSession().getAttribute("user_id"));
		 eventLogInfo.setDateOper(new Date());
		 eventLogInfo.setRemark(remark);
		 eventLogInfo.setEventStatus(ConstantMenu.EVENT_ANALYSIS_FINISH);
		 String mass = eventAssessBusiness.updateDealAsses(request,eventInfo,eventLogInfo);
		 return mass;
	 }
}
