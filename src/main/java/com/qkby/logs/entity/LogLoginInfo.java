package com.qkby.logs.entity;

import java.util.Date;

public class LogLoginInfo {
    private Integer id;

    private Integer idUser;

    private String nameUser;

    private Date dateLogin;

    private String ipLogin;
    
    private String fgLogOut;

    private Date dateLoginOut;

    private String macLogin;

    private String extend1;

    private String extend2;

    private String extend3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser == null ? null : nameUser.trim();
    }

    public Date getDateLoginOut() {
        return dateLoginOut;
    }

    public void setDateLoginOut(Date dateLoginOut) {
        this.dateLoginOut = dateLoginOut;
    }
    
    public Date getDateLogin() {
        return dateLogin;
    }

    public void setDateLogin(Date dateLogin) {
        this.dateLogin = dateLogin;
    }

    public String getFgLogOut() {
        return fgLogOut;
    }

    public void setFgLogOut(String fgLogOut) {
        this.fgLogOut = fgLogOut == null ? null : fgLogOut.trim();
    }
    
    public String getIpLogin() {
        return ipLogin;
    }

    public void setIpLogin(String ipLogin) {
        this.ipLogin = ipLogin == null ? null : ipLogin.trim();
    }

    public String getMacLogin() {
        return macLogin;
    }

    public void setMacLogin(String macLogin) {
        this.macLogin = macLogin == null ? null : macLogin.trim();
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
}