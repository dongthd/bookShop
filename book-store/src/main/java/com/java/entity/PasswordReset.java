package com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(name = "password_resets")
public class PasswordReset implements Serializable{
	
	private String email;
	private String token;
	private Date createdAt;
	
}
