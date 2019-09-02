package com.qkby.sysmanage.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.qkby.sysmanage.business.SysUserPostInfoBusiness;

@Controller
public class SysUserPostInfoController {
	@Resource
	SysUserPostInfoBusiness sysUserPostInfoBusiness;
}
