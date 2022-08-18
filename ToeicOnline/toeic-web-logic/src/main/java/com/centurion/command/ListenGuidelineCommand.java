package com.centurion.command;

import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.web.command.AbstractCommand;

public class ListenGuidelineCommand extends AbstractCommand<ListenGuidelineDTO> {
	public ListenGuidelineCommand() {
		this.pojo = new ListenGuidelineDTO();
	}
}