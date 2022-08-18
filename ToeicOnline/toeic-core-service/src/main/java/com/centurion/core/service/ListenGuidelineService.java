package com.centurion.core.service;

import java.util.Map;

import com.centurion.core.dto.ListenGuidelineDTO;

public interface ListenGuidelineService {

	Object[] findListenGuidelineByProperties(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit);

	ListenGuidelineDTO findByListenGuidelineId(String property, Integer listenGuidelineId);

}
