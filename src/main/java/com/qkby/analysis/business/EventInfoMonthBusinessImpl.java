package com.qkby.analysis.business;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.analysis.dao.EventInfoMonthDao;
import com.qkby.analysis.entity.EventInfoMonth;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午2:59:44 
 *     
 * @version </pre>
 */
@Service
public class EventInfoMonthBusinessImpl implements EventInfoMonthBusiness{
    @Resource
	EventInfoMonthDao eventInfoMonthDao;
	@Override
	public List<EventInfoMonth> selectMonth() throws Exception {
		return eventInfoMonthDao.selectMonth();
	}
   
}
