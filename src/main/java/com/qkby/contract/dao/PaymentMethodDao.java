package com.qkby.contract.dao;

import java.util.List;
import java.util.Map;

import com.qkby.contract.entity.PaymentMethod;

public interface PaymentMethodDao {
	/**
	 * 
	 * @Description (查询所有的付款方式 )
	 * @param map
	 * @return
	 */
	List<PaymentMethod> selectPaymentMethodByCondition(Map<String,Object> map);
	/**
	 * 
	 * @Description (查询付款方式不带条件 )
	 * @return
	 */
	List<PaymentMethod> selectPaymentMethodAll();
	/**
	 * 
	 * @Description (根据ID查询付款方式)
	 * @param id
	 * @return
	 */
	PaymentMethod selectPaymentMethodById(String id);
}
