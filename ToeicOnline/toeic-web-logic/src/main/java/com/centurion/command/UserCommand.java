package com.centurion.command;

import java.util.List;

import com.centurion.core.dto.RoleDTO;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
	private String confirmPassword;
	private List<RoleDTO> roles;
	
	

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public UserCommand() {
		this.pojo = new UserDTO();
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
