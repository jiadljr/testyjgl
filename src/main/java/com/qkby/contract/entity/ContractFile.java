package com.qkby.contract.entity;

public class ContractFile {
	
	private String id;
	private String contractId;
	private Integer fileId;
	private String extend1;
	private String extend2;
	private String extend3;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
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
	public ContractFile(String id, String contractId, Integer fileId, String extend1, String extend2, String extend3) {
		super();
		this.id = id;
		this.contractId = contractId;
		this.fileId = fileId;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public ContractFile() {
		super();
	}
	@Override
	public String toString() {
		return "ContractFile [id=" + id + ", contractId=" + contractId + ", fileId=" + fileId + ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
	

}
