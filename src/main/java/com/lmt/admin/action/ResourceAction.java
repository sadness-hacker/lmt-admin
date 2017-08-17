package com.lmt.admin.action;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lmt.admin.model.Resource;
import com.lmt.common.action.BaseAction;

/**
 * 
 * 资源相关action
 * @author ducx
 * @date 2017-08-17
 *
 */
@Controller
@RequestMapping(value="/admin/resource")
public class ResourceAction extends BaseAction {
	
	@javax.annotation.Resource
	private RequestMappingHandlerMapping requestMappingHandlerMapping;
	
	
	private static final Map<String,String> resourceTypeMap = new HashMap<String, String>(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		{
			put("menu", "菜单");
			put("button","按钮");
			put("url","链接");
		}
		
	};
	
	/**
	 * 资源列表
	 * @param currPage
	 * @param limit
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list",name="资源列表")
	public String list(
			@RequestParam(name="currPage",defaultValue="1") int currPage,
			@RequestParam(name="limit",defaultValue="10") int limit,
			@RequestParam(name="keyword") String keyword,
			HttpServletRequest request,HttpServletResponse response){
		List<Resource> list = new ArrayList<Resource>();
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for(RequestMappingInfo info : map.keySet()){
        	Set<String> patterns = info.getPatternsCondition().getPatterns();
        	HandlerMethod hm = map.get(info);
        	Method method = hm.getMethod();
        	String requireInfo = "<span style='color:red;'>不需登录</span>";
            
        	String classMethodName = info.getName() + "#" + method.getDeclaringClass().getName() + "#" + method.getName();
        	
        }
        
        if(!StringUtils.isBlank(keyword)){
	        List<Resource> arList = new ArrayList<Resource>();
	        for(Resource ar : list){
				String key = ar.getDescription() + ar.getDeveloper() + ar.getKey() + ar.getName() + ar.getUrl() + ar.getStatus() + ar.getType() + resourceTypeMap.get(ar.getType());
				if(key.contains(keyword)){
					arList.add(ar);
				}
			}
	        list = arList;
        }
        request.setAttribute("list", list);
		return "admin/resource/list";
	}
	
}
