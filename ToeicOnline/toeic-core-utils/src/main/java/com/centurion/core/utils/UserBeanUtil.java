package com.centurion.core.utils;

import com.centurion.core.dto.UserDTO;
import com.centurion.core.persistence.entity.UserEntity;

public class UserBeanUtil {

	public static UserDTO entity2Dto(UserEntity entity) {

		UserDTO dto = new UserDTO();
		dto.setUserId(entity.getUserId());
		dto.setName(entity.getName());
		dto.setPassword(entity.getPassword());
		dto.setFullName(entity.getFullName());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setRoleDTO(RoleBeanUtil.entity2Dto(entity.getRoleEntity()));

		return dto;
	}

	public static UserEntity dto2Entity(UserDTO dto) {

		UserEntity entity = new UserEntity();
		entity.setUserId(dto.getUserId());
		entity.setName(dto.getName());
		entity.setPassword(dto.getPassword());
		entity.setFullName(dto.getFullName());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setRoleEntity(RoleBeanUtil.dto2Entity(dto.getRoleDTO()));

		return entity;
	}

}
