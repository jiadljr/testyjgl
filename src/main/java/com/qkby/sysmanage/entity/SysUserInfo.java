package com.qkby.sysmanage.entity;

import java.util.Date;

public class SysUserInfo {
    private Integer id;

    private String code;

    private String name;

    private String password;

    private String uuid;

    private Integer sex;

    private String wechat;

    private String tel;

    private String cal;

    private String mail;

    private String address;

    private Integer idCmpy;

    private Integer idDept;

    private Date createDate;

    private Integer createUser;

    private Date updateDate;

    private Integer updateUser;

    private Date pwdExpiredDate;

    private Integer ds;
    
    private Integer arrangeProxy;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal == null ? null : cal.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getIdCmpy() {
        return idCmpy;
    }

    public void setIdCmpy(Integer idCmpy) {
        this.idCmpy = idCmpy;
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

    public Date getPwdExpiredDate() {
        return pwdExpiredDate;
    }

    public void setPwdExpiredDate(Date pwdExpiredDate) {
        this.pwdExpiredDate = pwdExpiredDate;
    }

    public Integer getDs() {
        return ds;
    }

    public void setDs(Integer ds) {
        this.ds = ds;
    }
    
	public Integer getArrangeProxy() {
		return arrangeProxy;
	}

	public void setArrangeProxy(Integer arrangeProxy) {
		this.arrangeProxy = arrangeProxy;
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

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "SysUserInfo [id=" + id + ", code=" + code + ", name=" + name + ", password=" + password + ", uuid="
				+ uuid + ", sex=" + sex + ", wechat=" + wechat + ", tel=" + tel + ", cal=" + cal + ", mail=" + mail
				+ ", address=" + address + ", idCmpy=" + idCmpy + ", idDept=" + idDept + ", createDate=" + createDate
				+ ", createUser=" + createUser + ", updateDate=" + updateDate + ", updateUser=" + updateUser
				+ ", pwdExpiredDate=" + pwdExpiredDate + ", ds=" + ds + ", arrangeProxy=" + arrangeProxy + ", extend1="
				+ extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}

}