package com.centurion.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centurion.core.dao.UserDao;
import com.centurion.core.daoimpl.UserDaoImpl;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.persistence.entity.UserEntity;
import com.centurion.core.service.UserService;
import com.centurion.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {
	UserDao userDao = new UserDaoImpl();

	@Override
	public UserDTO isUserExist(UserDTO dto) {
		/* UserEntity entity = userDao.isUserExist(dto.getName(), dto.getPassword()); */
		UserEntity entity = userDao.findUserByNameAndPassword(dto.getName(), dto.getPassword());
		return UserBeanUtil.entity2Dto(entity);
	}

	@Override
	public UserDTO findRoleByUser(UserDTO dto) {
		UserEntity entity = userDao.findUserByNameAndPassword(dto.getName(), dto.getPassword());
		return UserBeanUtil.entity2Dto(entity);
	}

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		Object[] objects = userDao.findByProperty(property, sortExpression, sortDirection, offset, limit);
		List<UserDTO> userDTOS = new ArrayList<UserDTO>();
		for (UserEntity item : (List<UserEntity>) objects[1]) {
			UserDTO userDTO = UserBeanUtil.entity2Dto(item);
			userDTOS.add(userDTO);
		}
		objects[1] = userDTOS;
		return objects;
	}

	@Override
	public UserDTO findById(Integer userId) {
		UserEntity entity = userDao.findById(userId);
		UserDTO dto = UserBeanUtil.entity2Dto(entity);
		return dto;
	}

}
