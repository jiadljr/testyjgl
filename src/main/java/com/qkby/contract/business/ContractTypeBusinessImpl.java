package com.qkby.contract.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.contract.dao.ContractTypeDao;
import com.qkby.contract.entity.ContractType;

@Service
public class ContractTypeBusinessImpl implements ContractTypeBusiness {
	@Resource
	private ContractTypeDao contractTypeDao;

	@Override
	public List<ContractType> selectContractTypeAllByCondition(Map<String, Object> map) {
		
		return null;
	}

	@Override
	public List<ContractType> selectContractTypeAll() {
		return contractTypeDao.selectContractTypeAll();
	}

	@Override
	public ContractType selectContractTypeById(String id) {
		return contractTypeDao.selectContractTypeById(id);
	}

	@Override
	public int insertContractType(ContractType contractType) {
		return contractTypeDao.insertContractType(contractType);
	}

	@Override
	public int updateContractType(ContractType contractType) {
		return contractTypeDao.updateContractType(contractType);
	}

	@Override
	public int deleteContractType(Map<String,Object> map) {
		return contractTypeDao.deleteContractType(map);
	}

}
