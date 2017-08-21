package com.lmt.admin.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lmt.admin.mapper.IRoleMapper;
import com.lmt.admin.mapper.IRoleResourceMapper;
import com.lmt.admin.model.Role;
import com.lmt.admin.model.RoleResource;
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
	
	@Resource
	private IRoleResourceMapper roleResourceMapper;
	
	@Override
	public List<Role> listByAdminId(int adminId) {
		return roleMapper.listByAdminId(adminId);
	}

	@Override
	public void addRoleResource(Integer roleId, String[] srcIds) {
		Set<Integer> set = new HashSet<Integer>();
		for(String srcId : srcIds){
			int si = Integer.parseInt(srcId);
			if(si > 0){
				set.add(si);
			}
		}
		RoleResource rr = new RoleResource();
		rr.setRoleId(roleId);
		List<RoleResource> rrList = roleResourceMapper.list(rr);
		for(RoleResource rrs : rrList){
			if(set.contains(rrs.getResourceId())){
				set.remove(rrs.getResourceId());
				if(rrs.getStatus() == 0){
					rrs.setStatus(1);
					roleResourceMapper.update(rrs);
				}
			}else{
				if(rrs.getStatus() == 1){
					rrs.setStatus(0);
					roleResourceMapper.update(rrs);
				}
			}
		}
		for(Integer resId : set){
			rr = new RoleResource();
			rr.setResourceId(resId);
			rr.setRoleId(roleId);
			rr.setStatus(1);
			roleResourceMapper.insert(rr);
		}
	}

}
