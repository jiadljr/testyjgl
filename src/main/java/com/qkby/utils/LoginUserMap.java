package com.qkby.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginUserMap {
	private static Map<String, String> loginUsers = new ConcurrentHashMap<String, String>();

	/**
	 * 将用户和sessionId存入map
	 * 
	 * @param key
	 * @param value
	 */
	public static void setLoginUsers(String loginId, String sessionId) {
		loginUsers.put(loginId, sessionId);
	}

	/**
	 * 获取loginUsers
	 * 
	 * @return
	 */
	public static Map<String, String> getLoginUsers() {
		return loginUsers;
	}

	/**
	 * 根据sessionId移除map中的值
	 * 
	 * @param sessionId
	 */
	public static void removeUser(String sessionId) {
		for (Map.Entry<String, String> entry : loginUsers.entrySet()) {
			if (sessionId.equals(entry.getValue())) {
				loginUsers.remove(entry.getKey());
				break;
			}
		}
	}

	/**
	 * 判断用户是否在loginusers中
	 * 
	 * @param loginId
	 * @param sessionId
	 * @return
	 */
	public static boolean isInLoginUsers(String loginId, String sessionId) {
		return (loginUsers.containsKey(loginId) && sessionId.equals(loginUsers.get(loginId)));
	}
}
