package com.qkby.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.qkby.exception.BusinessException;

public class FilesUpload {
	
	
	@SuppressWarnings("unused")
	public Map<String,Object> fileUpload(HttpServletRequest request, HttpServletResponse response) throws BusinessException {
		Map<String,Object> map = new HashMap<String,Object>();
		String file_name_from = "";
		String file_name_to = "";
		String path_to = "";
		ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
		String domainName = resource.getString("domainName");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = mulRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile file = mulRequest.getFile(iter.next());
				if (file != null) {
					String myFileName = file.getOriginalFilename();
					String s = myFileName.substring(myFileName.indexOf(".")+1);
					if (!"".equals(myFileName.trim())) {
						file_name_from = myFileName;
						String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
				        uuid = uuid.replace("-", "");
						String newFileName = uuid +"."+s;
						file_name_to = newFileName;
						String path = domainName+ newFileName;
						File fileMkdir = new File(domainName);
						if (!fileMkdir.exists() && !fileMkdir.isDirectory()) {
							fileMkdir.mkdir();
						}
						path_to = path;
						File serverFile = new File(path);
						try{
							if (!serverFile.getParentFile().exists()) {  
								serverFile.getParentFile().mkdirs();  
				            }  
							if (!serverFile.exists()) {
								serverFile.createNewFile();
							}else{
								continue;
							}
							file.transferTo(serverFile);
						}catch(Exception e){
							throw new BusinessException("","保存文件失败！");
						}
					}
				}
			}
		}
		String file_name = file_name_from.split("\\.")[0];
		map.put("path_to", path_to);
		map.put("file_name", file_name);
		map.put("file_name_from", file_name_from);
		return map;
	}
	
	 public Map<String,Object> upload2(HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException {  
		    Map<String,Object> map = new HashMap<String,Object>();	
		    String file_name_from = "";
			String path_to = "";
			ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
			String domainName = resource.getString("domainName");  
		 //创建一个通用的多部分解析器  
	       CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	       //判断 request 是否有文件上传,即多部分请求  
	       if(multipartResolver.isMultipart(request)){  
	           //转换成多部分request    
	           MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
	           //取得request中的所有文件名  
	           Iterator<String> iter = multiRequest.getFileNames();  
	           while(iter.hasNext()){  
	               //取得上传文件  
	               MultipartFile file = multiRequest.getFile(iter.next());  
	               if(file != null){  
	                   //取得当前上传文件的文件名称  
	                   String myFileName = file.getOriginalFilename();  
						String s = myFileName.substring(myFileName.indexOf(".")+1);
	                   //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                   if(myFileName.trim() !=""){  
	                	   file_name_from = myFileName;
							String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
					        uuid = uuid.replace("-", "");
							String newFileName = uuid +"."+s;
							String path = domainName+ newFileName;
							path_to = path;
	                       File localFile = new File(path); 
	                       file.transferTo(localFile);  
	                   }  
	               }  
	           }  
	       }  
	   	String file_name = file_name_from.split("\\.")[0];
		map.put("path_to", path_to);
		map.put("file_name", file_name);
		map.put("file_name_from", file_name_from);
		return map;
	   }  
	 /**
	  * 上传单个文件
	  * @param file
	  * @return
	  * @throws BusinessException
	  */
	 public static Map<String,Object> fileUploadOne(MultipartFile file) throws BusinessException {
			Map<String,Object> map = new HashMap<String,Object>();
			String file_name_from = "";
			String path_to = "";
			ResourceBundle resource = ResourceBundle.getBundle("paramConfig");
			String domainName = resource.getString("domainName");
			if (file != null) {
				String myFileName = file.getOriginalFilename();
				String s = myFileName.substring(myFileName.indexOf(".")+1);
				if (!"".equals(myFileName.trim())) {
					file_name_from = myFileName;
					String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
			        uuid = uuid.replace("-", "");
					String newFileName = uuid +"."+s;
					String path = domainName+newFileName;
					path_to = path;
					File serverFile = new File(path);
					try{
						if (!serverFile.getParentFile().exists()) {  
							serverFile.getParentFile().mkdirs();  
			            }  
						if (!serverFile.exists()) {
							serverFile.createNewFile();
						}
						file.transferTo(serverFile);
					}catch(Exception e){
						throw new BusinessException("","保存文件失败！");
					}
				}
			}
			String file_name = file_name_from.split("\\.")[0];
			map.put("path_to", path_to);
			map.put("file_name", file_name);
			map.put("file_name_from", file_name_from);
			map.put("domainName", domainName);
			return map;
		}
	 
	 public static void downLoadExcelFile(File f ,HttpServletResponse response) throws IOException{
			// 设置response参数，可以打开下载页面
		       response.reset();
		       response.setContentType("application/vnd.ms-excel;charset=utf-8");
		       try {
		           response.setHeader("Content-Disposition", "attachment;filename="+ new String(("项目批量导入模板" + ".xlsx").getBytes(), "iso-8859-1"));//下载文件的名称
		       } catch (UnsupportedEncodingException e) {
		           e.printStackTrace();
		       }
		       BufferedInputStream bis = null;
		       BufferedOutputStream bos = null;
		       try {
		    	   ServletOutputStream out = response.getOutputStream();
		           bis = new BufferedInputStream(new FileInputStream(f));
		           bos = new BufferedOutputStream(out);
		           byte[] buff = new byte[2048];
		           int bytesRead;
		           while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		               bos.write(buff, 0, bytesRead);
		           }
		       } catch (final IOException e) {
		           throw e;
		       } finally {
		           if (bis != null){
		        	   bis.close();
		           }
		           if (bos != null){
		        	   bos.close();
		           }
		       }
		}
}
