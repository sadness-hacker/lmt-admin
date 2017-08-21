package com.lmt.admin.service;

import com.lmt.admin.mapper.IAdminMapper;
import com.lmt.admin.model.Admin;
import com.lmt.orm.mybatis.service.IMybatisBaseService;
/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
public interface IAdminService extends IMybatisBaseService<IAdminMapper, Admin, Integer> {

	/**
	 * 添加管理员角色
	 * @param id
	 * @param roleIds
	 */
	public void addAdminRole(int id, int[] roleIds);

}
