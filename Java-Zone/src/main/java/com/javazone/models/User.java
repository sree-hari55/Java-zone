package com.javazone.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="user_id",nullable = false,unique = true)
	protected long userId;
	
	@Column(name="user_nm")
	@NotBlank(message ="user name is required")
	protected String userName;
	
	@Column(name="email")
	protected String email;
	
	@Column(name="password")
	@NotBlank(message="password required")
	protected String password;
}
