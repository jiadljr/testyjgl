package com.qkby.sysconfig.business;

import javax.servlet.http.HttpServletRequest;

public interface ChangePasswordBusiness {
	String submitPassword(HttpServletRequest request) throws Exception;
}
