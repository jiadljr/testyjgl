package com.qkby.sysmanage.dao;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysDutyOrderInfo;

public interface SysDutyOrderDao {
	
     public void insert(SysDutyOrderInfo sysDutyOrder);
     
     public List<SysDutyOrderInfo> selectDutyOrder();
     
     public SysDutyOrderInfo selectDutyOrderById(Integer ids);
     
     public void updateDutyOrder(Map<String,Object> map);
     
     public void deleteDutyOrder(Integer id);
}
