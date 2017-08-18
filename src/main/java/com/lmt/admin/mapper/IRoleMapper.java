package com.lmt.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.lmt.admin.model.Role;
import com.lmt.orm.mybatis.mapper.IBaseMapper;
/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@MapperScan
public interface IRoleMapper extends IBaseMapper<Role, Integer> {

	/**
	 * 根据管理员id查寻该管理员角色
	 * @param adminId
	 * @return
	 */
	public List<Role> listByAdminId(@Param(value = "adminId") int adminId);
	
}
