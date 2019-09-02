package com.qkby.contract.business;

import com.qkby.contract.entity.ContractFile;

public interface ContractFileBusiness {
	/**
	 * 
	 * @Description (新增合同文件中间表)
	 * @param contractFile
	 * @return
	 */
	public int insertContractFile(ContractFile contractFile);
	/**
	 * 
	 * @Description (根据ID删除文件信息)
	 * @param id
	 * @return
	 */
	public int deleteContractFile(String id);

}
