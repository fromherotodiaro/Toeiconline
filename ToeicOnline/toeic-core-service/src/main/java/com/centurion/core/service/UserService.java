package com.centurion.core.service;

import com.centurion.core.dto.UserDTO;

public interface UserService {
	UserDTO isUserExist(UserDTO dto);

	UserDTO findRoleByUser(UserDTO dto);

}
