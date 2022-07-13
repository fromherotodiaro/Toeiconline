package com.centurion.core.service.impl;

import com.centurion.core.dao.UserDao;
import com.centurion.core.daoimpl.UserDaoImpl;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.persistence.entity.UserEntity;
import com.centurion.core.service.UserService;
import com.centurion.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {

	@Override
	public UserDTO isUserExist(UserDTO dto) {
		UserDao userDao = new UserDaoImpl();
		/* UserEntity entity = userDao.isUserExist(dto.getName(), dto.getPassword()); */
		UserEntity entity = userDao.findUserByNameAndPassword(dto.getName(), dto.getPassword());
		return UserBeanUtil.entity2Dto(entity);
	}

	@Override
	public UserDTO findRoleByUser(UserDTO dto) {
		UserDao userDao = new UserDaoImpl();
		UserEntity entity = userDao.findUserByNameAndPassword(dto.getName(), dto.getPassword());
		return UserBeanUtil.entity2Dto(entity);
	}

}
