package com.lmt.admin.service;

import com.lmt.admin.mapper.IAdminRoleMapper;
import com.lmt.admin.model.AdminRole;
import com.lmt.orm.mybatis.service.IMybatisBaseService;
/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
public interface IAdminRoleService extends IMybatisBaseService<IAdminRoleMapper, AdminRole, Integer> {

	/**
	 * 根据管理员id，角色id获取
	 * @param adminId
	 * @param roleId
	 * @return
	 */
	public AdminRole getByAdminIdRoleId(int adminId,int roleId);
	
}
