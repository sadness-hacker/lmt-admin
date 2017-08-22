package com.lmt.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.lmt.admin.model.RoleResource;
import com.lmt.orm.mybatis.mapper.IBaseMapper;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@MapperScan
public interface IRoleResourceMapper extends IBaseMapper<RoleResource, Integer> {

	/**
	 * 根据管理员id，资源id获取权限角色资源对象
	 * @param adminId
	 * @param resourceId
	 * @return
	 */
	public RoleResource getByAdminIdResId(
			@Param(value = "adminId") int adminId,
			@Param(value = "resourceId") int resourceId);
	
}
