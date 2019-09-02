package com.qkby.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import com.qkby.exception.BusinessException;
import com.qkby.proj.controller.ProjFileController;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

public class ReadFile {
	
	public static void downLoadFile(HttpServletResponse response,HttpServletRequest request,String filePath,String fileName) throws Exception{
		/**
		 * 文件下载
		 * @throws MyHandlerExceptionResolver 
		 * */
		String contentType = "application/octet-stream";
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
		contentType = Utils.getContentType(fileType);
		String downLoadPath = filePath;
		File file = new File(downLoadPath);
		boolean exists = file.exists();
		long fileLength = file.length();
		if(exists){
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath));
					BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());){
				
				String filename = fileName + "." + fileType;
				Boolean flag = request.getHeader("User-Agent").indexOf("like Gecko") > 0;
				if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") > 0 || flag) {
					filename = URLEncoder.encode(filename, "UTF-8");//IE浏览器
				} else {
					filename = MimeUtility.encodeWord(filename);
				}
				//设置文件输出类型
				response.setContentType(contentType);
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8"); 
				//设置输出长度
				response.setHeader("Content-Length", String.valueOf(fileLength));
				response.setHeader("Content-Disposition", "attachment;filename=" + filename);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				 bos.flush();   //不可少
		         response.flushBuffer();//不可少
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else{
			throw new BusinessException();
		}
	}
	public static void downProjZipFile(HttpServletResponse response,HttpServletRequest request,String filePath,String fileName,ProjFileController projFileController) throws Exception{
		/**
		 * 文件下载
		 * @throws MyHandlerExceptionResolver 
		 * */
		String contentType = "application/octet-stream";
		String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
		contentType = Utils.getContentType(fileType);
		String downLoadPath = filePath;
		File file = new File(downLoadPath);
		boolean exists = file.exists();
		long fileLength = file.length();
		if(exists){
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(downLoadPath));
					BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());){
				
				String filename = fileName + "." + fileType;
				Boolean flag = request.getHeader("User-Agent").indexOf("like Gecko") > 0;
				if (request.getHeader("User-Agent").toLowerCase().indexOf("msie") > 0 || flag) {
					filename = URLEncoder.encode(filename, "UTF-8");//IE浏览器
				} else {
					filename = MimeUtility.encodeWord(filename);
				}
				//设置文件输出类型
				response.setContentType(contentType);
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8"); 
				//设置输出长度
				response.setHeader("Content-Length", String.valueOf(fileLength));
				response.setHeader("Content-Disposition", "attachment;filename=" + filename);
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
				bos.flush();   //不可少
				response.flushBuffer();//不可少
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}else{
			throw new BusinessException();
		}
		
		projFileController.delteProjZipFile(filePath);
	}
}
