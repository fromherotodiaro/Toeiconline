package com.centurion.command;

import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.web.command.AbstractCommand;

public class ListenGuideLineCommand extends AbstractCommand<ListenGuidelineDTO> {

	public ListenGuideLineCommand() {
		this.pojo = new ListenGuidelineDTO();
	}

}
