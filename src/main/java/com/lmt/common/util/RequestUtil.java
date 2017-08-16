package com.lmt.common.util;

import javax.servlet.http.HttpServletRequest;

import com.lmt.admin.model.Admin;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 * request工具类
 *
 */
public class RequestUtil {

	public static final String CURR_ADMIN = "curr_admin";
	
	/**
	 * 设置当前登录的admin
	 * @param request
	 * @param admin
	 */
	public static void setCurrAdmin(HttpServletRequest request,Admin admin){
		request.getSession().setAttribute(CURR_ADMIN, admin);
	}
	/**
	 * 获取当前登录的admin
	 * @param request
	 * @return
	 */
	public static Admin getCurrAdmin(HttpServletRequest request){
		return (Admin) request.getSession().getAttribute(CURR_ADMIN);
	}
	
	/**
	 * 移除当前的管理员
	 * @param request
	 * @return
	 */
	public static void removeCurrAdmin(HttpServletRequest request){
		request.getSession().removeAttribute(CURR_ADMIN);
	}
	
}
