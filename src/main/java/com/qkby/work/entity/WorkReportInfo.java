package com.qkby.work.entity;

import java.util.Date;

public class WorkReportInfo {
	
	private int id;
	private String reportName;
	private int reportType;
	private int reportState;
	private Date subTime;
	private String reportContent;
	private int reportUser;
    private String reportStaff;
    private String reportStaffName;
    private int ds;
    private String extend1;
    private String extend2;
    private String extend3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReportName() {
		return reportName;
	}
	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	public int getReportType() {
		return reportType;
	}
	public void setReportType(int reportType) {
		this.reportType = reportType;
	}
	public int getReportState() {
		return reportState;
	}
	public void setReportState(int reportState) {
		this.reportState = reportState;
	}
	public Date getSubTime() {
		return subTime;
	}
	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public int getReportUser() {
		return reportUser;
	}
	public void setReportUser(int reportUser) {
		this.reportUser = reportUser;
	}
	public String getReportStaff() {
		return reportStaff;
	}
	public void setReportStaff(String reportStaff) {
		this.reportStaff = reportStaff;
	}
	public String getReportStaffName() {
		return reportStaffName;
	}
	public void setReportStaffName(String reportStaffName) {
		this.reportStaffName = reportStaffName;
	}
	public int getDs() {
		return ds;
	}
	public void setDs(int ds) {
		this.ds = ds;
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
	public WorkReportInfo(int id, String reportName, int reportType, int reportState, Date subTime,
			String reportContent, int reportUser, String reportStaff, String reportStaffName, int ds, String extend1,
			String extend2, String extend3) {
		super();
		this.id = id;
		this.reportName = reportName;
		this.reportType = reportType;
		this.reportState = reportState;
		this.subTime = subTime;
		this.reportContent = reportContent;
		this.reportUser = reportUser;
		this.reportStaff = reportStaff;
		this.reportStaffName = reportStaffName;
		this.ds = ds;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public WorkReportInfo() {
		super();
	}
	@Override
	public String toString() {
		return "WorkReportInfo [id=" + id + ", reportName=" + reportName + ", reportType=" + reportType
				+ ", reportState=" + reportState + ", subTime=" + subTime + ", reportContent=" + reportContent
				+ ", reportUser=" + reportUser + ", reportStaff=" + reportStaff + ", reportStaffName=" + reportStaffName
				+ ", ds=" + ds + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
	
}
