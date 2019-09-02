package com.qkby.proj.entity;

import java.util.Date;

public class ProjectType {
    private Integer id;
    private String code;
    private String name;
    private String color;
    private int idCreateUser;
    private int idUpdateUser;
    private Date dateCreate;
    private Date dateUpdate;
    private String remark;
    private int ds;
    private String extend1;
    private String extend2;
    private String extend3;
    

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color == null ? null : color.trim();
	}

	public int getIdCreateUser() {
		return idCreateUser;
	}

	public void setIdCreateUser(int idCreateUser) {
		this.idCreateUser = idCreateUser;
	}

	public int getIdUpdateUser() {
		return idUpdateUser;
	}

	public int getDs() {
		return ds;
	}

	public void setDs(int ds) {
		this.ds = ds;
	}

	public void setIdUpdateUser(int idUpdateUser) {
		this.idUpdateUser = idUpdateUser;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null?null:remark.trim();
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1  == null?null:extend1.trim();
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2 == null?null:extend2.trim();
	}

	public String getExtend3() {
		return extend3;
	}

	public void setExtend3(String extend3) {
		this.extend3 = extend3 == null?null:extend3.trim();
	}
    
    
}