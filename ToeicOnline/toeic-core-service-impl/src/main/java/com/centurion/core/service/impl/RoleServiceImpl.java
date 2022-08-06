package com.centurion.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.centurion.core.dao.RoleDao;
import com.centurion.core.daoimpl.RoleDaoImpl;
import com.centurion.core.dto.RoleDTO;
import com.centurion.core.persistence.entity.RoleEntity;
import com.centurion.core.service.RoleService;
import com.centurion.core.utils.RoleBeanUtil;

public class RoleServiceImpl implements RoleService {

	RoleDao roleDao = new RoleDaoImpl();

	@Override
	public List<RoleDTO> findAll() {
		// TODO Auto-generated method stub
		List<RoleEntity> entities = roleDao.findAll();
		List<RoleDTO> dtos = new ArrayList<RoleDTO>();
		for (RoleEntity item : entities) {
			RoleDTO dto = RoleBeanUtil.entity2Dto(item);
			dtos.add(dto);
		}
		return dtos;
	}

}
