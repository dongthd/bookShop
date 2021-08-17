package com.java.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"bookId", "userId"})})
public class Comment implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String content;
	private Integer rate;
	private Date createdAt;
	private Integer updatedAt;
	
	@ManyToOne
	@JoinColumn(name = "bookId")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
}
