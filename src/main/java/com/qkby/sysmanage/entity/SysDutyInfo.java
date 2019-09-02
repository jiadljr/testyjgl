package com.qkby.sysmanage.entity;

import java.util.Date;

public class SysDutyInfo {
    private Integer id;

    private Date dutyDate;

    private Integer dutyOrder;

    private Integer idUser;

    private String remark;
    
    private String extend1;

    private String extend2;

    private String extend3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public Integer getDutyOrder() {
        return dutyOrder;
    }

    public void setDutyOrder(Integer dutyOrder) {
        this.dutyOrder = dutyOrder;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
		return "SysDutyInfo [id=" + id + ", dutyDate=" + dutyDate + ", dutyOrder=" + dutyOrder + ", idUser=" + idUser
				+ ", remark=" + remark + ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
    
}