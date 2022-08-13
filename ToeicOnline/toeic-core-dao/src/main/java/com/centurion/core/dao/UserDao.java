package com.centurion.core.dao;

import java.util.List;

import com.centurion.core.data.dao.GerericDao;
import com.centurion.core.persistence.entity.UserEntity;

public interface UserDao extends GerericDao<Integer, UserEntity> {
	/*
	 * UserEntity isUserExist(String name, String password);
	 * 
	 * UserEntity findRoleByUser(String name, String password);
	 */

//	UserEntity findUserByNameAndPassword(String name, String password);
	Object[] checkLogin(String name, String password);

	List<UserEntity> findByUsers(List<String> names);
}
