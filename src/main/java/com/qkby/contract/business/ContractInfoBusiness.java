package com.qkby.contract.business;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.ContractInfo;

public interface ContractInfoBusiness {
	/**
	 * 
	 * @Description (查询所有的合同信息（带条件）)
	 * @param map
	 * @return
	 */
	public List<ContractInfo> selectContractInfoAll(Map<String,Object> map);
	/**
	 * 
	 * @Description (根据ID查询合同信息)
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> selectContractInfoById(String id) throws Exception;
	/**
	 * 
	 * @Description (新增合同信息)
	 * @param contract
	 * @return
	 */
	public int insertContractInfo(ContractInfo contract);
	/**
	 * 
	 * @Description (合同管理逻辑删除)
	 * @param contract
	 * @return
	 */
	public int contractLogicDelete(ContractInfo contract);
	/**
	 * 
	 * @Description (根据ID修改合同信息)
	 * @param contract
	 * @return
	 */
	public int updateContractInfoById(ContractInfo contract)throws Exception ;
	/**
	 * 
	 * @Description (查询付款台账)
	 * @return
	 */
	public List<ContractInfo> selectPaymentStandingBook(Map<String,Object> map);
	/**
	 * 
	 * @Description (查询合同信息总条数)
	 * @param map
	 * @return
	 */
	public int selectContractInfoCount(Map<String,Object> map);
	/**
	 * 
	 * @Description (查询合同明细)
	 * @param id
	 * @return
	 */
	public Map<String,Object> selectContractDetail(String id) throws Exception ;
	/**
	 * 
	 * @Description (跳转至修改页面)
	 * @param id
	 * @return
	 */
	public Map<String,Object> queryContractUpdatePage(String id);
	/**
	 * 
	 * @Description (跳转至新增页面)
	 * @return
	 */
	public Map<String,Object> queryInsertPage();
	/**
	 * 
	 * @Description (修改履约情况)
	 * @return
	 */
	public int updateHonourAgreement(ContractInfo contract);
	/**
	 * 
	 * @Description (跳转至付款登记页面)
	 * @param id
	 * @return
	 */
	public Map<String,Object> queryPayRegisterPage(String id);
	/**
	 * 
	 * @Description (预览导出)
	 * @param map
	 * @return
	 */
	public List<ContractInfo> selectExportPreview();
	
}
