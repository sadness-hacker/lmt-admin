package com.lmt.admin.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lmt.admin.service.IResourceService;

/**
 * 
 * @author ducx
 * @date 2016-05-24
 * 管理员登录验证拦截器
 *
 */
public class AuthAdminInterceptor extends HandlerInterceptorAdapter{
	
	@javax.annotation.Resource
	private IResourceService resourceService;
	
	/**
	 * 
	 * 方法执行前拦截
	 * 
	 */
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
//        HandlerMethod method = (HandlerMethod) handler;
        
        return true;
    }
}
