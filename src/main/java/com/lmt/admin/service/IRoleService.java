package com.lmt.admin.service;

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

}
