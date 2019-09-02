package com.qkby.analysis.entity;

import java.math.BigDecimal;

public class EventInfoDay {
    private String dates;

    private Long amount;

    private BigDecimal oneLevel;

    private BigDecimal twoLevel;

    private BigDecimal threeLevel;

    private BigDecimal fourLevel;

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates == null ? null : dates.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public BigDecimal getOneLevel() {
        return oneLevel;
    }

    public void setOneLevel(BigDecimal oneLevel) {
        this.oneLevel = oneLevel;
    }

    public BigDecimal getTwoLevel() {
        return twoLevel;
    }

    public void setTwoLevel(BigDecimal twoLevel) {
        this.twoLevel = twoLevel;
    }

    public BigDecimal getThreeLevel() {
        return threeLevel;
    }

    public void setThreeLevel(BigDecimal threeLevel) {
        this.threeLevel = threeLevel;
    }

    public BigDecimal getFourLevel() {
        return fourLevel;
    }

    public void setFourLevel(BigDecimal fourLevel) {
        this.fourLevel = fourLevel;
    }
}