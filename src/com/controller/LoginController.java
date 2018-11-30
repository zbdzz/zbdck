package com.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.backenduser;
import com.service.UserService;

@Controller
public class LoginController {
	@Resource
	private UserService userService;
	@RequestMapping(value="backendlogin.html")
	public String backendlogin() {
		return "backendlogin";
	}
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpServletRequest request,HttpSession session)throws Exception{
		backenduser uBackenduser = userService.selectNamepwd(userCode, userPassword);
		if(null!=uBackenduser) {
			session.setAttribute("backenduser", uBackenduser);
			return "redirect:/sys/index.html";
		}else {
			request.setAttribute("error", "用户名或密码不正确");
			return "backendlogin";
		}
	}
	@RequestMapping(value="/logout.html")
	public String logout(HttpSession session) {
		session.removeAttribute("backenduser");
		return "backendlogin";
	}
	@RequestMapping(value="/sys/index.html")
	public String index() {
		return "frame";
	}
}
