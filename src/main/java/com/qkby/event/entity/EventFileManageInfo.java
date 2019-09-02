package com.qkby.event.entity;

public class EventFileManageInfo {
    private Integer id;

    private Integer idDeal;

    private Integer idFile;

    private String extend1;

    private String extend2;

    private String extend3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdDeal() {
        return idDeal;
    }

    public void setIdDeal(Integer idDeal) {
        this.idDeal = idDeal;
    }

    public Integer getIdFile() {
        return idFile;
    }

    public void setIdFile(Integer idFile) {
        this.idFile = idFile;
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
		return "EventFileManageInfo [id=" + id + ", idDeal=" + idDeal + ", idFile=" + idFile + ", extend1=" + extend1
				+ ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
    
}