package com.centurion.core.service;

import java.util.List;
import java.util.Map;

import com.centurion.core.dto.CheckLogin;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.dto.UserImportDTO;

public interface UserService {
//	UserDTO isUserExist(UserDTO dto);
//
//	UserDTO findRoleByUser(UserDTO dto);

	Object[] findByProperty(Map<String, Object> property, String sortExpression, String sortDirection, Integer offset,
			Integer limit);

	UserDTO findById(Integer userId);

	void saveUser(UserDTO userDTO);

	UserDTO updateUser(UserDTO userDTO);

	CheckLogin checkLogin(String name, String password);

	void validateImportUser(List<UserImportDTO> userImportDTOs);
}
