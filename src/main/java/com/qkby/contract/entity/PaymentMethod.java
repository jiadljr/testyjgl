package com.qkby.contract.entity;

public class PaymentMethod {
	
	private String id;
	private String code;
	private String name;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public PaymentMethod(String id, String code, String name, String extend1, String extend2, String extend3) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public PaymentMethod() {
		super();
	}
	@Override
	public String toString() {
		return "PaymentMethod [id=" + id + ", code=" + code + ", name=" + name + ", extend1=" + extend1 + ", extend2="
				+ extend2 + ", extend3=" + extend3 + "]";
	}
	

}
