package com.qkby.sysconfig.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.qkby.sysconfig.business.SysRoleInfoBusiness;
import com.qkby.sysconfig.entity.SysRoleInfo;
import com.qkby.sysmanage.business.SysPostInfoBusiness;
import com.qkby.sysmanage.business.SysUserInfoBusiness;
import com.qkby.utils.ParamConfigInfosUtil;
import com.qkby.utils.Utils;
/**
 * 
 * <pre>项目名称：Security_20170911    
 * Created by 家栋梁 on 2017年9月20日下午12:20:24 
 *     
 * @version </pre>
 */
@Controller
public class PersonalController {
	 @Resource
     public SysUserInfoBusiness sysUserInfoBusiness;
	 @Resource
	 public SysRoleInfoBusiness sysRoleInfoBusiness;
	 @Resource
	 SysPostInfoBusiness sysPosyInfoBusiness;
     
	 @RequestMapping("personalCenter.do")
	 public ModelAndView personalCenter(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HttpSession session = request.getSession();
			String user_id = String.valueOf(session.getAttribute("user_id"));
			int userId=0;
			if (Utils.isNum(user_id)) {
				userId= Integer.valueOf(user_id);
			}
			Map<String, Object> userMap = new HashMap<String,Object>();		
			Map<String, Object> selectById = sysUserInfoBusiness.selectNameById(userId);
			//查询角色
			List<SysRoleInfo> roleType = sysRoleInfoBusiness.selectRo(userId);
			String roleName_a="";
			for (int i = 0; i < roleType.size(); i++) {
				String roleName_b = roleType.get(i).getName();
				if (i < roleType.size()-1) {
					roleName_a+=roleName_b+",";
				}else{
					roleName_a+=roleName_b;
				}
			}
			SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			String toDate  = sf.format(selectById.get("pwdExpiredDate"));
			ParamConfigInfosUtil pcu = new ParamConfigInfosUtil();
			Map<String, String> parMap = null ;
			try {
				parMap = pcu.getUserPwdFileUreNum(request);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Integer pwdAging = Integer.valueOf(parMap.get("pwdAging"));//密码失效天数
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(sf.parse(toDate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			calendar.add(Calendar.DATE, pwdAging*-1);
			String passwordCreate=sf.format(calendar.getTime());
			userMap.put("user", selectById);
			userMap.put("roleName", roleName_a);
			userMap.put("toDate", toDate);
			userMap.put("passwordCreate", passwordCreate);
			userMap.put("user_id", user_id);
		 return new ModelAndView("homepage/personal_center",userMap);
	 }
	 
}
