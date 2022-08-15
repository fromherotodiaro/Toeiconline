package com.centurion.api.test;

import org.testng.annotations.Test;

import com.centurion.core.dao.RoleDao;
import com.centurion.core.daoimpl.RoleDaoImpl;
import com.centurion.core.persistence.entity.RoleEntity;

public class ImportTest {

	@Test
	public void testImport() {
		RoleDao roleDao = new RoleDaoImpl();
		RoleEntity entity = roleDao.findEqualUnique("name", "USER");
		System.out.println(entity.getName());
	}
}
