package com.qkby.contract.dao;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.ContractInfo;

/**
 * 
 * @ClassName ContractInfoDao
 * @Description TODO(合同管理Dao)
 * @author Administrator
 * @Date 2018年5月3日 上午11:48:12
 * @version 1.0.0
 */
public interface ContractInfoDao {
	/**
	 * 
	 * @Description (查询所有的合同信息（带条件）)
	 * @param map
	 * @return
	 */
	public List<ContractInfo> selectContractInfoByCondition(Map<String,Object> map);
	/**
	 * 
	 * @Description (根据ID查询合同信息)
	 * @param id
	 * @return
	 */
	public ContractInfo selectContractInfoById(String id);
	/**
	 * 
	 * @Description (查询合同信息的总条数)
	 * @param map
	 * @return
	 */
	public int selectContractInfoCount(Map<String,Object> map);
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
	public int updateContractInfoById(ContractInfo contract);
	/**
	 * 
	 * @Description (修改履约情况)
	 * @param contract
	 * @return
	 */
	public int updateHonourAgreement(ContractInfo contract);
	/**
	 * 
	 * @Description (查询所有的合同信息（不带条件）)
	 * @return
	 */
	public List<ContractInfo> selectContractInfoAll();

}
