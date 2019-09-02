package com.qkby.contract.entity;
/**
 * 
 * @ClassName ContractType
 * @Description TODO(合同类型表实体类)
 * @author Administrator
 * @Date 2018年5月3日 上午11:42:47
 * @version 1.0.0
 */
public class ContractType {
	
	private String id;
	private String code;
	private String typeName;
	private String typeExplain;
	private String color;
	private Integer logicallyDelete;
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
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeExplain() {
		return typeExplain;
	}
	public void setTypeExplain(String typeExplain) {
		this.typeExplain = typeExplain;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getLogicallyDelete(){
		return logicallyDelete;
	}
	public void setLogicallyDelete(Integer logicallyDelete){
		this.logicallyDelete = logicallyDelete;
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
	public ContractType(String id, String code, String typeName, String typeExplain, String color, Integer logicallyDelete, String extend1,
			String extend2, String extend3) {
		super();
		this.id = id;
		this.code = code;
		this.typeName = typeName;
		this.typeExplain = typeExplain;
		this.color = color;
		this.logicallyDelete = logicallyDelete;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public ContractType() {
		super();
	}
	@Override
	public String toString() {
		return "ContractType [id=" + id + ", code=" + code + ", typeName=" + typeName + ", typeExplain=" + typeExplain
				+ ", color=" + color + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}

}
