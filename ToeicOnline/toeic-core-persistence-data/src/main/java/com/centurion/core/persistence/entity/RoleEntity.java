package com.centurion.core.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class RoleEntity {

	@Id
	@Column(name = "roleid")
	private Integer roleId;
	private String name;
	
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<UserEntity> userList; 

	public RoleEntity() {

	}

	public RoleEntity(Integer roleid, String name) {

		this.roleId = roleid;
		this.name = name;
	}

	public Integer getRoleid() {
		return roleId;
	}

	public void setRoleid(Integer roleid) {
		this.roleId = roleid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
