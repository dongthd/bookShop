package com.java.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.common.ServiceCommon;
import com.java.entity.Book;
import com.java.repository.AuthorRepositoy;
import com.java.repository.BookRepository;
import com.java.repository.CategoryRepository;

@Controller
public class ShopController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AuthorRepositoy authorRepositoy;

	@Autowired
	ServiceCommon serviceCommon;

	public ShopController(ServiceCommon serviceCommon) {
		this.serviceCommon = serviceCommon;
	}

	@GetMapping("/products")
	public String shop(Model model, Pageable pageable, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(12);

		Page<Book> bookPage = findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("bookPage", bookPage);
		int totalPages = bookPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		serviceCommon.commonData(model);
		listBook10(model);

		return "site/shop";
	}

	public Page<Book> findPaginated(Pageable pageable) {

		List<Book> bookPage = bookRepository.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Book> list;

		if (bookPage.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, bookPage.size());
			list = bookPage.subList(startItem, toIndex);
		}

		Page<Book> bookPages = new PageImpl<Book>(list, PageRequest.of(currentPage, pageSize), bookPage.size());

		return bookPages;
	}

	// search product
	@GetMapping(value = "/searchProduct")
	public String showsearch(Model model, Pageable pageable, @RequestParam("keyword") String keyword,
			@RequestParam("size") Optional<Integer> size, @RequestParam("page") Optional<Integer> page) {

		List<Book> bookList = bookRepository.searchBook(keyword);

		model.addAttribute("bookPage", bookList);
		serviceCommon.commonData(model);
		return "site/shop";
	}

	// list books by category
	@GetMapping(value = "/bookByCategory")
	public String listBookbyid(Model model, @RequestParam("id") Integer id) {
		List<Book> bookList = bookRepository.listBookByCategory(id);

		model.addAttribute("bookPage", bookList);
		serviceCommon.commonData(model);
		return "site/shop";
	}

	// list books by Author publishingHouse
	@GetMapping(value = "/bookByAuthor")
	public String listBookbyAuthor(Model model, @RequestParam("id") Integer id) {
		List<Book> bookList = bookRepository.listBookByAuthor(id);

		model.addAttribute("bookPage", bookList);
		serviceCommon.commonData(model);
		return "site/shop";
	}

	// list books by publishingHouse
	@GetMapping(value = "/bookByPublishingHouse")
	public String listBookbyPublishingHouse(Model model, @RequestParam("id") Integer id) {
		List<Book> bookList = bookRepository.listBookByPublishingHouse(id);

		model.addAttribute("bookPage", bookList);
		serviceCommon.commonData(model);
		return "site/shop";
	}

	// Hiển thị Top 10 sản phẩm thịnh hành.
	public void listBook10(Model model) {
		List<Object[]> bookList = bookRepository.listBook10();
		if (bookList != null) {
			ArrayList<Integer> listIdBookArrayList = new ArrayList<>();
			for (int i = 0; i < bookList.size(); i++) {
				String id = String.valueOf(bookList.get(i)[0]);
				listIdBookArrayList.add(Integer.valueOf(id));
			}
			List<Book> listBooks = bookRepository.findByInventoryIds(listIdBookArrayList);
			model.addAttribute("listTop10Books", listBooks);
		}

	}

}
