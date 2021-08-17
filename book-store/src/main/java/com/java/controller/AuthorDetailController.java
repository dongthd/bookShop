package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.common.ServiceCommon;
import com.java.entity.Author;
import com.java.entity.Book;
import com.java.repository.AuthorRepositoy;
import com.java.repository.BookRepository;

@Controller
public class AuthorDetailController {

	@Autowired
	AuthorRepositoy authorRepositoy;
	
	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	ServiceCommon serviceCommon;

	public AuthorDetailController(ServiceCommon serviceCommon) {
		this.serviceCommon = serviceCommon;
	}

	@GetMapping(value = "/authorDetail")
	public String authorDetail(@RequestParam("id") Integer id, Model model) {

		Author authors = authorRepositoy.findById(id).orElse(null);
		model.addAttribute("author", authors);

		listAuthorByBook(model, id);
		listBookByAuthor(model, id);

		return "site/authorDetail";
	}
	
	// author by books
	public void listAuthorByBook(Model model, Integer id) {

		Integer coutnBookByAuthor = authorRepositoy.countBookByAuthorId(id);
		model.addAttribute("coutnBookByAuthor", coutnBookByAuthor);
	}

	// Gợi ý tất cả sách cùng tác giả
	public void listBookByAuthor(Model model, Integer authorId) {
		
		List<Book> bookList = bookRepository.listBookByAuthor(authorId);
		model.addAttribute("bookByAuthor", bookList);

	}
}
