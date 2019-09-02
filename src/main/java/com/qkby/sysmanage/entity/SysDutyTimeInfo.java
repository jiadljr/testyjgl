package com.qkby.sysmanage.entity;

import java.util.Date;

/**
 * 值班人员表的实体类
 * @author Administrator
 *
 */
public class SysDutyTimeInfo {
	
	private int id;
	private int idUser;
	private Date dutyTime;
	private String extend1;
	private String extend2;
	private String extend3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public Date getDutyTime() {
		return dutyTime;
	}
	public void setDutyTime(Date dutyTime) {
		this.dutyTime = dutyTime;
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
	public SysDutyTimeInfo(int id, int idUser, Date dutyTime, String extend1, String extend2, String extend3) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.dutyTime = dutyTime;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public SysDutyTimeInfo() {
		super();
	}
	@Override
	public String toString() {
		return "SysDutyTimeInfo [id=" + id + ", idUser=" + idUser + ", dutyTime=" + dutyTime + ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}

}
