package com.qkby.event.business;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.qkby.event.dao.KbEventInfoDao;
import com.qkby.event.entity.KbEventInfo;
import com.qkby.sysmanage.dao.SysAssetsTypeDao;
import com.qkby.sysmanage.entity.SysAssetsType;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年11月27日下午1:39:34 
 *     
 * @version </pre>
 */
@Service
public class KbEventInfoBusinessImpl implements KbEventInfoBusiness{
	@Resource
	private KbEventInfoDao kbEventInfoDao;
	@Resource
	private SysAssetsTypeDao sysAssetsTypeDao;
	@Override
	public List<Map<String,Object>> selectKbEventAll(Map<String,Object> map) throws Exception {
		List<Map<String,Object>> selectKbEventAll = kbEventInfoDao.selectKbEventAll(map);
		return selectKbEventAll;
	}

	@Override
	public int insert(KbEventInfo kbEventInfo) throws Exception {
		int in = kbEventInfoDao.insert(kbEventInfo);
		return in;
	}

	@Override
	public List<Map<String, Object>> selectKbEventCondition(Map<String, Object> map) throws Exception {
		List<Map<String, Object>> selectKbEventCondition = kbEventInfoDao.selectKbEventCondition(map);
		return selectKbEventCondition;
	}

	@Override
	public KbEventInfo selectKbEventById(int id) throws Exception {
		KbEventInfo selectKbEventById = kbEventInfoDao.selectKbEventById(id);
		return selectKbEventById;
	}

	@Override
	public int selectKbEventCount(Map<String,Object> map) {
		return kbEventInfoDao.selectKbEventCount(map);
	}

	@Override
	public Map<String, Object> insertKbEvent() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysAssetsType> AssList = sysAssetsTypeDao.selectAll();
		map.put("assList", AssList);
		return map;
	}

	@Override
	public int deleteKbEvent(int id) {
		return kbEventInfoDao.deleteKbEvent(id);
	}

	@Override
	public int updateByPrimaryKeySelective(KbEventInfo kbEventInfo) {
		int update = kbEventInfoDao.updateByPrimaryKeySelective(kbEventInfo);
		return update;
	}
}
