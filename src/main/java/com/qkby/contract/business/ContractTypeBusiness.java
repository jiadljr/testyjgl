package com.qkby.contract.business;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.ContractType;

public interface ContractTypeBusiness {
	/**
	 * 
	 * @Description (查询所有的合同类型)
	 * @param map
	 * @return
	 */
	public List<ContractType> selectContractTypeAllByCondition(Map<String,Object> map);
	/**
	 * 
	 * @Description (查询所有的合同类型)
	 * @return
	 */
	public List<ContractType> selectContractTypeAll();
	/**
	 * 
	 * @Description (根据ID查询合同类型)
	 * @param id
	 * @return
	 */
	public ContractType selectContractTypeById(String id);
	/**
	 * 
	 * @Description (新增合同类型)
	 * @param contractType
	 * @return
	 */
	public int insertContractType(ContractType contractType);
	/**
	 * 
	 * @Description (修改合同类型)
	 * @param contractType
	 * @return
	 */
	public int updateContractType(ContractType contractType);
	/**
	 * 
	 * @Description (删除合同类型)
	 * @param id
	 * @return
	 */
	public int deleteContractType(Map<String,Object> map);
}
