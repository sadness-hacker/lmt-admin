package com.lmt.admin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmt.admin.model.Admin;
import com.lmt.admin.model.AdminRole;
import com.lmt.admin.model.Resource;
import com.lmt.admin.service.IAdminRoleService;
import com.lmt.admin.service.IAdminService;
import com.lmt.admin.service.IResourceService;
import com.lmt.common.action.BaseAction;
import com.lmt.common.util.PasswordUtil;
import com.lmt.common.util.RequestUtil;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 * 管理员登录退出action
 *
 */
@Controller
@RequestMapping(value="/admin")
public class LoginAction extends BaseAction {
	
	@javax.annotation.Resource
	private IAdminService adminService;
	
	@javax.annotation.Resource
	private IAdminRoleService adminRoleService;
	
	@javax.annotation.Resource
	private IResourceService resourceService;
	
	@RequestMapping(value="/login",method=RequestMethod.GET,name="获取登录页面")
	public String login(
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		Admin admin = RequestUtil.getCurrAdmin(request);
		if(admin != null){
			response.sendRedirect("index");
			return null;
		}
		return "admin/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST,name="返回登录结果")
	@ResponseBody
	public Map<String,Object> login(
			@RequestParam(value="username") String username,
			@RequestParam(value="password") String password,
			@RequestParam(value="imageCode",required=false) String imageCode,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		Admin admin = RequestUtil.getCurrAdmin(request);
		if(admin != null){
			if(username.equalsIgnoreCase(admin.getUsername())){
				map.put("code", 200);
				map.put("msg", "登录成功");
				return map;
			}
		}
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			map.put("code", 201);
			map.put("msg", "用户名和密码不能为空");
			return map;
		}
		admin = new Admin();
		admin.setUsername(username);
		admin = adminService.load(admin);
		if(admin == null){
			map.put("code", 202);
			map.put("msg", "用户不存在");
			return map;
		}
		password = PasswordUtil.encode(admin.getUsername(), password, admin.getSalt());
		if(!password.equals(admin.getPassword())){
			map.put("code", 203);
			map.put("msg", "密码错误");
			return map;
		}
		RequestUtil.setCurrAdmin(request, admin);
		map.put("code", 200);
		map.put("msg", "登录成功");
		AdminRole ar = adminRoleService.getByAdminIdRoleId(admin.getId(), 1);
		if(ar != null){
			request.getSession().setAttribute("isSuperAdmin", true);
		}else{
			List<Resource> resList = resourceService.listByAdminId(admin.getId());
			Map<String,Boolean> menuMap = new HashMap<String, Boolean>();
			for(Resource res : resList){
				if("menu".equals(res.getType())){
					menuMap.put(res.getKey(), true);
				}
			}
			request.getSession().setAttribute("menuMap", menuMap);
		}
		return map;
	}
	
	@RequestMapping(value="/logout",name="管理员用户退出")
	public void logout(
			HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.getSession().invalidate();
		response.sendRedirect("login");
	}
	
}
