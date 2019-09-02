package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;

import com.qkby.sysmanage.entity.SysDutyOrderInfo;

public interface SysDutyOrderBusiness {
   public Map<String,Object> queryDutyTime() throws Exception;
   
   public void insertDutyOrder(SysDutyOrderInfo sysDutyOrder);
   
   public List<SysDutyOrderInfo> selectDutyOrder();
   
   public SysDutyOrderInfo selectDutyOrderById(Integer id);
   
   public void updateDutyOrder(Map<String,Object> map);
   
   public void deleteDutyOrder(Integer id);
}
