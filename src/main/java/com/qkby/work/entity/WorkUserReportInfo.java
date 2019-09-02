package com.qkby.work.entity;

public class WorkUserReportInfo {
	
	private int id;
	private int userId;
	private int reportId;
	private int ds;
	private int examine;
	private String extend1;
	private String extend2;
	private String extend3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public int getDs() {
		return ds;
	}
	public void setDs(int ds) {
		this.ds = ds;
	}
	public int getExamine() {
		return examine;
	}
	public void setExamine(int examine) {
		this.examine = examine;
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
	public WorkUserReportInfo(int id, int userId, int reportId, int ds, int examine, String extend1, String extend2,
			String extend3) {
		super();
		this.id = id;
		this.userId = userId;
		this.reportId = reportId;
		this.ds = ds;
		this.examine = examine;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public WorkUserReportInfo() {
		super();
	}
	@Override
	public String toString() {
		return "WorkUserReportInfo [id=" + id + ", userId=" + userId + ", reportId=" + reportId + ", ds=" + ds
				+ ", examine=" + examine + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3=" + extend3
				+ "]";
	}
	
}
