package com.qkby.sysmanage.business;

import java.util.Map;

import com.qkby.sysmanage.entity.SysDutyRemarkInfo;

public interface SysDutyRemarkBusiness {
     public void insert(SysDutyRemarkInfo sysDutyRemark);
     
     public SysDutyRemarkInfo selectDutyRemark(String dutyTime);
     
     public void deleteDutyRemark(String dutyTime);
     
     public void updateDutyRemark(Map<String,Object> map);
}
