package com.qkby.analysis.business;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.analysis.dao.EventInfoSkyDao;
import com.qkby.analysis.entity.EventInfoSky;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年10月25日下午4:04:20 
 *     
 * @version </pre>
 */
@Service
public class EventInfoSkyBusinessImpl implements EventInfoSkyBusiness{
   @Resource
	EventInfoSkyDao eventInfoSkyDao;
	@Override
	public List<EventInfoSky> selectSky() throws Exception {
		return eventInfoSkyDao.selectSky();
	}
   
}
