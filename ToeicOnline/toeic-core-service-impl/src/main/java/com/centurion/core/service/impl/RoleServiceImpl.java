package com.centurion.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.centurion.core.dto.RoleDTO;
import com.centurion.core.persistence.entity.RoleEntity;
import com.centurion.core.service.RoleService;
import com.centurion.core.service.utils.SingletonDaoUtil;
import com.centurion.core.utils.RoleBeanUtil;

public class RoleServiceImpl implements RoleService {

	@Override
	public List<RoleDTO> findAll() {
		// TODO Auto-generated method stub
		List<RoleEntity> entities = SingletonDaoUtil.getRoleDaoInstance().findAll();
		List<RoleDTO> dtos = new ArrayList<RoleDTO>();
		for (RoleEntity item : entities) {
			RoleDTO dto = RoleBeanUtil.entity2Dto(item);
			dtos.add(dto);
		}
		return dtos;
	}

}
