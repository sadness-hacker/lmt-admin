package com.lmt.admin.service;

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

}
