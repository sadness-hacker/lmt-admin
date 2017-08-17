package com.lmt.admin.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author ducx
 * @date 2017-08-16
 * 资源
 *
 */
@Entity
@Table(name="lmt_resource")
public class Resource implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	/**
	 * 资源类型：menu(菜单),url(链接),button(按钮)
	 */
	@Column(name="type")
	private String type;
	/**
	 * 
	 * 资源味一标识，一般和name相同
	 * menu/button类型：元素id
	 * url类型：链接地址
	 * 
	 */
	@Column(name="c_key")
	private String key;
	/**
	 * 链接地址
	 */
	@Column(name="url")
	private String url;
	
	/**
	 * 
	 * 返回值类型：page(页面),json(json字符串),redirect(重定向)
	 * 默认值json
	 * 
	 */
	@Column(name="return_type")
	private String returnType;
	/**
	 * 验证不通过时的返回值
	 * redirect：登录页,默认值/admin/login
	 * page:指定jsp页面,默认值/WEB-INF/views/admin/login.jsp
	 * json:指定的字符串,默认值为{"success":true,"code":400,"msg":"ADMIN_NOT_LOGIN"}
	 * 
	 */
	@Column(name="return_value")
	private String returnValue;
	
	@Column(name="description")
	private String description;
	
	@Column(name="status")
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public String getReturnValue() {
		return returnValue;
	}

	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	
}
