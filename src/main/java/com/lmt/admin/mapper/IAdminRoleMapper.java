package com.lmt.admin.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.lmt.admin.model.AdminRole;
import com.lmt.orm.mybatis.mapper.IBaseMapper;
/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@MapperScan
public interface IAdminRoleMapper extends IBaseMapper<AdminRole, Integer> {

}
