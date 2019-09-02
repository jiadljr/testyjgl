package com.qkby.contract.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qkby.contract.business.CapitalSourceBusiness;
import com.qkby.contract.entity.CapitalSource;

@Controller
public class CapitalSourceController {
	@Resource
	private CapitalSourceBusiness capitalSourceBusiness;
	/**
	 * 
	 * @Description (查询所有的资金来源（不带条件）)
	 * @return
	 */
	@RequestMapping("/selectCapitalSourceAll.do")
	@ResponseBody
	public List<CapitalSource> selectCapitalSourceAll(){
		return capitalSourceBusiness.selectCapitalSourceAll();
	}
	
}
