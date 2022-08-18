package com.centurion.core.utils;

import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.persistence.entity.ListenGuidelineEntity;

public class ListenGuidelineBeanUtil {

	public static ListenGuidelineDTO entity2Dto(ListenGuidelineEntity entity) {

		ListenGuidelineDTO dto = new ListenGuidelineDTO();
		dto.setListenGuidelineId(entity.getListenGuidelineid());
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setContent(entity.getContent());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedDate(entity.getModifiedDate());
		return dto;
	}

	public static ListenGuidelineEntity dto2Entity(ListenGuidelineDTO dto) {
		ListenGuidelineEntity entity = new ListenGuidelineEntity();
		entity.setListenGuidelineid(dto.getListenGuidelineId());
		entity.setImage(dto.getImage());
		entity.setContent(dto.getContent());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedDate(dto.getModifiedDate());
		return entity;
	}

}
