package com.qkby.contract.dao;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.ContractCurrencyType;

/**
 * 
 * @ClassName ContractCurrencyTypeDao
 * @Description TODO(合同货币Dao)
 * @author Administrator
 * @Date 2018年5月3日 上午11:47:25
 * @version 1.0.0
 */
public interface ContractCurrencyTypeDao {
	/**
	 * 
	 * @Description (查询所有的合同货币（带条件）)
	 * @param map
	 * @return
	 */
	public List<ContractCurrencyType> selectContractCurrencyTypeByConditionAll(Map<String,Object> map);
	/**
	 * 
	 * @Description (查询所有的合同类型)
	 * @return
	 */
	public List<ContractCurrencyType> selectContractCurrencyTypeAll();
	/**
	 * 
	 * @Description (根据ID查询合同货币)
	 * @param id
	 * @return
	 */
	public ContractCurrencyType selectContractCurrencyTypeById(String id);
	/**
	 * 
	 * @Description (新增合同货币)
	 * @param contractCurrencyType
	 * @return
	 */
	public int insertContractCurrencyType(ContractCurrencyType contractCurrencyType);
	/**
	 * 
	 * @Description (修改合同货币)
	 * @param contractCurrencyType
	 * @return
	 */
	public int updateContractCurrencyType(ContractCurrencyType contractCurrencyType);
	/**
	 * 
	 * @Description (根据ID删除合同货币)
	 * @param id
	 * @return
	 */
	public int deleteContractCurrencyType(String id);

}
