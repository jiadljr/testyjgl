package com.qkby.analysis.dao;

import com.qkby.analysis.entity.EventInfoSky;

import java.util.List;

public interface EventInfoSkyDao {
	List<EventInfoSky> selectSky() throws Exception;
}