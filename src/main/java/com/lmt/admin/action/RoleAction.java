package com.lmt.admin.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lmt.admin.model.Role;
import com.lmt.admin.service.IResourceService;
import com.lmt.admin.service.IRoleResourceService;
import com.lmt.admin.service.IRoleService;
import com.lmt.common.action.BaseAction;
import com.lmt.orm.common.model.PaginationModel;

/**
 * 
 * @author ducx
 * @date 2017-08-17
 *
 */
@Controller
@RequestMapping(value="/admin/role")
public class RoleAction extends BaseAction {
	
	@javax.annotation.Resource
	private IRoleService roleService;
	
	@javax.annotation.Resource
	private IRoleResourceService roleResourceService;
	
	@javax.annotation.Resource
	private IResourceService resourceService;

	/**
	 * 获取角色列表
	 * @param currPage
	 * @param limit
	 * @param keyword
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list",name="获取角色列表")
	public String list(
			@RequestParam(value="currPage",defaultValue="1") int currPage,
			@RequestParam(value="limit",defaultValue="10") int limit,
			@RequestParam(value="keyword",required=false) String keyword,
			HttpServletRequest request,HttpServletResponse response){
		PaginationModel<Role> pageModel = new PaginationModel<Role>();
		pageModel.setCurrPage(1);
		pageModel.setLimit(limit);
		pageModel = roleService.queryByPagination(pageModel);
		request.setAttribute("pageModel", pageModel);
		return "admin/role/list";
	}
	
	/**
	 * 角色编辑
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/edit",name="角色编辑")
	public String edit(
			@RequestParam(value="id",defaultValue="0") int id,
			HttpServletRequest request,HttpServletResponse response){
		if(id > 0){
			Role role = roleService.get(id);
			request.setAttribute("role", role);
		}
		return "admin/role/edit";
	}
	
	/**
	 * 保存角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save",name="保存角色")
	@ResponseBody
	public Map<String,Object> save(
			@RequestParam(value="id",defaultValue="0") int id,
			@RequestParam(value="name") String name,
			@RequestParam(value="status") int status,
			@RequestParam(value="description") String description,
			HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("success", true);
		map.put("code", 200);
		Role role = roleService.get(id);
		if(role == null){
			role = new Role();
			role.setName(name);
			role = roleService.load(role);
			if(role != null){
				map.put("success", true);
				map.put("code", 201);
				map.put("msg", "相同角色名存在");
				return map;
			}
			role = new Role();
			role.setName(name);
			role.setStatus(status);
			role.setDescription(description);
			roleService.insert(role);
			map.put("msg", "添加成功");
		}else{
			Role role1 = new Role();
			role1.setName(name);
			role1 = roleService.load(role1);
			if(role1 != null && id != role1.getId().intValue()){
				map.put("success", true);
				map.put("code", 201);
				map.put("msg", "相同角色名存在");
				return map;
			}
			role.setDescription(description);
			role.setName(name);
			roleService.update(role);
			map.put("msg", "修改成功");
		}
		return map;
	}
	
	
	
}
