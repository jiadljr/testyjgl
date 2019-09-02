package com.qkby.work.entity;

public class WorkPlanFileInfo {
	
	private int id;
	private int planId;
	private int fileId;
	private String extend1;
	private String extend2;
	private String extend3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
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
	public WorkPlanFileInfo(int id, int planId, int fileId, String extend1, String extend2, String extend3) {
		super();
		this.id = id;
		this.planId = planId;
		this.fileId = fileId;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public WorkPlanFileInfo() {
		super();
	}
	@Override
	public String toString() {
		return "WorkPlanFileInfo [id=" + id + ", planId=" + planId + ", fileId=" + fileId + ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
	
}
