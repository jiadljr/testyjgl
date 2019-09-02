package com.qkby.contract.business;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.contract.dao.ContractFileDao;
import com.qkby.contract.entity.ContractFile;
@Service
public class ContractFileBusinessImpl implements ContractFileBusiness{
	@Resource
	private ContractFileDao contractFileDao;

	@Override
	public int insertContractFile(ContractFile contractFile) {
		return contractFileDao.insertContractFile(contractFile);
	}

	@Override
	public int deleteContractFile(String id) {
		return contractFileDao.deleteContractFile(id);
	}
	
	

}
