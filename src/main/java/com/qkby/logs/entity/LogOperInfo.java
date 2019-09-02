package com.qkby.logs.entity;

import java.util.Date;

public class LogOperInfo {
    private Integer id;

    private Integer idUser;

    private String nameUser;

    private Date dateOper;

    private String tableOper;

    private Integer idMenuOper;

    private String nameMenuOper;

    private Integer pkValue;

    private Integer typeOper;

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

    public Date getDateOper() {
        return dateOper;
    }

    public void setDateOper(Date dateOper) {
        this.dateOper = dateOper;
    }

    public String getTableOper() {
        return tableOper;
    }

    public void setTableOper(String tableOper) {
        this.tableOper = tableOper == null ? null : tableOper.trim();
    }

    public Integer getIdMenuOper() {
        return idMenuOper;
    }

    public void setIdMenuOper(Integer idMenuOper) {
        this.idMenuOper = idMenuOper;
    }

    public String getNameMenuOper() {
        return nameMenuOper;
    }

    public void setNameMenuOper(String nameMenuOper) {
        this.nameMenuOper = nameMenuOper == null ? null : nameMenuOper.trim();
    }

    public Integer getPkValue() {
        return pkValue;
    }

    public void setPkValue(Integer pkValue) {
        this.pkValue = pkValue;
    }

    public Integer getTypeOper() {
        return typeOper;
    }

    public void setTypeOper(Integer typeOper) {
        this.typeOper = typeOper;
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