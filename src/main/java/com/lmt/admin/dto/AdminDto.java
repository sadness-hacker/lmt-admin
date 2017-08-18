package com.lmt.admin.dto;

import java.util.List;

import com.lmt.admin.model.Admin;
import com.lmt.admin.model.Role;
/**
 * 
 * @author ducx
 * @date 2017-08-18
 *
 */
public class AdminDto extends Admin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Role> roleList;
	
	public AdminDto(){
		
	}
	
	public AdminDto(Admin admin){
		if(admin == null){
			return;
		}
		this.setAvatar(admin.getAvatar());
		this.setBrief(admin.getBrief());
		this.setEmail(admin.getEmail());
		this.setId(admin.getId());
		this.setPassword(admin.getPassword());
		this.setPhoneNum(admin.getPhoneNum());
		this.setRealname(admin.getRealname());
		this.setSalt(admin.getSalt());
		this.setStatus(admin.getStatus());
		this.setUsername(admin.getUsername());
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	
}
