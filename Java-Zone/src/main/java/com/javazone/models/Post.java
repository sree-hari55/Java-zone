package com.javazone.models;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="post")
public class Post implements Serializable{


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="post_id",nullable = false,unique = true)
	protected long postId;
	
	@Column(name="title",nullable = false)
	@NotEmpty(message = "title is required")
	protected String title;
	
	@Column(name="content",nullable = false)
	@Lob
	@NotBlank(message = "content required")
	protected String content;
	
	@Column(name="user_nm",nullable = false)
	@NotEmpty(message = "username is required")
	protected String userName;
	
	@Column(name="create_dt",nullable = false)
	protected Instant createdOn;
	
	@Column(name="updated_dt",nullable = false)
	protected Instant updatedOn;
	
	
}
