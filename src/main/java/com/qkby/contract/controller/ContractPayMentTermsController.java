package com.qkby.contract.controller;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qkby.constant.ConstantMenu;
import com.qkby.contract.business.ContractPayMentTermsBusiness;
import com.qkby.contract.entity.ContractPaymentTerms;

import net.sf.json.JSONArray;

@Controller
public class ContractPayMentTermsController {
	
	@Resource
	private ContractPayMentTermsBusiness contractPayMentTermsBusiness;
	/**
	 * 
	 * @Description (新增支付条件信息)
	 * @param request
	 */
	@RequestMapping("/insertContractPayMentTerms.do")
	@ResponseBody
	public int insertContractPayMentTerms(HttpServletRequest request){
		ContractPaymentTerms contractPaymentTerms = new ContractPaymentTerms();
		String payName = request.getParameter("payName");
		String paymentAmount = request.getParameter("paymentAmount");
		String paymentProportion = request.getParameter("paymentProportion");
		String contractId = request.getParameter("contractId");
		double parseDouble = Double.parseDouble(paymentAmount);
		float parseFloat = Float.parseFloat(paymentProportion);
		float closedProportion = 0.0f;
		String uuid = UUID.randomUUID().toString(); 
		uuid = uuid.replace("-", "");
		contractPaymentTerms.setId(uuid);
		contractPaymentTerms.setContractId(contractId);
		contractPaymentTerms.setName(payName);
		contractPaymentTerms.setPaymentAmount(parseDouble);
		contractPaymentTerms.setPaymentProportion(parseFloat);
		contractPaymentTerms.setClosedProportion(closedProportion);
		contractPaymentTerms.setLogicDelete(ConstantMenu.CONTRACT_ONE);
		return contractPayMentTermsBusiness.insertContractPaymentTerms(contractPaymentTerms);
	}
	/**
	 * 
	 * @Description (批量新增支付条件)
	 * @param request
	 * @return
	 */
	@RequestMapping("/insertContractPayMentTermsBatch.do")
	@ResponseBody
	@Transactional
	public int insertContractPayMentTermsBatch(HttpServletRequest request){
		ContractPaymentTerms contractPaymentTerms = new ContractPaymentTerms();
		String paySoArray = request.getParameter("paySoArray");
		JSONArray json=JSONArray.fromObject(paySoArray);
		for (int i = 0; i < json.size(); i++) {
			String name = (String)json.getJSONObject(i).get("name");
			if(name != null){
				double pay1 = 0.0;
				if(!json.getJSONObject(i).get("pay1").equals("")){
					pay1 = Double.valueOf((String) json.getJSONObject(i).get("pay1"));
				}
				float pay2 = 0.0f;
				if(!json.getJSONObject(i).get("pay2").equals("")){
					pay2 = (float)json.getJSONObject(i).get("pay2");
				}
				String contractId = (String)json.getJSONObject(i).get("contractId");
				String uuid = UUID.randomUUID().toString(); 
				uuid = uuid.replace("-", "");
				contractPaymentTerms.setId(uuid);
				contractPaymentTerms.setName(name);
				contractPaymentTerms.setContractId(contractId);
				contractPaymentTerms.setPaymentAmount(pay1);
				contractPaymentTerms.setPaymentProportion(pay2);
				contractPaymentTerms.setLogicDelete(ConstantMenu.CONTRACT_ONE);
				contractPayMentTermsBusiness.insertContractPaymentTerms(contractPaymentTerms);
				
			}
		}
		return 0;
	}
	@RequestMapping("/updateContractPayMentTermsBatch.do")
	@ResponseBody
	@Transactional
	public int updateContractPayMentTermsBatch(HttpServletRequest request){
		ContractPaymentTerms contractPaymentTerms = new ContractPaymentTerms();
		Map<String,Object> map = new HashMap<String,Object>();
		String paySoArray = request.getParameter("paySoArray");
		String payId = request.getParameter("payId");
		if(!"".equals(payId)){
			String[] split = payId.split(",");
			for (int i = 0; i < split.length; i++) {
				map.put("id", split[i]);
				map.put("logicDelete", ConstantMenu.CONTRACT_TWO);
				contractPayMentTermsBusiness.deleteContractPaymentTerms(map);
			}
		}
		JSONArray json=JSONArray.fromObject(paySoArray);
		for (int i = 0; i < json.size(); i++) {
			String name = (String)json.getJSONObject(i).get("name");
			if(name != null){
		    String id = (String)json.getJSONObject(i).get("payId");
		    if(id == null){
		    	double pay1 = 0.0;
				if(!json.getJSONObject(i).get("pay1").equals("")){
					pay1 = Double.valueOf((String) json.getJSONObject(i).get("pay1"));
				}
				float pay2 = 0.0f;
				System.out.println(json.getJSONObject(i).get("pay2"));
				if(!json.getJSONObject(i).get("pay2").equals("")){
					pay2 = Float.valueOf((String) json.getJSONObject(i).get("pay2"));
				}
				String contractId = (String)json.getJSONObject(i).get("contractId");
				String uuid = UUID.randomUUID().toString(); 
				uuid = uuid.replace("-", "");
				contractPaymentTerms.setId(uuid);
				contractPaymentTerms.setName(name);
				contractPaymentTerms.setContractId(contractId);
				contractPaymentTerms.setPaymentAmount(pay1);
				contractPaymentTerms.setPaymentProportion(pay2);
				contractPaymentTerms.setLogicDelete(ConstantMenu.CONTRACT_ONE);
				contractPayMentTermsBusiness.insertContractPaymentTerms(contractPaymentTerms);
		    }else if(id != null){
		    	double pay1 = 0.0;
				if(!json.getJSONObject(i).get("pay1").equals("")){
					pay1 = Double.valueOf((String) json.getJSONObject(i).get("pay1"));
				}
				float pay2 = 0.0f;
				if(!json.getJSONObject(i).get("pay2").equals("")){
					pay2 = (float)json.getJSONObject(i).get("pay2");
				}
				contractPaymentTerms.setId(id);
				contractPaymentTerms.setName(name);
				contractPaymentTerms.setPaymentAmount(pay1);
				contractPaymentTerms.setPaymentProportion(pay2);
				contractPayMentTermsBusiness.updateContractPaymentTerms(contractPaymentTerms);
		    }
			}
		}
		return 0;
	}
	/**
	 * 
	 * @Description (查询所有的支付条件)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectContractPayMentTermsAll.do")
	@ResponseBody
	public List<ContractPaymentTerms> selectContractPayMentTermsAll(HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		String contractId = request.getParameter("contractId");
		map.put("contractId", contractId);
		return contractPayMentTermsBusiness.selectContractPaymentTermsAll(map);
	}
	/**
	 * 
	 * @Description (根据ID查询支付条件)
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectContractPayMentTermsById.do")
	@ResponseBody
	public ContractPaymentTerms selectContractPayMentTermsById(HttpServletRequest request){
		String paymentId = request.getParameter("paymentId");
		return contractPayMentTermsBusiness.selectContractPaymentTermsById(paymentId);
	}
	/**
	 * 
	 * @Description (修改支付条件)
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateContractPaymentTerms.do")
	@ResponseBody
	public int updateContractPaymentTerms(HttpServletRequest request)throws ParseException{
		Map<String,Object> map = new HashMap<String,Object>();
		String invoice = request.getParameter("invoice");
		String paymentDesc = request.getParameter("paymentDesc");
		String payId = request.getParameter("payId");
		String contractId = request.getParameter("contractId");
		String definition = request.getParameter("definition");
		String payName = request.getParameter("payName");
		String paymentAmount = request.getParameter("paymentAmount");
		String paymentTime = request.getParameter("paymentTime");
		HttpSession session = request.getSession();
		Integer userId = (Integer)session.getAttribute("user_id");
		map.put("paymentAmount", paymentAmount);
		map.put("userId", userId);
		map.put("payName", payName);
		map.put("paymentTime", paymentTime);
		map.put("invoice", invoice);
		map.put("paymentDesc", paymentDesc);
		map.put("contractId", contractId);
		map.put("payId", payId);
		map.put("definition", definition);
		return contractPayMentTermsBusiness.updateContractPaymentTerms(map);
	}
	
	
}
