package com.centurion.core.persistence.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String name;
	private String password;
	@Column(name = "fullname")
	private String fullName;
	@Column(name = "createddate")
	private Timestamp createdDate;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roleid")
	private RoleEntity role;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private List<CommentEntity> commentList;

	public UserEntity() {

	}

	public UserEntity(Integer userId, String name, String password, String fullName, Timestamp createdDate) {
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.fullName = fullName;
		this.createdDate = createdDate;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public RoleEntity getRoleEntity() {
		return role;
	}

	public void setRoleEntity(RoleEntity role) {
		this.role = role;
	}

	public List<CommentEntity> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<CommentEntity> commentList) {
		this.commentList = commentList;
	}

}
