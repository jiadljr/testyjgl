package com.qkby.contract.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.contract.dao.ContractInfoDao;
import com.qkby.contract.dao.ContractPayMentTermsDao;
import com.qkby.contract.entity.ContractInfo;
import com.qkby.contract.entity.ContractPaymentTerms;

@Service
public class ContractPayMentTermsBusinessImpl implements ContractPayMentTermsBusiness {
	@Resource
	private ContractPayMentTermsDao contractPayMentTermsDao;
	@Resource
	private ContractInfoDao contractInfoDao;

	@Override
	public List<ContractPaymentTerms> selectContractPaymentTermsAll(Map<String, Object> map) {
		return contractPayMentTermsDao.selectContractPaymentTermsAll(map);
	}

	@Override
	public ContractPaymentTerms selectContractPaymentTermsById(String id) {
		return contractPayMentTermsDao.selectContractPaymentTermsById(id);
	}

	@Override
	public int insertContractPaymentTerms(ContractPaymentTerms contractPaymentTerms) {
		return contractPayMentTermsDao.insertContractPaymentTerms(contractPaymentTerms);
	}

	@Override
	public int updateContractPaymentTerms(Map<String,Object> map) throws ParseException {
		ContractPaymentTerms contractPaymentTerms = new ContractPaymentTerms();
		String definition = (String)map.get("definition");
		if(definition.equals("1")){
			String invoice = (String)map.get("invoice");
			String paymentDesc = (String)map.get("paymentDesc");
			String payId = (String)map.get("payId");
			String contractId = (String)map.get("contractId");
			String paymentAmount = (String)map.get("paymentAmount");
			ContractInfo selectContractInfoById = contractInfoDao.selectContractInfoById(contractId);
			double contractMoney = selectContractInfoById.getContractMoney();
			float parseFloat = Float.parseFloat(paymentAmount);
			float closedProportion = parseFloat/(float)contractMoney*100;
			contractPaymentTerms.setRegistrant((Integer)map.get("userId"));
			contractPaymentTerms.setInvoice(invoice);
			contractPaymentTerms.setClosedProportion(closedProportion);
			contractPaymentTerms.setId(payId);
			contractPaymentTerms.setPaymentDesc(paymentDesc);
			return contractPayMentTermsDao.updateContractPaymentTerms(contractPaymentTerms);
		}else if(definition.equals("2")){
			SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
			String invoice = (String)map.get("invoice");
			String paymentDesc = (String)map.get("paymentDesc");
			String contractId = (String)map.get("contractId");
			String paymentAmount = (String)map.get("paymentAmount");
			String paymentTime = (String)map.get("paymentTime");
			String payName = (String)map.get("payName");
			double parseDouble = Double.parseDouble(paymentAmount);
			String uuid = UUID.randomUUID().toString(); 
			uuid = uuid.replace("-", "");
			contractPaymentTerms.setId(uuid);
			contractPaymentTerms.setName(payName);
			contractPaymentTerms.setPaymentAmount(parseDouble);
			contractPaymentTerms.setPaymentTime(sdf.parse(paymentTime));
			contractPaymentTerms.setInvoice(invoice);
			contractPaymentTerms.setContractId(contractId);
			contractPaymentTerms.setPaymentDesc(paymentDesc);
			return contractPayMentTermsDao.insertContractPaymentTerms(contractPaymentTerms);
		}
		return 0;
	}

	@Override
	public int deleteContractPaymentTerms(Map<String,Object> map) {
		return contractPayMentTermsDao.deleteContractPaymentTerms(map);
	}
	
	@Override
	public int updateContractPaymentTerms(ContractPaymentTerms contractPaymentTerms){
		return contractPayMentTermsDao.updateContractPaymentTerms(contractPaymentTerms);
	}

}
