package com.centurion.core.persistence.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "listenguideline")
public class ListenGuidelineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer listenGuideLineId;

	private String tittle;
	private String image;
	private String content;
	@Column(name = "createddate")
	private Timestamp createdDate;
	@Column(name = "modifieddate")
	private Timestamp modifiedDate;
	
	@OneToMany(mappedBy = "listenGuideLine", fetch = FetchType.LAZY)
	private List<CommentEntity> commentList; 

	public ListenGuidelineEntity() {

	}

	public ListenGuidelineEntity(Integer listenGuideLineid, String tittle, String image, String content,
			Timestamp createdDate, Timestamp modifiedDate) {

		this.listenGuideLineId = listenGuideLineid;
		this.tittle = tittle;
		this.image = image;
		this.content = content;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Integer getListenGuideLineid() {
		return listenGuideLineId;
	}

	public void setListenGuideLineid(Integer listenGuideLineid) {
		this.listenGuideLineId = listenGuideLineid;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public List<CommentEntity> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEntity> commentList) {
		this.commentList = commentList;
	}
	
	

}