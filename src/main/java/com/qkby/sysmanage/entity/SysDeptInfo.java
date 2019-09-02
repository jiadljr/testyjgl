package com.qkby.sysmanage.entity;

public class SysDeptInfo {
	private Integer id;

	private String code;

	private String name;

	private Integer level;

	private String tel;

	private Integer parentId;

	private String extend1;

	private String extend2;

	private String extend3;

	private String remark;

	private int ds;

	public int getDs() {
		return ds;
	}

	public void setDs(int ds) {
		this.ds = ds;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1 == null ? null : extend1.trim();
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2 == null ? null : extend2.trim();
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3 == null ? null : extend3.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	@Override
	public String toString() {
		return "SysDeptInfo [id=" + id + ", code=" + code + ", name=" + name + ", level=" + level + ", tel=" + tel
				+ ", parentId=" + parentId + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3=" + extend3
				+ ", remark=" + remark + ", ds=" + ds + "]";
	}

}