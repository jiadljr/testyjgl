package com.qkby.sysmanage.entity;

import java.util.Date;

/**
 * 值班备注表的实体类
 * @author Administrator
 *
 */
public class SysDutyRemarkInfo {
	
	private int id;
	private Date dutyTime;
	private String dutyRemark;
	private String extend1;
	private String extend2;
	private String extend3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDutyTime() {
		return dutyTime;
	}
	public void setDutyTime(Date dutyTime) {
		this.dutyTime = dutyTime;
	}
	public String getDutyRemark() {
		return dutyRemark;
	}
	public void setDutyRemark(String dutyRemark) {
		this.dutyRemark = dutyRemark;
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
	public SysDutyRemarkInfo(int id, Date dutyTime, String dutyRemark, String extend1, String extend2, String extend3) {
		super();
		this.id = id;
		this.dutyTime = dutyTime;
		this.dutyRemark = dutyRemark;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public SysDutyRemarkInfo() {
		super();
	}
	@Override
	public String toString() {
		return "SysDutyRemarkInfo [id=" + id + ", dutyTime=" + dutyTime + ", dutyRemark=" + dutyRemark + ", extend1="
				+ extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
	
}
