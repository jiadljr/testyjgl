package com.qkby.event.entity;

import java.util.Date;

public class EventLogInfo {
    private Integer id;

    private Integer eventId;

    private String eventCode;

    private String eventName;

    private Integer idOperUser;

    private Integer idOperType;

    private Date dateOper;

    private Integer eventStatus;

    private Integer fromId;

    private Integer toId;

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

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public Integer getIdOperUser() {
        return idOperUser;
    }

    public void setIdOperUser(Integer idOperUser) {
        this.idOperUser = idOperUser;
    }

    public Integer getIdOperType() {
        return idOperType;
    }

    public void setIdOperType(Integer idOperType) {
        this.idOperType = idOperType;
    }

    public Date getDateOper() {
        return dateOper;
    }

    public void setDateOper(Date dateOper) {
        this.dateOper = dateOper;
    }

    public Integer getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(Integer eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
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
		return "EventLogInfo [id=" + id + ", eventId=" + eventId + ", eventCode=" + eventCode + ", eventName="
				+ eventName + ", idOperUser=" + idOperUser + ", idOperType=" + idOperType + ", dateOper=" + dateOper
				+ ", eventStatus=" + eventStatus + ", fromId=" + fromId + ", toId=" + toId + ", remark=" + remark
				+ ", extend1=" + extend1 + ", extend2=" + extend2 + ", extend3=" + extend3 + "]";
	}
    
}