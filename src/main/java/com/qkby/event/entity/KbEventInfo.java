package com.qkby.event.entity;

public class KbEventInfo {
    private Integer id;
    
    private String kbCode;

    private String eventCode;

    private String eventName;

    private Integer assetType;

    private Integer eventType;

    private Integer eventLevel;

    private String eventCauses;

    private String eventDesc;

    private String eventResolvent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getKbCode() {
        return kbCode;
    }

    public void setKbCode(String kbCode) {
        this.kbCode = kbCode == null ? null : kbCode.trim();
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

    public Integer getAssetType() {
        return assetType;
    }

    public void setAssetType(Integer assetType) {
        this.assetType = assetType;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(Integer eventLevel) {
        this.eventLevel = eventLevel;
    }

    public String getEventCauses() {
        return eventCauses;
    }

    public void setEventCauses(String eventCauses) {
        this.eventCauses = eventCauses == null ? null : eventCauses.trim();
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc == null ? null : eventDesc.trim();
    }

    public String getEventResolvent() {
        return eventResolvent;
    }

    public void setEventResolvent(String eventResolvent) {
        this.eventResolvent = eventResolvent == null ? null : eventResolvent.trim();
    }
}