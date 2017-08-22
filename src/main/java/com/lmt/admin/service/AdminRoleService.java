package com.lmt.admin.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IAdminRoleMapper;
import com.lmt.admin.model.AdminRole;
import com.lmt.orm.mybatis.service.MybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@Service
public class AdminRoleService extends MybatisBaseService<IAdminRoleMapper, AdminRole, Integer> implements IAdminRoleService {

	@Resource
	private IAdminRoleMapper adminRoleMapper;
	
	@Override
	public AdminRole getByAdminIdRoleId(int adminId, int roleId) {
		AdminRole ar = new AdminRole();
		ar.setAdminId(adminId);
		ar.setRoleId(roleId);
		return adminRoleMapper.load(ar);
	}

}
