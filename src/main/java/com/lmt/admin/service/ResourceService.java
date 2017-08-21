package com.lmt.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IResourceMapper;
import com.lmt.admin.model.Resource;
import com.lmt.orm.mybatis.service.MybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-17
 *
 */
@Service
public class ResourceService extends MybatisBaseService<IResourceMapper, Resource, Integer> implements IResourceService {

	@javax.annotation.Resource
	private IResourceMapper resourceMapper;
	
	@Override
	public List<Resource> listByRoleId(int roleId) {
		return resourceMapper.listByRoleId(roleId);
	}

}
