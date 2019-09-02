package com.qkby.event.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.event.dao.KbEventFileInfoDao;
import com.qkby.event.entity.KbEventFileInfo;

@Service
public class KbEventFileInfoBusinessImpl implements KbEventFileInfoBusiness{
    @Resource
	KbEventFileInfoDao kbEventFileInfoDao;
	@Override
	public int insert(KbEventFileInfo kbEventFileInfo) throws Exception {
		int in = kbEventFileInfoDao.insert(kbEventFileInfo);
		return in;
	}
	@Override
	public List<Map<String, Object>> selectKbEventFileById(int id) throws Exception {
		List<Map<String, Object>> selectKbEventFileById = kbEventFileInfoDao.selectKbEventFileById(id);
		return selectKbEventFileById;
	}
   
}
