package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysDutyTimeInfo;

public interface SysDutyTimeBusiness {
     public void insert(SysDutyTimeInfo sysDutyTime);
     
     public List<SysDutyTimeInfo> selectDutyTime();
     
     public void deleteDutyTime(String dutyTime);
     
     public SysDutyTimeInfo selectDutyIdUser(String dutyTime);
     
     public int updateDutyTime(String dutyTime,String dutyUser);
     
     public List<SysDutyTimeInfo> selectDutyTimeByTime(String dutyTime);
     
     public List<SysDutyTimeInfo> selectDutyTimeByIdUser(Integer idUser);
}
