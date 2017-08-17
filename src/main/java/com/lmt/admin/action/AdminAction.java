package com.lmt.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmt.admin.model.Admin;
import com.lmt.admin.service.IAdminService;
import com.lmt.common.action.BaseAction;
import com.lmt.common.util.PasswordUtil;

/**
 * 
 * @author ducx
 * @date 2017-08-17
 *
 */
@Controller
@RequestMapping(value="/admin")
public class AdminAction extends BaseAction {
	
	@Resource
	private IAdminService adminService;
	
	/**
	 * 后台首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/index",name="后台首页")
	public String index(
			HttpServletRequest request,HttpServletResponse response){
		
		return "admin/index";
	}

	/**
	 * 管理员列表
	 * @param currPage
	 * @param limit
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list",name="管理员列表")
	public String list(
			@RequestParam(name="currPage",defaultValue="0") int currPage,
			@RequestParam(name="limit",defaultValue="10") int limit,
			HttpServletRequest request,HttpServletResponse response){
		
		return "admin/list";
	}
	
	/**
	 * 编辑、添加管理员
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit",name="编辑、添加管理员")
	public String edit(
			@RequestParam(name="id",defaultValue="0") int id,
			HttpServletRequest request,HttpServletResponse response){
		if(id > 0){
			Admin admin = adminService.get(id);
			request.setAttribute("admin", admin);
		}
		return "admin/edit";
	}
	
	/**
	 * 保存管理员
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save",name="保存管理员")
	@ResponseBody
	public Map<String,Object> save(
			@RequestParam(name="id",defaultValue="0") int id,
			@RequestParam(name="username") String username,
			@RequestParam(name="password",required=false) String password,
			@RequestParam(name="email") String email,
			@RequestParam(name="phoneNum") String phoneNum,
			@RequestParam(name="realname") String realname,
			@RequestParam(name="status",defaultValue="1") int status,
			HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("success", true);
		if(id <= 0){
			Admin admin = new Admin();
			admin.setUsername(username);
			admin = adminService.load(admin);
			if(admin != null){
				map.put("code", 201);
				map.put("msg", "用户名已存在");
				return map;
			}
			admin = new Admin();
			admin.setEmail(email);
			admin = adminService.load(admin);
			if(admin != null){
				map.put("code", 202);
				map.put("msg", "该邮箱已被使用");
				return map;
			}
			admin = new Admin();
			admin.setPhoneNum(phoneNum);
			admin = adminService.load(admin);
			if(admin != null){
				map.put("code", 203);
				map.put("msg", "该手机号已被使用");
				return map;
			}
			admin = new Admin();
			admin.setEmail(email);
			String salt = PasswordUtil.generateSalt();
			admin.setPassword(PasswordUtil.encode(username, password, salt));
			admin.setPhoneNum(phoneNum);
			admin.setRealname(realname);
			admin.setSalt(salt);
			admin.setStatus(status);
			admin.setUsername(username);
			adminService.insert(admin);
		}else{
			Admin admin = adminService.get(id);
			boolean update = false;
			if(!email.equalsIgnoreCase(admin.getEmail())){
				Admin a = new Admin();
				a.setEmail(email);
				a = adminService.load(a);
				if(a != null){
					map.put("code", 202);
					map.put("msg", "该邮箱已被使用");
					return map;
				}
				admin.setEmail(email);
				update = true;
			}
			if(!phoneNum.equalsIgnoreCase(admin.getPhoneNum())){
				Admin a = new Admin();
				a.setPhoneNum(phoneNum);
				a = adminService.load(a);
				if(a != null){
					map.put("code", 203);
					map.put("msg", "该手机号已被使用");
					return map;
				}
				admin.setPhoneNum(phoneNum);
				update = true;
			}
			if(update){
				adminService.update(admin);
			}
		}
		map.put("code", 200);
		map.put("msg", "保存成功");
		return map;
	}
	
	/**
	 * 找回密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findPwd",name="找回密码")
	public String findPwd(
			HttpServletRequest request,HttpServletResponse response){
		
		return "admin/findPwd";
	}
	
	/**
	 * 邮箱找回密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/emailFindPwd",name="邮箱找回密码")
	public String emailFindPwd(
			HttpServletRequest request,HttpServletResponse response){
		
		return "admin/emailFindPwd";
	}
	
	/**
	 * 发送密码找回email
	 * @param username
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="sendFindPwdEmail",name="发送密码找回email")
	public Map<String,Object> sendFindPwdEmail(
			@RequestParam(name="username") String username,
			HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("code", 200);
		return map;
	}
	
	/**
	 * 邮件发送成功
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/sendFindPwdEmailSuccess",name="邮件发送成功")
	public String sendFindPwdEmailSuccess(
			HttpServletRequest request,HttpServletResponse response){
		
		return "admin/sendFindPwdEmailSuccess";
	}
	
	/**
	 * 重置密码
	 * @param sign
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/resetPwd",name="重置密码")
	public String resetPwd(
			@RequestParam(name="sign") String sign,
			HttpServletRequest request,HttpServletResponse response){
		
		return "admin/resetPwd";
	}
	
}
