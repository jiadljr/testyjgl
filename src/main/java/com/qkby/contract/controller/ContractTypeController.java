package com.qkby.contract.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qkby.constant.ConstantMenu;
import com.qkby.contract.business.ContractTypeBusiness;
import com.qkby.contract.entity.ContractType;

@Controller
public class ContractTypeController {
	@Resource
	private ContractTypeBusiness contractTypeBusiness;
	/**
	 * 
	 * @Description (查询所有的合同类型)
	 * @return
	 */
	@RequestMapping("/selectContractTypeAll.do")
	@ResponseBody
	public List<ContractType> selectContractTypeAll(){
		return contractTypeBusiness.selectContractTypeAll();
	}
	/**
	 * 
	 * @Description (根据ID查询合同类型)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectContractTypeBuId.do")
	@ResponseBody
	public ContractType selectContractTypeBuId(HttpServletRequest request){
		String contractTypeId = request.getParameter("contractTypeId");
		return contractTypeBusiness.selectContractTypeById(contractTypeId);
	}
	/**
	 * 
	 * @Description (新增合同类型)
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertContractType.do")
	@ResponseBody
	public String insertContractType(HttpServletRequest request){
		ContractType contractType = new ContractType();
		String uuid = UUID.randomUUID().toString(); 
		uuid = uuid.replace("-", "");
		String code = "contract"+new SimpleDateFormat("yyMMddhhmmss").format(new Date());
		String typeName = request.getParameter("typeName");
		String typeDesc = request.getParameter("typeDesc");
		String color = request.getParameter("color");
		contractType.setId(uuid);
		contractType.setCode(code);
		contractType.setColor(color);
		contractType.setTypeName(typeName);
		contractType.setTypeExplain(typeDesc);
		contractType.setLogicallyDelete(1);
		contractTypeBusiness.insertContractType(contractType);
		return "success";
	}
	/**
	 * 
	 * @Description (修改合同类型)
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateContractType.do")
	@ResponseBody
	public String updateContractType(HttpServletRequest request){
		ContractType contractType = new ContractType();
		String typeId = request.getParameter("typeId");
		String typeName = request.getParameter("typeName");
		String typeDesc = request.getParameter("typeDesc");
		String color = request.getParameter("color");
		contractType.setId(typeId);
		contractType.setColor(color);
		contractType.setTypeName(typeName);
		contractType.setTypeExplain(typeDesc);
		contractTypeBusiness.updateContractType(contractType);
		return "success";
	}
	/**
	 * 
	 * @Description (删除合同类型)
	 * @param request
	 * @return
	 */
	@RequestMapping("/deleteContractType.do")
	@ResponseBody
	public String deleteContractType(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String typeId = request.getParameter("typeId");
		map.put("id", typeId);
		map.put("logicallyDelete", ConstantMenu.CONTRACT_TWO);
		contractTypeBusiness.deleteContractType(map);
		return "success";
	}

}
