package com.qkby.proj.entity;

import java.util.Date;

public class TaskRecord {
    private Integer id;

    private Integer idTask;

    private Date createTime;

    private int idCreateUser;
    
    private String taskRecord;
    private String extend1;
	private String extend2;
	private String extend3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
    public int getIdCreateUser() {
		return idCreateUser;
	}

	public void setIdCreateUser(int idCreateUser) {
		this.idCreateUser = idCreateUser;
	}

	public String getTaskRecord() {
        return taskRecord;
    }

    public void setTaskRecord(String taskRecord) {
        this.taskRecord = taskRecord == null ? null : taskRecord.trim();
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