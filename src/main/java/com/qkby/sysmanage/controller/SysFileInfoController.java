package com.qkby.sysmanage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.sysmanage.business.SysFileInfoBusiness;

/**
 * 
 * <pre>
 * 项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月20日下午5:37:57 
 *     
 * &#64;version
 * </pre>
 */
@Controller
public class SysFileInfoController {
	@Resource
	public SysFileInfoBusiness sysFileInfoBusiness;

	/**
	 * <pre>
	 * seleDo (跳转到列表页面)
	 * Created by 家栋梁 on 2017年9月20日下午5:46:56  
	 *
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/seleDo.do")
	public ModelAndView seleDo(HttpServletRequest request) throws Exception {
		Map<String, Object> sysFile = sysFileInfoBusiness.selectAllFileType();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		sysFile.put("pageNumber", pageNumber);
		sysFile.put("pageSize", pageSize);
		sysFile.put("totalPage", totalPage);
		sysFile.put("totalRow", totalRow);
		sysFile.put("pages", pages);
		return new ModelAndView("sys/sys_file_list", sysFile);
	}

	/**
	 * <pre>
	 * selectAll (查询全部)
	 * Created by 家栋梁 on 2017年9月20日下午5:37:55  
	 *
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/selectAll.do")
	@ResponseBody
	public Map<String, Object> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> selectAll = sysFileInfoBusiness.selectAll(request, response);
		return selectAll;
	}

	/**
	 * <pre>
	 * insertDo (跳转到新增页面)
	 * Created by 家栋梁 on 2017年9月20日下午7:12:34  
	 *
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/insertDo.do")
	public ModelAndView insertDo(HttpServletRequest request) throws Exception {
		Map<String, Object> sysFile = sysFileInfoBusiness.selectAllFileType();
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		sysFile.put("pageNumber", pageNumber);
		sysFile.put("pageSize", pageSize);
		sysFile.put("totalPage", totalPage);
		sysFile.put("totalRow", totalRow);
		sysFile.put("pages", pages);
		return new ModelAndView("sys/sys_file_add", sysFile);
	}

	/**
	 * <pre>
	 * insertFile (新增)
	 * Created by 家栋梁 on 2017年9月20日下午7:25:44  
	 *
	 * &#64;param request
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/insertFile.do")
	@ResponseBody
	public int insertFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int insert = sysFileInfoBusiness.insert(request, response);
		return insert;
	}

	/**
	 * <pre>
	 * modFileBack (跳转到修改页面)
	 * Created by 家栋梁 on 2017年9月21日下午2:00:16  
	 *
	 * &#64;param request
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/modFileBack.do")
	@ResponseBody
	public ModelAndView modFileBack(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> sysFile = sysFileInfoBusiness.selectByExample(request, response);
		String pageNumber = request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize");
		String totalPage = request.getParameter("totalPage");
		String totalRow = request.getParameter("totalRow");
		String pages = request.getParameter("pages");
		sysFile.put("pageNumber", pageNumber);
		sysFile.put("pageSize", pageSize);
		sysFile.put("totalPage", totalPage);
		sysFile.put("totalRow", totalRow);
		sysFile.put("pages", pages);
		return new ModelAndView("sys/sys_file_mod", sysFile);
	}

	/**
	 * <pre>
	 * updateFile (修改)
	 * Created by 家栋梁 on 2017年9月21日上午11:42:10  
	 *
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/modFileOk.do")
	@ResponseBody
	public int updateFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int us = sysFileInfoBusiness.updateByPrimaryKeySelective(request, response);
		return us;
	}

	/**
	 * <pre>
	 * delFile (根据ID进行删除)
	 * Created by 家栋梁 on 2017年9月21日下午1:10:37  
	 *
	 * &#64;param request
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/delFile.do")
	@ResponseBody
	public int delFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return sysFileInfoBusiness.deleteByPrimaryKey(request, response);
	}

	/**
	 * <pre>
	 * delFile (根据ID进行删除)
	 * Created by 李帅 on 2017年10月10号下午16:17
	 *
	 * &#64;param request
	 * &#64;param response
	 * &#64;return
	 * </pre>
	 * 
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/checkFile.do")
	public Map<String, Object> checkFile(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> fileMap = sysFileInfoBusiness.selectByExample(request, response);
		return fileMap;
	}

	@RequestMapping("/upload2.do")
	public void upload2(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						// 重命名上传后的文件名
						String fileName = "demoUpload" + file.getOriginalFilename();
						// 定义上传路径
						String path = "D:/" + fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
			}

		}
	}
}
