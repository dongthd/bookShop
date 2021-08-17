package com.java.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.java.entity.Author;
import com.java.entity.Book;
import com.java.entity.Category;
import com.java.entity.Order;
import com.java.repository.AuthorRepositoy;
import com.java.repository.BookRepository;
import com.java.repository.CategoryRepository;
import com.java.repository.OrderRepository;

@Controller
public class IndexController {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AuthorRepositoy authorRepositoy;
	
	@Autowired
	BookRepository bookRepository;

	@Autowired
	OrderRepository orderRepository;

	@GetMapping(value = "/admin/home")
	public String authorList() {

		return "admin/index";
	}

	// show category
	@GetMapping(value = "/admin/categories")
	public String categoryList(Model model) {

		List<Category> listCategories = categoryRepository.findAll();
		model.addAttribute("listCategories", listCategories);
		return "admin/categoryList";
	}

	// show author
	@GetMapping(value = "/admin/authorList")
	public String authorList(Model model) {

		List<Author> listAuthors = authorRepositoy.findAll();
		model.addAttribute("listAuthors", listAuthors);
		return "admin/authorList";
	}

	// show order
	@GetMapping(value = "/admin/orders")
	public String orders(Model model) {

		List<Order> listOrders = orderRepository.findAll();
		model.addAttribute("listOrders", listOrders);
		return "admin/orderList";
	}
	
	// show books
	@GetMapping(value = "/admin/books")
	public String books(Model model) {

		List<Book> listBooks = bookRepository.findAll();
		model.addAttribute("listBooks", listBooks);
		return "admin/bookList";
	}
}
