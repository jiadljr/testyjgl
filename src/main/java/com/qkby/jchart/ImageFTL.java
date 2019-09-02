package com.qkby.jchart;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import freemarker.template.Configuration;
import freemarker.template.Template;
import sun.misc.BASE64Encoder;

public class ImageFTL {

	public void createImageFTL(int idC,int idT,String imageName,String imageStr,String tempName,String url) throws Exception {
		
		String appPath = System.getProperty("appPath");
		String templatePath = new StringBuffer(appPath).append("template").append(File.separator).append("image").toString();
		Configuration cfg = new Configuration();
		cfg.setDirectoryForTemplateLoading(new File(templatePath));
		cfg.setEncoding(Locale.CHINA, "UTF-8");
		Template template = cfg.getTemplate(tempName+".ftl");
		template.setEncoding("UTF-8");
		Map<String, Object> rootMap = new HashMap<String, Object>();
		rootMap.put("imageName", imageName);
		rootMap.put("imageStr", imageStr);
		StringBuffer savePath = new StringBuffer(appPath).append("template").append(File.separator).append(url).append(File.separator).append("pics").append(File.separator).append(idC).append(File.separator).append(idT);
		File fileDir = new File(savePath.toString());
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		String putFileName = savePath.append(File.separator).append(imageName).append(".ftl").toString();
		Writer write = null;
		try {
			write = new OutputStreamWriter(
					new FileOutputStream(putFileName), "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		template.process(rootMap, write);
		write.close();
	}
	
	public static String getImage(JFreeChart chart){
		BASE64Encoder BASE64 = new BASE64Encoder();
		 ByteArrayOutputStream bas = new ByteArrayOutputStream();
		 try {
		 ChartUtilities.writeChartAsJPEG(bas, 1.0f, chart, 500, 350, null);
		 bas.flush();
		 bas.close();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 byte[] byteArray = bas.toByteArray();
		 try {
		 InputStream is = new ByteArrayInputStream(byteArray);
		 byteArray = new byte[is.available()];
		 is.read(byteArray);
		 is.close();
		 } catch (IOException e) {
		 e.printStackTrace();
		 }
		 String xml_img = BASE64.encode(byteArray);
		 return xml_img;
	}
}
