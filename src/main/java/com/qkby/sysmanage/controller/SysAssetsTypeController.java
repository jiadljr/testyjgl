package com.qkby.sysmanage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.sysmanage.business.SysAssetsTypeBusiness;
import com.qkby.sysmanage.entity.SysAssetsType;
import com.qkby.sysmanage.entity.SysDeptInfo;
import com.qkby.utils.ChinesePinYin;
import com.qkby.utils.ChineseToInitials;
import com.qkby.utils.JsonResult;
import com.qkby.utils.Token;
import com.qkby.utils.Utils;

@Controller
public class SysAssetsTypeController {
	@Resource
	SysAssetsTypeBusiness sysAssetsTypeBusiness;
	/**
	 * 跳转资产类型页面
	 * @author 李帅
	 */
	@RequestMapping("/toSysAssetsType.do")
	public ModelAndView toSysAssetsType(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
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
		return new ModelAndView("sys/sys_assets_type_list",map);
	}
	/**
	 * 根据条件查询资产类型信息
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/selectSysAssetsType.do")
	public Map<String, Object> selectSysAssetsType(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map<String, Object> map = sysAssetsTypeBusiness.selectByExample(request, response);
		return map;
	}
	
	/**
	 * 跳转资产类型添加页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/addAssetsType.do")
	@Token(remove=false, save = true)
	public ModelAndView addAssetsType(HttpServletRequest request) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<SysAssetsType> pName = sysAssetsTypeBusiness.selectParentName(0);
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
		map.put("pName", pName);
		return  new ModelAndView("sys/sys_assets_type_add",map);
	}
	
	/**
	 * 添加资产类型
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/insertAssetsType.do")
	@Token(remove=true, save = false)
	public void insertAssetsType(HttpServletRequest request,HttpServletResponse response) throws Exception{
		sysAssetsTypeBusiness.insert(request, response);
	}
	/**
	 * 跳转资产类型修改页面
	 * @author 李帅
	 * @throws Exception 
	 */
	@RequestMapping("/modifyAssets.do")
	@Token(remove=false, save = true)
	public ModelAndView modifyAssets(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String ids = request.getParameter("id");
		int id = Integer.valueOf(ids);
		Map<String, Object> map=new HashMap<String, Object>();
		SysAssetsType assetsTypeOne = sysAssetsTypeBusiness.selectByPrimaryKey(request, response);
		List<SysAssetsType> pName = sysAssetsTypeBusiness.selectParentName(assetsTypeOne.getLayer());
		List<SysAssetsType> assetsTypeByparentId = sysAssetsTypeBusiness.selectAssetsTypeByparentId(id);
		String flag = "no";
		if (assetsTypeByparentId != null && assetsTypeByparentId.size()>0) {
			flag = "yes";
		}
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
		map.put("assetsTypeOne", assetsTypeOne);
		map.put("pName", pName);
		map.put("flag", flag);
		return new ModelAndView("sys/sys_assets_type_mod",map);
	}
	/**
	 * 修改资产类型
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/modifyAssetsTypeOk.do")
	@Token(remove=true, save = false)
	public void modifyAssetsTypeOk(HttpServletRequest request,HttpServletResponse response) throws Exception{
		sysAssetsTypeBusiness.updateByPrimaryKey(request, response);
	}
	
	/**
	 * 删除资产类型
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/deleteAssetsType.do")
	public JsonResult<String> deleteAssetsType(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String result = sysAssetsTypeBusiness.deleteByPrimaryKey(request, response);
		return new JsonResult<String>(result);
	}
	/**
	 * 修改资产类型
	 * @author 李帅
	 * @throws Exception 
	 */
	@ResponseBody
	@RequestMapping("/checkAssetsType.do")
	public SysAssetsType checkAssetsType(HttpServletRequest request,HttpServletResponse response) throws Exception{
		return sysAssetsTypeBusiness.selectByPrimaryKey(request, response);
	}
	@ResponseBody
	@RequestMapping("/selectAssetsTypeByLevel.do")
	public List<SysAssetsType> selectAssetsTypeByLevel(HttpServletRequest request) throws Exception{
		Map<String, Object> pgMap = new HashMap<String, Object>();
		String level = request.getParameter("level");
		int levelId=Integer.valueOf(level);
		pgMap.put("levelId", levelId);
		String assetsTypeId=request.getParameter("assetsTypeId");
		if (Utils.isNum(assetsTypeId)) {
			int assetsId = Integer.valueOf(assetsTypeId);
			pgMap.put("assetsId", assetsId);
		}
		List<SysAssetsType> assetsType = sysAssetsTypeBusiness.selectAssetsTypeByLevel(pgMap);
		return assetsType;
	}
	
	@ResponseBody
	@RequestMapping("/selectAssetsTypeAll.do")
	public List<SysAssetsType> selectAssetsTypeAll() throws Exception{
		List<SysAssetsType> sysAssetsTypeAll = sysAssetsTypeBusiness.selectPrimAll();
		for (SysAssetsType sysAssetsType : sysAssetsTypeAll) {
			String allName=ChinesePinYin.toHanyuPinyin(sysAssetsType.getName());
			sysAssetsType.setExtend1(allName);
			String firstName=ChineseToInitials.getPYIndexStr(sysAssetsType.getName(), true).toLowerCase();
			sysAssetsType.setExtend2(firstName);
		}
		return sysAssetsTypeAll;
	}
	
	@ResponseBody
	@RequestMapping("/selectAssetsTypeForTree.do")
	public Map<String, Object> selectAssetsTypeForTree() throws Exception{
		return sysAssetsTypeBusiness.selectAssetsTypeForTree();
	}
	/**
	 * 资产类型查询并回填已选择的资产
	 * @param request
	 * @return
	 */
	@RequestMapping("/showAssets.do")
	public ModelAndView showAssets(HttpServletRequest request){
		Map<String, Object> map = new HashMap<String, Object>();
		String assets_id = request.getParameter("assets_id");
		String property = request.getParameter("property");
		String[] assets = assets_id.split(",");
		String[] prop = property.split(",");
		List<Map<String,Object>> assetsListAll = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < assets.length; i++) {
			Map<String,Object> assetsMap = new HashMap<String,Object>();
			assetsMap.put("assets", assets[i]);
			assetsMap.put("prop", prop[i]);
			assetsListAll.add(assetsMap);
		}
		map.put("assetsListAll", assetsListAll);
		return new ModelAndView("service/ser_deal_assets",map);
	}
	@RequestMapping("/selectAssetsTypePareateId.do")
	@ResponseBody
	public List<SysAssetsType> selectAssetsTypePareateId(HttpServletRequest req){
		String id = req.getParameter("id");
		List<SysAssetsType> selectAssetsTypePareateId = sysAssetsTypeBusiness.selectAssetsTypePareateId(Integer.valueOf(id));
		return selectAssetsTypePareateId;
	}
}
