package com.centurion.command;

import java.util.List;

import com.centurion.core.dto.RoleDTO;
import com.centurion.core.dto.UserDTO;
import com.centurion.core.dto.UserImportDTO;
import com.centurion.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
	public UserCommand() {
		this.pojo = new UserDTO();
	}

	private String confirmPassword;
	private List<RoleDTO> roles;
	private Integer roleId;
	// private UserImportDTO userImportDTO;
	private List<UserImportDTO> userImportDTOS;

	public List<RoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDTO> roles) {
		this.roles = roles;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<UserImportDTO> getUserImportDTOS() {
		return userImportDTOS;
	}

	public void setUserImportDTOS(List<UserImportDTO> userImportDTOS) {
		this.userImportDTOS = userImportDTOS;
	}

//	public UserImportDTO getUserImportDTO() {
//		return userImportDTO;
//	}
//
//	public void setUserImportDTO(UserImportDTO userImportDTO) {
//		this.userImportDTO = userImportDTO;
//	}

}
