package com.qkby.contract.dao;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.ContractPaymentTerms;

/**
 * 
 * @ClassName ContractPayMentTermsDao
 * @Description TODO(支付条件Dao)
 * @author Administrator
 * @Date 2018年5月3日 上午11:48:52
 * @version 1.0.0
 */
public interface ContractPayMentTermsDao {
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
	 * @Description (根据合同ID查询支付条件)
	 * @param contractId
	 * @return
	 */
	public List<ContractPaymentTerms> selectContractPaymentTermsByContractId(String contractId);
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
	public int updateContractPaymentTerms(ContractPaymentTerms contractPaymentTerms);
	/**
	 * 
	 * @Description (根据ID删除支付条件)
	 * @param id
	 * @return
	 */
	public int deleteContractPaymentTerms(Map<String,Object> map);
	/**
	 * 
	 * @Description (根据合同ID查询支付条件支付金额未结金额的总和)
	 * @param id
	 * @return
	 */
	public ContractPaymentTerms selectNotPaymentAmountSum(String id);
	/**
	 * 
	 * @Description (根据合同ID查询支付条件支付金额已结金额的总和)
	 * @param id
	 * @return
	 */
	public ContractPaymentTerms selectAlreadyPaymentAmountSum(String id);
	/**
	 * 
	 * @Description (根据合同ID查询未支付的条件)
	 * @param contractId
	 * @return
	 */
	public List<ContractPaymentTerms> selectContractTermsByContractId(String contractId);

}
