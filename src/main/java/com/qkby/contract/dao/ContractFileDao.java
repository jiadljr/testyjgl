package com.qkby.contract.dao;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.ContractFile;

public interface ContractFileDao {
	/**
	 * 
	 * @Description (新增合同文件中间表)
	 * @param contractFile
	 * @return
	 */
	public int insertContractFile(ContractFile contractFile);
	/**
	 * 
	 * @Description (根据合同ID查询文件ID)
	 * @return
	 */
	public List<Map<String,Object>> selectContractFileByContractId(String contractId);
	/**
	 * 
	 * @Description (根据ID删除文件信息)
	 * @return
	 */
	public int deleteContractFile(String id);
}
