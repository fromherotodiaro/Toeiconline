package com.centurion.core.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.centurion.core.dao.RoleDao;
import com.centurion.core.daoimpl.RoleDaoImpl;
import com.centurion.core.persistence.entity.RoleEntity;

public class RoleTest {

	/*
	 * @Test public void checkFindAll() {
	 * 
	 * RoleDao roleDao = new RoleDaoImpl(); List<RoleEntity> list =
	 * roleDao.findAll(); }
	 */

	/*
	 * @Test public void checkUpdateRole() { RoleDao roleDao = new RoleDaoImpl();
	 * RoleEntity entity = new RoleEntity(); entity.setRoleid(2);
	 * entity.setName("USER_1"); roleDao.update(entity); }
	 */

	@Test
	public void checkSaveRole() {
		RoleDao roleDao = new RoleDaoImpl();
		RoleEntity entity = new RoleEntity();
		entity.setRoleid(1);
		entity.setName("ADMIN");
		roleDao.update(entity);
		RoleEntity entity1 = new RoleEntity();
		entity1.setRoleid(2);
		entity1.setName("USER");
		roleDao.update(entity1);
	}

	/*
	 * @Test public void checkFindById() { RoleDao roleDao = new RoleDaoImpl();
	 * RoleEntity entity = roleDao.findId(3); }
	 */

	/*
	 * @Test public void checkFindByProperty() { RoleDao roleDao = new
	 * RoleDaoImpl(); String property = null; Object value = null; String
	 * sortExpression = null; String sortDirection = null; Object[] objects =
	 * roleDao.findByProperty(property, value, sortExpression, sortDirection); }
	 */

	/*
	 * @Test public void checkDelete() { List<Integer> listId = new
	 * ArrayList<Integer>(); listId.add(1); listId.add(2); RoleDao roleDao = new
	 * RoleDaoImpl();
	 * 
	 * Integer count = roleDao.delete(listId);
	 * 
	 * }
	 */

}
