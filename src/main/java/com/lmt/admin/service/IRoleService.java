package com.lmt.admin.service;

import java.util.List;

import com.lmt.admin.mapper.IRoleMapper;
import com.lmt.admin.model.Role;
import com.lmt.orm.mybatis.service.IMybatisBaseService;
/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
public interface IRoleService extends IMybatisBaseService<IRoleMapper, Role, Integer> {

	/**
	 * 根据adminId获取该admin的角色列表
	 * @param adminId
	 * @return
	 */
	public List<Role> listByAdminId(int adminId);

	/**
	 * 批量添加角色资源
	 * @param id
	 * @param srcIds
	 */
	public void addRoleResource(Integer id, String[] srcIds);

}
