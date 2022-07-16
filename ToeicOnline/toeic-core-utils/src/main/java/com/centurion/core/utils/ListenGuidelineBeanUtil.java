package com.centurion.core.utils;

import com.centurion.core.dto.ListenGuidelineDTO;
import com.centurion.core.persistence.entity.ListenGuidelineEntity;

public class ListenGuidelineBeanUtil {

	public static ListenGuidelineDTO entity2Dto(ListenGuidelineEntity entity) {
//		private String tittle;
//		private String image;
//		private String content;
//		private Timestamp createdDate;
//		private Timestamp modifiedDate;

		ListenGuidelineDTO dto = new ListenGuidelineDTO();
		dto.setListenGuideLineId(entity.getListenGuideLineid());
		dto.setTitle(entity.getTitle());
		dto.setImage(entity.getImage());
		dto.setContent(entity.getContent());
		dto.setCreatedDate(entity.getCreatedDate());
		dto.setModifiedDate(entity.getModifiedDate());
		return dto;
	}

	public static ListenGuidelineEntity dto2Entity(ListenGuidelineDTO dto) {
		ListenGuidelineEntity entity = new ListenGuidelineEntity();
		entity.setListenGuideLineid(dto.getListenGuideLineId());
		entity.setImage(dto.getImage());
		entity.setContent(dto.getContent());
		entity.setCreatedDate(dto.getCreatedDate());
		entity.setModifiedDate(dto.getModifiedDate());
		return entity;
	}

}
