package com.qkby.contract.business;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.qkby.contract.dao.CapitalSoureDao;
import com.qkby.contract.dao.ContractCurrencyTypeDao;
import com.qkby.contract.dao.ContractFileDao;
import com.qkby.contract.dao.ContractInfoDao;
import com.qkby.contract.dao.ContractPayMentTermsDao;
import com.qkby.contract.dao.ContractTypeDao;
import com.qkby.contract.dao.PaymentMethodDao;
import com.qkby.contract.entity.CapitalSource;
import com.qkby.contract.entity.ContractCurrencyType;
import com.qkby.contract.entity.ContractFile;
import com.qkby.contract.entity.ContractInfo;
import com.qkby.contract.entity.ContractPaymentTerms;
import com.qkby.contract.entity.ContractType;
import com.qkby.contract.entity.PaymentMethod;
import com.qkby.sysmanage.dao.SysFileInfoDao;
import com.qkby.sysmanage.dao.SysUserInfoDao;
import com.qkby.sysmanage.entity.SysFileInfo;
import com.qkby.sysmanage.entity.SysUserInfo;
import com.qkby.work.entity.WorkReportFileInfo;

@Service
public class ContractInfoBusinessImpl implements ContractInfoBusiness{
	
	@Resource
	private ContractInfoDao contractInfoDao;
	@Resource
	private CapitalSoureDao capitalSoureDao;
	@Resource
	private PaymentMethodDao paymentMethodDao;
	@Resource
	private ContractTypeDao contractTypeDao;
	@Resource
	private ContractPayMentTermsDao contractPayMentTermsDao;
	@Resource
	private ContractFileDao contractFileDao;
	@Resource
	private SysFileInfoDao sysFileInfoDao;
	@Resource
	private SysUserInfoDao sysUserInfoDao;
	@Resource
	private ContractCurrencyTypeDao contractCurrencyTypeDao;

	@Override
	public List<ContractInfo> selectContractInfoAll(Map<String, Object> map) {
		List<ContractInfo> contractInfoAll = new ArrayList<ContractInfo>();
		List<ContractInfo> selectContractInfoAll = contractInfoDao.selectContractInfoByCondition(map);
		List<CapitalSource> selectCapitalSourceAll = capitalSoureDao.selectCapitalSourceAll();
		List<ContractType> selectContractTypeAll = contractTypeDao.selectContractTypeAll();
		for (ContractInfo contractInfo : selectContractInfoAll) {
			String fundSource = contractInfo.getFundSource();
			String typeCode = contractInfo.getContractTypeCode();
			for (CapitalSource capitalSource : selectCapitalSourceAll) {
				String id = capitalSource.getId();
				if(fundSource.equals(id)){
					contractInfo.setExtend1(capitalSource.getName());
				}
			}
			for (ContractType contractType : selectContractTypeAll) {
				String id = contractType.getId();
				if(typeCode.equals(id)){
					contractInfo.setExtend2(contractType.getTypeName());
				}
			}
			contractInfoAll.add(contractInfo);
		}
		return contractInfoAll;
	}

	@Override
	public Map<String,Object> selectContractInfoById(String id) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		ContractInfo contractInfo = contractInfoDao.selectContractInfoById(id);
		List<Map<String,Object>> selectPlanFile = contractFileDao.selectContractFileByContractId(id);
		List<ContractPaymentTerms> contractPaymentTerms = contractPayMentTermsDao.selectContractPaymentTermsByContractId(id);
		List<ContractType> contractType = contractTypeDao.selectContractTypeAll();
		List<ContractCurrencyType> contractCurrencyType = contractCurrencyTypeDao.selectContractCurrencyTypeAll();
		List<CapitalSource> capitalSource = capitalSoureDao.selectCapitalSourceAll();
		List<PaymentMethod> paymentMethod = paymentMethodDao.selectPaymentMethodAll();
		map.put("capitalSource", capitalSource);
		map.put("paymentMethod", paymentMethod);
		map.put("contractInfo", contractInfo);
		map.put("contractType", contractType);
		map.put("contractCurrencyType", contractCurrencyType);
		map.put("selectPlanFile", selectPlanFile);
		map.put("contractPaymentTerms", contractPaymentTerms);
		return map;
	}

	@Override
	public int insertContractInfo(ContractInfo contract) {
		int insertContractInfo = contractInfoDao.insertContractInfo(contract);
		return insertContractInfo;
	}

	@Override
	public int contractLogicDelete(ContractInfo contract) {
		return contractInfoDao.contractLogicDelete(contract);
		
	}

	@Override
	public int updateContractInfoById(ContractInfo contract) {
		return contractInfoDao.updateContractInfoById(contract);
	}

	@Override
	public List<ContractInfo> selectPaymentStandingBook(Map<String, Object> map) {
		List<ContractInfo> selectContractInfoAll = contractInfoDao.selectContractInfoByCondition(map);
		List<ContractType> selectContractTypeAll = contractTypeDao.selectContractTypeAll();
		List<ContractPaymentTerms> selectContractPaymentTermsAll = contractPayMentTermsDao.selectContractPaymentTermsAll(map);
		for (ContractInfo contractInfo : selectContractInfoAll) {
			String typeCode = contractInfo.getContractTypeCode();
			String contractId = contractInfo.getId();
			for (ContractType contractType : selectContractTypeAll) {
				String id = contractType.getId();
				if(typeCode.equals(id)){
					contractInfo.setExtend1(contractType.getTypeName());
				}
			}
			for (ContractPaymentTerms contractPaymentTerms : selectContractPaymentTermsAll) {
				String contractId2 = contractPaymentTerms.getContractId();
				if(contractId2.equals(contractId)){
					float closedProportion = contractPaymentTerms.getClosedProportion();
					contractInfo.setextend4(String.valueOf(closedProportion));
				}
			}
			ContractPaymentTerms contractAlreadySum = contractPayMentTermsDao.selectAlreadyPaymentAmountSum(contractId);
			ContractPaymentTerms contractNotSum = contractPayMentTermsDao.selectNotPaymentAmountSum(contractId);
			String already = contractAlreadySum.getExtend1();
			String not = contractNotSum.getExtend1();
			float alreadySum = Float.parseFloat(already);
			float notSum = Float.parseFloat(not);
			contractInfo.setExtend2(String.valueOf(alreadySum));
			contractInfo.setExtend3(String.valueOf(notSum));
		}
		return selectContractInfoAll;
	}

	@Override
	public int selectContractInfoCount(Map<String, Object> map) {
		return contractInfoDao.selectContractInfoCount(map);
	}

	@Override
	public Map<String, Object> selectContractDetail(String id) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		ContractInfo contractInfo = contractInfoDao.selectContractInfoById(id);
		List<ContractPaymentTerms> contractPaymentTerms = contractPayMentTermsDao.selectContractPaymentTermsByContractId(id);
		List<SysUserInfo> seleUserAll = sysUserInfoDao.seleUserAll();
		for (ContractPaymentTerms contractPaymentTerms2 : contractPaymentTerms) {
			Integer registrant = contractPaymentTerms2.getRegistrant();
			for (SysUserInfo sysUserInfo : seleUserAll) {
				Integer userId = sysUserInfo.getId();
				if(registrant == userId){
					contractPaymentTerms2.setExtend1(sysUserInfo.getName());
				}
			}
		}
		map.put("contractInfo", contractInfo);
		map.put("contractPaymentTerms", contractPaymentTerms);
		return map;
	}

	@Override
	public Map<String, Object> queryContractUpdatePage(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		ContractInfo contractInfo = contractInfoDao.selectContractInfoById(id);
		List<ContractPaymentTerms> contractPaymentTerms = contractPayMentTermsDao.selectContractPaymentTermsByContractId(id);
		map.put("contractInfo", contractInfo);
		map.put("contractPaymentTerms", contractPaymentTerms);
		return map;
	}

	@Override
	public Map<String, Object> queryInsertPage() {
		Map<String,Object> map = new HashMap<String,Object>();
		List<ContractType> contractType = contractTypeDao.selectContractTypeAll();
		List<ContractCurrencyType> contractCurrencyType = contractCurrencyTypeDao.selectContractCurrencyTypeAll();
		List<CapitalSource> capitalSource = capitalSoureDao.selectCapitalSourceAll();
		List<PaymentMethod> paymentMethod = paymentMethodDao.selectPaymentMethodAll();
		map.put("contractType", contractType);
		map.put("contractCurrencyType", contractCurrencyType);
		map.put("capitalSource", capitalSource);
		map.put("paymentMethod", paymentMethod);
		return map;
	}

	@Override
	public int updateHonourAgreement(ContractInfo contract) {
		return contractInfoDao.updateHonourAgreement(contract);
	}

	@Override
	public Map<String, Object> queryPayRegisterPage(String id) {
		Map<String,Object> map = new HashMap<String,Object>();
		ContractInfo selectContractInfoById = contractInfoDao.selectContractInfoById(id);
		ContractPaymentTerms selectAlreadyPaymentAmountSum = contractPayMentTermsDao.selectAlreadyPaymentAmountSum(id);
		List<ContractPaymentTerms> selectContractTermsByContractId = contractPayMentTermsDao.selectContractTermsByContractId(id);
		String extend1 = selectAlreadyPaymentAmountSum.getExtend1();
		double contractMoney = selectContractInfoById.getContractMoney();
		double parseFloat = Double.parseDouble(extend1);
		double f = parseFloat/contractMoney;
		NumberFormat nf  =  NumberFormat.getPercentInstance();
		nf.setMinimumFractionDigits( 1 );
		String str  =  nf.format(f);
		map.put("contractInfo", selectContractInfoById);
		map.put("alreadyPaymentAmountSum", selectAlreadyPaymentAmountSum);
		map.put("str", str);
		map.put("contractTerms", selectContractTermsByContractId);
		return map;
	}

	@Override
	public List<ContractInfo> selectExportPreview() {
		List<ContractInfo> selectContractInfoAll = contractInfoDao.selectContractInfoAll();
		return selectContractInfoAll;
	}
}
