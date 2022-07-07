package com.centurion.core.utils;

import com.centurion.core.dto.RoleDTO;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.persistence.entity.RoleEntity;
import com.centurion.core.persistence.entity.UserEntity;

public class RoleBeanUtil {

	public static RoleDTO entity2Dto(RoleEntity roleEntity) {

		RoleDTO dto = new RoleDTO();
		dto.setRoleId(roleEntity.getRoleid());
		dto.setName(roleEntity.getName());
		return dto;
	}
	
	public static  RoleEntity dto2Entity(RoleDTO roleDTO) {

		RoleEntity entity = new RoleEntity();
		entity.setRoleid(roleDTO.getRoleId());
		entity.setName(roleDTO.getName());
		return entity;
	}
	

}
