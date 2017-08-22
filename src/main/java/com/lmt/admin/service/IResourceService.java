package com.lmt.admin.service;

import java.util.List;

import com.lmt.admin.mapper.IResourceMapper;
import com.lmt.admin.model.Resource;
import com.lmt.orm.mybatis.service.IMybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-14
 *
 */
public interface IResourceService extends IMybatisBaseService<IResourceMapper, Resource, Integer> {

	/**
	 * 根据角色id查寻资源
	 * @param roleId
	 * @return
	 */
	public List<Resource> listByRoleId(int roleId);

	/**
	 * 根据url获取权限
	 * @param url
	 * @return
	 */
	public Resource getResourceBy(String url);
	
	/**
	 * 根据adminId获取资源
	 * @param adminId
	 * @return
	 */
	public List<Resource> listByAdminId(int adminId);
	
}
