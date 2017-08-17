package com.lmt.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
			HttpServletRequest request,HttpServletResponse response){
		
		return "admin/resource/list";
	}
	
}
