package com.centurion.command;

import com.centurion.core.dto.UserDTO;
import com.centurion.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {

	public UserCommand() {
		this.pojo = new UserDTO();
	}

}
