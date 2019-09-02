package com.qkby.proj.entity;

public class TaskTemplate {
    private Integer id;

    private Integer templateId;

    private String taskName;

    private String phaseRemark;

    private String phaseShow;

    private int number;

    private String extend1;

    private String extend2;

    private String extend3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName == null ? null : taskName.trim();
    }


    public String getPhaseRemark() {
        return phaseRemark;
    }

    public void setPhaseRemark(String phaseRemark) {
        this.phaseRemark = phaseRemark == null ? null : phaseRemark.trim();
    }

    public String getPhaseShow() {
        return phaseShow;
    }

    public void setPhaseShow(String phaseShow) {
        this.phaseShow = phaseShow == null ? null : phaseShow.trim();
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

    
    
    public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }
}