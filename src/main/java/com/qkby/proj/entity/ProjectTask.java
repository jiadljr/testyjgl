package com.qkby.proj.entity;

import java.util.Date;

public class ProjectTask {
	private Integer id;
	private String codeTask;
	private String nameTask;
	private String projCode;
	private Integer level;
	private Integer parentId;
	private Date dateStart;
	private Date dateEnd;
	private Date dateReal;
	private Date dateCreate;// 任务添加时间
	private Date dateUpdate;// 任务修改时间
	private Integer idTaskHead;
	private Integer pf;// 是否超期（0：正常 1：已超期）
	private Integer taskStatus;
	private String phaseShow;
	private Integer milestoneTask;
	private Float taskSpeed;
	private String taskDesc;
	private Integer ds;//是否删除（0：正常 1：删除）
	private String vs;//时间戳
	private String taskPath;//任务文件夹路径
	private String extend1;
	private String extend2;
	private String extend3;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeTask() {
		return codeTask;
	}

	public void setCodeTask(String codeTask) {
		this.codeTask = codeTask;
	}

	
	public Float getTaskSpeed() {
		return taskSpeed;
	}

	public void setTaskSpeed(Float taskSpeed) {
		this.taskSpeed = taskSpeed;
	}

	public String getNameTask() {
		return nameTask;
	}

	public void setNameTask(String nameTask) {
		this.nameTask = nameTask;
	}

	public String getProjCode() {
		return projCode;
	}

	public void setProjCode(String projCode) {
		this.projCode = projCode;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	
	public Date getDateReal() {
		return dateReal;
	}

	public void setDateReal(Date dateReal) {
		this.dateReal = dateReal;
	}

	
	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public Integer getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}

	public Integer getMilestoneTask() {
		return milestoneTask;
	}

	public void setMilestoneTask(Integer milestoneTask) {
		this.milestoneTask = milestoneTask;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	public String getPhaseShow() {
		return phaseShow;
	}

	public void setPhaseShow(String phaseShow) {
		this.phaseShow = phaseShow;
	}

	public Integer getDs() {
		return ds;
	}

	public void setDs(Integer ds) {
		this.ds = ds;
	}

	public String getVs() {
		return vs;
	}

	public void setVs(String vs) {
		this.vs = vs;
	}

	public String getTaskPath() {
		return taskPath;
	}

	public void setTaskPath(String taskPath) {
		this.taskPath = taskPath;
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

	public Integer getIdTaskHead() {
		return idTaskHead;
	}

	public void setIdTaskHead(Integer idTaskHead) {
		this.idTaskHead = idTaskHead;
	}
	
}
