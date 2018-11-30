package com.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.pojo.backenduser;
import com.pojo.category;
import com.pojo.devuser;
import com.pojo.dictionary;
import com.pojo.info;
import com.pojo.version;
import com.service.UserService;
import com.tools.PageSupport;

@Controller
@RequestMapping("/manager")
public class UserController {
	//登录
	@Resource
	private UserService userService;
	@RequestMapping(value="/dologin.html",method=RequestMethod.POST)
	public String doLogin(@RequestParam String userCode,@RequestParam String userPassword,HttpSession session,HttpServletRequest request) {
		backenduser backenduser = userService.selectNamepwd(userCode, userPassword);
		if(null!=backenduser) {
			session.setAttribute("user", backenduser);
			return "/backend/main";
		}else {
			request.setAttribute("error", "用户名或密码不正确");
			return "backendlogin";
		}
	}
	@RequestMapping(value="/login.html")
	public String login() {
		return "backendlogin";
	}
	//注销
	@RequestMapping(value="logout")
	public String exist() {
		return "backendlogin";
	}
	@RequestMapping(value="backend/app/list")
	public String getInfoList(Model model,
		@RequestParam(value="querySoftwareName",required=false)String softwareName,
		@RequestParam(value="queryFlatformId",required=false)Integer flatformId,
		@RequestParam(value="queryCategoryLevel1",required=false)String categoryLevel1,
		@RequestParam(value="queryCategoryLevel2",required=false)String categoryLevel2,
		@RequestParam(value="queryCategoryLevel3",required=false)String categoryLevel3,
		@RequestParam(value="pageIndex",required=false)String pageIndex
		) {
		String softwareName1 = null;
		String categoryLevel11 = null;
		String categoryLevel12 = null;
		String categoryLevel13 = null;
		String status1 = null;
		List<info> infos = null;
		List<category> listcate=null;
		List<dictionary> listdic=null;
		int pageSize = 5;
		int currentPageNo = 1;
		if(flatformId == null ) {
			flatformId = 0;
		}
		if(softwareName != null && !softwareName.equals("") && !softwareName.equals(" ")) {
			softwareName1 = softwareName;
		}
		if(categoryLevel1 != null && !categoryLevel1.equals("") && !categoryLevel1.equals(" ")) {
			categoryLevel11 = categoryLevel1;
		}
		if(categoryLevel2 != null && !categoryLevel2.equals("") && !categoryLevel2.equals(" ")) {
			categoryLevel12 = categoryLevel2;
		}
		if(categoryLevel3 != null && !categoryLevel3.equals("") && !categoryLevel3.equals(" ")) {
			categoryLevel13 = categoryLevel3;
		}
		if (pageIndex==null) {
			pageIndex="1";
		}
		if(pageIndex!=null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (Exception e) {
				return "redirect:";
			}
		}
		int totalCount = userService.getInfoCount(softwareName1, flatformId, categoryLevel13, categoryLevel2, categoryLevel3,1);
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		
		if(currentPageNo < 1) {
			currentPageNo = 1;
		}else if(currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		infos = userService.getInfoList(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3,1,(Integer.parseInt(pageIndex)-1)*pageSize, pageSize);
		if (infos.isEmpty() || infos == null) {
			pages.setTotalCount(0);
		}
		if (infos.size() == 1) {
			pages.setTotalCount(1);
		}
		System.out.println(infos.size());
		listcate=userService.listcate(0);//一级菜单
		listdic=userService.list("APP_FLATFORM");//app状态
		model.addAttribute("getInfoCount",infos);
		model.addAttribute("softwareName",softwareName);
		model.addAttribute("queryFlatformId",flatformId);
		model.addAttribute("pages",pages);
		model.addAttribute("queryCategoryLevel1",categoryLevel1);
		model.addAttribute("queryCategoryLevel2",categoryLevel2);
		model.addAttribute("queryCategoryLevel3",categoryLevel3);
		model.addAttribute("appInfoList", infos);
		model.addAttribute("categoryLevel1List", listcate);
		model.addAttribute("flatFormList", listdic);
		return "/backend/applist";
	}
	@RequestMapping(value="backend/app/categorylevellist",method=RequestMethod.GET,produces= {"application/json;charset=UTF-8"})
	@ResponseBody
	public Object catalist(Integer pid) {
		System.out.println(""+pid);
		return JSONArray.toJSONString(userService.listcate(pid));
	}
	@RequestMapping(value="backend/app/view")
	public String view(@RequestParam Integer aid,@RequestParam Integer vid,Model model){
		info infos = userService.getappinfo(aid);
		version versions = userService.getappversion(vid);
		model.addAttribute("appInfo",infos);
		model.addAttribute("appVersion",versions);
		return "/backend/appcheck";
	}
	@RequestMapping(value="backend/app/checksave")
	public String update(@RequestParam Integer status,Integer id,Model model) {
		version version = userService.getappversion(id);
		int info = userService.update(status, id);
		if(info>0) {
			return "redirect:/manager/backend/app/list";
		}
		model.addAttribute(version);
		return "/backend/appcheck";
	}
}
