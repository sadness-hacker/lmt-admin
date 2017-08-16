package com.lmt.admin.service;

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

}
