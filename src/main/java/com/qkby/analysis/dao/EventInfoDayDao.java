package com.qkby.analysis.dao;

import com.qkby.analysis.entity.EventInfoDay;
import java.util.List;

public interface EventInfoDayDao {
	List<EventInfoDay> selectDay() throws Exception;
}