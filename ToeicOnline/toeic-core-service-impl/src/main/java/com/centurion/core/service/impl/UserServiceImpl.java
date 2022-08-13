package com.centurion.core.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.centurion.core.dto.CheckLogin;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.dto.UserImportDTO;
import com.centurion.core.persistence.entity.RoleEntity;
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

	@Override
	public void validateImportUser(List<UserImportDTO> userImportDTOs) {
		List<String> names = new ArrayList<String>();
		List<String> roles = new ArrayList<String>();
		for (UserImportDTO item : userImportDTOs) {
			names.add(item.getUserName());
			if (!roles.contains(item.getRoleName())) {
				roles.add(item.getRoleName());
			}
		}
		Map<String, UserEntity> userEntityMap = new HashMap<String, UserEntity>();
		Map<String, RoleEntity> roleEntityMap = new HashMap<String, RoleEntity>();
		if (names.size() > 0) {
			List<UserEntity> userEntities = SingletonDaoUtil.getUserDaoInstance().findByUsers(names);
			for (UserEntity item : userEntities) {
				userEntityMap.put(item.getName().toUpperCase(), item);
			}
		}
		if (roles.size() > 0) {
			List<RoleEntity> roleEntities = SingletonDaoUtil.getRoleDaoInstance().findByRole(roles);
			for (RoleEntity item : roleEntities) {
				roleEntityMap.put(item.getName().toUpperCase(), item);
			}
		}
		for (UserImportDTO item : userImportDTOs) {
			String message = item.getError();
			if (item.isValid()) {
				UserEntity userEntity = userEntityMap.get(item.getUserName().toUpperCase());
				if (userEntity != null) {
					message += "<br/>";
					message += "Tên đăng nhập tồn tại";
				}
				RoleEntity roleEntity = roleEntityMap.get(item.getRoleName().toUpperCase());
				if (roleEntity == null) {
					message += "<br/>";
					message += "Vai trò không tồn tại";
				}
				if (StringUtils.isNotBlank(message)) {
					item.setValid(false);
					item.setError(message.substring(5));
				}

			}
		}
	}

}
