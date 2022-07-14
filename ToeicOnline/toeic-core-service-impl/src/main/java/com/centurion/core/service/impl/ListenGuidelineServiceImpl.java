package com.centurion.core.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.centurion.core.dao.ListenGuidelineDao;
import com.centurion.core.daoimpl.ListenGuidelineDaoImpl;
import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.persistence.entity.ListenGuidelineEntity;
import com.centurion.core.service.ListenGuidelineService;
import com.centurion.core.utils.ListenGuidelineBeanUtil;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {

	private ListenGuidelineDao listenGuidelineDao = new ListenGuidelineDaoImpl();

	@Override
	public Object[] findListenGuidelineByProperties(String property, Object value, String sortExpression,
			String sortDirection, Integer offset, Integer limit) {
		List<ListenGuidelineDTO> result = new ArrayList<ListenGuidelineDTO>();

		Object[] objects = listenGuidelineDao.findByProperty(property, value, sortExpression, sortDirection, offset,
				limit);

		for (ListenGuidelineEntity item : (List<ListenGuidelineEntity>) objects[1]) {// objects[1] Object[] { totalItem,
																						// list };
			ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
			result.add(dto);
		}

		objects[1] = result;

		return objects;
	}

}
