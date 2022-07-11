package com.centurion.tests;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import com.centurion.core.dao.UserDao;
import com.centurion.core.daoimpl.UserDaoImpl;
import com.centurion.core.persistence.entity.UserEntity;

public class LoginTest {
	private final Logger log = Logger.getLogger(this.getClass());

	@Test
	public void checkIsUserExist() {
		UserDao userDao = new UserDaoImpl();
		String name = "centurion";
		String password = "123456";
		UserEntity entity = userDao.isUserExist(name, password);
		if (entity != null) {
			log.error("login success");
		} else {
			log.error("login fail");
		}

	}

	@Test
	public void checkFindRoleByUser() {
		UserDao userDao = new UserDaoImpl();
		String name = "centurion";
		String password = "123456";
		UserEntity entity = userDao.findRoleByUser(name, password);
		log.error(entity.getRoleEntity().getRoleid() + "-" + entity.getRoleEntity().getName());
	}

}
