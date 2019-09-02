package com.qkby.sysmanage.business;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qkby.sysmanage.entity.SysDutyInfo;

public interface SysDutyInfoBusiness {
	/**
	 * <pre>selectByExample 
	 * Created by 家栋梁 on 2017年9月16日下午4:06:56  
	 *
	 * @return</pre>
	 */
   List<SysDutyInfo> selectByExample()throws Exception;
   /**
    * <pre>selectByCond 
    * Created by 家栋梁 on 2017年9月18日下午7:45:33  
    *
    * @return</pre>
    */
   Map<String,Object> selectByCond(HttpServletRequest request, HttpServletResponse response)throws Exception;
   /**
    * <pre>insertDuty 
    * Created by 家栋梁 on 2017年9月19日下午2:12:45  
    *
    * @return</pre>
    */
   void submitDuty(HttpServletRequest request, HttpServletResponse response)throws Exception;
   /**
    * <pre>selectDutyDate 
    * Created by 家栋梁 on 2017年11月23日上午10:52:27  
    *
    * @param id
    * @return</pre>
    */
   Map<String,Object> selectDutyDate(int id)throws Exception;
}
