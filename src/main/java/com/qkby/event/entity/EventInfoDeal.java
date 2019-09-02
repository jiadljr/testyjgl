package com.qkby.event.entity;

import java.util.Date;

public class EventInfoDeal {
    private Integer id;

    private Integer idEvent;

    private Integer idPost;

    private Integer idUserDeal;
    
    private Integer idRedeRein;
    
    private String assetNumber;
    
    private Date dateRespon;
    
    private Date dateDeal;
    
    private Integer dealStatus;
    
    private String eventCause;

    private String dealDesc;

    private String extend1;

    private String extend2;

    private String extend3;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public Integer getIdUserDeal() {
        return idUserDeal;
    }

    public void setIdUserDeal(Integer idUserDeal) {
        this.idUserDeal = idUserDeal;
    }
    
    public Integer getIdRedeRein() {
        return idRedeRein;
    }

    public void setIdRedeRein(Integer idRedeRein) {
        this.idRedeRein = idRedeRein;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }
    
    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber == null ? null : assetNumber.trim();
    }

    public String getEventCause() {
		return eventCause;
	}

	public void setEventCause(String eventCause) {
		this.eventCause = eventCause;
	}

	public String getDealDesc() {
        return dealDesc;
    }

    public void setDealDesc(String dealDesc) {
        this.dealDesc = dealDesc == null ? null : dealDesc.trim();
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
    
    public Date getDateRespon() {
        return dateRespon;
    }

    public void setDateRespon(Date dateRespon) {
        this.dateRespon = dateRespon;
    }
    
    public Date getDateDeal() {
        return dateDeal;
    }

    public void setDateDeal(Date dateDeal) {
        this.dateDeal = dateDeal;
    }

    public String getExtend3() {
        return extend3;
    }

    public void setExtend3(String extend3) {
        this.extend3 = extend3 == null ? null : extend3.trim();
    }

	public EventInfoDeal(Integer idEvent, Integer idUserDeal,
			Integer serviceAttitude,
			Integer professionalism, Integer eventAssess, String assessDesc) {
		super();
		this.idEvent = idEvent;
		this.idUserDeal = idUserDeal;
	}

	public EventInfoDeal() {
		super();
	}
	
    
    
}