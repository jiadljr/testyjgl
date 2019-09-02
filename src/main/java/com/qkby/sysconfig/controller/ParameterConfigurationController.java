package com.qkby.sysconfig.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/**
 * 参数配置功能
 * @author H.w
 * 
 */

@Controller
public class ParameterConfigurationController {
	/**
	 * 读取参数配置文件，将现有参数在页面显示
	 * @param request
	 * @param response
	 * @return paramMap 封装有系统参数的map
	 */
	@RequestMapping("/toParamConfig.do")
	@ResponseBody
	public ModelAndView addDeal(HttpServletRequest request,HttpServletResponse response){
		String pwdAging = "";//密码有效期
		String fDayShift = "";//白班开始时间
		String tDayShift = "";//白班结束时间
		String fNightShift = "";//夜班开始时间
		String tNightShift = "";//夜班结束时间
		//返回参数
		Map<String,Object> paramMap = new HashMap<String,Object>();
		//配置文件所在路径
		String basePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties";
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in =new FileInputStream(basePath);
           prop.load(in);
            Set<Object> keyValue= prop.keySet();
            for(Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
               String key = (String) it.next();
               if (key.equals("pwdAging")) {
            	   pwdAging = (String) prop.get(key);
               } else if (key.equals("fDayShift")) {
            	   fDayShift = (String) prop.get(key);
               } else if (key.equals("tDayShift")){
            	   tDayShift=(String) prop.get(key);
               } else if (key.equals("fNightShift")) {
            	   fNightShift = (String) prop.get(key);
               } else if (key.equals("tNightShift")){
            	   tNightShift=(String) prop.get(key);
               }
            }
            paramMap.put("pwdAging", pwdAging);
            paramMap.put("fDayShift", fDayShift);
            paramMap.put("tDayShift", tDayShift);
            paramMap.put("fNightShift", fNightShift);
            paramMap.put("tNightShift", tNightShift);
            
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
		return new ModelAndView("forward:/views/configure/con_para_info.jsp",paramMap);
	}
	
	/**
	 * 保存修改后的参数，并写入到paramConfig.properties文件中做持久化
	 * @param request
	 * @param response
	 */
	@RequestMapping("/saveParamConfig.do")
	private void saveParamConfig (HttpServletRequest request, HttpServletResponse response) {
		String pwdAging = request.getParameter("pwdAging");
		String fDayShift = request.getParameter("fDayShift");
		String tDayShift = request.getParameter("tDayShift");
		String fNightShift = request.getParameter("fNightShift");
		String tNightShift = request.getParameter("tNightShift");
		
		//配置文件所在路径
		String basePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties";
		//创建properties对象
		Properties properties = new Properties();  
        OutputStream output = null;  
        try {  
        	synchronized (this) {
        		//指定写出文件的位置
        		output = new FileOutputStream(basePath);  
        		//保存键值对到内存  
        		properties.setProperty("pwdAging", pwdAging);
        		properties.setProperty("fDayShift", fDayShift);
        		properties.setProperty("tDayShift", tDayShift);
        		properties.setProperty("fNightShift", fNightShift);
        		properties.setProperty("tNightShift", tNightShift);
        		// 保存键值对到文件中
        		properties.store(output, "paramConfig modify " + new Date().toString());
				
			}
            
        } catch (IOException io) {  
            io.printStackTrace();  
        } finally {  
            if (output != null) {  
                try {  
                    output.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
	}
	 
	
}
