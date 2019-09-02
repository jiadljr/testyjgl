package com.qkby.contract.business;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.ContractPaymentTerms;

public interface ContractPayMentTermsBusiness {
	
	/**
	 * 
	 * @Description (查询所有的支付条件（带条件）)
	 * @param map
	 * @return
	 */
	public List<ContractPaymentTerms> selectContractPaymentTermsAll(Map<String,Object> map);
	/**
	 * 
	 * @Description (根据ID查询支付条件)
	 * @param id
	 * @return
	 */
	public ContractPaymentTerms selectContractPaymentTermsById(String id);
	/**
	 * 
	 * @Description (新增支付条件)
	 * @param contractPaymentTerms
	 * @return
	 */
	public int insertContractPaymentTerms(ContractPaymentTerms contractPaymentTerms);
	/**
	 * 
	 * @Description (修改支付条件)
	 * @param contractPaymentTerms
	 * @return
	 */
	public int updateContractPaymentTerms(Map<String,Object> map)throws ParseException;
	/**
	 * 
	 * @Description (根据ID删除支付条件)
	 * @param id
	 * @return
	 */
	public int deleteContractPaymentTerms(Map<String,Object> map);
	/**
	 * 
	 * @Description (修改支付条件)
	 * @param contractPaymentTerms
	 * @return
	 */
	public int updateContractPaymentTerms(ContractPaymentTerms contractPaymentTerms);

}
