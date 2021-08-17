package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java.entity.Author;

@Repository
public interface AuthorRepositoy extends JpaRepository<Author, Integer> {

	// Hiển thị mỗi tác giả có bao nhiêu tác phẩm
	@Query(value = "SELECT a.author_name,\r\n"
			+ "COUNT(*) AS SoLuong\r\n"
			+ "FROM books b\r\n"
			+ "JOIN authors a ON b.author_id = a.id\r\n"
			+ "GROUP BY a.author_name" , nativeQuery = true)
	List<Object[]> listAuthorByBook();
	
	// Hiển thị một tác giả có bao nhiêu tác phẩm
	@Query(value = "SELECT COUNT(*) AS SoLuong\r\n"
			+ "FROM books b\r\n"
			+ "JOIN authors a ON b.author_id = a.id\r\n"
			+ "where author_id = ?1\r\n"
			+ "GROUP BY a.author_name" , nativeQuery = true)
	Integer countBookByAuthorId( Integer id);
}
