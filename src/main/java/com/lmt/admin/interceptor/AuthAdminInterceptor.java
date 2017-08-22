package com.lmt.admin.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.lmt.admin.model.Admin;
import com.lmt.admin.model.AdminRole;
import com.lmt.admin.model.Resource;
import com.lmt.admin.model.RoleResource;
import com.lmt.admin.service.IAdminRoleService;
import com.lmt.admin.service.IResourceService;
import com.lmt.admin.service.IRoleResourceService;
import com.lmt.common.util.RequestUtil;

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
	
	@javax.annotation.Resource
	private IAdminRoleService adminRoleService;
	
	@javax.annotation.Resource
	private IRoleResourceService roleResourceService;
	
	/**
	 * 
	 * 方法执行前拦截
	 * 
	 */
    @Override
    public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
        /**
         * 获取请求连接
         */
    	String path = request.getContextPath();
    	String uri = request.getRequestURI();
    	if(StringUtils.isNotBlank(path)){
    		uri = uri.replaceFirst(path, "");
    	}
    	/**
    	 * 判断资源权限是否已配置
    	 */
    	Resource res = resourceService.getResourceBy(uri);
    	if(res == null){
    		HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
            if(responseBody == null){
            	request.setAttribute("error_msg", "您无权限进行此操作！资源权限未配置,如有疑问请联系管理！");
            	request.getRequestDispatcher("/WEB-INF/views/admin/resource-not-auth.jsp").forward(request, response);
            	return false;
            }else{
            	String result = "{'success':true,'code':403,'msg':'您无权限进行此操作！资源权限未配置'}";
            	response.setContentType("text/html;charset=UTF-8");
            	response.getWriter().println(result);
            	return false;
            }
    	}
    	/**
    	 * 判断是否需要验证
    	 */
		if(res.getNeedAuth().intValue() == 0){
			return true;
		}
		/**
		 * 判断管理员是否登录
		 */
    	Admin admin = RequestUtil.getCurrAdmin(request);
    	if(admin == null){
    		String returnType = res.getReturnType();
    		if("json".equals(returnType)){
    			String result = "{'success':true,'code':400,'msg':'您未登录，请登录后操作！'}";
            	response.setContentType("text/html;charset=UTF-8");
            	response.getWriter().println(result);
            	return false;
    		}else{
    			response.sendRedirect(path + "/admin/login");
    			return false;
    		}
    	}
    	/**
    	 * 判断是否是超管，超管直接放行
    	 */
    	AdminRole ar = new AdminRole();
    	ar.setAdminId(admin.getId());
    	ar.setRoleId(1);
    	ar = adminRoleService.load(ar);
    	if(ar != null){
    		return true;
    	}
    	/**
    	 * 非超管，判断资源是否有权限
    	 */
    	RoleResource rr = roleResourceService.getByAdminIdResId(admin.getId(), res.getId());
    	if(rr == null){
    		if(res.getPid() > 0){
        		rr = roleResourceService.getByAdminIdResId(admin.getId(), res.getId());
        	}
    	}
    	if(rr != null){
    		return true;
    	}
    	String returnType = res.getReturnType();
		if("json".equals(returnType)){
			String result = "{'success':true,'code':403,'msg':'您无权进行此操作，请联系管理员！'}";
        	response.setContentType("text/html;charset=UTF-8");
        	response.getWriter().println(result);
		}else{
			request.setAttribute("error_msg", "您无权限进行此操作！资源权限未配置,如有疑问请联系管理！");
        	request.getRequestDispatcher("/WEB-INF/views/admin/resource-not-auth.jsp").forward(request, response);
		}
        return false;
    }
}
