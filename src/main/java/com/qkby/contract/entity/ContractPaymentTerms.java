package com.qkby.contract.entity;

import java.util.Date;

/**
 * 
 * @ClassName ContractPaymentTerms
 * @Description TODO(支付条件表实体类)
 * @author Administrator
 * @Date 2018年5月3日 上午11:39:10
 * @version 1.0.0
 */
public class ContractPaymentTerms {
	
	private String id;
	private String code;
	private String contractId;
	private String name;
	private double paymentAmount;
	private float paymentProportion;
	private float closedProportion;
	private Date paymentTime;
	private String invoice;
	private Integer registrant;
	private String paymentDesc;
	private Integer logicDelete;
	private String extend1;
	private String extend2;
	private String extend3;
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
	public String getContractId(){
		return contractId;
	}
	public void setContractId(String contractId){
		this.contractId = contractId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPaymentAmount() {
		return paymentAmount;
	}
	public void setPaymentAmount(double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	public float getPaymentProportion() {
		return paymentProportion;
	}
	public void setPaymentProportion(float paymentProportion) {
		this.paymentProportion = paymentProportion;
	}
	public float getClosedProportion(){
		return closedProportion;
	}
	public void setClosedProportion(float closedProportion){
		this.closedProportion = closedProportion;
	}
	public Date getPaymentTime() {
		return paymentTime;
	}
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public Integer getRegistrant(){
		return registrant;
	}
	public void setRegistrant(Integer registrant){
		this.registrant = registrant;
	}
	public String getPaymentDesc() {
		return paymentDesc;
	}
	public void setPaymentDesc(String paymentDesc) {
		this.paymentDesc = paymentDesc;
	}
	public Integer getLogicDelete(){
		return logicDelete;
	}
	public void setLogicDelete(Integer logicDelete){
		this.logicDelete = logicDelete;
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
	public ContractPaymentTerms(String id, String code, String contractId, String name, double paymentAmount, float paymentProportion,
			float closedProportion, Date paymentTime, String invoice, Integer registrant, String paymentDesc, Integer logicDelete, String extend1, String extend2, String extend3) {
		super();
		this.id = id;
		this.code = code;
		this.contractId = contractId;
		this.name = name;
		this.paymentAmount = paymentAmount;
		this.paymentProportion = paymentProportion;
		this.closedProportion = closedProportion;
		this.paymentTime = paymentTime;
		this.invoice = invoice;
		this.registrant = registrant;
		this.paymentDesc = paymentDesc;
		this.logicDelete = logicDelete;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public ContractPaymentTerms() {
		super();
	}
	@Override
	public String toString() {
		return "ContractPaymentTerms [id=" + id + ", code=" + code +", contractId="+contractId+ ", name=" + name + ", paymentAmount="
				+ paymentAmount + ", paymentProportion=" + paymentProportion + ", paymentTime=" + paymentTime
				+ ", invoice=" + invoice + ", paymentDesc=" + paymentDesc + ", extend1=" + extend1 +", logicDelete="+logicDelete + ", extend2="
				+ extend2 + ", extend3=" + extend3 + "]";
	}
	
}
