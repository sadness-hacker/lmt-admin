package com.lmt.admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IRoleMapper;
import com.lmt.admin.model.Role;
import com.lmt.orm.mybatis.service.MybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@Service
public class RoleService extends MybatisBaseService<IRoleMapper, Role, Integer> implements IRoleService {

	@javax.annotation.Resource
	private IRoleMapper roleMapper;
	
	@Override
	public List<Role> listByAdminId(int adminId) {
		return roleMapper.listByAdminId(adminId);
	}

}
