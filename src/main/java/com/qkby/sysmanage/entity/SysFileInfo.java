package com.qkby.sysmanage.entity;

import java.util.Date;

public class SysFileInfo {
    private Integer id;

    private String code;

    private String name;
    
    private String nameDepost;

    private Integer idFileType;

    private String path;

    private Date createDate;

    private Integer createUser;

    private String remark;

    private Integer ds;

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

    public String getNameDepost() {
		return nameDepost;
	}

	public void setNameDepost(String nameDepost) {
		this.nameDepost = nameDepost;
	}

	public Integer getIdFileType() {
        return idFileType;
    }

    public void setIdFileType(Integer idFileType) {
        this.idFileType = idFileType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getDs() {
        return ds;
    }

    public void setDs(Integer ds) {
        this.ds = ds;
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

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

	@Override
	public String toString() {
		return "SysFileInfo [id=" + id + ", code=" + code + ", name=" + name + ",nameDepost" + nameDepost + ", idFileType=" + idFileType + ", path="
				+ path + ", createDate=" + createDate + ", createUser=" + createUser + ", remark=" + remark + ", ds="
				+ ds + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
    
}