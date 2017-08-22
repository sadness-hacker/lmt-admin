package com.lmt.admin.service;

import com.lmt.admin.mapper.IRoleResourceMapper;
import com.lmt.admin.model.RoleResource;
import com.lmt.orm.mybatis.service.IMybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
public interface IRoleResourceService extends IMybatisBaseService<IRoleResourceMapper, RoleResource, Integer> {
	/**
	 * 根据adminId,资源id获取角色资源对象
	 * @param adminId
	 * @param resourceId
	 * @return
	 */
	public RoleResource getByAdminIdResId(int adminId, int resourceId);
	
}
