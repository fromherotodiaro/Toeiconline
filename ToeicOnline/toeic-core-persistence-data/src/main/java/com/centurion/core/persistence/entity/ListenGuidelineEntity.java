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
	private Integer listenGuidelineId;

	private String title;
	private String image;
	private String content;
	@Column(name = "createddate")
	private Timestamp createdDate;
	@Column(name = "modifieddate")
	private Timestamp modifiedDate;

	@OneToMany(mappedBy = "listenGuideline", fetch = FetchType.LAZY)
	private List<CommentEntity> commentList;

	public ListenGuidelineEntity() {

	}

	public ListenGuidelineEntity(Integer listenGuidelineid, String tittle, String image, String content,
			Timestamp createdDate, Timestamp modifiedDate) {

		this.listenGuidelineId = listenGuidelineid;
		this.title = tittle;
		this.image = image;
		this.content = content;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

	public Integer getListenGuidelineid() {
		return listenGuidelineId;
	}

	public void setListenGuidelineid(Integer listenGuideLineid) {
		this.listenGuidelineId = listenGuideLineid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String tittle) {
		this.title = tittle;
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
