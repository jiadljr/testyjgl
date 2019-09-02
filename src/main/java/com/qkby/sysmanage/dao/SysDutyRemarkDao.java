package com.qkby.sysmanage.dao;

import java.util.Map;

import com.qkby.sysmanage.entity.SysDutyRemarkInfo;

public interface SysDutyRemarkDao {
    public void insert(SysDutyRemarkInfo sysDutyRemarkInfo);
    
    public SysDutyRemarkInfo selectDutyRemark(String dutyTime);
    
    public void deleteDutyRemark(String dutyTime);
    
    public void updateDutyRemark(Map<String,Object> map);
}
