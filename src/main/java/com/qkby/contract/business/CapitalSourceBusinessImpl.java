package com.qkby.contract.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qkby.contract.dao.CapitalSoureDao;
import com.qkby.contract.entity.CapitalSource;

@Service
public class CapitalSourceBusinessImpl implements CapitalSourceBusiness{
	@Resource
	private CapitalSoureDao capitalSourceDao;

	@Override
	public List<CapitalSource> selectCapitalSourceAllByCondition(Map<String, Object> map) {
		return capitalSourceDao.selectCapitalSourceAllByCondition(map);
	}

	@Override
	public List<CapitalSource> selectCapitalSourceAll() {
		return capitalSourceDao.selectCapitalSourceAll();
	}

	@Override
	public CapitalSource selectCapitalSourceById(String id) {
		return capitalSourceDao.selectCapitalSourceById(id);
	}

	@Override
	public int insertCapitalSource(CapitalSource capitalSource) {
		return capitalSourceDao.insertCapitalSource(capitalSource);
	}

	@Override
	public int updateCapitalSource(CapitalSource capitalSource) {
		return capitalSourceDao.updateCapitalSource(capitalSource);
	}

	@Override
	public int deleteCapitalSource(String id) {
		return capitalSourceDao.deleteCapitalSource(id);
	}

}
