package com.qkby.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public class ParamConfigInfosUtil {
	
	/**
	 * 获取配置文件中参数
	 * @throws Exception 
	 * */
	public static Map<String,String> getUserPwdFileUreNum(HttpServletRequest request) throws Exception{
		Map<String,String> paramMap = new HashMap<String,String>();
		String basePath = request.getSession().getServletContext().getRealPath("/")+"WEB-INF\\classes\\paramConfig.properties";
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in =new FileInputStream(basePath);
           prop.load(in);
            Set<Object> keyValue= prop.keySet();
            for(Iterator<Object> it = keyValue.iterator(); it.hasNext();) {
               String key = (String) it.next();
               paramMap.put(key, (String) prop.get(key));
            }
        }catch(Exception e){
        	throw e;
        }finally{
        	if (in != null) {
                try {  
                    in.close();  
                } catch (IOException e) {  
                    throw e;
                }  
            }
        }
		return paramMap;
	}
}
