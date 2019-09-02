package com.qkby.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadProjZipFile implements CallBack{
	
	public void download(HttpServletRequest request,HttpServletResponse response
			,String zipFilePath, String fileName) throws Exception{
		ReadFile.downLoadFile(response, request, zipFilePath, fileName);
	}

	@Override
	public void delteProjZipFile(String zipFilePath) {
		File file = new File(zipFilePath);
		file.delete();
	}
}
