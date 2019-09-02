package com.qkby.analysis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.qkby.analysis.business.EventInfoSkyBusiness;
import com.qkby.analysis.entity.EventInfoSky;

/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午4:06:36 
 *     
 * @version </pre>
 */
@Controller
public class EventInfoSkyController {
  @Resource
  EventInfoSkyBusiness eventInfoSkyBusiness;
  /**
   * <pre>querySky 
   * Created by 家栋梁 on 2017年10月25日下午4:45:20  
   *
   * @return</pre>
   */
  @RequestMapping("/querySky.do")
  public String querySky(){
	  return "analysis/ana_time_info";
  }
  /**
   * <pre>selectSky 
   * Created by 家栋梁 on 2017年10月25日下午4:08:32  
   * 申告分布时间统计
   * @return</pre>
 * @throws Exception 
   */
  @RequestMapping("/selectSky.do")
  @ResponseBody
  public List<EventInfoSky> selectSky() throws Exception{
	  return eventInfoSkyBusiness.selectSky();
  }
  @RequestMapping("/showAssetsDeal.do")
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
	  return new ModelAndView("analysis/ana_time_dept",map);
  }
  @RequestMapping("/showSourcDeal.do")
  public ModelAndView showSourcDeal(HttpServletRequest request){
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
	  return new ModelAndView("analysis/ana_time_sourc",map);
  }
  @RequestMapping("/showServiceDeal.do")
  public ModelAndView showServiceDeal(HttpServletRequest request){
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
	  return new ModelAndView("analysis/ana_time_service",map);
  }
}
