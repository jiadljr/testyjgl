package com.qkby.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import net.sf.json.JSONObject;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		Map<String, Object> model = new HashMap<String, Object>();  
        model.put("ex", ex);  
//		String rqType = request.getHeader("X-Requested-With");
//		if(StringUtils.equals(rqType, "XMLHttpRequest")){
//			
//		}
		if(ex instanceof BusinessException){
			BusinessException be = (BusinessException)ex;
			JSONObject data = new JSONObject();
			data.put("code", be.getCode());
			data.put("msg", be.getMsg());
			try {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(data.toString());
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}else{
			return new ModelAndView("/error/error",model);
		}
	}
	
}
