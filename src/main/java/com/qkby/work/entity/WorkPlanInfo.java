package com.qkby.work.entity;

import java.util.Date;

public class WorkPlanInfo {
	
	private int id;	//主键ID
	private String planHeadline; //计划标题
	private int planType;  //计划类型
	private int planState;  //计划状态
	private Date foundTime;  //创建时间
	private Date recentUpdate;  //最近更新
	private int planner;  //计划人
	private Date planTime;  //计划时间
	private Date planBeginTime;  //计划开始时间
	private Date planFinishTime;  //计划结束时间
	private int workType;  //类型
	private int remind;  //提醒
	private String remindTime;  //提醒时间
	private String content;  //内容
	private String pushStaff;  //推送人员
	private String pushStaffName;  //推送人员名字
	private int principal;  //负责人
	private String participant;  //参与人
	private String participantName;
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
	public String getPlanHeadline() {
		return planHeadline;
	}
	public void setPlanHeadline(String planHeadline) {
		this.planHeadline = planHeadline;
	}
	public int getPlanType() {
		return planType;
	}
	public void setPlanType(int planType) {
		this.planType = planType;
	}
	public int getPlanState() {
		return planState;
	}
	public void setPlanState(int planState) {
		this.planState = planState;
	}
	public Date getFoundTime() {
		return foundTime;
	}
	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}
	public Date getRecentUpdate() {
		return recentUpdate;
	}
	public void setRecentUpdate(Date recentUpdate) {
		this.recentUpdate = recentUpdate;
	}
	public int getPlanner() {
		return planner;
	}
	public void setPlanner(int planner) {
		this.planner = planner;
	}
	public Date getPlanTime() {
		return planTime;
	}
	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}
	public Date getPlanBeginTime() {
		return planBeginTime;
	}
	public void setPlanBeginTime(Date planBeginTime) {
		this.planBeginTime = planBeginTime;
	}
	public Date getPlanFinishTime() {
		return planFinishTime;
	}
	public void setPlanFinishTime(Date planFinishTime) {
		this.planFinishTime = planFinishTime;
	}
	public int getWorkType() {
		return workType;
	}
	public void setWorkType(int workType) {
		this.workType = workType;
	}
	public int getRemind() {
		return remind;
	}
	public void setRemind(int remind) {
		this.remind = remind;
	}
	public String getRemindTime() {
		return remindTime;
	}
	public void setRemindTime(String remindTime) {
		this.remindTime = remindTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPushStaff() {
		return pushStaff;
	}
	public void setPushStaff(String pushStaff) {
		this.pushStaff = pushStaff;
	}
	public String getPushStaffName() {
		return pushStaffName;
	}
	public void setPushStaffName(String pushStaffName) {
		this.pushStaffName = pushStaffName;
	}
	public int getPrincipal() {
		return principal;
	}
	public void setPrincipal(int principal) {
		this.principal = principal;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public String getParticipantName() {
		return participantName;
	}
	public void setParticipantName(String participantName) {
		this.participantName = participantName;
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
	public WorkPlanInfo(int id, String planHeadline, int planType, int planState, Date foundTime, Date recentUpdate,
			int planner, Date planTime, Date planBeginTime, Date planFinishTime, int workType, int remind,
			String remindTime, String content, String pushStaff, String pushStaffName, int principal,
			String participant, String participantName, int ds, String extend1, String extend2, String extend3) {
		super();
		this.id = id;
		this.planHeadline = planHeadline;
		this.planType = planType;
		this.planState = planState;
		this.foundTime = foundTime;
		this.recentUpdate = recentUpdate;
		this.planner = planner;
		this.planTime = planTime;
		this.planBeginTime = planBeginTime;
		this.planFinishTime = planFinishTime;
		this.workType = workType;
		this.remind = remind;
		this.remindTime = remindTime;
		this.content = content;
		this.pushStaff = pushStaff;
		this.pushStaffName = pushStaffName;
		this.principal = principal;
		this.participant = participant;
		this.participantName = participantName;
		this.ds = ds;
		this.extend1 = extend1;
		this.extend2 = extend2;
		this.extend3 = extend3;
	}
	public WorkPlanInfo() {
		super();
	}
	@Override
	public String toString() {
		return "WorkPlanInfo [id=" + id + ", planHeadline=" + planHeadline + ", planType=" + planType + ", planState="
				+ planState + ", foundTime=" + foundTime + ", recentUpdate=" + recentUpdate + ", planner=" + planner
				+ ", planTime=" + planTime + ", planBeginTime=" + planBeginTime + ", planFinishTime=" + planFinishTime
				+ ", workType=" + workType + ", remind=" + remind + ", remindTime=" + remindTime + ", content="
				+ content + ", pushStaff=" + pushStaff + ", pushStaffName=" + pushStaffName + ", principal=" + principal
				+ ", participant=" + participant + ", participantName=" + participantName + ", ds=" + ds + ", extend1="
				+ extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
	
}
