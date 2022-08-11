package com.centurion.core.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centurion.core.dto.CheckLogin;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.persistence.entity.UserEntity;
import com.centurion.core.service.UserService;
import com.centurion.core.service.utils.SingletonDaoUtil;
import com.centurion.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {

	@Override
	public Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit) {
		Object[] objects = SingletonDaoUtil.getUserDaoInstance().findByProperty(property, sortExpression, sortDirection,
				offset, limit);
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
		UserEntity entity = SingletonDaoUtil.getUserDaoInstance().findById(userId);
		UserDTO dto = UserBeanUtil.entity2Dto(entity);
		return dto;
	}

	@Override
	public void saveUser(UserDTO userDTO) {
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		userDTO.setCreatedDate(createDate);
		UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
		SingletonDaoUtil.getUserDaoInstance().save(entity);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO) {
		Timestamp createDate = new Timestamp(System.currentTimeMillis());
		userDTO.setCreatedDate(createDate);
		UserEntity entity = UserBeanUtil.dto2Entity(userDTO);
		SingletonDaoUtil.getUserDaoInstance().update(entity);
		userDTO = UserBeanUtil.entity2Dto(entity);
		return userDTO;
	}

	@Override
	public CheckLogin checkLogin(String name, String password) {
		CheckLogin checkLogin = new CheckLogin();
		if (name != null && password != null) {
			Object[] objects = SingletonDaoUtil.getUserDaoInstance().checkLogin(name, password);
			checkLogin.setUserExist((Boolean) objects[0]);
			if (checkLogin.isUserExist()) {
				checkLogin.setRoleName(objects[1].toString());
			}
		}
		return checkLogin;
	}

}
