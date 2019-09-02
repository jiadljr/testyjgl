package com.qkby.sysmanage.entity;

import java.util.Date;

public class SysAssetsInfo {
    private Integer id;

    private String code;

    private String name;

    private Integer idType;

    private Integer idGrade;

    private String asModel;

    private String asIp;

    private String remark;

    private String asManuf;

    private String asAddr;

    private Integer idUser;

    private Integer idDept;

    private Date createDate;

    private Integer createUser;

    private Date updateDate;

    private Integer updateUser;

    private Integer status;
    
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public Integer getIdGrade() {
        return idGrade;
    }

    public void setIdGrade(Integer idGrade) {
        this.idGrade = idGrade;
    }

    public String getAsModel() {
        return asModel;
    }

    public void setAsModel(String asModel) {
        this.asModel = asModel == null ? null : asModel.trim();
    }

    public String getAsIp() {
        return asIp;
    }

    public void setAsIp(String asIp) {
        this.asIp = asIp == null ? null : asIp.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getAsManuf() {
        return asManuf;
    }

    public void setAsManuf(String asManuf) {
        this.asManuf = asManuf == null ? null : asManuf.trim();
    }

    public String getAsAddr() {
        return asAddr;
    }

    public void setAsAddr(String asAddr) {
        this.asAddr = asAddr == null ? null : asAddr.trim();
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdDept() {
        return idDept;
    }

    public void setIdDept(Integer idDept) {
        this.idDept = idDept;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser) {
        this.updateUser = updateUser;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
	public Integer getDs() {
		return ds;
	}

	public void setDs(Integer ds) {
		this.ds = ds;
	}

	@Override
	public String toString() {
		return "SysAssetsInfo [id=" + id + ", code=" + code + ", name=" + name + ", idType=" + idType + ", idGrade="
				+ idGrade + ", asModel=" + asModel + ", asIp=" + asIp + ", remark=" + remark + ", asManuf=" + asManuf
				+ ", asAddr=" + asAddr + ", idUser=" + idUser + ", idDept=" + idDept + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser
				+ ", status=" + status + ", ds=" + ds + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3="
				+ extend3 + "]";
	}
        
}