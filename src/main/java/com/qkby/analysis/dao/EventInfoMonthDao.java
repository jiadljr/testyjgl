package com.qkby.analysis.dao;

import com.qkby.analysis.entity.EventInfoMonth;
import java.util.List;

public interface EventInfoMonthDao {
	List<EventInfoMonth> selectMonth() throws Exception;
}