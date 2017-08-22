package com.lmt.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IRoleResourceMapper;
import com.lmt.admin.model.RoleResource;
import com.lmt.orm.mybatis.service.MybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@Service
public class RoleResourceService extends MybatisBaseService<IRoleResourceMapper, RoleResource, Integer> implements IRoleResourceService {

	@Resource
	private IRoleResourceMapper roleResourceMapper;
	
	@Override
	public RoleResource getByAdminIdResId(int adminId, int resourceId) {
		return roleResourceMapper.getByAdminIdResId(adminId, resourceId);
	}

}
