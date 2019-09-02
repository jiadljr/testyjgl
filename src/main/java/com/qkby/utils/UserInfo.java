package com.qkby.utils;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录人员信息
 * @author H.W
 *
 */

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1372356532975527045L;
	
	private Map map = new ConcurrentHashMap();
	
	public Object getMapValue(String key){
		Object value = map.get(key);
		return value;
	}
	
	public void setMapValue(String key,Object value){
		map.put(key, value);
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

}
