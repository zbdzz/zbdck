package com.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mysql.jdbc.Constants;
import com.pojo.backenduser;

public class SysInterceptor extends HandlerInterceptorAdapter{
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		HttpSession session = request.getSession();
		backenduser user = (backenduser)session.getAttribute("user");
		if(user == null) {
			response.sendRedirect(request.getContextPath()+"/403.jsp");
			return false;
		}
		return true;
		
	}
}
