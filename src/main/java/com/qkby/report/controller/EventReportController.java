package com.qkby.report.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qkby.report.business.EventReportBusiness;
import com.qkby.sysmanage.business.SysServiceTypeBusiness;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Controller
public class EventReportController {
	@Resource
	EventReportBusiness eventReportBusiness;
	@Resource
	SysServiceTypeBusiness sysServiceTypeBusiness;
	
	private Logger log = Logger.getLogger("EventReportController.class");
	
	/*@RequestMapping("toChooseDate.do")
	public String toChooseDate(){
		return "service/date_choose";
	}*/
	
	@ResponseBody
	@RequestMapping("/generatReport.do")
	public String generatReport(HttpServletRequest req,HttpServletResponse res) throws Exception{
		File file = null;  
        InputStream in = null;  
        ServletOutputStream out = null;  
		try{
			Map<String, Object> dataMap = new HashMap<String, Object>();
			SimpleDateFormat sfformat = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
			String user_name = String.valueOf(req.getSession().getAttribute("user_name"));
			String start_time = req.getParameter("start_time");
			String end_time = req.getParameter("end_time");
			Map<String, Object> paramMap = new HashMap<String, Object>();//参数集合
			paramMap.put("startPos", 0);
			paramMap.put("pageSize", 5);
			if (!"".equals(start_time) && start_time != null) {
				paramMap.put("startTime", start_time);
				dataMap.put("startTime", sf.format(sfformat.parse(start_time)));
			}
			if (!"".equals(end_time) && end_time!=null) {
				paramMap.put("endTime", end_time);
				dataMap.put("endTime", sf.format(sfformat.parse(end_time)));
			}
			dataMap = eventReportBusiness.getDataMap(paramMap);
			dataMap.put("statisticsUser", user_name);
			dataMap.put("statisticsDate", sf.format(new Date()));
			// 调用工具类WordGenerator的createDoc方法生成Word文档  
			String templateStr = "ftl";
			String templatePath = req.getServletContext().getRealPath(templateStr);
			Configuration cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templatePath)); 
			cfg.setDefaultEncoding("utf-8");
			// 将数据写入
			String name = "eventReport.doc";
			file = new File(name);
			Template temp = cfg.getTemplate("report.ftl", "UTF-8");
			Writer w = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
			temp.process(dataMap, w);
			w.close();
			//输出文件
            in = new FileInputStream(file);  
            res.setCharacterEncoding("utf-8");  
            res.setContentType("application/msword");  
            // 设置浏览器以下载的方式处理该文件，设置默认名
            res.addHeader("Content-Disposition", "attachment;filename="+name);  
            out = res.getOutputStream();  
            byte[] buffer = new byte[512];  // 缓冲区  
            int bytesToRead = -1;  
            // 通过循环将读入的Word文件的内容输出到浏览器中  
            while((bytesToRead = in.read(buffer)) != -1) {  
                out.write(buffer, 0, bytesToRead);  
            }  
			
		}catch(Exception e){
			if (out!=null )out.print("error");
			e.printStackTrace();
			log.error("生成报告时发生异常："+e.getMessage(),e);
		}finally{
			if(out!=null){
				out.close();
			}
		}
		return "";
	}
}
