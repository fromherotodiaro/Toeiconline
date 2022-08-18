package com.centurion.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.persistence.entity.ListenGuidelineEntity;
import com.centurion.core.service.ListenGuidelineService;
import com.centurion.core.service.utils.SingletonDaoUtil;
import com.centurion.core.utils.ListenGuidelineBeanUtil;

public class ListenGuidelineServiceImpl implements ListenGuidelineService {

	@Override
	public Object[] findListenGuidelineByProperties(Map<String, Object> property, String sortExpression,
			String sortDirection, Integer offset, Integer limit) {
		List<ListenGuidelineDTO> result = new ArrayList<ListenGuidelineDTO>();
		Object[] objects = SingletonDaoUtil.getListenGuidelineDaoInstance().findByProperty(property, sortExpression,
				sortDirection, offset, limit);
		for (ListenGuidelineEntity item : (List<ListenGuidelineEntity>) objects[1]) {
			ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(item);
			result.add(dto);
		}
		objects[1] = result;
		return objects;
	}

	@Override
	public ListenGuidelineDTO findByListenGuidelineId(String property, Integer listenGuideLineId) {
		ListenGuidelineEntity entity = SingletonDaoUtil.getListenGuidelineDaoInstance().findEqualUnique(property,
				listenGuideLineId);
		ListenGuidelineDTO dto = ListenGuidelineBeanUtil.entity2Dto(entity);
		return dto;
	}

}
