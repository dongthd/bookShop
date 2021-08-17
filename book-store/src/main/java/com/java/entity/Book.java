package com.java.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "book_name")
	private String bookName;
	private String description;
	@Column(name = "publish_date")
	private Date publishDate;
	private Integer suggest;
	@Column(name = "publishing_house")
	private String publishingHouse;
	private String translator;
	@Column(name = "number_of_pages")
	private Integer numberOfPages;
	private Integer quality;
	private Integer price;
	@Column(name = "cover_price")
	private Integer coverPrice;
	@Column(name = "book_image")
	public String bookImage;
	private String images;
	@Column(name = "created_at")
	private Date createdAt;
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Companie companie;
	
	@OneToMany(mappedBy = "book")
	private List<OrderDetail> orderDetails;
	
	@OneToMany(mappedBy = "book")
	private List<Comment> comments;
	
	@OneToMany(mappedBy = "book")
	private List<Save> saves;
	
}
