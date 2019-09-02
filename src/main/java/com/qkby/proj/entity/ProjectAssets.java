package com.qkby.proj.entity;

public class ProjectAssets {
	private Integer id;
	private String projCode;//项目编号
	private Integer idAsset;//资产ID
	private String extend1;
	private String extend2;
	private String extend3;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjCode() {
		return projCode;
	}
	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	public Integer getIdAsset() {
		return idAsset;
	}
	public void setIdAsset(Integer idAsset) {
		this.idAsset = idAsset;
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
	
}
