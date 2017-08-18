package com.lmt.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.lmt.admin.model.Resource;
import com.lmt.orm.mybatis.mapper.IBaseMapper;
/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@MapperScan
public interface IResourceMapper extends IBaseMapper<Resource, Integer> {

	/**
	 * 根据角色id查寻资源列表
	 * @param roleId
	 * @return
	 */
	public List<Resource> listByRoleId(@Param(value = "roleId") int roleId);
	
	/**
	 * 根据管理员id查寻该管理员的资源列表
	 * @param adminId
	 * @return
	 */
	public List<Resource> listByAdminId(@Param(value = "adminId") int adminId);
	
}
