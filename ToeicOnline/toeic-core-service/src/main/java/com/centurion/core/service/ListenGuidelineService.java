package com.centurion.core.service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

import org.hibernate.exception.ConstraintViolationException;

import com.centurion.core.dto.ListenGuidelineDTO;

public interface ListenGuidelineService {

	Object[] findListenGuidelineByProperties(Map<String, Object> property, String sortExpression, String sortDirection,
			Integer offset, Integer limit);

	ListenGuidelineDTO findByListenGuidelineId(String property, Integer listenGuidelineId);

	void saveListenGuideline(ListenGuidelineDTO dto)
			throws ConstraintViolationException, SQLIntegrityConstraintViolationException, Exception;

	ListenGuidelineDTO updateListenGuideline(ListenGuidelineDTO dto);

}
