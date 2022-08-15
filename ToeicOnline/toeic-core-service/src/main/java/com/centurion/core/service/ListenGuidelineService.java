package com.centurion.core.service;

import java.util.Map;

public interface ListenGuidelineService {

	Object[] findListenGuidelineByProperties(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit);

}
