package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qkby.exception.BusinessException;
import com.qkby.sysmanage.entity.SysArrange;


public interface SysArrangeBusiness {
	/**
	 * 查询值班信息
	 * 2017年12月27日 下午9:03:59
	 * @李帅
	 * @param pgMap
	 * @throws Exception 
	 */
	Map<String, Object> selectArrangeList(Map<String, Object> pgMap,String pages,HttpServletRequest req)throws Exception;
	/**
	 * 新增值班
	 * 2017年12月28日 下午3:00:08
	 * @李帅
	 * @param duty_user
	 * @param duty_start_time
	 * @param duty_end_time
	 * @throws Exception 
	 */
	Map<String,Object> insertArrange(String duty_user,String duty_start_time,String duty_end_time,HttpServletRequest request,String timeScope) throws Exception;
	/**
	 * 查询单条值班数据
	 * 2017年12月28日 下午7:07:06
	 * @李帅
	 * @param user_id
	 * @throws Exception 
	 */
	Map<String, Object> selectByPrimaryKey(int user_id)throws Exception;
	/**
	 * 修改值班数据
	 * 2017年12月28日 下午7:35:41
	 * @李帅
	 * @param arrange
	 * @throws Exception 
	 */
	void updateArrange(SysArrange arrange)throws Exception;
	/**
	 * 查询该时间是否在值班
	 * 2018年1月1日 下午6:28:05
	 * @李帅
	 * @param pgMap
	 * @throws Exception 
	 */
	Boolean selectArrangeByDate(String user_id,HttpServletRequest request)throws Exception;
	/**
	 * 排班表导出
	 * 2018年1月3日 下午6:22:32
	 * @李帅
	 * @param
	 * @throws BusinessException 
	 * @throws Exception 
	 */
	void dutyExport(HttpServletResponse response,HttpServletRequest request) throws Exception;
	
	String deleteArrange(int dutyId) throws Exception;
	
	/**
	 * 判断是否有可导出的数据
	 * 2018年1月14日 下午12:08:46
	 * @李帅
	 * @param
	 * @throws Exception 
	 */
	void ifHaveData() throws Exception;
	
	List<SysArrange> selectDutyArrange();
}
