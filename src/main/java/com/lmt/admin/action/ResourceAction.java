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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.lmt.admin.model.Resource;
import com.lmt.admin.service.IResourceService;
import com.lmt.common.action.BaseAction;
import com.lmt.orm.common.model.PaginationModel;

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
	
	@javax.annotation.Resource
	private IResourceService resourceService;
	
	
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
	
	private static final Map<String,String> returnTypeMap = new HashMap<String, String>(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		{
			put("json", "json字符串");
			put("page","jsp页面");
			put("redirect","重定向");
		}
		
	};
	
	private static final Map<String,String> returnValueMap = new HashMap<String, String>(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		{
			put("json", "{'success':true,'code':403,'msg':'您无权进行此操作'}");
			put("page","/WEB-INF/views/admin/login.jsp");
			put("redirect","/admin/login");
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
			@RequestParam(name="keyword",required=false) String keyword,
			HttpServletRequest request,HttpServletResponse response){
		List<Resource> list = resourceService.listAll();
		Map<String, Resource> resMap = new HashMap<String, Resource>(list.size());
		for(Resource r : list){
			resMap.put(r.getUrl(), r);
		}
		Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for(RequestMappingInfo info : map.keySet()){
        	Set<String> patterns = info.getPatternsCondition().getPatterns();
        	HandlerMethod hm = map.get(info);
        	Method method = hm.getMethod();
        	ResponseBody responseBody = method.getAnnotation(ResponseBody.class);
        	String classMethodName = info.getName() + "<br/>" + method.getDeclaringClass().getName() + "<br/>" + method.getName();
        	for(String url : patterns){
        		Resource res = resMap.get(url);
        		if(res == null){
        			res = new Resource();
	        		res.setDescription(classMethodName);
	        		res.setDeveloper("未知");
	        		res.setKey(url);
	        		res.setName(info.getName());
	        		res.setPid(0);
	        		if(responseBody == null){
	        			res.setReturnType("json");
		        		res.setReturnValue("{'success':true,'code':403,'msg':'您无权进行此操作'}");
	        		}else{
	        			res.setReturnType("redirect");
		        		res.setReturnValue("/admin/login");
	        		}
	        		res.setStatus(0);
	        		res.setType("url");
	        		res.setUrl(url);
	        		list.add(res);
        		}
        	}
        }
        
        if(StringUtils.isNotBlank(keyword)){
	        List<Resource> arList = new ArrayList<Resource>();
	        for(Resource ar : list){
				String key = ar.getDescription() + ar.getDeveloper() + ar.getKey() + ar.getName() + ar.getUrl() + ar.getStatus() + ar.getType() + resourceTypeMap.get(ar.getType());
				if(key.contains(keyword)){
					arList.add(ar);
				}
			}
	        list = arList;
        }
        PaginationModel<Resource> pageModel = new PaginationModel<Resource>();
		pageModel.setCurrPage(currPage);
		pageModel.setLimit(limit);
        pageModel.setList(list);
        request.setAttribute("pageModel", pageModel);
        request.setAttribute("resourceTypeMap", resourceTypeMap);
		return "admin/resource/list";
	}
	
	/**
	 * 获取资源编辑页面
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit",name="获取资源编辑页面")
	public String edit(
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="description",required=false) String description,
			@RequestParam(value="developer",required=false) String developer,
			@RequestParam(value="key",required=false) String key,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="pid",defaultValue="0") int pid,
			@RequestParam(value="returnType",required=false) String returnType,
			@RequestParam(value="status",defaultValue="1") int status,
			@RequestParam(value="type",required=false) String type,
			@RequestParam(value="url",required=false) String url,
			HttpServletRequest request,HttpServletResponse response){
		Resource res = null;
		if(id > 0){
			res = resourceService.get(id);
		}
		if(res == null){
			res = new Resource();
			res.setDescription(description);
			res.setDeveloper(developer);
			res.setKey(key);
			res.setName(name);
			res.setPid(pid);
			res.setReturnType(returnType);
			if("json".equals(returnType)){
				res.setReturnValue("{'success':true,'code':403,'msg':'您无权进行此操作'}");
			}else{
				res.setReturnValue("/admin/login");
			}
			res.setStatus(status);
			res.setType(type);
			res.setUrl(url);
		}
		Resource res1 = new Resource();
		res1.setType("menu");
		List<Resource> resList = resourceService.list(res1);
		request.setAttribute("resList", resList);
		request.setAttribute("res", res);
		request.setAttribute("resourceTypeMap", resourceTypeMap);
		request.setAttribute("returnTypeMap", returnTypeMap);
		request.setAttribute("returnValueMap", returnValueMap);
		return "admin/resource/edit";
	}
	
	/**
	 * 管理员保存资源
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save",name="管理员保存资源")
	@ResponseBody
	public Map<String,Object> save(
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="description") String description,
			@RequestParam(value="developer") String developer,
			@RequestParam(value="key") String key,
			@RequestParam(value="name") String name,
			@RequestParam(value="pid",defaultValue="0") int pid,
			@RequestParam(value="returnType") String returnType,
			@RequestParam(value="returnValue") String returnValue,
			@RequestParam(value="status",defaultValue="1") int status,
			@RequestParam(value="type") String type,
			@RequestParam(value="url") String url,
			HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		Resource res = null;
		if(id > 0){
			res = resourceService.get(id);
		}
		if(res == null){
			Resource r = new Resource();
			r.setKey(key);
			r = resourceService.load(r);
			if(r != null){
				map.put("code", 201);
				map.put("msg", "资源标识已被使用");
				return map;
			}
			r = new Resource();
			r.setUrl(url);
			r = resourceService.load(r);
			if(r != null){
				map.put("code", 202);
				map.put("msg", "资源链接已存在");
				return map;
			}
			r = new Resource();
			r.setName(name);
			r = resourceService.load(r);
			if(r != null){
				map.put("code", 203);
				map.put("msg", "资源名已被使用");
				return map;
			}
			res = new Resource();
			res.setDescription(description);
			res.setDeveloper(developer);
			res.setKey(key);
			res.setName(name);
			res.setPid(pid);
			res.setReturnType(returnType);
			res.setReturnValue(returnValue);
			res.setStatus(status);
			res.setType(type);
			res.setUrl(url);
			resourceService.insert(res);
			map.put("code", 200);
			map.put("msg", "保存成功");
			return map;
		}
		Resource r = new Resource();
		r.setKey(key);
		r = resourceService.load(r);
		if(r != null && id != r.getId().intValue()){
			map.put("code", 201);
			map.put("msg", "资源标识已被使用");
			return map;
		}
		r = new Resource();
		r.setUrl(url);
		r = resourceService.load(r);
		if(r != null && id != r.getId().intValue()){
			map.put("code", 202);
			map.put("msg", "资源链接已存在");
			return map;
		}
		r = new Resource();
		r.setName(name);
		r = resourceService.load(r);
		if(r != null && id != r.getId().intValue()){
			map.put("code", 203);
			map.put("msg", "资源名已被使用");
			return map;
		}
		res.setDescription(description);
		res.setDeveloper(developer);
		res.setKey(key);
		res.setName(name);
		res.setPid(pid);
		res.setReturnType(returnType);
		res.setReturnValue(returnValue);
		res.setStatus(status);
		res.setType(type);
		res.setUrl(url);
		resourceService.update(res);
		map.put("code", 200);
		map.put("msg", "更新成功");
		return map;
	}
	
}
