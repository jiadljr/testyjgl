package com.qkby.contract.entity;

import java.util.Date;
/**
 * 
 * @ClassName ContractInfo
 * @Description TODO(合同管理主表实体类)
 * @author Administrator
 * @Date 2018年5月3日 上午11:35:04
 * @version 1.0.0
 */
public class ContractInfo {
	
	private String id;
	private String code;
	private String contractTitle;
	private String firstPartyUnit;
	private String secondPartyUnit;
	private String firstPartyPrincipal;
	private String secondPartyPrincipal;
	private String firstPartyContactWay;
	private String secondPartyContactWay;
	private String contractTypeCode;
	private Date signTime;
	private double contractMoney;
	private Integer honourAgreement;
	private String currencyId;
	private String fundSource;
	private String paymentMethod;
	private Date contractStartTime;
	private Date contractEndTime;
	private String contractObject;
	private String contractDesc;
	private Integer logicDelete;
	private String extend1;
	private String extend2;
	private String extend3;
	private String extend4;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContractTitle() {
		return contractTitle;
	}
	public void setContractTitle(String contractTitle) {
		this.contractTitle = contractTitle;
	}
	public String getFirstPartyUnit() {
		return firstPartyUnit;
	}
	public void setFirstPartyUnit(String firstPartyUnit) {
		this.firstPartyUnit = firstPartyUnit;
	}
	public String getSecondPartyUnit() {
		return secondPartyUnit;
	}
	public void setSecondPartyUnit(String secondPartyUnit) {
		this.secondPartyUnit = secondPartyUnit;
	}
	public String getFirstPartyPrincipal() {
		return firstPartyPrincipal;
	}
	public void setFirstPartyPrincipal(String firstPartyPrincipal) {
		this.firstPartyPrincipal = firstPartyPrincipal;
	}
	public Integer getHonourAgreement(){
		return honourAgreement;
	}
	public void setHonourAgreement(Integer honourAgreement){
		this.honourAgreement = honourAgreement;
	}
	public String getSecondPartyPrincipal() {
		return secondPartyPrincipal;
	}
	public void setSecondPartyPrincipal(String secondPartyPrincipal) {
		this.secondPartyPrincipal = secondPartyPrincipal;
	}
	public String getFirstPartyContactWay() {
		return firstPartyContactWay;
	}
	public void setFirstPartyContactWay(String firstPartyContactWay) {
		this.firstPartyContactWay = firstPartyContactWay;
	}
	public String getSecondPartyContactWay() {
		return secondPartyContactWay;
	}
	public void setSecondPartyContactWay(String secondPartyContactWay) {
		this.secondPartyContactWay = secondPartyContactWay;
	}
	public String getContractTypeCode() {
		return contractTypeCode;
	}
	public void setContractTypeCode(String contractTypeCode) {
		this.contractTypeCode = contractTypeCode;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public double getContractMoney() {
		return contractMoney;
	}
	public void setContractMoney(double contractMoney) {
		this.contractMoney = contractMoney;
	}
	public String getCurrencyId() {
		return currencyId;
	}
	public void setCurrencyId(String currencyId) {
		this.currencyId = currencyId;
	}
	public String getFundSource() {
		return fundSource;
	}
	public void setFundSource(String fundSource) {
		this.fundSource = fundSource;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public Date getContractStartTime() {
		return contractStartTime;
	}
	public void setContractStartTime(Date contractStartTime) {
		this.contractStartTime = contractStartTime;
	}
	public Date getContractEndTime() {
		return contractEndTime;
	}
	public void setContractEndTime(Date contractEndTime) {
		this.contractEndTime = contractEndTime;
	}
	public String getContractObject() {
		return contractObject;
	}
	public void setContractObject(String contractObject) {
		this.contractObject = contractObject;
	}
	public String getContractDesc() {
		return contractDesc;
	}
	public void setContractDesc(String contractDesc) {
		this.contractDesc = contractDesc;
	}
	public void setLogicDelete(Integer logicDelete){
		this.logicDelete = logicDelete;
	}
	public Integer getLogicDelete(){
		return logicDelete;
	}
	public String getExtend1() {
		return extend1;
	}
	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}
	public String getExtend2() {
		return extend2;
	}
	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}
	public String getExtend3() {
		return extend3;
	}
	public void setExtend3(String extend3) {
		this.extend3 = extend3;
	}
	public String getExtend4(){
		return extend4;
	}
	public void setextend4(String extend4){
		this.extend4 = extend4;
	}
	public ContractInfo(String id, String code, String contractTitle, String firstPartyUnit, String secondPartyUnit,
			String firstPartyPrincipal, String secondPartyPrincipal, String firstPartyContactWay,
			String secondPartyContactWay, String contractTypeCode, Date signTime, double contractMoney,Integer honourAgreement,
			String currencyId, String fundSource, String paymentMethod, Date contractStartTime, Date contractEndTime,
			String contractObject, String contractDesc, Integer logicDelete, String extend1, String extend2, String extend3, String extend4) {
		super();
		this.id = id;
		this.code = code;
		this.contractTitle = contractTitle;
		this.firstPartyUnit = firstPartyUnit;
		this.secondPartyUnit = secondPartyUnit;
		this.firstPartyPrincipal = firstPartyPrincipal;
		this.secondPartyPrincipal = secondPartyPrincipal;
		this.firstPartyContactWay = firstPartyContactWay;
		this.secondPartyContactWay = secondPartyContactWay;
		this.contractTypeCode = contractTypeCode;
		this.signTime = signTime;
		this.contractMoney = contractMoney;
		this.honourAgreement = honourAgreement;
		this.currencyId = currencyId;
		this.fundSource = fundSource;
		this.paymentMethod = paymentMethod;
		this.contractStartTime = contractStartTime;
		this.contractEndTime = contractEndTime;
		this.contractObject = contractObject;
		this.contractDesc = contractDesc;
		this.logicDelete = logicDelete;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
		this.extend4 = extend4;
	}
	public ContractInfo() {
		super();
	}
	@Override
	public String toString() {
		return "ContractInfo [id=" + id + ", code=" + code + ", contractTitle=" + contractTitle + ", firstPartyUnit="
				+ firstPartyUnit + ", secondPartyUnit=" + secondPartyUnit + ", firstPartyPrincipal="
				+ firstPartyPrincipal + ", secondPartyPrincipal=" + secondPartyPrincipal + ", firstPartyContactWay="
				+ firstPartyContactWay + ", secondPartyContactWay=" + secondPartyContactWay + ", contractTypeCode="
				+ contractTypeCode + ", signTime=" + signTime + ", contractMoney=" + contractMoney +", honourAgreement ="+honourAgreement+ ", currencyId="
				+ currencyId + ", fundSource=" + fundSource + ", paymentMethod=" + paymentMethod
				+ ", contractStartTime=" + contractStartTime + ", contractEndTime=" + contractEndTime
				+ ", contractObject=" + contractObject + ", contractDesc=" + contractDesc +", logicDelete="+logicDelete+ ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", extend3=" + extend3 +", extend4="+ extend4 + "]";
	}
	
}
