package com.qkby.sysmanage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.business.SysAssetsInfoBusiness;
import com.qkby.sysmanage.entity.SysAssetsInfo;
import com.qkby.utils.ExcelUtil;
import com.qkby.utils.FilesUpload;
import com.qkby.utils.Token;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月22日下午3:17:10 
 *     
 * @version </pre>
 */
@Controller
public class SysAssetsInfoController {
	  @Resource
      SysAssetsInfoBusiness sysAssetsInfoBusiness;
	  /**
	   * <pre>seleAssets (跳转到查询页面)
	   * Created by 家栋梁 on 2017年9月22日下午3:22:01  
	   *
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/seleAssets.do")
	  public ModelAndView seleAssets(HttpServletRequest request) throws Exception{
		  Map<String, Object> map = sysAssetsInfoBusiness.seleAssets();
		  String pageNumber = request.getParameter("pageNumber");
		  String pageSize = request.getParameter("pageSize");
		  String totalPage = request.getParameter("totalPage");
		  String totalRow = request.getParameter("totalRow");
		  String pages = request.getParameter("pages");
		  map.put("pageNumber", pageNumber);
		  map.put("pageSize", pageSize);
		  map.put("totalPage", totalPage);
		  map.put("totalRow", totalRow);
		  map.put("pages", pages);
		  return new ModelAndView("sys/sys_assets_list",map);
	  }
	  /**
	   * <pre>selectAll (查询全部)
	   * Created by 家栋梁 on 2017年9月22日下午3:21:04  
	   *
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/selectAssets.do")
	  @ResponseBody
	  public Map<String,Object> selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  Map<String,Object> map = sysAssetsInfoBusiness.selectAll(request,response);
		  return map;
	  }
	  /**
	   * <pre>insertAssets (新增)
	   * Created by 家栋梁 on 2017年9月25日上午9:36:44  
	   *
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/addAssetsOk.do")
	  @ResponseBody
	  @Token(remove=true, save = false)
	  public int insertAssets(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  return sysAssetsInfoBusiness.insert(request,response);
	  }
	  /**
	   * <pre>addAssets (跳转到新增页面)
	   * Created by 家栋梁 on 2017年9月25日上午9:59:29  
	   *
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/addAssets.do")
	  @Token(remove=false, save = true)
	  public ModelAndView addAssets(HttpServletRequest request) throws Exception{
		  Map<String, Object> map = sysAssetsInfoBusiness.addAssets();
		  String pageNumber = request.getParameter("pageNumber");
		  String pageSize = request.getParameter("pageSize");
		  String totalPage = request.getParameter("totalPage");
		  String totalRow = request.getParameter("totalRow");
		  String pages = request.getParameter("pages");
		  map.put("pageNumber", pageNumber);
		  map.put("pageSize", pageSize);
		  map.put("totalPage", totalPage);
		  map.put("totalRow", totalRow);
		  map.put("pages", pages);
		  return new ModelAndView("sys/sys_assets_add",map);
	  }
	  /**
	   * <pre>deleteAssets (删除)
	   * Created by 家栋梁 on 2017年9月25日下午12:02:03  
	   *
	   * @param request
	   * @param response
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/deleteAssets.do")
	  @ResponseBody
	  public int deleteAssets(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  int de = sysAssetsInfoBusiness.deleteAssets(request, response);
		  return de;
	  }
	  /**
	   * <pre>updateAssets (跳转到修改页面)
	   * Created by 家栋梁 on 2017年9月25日下午1:06:24  
	   *
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/modifyAsset.do")
	  @ResponseBody
	  public ModelAndView updateAssets(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  Map<String, Object> map = sysAssetsInfoBusiness.selectByPrimaryKey(request, response);
		  String pageNumber = request.getParameter("pageNumber");
		  String pageSize = request.getParameter("pageSize");
		  String totalPage = request.getParameter("totalPage");
		  String totalRow = request.getParameter("totalRow");
		  String pages = request.getParameter("pages");
		  map.put("pageNumber", pageNumber);
		  map.put("pageSize", pageSize);
		  map.put("totalPage", totalPage);
		  map.put("totalRow", totalRow);
		  map.put("pages", pages);
		  return new ModelAndView("sys/sys_assets_mod",map);
	  }
	  /**
	   * <pre>updateAssets (根据ID进行查询)
	   * Created by 家栋梁 on 2017年9月25日下午1:06:24  
	   *
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/seleAssById.do")
	  @ResponseBody
	  public Map<String,Object> selectById(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  Map<String, Object> map = sysAssetsInfoBusiness.seleAssById(request, response);
		  return map;
	  }
	  /**
	   * <pre>assSub (修改)
	   * Created by 家栋梁 on 2017年9月25日下午3:31:11  
	   *
	   * @param request
	   * @param response
	   * @return</pre>
	 * @throws Exception 
	   */
	  @RequestMapping("/assSub.do")
	  @ResponseBody
	  public String assSub(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  sysAssetsInfoBusiness.updateAssets(request, response);
		  return "success";
	  }

	@ResponseBody
	@RequestMapping("/selectAssetsTypeByThreeLayer.do")
	public List<SysAssetsInfo> selectAssetsTypeByThreeLayer(HttpServletRequest request) throws NumberFormatException, Exception{
		String typeId = request.getParameter("typeId");
		return sysAssetsInfoBusiness.selectAssetsByThreeLayer(Integer.valueOf(typeId));
	}
	
	@RequestMapping("/toLead.do")
	public String toLead(){
		return "sys/sys_assets_lead";
	}
	/**
	 * 资产导入
	 * 2018年1月2日 下午3:42:53
	 * @李帅
	 * @param request
	 * @param response
	 */
	@ResponseBody
	@RequestMapping("/leadAssets.do")
	public String leadAssets(HttpServletRequest request,HttpServletResponse response) throws Exception{
		InputStream in;
		FilesUpload file = new FilesUpload();
		Map<String, Object> fileUpload = file.fileUpload(request,response);
		String path = (String)fileUpload.get("path_to");
		String file_name = (String)fileUpload.get("file_name_from");
		String fileSuffix = file_name.substring(file_name.lastIndexOf("."), file_name.length());
		if (!".xlsx".equals(fileSuffix.toLowerCase())) {
			throw new BusinessException("500", "请选择正确的文件格式，进行资产的导入！");
		}
		in = new FileInputStream(new File(path));
		List<List<String>> excelList = ExcelUtil.getBankListByExcel(in, file_name);
		sysAssetsInfoBusiness.insertAssetsByList(excelList,request);
		File f = new File(path);
		deleteFile(f);
		String success = "success";
		return success;
	}
	
	public void deleteFile(File file){
	     if(file.isDirectory()){
	          File[] files = file.listFiles();
	          for(int i=0; i<files.length; i++){
	               deleteFile(files[i]);
	          }
	     }
	     file.delete();
	}
	/**
	 * 判断文件是否存在
	 * 2018年1月2日 下午1:53:46
	 * @李帅
	 * @param
	 */
	@ResponseBody
	@RequestMapping( value = "/excelOut")
    public String excelStandardTemplateOut(HttpServletRequest request,
           HttpServletResponse response) throws IOException, BusinessException{
		String str = Utils.getLocalName(request);
		str = str +"/template/ASSETExcel.xlsx";//Excel模板所在的路径。
        File f = new File(str);
        boolean exists = f.exists();
        if (!exists) {
			throw new BusinessException("", "系统发生异常 ，请联系管理员！");
		}
        str = "success";
        return str;
	}
	@RequestMapping( value = "/downExcel")
	public void downExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = Utils.getLocalName(request);
		str = str +"/template/ASSETExcel.xlsx";//Excel模板所在的路径。
        File f = new File(str);
        sysAssetsInfoBusiness.downLoadExcel(f, response);
	}
}
