package com.centurion.core.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import com.centurion.core.persistence.entity.RoleEntity;



public class UserDTO implements Serializable {
	
	private Integer userId;
	private String name;
	private String password;
	private String fullName;
	private Timestamp createdDate;
	private RoleDTO roleDTO;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public RoleDTO getRoleDTO() {
		return roleDTO;
	}
	public void setRoleDTO(RoleDTO role) {
		this.roleDTO = role;
	}
	
	
}
