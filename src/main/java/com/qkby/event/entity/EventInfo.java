package com.qkby.event.entity;

import java.util.Date;

public class EventInfo {
    private Integer id;

    private String eventCode;

    private String eventName;

    private Date dateCreate;

    private Integer idUserCreate;
    
    private String eventContact;
    
    private Integer idDept;

    private Integer idUserAplly;

    private String eventDesc;
    
    private Integer eventWay;

    private Integer idUserAccept;

    private Date dateAccept;

    private Integer eventType;

    private Integer eventService;

    private Integer eventLevel;

    private Integer eventSource;
    
    private Integer eventPriority;

    private String acceptDesc;
    
    private Integer idUserDeal;

    private Integer idUserCheckCenter;

    private Date dateCheckCenter;

    private String centerDesc;

    private Integer idUserCheckOffice;

    private Date dateCheckOffice;

    private String officeDesc;
    
    private Integer eventAssess;
    
    private Date dateAssess;
    
    private String assessDesc;

    private String eventStatus;
    
    private String eventTs;

    private String extend1;

    private String extend2;

    private String extend3;

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventContact() {
        return eventContact;
    }

    public void setEventContact(String eventContact) {
        this.eventContact = eventContact == null ? null : eventContact.trim();
    }
    
    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode == null ? null : eventCode.trim();
    }
    
    public Integer getEventWay() {
        return eventWay;
    }

    public void setEventWay(Integer eventWay) {
    	 this.eventWay = eventWay;
    }

    public String getEventTs() {
        return eventTs;
    }

    public void setEventTs(String eventTs) {
        this.eventTs = eventTs == null ? null : eventTs.trim();
    }
    
    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName == null ? null : eventName.trim();
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getIdUserCreate() {
        return idUserCreate;
    }

    public void setIdUserCreate(Integer idUserCreate) {
        this.idUserCreate = idUserCreate;
    }
    
    public Integer getIdDept() {
        return idDept;
    }

    public void setIdDept(Integer idDept) {
        this.idDept = idDept;
    }

    public Integer getIdUserAplly() {
        return idUserAplly;
    }

    public void setIdUserAplly(Integer idUserAplly) {
        this.idUserAplly = idUserAplly;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public Integer getIdUserAccept() {
        return idUserAccept;
    }

    public void setIdUserAccept(Integer idUserAccept) {
        this.idUserAccept = idUserAccept;
    }

    public Date getDateAccept() {
        return dateAccept;
    }

    public void setDateAccept(Date dateAccept) {
        this.dateAccept = dateAccept;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getEventService() {
        return eventService;
    }

    public void setEventService(Integer eventService) {
        this.eventService = eventService;
    }

    public Integer getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(Integer eventLevel) {
        this.eventLevel = eventLevel;
    }
    
    public Integer getEventSource() {
		return eventSource;
	}

	public void setEventSource(Integer eventSource) {
		this.eventSource = eventSource;
	}

	public Integer getEventPriority() {
        return eventPriority;
    }

    public void setEventPriority(Integer eventPriority) {
        this.eventPriority = eventPriority;
    }

    public String getAcceptDesc() {
        return acceptDesc;
    }

    public void setAcceptDesc(String acceptDesc) {
        this.acceptDesc = acceptDesc == null ? null : acceptDesc.trim();
    }

    public Integer getIdUserCheckCenter() {
        return idUserCheckCenter;
    }

    public void setIdUserCheckCenter(Integer idUserCheckCenter) {
        this.idUserCheckCenter = idUserCheckCenter;
    }

    public Date getDateCheckCenter() {
        return dateCheckCenter;
    }

    public void setDateCheckCenter(Date dateCheckCenter) {
        this.dateCheckCenter = dateCheckCenter;
    }

    public String getCenterDesc() {
        return centerDesc;
    }

    public void setCenterDesc(String centerDesc) {
        this.centerDesc = centerDesc == null ? null : centerDesc.trim();
    }

    public Integer getIdUserCheckOffice() {
        return idUserCheckOffice;
    }

    public void setIdUserCheckOffice(Integer idUserCheckOffice) {
        this.idUserCheckOffice = idUserCheckOffice;
    }

    public Date getDateCheckOffice() {
        return dateCheckOffice;
    }

    public void setDateCheckOffice(Date dateCheckOffice) {
        this.dateCheckOffice = dateCheckOffice;
    }

    public String getOfficeDesc() {
        return officeDesc;
    }

    public void setOfficeDesc(String officeDesc) {
        this.officeDesc = officeDesc == null ? null : officeDesc.trim();
    }

	public Integer getEventAssess() {
		return eventAssess;
	}

	public void setEventAssess(Integer eventAssess) {
		this.eventAssess = eventAssess;
	}
	
	public Date getDateAssess() {
		return dateAssess;
	}

	public void setDateAssess(Date dateAssess) {
		this.dateAssess = dateAssess;
	}

	public String getAssessDesc() {
		return assessDesc;
	}

	public void setAssessDesc(String assessDesc) {
		this.assessDesc = assessDesc == null ? null : assessDesc.trim();
	}

	public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus == null ? null : eventStatus.trim();
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

	public Integer getIdUserDeal() {
		return idUserDeal;
	}

	public void setIdUserDeal(Integer idUserDeal) {
		this.idUserDeal = idUserDeal;
	}

	@Override
	public String toString() {
		return "EventInfo [id=" + id + ", eventCode=" + eventCode + ", eventName=" + eventName + ", dateCreate="
				+ dateCreate + ", idUserCreate=" + idUserCreate + ", eventContact=" + eventContact + ", idDept="
				+ idDept + ", idUserAplly=" + idUserAplly + ", eventDesc=" + eventDesc + ", eventWay=" + eventWay
				+ ", idUserAccept=" + idUserAccept + ", dateAccept=" + dateAccept + ", eventType=" + eventType
				+ ", eventService=" + eventService + ", eventLevel=" + eventLevel + ", eventSource=" + eventSource
				+ ", eventPriority=" + eventPriority + ", acceptDesc=" + acceptDesc + ", idUserDeal=" + idUserDeal
				+ ", idUserCheckCenter=" + idUserCheckCenter + ", dateCheckCenter=" + dateCheckCenter + ", centerDesc="
				+ centerDesc + ", idUserCheckOffice=" + idUserCheckOffice + ", dateCheckOffice=" + dateCheckOffice
				+ ", officeDesc=" + officeDesc + ", eventAssess=" + eventAssess + ", dateAssess=" + dateAssess
				+ ", assessDesc=" + assessDesc + ", eventStatus=" + eventStatus + ", extend1=" + extend1 + ", extend2="
				+ extend2 + ", extend3=" + extend3 + "]";
	}

    
}