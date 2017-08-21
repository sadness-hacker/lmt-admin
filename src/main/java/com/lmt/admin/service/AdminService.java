package com.lmt.admin.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IAdminMapper;
import com.lmt.admin.mapper.IAdminRoleMapper;
import com.lmt.admin.model.Admin;
import com.lmt.admin.model.AdminRole;
import com.lmt.orm.mybatis.service.MybatisBaseService;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 *
 */
@Service
public class AdminService extends MybatisBaseService<IAdminMapper, Admin, Integer> implements IAdminService {

	@Resource
	private IAdminRoleMapper adminRoleMapper;
	
	@Override
	public void addAdminRole(int id, int[] roleIds) {
		Set<Integer> set = new HashSet<Integer>();
		if(roleIds != null){
			for(int roleId : roleIds){
				set.add(roleId);
			}
		}
		//获取该管理员已拥有的所有角色
		AdminRole ar = new AdminRole();
		ar.setAdminId(id);
		List<AdminRole> list = adminRoleMapper.list(ar);
		for(AdminRole r : list){
			//已拥有的角色已包含要添加的角色
			if(set.contains(r.getRoleId())){
				set.remove(r.getRoleId());
				if(r.getStatus().intValue() == 0){
					r.setStatus(1);
					adminRoleMapper.update(r);
				}
			}else{
				//新角色中不包含已拥有的角色则设置原角色为已删除
				if(r.getStatus().intValue() == 1){
					r.setStatus(0);
					adminRoleMapper.update(r);
				}
			}
		}
		//循环插入新的角色
		for(int roleId : set){
			ar = new AdminRole();
			ar.setAdminId(id);
			ar.setRoleId(roleId);
			ar.setStatus(1);
			adminRoleMapper.insert(ar);
		}
	}

}
