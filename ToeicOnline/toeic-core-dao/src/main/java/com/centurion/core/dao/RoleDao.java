package com.centurion.core.dao;

import java.util.List;

import com.centurion.core.data.dao.GerericDao;
import com.centurion.core.persistence.entity.RoleEntity;

public interface RoleDao extends GerericDao<Integer, RoleEntity> {
	List<RoleEntity> findByRole(List<String> roles);
}
