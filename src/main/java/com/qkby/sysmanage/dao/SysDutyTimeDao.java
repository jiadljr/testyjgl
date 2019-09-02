package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysDutyTimeInfo;

public interface SysDutyTimeDao {
    public void insert(SysDutyTimeInfo sysDutyTimeInfo);
    
    public List<SysDutyTimeInfo> selectDutyTime();
    
    public void deleteDutyTime(String dutyTime);
    
    public SysDutyTimeInfo selectDutyIdUser(String dutyTime);
    
    public int updateDutyTime(Map<String,Object> map);
    
    public List<SysDutyTimeInfo> selectDutyTimeByTime(String dutyTime);
    
    public List<SysDutyTimeInfo> selectDutyTimeByIdUser(Integer idUser);
}
